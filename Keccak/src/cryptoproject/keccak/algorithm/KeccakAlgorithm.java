package cryptoproject.keccak.algorithm;

import java.math.BigInteger;

public class KeccakAlgorithm {

    /**
     * Keccak round constants.
     */
    private static final long ROUND_CONST[] = { 0x0000000000000001,
	    0x0000000000008082, 0x800000000000808aL, 0x8000000080008000L,
	    0x000000000000808bL, 0x0000000080000001L, 0x8000000080008081L,
	    0x8000000000008009L, 0x000000000000008aL, 0x0000000000000088L,
	    0x0000000080008009L, 0x000000008000000aL, 0x000000008000808bL,
	    0x800000000000008bL, 0x8000000000008089L, 0x8000000000008003L,
	    0x8000000000008002L, 0x8000000000000080L, 0x000000000000800aL,
	    0x800000008000000aL, 0x8000000080008081L, 0x8000000000008080L,
	    0x0000000080000001L, 0x8000000080008008L };

    /**
     * Keccak rotation constants.
     */
    private static final int ROTATION_CONST[][] = { { 0, 36, 3, 41, 18 },
	    { 1, 44, 10, 45, 2 }, { 62, 6, 43, 15, 61 },
	    { 28, 55, 25, 21, 56 }, { 27, 20, 39, 8, 14 } };

    /**
     * The number of rounds.
     */
    private int numberOfRounds;

    /**
     * Bitrate.
     */
    private int r;
    /**
     * Capacity.
     */
    private int c;
    /**
     * The word length in bits.
     */
    private int w;

    private int b;
    /**
     * Desired output length.
     */
    private int n;
    /**
     * Indicates if the inner states should be documented, default value is
     * false.
     */
    private boolean documentStates = true;
    /**
     * A file representation of the hashing process in every round.
     */
    private StringBuilder algorithmStates;
    /**
     * A file representation of the hashing process of every step in every
     * round.
     */
    private StringBuilder algorithmSteps;

    /**
     * The algorithm constructor.
     * 
     * @param bitrate
     * @param capacity
     * @param outputLength
     */
    public KeccakAlgorithm(int bitrate, int capacity, int outputLength) {

	this.r = bitrate;
	this.c = capacity;
	this.b = r + c;
	this.n = outputLength;
	this.w = b / 25;
	checkArguments();
	calculateRounds();

    }

    /**
     * Calculates the number of rounds of the permutation function with the
     * appropriate formula.
     */
    private void calculateRounds() {

	numberOfRounds = (int) Math.round(Math.log(w) / Math.log(2)) * 2 + 12;

    }

    /**
     * The constructor with some default values.
     * 
     * @param bParam
     *            The b parameter of the algorithm.
     */
    public KeccakAlgorithm(KeccakParams bParam) {

	switch (bParam) {

	case KECCAK_25:
	    b = 25;
	    break;
	case KECCAK_100:
	    b = 100;
	    break;
	case KECCAK_200:
	    b = 200;
	    break;
	case KECCAK_400:
	    b = 400;
	    break;
	case KECCAK_800:
	    b = 800;
	    break;
	case KECCAK_1600:
	    b = 1600;
	    r = 1024;
	    c = 576;
	    n = 1024;

	}

	w = b / 25;
	calculateRounds();

    }

    /**
     * Checks all the algorithm parameters.
     */
    private void checkArguments() {

	if (r < 0 || r % 8 != 0) {

	    throw new KeccakException(
		    "The bitrate has to be a positive multiple of 8.");

	}

	if (n % 8 != 0 || n < 0) {

	    throw new KeccakException(
		    "The output length has to be a positive multiple of 8.");
	}

	if (b != 25 && b != 100 && b != 200 && b != 400 && b != 800
		&& b != 1600) {

	    throw new KeccakException(
		    "The argument b = r + c has to be one of the following: 25, 100, 200, 400, 800, 1600.");

	}

	if (b % 25 != 0) {
	    throw new KeccakException(
		    "The argument b (r + c) has to be a multiple of 25");
	}

	if (w % 2 != 0 && w != 1) {

	    throw new KeccakException(
		    "The word length ( b/25 ) has to be a multiple of 2 or equal to 1.");
	}

    }

    /**
     * Sets the algorithm parameters to a new value.
     * 
     * @param bitrate
     * @param capacity
     * @param outputLength
     */
    public void setParams(int bitrate, int capacity, int outputLength) {

	this.r = bitrate;
	this.c = capacity;
	this.b = r + c;
	this.n = outputLength;
	this.w = b / 25;
	checkArguments();
	calculateRounds();

    }

    /**
     * Sets if the states should be documented.
     * 
     * @param value
     */
    public void setDocumentStates(boolean value) {

	documentStates = value;

    }

    /**
     * The sponge function of the algorithm.
     * 
     * @param hexMessage
     *            The message in hexadecimal coding.
     * @param requestedManifestLength
     * @return
     */
    public String keccak(String hexMessage) {

	algorithmStates = new StringBuilder();
	algorithmSteps = new StringBuilder();
	/**
	 * Java default value of all elements is 0.
	 */
	long S[][] = new long[5][5];

	StringBuilder message = new StringBuilder();
	message.append(hexMessage);

	document("Message length: " + message.length() * 4 + "bits\n", 0);

	/**
	 * Padding to fill the length of the message so that it has the length
	 * of r bits.
	 */
	message = messagePadding(message);

	document("Message to be absorbed: " + message.toString(), 0);

	/**
	 * Absorbing phase of the sponge function.
	 */
	for (int i = 0; i < message.length() * 4 / r; i++) {

	    StringBuilder blockMessage = new StringBuilder();
	    blockMessage.append(message.substring(i * r / 4, (i + 1) * r / 4));

	    /**
	     * Padding to fill the message to the permutation block size.
	     */
	    for (int j = 0; j < c / 8; j++) {

		blockMessage.append("00");

	    }

	    document("--------------------------Block" + i
		    + "---------------------", 0);
	    for (int x = 0; x < 5; x++)
		for (int y = 0; y < 5; y++) {
		    int offset = (x + 5 * y) * (w / 4);
		    String laneMessage = blockMessage.substring(offset, offset
			    + w / 4);
		    S[x][y] ^= stringToLong(laneMessage);
		}

	    S = keccakF(S);
	}

	/**
	 * Squeezing phase.
	 */
	StringBuilder Z = new StringBuilder();
	int outputLength = n;
	while (outputLength > 0) {

	    String matrixString = matrixToString(S);
	    Z.append(matrixString.substring(0, r / 4));
	    outputLength -= r;
	    if (outputLength > 0) {

		S = keccakF(S);

	    }

	}

	return Z.substring(0, n / 4);

    }

    /**
     * Computes the Keccak hash for the given message.
     * 
     * @param message
     *            The message
     * @return The Keccak hash value.
     */
    public String computeHash(String message) {

	/**
	 * Code the string by UTF-8
	 */
	StringBuilder hexMessage = new StringBuilder();
	int byteValue;
	for (byte b : message.getBytes()) {

	    if (b < 0) {

		b ^= 0x10000000;
		byteValue = b;
		byteValue ^= 0x10000000;

	    } else {

		byteValue = b;

	    }
	    String byteHexString = Integer.toHexString(byteValue);
	    if (byteHexString.length() != 2)
		byteHexString = "0" + byteHexString;
	    hexMessage.append(byteHexString);

	}
	/**
	 * Return the hash.
	 */
	return keccak(hexMessage.toString());

    }

    /**
     * Converts the Keccak state to a single string.
     * 
     * @param S
     *            The state.
     * @return
     */
    private String matrixToString(long[][] S) {

	StringBuilder matrixString = new StringBuilder();
	for (int y = 0; y < 5; y++)
	    for (int x = 0; x < 5; x++) {

		matrixString.append(readLane(S[x][y]));

	    }
	return matrixString.toString();

    }

    /**
     * Converts a lane value to a hexadecimal string.
     * 
     * @param l
     *            The lane value.
     * @return The hexadecimal String.
     */
    private String readLane(long l) {

	String laneHex = Long.toHexString(l);
	while (laneHex.length() != w / 4) {
	    laneHex = "0" + laneHex;

	}
	int nrBytes = laneHex.length() / 2;
	StringBuilder temp = new StringBuilder();
	for (int i = nrBytes; i > 0; i--) {

	    temp.append(laneHex.substring(2 * (i - 1), 2 * i));

	}

	return temp.toString();
    }

    /**
     * The 010*1 message padding.
     * 
     * @param message
     *            The message in hexadecimal coding.
     * @return
     */
    private StringBuilder messagePadding(StringBuilder message) {

	if (message.length() % 2 != 0 && message.length() != 0) {
	    throw new KeccakException(
		    "Message character representation is wrong! The message length is: "
			    + message.length());

	}

	if (message.length() % (r / 4) == r / 4 - 2) {

	    return message.append("81");

	}

	message.append("01");

	while (message.length() % (r / 4) != (r / 4 - 2)) {

	    message.append("00");

	}

	return message.append("80");
    }

    /**
     * Keccak round.
     * 
     * @param A
     *            The current state.
     * @param RC
     *            The round constant used in this round.
     * @return The altered state.
     */
    private long[][] keccakRound(long[][] A, long RC) {

	long[] C = new long[5];
	long[] D = new long[5];
	long[][] B = new long[5][5];

	/**
	 * Theta step.
	 */
	for (int x = 0; x < 5; x++)
	    C[x] = A[x][0] ^ A[x][1] ^ A[x][2] ^ A[x][3] ^ A[x][4];
	for (int x = 0; x < 5; x++)
	    D[x] = C[(x + 4) % 5] ^ rotate(C[(x + 1) % 5], 1);
	for (int x = 0; x < 5; x++)
	    for (int y = 0; y < 5; y++) {

		A[x][y] = A[x][y] ^ D[x];

	    }

	document("Theta step:", 2);
	document(stateString(A), 2);

	/**
	 * Rho and pi steps.
	 * 
	 */

	for (int x = 0; x < 5; x++)
	    for (int y = 0; y < 5; y++) {

		B[y][(2 * x + 3 * y) % 5] = rotate(A[x][y],
			ROTATION_CONST[x][y]);

	    }

	document("Rho and pi steps:", 2);
	document(stateString(A), 2);

	/**
	 * Chi step.
	 */
	for (int x = 0; x < 5; x++)
	    for (int y = 0; y < 5; y++) {

		A[x][y] = B[x][y] ^ ((~B[(x + 1) % 5][y]) & B[(x + 2) % 5][y]);

	    }

	document("Chi step:", 2);
	document(stateString(A), 2);

	/**
	 * Iota step.
	 */

	A[0][0] ^= RC;

	document("Iota step:", 2);
	document(stateString(A), 2);

	return A;

    }

    /**
     * Keccak function for block permutation.
     * 
     * @param A
     *            the state to be mutate.
     * @return Mutated state.
     */
    private long[][] keccakF(long[][] A) {

	document("Start state:", 0);
	document(stateString(A), 0);

	for (int i = 0; i < numberOfRounds; i++) {

	    document("--------------------------Round" + (i + 1)
		    + "-----------------------------------------------------",
		    0);
	    A = keccakRound(A, ROUND_CONST[i]);

	    document(stateString(A), 1);
	}

	return A;
    }

    /**
     * Function that turns a String to a long value in terms with the ASCII
     * encoding standard.
     * 
     * @param text
     *            The text to be transformed.
     * @return The long value of the text.
     */
    private long stringToLong(String hexString) {

	StringBuilder temp = new StringBuilder();
	int nrBytes = hexString.length() / 2;
	for (int i = 0; i < nrBytes; i++) {

	    int offset = (nrBytes - i - 1) * 2;
	    temp.append(hexString.substring(offset, offset + 2));

	}
	BigInteger i = new BigInteger(temp.toString(), 16);

	return i.longValue();
    }

    /**
     * Bitwise rotation (to the left) of n bits considering the string of bits
     * is w bits long.
     * 
     * @param x
     * @param n
     * @return
     */
    private long rotate(long x, int n) {

	n = n % this.w;

	if (w == 64)
	    return Long.rotateLeft(x, n);

	return ((x >> (this.w - n)) + (x << n)) % (1 << w);
    }

    /**
     * Converts the state in a matrix format string.
     * 
     * @param A
     *            The state to be converted.
     * @return The string.
     */
    private String stateString(long A[][]) {

	StringBuilder state = new StringBuilder();

	for (int y = 0; y < 5; y++) {
	    state.append("\n[  ");
	    for (int x = 0; x < 5; x++) {

		state.append("'0x" + Long.toHexString(A[x][y]) + "'  ");

	    }
	    state.append("]\n");

	}
	return state.toString();

    }

    /**
     * Documents the algorithm in 2 documents.
     * 
     * @param m
     *            The message to be documented.
     * @param choice
     *            The choice of the document to hold the documentation.
     */
    private void document(String m, int choice) {

	if (documentStates) {

	    switch (choice) {

	    case 0:
		algorithmStates.append("\n\n" + m);
		algorithmSteps.append("\n\n" + m);
		break;
	    case 1:
		algorithmStates.append("\n\n" + m);
		break;
	    case 2:
		algorithmSteps.append("\n\n" + m);
	    }

	}

    }

    public String getDocumentedAlgorithmSteps() {
	return algorithmSteps.toString();
    }

    public String getDocumentedAlgorithmStates() {

	return algorithmStates.toString();

    }

}

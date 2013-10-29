package cryptoproject.keccak.algorithm;

public class Keccak {

    public Keccak(PermutationWidth width) {

	switch (width) {

	case WIDTH_25:
	    break;
	case WIDTH_50:
	    break;
	case WIDTH_100:
	    break;
	case WIDTH_200:
	    break;
	case WIDTH_400:
	    break;
	case WIDTH_800:
	    break;
	case WIDTH_1600:
	    break;
	default:
	    System.err.println("Unknown width");

	}

    }

}

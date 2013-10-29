package cryptoproject.keccak.binary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FixedLengthBinary {

    private Boolean bits[];
    private int number;

    public FixedLengthBinary(Boolean[] bits) {

	this.bits = bits;
	convertToInt(bits);

    }
    
    public FixedLengthBinary(int number, int size) {
	
	this.number = number;
	convertToBooleanArray(number, size);
	
    }

    public Boolean[] getBits() {

	return bits;

    }

    public Boolean bit(int index) {

	return bits[index];

    }

    public void shiftRight(int offset) {
	    
	    number = number >> offset; 
	    number = number & ((int)Math.pow(2, bits.length)-1);
	    convertToBooleanArray(number, bits.length);
	    
	    
	}

    public void shiftLeft(int offset) {
	
	number = number << offset;
	number = number & ((int)Math.pow(2, bits.length)-1);
	convertToBooleanArray(number, bits.length);

    }


    public void rotate(int offset) {
	
	List<Boolean> bitList = Arrays.asList(bits);
	Collections.rotate(bitList, offset);
	bits = (Boolean[])bitList.toArray();
	convertToInt(bits);
	
    }

    private void convertToInt(Boolean[] bits) {

	int n = 0, l = bits.length;
	for (int i = 0; i < l; ++i) {
	    n = (n << 1) + (bits[i].booleanValue() ? 1 : 0);
	}
	number = n;

    }

    private void convertToBooleanArray(int number, int size) {

	if (Math.pow(2, size) < number) {
	    throw new IllegalArgumentException(
		    "Number too large for fixed bit representation");
	}
	bits = new Boolean[size];
	for(int i = size - 1;  i >= 0; i--) {
	    
	    Boolean carry = number % 2 == 1;
	    number /= 2;
	    bits[i] = carry;
	    
	}

    }
    
    @Override
    public String toString() {
        
	return Integer.toBinaryString(number);
	
	
    }
    
    public String hexString() {
	
	return Integer.toHexString(number);
	
    }
    

}

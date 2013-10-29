package cryptoproject.keccak.binary;
import org.junit.Test;


public class BinaryOperationTests {

    
    @Test
    public void rotationTest() {
	
	FixedLengthBinary bin = new FixedLengthBinary(1, 4);
	bin.rotate(2);
	System.out.println(bin.hexString());
	
	
    }
}

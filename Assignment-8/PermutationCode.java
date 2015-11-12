import java.util.*;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
public class PermutationCode{
    /** The original list of characters to be encoded */
    ArrayList<Character> alphabet = 
        new ArrayList<Character>(Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                    't', 'u', 'v', 'w', 'x', 'y', 'z'));

    ArrayList<Character> code = new ArrayList<Character>(26);

    /** A random number generator */
    Random rand = new Random();

    /**
     * Create a new instance of the encoder/decoder with a new permutation code 
     */
    PermutationCode(){
        this.code = this.initEncoder();
    }

    /**
     * Create a new instance of the encoder/decoder with the given code 
     */
    PermutationCode(ArrayList<Character> code){
        this.code = code;
    }

    /** Initialize the encoding permutation of the characters */
    ArrayList<Character> initEncoder(){
    	ArrayList<Character> temp = new ArrayList<Character>(this.alphabet);
    	int index = 0;
    	for (int i = this.alphabet.size() - 1; i >= 0; i++) {
    		index = rand.nextInt(i);
    		this.code.add(temp.get(index));
    		temp.remove(index);
    	}
        return temp;
    }

    /**
     * produce an encoded <code>String</code> from the given <code>String</code>
     * @param source the <code>String</code> to encode
     * @return the secretly encoded <String>
     */
    String encode(String source){
        String temp = "";
        int index = 0;
        for (int i = 0; i < source.length(); i++) {
        	index = transulate(source.charAt(i), this.alphabet);
        	temp = temp + this.code.get(index);
        }
        return temp;
    }

    /**
     * produce an decoded <code>String</code> from the given <code>String</code>
     * @param source the <code>String</code> to decode
     * @return the revealed <String>
     */
    String decode(String code){
        String temp = "";
        int index = 0;
        for (int i = 0; i < code.length(); i++) {
        	index = transulate(code.charAt(i), this.code);
        	temp = temp + this.alphabet.get(index);
        }
        return temp;
    }
   
    int transulate(char letter, ArrayList<Character> array) {
        int count = 0;
        for (char c : array) {
        	if (letter == c) {
        		return count;
        	}
        	count++;
       	}
       	throw new IllegalArgumentException("Char is not found in the alphabet");
    }
}

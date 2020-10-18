
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[] countLetters(String s) {
        int[] indices = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = alphabet.indexOf(Character.toUpperCase(s.charAt(i)));
            if (index != -1) {
                indices[index] += 1;
            }
        }
        return indices;
    }
    
    private int maxIndex(int[] values) {
        if (values.length == 0) {
            return -1;
        }
        int maxInd = 0;
        int maxValue = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxInd = i;
            }
        }
        return maxInd; 
    }
    
    public String BreakCaesarCipher(String s) {
        int posskey = (22 + maxIndex(countLetters(s))) % 26;
        CaesarCipher cc = new CaesarCipher(posskey);
        return cc.decrypt(s);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        System.out.println("original: ");
        System.out.println(input);
        input = cc.encrypt(input);
        System.out.println("after encryption: ");
        System.out.println(input);
        input = BreakCaesarCipher(input);
        System.out.println("after decryption: ");
        System.out.println(input);
    }
}

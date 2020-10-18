
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    public String CaesarCipherTwoBreaker(String s) {
        CaesarCipherTwo cct = new CaesarCipherTwo(0,0);
        String s1 = cct.halfOfString(s, 0);
        String s2 = cct.halfOfString(s, 1);
        TestCaesarCipher tcct = new TestCaesarCipher();
        int key1 = (tcct.maxIndex(tcct.countLetters(s1)) + 22) % 26;
        int key2 = (tcct.maxIndex(tcct.countLetters(s2)) + 22) % 26;
        System.out.println(key1);
        System.out.println(key2);
        cct = new CaesarCipherTwo(key1, key2);
        return cct.decrypt(s);
    }
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        //String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //CaesarCipherTwo cct = new CaesarCipherTwo(14, 24);
        System.out.println("original: ");
        System.out.println(input);
        input = CaesarCipherTwoBreaker(input);
        System.out.println("after decryption: ");
        System.out.println(input);
        //input = CaesarCipherTwoBreaker(input);
        //System.out.println("after decryption: ");
        //System.out.println(input);
    }
}

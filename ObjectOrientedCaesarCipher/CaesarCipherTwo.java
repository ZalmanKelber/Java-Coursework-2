
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private CaesarCipher cc1;
    private CaesarCipher cc2;
    public CaesarCipherTwo(int key1, int key2) {
        cc1 = new CaesarCipher(key1);
        cc2 = new CaesarCipher(key2);
    }
    
    public String encrypt(String input) {
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        s1 = cc1.encrypt(s1);
        s2 = cc2.encrypt(s2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                sb.append(s1.charAt(i / 2));
            } else {
                sb.append(s2.charAt(i / 2));
            }
        }
        return sb.toString();
    }
    
     public String decrypt(String input) {
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        s1 = cc1.decrypt(s1);
        s2 = cc2.decrypt(s2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                sb.append(s1.charAt(i / 2));
            } else {
                sb.append(s2.charAt(i / 2));
            }
        }
        return sb.toString();
    }
    
    private String halfOfString(String s, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i += 2) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

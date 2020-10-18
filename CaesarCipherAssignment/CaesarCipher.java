
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String encrypt(String s, int key) {
        String shifted = alph.substring(key) + alph.substring(0, key);
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            boolean lower = Character.isLowerCase(ch);
            ch = Character.toUpperCase(ch);
            int index = alph.indexOf(ch);
            if (index != -1) {
                char newChar = shifted.charAt(index);
                if (lower) {
                    newChar = Character.toLowerCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
    
    public void testEncrypt() {
        String[] strs = new String[]{"FIRST TEST", "First Test", "CASE W1TH OTHER CH4RACTER$"};
        for (String s : strs) {
            System.out.println(s);
            System.out.println(encrypt(s, 1));
            System.out.println(encrypt(s, 5));
            System.out.println(encrypt(encrypt(s, 5), 21));
        }
        String test = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(test);
        System.out.println(encrypt(test, 15));
    }
    
    public String encryptTwoKeys(String s, int key1, int key2) {
        String shifted1 = alph.substring(key1) + alph.substring(0, key1);
        String shifted2 = alph.substring(key2) + alph.substring(0, key2);
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            boolean lower = Character.isLowerCase(ch);
            ch = Character.toUpperCase(ch);
            int index = alph.indexOf(ch);
            if (index != -1) {
                char newChar = i % 2 == 0 ? shifted1.charAt(index) : shifted2.charAt(index);
                if (lower) {
                    newChar = Character.toLowerCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
    
    public void testEncryptTwoKeys() {
        String test = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(test);
        System.out.println(" --> ");
        System.out.println(encryptTwoKeys(test, 21, 8));
    }
}

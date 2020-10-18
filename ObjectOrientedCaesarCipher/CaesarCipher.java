
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            boolean lower = Character.isLowerCase(ch);
            ch = Character.toUpperCase(ch);
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                if (lower) {
                    newChar = Character.toLowerCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
    
     public String decrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            boolean lower = Character.isLowerCase(ch);
            ch = Character.toUpperCase(ch);
            int index = shiftedAlphabet.indexOf(ch);
            if (index != -1) {
                char newChar = alphabet.charAt(index);
                if (lower) {
                    newChar = Character.toLowerCase(newChar);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
}

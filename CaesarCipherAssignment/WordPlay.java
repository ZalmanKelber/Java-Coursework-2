
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel (char ch) {
        return "AEIOU".contains(String.valueOf(ch).toUpperCase());
    }
    
    public void testIsVowel() {
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'A', 'B', '1', '#'};
        for (char ch: chars) {
            System.out.println(ch + ": " + isVowel(ch));
        }
    }
    
    public String replaceVowels(String s, char ch) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels() {
        String[] strs = new String[]{"These", "", "are", "some", "sample words"};
        for (String s : strs) {
            System.out.println(s + " --> " + replaceVowels(s, '*'));
        }
    }
    
    public String emphasize(String s, char ch) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (String.valueOf(s.charAt(i)).toUpperCase().equals(String.valueOf(ch).toUpperCase())) {
                sb.setCharAt(i, i % 2 == 0 ? '*' : '+');
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize() {
        String[] strs = new String[]{"Mary Bella Abracadabra", "dna ctgaaactga", "These", "", "are", "some", "sample words"};
        for (String s : strs) {
            System.out.println(s + " --> " + emphasize(s, 'a'));
        }
    }
}

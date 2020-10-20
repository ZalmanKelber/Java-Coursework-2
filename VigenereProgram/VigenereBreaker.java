import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hs = new HashSet<String>();
        for (String word : fr.lines()) {
            hs.add(word.toLowerCase());
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int total = 0;
        for (String word : message.toLowerCase().split("\\W+")) {
            total += dictionary.contains(word) ? 1 : 0;
        }
        return total;
    }
    
    public int breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxWords = 0;
        int maxIndex = 0;
        for (int i = 1; i < 100; i++) {
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted, i, mostCommonChar(dictionary)));
            String decrypted = vc.decrypt(encrypted);
            int numWords = countWords(decrypted, dictionary);
            if (numWords > maxWords) {
                maxWords = numWords;
                maxIndex = i;
                if (i == 38) {
                    System.out.println(numWords);
                }
            }
        }
        System.out.println("max words: " + maxWords);
        return maxIndex;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker cc = new CaesarCracker(mostCommon);
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++ ) {
            String subMessage = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(subMessage);
        }
        return key;
    }
    
    public char mostCommonChar(HashSet<String> dictionary) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < 26; i ++) {
            hm.put("abcdefghijklmnopqrstuvwxyz".charAt(i), 0);
        }
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                if (hm.keySet().contains(word.charAt(i))) {
                    hm.put(word.charAt(i), hm.get(word.charAt(i)) + 1);
                }
            }
        }
        char maxChar = 'a';
        int maxCount = 0;
        for (char ch : hm.keySet()) {
            if (hm.get(ch) > maxCount) {
                maxCount = hm.get(ch);
                maxChar = ch;
            }
        }
        return maxChar;
    }
    
    public void testMostCommon() {
        FileResource fr = new FileResource();
        System.out.println("most common character: " + mostCommonChar(readDictionary(fr)));
    }
    
    public void breakVigenere() {
        String[] langs = new String[]{"English","French","Portuguese","German","Dutch","Danish","Spanish","Italian"};
        FileResource encFr = new FileResource();
        String encrypted = encFr.asString();
        int maxWords = 0;
        String bestLang = "";
        String bestDecrypted = "";
        for (String lang : langs) {
            System.out.println(lang);
            FileResource fr = new FileResource("dictionaries/" + lang);
            HashSet<String> dict = readDictionary(fr);
            int i = breakForLanguage(encrypted, dict);
            int[] key = tryKeyLength(encrypted, i, mostCommonChar(dict));
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int words = countWords(decrypted, dict);
            if (words > maxWords) {
                maxWords = words;
                bestLang = lang;
                bestDecrypted = decrypted;
            }
        }
        System.out.println("language: " + bestLang);
        System.out.println(bestDecrypted.substring(0, 200));
    }
    
}

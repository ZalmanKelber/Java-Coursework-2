
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public int countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int l = word.length();
            l -= !Character.isLetter(word.charAt(l - 1)) ? 1 : 0;
            l -= !Character.isLetter(word.charAt(0)) && l > 0 ? 1 : 0;
            l = l >= counts.length ? counts.length - 1 : l;
            counts[l] += 1;
            if (l > 20) {
                System.out.println(word);
            }
        }
        return indexOfMax(counts);
    }
    
    public int indexOfMax(int[] values) {
        if (values.length == 0) {
            return -1;
        }
        int maxIndex = 0;
        int maxValue = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
        
    }
    
    public void testCountWordLengths() {
        int l = 31;
        int[] counts = new int[l];
        FileResource resource = new FileResource();
        int maxIndex = countWordLengths(resource, counts);
            System.out.println("word length with max words = " + maxIndex);
    }
}

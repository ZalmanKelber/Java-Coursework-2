
/**
 * Write a description of CountCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CountCodons {
    private HashMap<String, Integer> hm;
    
    public CountCodons() {
        hm = new HashMap<String, Integer>();
    }
    
    public void buildCodon(int start, String dna) {
        dna = dna.toUpperCase().trim();
        hm.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i+3);
            if (hm.containsKey(codon)) {
                hm.put(codon, hm.get(codon) + 1);
            } else {
                hm.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        String mostCommon = "";
        int freq = 0;
        for (String codon : hm.keySet()) {
            if (hm.get(codon) > freq) {
                freq = hm.get(codon);
                mostCommon = codon;
            }
        }
        return mostCommon;
    }
    
    public void printCodonCounts(int num1, int num2) {
        int count = 0;
        for (String codon : hm.keySet()) {
            if (hm.get(codon) >= num1 && hm.get(codon) <= num2) {
                count++;
                System.out.println("codon: " + codon + "\tcount: " + hm.get(codon));
            }
        }
        System.out.println("total codons: " + count);
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        for (int i = 0; i < 3; i++) {
            buildCodon(i, dna);
            System.out.println("running with start " + i);
            printCodonCounts(1, 10000);
            System.out.println("most common codon is " + getMostCommonCodon());
        }
    }
}

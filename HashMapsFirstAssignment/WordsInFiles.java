
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> hm;
    
    public WordsInFiles() {
        hm = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!hm.containsKey(word)) {
                hm.put(word, new ArrayList<String>());
            }
            ArrayList<String> al = hm.get(word);
            if (al.indexOf(f.getName()) == -1) {
                al.add(f.getName());
                hm.put(word, al);
            }
        }
    }
    
    private void buildWordFileMap() {
        hm.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int max = 0;
        for (String word : hm.keySet()) {
            if (hm.get(word).size() > max) {
                max = hm.get(word).size();
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int n) {
        ArrayList<String> al = new ArrayList<String>();
        for (String word : hm.keySet()) {
            if (hm.get(word).size() == n) {
                al.add(word);
            }
        }
        return al;
    }
    
    private void printFilesIn(String word){
        for (int i = 0; i < hm.get(word).size(); i++) {
            System.out.println(hm.get(word).get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int n = maxNumber();
        System.out.println("max number of files is: " + n);
        ArrayList<String> words = wordsInNumFiles(7);
        System.out.println(words.size());
        words = wordsInNumFiles(4);
        System.out.println(words.size());
        System.out.println("sea");
        printFilesIn("sea");
        System.out.println("tree");
        printFilesIn("tree");
        //for (int i = 0; i < words.size(); i++) {
            //System.out.println(words.get(i));
            //printFilesIn(words.get(i));
        //}
    }
}

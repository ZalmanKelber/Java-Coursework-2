import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> hm;
    
    private ArrayList<String> seen;
    
    private ArrayList<String> categoriesConsidered;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        hm = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        categoriesConsidered = new ArrayList<String>();
        seen = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        hm = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        categoriesConsidered = new ArrayList<String>();
        seen = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = new String[]{"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for (String cat : categories) {
            hm.put(cat, readIt(source + "/" + cat + ".txt"));
        }
        seen = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (categoriesConsidered.indexOf(label) == -1) {
            categoriesConsidered.add(label);
        }
        return randomFrom(hm.get(label));
    }
    
    private boolean containsWord(ArrayList<String> seen, String sub){
        for (int i = 0; i < seen.size(); i++) {
            if (seen.get(i).equals(sub)) {
                return true;
            }
        }
        return false;
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = "";
        do {
            sub = getSubstitute(w.substring(first+1,last));
        } while (containsWord(seen, sub));
        seen.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
        private String fromTemplate(String source){
        seen.clear();
        categoriesConsidered.clear();
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String key : hm.keySet()) {
            total += hm.get(key).size();
        }
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for (int i = 0; i < categoriesConsidered.size(); i++) {
            total += hm.get(categoriesConsidered.get(i)).size();
        }
        return total;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nnumber of words replaced: " + seen.size());
        System.out.println("total words in map: " + totalWordsInMap());
        System.out.println("total words considered: " + totalWordsConsidered());
    }
    


}
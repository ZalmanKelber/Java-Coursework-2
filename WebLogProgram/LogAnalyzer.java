
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public int countUniqueIPs() {
         Set<String> IPs = new HashSet<String>();
         for (LogEntry le : records) {
             IPs.add(le.getIpAddress());
         }
         return IPs.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         Set<String> IPs = new HashSet<String>();
         for (LogEntry le : records) {
             if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                 IPs.add(le.getIpAddress());
             }
         }
         return IPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String date) {
         Set<String> IPs = new HashSet<String>();
         for (LogEntry le : records) {
             if (le.getAccessTime().toString().substring(4,10).equals(date)) {
                 IPs.add(le.getIpAddress());
             }
         }
         return new ArrayList<String>(IPs);
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> hm = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (hm.keySet().contains(ip)) {
                 hm.put(ip, hm.get(ip) + 1);
             } else {
                 hm.put(ip, 1);
             }
         }
         return hm;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> hm) {
         int max = 0;
         for (String ip : hm.keySet()) {
             if (hm.get(ip) > max) {
                 max = hm.get(ip);
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> hm) {
         int max = mostNumberVisitsByIP(hm);
         ArrayList<String> ips = new ArrayList<String>();
         for (String ip : hm.keySet()) {
             if (hm.get(ip) == max) {
                 ips.add(ip);
             }
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             String date = le.getAccessTime().toString().substring(4,10);
             if (!hm.keySet().contains(date)) {
                 ArrayList<String> al = new ArrayList();
                 al.add(le.getIpAddress());
                 hm.put(date, al);
             } else if (!hm.get(date).contains(le.getIpAddress())) {
                 ArrayList<String> al = hm.get(date);
                 al.add(le.getIpAddress());
                 hm.put(date, al);
             }
         }
         return hm;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> hm) {
         int max = 0;
         String dateOfMax = "";
         for (String date : hm.keySet()) {
             int m = hm.get(date).size();
             if (m > max) {
                 max = m;
                 dateOfMax = date;
             }
         }
         return dateOfMax;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hm, String date) {
         HashMap<String, Integer> visits = new HashMap<String, Integer>();
         ArrayList<String> ipsOnDate = hm.get(date);
         for (int i = 0; i < ipsOnDate.size(); i++) {
             visits.put(ipsOnDate.get(i), 0);
         }
         for (LogEntry le : records) {
             String d = le.getAccessTime().toString().substring(4,10);
             if (d.equals(date)) {
                 visits.put(le.getIpAddress(), visits.get(le.getIpAddress())+1);
             }
         }
         return iPsMostVisits(visits);
     }
     
}

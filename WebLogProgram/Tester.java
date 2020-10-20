
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //la.printAll();
        System.out.println("countUniqueIps:\n" + la.countUniqueIPs());
        System.out.println("unique visits on day sep 24:\n" + la.uniqueIPVisitsOnDay("Sep 24"));
        System.out.println("countUniqueIPsInRange(400,499):\n" + la.countUniqueIPsInRange(400,499));
        System.out.println("mostNumberVisitsByIP:\n" + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        System.out.println("iPsMostVisits:\n" + la.iPsMostVisits(la.countVisitsPerIP()));
        System.out.println("days with most visits:\n" + la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println("iPsWithMostVisitsOnDay Sep 30:\n" + la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        System.out.println("unique IP addresses: " + la.countUniqueIPs());
    }
}

import java.io.*;
import java.util.*;

class Event {
    public int p, m, t;
    public Event(int p, int m, int t) {
        this.p = p;
        this.m = m;
        this.t = t;
    }
}

class SickEvent {
    public int p, t;
    public SickEvent(int p, int t) {
        this.p = p;
        this.t = t;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("badmilk.in"));
        int N = sc.nextInt();
        int M = sc.nextInt();
        int D = sc.nextInt();
        int S = sc.nextInt();

        // Store events for each person
        HashMap<Integer, ArrayList<Event>> peopleEvents = new HashMap<>();
        // Store unique milks per person
        HashMap<Integer, ArrayList<Integer>> peopleMilks = new HashMap<>();
        
        for (int i = 0; i < D; i++) {
            int p = sc.nextInt();
            int m = sc.nextInt();
            int t = sc.nextInt();
            Event ev = new Event(p, m, t);

            peopleEvents.putIfAbsent(p, new ArrayList<>());
            peopleEvents.get(p).add(ev);

            // Add milk only once per person
            peopleMilks.putIfAbsent(p, new ArrayList<>());
            if (!peopleMilks.get(p).contains(m)) {
                peopleMilks.get(p).add(m);
            }
        }

        HashMap<Integer, Integer> sickTime = new HashMap<>();
        for (int i = 0; i < S; i++) {
            int p = sc.nextInt();
            int t = sc.nextInt();
            sickTime.put(p, t);
        }

        ArrayList<Integer> suspects = new ArrayList<>();
        boolean first = true;
        ArrayList<Integer> toRemove = new ArrayList<>();

        for (int sickPerson : sickTime.keySet()) {
            int timeSick = sickTime.get(sickPerson);
            ArrayList<Integer> drankBeforeSick = new ArrayList<>();

            for (Event ev : peopleEvents.get(sickPerson)) {
                if (ev.t < timeSick && !drankBeforeSick.contains(ev.m)) {
                    drankBeforeSick.add(ev.m);
                }
            }

            if (first) {
                suspects.addAll(drankBeforeSick);
                first = false;
            } else {
                toRemove.clear();
                for (int i = 0; i < suspects.size(); i++) {
                    if (!drankBeforeSick.contains(suspects.get(i))) {
                        toRemove.add(i);
                    }
                }
                // Remove from end to avoid index shifting issues
                for (int i = toRemove.size() - 1; i >= 0; i--) {
                    suspects.remove((int) toRemove.get(i));
                }
            }
        }

        int maxDoses = 0;
        for (int milk : suspects) {
            int count = 0;
            for (int person : peopleMilks.keySet()) {
                if (peopleMilks.get(person).contains(milk)) {
                    count++;
                }
            }
            maxDoses = Math.max(maxDoses, count);
        }

        PrintWriter out = new PrintWriter(new File("badmilk.out"));
        out.println(maxDoses);
        out.close();
    }
}
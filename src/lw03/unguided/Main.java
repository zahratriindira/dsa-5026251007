package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> registrations = new HashSet<>();
        Set<String> checkins = new HashSet<>();
        int rejectedAttempts = 0;

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (sc1.hasNextLine()) {
            String ID = sc1.nextLine().trim();

            if (!ID.isEmpty()) {
                registrations.add(ID);
            }
        }
        sc1.close();

        System.out.println("==== Event Check-in Results ====");
        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (sc2.hasNextLine()) {
            String ID = sc2.nextLine().trim();
            if (ID.isEmpty()) continue;

            if (!registrations.contains(ID)) {
                System.out.println(ID + ": Rejected (Not Registered)");
                rejectedAttempts++;
            } else if (checkins.contains(ID)) {
                System.out.println(ID + ": Rejected (Already checked in)");
                rejectedAttempts++;
            } else {
                System.out.println(ID + ": Checked in");
                checkins.add(ID);
            }
        }
        sc2.close();

        System.out.println("\n==== Final Event Summary ====");
        System.out.println("Registered students: " + registrations.size());
        System.out.println("Successful check-ins: " + checkins.size());
        System.out.println("Absent students: " + (registrations.size() - checkins.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}
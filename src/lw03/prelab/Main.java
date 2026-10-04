package lw03.prelab;

import java.util.*;

public class Main {
    public static void main (String[] args) {
        solveProblem1();
        System.out.println();

        solveProblem2();
        System.out.println();

        solveProblem3();
    }

    // Problem 1: Playlist Management
    private static void solveProblem1() {
        System.out.println("==== Problem 1 ====");
        List<String> playlist = new ArrayList<>();
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("ADD ")) {
                String song = line.substring(4);
                playlist.add(song);
            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.substring(7).split(" ", 2);
                int index = Integer.parseInt(parts[0]);
                String song = parts[1];
                playlist.add(index, song);
            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring(7);
                playlist.remove(song);
                
            }
        }
        scanner.close();

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ". " + playlist.get(i));
        }
    }

    // Problem 2: Workshop Participants
    private static void solveProblem2() {
        System.out.println("==== Problem 2 ====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) continue;
            
            if (!participants.add(name)) {
                duplicateCount++;
            }
        }
        scanner.close();

        System.out.println("Total participants: " + participants.size());
        int index = 1;
        for (String name : participants) {
            System.out.println((index++) + ". " + name);
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    // Problem 3: Product Inventory
    private static void solveProblem3() {
        System.out.println("==== Problem 3 ====");
        Map<String, Integer> inventory = new HashMap<>();
        int failedSales = 0;
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (scanner.hasNextLine()) {
            String Line = scanner.nextLine().trim();
            if (Line.isEmpty()) continue;

            String[] parts = Line.split("\\s+");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        scanner.close();

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}

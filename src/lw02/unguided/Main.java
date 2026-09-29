package lw02.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> food = new LinkedList<>();
        LinkedList<String[]> drink = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        food.add(new String[]{"Bakso", "2"});
        food.add(new String[]{"Sate", "1"});
        food.add(new String[]{"Soto", "2"});

        drink.add(new String[]{"EsTeh", "4"});
        drink.add(new String[]{"EsJeruk", "2"});

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("order.txt"));

        while(scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next(); // nama
            order[1] = scanner.next(); // food
            order[2] = scanner.next(); // drink
            order[3] = scanner.next(); // number table
            orders.add(order);
        }
        scanner.close();

        queue.addAll(orders);

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String foods = order[1];
            String drinks = order[2];

            String[] targetFood = null;
            String[] targetDrink = null;

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            if(!foods.equals("-")) {
                for(String[] f : food) {
                    if(f[0].equals(foods)) {
                        targetFood = f;
                        break;
                    }
                }
                if(targetFood == null || Integer.parseInt(targetFood[1]) <= 0) {
                    foodAvailable = false;
                }
            }

            if(!drinks.equals("-")) {
                for(String[] d : drink) {
                    if(d[0].equals(drinks)) {
                        targetDrink = d;
                        break;
                    }
                }
                if(targetDrink == null || Integer.parseInt(targetDrink[1]) <= 0) {
                    drinkAvailable = false;
                }
            }
            if(foodAvailable && drinkAvailable) {
                if(targetFood != null) {
                    int currentStock = Integer.parseInt(targetFood[1]);
                    targetFood[1] = String.valueOf(currentStock - 1);
                }
                if(targetDrink != null) {
                    int currentStock = Integer.parseInt(targetDrink[1]);
                    targetDrink[1] = String.valueOf(currentStock - 1);
                }
                successOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successful Orders ===");
        for(String[] o : successOrders) {
            System.out.println(o[0] + " " + o[1] + " "+ o[2] + " " + o[3]);
        }
        System.out.println("\n=== Remaining Food Stock ===");
        for(String[] f : food) {
            System.out.println(f[0] + ": " + f[1]);
        }
        System.out.println("\n=== Remaining Drink Stock ===");
        for(String[] d : drink) {
            System.out.println(d[0] + ": " + d[1]);
        }
        System.out.println("\n=== Failed Orders ===");
        while(!failed.isEmpty()) {
            String[] failedOrder = failed.pop();
            System.out.println(failedOrder[0] + " " + failedOrder[1] + " "+ failedOrder[2] + " " + failedOrder[3]);
        }
    }
}
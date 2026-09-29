package lw02.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        // Membaca input file
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transaction.txt"));

            while(scanner.hasNext()) {
                String[] transaction = new String[3];
                transaction[0] = scanner.next(); // Nama nasabah
                transaction[1] = scanner.next(); // Jenis transaksi (DEPOSIT/WITHDRAW
                transaction[2] = scanner.next(); // Jumlah transaksi
                transactions.add(transaction);
            }
            scanner.close();

            queue.addAll(transactions);


            while (!queue.isEmpty()) {
                String[] transaction = queue.poll();
                String name = transaction[0];
                String type = transaction[1];
                int amount = Integer.parseInt(transaction[2]);

                String[] customer = null;

                for(String[] data : customers) {
                    if(data[0].equals(name)) {
                        customer = data;
                        break;
                    }
                }

                if(customer == null) {
                    customer = new String[]{name, "0"};
                    customers.add(customer);
                }

                int balance = Integer.parseInt(customer[1]);

                if(type.equals("DEPOSIT")) {
                    balance += amount;
                    customer[1] = String.valueOf(balance);
                } else if(type.equals("WITHDRAW")) {
                    if(amount <= balance) {
                        balance -= amount;
                        customer[1] = String.valueOf(balance);
                    } else {
                        failed.push(transaction);
                    }
                }
            }
            System.out.println("=== Final Balances ===");
            for(String[] customer : customers) {
                System.out.println(customer[0] + " : " + customer[1]);
            }

            System.out.println("=== Failed Transactions ===");
            while(!failed.isEmpty()) {
                String[] failedTransaction = failed.pop();
                System.out.println(failedTransaction[0] + " " + failedTransaction[1] + " " + failedTransaction[2]);
            }
    }
}
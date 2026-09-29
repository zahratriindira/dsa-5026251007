package lw02.prelab;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // LinkedList untuk menyimpan data transaksi
        LinkedList<String[]> transactions = new LinkedList<>();
        // LinkedList untuk menyimpan data nasabah
        LinkedList<String[]> customers = new LinkedList<>();

        // Membaca input file
        try (InputStream is = Main.class.getResourceAsStream("transaction.txt")) {
            if (is == null) {
                System.out.println("File transaction.txt tidak ditemukan.");
                return;
            }

            try (Scanner scanner = new Scanner(is)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split("\\s+");
                    String name = parts[0];
                    String type = parts[1];
                    String amount = parts[2];

                    // Simpah transaksi ke LinkedList
                    transactions.add(new String[]{name, type, amount});

                    // Daftarkan nasabah jika belum ada
                    boolean exists = false;
                    for (String[] customer : customers) {
                        if (customer[0].equals(name)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        customers.add(new String[]{name, "0"}); // Inisialisasi saldo nasabah dengan 0
                    }
                }
            }
        }   catch (Exception e) {
                System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
                return;
            }

        // Pindahkan transaksi dari LinkedList ke Queue untuk diproses
        Queue<String[]> transactionQueue = new LinkedList<>();
        for (String[] trans : transactions) {
            transactionQueue.add(trans);
        }
        
        // Stack untuk menyimpan transaksi Withdraw yang gagal
        Stack<String[]> failedStack = new Stack<>();

        // Proses transaksi dari queue
        while (!transactionQueue.isEmpty()) {
            String[] trans = transactionQueue.poll();
            String name = trans[0];
            String type = trans[1];
            int amount = Integer.parseInt(trans[2]);

            // Cari nasabah di customers
            String[] targetCustomer = null;
            for (String[] cust : customers) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equalsIgnoreCase("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equalsIgnoreCase("WITHDRAW")) {
                    if (amount > currentBalance) {
                        // Jika saldo kurang, transaksi gagal dan masuk Stack
                        failedStack.push(trans);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }
        // Cetak hasil sesuai format output
        System.out.println("=== Final Balances ===");
            
        for (String[] cust : customers) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        System.out.println("\n=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}
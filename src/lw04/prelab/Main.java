package lw04.prelab;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = Main.class.getResourceAsStream("branches.txt");
        
        // Jika tidak ketemu di package, coba cari dari root classpath
        if (inputStream == null) {
            inputStream = Main.class.getResourceAsStream("/branches.txt");
        }

        // Jika masih tidak ketemu juga, beri peringatan agar tidak error null
        if (inputStream == null) {
            System.out.println("Error: File branches.txt tidak ditemukan di dalam folder package!");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        // Baris 1: Jumlah cabang (N)
        int n = scanner.nextInt();
        int[] branches = new int[n];

        // Baris 2: Nilai stabilitas cabang
        for (int i = 0; i < n; i++) {
            branches[i] = scanner.nextInt();
        }

        // Baris 3: Jumlah target (M)
        int m = scanner.nextInt();
        int[] targets = new int[m];

        // Baris 4: Nilai target stabilitas
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        // Insertion Sort (Descending) tepat 3 passes (i = 1 sampai 3)
        int passes = 3;
        for (int i = 1; i <= passes; i++) {
            int key = branches[i];
            int j = i - 1;

            // Geser elemen yang lebih kecil dari key ke kanan
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }

        // Menampilkan isi array setelah 3 passes
        for (int i = 0; i < n; i++) {
            System.out.print(branches[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        // Linear Search untuk setiap target
        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int foundIndex = -1;

            for (int j = 0; j < n; j++) {
                if (branches[j] == target) {
                    foundIndex = j;
                    break;
                }
            }

            // Menampilkan hasil pencarian sesuai format
            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}
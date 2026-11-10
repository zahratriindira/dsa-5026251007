package lw04.unguided;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class Main {
    static void bubbleSort(int[] arr, int startPass, int endPass) {
        int n = arr.length;
        for(int i = startPass; i < endPass; i++) {
            for(int j = 0; j < n - 1 - i; j++) {
                if(arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;
        int foundIndex = -1;

        while (low <= high) {
            steps++;
            int mid = (low + high) / 2;
        
            if (arr[mid] == target) {
                foundIndex = mid;
                break;
            } else if (arr[mid] < target) {
                high  = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (foundIndex != -1) {
            System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = null;

        InputStream is = Main.class.getResourceAsStream("parcels.txt");

        if (is != null) {
            sc = new Scanner(is);
        } else {
            try {
                sc = new Scanner(new File("src/lw04/unguided/parcels.txt"));
            } catch (Exception e) {
                try {
                    sc = new Scanner(new File("parcels.txt"));
                } catch (Exception ex) {
                    System.out.println("Error: File parcels.txt not found.");
                    return;
                }
            }
        }

        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        bubbleSort(parcels, 0, n);

        int[] after2Passes = new int[n];
        for (int i = 0; i < n; i++) {
            after2Passes[i] = parcels[i];
        }

        bubbleSort(parcels, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(after2Passes[i] + (i == n - 1 ? "\n" : " "));
        }

        System.out.println("\n==== Fully Sorted ====");
        for (int i = 0; i < n; i++) {
            System.out.print(parcels[i] + (i == n - 1 ? "\n" : " "));
        }

        System.out.println("\n==== Search Results ====");
        for (int target : targets) {
            binarySearch(parcels, target);
        }
    }
}
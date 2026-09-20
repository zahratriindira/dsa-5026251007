package lw01.prelab;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        List<PrintJob> jobs;
        try (Scanner scanner = new Scanner(new File("src/lw01/prelab/jobs.txt"))) {
            jobs = new ArrayList<>();
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();
                
                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
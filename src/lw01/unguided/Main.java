package lw01.unguided;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
    List<WashService> washes = new ArrayList<>();

        Scanner scan = new Scanner(Main.class.getResourceAsStream("washes.txt")); {
        WashService[] washesArray = new WashService[4];
            for (int i = 0; i < washesArray.length; i++) {
                String type = scan.next();
                String id = scan.next();
                int days = scan.nextInt();
                int units = scan.nextInt();
                
                WashService wash;

                if (type.equals("CAR")) {
                    wash = new CarWash(id, days);
                } else {
                    wash = new MotorcycleWash(id, days);
                }
                washes[i] = wash;
            }
        }
        scan.close();

        for (WashService wash : washes) {
            System.out.println(wash.summary());
        }
    }
}

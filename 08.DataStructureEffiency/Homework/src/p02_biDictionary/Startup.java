package p02_biDictionary;

import java.util.ArrayList;

public class Startup {
    public static void main(String[] args) {
        BiDictionary<String, String, Integer> distances = new BiDictionary<String, String, Integer>();

        distances.add("Sofia", "Varna", 443);
        distances.add("Sofia", "Varna", 468);
        distances.add("Sofia", "Varna", 490);
        distances.add("Sofia", "Plovdiv", 145);
        distances.add("Sofia", "Bourgas", 383);
        distances.add("Plovdiv", "Bourgas", 253);
        distances.add("Plovdiv", "Bourgas", 292);

        // if there is no such key returns null
        ArrayList<Integer> distancesFromSofia = distances.findByFirstKey("Sofia");
        System.out.println(distancesFromSofia); // [443, 468, 490, 145, 383]

        ArrayList<Integer> distancesToBourgas = distances.findBySecondKey("Bourgas");
        System.out.println(distancesToBourgas); // [383, 253, 292]

        ArrayList<Integer> distancesPlovdivBourgas = distances.findByBothKeys("Plovdiv", "Bourgas");
        System.out.println(distancesPlovdivBourgas);    // [253, 292]

        ArrayList<Integer> disntancesRousseVarna = distances.findByBothKeys("Rousse", "Varna");
        System.out.println(disntancesRousseVarna);  // null

        ArrayList<Integer> distancesSofiaVarna = distances.findByBothKeys("Sofia", "Varna");
        System.out.println(distancesSofiaVarna);

        System.out.println("");
    }
}

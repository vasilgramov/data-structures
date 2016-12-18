package p01_hashTableWithChaining;

import java.util.*;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        Map<String, Integer> noteByName = new HashMap<>();
//        noteByName.put("Gosho", 3);
//        noteByName.put("Pesho", 2);
//        noteByName.put("Loshko", 5);
//
////        System.out.println(noteByName.containsKey("Gosho"));
//        System.out.println(noteByName.containsValue("ASd"));
//
//        noteByName.clear();
//        for ( Map.Entry<String, Integer> entry : noteByName.entrySet() ) {
//
//            System.out.println(entry);
//        }

//        Collection<String> ad = noteByName.values();

        HashTable<String, Integer> noteByName = new HashTable<>(1);

        noteByName.put("Pesho", 2);
        noteByName.put("Gosho", 3);
        noteByName.put("Marti", 4);
//        noteByName.put("Gosho", 8);

//        int note = noteByName.get("Gosho");

//        for (KeyValue<String, Integer> keyValue : noteByName) {
//            System.out.println(keyValue.getKey() + " " + keyValue.getValue());
//        }

//        noteByName.put("Gosho", note + 10);
//        System.out.println(note);

        Object[] allValues = noteByName.values(); // returns array of objects
        for (Object value : allValues) {
            System.out.println(value);
        }

        //System.out.println(noteByName.containsValue(2));

        //noteByName.clear();
        //System.out.println(noteByName.getCount());
//        for (KeyValue<String, Integer> keyValue : noteByName) {
//            System.out.println(keyValue.getKey() + " " + keyValue.getValue());
//        }

    }
}

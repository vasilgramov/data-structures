import p01_dictionaryImplementation.HashTable;

import java.util.Scanner;

public class p03_phonebook {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        HashTable<String, String> numbersByName = new HashTable<>();

        String[] lineData = in.nextLine().split("\\s+|-");
        while (!lineData[0].equals("search")) {
            String name = lineData[0];
            if (name.equals("search")) {
                break;
            }

            String telephone = lineData[1];

            numbersByName.put(name, telephone);

            lineData = in.nextLine().split("\\s+|-");
        }

        String searchedName = in.nextLine();
        while (!searchedName.equals("end")) {
            if (numbersByName.containsKey(searchedName)) {
                System.out.println(searchedName + " -> " + numbersByName.get(searchedName));
            } else {
                System.out.println("Contact " + searchedName + " does not exist!");
            }

            searchedName = in.nextLine();
        }
    }
}
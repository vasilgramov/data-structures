package p06_reversedList;

import java.util.ArrayList;
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ReversedArrayList<String> reversedArrayList = new ReversedArrayList<>();
        reversedArrayList.add("1");
        reversedArrayList.add("2");
        reversedArrayList.add("3");
        reversedArrayList.add("4");
        reversedArrayList.add("5");

        for (String element : reversedArrayList) {
            System.out.println(element);
        }
    }
}

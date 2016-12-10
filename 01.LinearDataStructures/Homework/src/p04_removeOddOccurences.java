import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class p04_removeOddOccurences {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] numbersAsStringARR = in.nextLine().split("\\s+");
        ArrayList<String> numbersAsStringInAL = new ArrayList<>(Arrays.asList(numbersAsStringARR));

        for (int i = 0; i < numbersAsStringARR.length; i++) {
            int numberOfOccurences = 0;
            for (int j = 0; j < numbersAsStringARR.length; j++) {
                if (numbersAsStringARR[i].equals(numbersAsStringARR[j])) {
                    numberOfOccurences++;
                }
            }

            if (numberOfOccurences % 2 != 0) {
                numbersAsStringInAL.removeAll(Collections.singleton(numbersAsStringARR[i]));
            }
        }

        System.out.println(String.join(" ", numbersAsStringInAL));
    }
}

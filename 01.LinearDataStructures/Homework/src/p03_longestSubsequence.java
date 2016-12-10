import java.util.ArrayList;
import java.util.Scanner;

public class p03_longestSubsequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] numbersAsString = in.nextLine().split("\\s+");

        ArrayList<String> longestSubsequence = new ArrayList<>();

        for (int i = 0; i < numbersAsString.length; i++) {
            ArrayList<String> currentSequence = new ArrayList<>();
            currentSequence.add(numbersAsString[i]);
            for (int j = i + 1; j < numbersAsString.length; j++) {
                if (numbersAsString[i].equals(numbersAsString[j])) {
                    currentSequence.add(numbersAsString[j]);
                }
            }

            if (currentSequence.size() > longestSubsequence.size()) {
                longestSubsequence = currentSequence;
            }
        }

        System.out.println(String.join(" ", longestSubsequence));
    }
}

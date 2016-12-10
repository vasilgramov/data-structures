import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class p05_countOfOccurences {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] numbersAsString = in.nextLine().split("\\s+");

        Map<String, Integer> coutByOccurences = new HashMap<>();

        for (int i = 0; i < numbersAsString.length; i++) {
            if (!coutByOccurences.containsKey(numbersAsString[i])) {
                coutByOccurences.put(numbersAsString[i], 1);
            } else {
                coutByOccurences.put(numbersAsString[i], coutByOccurences.get(numbersAsString[i]) + 1);
            }
        }

        for(Map.Entry<String, Integer> entry : coutByOccurences.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.printf("%s -> %d times\n", key, value);
        }
    }
}

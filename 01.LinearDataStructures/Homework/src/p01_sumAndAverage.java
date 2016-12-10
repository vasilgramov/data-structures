import java.util.Scanner;

public class p01_sumAndAverage {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] numbersAsString = in.nextLine().split("\\s+");

        long sumOfNumbers = 0;

        for (int i = 0; i < numbersAsString.length; i++) {
            sumOfNumbers += Integer.parseInt(numbersAsString[i]);
        }

        System.out.printf("%s %s", sumOfNumbers, sumOfNumbers / (double)numbersAsString.length);
    }
}

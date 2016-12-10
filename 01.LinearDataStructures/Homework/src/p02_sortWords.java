import java.util.Arrays;
import java.util.Scanner;

public class p02_sortWords {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] words = in.nextLine().split("\\s+");
        Arrays.sort(words);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(words[i] + " ");
        }

        System.out.println(builder.toString().trim());
    }
}

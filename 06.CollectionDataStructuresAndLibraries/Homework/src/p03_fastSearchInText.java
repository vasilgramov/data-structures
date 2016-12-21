import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class p03_fastSearchInText {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<String, Integer> appearanceByWord = new HashMap<>();
        int wordCount = Integer.parseInt(in.nextLine());
        for (int i = 0; i < wordCount; i++) {
            String currentWord = in.nextLine();
            appearanceByWord.put(currentWord, 0);
        }

        int sentenceCount = Integer.parseInt(in.nextLine());
        for (int i = 0; i < sentenceCount; i++) {
            String currentSentece = in.nextLine().toLowerCase();
            for (Map.Entry<String, Integer> entry : appearanceByWord.entrySet()) {
                String key = entry.getKey();
                String copySentence = currentSentece;
                int index = copySentence.indexOf(key.toLowerCase());
                int count = 0;
                while (index != -1) {
                    count++;
                    copySentence = copySentence.substring(index + 1);
                    index = copySentence.indexOf(key.toLowerCase());
                }

                appearanceByWord.put(key, appearanceByWord.get(key) + count);
            }
        }

        for (Map.Entry<String, Integer> entry : appearanceByWord.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.printf("%s -> %d\r\n", key, value);
        }
    }
}

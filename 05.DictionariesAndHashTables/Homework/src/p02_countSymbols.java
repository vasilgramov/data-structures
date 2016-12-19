import p01_dictionaryImplementation.HashTable;
import p01_dictionaryImplementation.KeyValue;

import java.util.*;

public class p02_countSymbols {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[] text = in.nextLine().toCharArray();

        HashTable<Character, Integer> appearanceBySymbol = new HashTable<>();
        for (char symbol : text) {
            if (appearanceBySymbol.containsKey(symbol)) {
                appearanceBySymbol.put(symbol, appearanceBySymbol.get(symbol) + 1);
            } else {
                appearanceBySymbol.put(symbol, 1);
            }
        }

        List<KeyValue> orderedList = new ArrayList<>(appearanceBySymbol.getAllElements());
        Collections.sort(orderedList);

        for (KeyValue<Character, Iterable> element : orderedList) {
            System.out.println(element);
        }
    }
}

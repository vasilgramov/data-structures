import java.util.*;

public class p01_productInPriceRange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Integer numberOfElements = Integer.parseInt(in.nextLine());

        Map<String, Double> priceByProduct = new HashMap<>();

        for (int i = 0; i < numberOfElements; i++) {
            String[] currentLine = in.nextLine().split("\\s+");
            String product = currentLine[0];
            double price = Double.parseDouble(currentLine[1]);

            priceByProduct.put(product, price);
        }

        String[] bottomAndUpperLimit = in.nextLine().split("\\s+");
        double bottomLimit = Double.parseDouble(bottomAndUpperLimit[0]);
        double upperLimit = Double.parseDouble(bottomAndUpperLimit[1]);

        Map<String, Double> sortedMapAsc = sortByComparator(priceByProduct, true);

        int counter = 0;
        for (Map.Entry<String, Double> entry : sortedMapAsc.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            if (value >= bottomLimit && value <= upperLimit && counter < 20) {
                System.out.printf("%f %s\r\n", value, key);

                counter++;
            }
        }

        System.out.println("");
    }

    private static Map<String, Double> sortByComparator(Map<String, Double> unsortedMap, final boolean order)
    {
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortedMap.entrySet());

        Collections.sort(list, (o1, o2) -> {
            if (order)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
            else
            {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}

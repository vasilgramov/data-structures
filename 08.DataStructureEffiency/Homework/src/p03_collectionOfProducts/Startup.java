package p03_collectionOfProducts;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Startup {
    public static void main(String[] args) {
        CollectionOfProducts collectionOfProducts = new CollectionOfProducts();

        collectionOfProducts.addProduct(new Product("0005", "Apple", "AppleAndApples", 5));
        collectionOfProducts.addProduct(new Product("0001", "Coke", "Nestle", 5));
        collectionOfProducts.addProduct(new Product("0003", "Coke", "ColaCola", 3.3));
        collectionOfProducts.addProduct(new Product("0002", "Vodka", "Sushi", 4.6));
        collectionOfProducts.addProduct(new Product("0004", "Juice", "Orange", 5));
        collectionOfProducts.addProduct(new Product("0001", "AnotherCorn", "Nestle", 5));

        collectionOfProducts.removeProduct("0001");

//        SortedMap<Double, TreeMap<String, Product>> productsInRangeSortedById = collectionOfProducts.getInPriceRange(3, 5);
//        for (Map.Entry<Double, TreeMap<String, Product>> entry : productsInRangeSortedById.entrySet()) {
//            Double key = entry.getKey();
//            TreeMap<String, Product> value = entry.getValue();
//
//            for (Map.Entry<String, Product> productEntry : value.entrySet()) {
//                String id = productEntry.getKey();
//                Product product = productEntry.getValue();
//
//                System.out.println(product);
//            }
//        }

        //--------------------------------------------------------------------------------------------

//        collectionOfProducts.print();

//        TreeMap<String, Product> byTitle = collectionOfProducts.findByTitle("Coke");

        System.out.println();
    }
}

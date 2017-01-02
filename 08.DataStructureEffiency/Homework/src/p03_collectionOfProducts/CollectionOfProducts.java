package p03_collectionOfProducts;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CollectionOfProducts {
    private TreeMap<String, Product> productById;
    private TreeMap<Double, TreeMap<String, Product>> sortedByIdByPrice;
    private HashMap<String, TreeMap<String, Product>> byTitle;

    public CollectionOfProducts() {
        this.productById = new TreeMap<String, Product>();
        this.sortedByIdByPrice = new TreeMap<Double, TreeMap<String, Product>>();
        this.byTitle = new HashMap<String, TreeMap<String, Product>>();
    }

    public void addProduct(Product product) {
        toById(product);

        toBySortedByPrice(product);

        toByTitle(product);

    }

    private void toByTitle(Product product) {
        if (!this.byTitle.containsKey(product.getTitle())) {
            this.byTitle.put(product.getTitle(), new TreeMap<String, Product>());
        }

        TreeMap<String, Product> currentTreeMap = this.byTitle.get(product.getTitle());
        currentTreeMap.put(product.getId(), product);
        this.byTitle.put(product.getTitle(), currentTreeMap);
    }

    private void toBySortedByPrice(Product product) {
        if (!this.sortedByIdByPrice.containsKey(product.getPrice())) {
            this.sortedByIdByPrice.put(product.getPrice(), new TreeMap<String, Product>());
        }

        TreeMap<String, Product> currentTreeMap = this.sortedByIdByPrice.get(product.getPrice());
        currentTreeMap.put(product.getId(), product);
        this.sortedByIdByPrice.put(product.getPrice(), currentTreeMap);
    }

    private void toById(Product product) {
        this.productById.put(product.getId(), product);
    }

    public boolean removeProduct(String id) {
        if (!this.productById.containsKey(id)) {
            return false;
        }

        Product toBeRemove = this.productById.get(id);

        removeById(toBeRemove);

        TreeMap<String, Product> currentTreeMap = this.sortedByIdByPrice.get(toBeRemove.getPrice());
        currentTreeMap.remove(toBeRemove.getId());
        if (currentTreeMap.size() == 0) {
            this.sortedByIdByPrice.remove(toBeRemove.getPrice());
        } else {
            this.sortedByIdByPrice.put(toBeRemove.getPrice(), currentTreeMap);
        }

        return true;
    }

    private void removeById(Product product) {
        this.productById.remove(product.getId());
    }

    public SortedMap<Double, TreeMap<String, Product>> getInPriceRange(double startPrice, double endPrice) {
        return this.sortedByIdByPrice.subMap(startPrice, endPrice);
    }

    public TreeMap<String, Product> findByTitle(String title) {
        return this.byTitle.get(title);
    }

    public void print() {
        for (Map.Entry<String, Product> entry : this.productById.entrySet()) {
            String key = entry.getKey();
            Product value = entry.getValue();

            System.out.println(value);
        }
    }
}

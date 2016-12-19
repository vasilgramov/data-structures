package p04_orderedSet;

public class Startup {
    public static void main(String[] args) {
        OrderedTree<Integer> orderedTree = new OrderedTree<>();

        // adding elements first is the root of the tree
        orderedTree.add(17);
        orderedTree.add(9);
        orderedTree.add(19);
        orderedTree.add(25);
        orderedTree.add(6);

        System.out.println("foreach-able------------------------");
        for (Integer element : orderedTree) {
            System.out.println(element);
        }

        System.out.println("----------------------------------------");

        System.out.println("count  -> " + orderedTree.getCount());

        System.out.println("contains----------------- 12 -> " + orderedTree.contains(12));
        System.out.println("contains----------------- 19 -> " + orderedTree.contains(19));
        System.out.println("contains----------------- 16 -> " + orderedTree.contains(16));
    }
}

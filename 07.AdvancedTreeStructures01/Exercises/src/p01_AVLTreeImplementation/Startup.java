package p01_AVLTreeImplementation;

public class Startup {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();

        avlTree.add(20);
        avlTree.add(12);
        avlTree.add(25);
        avlTree.add(26);
        avlTree.add(17);

        for (Integer node : avlTree) {
            System.out.println(node);
        }


    }
}

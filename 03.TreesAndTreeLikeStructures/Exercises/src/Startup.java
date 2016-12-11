public class Startup {
    public static void main(String[] args) {
        Tree<Integer> parent = new Tree(7, new Tree[] {
                new Tree<>(19, new Tree[] {
                        new Tree<>(1),
                        new Tree<>(12)
                }),
                new Tree<>(21),
                new Tree<>(42, new Tree[] {
                        new Tree<>(23),
                        new Tree<>(6)
                })
        });

        Tree<Integer> parent2 = new Tree<>(1, new Tree<>(4), new Tree<>(12));

        //parent.print(0);
        //parent2.print(0);

        // 01001100 01001111 01010110 01000101 00100000 01010000 01010010 01001111 01000111 01010010 01000001 01001101 01001101 01001001 01001110 01000111 00001101 00001010

        BinaryTree<Integer> binaryTree =
                new BinaryTree<Integer>(1,
                        new BinaryTree<Integer>(2, new BinaryTree<Integer>(4), new BinaryTree<Integer>(11)),
                        new BinaryTree<Integer>(13));

        binaryTree.print(0);

    }
}

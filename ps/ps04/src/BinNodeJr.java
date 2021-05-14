// C343 / Fall 2020
//
// a very simple, starting binary node class;
// it's so simple, it's named BinNodeJr.

public class BinNodeJr <E extends Comparable<?super E>>{
    private E value;
    private BinNodeJr<E> left;
    private BinNodeJr<E> right;

    public BinNodeJr(E e) {
        value = e;
        left = right = null;
    }

    public void setLeft(BinNodeJr<E> node) {
        left = node;
    }

    public void setRight(BinNodeJr<E> node) {
        right = node;
    }

    // TODO for C343/Fall 2020 - Problem Set 04 Task B
    //The root is going to be this, with a value of q. You need to do this recursively because it is
    public boolean find(E q) {
        return findHelper(this, q);
    }

    //A helper function that actually does the work to for the find function, called find Helper.
    // You need to use recursion for the regular find method.
    public boolean findHelper(BinNodeJr<E> root, E q) {
        while(root != null) {
            if (q.compareTo(root.value) < 0) {
                root = root.left;
            }
            else if (q.compareTo(root.value) > 0) {
                root = root.right;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] argv) {
        //Creating random Integer Node Values
        BinNodeJr<Integer> root = new BinNodeJr<Integer>(8);
        BinNodeJr<Integer> node1 = new BinNodeJr<Integer>(28);
        BinNodeJr<Integer> node2 = new BinNodeJr<Integer>(38);
        BinNodeJr<Integer> node3 = new BinNodeJr<Integer>(7);
        BinNodeJr<Integer> node4 = new BinNodeJr<Integer>(10);
        BinNodeJr<Integer> node5 = new BinNodeJr<Integer>(1);
        BinNodeJr<Integer> node6 = new BinNodeJr<Integer>(27);
        BinNodeJr<Integer> node7 = new BinNodeJr<Integer>(40);
        BinNodeJr<Integer> node8 = new BinNodeJr<Integer>(69);
        BinNodeJr<Integer> node9 = new BinNodeJr<Integer>(15);
        BinNodeJr<Integer> node10 = new BinNodeJr<Integer>(12);

        //Constructing the Binary Tree
        /**
         * My Binary Tree:
         *          8
         *      28      38
         *    7       27  40
         *  1  10   12   15 69
         *
         */

        //Level 2
        root.setLeft(node1);
        root.setRight(node2);

        //Level 3
        node1.setLeft(node3);
        node2.setLeft(node6);
        node2.setRight(node7);

        //Level 4
        node3.setLeft(node5);
        node3.setRight(node4);
        node6.setLeft(node10);
        node7.setLeft(node9);
        node7.setRight(node8);

        // find() needs to be implemented
        System.out.println("Initial Tests");
        System.out.println("is 38 found in the tree: " + root.find(38));
        // find(38) should return true
        System.out.println("is 100 found in the tree: " + root.find(100));
        // find(100) should return false
        System.out.println();

        //The printing tests depends on which nodes are connected to what,
        // as build in the earlier part of code.

        //Level 3 of Tree
        System.out.println("Level 3 of Tree");
        System.out.println("is 7 found in the tree: " + node1.find(7));
        System.out.println("is 27 found in the tree: " + node2.find(27));
        System.out.println("is 40 found in the tree: " + node2.find(40));
        System.out.println();

        //Level 4 of Tree
        System.out.println("Level 4 of Tree");
        System.out.println("is 1 found in the tree: " + node3.find(1));
        System.out.println("is 10 found in the tree: " + node3.find(10));
        System.out.println("is 12 found in the tree: " + node6.find(12));
        System.out.println("is 15 found in the tree: " + node7.find(15));
        System.out.println("is 69 found in the tree: " + node7.find(69));

        //False Tests
        System.out.println();
        System.out.println("False Tests");
        System.out.println("is 20 found in the tree: " + node3.find(20));
        System.out.println("is 100 found in the tree: " + root.find(100));
    }
}
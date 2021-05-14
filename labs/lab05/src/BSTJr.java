// C343 / Fall 2020
//
// a very simple, starting BST class;
// it's so simple, it's named BSTJr.

public class BSTJr <K extends Comparable<?super K>> {
    BinNode<K> root;
    BinNode<K> curr;

    // TODO for C343/Fall 2020 - Lab 05 part A
    // "unbalanced" is used for balance checking:
    //  if a node is unbalanced, the tree will be unbalanced
    BinNode<K> unbalanced = null;

    public BSTJr() {
        root = null;
        curr = null;
    }
    public void build(K[] ks) {
        for (int i = 0; i < ks.length; i++)
            insert(ks[i]);
    }
    public void insert(K k) {
        BinNode<K> t = new BinNode<K>(k);
        if (root == null) {
            root = curr = t;
        } else {
            curr = search(root, k);
            if (k.compareTo(curr.getKey()) < 0)
                curr.setLeft(t);
            else
                curr.setRight(t);
        }
    }
    public BinNode<K> search(BinNode<K> entry, K k) {
        if (entry == null)
            return null;
        else {
            entry.setSize(entry.getSize() + 1); //update the size of the subtree by one
            if (entry.isLeaf())
                return entry;
            if (k.compareTo(entry.getKey()) < 0) {
                /*  bug in original version: if (k.compareTo(curr.getKey()) < 0) {  */
                if (entry.getLeft() != null)
                    return search(entry.getLeft(), k);
                else
                    return entry;
            } else {
                if (entry.getRight() != null)
                    return search(entry.getRight(), k);
                else
                    return entry;
            }
        }
    }

    public void display() {
        if (root == null) return;
        System.out.println("Preorder enumeration: key(size-of-the-subtree)");
        preorder(root);
        System.out.println();
        System.out.println("Postorder enumeration: key(size-of-the-subtree)");
        postorder(root);
        System.out.println();
        System.out.println("Inorder enumeration: key(size-of-the-subtree)");
        inorder(root);
        System.out.println();
    }
    public void preorder(BinNode<K> entry) {
        if(entry == null) return;
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
        if (entry.getLeft() != null) preorder(entry.getLeft());
        if (entry.getRight() != null) preorder(entry.getRight());
    }
    //Adding the postOrder and inOrder methods
    public void postorder(BinNode<K> entry) {
        if(entry == null) return;
        if (entry.getLeft() != null) postorder(entry.getLeft());
        if (entry.getRight() != null) postorder(entry.getRight());
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
    }

    public void inorder(BinNode<K> entry) {
        if(entry == null) return;
        if (entry.getLeft() != null) inorder(entry.getLeft());
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
        if (entry.getRight() != null) inorder(entry.getRight());
    }

    // TODO for C343/Fall 2020 - Lab 05 part A
    // implement checkBalance(), and you may write treeHeight(node) as helper function
    //Boolean method that returns true if tree is balanced false otherwise.
    public boolean checkBalance(BinNode<K> node) {
        //This condition needs to be here to avoid getting a null pointer exception
        if(node == null) {
            return true;
        }
        //Calculating heights of left and right subtree with variables leftHeight
        //And you must take absolute value of left-right subtree to not get negative values such that and making sure
        // the difference is less than or equal to 1.
        int leftHeight = treeHeight(node.getLeft());
        int rightHeight = treeHeight(node.getRight());
        if((checkBalance(node.getLeft())) && checkBalance(node.getRight()) && (Math.abs(leftHeight - rightHeight) <= 1)) {
            return true;
        }
        return false;
    }

    //Helper method for checkBalance()
    public int treeHeight(BinNode<K> node) {
        if(node == null) {
            return 0;
        }
        //The Math.max takes in two parameters and checks to see which one is larger, and we are adding 1 to account
        // for the root node.
        return 1 + Math.max(treeHeight(node.getLeft()), treeHeight((node.getRight())));
    }

    public BinNode<K> findKthSmallest(BinNode<K> node, int k) {
        int count = 0;
        if(node == null) {
            return null;
        }
        if(node.getLeft() != null) {
            count = node.getLeft().getSize();
        }
        if (count == k-1) {
            return node;
        }
        if (count > k-1) {
            return findKthSmallest(node.getLeft(), k);
        }
        //Similar to else statement
        return findKthSmallest(node.getRight(), k - count-1);
    }

    public static void main(String[] argv) {
        //One is the root
        BSTJr<Integer> tree = new BSTJr<Integer>();
        Integer[] ks = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10, 11};
        tree.build(ks);
        tree.display();
        System.out.println("Find k smallest value(s): " + tree.findKthSmallest(tree.root, 3).getKey());
        System.out.println("Is balanced: " + tree.checkBalance(tree.root));
    }
}

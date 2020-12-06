package tree.binary;


/*
public class MyBinaryTree<> {
    private TreeNode root;

    private TreeNode addRecursive(int key,E data, TreeNode current) {

        if (current == null) {
            return new TreeNode(key, data);
        }

        if (current.key < key) {
            current.right = addRecursive(key, data, current.right);
        } else if (current.key > key) {
            current.left = addRecursive(key, data, current.left);
        } else {
            return current;
        }
        return current;
    }

    public void add(int key, E data) {
        root = addRecursive(key, data, root);
    }

    private boolean containsRecursive(int key, TreeNode current) {
        if (current == null) {
            return false;
        }

        if (current.key == key) {
            return true;
        } else {
            if (current.key > key) {
                return containsRecursive(key, current.right);
            } else {
                return containsRecursive(key, current.left);
            }
        }
    }

    public boolean contains(int key) {
        return containsRecursive(key, root);
    }

    private TreeNode removeRecursive(int key, TreeNode current) {
        if (current == null) {
            return null;
        }

        if (current.key == key) {
            if (current.left == null && current.right == null) {
                current = null;
                return current;
            } else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                int smallestKey = findSmallestValue(current.right);
                current.key = smallestKey;
                current.right = removeRecursive(smallestKey, current.right);
                return current;
            }
        }

        if (current.key > key) {
            current.right = removeRecursive(key, current.right);
            return current;
        }
        current.left = removeRecursive(key, current.left);
        return current;
    }

    private int findSmallestValue(TreeNode root) {
        if (root.left == null) {
            return root.key;
        } else {
            findSmallestValue(root.left);
        }
        return 0;
    }

    public void remove(int key) {
        if(!(this.contains(key))){
            throw new NoSuchElementException("This key doesn't exist!");
        } else {
            root = removeRecursive(key, root);
        }
    }

    public E getRecursive(int key, TreeNode<E> current){
        if(current == null){
            return null;
        }
        if(current.key == key){
            return current.value;
        }

        if(current.key > key){
            return getRecursive(key, current.right);
        } else if(current.key < key){
            return getRecursive(key, current.left);
        }
            return null;
    }

    public E get(int key){
        if(!(this.contains(key))){
            throw new NoSuchElementException("This key doesn't exist!");
        }
        else{
            return (E) getRecursive(key, root);
        }
    }
}
*/
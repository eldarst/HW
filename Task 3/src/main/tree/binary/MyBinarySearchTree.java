package tree.binary;


import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyBinarySearchTree<Key, Val> {
    public class TreeNode<Key, Val> {
        Key key;
        Val value;
        TreeNode<Key, Val> left;
        TreeNode<Key, Val> right;
        int height;

        TreeNode(Key key, Val data) {
            this.value = data;
            this.key = key;
            this.right = null;
            this.left = null;
            this.height = 1;
        }

        public int getHeight() {
            return this.height;
        }

        public Val getVal() {
            return this.value;
        }

        public Key getKey() {
            return this.key;
        }

        public TreeNode<Key, Val> getLeft() {
            return this.left;
        }

        public TreeNode<Key, Val> getRight() {
            return this.right;
        }

        public int compareTo(Key val1) {
            Comparator<Key> comp = new Comparator<Key>() {
                @Override
                public int compare(Key o1, Key o2) {
                    try {
                        Comparable val1 = (Comparable) o1;
                        Comparable val2 = (Comparable) o2;
                        return val1.compareTo(val2);
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            return comp.compare(val1, this.key);
        }
    }

    public TreeNode<Key, Val> root;

    public MyBinarySearchTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }


    //all balancing starts here
    int getHeight(TreeNode<Key, Val> root) {
        //System.out.println("Now, we looking for Height of");
        /*if(root != null) {
            System.out.println("Key is: " + root.getKey() + " and Value is: " + root.getVal());
        }
        else {
            System.out.println("root is NULL");
        }*/
        return root != null ? root.height : 0;
    }

    int getBalanceFactor(TreeNode<Key, Val> root) {
        //System.out.println("\n Calculating Balance Factor...");
        if (root == null) {
            return 0;
        }
        //System.out.println("Left  is: " + getHeight(root.left) + " ID : " + root.left + " - Right is: "+ getHeight(root.right) + " ID : " + root.right);
        return getHeight(root.right) - getHeight(root.left);
    }

    void fixHeight(TreeNode<Key, Val> root) {
        if (root == null) {
            return;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        root.height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

    TreeNode<Key, Val> rotateRight(TreeNode<Key, Val> root) {
        TreeNode<Key, Val> leftTree = root.left;
        root.left = leftTree.right;
        leftTree.right = root;
        fixHeight(root);
        fixHeight(leftTree);
        return leftTree;
    }

    TreeNode<Key, Val> rotateLeft(TreeNode<Key, Val> root) {
        TreeNode<Key, Val> rightTree = root.right;
        root.right = rightTree.left;
        rightTree.left = root;
        fixHeight(root);
        fixHeight(rightTree);
        return rightTree;
    }

    TreeNode<Key, Val> balance(TreeNode<Key, Val> root) {
        fixHeight(root);
        if (getBalanceFactor(root) == 2) {
            if (getBalanceFactor(root.right) < 0) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }
        if (getBalanceFactor(root) == (-2)) {
            if (getBalanceFactor(root.left) > 0) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        }
        //System.out.println("NO Rotation! \n Balance Factor is " + getBalanceFactor(root));
        return root;
    }

    private TreeNode<Key, Val> addRecursive(Key key, Val value, TreeNode<Key, Val> root) {
        TreeNode<Key, Val> newNode = new TreeNode<>(key, value);

        if (value == null) {
            throw new NullPointerException("Null values are not permitted!");
        } else if (root == null) {
            root = newNode;
        } else if (root.compareTo(key) < 0) {
            root.left = addRecursive(key, value, root.left);
        } else if (root.compareTo(key) > 0) {
            root.right = addRecursive(key, value, root.right);
        } else if (root.compareTo(key) == 0) {
            TreeNode<Key, Val> old = root;
            root.value = value;
            return balance(old);
        }
        return balance(root);
    }


    public Val add(Key key, Val value) {
        this.root = addRecursive(key, value, this.root);
        return this.root.getVal();
    }

    public boolean contains(Key key) {
        TreeNode<Key, Val> current = this.root;
        while (current != null) {
            if (current.compareTo(key) < 0) {
                current = current.left;
            } else if (current.compareTo(key) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;

    }

    public Val get(Key key) {
        if (!this.contains(key)) {
            throw new NoSuchElementException();
        }
        TreeNode<Key, Val> current = this.root;
        while (current != null) {
            if (current.compareTo(key) < 0) {
                current = current.left;
            } else if (current.compareTo(key) > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;

    }

    private TreeNode<Key, Val> findSmallestValue(TreeNode<Key, Val> root) {
        TreeNode<Key, Val> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    private TreeNode<Key, Val> removeSmallestValue(TreeNode<Key, Val> root) {
        if (root.left == null) {
            return root.right;
        } else {
            root.left = removeSmallestValue(root.left);
            return balance(root);
        }
    }

    public TreeNode<Key, Val> findParent(TreeNode<Key, Val> elem) {
        if (elem != this.root) {

            TreeNode<Key, Val> current = this.root;
            while (current.left != elem && current.right != elem) {
                if (elem.compareTo(current.key) > 0) {
                    current = current.left;
                } else if (elem.compareTo(current.key) < 0) {
                    current = current.right;
                } else {
                    return current;
                }
            }
            return current;
        }
        return null;
    }

    private TreeNode<Key, Val> removeRecursive(Key key, TreeNode<Key, Val> root) {
        if (!this.contains(key)) {
            throw new NoSuchElementException();
        }
        TreeNode<Key, Val> current = root;

        if (current == null) {
            return null;
        }

        if (current.compareTo(key) > 0) {
            current.right = removeRecursive(key, current.right);
        } else if (current.compareTo(key) < 0) {
            current.left = removeRecursive(key, current.left);
        } else {
            //System.out.println("Height of: "+current.value + " IS " + current.height);
            TreeNode<Key, Val> leftTree = current.left;
            TreeNode<Key, Val> rightTree = current.right;
            if (rightTree == null) {
                return balance(leftTree);
            }
            TreeNode<Key, Val> min = findSmallestValue(rightTree);
            min.right = removeSmallestValue(rightTree);
            min.left = leftTree;

            return balance(min);
        }
        return balance(current);
    }

    public void remove(Key key) {
        this.root = removeRecursive(key, this.root);
        return;
    }

    public void print(TreeNode<Key, Val> root) {
        if (root == null) {
            throw new NullPointerException("Null values are not permitted");
        }
        if (root.left != null) this.print(root.left);
        System.out.print(root.value + " ");
        if (root.right != null) this.print(root.right);
    }

}

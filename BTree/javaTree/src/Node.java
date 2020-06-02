public class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = null;
        right = null;
    }

    public void insert(int value) {
        if (value < key) {
            if (left != null) {
                left.insert(value);
            } else {
                left = new Node(value);
            }
        } else if (value > key) {
            if (right != null) {
                right.insert(value);
            } else {
                right = new Node(value);
            }
        }
    }

    public Node search (int value){
        if (value==key){
            return this;
        }else if(value<key && left!=null) {
            return left.search(value);
        }else if(right!=null)
            return right.search(value);
        return null;
    }

    public static Node delete(Node root, int value) {
        /* Base Case: If the tree is empty */
        if (root == null)
            return null;
        /* Otherwise, recur down the tree */
        if (value < root.key)
            root.left = delete(root.left, value);
        else if (value > root.key) {
            root.right = delete(root.right, value);
        }else { // if key is same as root's key, then This is the node to be deleted
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = minValue(root.right);
            // Delete the inorder successor
            root.right = delete(root.right, root.key);
        }
        return root;
    }

    private static int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    public void draw() {
        if (left != null)
            left.draw();
        System.out.print(key + " ");
        if (right != null)
            right.draw();
    }
} 
public class GenericNode <T extends Comparable<T>> {
    T key;
    GenericNode<T> left, right;

    public GenericNode(T item) {
        key = item;
        left = null;
        right = null;
    }

    public void insert(T value) {
        if (value.compareTo(key)<0) {
            if (left != null) {
                left.insert(value);
            } else {
                left = new GenericNode<>(value);
            }
        } else if (value.compareTo(key)>0) {
            if (right != null) {
                right.insert(value);
            } else {
                right = new GenericNode<>(value);
            }
        }
    }
    public GenericNode<T> search (T value){
        if (value.compareTo(key)==0){
            return this;
        }else if(value.compareTo(key)<0 && left!=null) {
            return left.search(value);
        }else if(right!=null)
            return right.search(value);
        return null;
    }
    public GenericNode<T> delete(GenericNode<T> root, T value) {
        /* Base Case: If the tree is empty */
        if (root == null)
            return null;
        /* Otherwise, recur down the tree */
        if (value.compareTo(root.key)<0)
            root.left = delete(root.left, value);
        else if (value.compareTo(root.key)>0) {
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
    private T minValue(GenericNode<T> root) {
        T minv = root.key;
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

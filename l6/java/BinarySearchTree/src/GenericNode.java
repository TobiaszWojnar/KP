/**
 * The <code>GenericNode</code> class is responsible for creating binary search tree.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 * @param <T> type of key in nodes of {@link GenericBinaryTree tree}
 * <p>
 * <code>GenericNode</code> implements:
 * <ul> {@link #insert(Comparable)}
 * <ul> {@link #delete(Comparable)}
 * <li> {@link #search(Comparable)}
 * <li>{@link #draw()} function printing inOrder elements in tree.
 * </ul> <p>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         GenericBinaryTree
 * @see         TestImplementation
 */
public class GenericNode <T extends Comparable<T>> {
    public T key;
    public GenericNode<T> left, right;

    /**
     * Constructor for node.
     * Initiates node with empty left and right child.
     * @param item key of the node
     */
    public GenericNode(T item) {
        key = item;
        left = null;
        right = null;
    }

    /**
     * Inserts value to tree.
     * If value is smaller then key inserts it into left subtree, if larger then into right.
     * If key equal to value then ignores.
     * @param value to be inserted
     */
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

    /**
     * Searches for key in tree.
     * @param value parameter to be found
     * @return pointer on node or null if value not found
     */
    public GenericNode<T> search (T value){
        if (value.compareTo(key)==0){
            return this;
        }else if(value.compareTo(key)<0 && left!=null) {
            return left.search(value);
        }else if(right!=null)
            return right.search(value);
        return null;
    }

    /**
     * Deletes key from tree.
     * @param value to be deleted
     * @return if deleted itself it returns pointer to its children or to successor
     */
    public GenericNode<T> delete(T value) {
        if (value.compareTo(key) < 0 && left!=null)
            left = left.delete(value);
        else if (value.compareTo(key) > 0 && right!=null) {
            right = right.delete(value);
        } else { //value == key
            if (left == null)
                return right;
            else if (right == null)
                return left;
            //node has two children: get successor and delete it in right sub tree
            key = minValue(right);
            right = right.delete(key);
        }
        return this;
    }

    /**
     * Searches for smallest value in subtree.
     * @param root node from which it starts search
     * @return smallest key
     */
    private T minValue(GenericNode<T> root) {
        T minKey = root.key;
        while (root.left != null) {
            minKey = root.left.key;
            root = root.left;
        }
        return minKey;
    }

    /**
     * Prints tree inOrder with arrows to indicate hierarchy in tree.
     */
    public void draw (){
        if (left != null) {
            System.out.print("↲ ");
            left.draw();
            System.out.print("↗ ");
        }
        System.out.print(key+" ");
        if (right != null) {
            System.out.print("↳ ");
            right.draw();
            System.out.print("↖ ");
        }
    }
}

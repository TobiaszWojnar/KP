/**
 * The <code>GenericBinaryTree</code> class is responsible for creating binary search tree.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 * @param <T> type of key in {@link GenericNode nodes}
 * </p>
 * <p>
 * <code>GenericBinaryTree</code> implements:
 * <ul>
 * <li> {@link #insert(Comparable)}
 * <li> {@link #delete(Comparable)}
 * <li> {@link #search(Comparable)}
 * <li>{@link #draw()} function printing inOrder elements in tree.
 * </ul> </p>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         GenericNode
 * @see         TestImplementation
 */
public class GenericBinaryTree <T extends Comparable<T>>{
    GenericNode<T> root;

    /**
     * Constructor for GenericBinaryTree.
     * Initiates tree with empty root.
     * @param treeType type of keys in nodes
     */
    GenericBinaryTree (Class<T> treeType) {
        root = null;
    }
    /**
     * Constructor for GenericBinaryTree.
     * Initiates tree with empty root.
     */
    GenericBinaryTree () {
        root = null;
    }

    /**
     * Inserts key to tree.
     * If root is empty adds key as root, else calls {@link GenericNode#insert(Comparable)} on root
     * @param key parameter to be inserted
     */
    public void insert(T key) {
        if(root==null)
            root=new GenericNode<>(key);
        else
            root.insert(key);
    }
    /**
     * Deletes key from tree.
     * If root is empty ignores it, else calls {@link GenericNode#delete(Comparable)} on root
     * @param key parameter to be deleted
     */
    public void delete(T key) {
        if (root != null) {
            root = root.delete(key);
        }
    }
    /**
     * Searches for key in tree.
     * Calls {@link GenericNode#search(Comparable)} on root
     * @param key parameter to be found
     * @return pointer on node or null if value not found
     */
    public GenericNode<T> search(T key){
        return root.search(key);
    }

    /**
     * Prints tree inOrder
     */
    public void draw(){
        if(root!=null) {
            root.draw();
        }
    }
}
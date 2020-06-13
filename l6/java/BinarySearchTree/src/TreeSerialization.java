/**
 * The <code>TreeSerialization</code> class is responsible for serializing {@link GenericBinaryTree tree} and returning it as <code>String</code>.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 *     <code>TreeSerialization</code> enables serializing <code>GenericBinaryTree</code> with {@link #getSerializedTree(GenericBinaryTree)} method.
 *     It return indexes of nodes and their key values.
 *     Root has index 1, every left child has index equal to twice as its parent
 *     and right child equals to twice plus one.
 *     Index number and key are separated by " " and nodes by "\t".
 * <p/>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         TreeCanvas
 * @see         MyServer
 * @see         GenericBinaryTree
 * @see         GenericNode
 */
public class TreeSerialization {
    /**
     * Gets serialized tree as indexes of nodes and their key values.
     * Root has index 1, every left child has index equal to twice as its parent
     * and right child equals to twice plus one.
     * Index number and key are separated by " " and nodes by "\t".
     * @param tree Tree to be serialized.
     * @return It return indexes of nodes and their key values.
     */
    public static String getSerializedTree(GenericBinaryTree tree){
        if (tree != null && tree.root != null)
            return getSerializedNode(tree.root, 1, "");
        return "";
    }

    /**
     * Recursive function serializing node and its children.
     * @param node node to be serialized
     * @param nodeIndex index of this node.
     * @param serializedTree nodes serialized so far.
     * @return It return indexes of nodes and their key values.
     */
    private static String getSerializedNode(GenericNode node, int nodeIndex, String serializedTree) {
        serializedTree=serializedTree+nodeIndex+" "+node.key+"\t";
        if(node.left!=null)
            serializedTree=getSerializedNode(node.left,2*nodeIndex,serializedTree);
        if(node.right!=null)
            serializedTree=getSerializedNode(node.right,2*nodeIndex+1,serializedTree);
        return serializedTree;
    }
}
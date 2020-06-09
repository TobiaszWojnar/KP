public class TreeSerialization {//TODO documentation
    public static String getSerializedTree(GenericBinaryTree tree){
        if (tree != null && tree.root != null)
            return getSerializedNode(tree.root, 1, "");
        return "";
    }

    private static String getSerializedNode(GenericNode node, int nodeIndex, String serializedTree) {
        serializedTree=serializedTree+nodeIndex+" "+node.key+"\t";
        if(node.left!=null)
            serializedTree=getSerializedNode(node.left,2*nodeIndex,serializedTree);
        if(node.right!=null)
            serializedTree=getSerializedNode(node.right,2*nodeIndex+1,serializedTree);
        return serializedTree;
    }
}
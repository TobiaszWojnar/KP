import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

class TreeContainer extends JPanel {
    JTree jTree;
    public TreeContainer (GenericNode root){
        if(root!=null){
            DefaultMutableTreeNode jRoot = new DefaultMutableTreeNode(root.key);
            addToTree(root, jRoot);

            jTree = new JTree(jRoot);
            add(jTree);
            jTree.setShowsRootHandles(true);
            for (int i = 0; i < jTree.getRowCount(); i++) {
                jTree.expandRow(i);
            }
            jTree.setEnabled(false);
        }
    }
    private void addToTree(GenericNode rootNode, DefaultMutableTreeNode root) {
        if (rootNode != null) {
            if (rootNode.left != null) {
                DefaultMutableTreeNode left = new DefaultMutableTreeNode(rootNode.left.key);
                root.add(left);
                addToTree(rootNode.left, left);
            }
            if (rootNode.right != null) {
                DefaultMutableTreeNode right = new DefaultMutableTreeNode(rootNode.right.key);
                root.add(right);
                addToTree(rootNode.right, right);
            }
        }
    }
}
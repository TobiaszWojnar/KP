import javax.swing.*;

public class Main {

    public static void main(String[] args) {//TODO make separate main for server
        //TODO only server know type it returns string
        //TODO make serializator na serwerze
        testInt();
        //testDouble();
        //testString();
    }
    public static void testInt(){
        GenericBinaryTree<Integer> treeInt = new GenericBinaryTree<>();
        treeInt.insert(50);
        treeInt.insert(30);
        treeInt.insert(20);
        treeInt.insert(40);
        treeInt.insert(70);
        treeInt.insert(60);
        treeInt.insert(80);

        System.out.println("Inorder traversal of the given tree");
        treeInt.draw();
        System.out.println("\nDelete 80");
        treeInt.delete(80);
        System.out.println("Inorder traversal of the modified tree");
        treeInt.draw();
        treeInt.delete(30);//TODO does not delete root
        treeInt.draw();
        SwingUtilities.invokeLater(() -> new TreeGUI(treeInt));
    }
    public static void testDouble(){
        GenericBinaryTree<Double> treeDouble = new GenericBinaryTree<>();
        treeDouble.insert(50.0);
        treeDouble.insert(30.0);
        treeDouble.insert(20.0);
        treeDouble.insert(40.0);
        treeDouble.insert(70.0);
        treeDouble.insert(60.0);
        treeDouble.insert(80.0);

        System.out.println("Inorder traversal of the given tree");
        treeDouble.draw();
        System.out.println("\nDelete 80");
        treeDouble.delete(80.0);
        System.out.println("Inorder traversal of the modified tree");
        treeDouble.draw();
        System.out.println(treeDouble.search(30.0).right.key);

        //SwingUtilities.invokeLater(() -> new TreeGUI(treeDouble));
    }
    public static void testString(){
        //BinarySearchTree tree2 = new BinarySearchTree();
        GenericBinaryTree<String> tree2 = new GenericBinaryTree<>();
        tree2.insert("50.0");
        tree2.insert("30.0");
        tree2.insert("20.0");
        tree2.insert("40.0");
        tree2.insert("70.0");
        tree2.insert("60.0");
        tree2.insert("80.0");

        System.out.println("Inorder traversal of the given tree");
        tree2.draw();
        System.out.println("\nDelete 80");
        tree2.delete("80.0");
        System.out.println("Inorder traversal of the modified tree");
        tree2.draw();
        System.out.println(tree2.search("30.0").right.key);
        /*
        System.out.println("\nDelete 30");
        tree.delete(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.draw();

        System.out.println("\nDelete 50");
        tree.delete(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.draw();
        */
        //SwingUtilities.invokeLater(() -> new TreeGUI(tree2));
    }
}

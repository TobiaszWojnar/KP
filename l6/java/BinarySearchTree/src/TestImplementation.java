/**
 * The <code>TestImplementation</code> class is responsible for testing implementation of {@link GenericBinaryTree binary tree}.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 * <code>GenericBinaryTree</code> implements:
 * <ul>
 * <li> {@link GenericBinaryTree#insert(Comparable) insert(Comparable)}
 * <li> {@link GenericBinaryTree#delete(Comparable) delete(Comparable)}
 * <li> {@link GenericBinaryTree#search(Comparable) search(Comparable)} function returns pointer to element or null
 * <li>{@link GenericBinaryTree#draw() draw()} function printing inOrder elements in tree.
 * </ul> <p>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         GenericNode
 * @see         GenericBinaryTree
 */
public class TestImplementation {

    /**
     * Main class tests implementation of {@link GenericBinaryTree}.
     * Tests implementation on {@link #testInt() integers},  {@link #testDouble() doubles} and {@link #testString() strings}
     */
    public static void main(String[] args) {
        testInt();
        testDouble();
        testString();
    }

    /**
     * Creates {@link GenericBinaryTree} with keys being {@link Integer}.
     * Creates tree, inserts values, deletes and searches.
     */
    public static void testInt(){
        GenericBinaryTree<Integer> treeInt = new GenericBinaryTree<>();
        System.out.println("Integer tree\nInsert 50");
        treeInt.insert(50);
        System.out.println("Delete 50");
        treeInt.delete(50);
        treeInt.draw();
        System.out.println("Insert 30");
        treeInt.insert(30);
        treeInt.draw();
        System.out.println("Insert 20,40,70,60,80");
        treeInt.insert(20);
        treeInt.insert(40);
        treeInt.insert(70);
        treeInt.insert(60);
        treeInt.insert(80);

        System.out.println("Inorder traversal of the given tree");
        treeInt.draw();
        System.out.println("Search 80");
        System.out.println(treeInt.search(80));
        System.out.println("Delete 80");
        treeInt.delete(80);
        System.out.println("Search 80");
        System.out.println(treeInt.search(80));
        treeInt.draw();
        System.out.println("Delete 30");
        treeInt.delete(30);
        treeInt.draw();
        System.out.println("Delete 50");
        treeInt.delete(50);
        treeInt.draw();
        System.out.println("Delete 60,70,20,40");
        treeInt.delete(60);
        treeInt.delete(70);
        treeInt.delete(20);
        treeInt.delete(40);
        treeInt.draw();
    }
    /**
     * Creates {@link GenericBinaryTree} with keys being {@link Double}.
     * Creates tree, inserts values, deletes and searches.
     */
    public static void testDouble(){
        GenericBinaryTree<Double> treeDouble = new GenericBinaryTree<>();
        System.out.println("Double Tree\n" +
                "Inert 50,30,20,40,70,60,80");
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
    }
    /**
     * Creates {@link GenericBinaryTree} with keys being {@link String}.
     * Creates tree, inserts values, deletes and searches.
     */
    public static void testString() {
        GenericBinaryTree<String> tree2 = new GenericBinaryTree<>();
        System.out.println("String Tree\n" +
                "Inert 50,30,20,40,70,60,80");
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

        System.out.println("\nDelete 30");
        tree2.delete("30");
        System.out.println("Inorder traversal of the modified tree");
        tree2.draw();

        System.out.println("\nDelete 50");
        tree2.delete("50");
        System.out.println("Inorder traversal of the modified tree");
        tree2.draw();
    }
}

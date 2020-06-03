public class GenericBinaryTree <T extends Comparable<T>>{
    GenericNode<T> root;
    GenericBinaryTree () {
        root = null;
    }

    public void insert(T key) {
        if(root==null)
            root=new GenericNode<>(key);
        root.insert(key);
    }

    public void delete(T key) {
        if (root != null) {
            root = root.delete(key);
        }
    }

    public GenericNode<T> search(T key){
        return root.search(key);
    }
    public void draw() {
        if(root!=null) {
            root.draw();
            System.out.println(" ");
        }
    }


}
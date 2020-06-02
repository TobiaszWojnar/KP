public class GenericBinaryTree <T extends Comparable<T>>{
    GenericNode<T> root;
    GenericBinaryTree () {
        root = null;
    }

    public void insert(T key) {
        if(root==null)
            root=new GenericNode<T>(key);
        root.insert(key);
    }

    public void delete(GenericNode<T> root, T key) {//Was a problem with deleting root
        root.delete(this.root, key);//is it nice?
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

    public void delete(T key) {
        delete(this.root, key);
    }
}
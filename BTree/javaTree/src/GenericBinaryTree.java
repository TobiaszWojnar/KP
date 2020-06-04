public class GenericBinaryTree <T extends Comparable<T>>{
    GenericNode<T> root;
    GenericBinaryTree (Class<T> treeType) {
        root = null;
    }
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
    public String draw() {
        String result="";
        if(root!=null) {
            result.concat(root.draw(result));
        }
        return result;
    }


}
public class GenericNode <T extends Comparable<T>> {//TODO documentation
    public T key;
    public GenericNode<T> left, right;

    public GenericNode(T item) {
        key = item;
        left = null;
        right = null;
    }

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
    public GenericNode<T> search (T value){
        if (value.compareTo(key)==0){
            return this;
        }else if(value.compareTo(key)<0 && left!=null) {
            return left.search(value);
        }else if(right!=null)
            return right.search(value);
        return null;
    }
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
    private T minValue(GenericNode<T> root) {
        T minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

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

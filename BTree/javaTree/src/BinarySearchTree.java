class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        if(root==null)
            root=new Node(key);
        root.insert(key);
    }

    public void delete(int key) {//Was a problem with deleting root
        root = Node.delete(root, key);//is it nice?
    }

    public Node search(int key){
        return root.search(key);
    }
    public void draw() {
        if(root!=null) {
            root.draw();
            System.out.println(" ");
        }
    }

}


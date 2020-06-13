#include<iostream>
using namespace std;
/**
 * The <code>BinarySearchTree</code> class is an implementation of binary search tree on generic types.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create GUI  application and implement client-server architecture.
 * @tparam <T> type of elements in tree
 * <p>
 * <code>BinarySearchTree</code> implements:
 * <ul>
 * <li> <code>insert</code>
 * <li> <code>delete</code>
 * <li> <code>search</code>
 * <li> <code>draw</code> function printing inOrder elements in tree.
 * </ul> </p>
 * @version     11 June 2020
 * @author      Tobiasz Wojnar
 */
template<typename T> class BinarySearchTree {
public:
    /**
     * Constructor for GenericBinaryTree.
     * Initiates tree with empty root.
     */
    BinarySearchTree();
    /**
     * Base destructor.
     */
    ~BinarySearchTree();
    /**
     * Inserts key to tree.
     * If key is equal to value already in tree it is ignored.
     */
    void insert(T);
    /**
     * Searches for key in tree.
     * @return true if value is in tree
     */
    bool search(T);
    /**
     * Deletes key from tree.
     */
    void deleteNode(T);
    /**
     * Prints tree inOrder with arrows to indicate hierarchy in tree.
     */
    void draw();
private:
    struct node{
        T key;
        node* left;
        node* right;
    };
    node* root;
    /**
     * Inserts value to subtree.
     * If value is smaller then key inserts it into left subtree, if larger then into right.
     * If key equal to value then ignores.
     */
    static void insert(node*&,T);
    /**
     * Searches for key in subtree.
     * @return true if value is in tree
     */
    static bool search(node*&,T);
    /**
     * Deletes key from subtree.
     */
    static void deleteNode(node*&,T);
    /**
     * Searches for smallest value in subtree.
     * @return smallest key in sub tree.
     */
    static int minValue(node *);
    /**
     * Prints subtree inOrder with arrows to indicate hierarchy in tree.
     */
    static void draw(node*);

};


int main() {
    BinarySearchTree<float> tree = *new BinarySearchTree<float>;
    tree.insert(50);
    tree.insert(60);
    tree.insert(40);
    cout<<"inserted 50, 60, 40\n";
    tree.draw();
    cout<<"searched for 60\nResult =\t"<<tree.search(60)<<endl;
    cout<<"searched for 70\nResult =\t"<<tree.search(70)<<endl;

    cout<<"Deleting node 60\n";
    tree.deleteNode(60);
    tree.draw();

    cout<<"Inserted 80,20,30,10\n";
    tree.insert(80);
    tree.insert(20);
    tree.insert(30);
    tree.insert(10);
    tree.draw();

    return 0;
}

/**
 * Constructor for GenericBinaryTree.
 * Initiates tree with empty root.
  * @tparam T type of elements in tree
  */
template<typename T>
BinarySearchTree<T>::BinarySearchTree() {
    root= nullptr;
}

/**
 * Base destructor.
 */
template<typename T>
BinarySearchTree<T>::~BinarySearchTree() = default;
 /**
  * Inserts key to tree.
  * If key is equal to value already in tree it is ignored.
  * @tparam T type of elements in tree
  * @param key parameter to be inserted
  */
template<typename T>
void BinarySearchTree<T>::insert(T key) {
        insert(root, key);
}

 /**
  * Inserts value to subtree.
  * If value is smaller then key inserts it into left subtree, if larger then into right.
  * If key equal to value then ignores.
  * @tparam T type of elements in tree.
  * @param tempRoot root fo subtree.
  * @param newKey new key to be inserted.
  */
template<typename T>
void BinarySearchTree<T>::insert(node* &tempRoot, T newKey) {
    if(tempRoot!=nullptr){
        if(newKey<tempRoot->key){
            insert(tempRoot->left,newKey);
        }else if(newKey>tempRoot->key){
            insert(tempRoot->right,newKey);
        }
    } else{
        tempRoot = new node;
        tempRoot->key=newKey;
        tempRoot->left=nullptr;
        tempRoot->right=nullptr;
    }
}

/**
 * Searches for key in tree.
 * @tparam T type of elements in tree
 * @param newKey parameter to be found.
 * @return true if value is in tree.
 */
template<typename T>
bool BinarySearchTree<T>::search(T newKey) {
    return search(root,newKey);
}
/**
 *
 * @tparam T type of elements in tree
 * @param tempRoot root fo subtree
 * @param value searched element
 * @return true if value is in tree.
 */
template<typename T>
bool BinarySearchTree<T>::search(node* &tempRoot, T value) {
    if(tempRoot!=nullptr) {
        if(value==tempRoot->key){
            return true;
        }else if(value<tempRoot->key){
            return search(tempRoot->left,value);
        }else if(value>tempRoot->key){
            return search(tempRoot->right,value);
        }
    }
    return false;
}
/**
 * Deletes element form tree.
 * @tparam T type of elements in tree.
 * @param value element to be deleted.
 */
template<typename T>
void BinarySearchTree<T>::deleteNode(T value) {
    deleteNode(root,value);
}
/**
 * Deletes element form subtree.
 * @tparam T type of elements in tree.
 * @param tempRoot root fo subtree.
 * @param value element to be deleted.
 */
template<typename T>
void BinarySearchTree<T>::deleteNode(node *&tempRoot, T value) {
    if(tempRoot!=nullptr){
        if(value<tempRoot->key){
            deleteNode(tempRoot->left,value);
        }else if(value>tempRoot->key){
            deleteNode(tempRoot->right,value);
        }else if(value==tempRoot->key) {
            node* toDelete=tempRoot;
            if(tempRoot->left==nullptr){
                tempRoot=tempRoot->right;
            }else if(tempRoot->right==nullptr){
                tempRoot=tempRoot->left;
            } else {//has two children
                tempRoot->key = minValue(tempRoot->right);
                deleteNode(tempRoot->right, tempRoot->key);
            }
            delete toDelete;
        }
    }
}
/**
 * Searches for minimal value in subtree.
 * @tparam T type of elements in tree.
 * @param tempRoot root fo subtree.
 * @return value of minimal element.
 */
template<typename T>
int BinarySearchTree<T>::minValue(node *tempRoot){
    int minKey = tempRoot->key;
    if (tempRoot->left!=nullptr){
        minKey = tempRoot->left->key;
        tempRoot->left = tempRoot->left->left;
    }
    return minKey;
}/**
 * Prints tree inOrder with arrows to indicate hierarchy in tree.
 * @tparam T type of elements in tree
 */
template<typename T>
void BinarySearchTree<T>::draw() {
    if(root!=nullptr) {
        draw(root);
    }
    cout<<endl;
}
/**
 * Prints tree inOrder with arrows to indicate hierarchy in tree.
 * @tparam T type of elements in tree
 * @param tempRoot root fo subtree
 */
template<typename T>
void BinarySearchTree<T>::draw(node * tempRoot) {
    if(tempRoot->left!=nullptr){
        cout<<"↲ ";
        draw(tempRoot->left);
        cout<<"↗ ";
    }
    cout<<tempRoot->key;
    if(tempRoot->right!=nullptr){
        cout<<"↳ ";
        draw(tempRoot->right);
        cout<<"↖ ";
    }
}
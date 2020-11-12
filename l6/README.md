# BTree
## List 6 - Implement in Java and C++ Binary Search Tree on generic types, create GUI and implement client-server architecture.

### Programing Course
Object-oriented programming course at Wroclaw University of Science and Technology
2020

#### Setup
###### Java
```
    cd java/BinarySearchTree/src
    javac *.java
```
* To test implementation run 
```
   java TestImplementation
```
* To run server:
```
    java MyServer
```
* To run GUI (it will not work without `MyServer`)
```
    java TreeGUI
```
###### C++
```
    cd cpp
    make
    make clean 
    ./BTree
```

#### Requirements
* [x] for 3.0
    * implement Binary Search Tree on generic types in Java and C++.
    * Functions: insert, search, delete, draw (printing tree inOrder)

* [x] for 4.0
    * Add GUI in Java

* [x] for 5.0
    * Java program should work in client-server architecture.
    * Client should be window app that sends requests to server and displays results.
    * Server should compute all requests.
* Generate documentation

#### What I Learned
* Declaring and using parameterized classes
* Algorithms for inserting, searching, deleting elements on binary trees
* Better understanding of listeners
* Better understanding of Swing (JScrollPane, JScrollBar, JTree, JSplitPane)
* Better understanding of javadoc
* understanding doxygen
* client-server architecture
* Using sockets

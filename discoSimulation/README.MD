discoSimulation
=====
Object-oriented programming course at Wroclaw University of Science and Technology 2020

List 5 - using threads create simulation with flickering squares

Installation
----
Run Command:
```
    cd src
    javac *.java
```
Parameters:
* `n & m` - board dimensions
* `k`     - speed
* `p`     - probability of changing color
Requirements
----
Simulate a board of `n` x `m` rectangles with random initial color.
Each rectangle is thread that after time `t` changes it's color.
Time `t` is random number of milliseconds in range [0.5k, 1.5k].


* [x] for 3.0 
    * rectangle changes its color:
        * with probability `p` to random
        * with probability `p-1` leaves old color
* [x] for 4.0
    * rectangle changes its color:
        * with probability `p` to random
        * with probability `p-1` to average of its neighbours (treat board as 2d torus)
    * take care of synchronization to avoid conflicts
* [x] for 5.0
    * add ability to stop each thread by clicking on rectangle

* [x] add rescaling application
* [ ] generate documentation with `javadoc` and `doxygen`

Wskazówki:
Zobacz opis klasy generuj¡cej liczby pseudolosowe java.util.Random i u»yj metod double
nextDouble() i int nextInt(int n) - generator pseudolosowy powinien by¢ tylko jeden w
caaej aplikacji.

What I Learned
----
* 

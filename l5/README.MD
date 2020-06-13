# discoSimulation
## List 5 -  using threads create simulation with flickering rectangles

### Programing Course
Object-oriented programming course at Wroclaw University of Science and Technology
2020

#### Setup
Run Command:
```
    cd discoSimulation/src
    javac *.java
    java L5
```
Parameters:
* `n & m` - board dimensions
* `k`     - speed
* `p`     - probability of changing color
#### Requirements
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
    * after being clicked does not take part in calculating average color of its neighbours

* [x] add rescaling application
* [x] generate documentation with `javadoc` and `doxygen`
* [x] use only one pseudorandom generator

#### What I Learned
* using labels and textFields in swing
* using threads
* stopping and restarting threads
* making better javadoc
* first used a ternary operator `a ? b : c`

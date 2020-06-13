all: BTree

BTree: main.o
	g++ -o BTree -lm main.o

main.o: main.cpp
	g++ -c main.cpp

clean: 
	rm -f *.o 

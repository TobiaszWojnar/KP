all: main

main: RzymArab.o RzymArabException.o Main.o
	g++ -o Main -lm RzymArab.o RzymArabException.o Main.o

RzymArab.o: RzymArab.cpp
	g++ -c RzymArab.cpp

RzymArabException.o: RzymArabException.cpp
	g++ -c RzymArabException.cpp

Main.o: Main.cpp
	g++ -c Main.cpp

clean: 
	rm -f Main *.o *~

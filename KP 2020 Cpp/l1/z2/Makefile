all: main

main: PrimeNumbers.o Test.o
	g++ -o Test -lm PrimeNumbers.o Test.o

LiczbyPierwsze.o: PrimeNumbers.cpp
	g++ -c PrimeNumbers.cpp

Test.o: Test.cpp
	g++ -c Test.cpp

clean: 
	rm -f main *.o *~

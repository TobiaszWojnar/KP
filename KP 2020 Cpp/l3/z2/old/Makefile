all: Figury

Figury: Czworokat.o Kolo.o Kwadrat.o Prostakat.o Pieciokat.o Romb.o Szesciokat.o Main.o
	g++ -o Figury -lm Czworokat.o Kolo.o Kwadrat.o Prostakat.o Pieciokat.o Romb.o Szesciokat.o Main.o

Czworokat.o: Czworokat.cpp
	g++ -c Czworokat.cpp

Kolo.o: Kolo.cpp
	g++ -c Kolo.cpp

Kwadrat.o: Kwadrat.cpp
	g++ -c Kwadrat.cpp

Prostakat.o: Prostakat.cpp
	g++ -c Prostakat.cpp

Pieciokat.o: Pieciokat.cpp
	g++ -c Pieciokat.cpp

Romb.o: Romb.cpp
	g++ -c Romb.cpp

Szesciokat.o: Szesciokat.cpp
	g++ -c Szesciokat.cpp

Main.o: Main.cpp
	g++ -c Main.cpp

clean: 
	rm -f *.o *~

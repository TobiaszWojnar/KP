all: Figure

Figure: Quadrangle.o Rhombus.o Square.o Rectangle.o Circle.o Pentagon.o Hexagon.o Main.o
	g++ -o Figure -lm Quadrangle.o Rhombus.o Square.o Rectangle.o Circle.o Pentagon.o Hexagon.o Main.o

Quadrangle.o: Quadrangle.cpp
	g++ -c  Quadrangle.cpp

Rhombus.o: Rhombus.cpp
	g++ -c Rhombus.cpp

Square.o: Square.cpp
	g++ -c Square.cpp

Rectangle.o: Rectangle.cpp
	g++ -c Rectangle.cpp

Circle.o: Circle.cpp
	g++ -c Circle.cpp

Pentagon.o: Pentagon.cpp
	g++ -c Pentagon.cpp

Hexagon.o: Hexagon.cpp
	g++ -c Hexagon.cpp

Main.o: Main.cpp
	g++ -c Main.cpp

clean: 
	rm -f *.o 

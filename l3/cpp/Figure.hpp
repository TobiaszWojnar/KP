/**
 * Virtual class for creating figures, with methods for calculating surface and circumference
 * It is extended by 4 subclasses, Hexagon, Pentagon, Quadrangle and Circle
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see Main.cpp for full requriments
 * @see Hexagon.cpp
 * @see Pentagon.cpp
 * @see Quadrangle.cpp
 * @see Circle.cpp
 */
#ifndef _FIGURE_HPP
#define _FIGURE_HPP

class Figure {
    public:
        virtual double Surface () = 0;
        virtual double Circumference () = 0;
};

#endif

package l3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiguryTest {

    @Test
    void isFigureTest() {
        assertThrows( IllegalArgumentException.class,()->Figury.isFigure(new String[] {"g"}));
        assertThrows( IllegalArgumentException.class,()->Figury.isFigure(new String[] {"j"}),"Shape 'j' does not exist");
        assertThrows( IndexOutOfBoundsException.class,()->Figury.isFigure(new String[] {}));

        assertEquals("c",Figury.isFigure(new String[] {"c"}));
    }

    @Test
    void hasValidArgsTest() {

    }

    @Test
    void calculateTest() {

    }
}
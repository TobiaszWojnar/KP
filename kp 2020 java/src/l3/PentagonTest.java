package l3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PentagonTest {
    Pentagon p = new Pentagon(2);
    @Test
    void surfaceTest() {
        assertEquals(1.25/Math.tan(Math.PI/5), new Pentagon(1).surface());
    }

    @Test
    void circumferenceTest() {
        assertEquals(5,new Pentagon(1).circumference());
        assertEquals(10,p.circumference());
    }
}
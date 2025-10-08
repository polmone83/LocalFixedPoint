package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    void getValue() {
        Assignment<Integer,Boolean> a = new Assignment<>(false);
        assertEquals(false,a.getValue(2));
        a.setValue(2,true);
        assertEquals(true, a.getValue(2));
    }

    @Test
    void setValue() {
    }
}
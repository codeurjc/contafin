package com.daw.contafin.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class UnitTest {

    @Test
    void testConstructorAndGetters() {
        // GIVEN
        String name = "Unit Test";
        Unit unit = new Unit(name);

        // THEN
        assertEquals(name, unit.getName());
    }

    @Test
    void testSetters() {
        // GIVEN
        Unit unit = new Unit();
        String name = "Updated Unit";
        long id = 1L;

        // WHEN
        unit.setName(name);
        unit.setId(id);

        // THEN
        assertEquals(name, unit.getName());
        assertEquals(id, unit.getId());
    }

    @Test
    void testLessonsAssociation() {
        // GIVEN
        Unit unit = new Unit();
        List<Lesson> lessons = new ArrayList<>();
        Lesson lesson1 = new Lesson();
        Lesson lesson2 = new Lesson();
        lessons.add(lesson1);
        lessons.add(lesson2);

        // WHEN
        unit.setLessons(lessons);

        // THEN
        assertNotNull(unit.getLessons());
        assertEquals(2, unit.getLessons().size());
        assertTrue(unit.getLessons().contains(lesson1));
        assertTrue(unit.getLessons().contains(lesson2));
    }
}

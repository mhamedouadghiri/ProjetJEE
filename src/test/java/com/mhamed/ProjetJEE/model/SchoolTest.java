package com.mhamed.ProjetJEE.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SchoolTest {
    public static final Long ID = 1L;
    public static final String EMAIL = "test@email.com";
    public static final String PASSWORD = "pwd123";
    public static final String PHONE = "0123456789";
    public static final String NAME = "school name";

    public final User user = new User(ID, EMAIL, PASSWORD, PHONE);
    public final School school = new School(ID, EMAIL, PASSWORD, PHONE, NAME);

    @Test
    void testNotEquals() {
        assertNotEquals(school, user);
        assertNotEquals(user, school);
    }

    @Test
    void testEquals() {
        User otherSchool = new School(ID, EMAIL, PASSWORD, PHONE, NAME);

        assertEquals(school, otherSchool);
        assertEquals(otherSchool, school);
    }
}

package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;
import com.mhamed.ProjetJEE.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StudentDatasourceTest extends User {

    private StudentDatasource studentDatasource;

    @BeforeClass
    public void beforeClass() {
        studentDatasource = new StudentDatasource();
    }

    @AfterClass
    public void afterClass() {
        JDBCConnection.closeConnection();
    }

    @Test
    public void testGet() {
        Student actual = (Student) studentDatasource.get(1L);

        Student expected = new Student(1L,
                "btolland0@hostgator.com",
                "73a16db84386c0c8b990c3716a64ba8b19e82f9b",
                "9293522440",
                "Blane",
                "Tolland",
                "Sihe",
                "China",
                "3 Iowa Parkway",
                false,
                4L,
                "Basic Industries",
                20L);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetByEmail() {
        // with null values

        Student actual = (Student) studentDatasource.getByEmail("rholbury1@t-online.de");

        Student expected = new Student(2L,
                "rholbury1@t-online.de",
                "45d6035a87852a838f1be18282e4d1d62db42895",
                "1789010614",
                "Reynard",
                "Holbury",
                "São Marcos",
                "Brazil",
                "03455 Arrowood Trail",
                false,
                null,
                null,
                8L);

        Assert.assertEquals(actual, expected);
    }
}

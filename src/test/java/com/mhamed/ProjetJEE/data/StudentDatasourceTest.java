package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Student;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
class StudentDatasourceTest {

    private static StudentDatasource studentDatasource;

    @BeforeAll
    static void beforeAll() {
        studentDatasource = new StudentDatasource();
    }

    @AfterAll
    static void afterAll() {
        JDBCConnection.closeConnection();
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void get() {
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

        Assertions.assertEquals(actual, expected);
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

        Assertions.assertEquals(actual, expected);
    }
}

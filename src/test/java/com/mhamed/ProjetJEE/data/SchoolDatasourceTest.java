package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.School;
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
class SchoolDatasourceTest {

    private static SchoolDatasource schoolDatasource;

    @BeforeAll
    static void beforeAll() {
        schoolDatasource = new SchoolDatasource();
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
        School actual = (School) schoolDatasource.get(1L);

        School expected = new School(1L,
                "ameins0@mozilla.org",
                "909a38a6d05750564031fc1c5bb0288bcd5a2ea4",
                "9361142625",
                "Philosophisch-Theologische Hochschule der Salesianer Don Boscos");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void getByEmail() {
        School actual = (School) schoolDatasource.getByEmail("ameins0@mozilla.org");

        School expected = new School(1L,
                "ameins0@mozilla.org",
                "909a38a6d05750564031fc1c5bb0288bcd5a2ea4",
                "9361142625",
                "Philosophisch-Theologische Hochschule der Salesianer Don Boscos");

        Assertions.assertEquals(actual, expected);
    }
}

package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.School;
import com.mhamed.ProjetJEE.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SchoolDatasourceTest extends User {

    private SchoolDatasource schoolDatasource;

    @BeforeClass
    public void beforeClass() {
        schoolDatasource = new SchoolDatasource();
    }

    @AfterClass
    public void afterClass() {
        JDBCConnection.closeConnection();
    }

    @Test
    public void testGet() {
        School actual = (School) schoolDatasource.get(1L);

        School expected = new School(1L,
                "ameins0@mozilla.org",
                "909a38a6d05750564031fc1c5bb0288bcd5a2ea4",
                "9361142625",
                "Philosophisch-Theologische Hochschule der Salesianer Don Boscos");

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetByEmail() {
        School actual = (School) schoolDatasource.getByEmail("ameins0@mozilla.org");

        School expected = new School(1L,
                "ameins0@mozilla.org",
                "909a38a6d05750564031fc1c5bb0288bcd5a2ea4",
                "9361142625",
                "Philosophisch-Theologische Hochschule der Salesianer Don Boscos");

        Assert.assertEquals(actual, expected);
    }
}

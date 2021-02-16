package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Company;
import com.mhamed.ProjetJEE.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompanyDatasourceTest extends User {

    private CompanyDatasource companyDatasource;

    @BeforeClass
    public void beforeClass() {
        companyDatasource = new CompanyDatasource();
    }

    @AfterClass
    public void afterClass() {
        JDBCConnection.closeConnection();
    }

    @Test
    public void testGet() {
        Company actual = (Company) companyDatasource.get(1L);

        Company expected = new Company(1L,
                "ccrickmer0@ihg.com",
                "39f7652c0e1242838ba2f8ee6afd6ffdc482f63b",
                "2832212609",
                "Zoombeat",
                null,
                "Cochrane",
                null,
                "69988 Barby Park");

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetByEmail() {
        Company actual = (Company) companyDatasource.getByEmail("ccrickmer0@ihg.com");

        Company expected = new Company(1L,
                "ccrickmer0@ihg.com",
                "39f7652c0e1242838ba2f8ee6afd6ffdc482f63b",
                "2832212609",
                "Zoombeat",
                null,
                "Cochrane",
                null,
                "69988 Barby Park");

        Assert.assertEquals(actual, expected);
    }
}

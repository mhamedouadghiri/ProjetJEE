package com.mhamed.ProjetJEE.data;

import com.mhamed.ProjetJEE.model.Company;
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
class CompanyDatasourceTest {

    private static CompanyDatasource companyDatasource;

    @BeforeAll
    static void beforeAll() {
        companyDatasource = new CompanyDatasource();
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
    void get() {
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

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void getByEmail() {
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

        Assertions.assertEquals(actual, expected);
    }
}

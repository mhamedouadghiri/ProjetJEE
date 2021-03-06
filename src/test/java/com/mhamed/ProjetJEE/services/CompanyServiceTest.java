package com.mhamed.ProjetJEE.services;

import com.mhamed.ProjetJEE.util.TestUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

import static com.mhamed.ProjetJEE.util.TestUtils.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Arquillian.class)
class CompanyServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @BeforeEach
    void setUp() {
        Assumptions.assumeTrue(TestUtils.serverListening(), "Server is not up");
    }

    @Test
    void getAll() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/company/all")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        assertEquals(HttpURLConnection.HTTP_OK, response.code());
        assertEquals(MediaType.get("application/json"), Objects.requireNonNull(response.body()).contentType());
    }
}

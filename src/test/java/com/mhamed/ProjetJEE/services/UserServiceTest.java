package com.mhamed.ProjetJEE.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhamed.ProjetJEE.util.TestUtils;
import com.mhamed.ProjetJEE.model.School;
import okhttp3.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;

import static com.mhamed.ProjetJEE.util.TestUtils.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Arquillian.class)
class UserServiceTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

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
    void getUser_statusCode() throws IOException {
        Response response = getResponseSchool1();
        assertEquals(HttpURLConnection.HTTP_OK, response.code());
    }

    @Test
    void getUser_mediaType() throws IOException {
        Response response = getResponseSchool1();
        ResponseBody responseBody = response.body();

        Assertions.assertNotNull(responseBody);

        assertEquals(MediaType.get("application/json"), responseBody.contentType());
    }

    @Test
    void getUser_payload() throws IOException {
        Response response = getResponseSchool1();
        ResponseBody responseBody = response.body();

        Assertions.assertNotNull(responseBody);

        School actualSchool = objectMapper.readValue(responseBody.string(), School.class);

        School expectedSchool1 = new School(1L,
                "ameins0@mozilla.org",
                "909a38a6d05750564031fc1c5bb0288bcd5a2ea4",
                "9361142625",
                "Philosophisch-Theologische Hochschule der Salesianer Don Boscos");

        Assertions.assertEquals(expectedSchool1, actualSchool);
    }

    @Test
    void checkUserCredentials_mediaType_bad() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "password=0&user-type=0&email=0");
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/users/auth/check-user")
                .method("POST", body)
                .addHeader("Content-Type", "text/plain")
                .build();
        Response response = client.newCall(request).execute();

        Assertions.assertEquals(HttpURLConnection.HTTP_UNSUPPORTED_TYPE, response.code());
    }

    @Test
    void checkUserCredentials_userType_bad() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "password=0&user-type=0&email=0");
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/users/auth/check-user")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();

        Assertions.assertEquals(HttpURLConnection.HTTP_BAD_REQUEST, response.code());
    }

    @NotNull
    private Response getResponseSchool1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/users/user/1?user-type=school")
                .method("GET", null)
                .build();
        return client.newCall(request).execute();
    }
}

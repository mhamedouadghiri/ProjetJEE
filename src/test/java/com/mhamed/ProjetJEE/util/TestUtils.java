package com.mhamed.ProjetJEE.util;

import java.net.Socket;

public class TestUtils {
    public static final String BASE_URL = "http://localhost:8080/ProjetJEE-1.0-SNAPSHOT";
    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    public static boolean serverListening() {
        Socket s = null;
        try {
            s = new Socket(HOST, PORT);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (Exception ignored) {
                }
        }
    }
}

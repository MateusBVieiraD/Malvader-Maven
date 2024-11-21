package org.example.view;

public class Sessao {
    private static String user;
    private static String password;

    public static void setUser(String user) {
        Sessao.user = user;
    }

    public static void setPassword(String password) {
        Sessao.password = password;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}

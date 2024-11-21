package org.example.view;

public class Sessao {
    private static String user;

    public static void setUser(String user) {
        Sessao.user = user;
    }

    public static String getUser() {
        return user;
    }

}

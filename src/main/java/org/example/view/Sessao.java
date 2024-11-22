package org.example.view;

import org.example.entity.UsuarioEntity;

public class Sessao {
    private static String user;
    private static String id;

    public static void setUser(String user) {
        Sessao.user = user;
    }

    public static String getUser() {
        return user;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Sessao.id = id;
    }

}

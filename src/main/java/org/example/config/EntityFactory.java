package org.example.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.entity.UsuarioEntity;

public class EntityFactory {
    //Instancia de um Singleton
    private static EntityManagerFactory entityManagerFactory;

    private EntityFactory() {}

    private static EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null){
            synchronized (EntityFactory.class){
                if (entityManagerFactory == null){
                    entityManagerFactory = Persistence.createEntityManagerFactory("malvader");
                }
            }
        }

        return entityManagerFactory;
    }

    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }

    public static void fechar(){
        if (entityManagerFactory != null && entityManagerFactory.isOpen()){
            entityManagerFactory.close();
        }
    }


}

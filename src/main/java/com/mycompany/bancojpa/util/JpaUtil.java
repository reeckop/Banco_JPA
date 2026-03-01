package com.mycompany.bancojpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT = "EmpresaPU";
    private static EntityManagerFactory emf;

    private JpaUtil() {}

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emf.createEntityManager();
    }
}
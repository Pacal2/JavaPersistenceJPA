package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Ufo ufo = new Ufo();
        ufo.setId(2);
        ufo.setName("Maria");
        ufo.setTech("Hardware");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(ufo);
        entityManager.getTransaction().commit();


        System.out.println(ufo);

    }
}

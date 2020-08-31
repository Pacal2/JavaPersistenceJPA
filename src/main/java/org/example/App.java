package org.example;

import javax.persistence.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("persistenceUnit");

    public static void main( String[] args )
    {

        addCustomer(1, "Sue", "Smith");
        addCustomer(2, "Mary", "Smith");
        addCustomer(3, "Jason", "Smith");
        addCustomer(4, "Bob", "Smith");

        getCustomer(1);

        changeFName(4, "Mark");

        deleteCustomer(3);

        entityManagerFactory.close();






    }

    public static void addCustomer(int id, String fName, String lName)  {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Customer customer = new Customer();
            customer.setId(id);
            customer.setfName(fName);
            customer.setlName(lName);

            entityManager.persist(customer);
            entityTransaction.commit();
        }
        catch (Exception ex) {
            if(entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    public static void getCustomer(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";

        TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);
        typedQuery.setParameter("custID", id);

        Customer customer = null;
        try {
            customer = typedQuery.getSingleResult();
            System.out.println(customer.getfName() + " " + customer.getlName());
        }
        catch (NoResultException exception) {
            System.out.println(exception);
        }
        finally {
            entityManager.close();
        }

    }

    public static void changeFName(int id, String fName)  {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        Customer customer = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            customer = entityManager.find(Customer.class, id);
            customer.setfName(fName);

            entityManager.persist(customer);
            entityTransaction.commit();
        }
        catch (Exception ex) {
            if(entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    public static void deleteCustomer(int id)  {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        Customer customer = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();


            customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);

            entityManager.persist(customer);
            entityTransaction.commit();
        }
        catch (Exception ex) {
            if(entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

}

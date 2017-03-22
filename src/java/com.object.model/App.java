package com.object.model;

import javax.persistence.*;

/**
 * Created by JackLee on 2017/3/22.
 */
public class App {
    public static void main(String[] args) {
        //open a database connection
        //(create a new database if not exists)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("points.odb");
        EntityManager em = emf.createEntityManager();

        //save 1000 Point objects in the database
        em.getTransaction().begin();
        for (int i = 0; i < 200000; i++) {
            Point p = new Point(i,i);
            em.persist(p);
        }
        em.getTransaction().commit();


        //find the num of Point objects in the database
        Query q1 = em.createQuery("select count(p) from Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        //find the average X value
        Query q2 = em.createQuery("select avg(p.x) from Point p");
        System.out.printf("Avg X: " + q2.getSingleResult());


        //close the database connection
        em.close();
        emf.close();


    }
}

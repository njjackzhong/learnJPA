package com.object.model;

import com.object.dao.PointDao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by JackLee on 2017/3/22.
 */
public class App {
    public static void main(String[] args) {

//        PointDao dao = new PointDao();
//        List<Point> points = dao.findAll();


        //open a database connection
        //(create a new database if not exists)
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("points.odb");        //represents a database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.object.pu");        //represents a database
        EntityManager em = emf.createEntityManager();                // represents a connection to database

        //find the num of Point objects in the database
        //1000000
        //1046130
        Query q = em.createQuery("select count(p) from Point p");
        Object c = q.getSingleResult();

        System.out.println("Total Points: " + q.getSingleResult());

        //save 1000 Point objects in the database
        em.getTransaction().begin();
        for (int i = 100000; i < 110000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();


        //find the num of Point objects in the database
        Query q1 = em.createQuery("select count(p) from Point p");
        Object c1 = q1.getSingleResult();
        System.out.println("Total Points: " + c1);

        //find the average X value
        Query q2 = em.createQuery("select avg(p.x) from Point p");
        System.out.println("Avg X: " + q2.getSingleResult());

        //retrieve all the Points objects from the database
        TypedQuery<Point> q3 = em.createQuery("select p from Point p where p.x > 100000", Point.class);
        List<Point> results = q3.getResultList();

        System.out.println("Count Point: " + results.size());
//        results.forEach(System.out::println);

        //close the database connection
        em.close();
        emf.close();


    }
}

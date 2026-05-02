package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure(); // no argument

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();


        // 🔹 INSERT OPERATION
        Movie m = new Movie(1, "Pushpa", "2025-05-01", "Released");
        session.save(m);

        System.out.println("Inserted Successfully");

        tx.commit();

        // 🔹 UPDATE USING HQL (POSITIONAL PARAMETERS)
        session.beginTransaction();

        String hql = "update Movie set name=?1, status=?2 where id=?3";
        Query q = session.createQuery(hql);

        q.setParameter(1, "Pushpa 2");
        q.setParameter(2, "Blockbuster");
        q.setParameter(3, 1);

        int rows = q.executeUpdate();

        System.out.println("Updated Rows: " + rows);

        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}
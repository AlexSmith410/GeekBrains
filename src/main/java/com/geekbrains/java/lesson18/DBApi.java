package com.geekbrains.java.lesson18;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;


public class DBApi {
    private Session session;
    private SessionFactory sessionFactory;

    public DBApi(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        session = null;
    }

    public void startSession() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    public void endSession() {
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }

    public void getGoodsByConsumerName(String name){
        Query query = session.createQuery("SELECT c FROM Consumer c WHERE c.name = :name", Consumer.class);
        query.setParameter("name", name);
        Consumer consumer = (Consumer) query.getSingleResult();
        for (Good good: consumer.getGoods()
        ) {
            System.out.println(good.getName() );
        }
    }

    public void getConsumersByGoodName(String name){
        Query query = session.createQuery("SELECT g FROM Good g WHERE g.name = :name", Good.class);
        query.setParameter("name", name);
        Good good = (Good) query.getSingleResult();
        for (Consumer consumer: good.getConsumers()
        ) {
            System.out.println(consumer.getName() );
        }
    }

    public void deleteConsumer(String name){
        Query query = session.createQuery("DELETE FROM Consumer c WHERE c.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    public void deleteGood(String name){
        Query query = session.createQuery("DELETE FROM Good g WHERE g.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    public void buy(Long consumerId, Long goodId){
        Good good = session.get(Good.class, goodId);
        Long currPrice = good.getCurrentPrice();
        Purchase purchase = new Purchase(currPrice, consumerId, goodId);
        session.save(purchase);
    }

}

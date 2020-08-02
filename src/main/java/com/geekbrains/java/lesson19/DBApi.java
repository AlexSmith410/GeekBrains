package com.geekbrains.java.lesson19;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import java.util.List;

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
        if (session != null)
            session.close();
    }

    public void commit(){
        session.getTransaction().commit();
    }

    public void commitAndClose(){
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }

    public void rollBack() {
        session.getTransaction().rollback();
    }

    public void raisingBet(Long userId, Long lotId, Long bet, String locker) {
        Query query = session.createQuery("SELECT l FROM Lot l WHERE l.id = :id", Lot.class);
        query.setParameter("id", lotId);
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        Lot lot = (Lot) query.getSingleResult();;
        User user = session.get(User.class, userId);
        lot.setCurrentBet(lot.getCurrentBet() + bet);
        lot.setlastOwner(user);
    }

    public void raisingBet(Long userId, Long lotId, Long bet) {
        Lot lot = session.get(Lot.class, lotId);;
        User user = session.get(User.class, userId);
        lot.setCurrentBet(lot.getCurrentBet() + bet);
        lot.setlastOwner(user);
    }

    public List<User> getUsers(){
        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<Lot> getLots(){
        return session.createQuery("SELECT l FROM Lot l", Lot.class).getResultList();
    }
}

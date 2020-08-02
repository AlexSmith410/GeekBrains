package com.geekbrains.java.lesson19;

import org.hibernate.SessionFactory;

import javax.persistence.OptimisticLockException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class OptimisticBlock extends Thread {
    private SessionFactory sessionFactory;
    private final User user;
    private final CountDownLatch cdl;

    public OptimisticBlock(String name, User user, CountDownLatch cdl, SessionFactory sessionFactory) {
        super(name);
        this.sessionFactory = sessionFactory;
        this.user = user;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        placeBet(100);
        cdl.countDown();
    }

    public void placeBet(long bet) {
        Random r = new Random();
        DBApi db = new DBApi(sessionFactory);
        db.startSession();
        try {
            for (int i = 0; i < 1000; i++) {
                Long userId = user.getId();
                Long lotId = (long) r.nextInt(4) + 1;
                db.raisingBet(userId, lotId, bet);
                Thread.sleep(1);
            }
            db.commit();
        } catch (OptimisticLockException e) {
            db.rollBack();
            placeBet(bet);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            db.endSession();
        }
    }
}

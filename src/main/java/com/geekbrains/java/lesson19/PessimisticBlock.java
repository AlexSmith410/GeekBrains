package com.geekbrains.java.lesson19;

import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class PessimisticBlock extends Thread {
    private final User user;
    private final CountDownLatch cdl;
    private final SessionFactory sessionFactory;

    public PessimisticBlock(String name, User user, CountDownLatch cdl, SessionFactory sessionFactory) {
        super(name);
        this.user = user;
        this.cdl = cdl;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        placeBet(100);
        cdl.countDown();
    }

    public void placeBet(long bet) {
        Random r = new Random();
        DBApi db = new DBApi(sessionFactory);
        try {
            for (int i = 0; i < 1000; i++) {
                db.startSession();
                Long userId = user.getId();
                Long lotId = (long) r.nextInt(4) + 1;
                db.raisingBet(userId, lotId, bet, "locked");
                db.commit();
                sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            db.endSession();
        }
    }
}

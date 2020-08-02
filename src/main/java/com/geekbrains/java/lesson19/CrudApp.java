package com.geekbrains.java.lesson19;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CrudApp {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(8);
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate19.cfg.xml")
                .buildSessionFactory();

        DBApi db = prepareData();
        runOptimistic(db, cdl, sessionFactory);
//        runPessimistic(db, cdl, sessionFactory);
        checkTotalSum(db);
        /*
        Время оптимистической: 12465
        Время пессимстической: 3083
        Сумма всех ставок совпадает
         */
    }

    public static DBApi prepareData() {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("hibernate19.cfg.xml")
                .buildSessionFactory();

        return new DBApi(factory);
    }

    public static void runOptimistic(DBApi db, CountDownLatch cdl, SessionFactory sessionFactory){
        db.startSession();
        List<User> userList = db.getUsers();
        db.commitAndClose();

        long time = System.currentTimeMillis();
        for (User user : userList) {
            new OptimisticBlock(user.getName(), user, cdl, sessionFactory).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Время оптимистической блокировки: " + (System.currentTimeMillis() - time));
    }

    public static void runPessimistic(DBApi db, CountDownLatch cdl, SessionFactory sessionFactory){
        db.startSession();
        List<User> userList = db.getUsers();
        db.commitAndClose();

        long time = System.currentTimeMillis();
        for (User user : userList) {
            new PessimisticBlock(user.getName(), user, cdl, sessionFactory).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Время пессимистической блокировки: " + (System.currentTimeMillis() - time));
    }

    public static void checkTotalSum(DBApi db){
        long sumOfBets = 0;
        db.startSession();
        List<Lot> lotList = db.getLots();
        for (Lot lot: lotList
        ) {
            sumOfBets += lot.getCurrentBet();
        }
        db.commitAndClose();
        System.out.println(sumOfBets == 800000);
    }
}

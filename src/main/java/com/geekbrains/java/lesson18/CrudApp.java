package com.geekbrains.java.lesson18;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CrudApp {
    public static void main(String[] args) throws IOException {
        DBApi db = prepareData();
        appInfo();
        parser(db);
    }

    public static void appInfo() {
        System.out.println("Доступные команды:");
        System.out.println("/showProductsByConsumer имя_покупателя");
        System.out.println("/showConsumersByProductTitle название_товара");
        System.out.println("/deleteConsumer (/deleteProduct) имя_элемента");
        System.out.println("/buy id_покупателя id_товара");
        System.out.println("Выход - /exit");
    }

    public static DBApi prepareData() {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        return new DBApi(factory);
    }

    public static void parser(DBApi db) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while(!input.equals("exit")) {
            db.startSession();
            input = reader.readLine();
            String[] commands = input.split(" ");
            switch (commands[0]) {
                case "/showProductsByConsumer":
                    db.getGoodsByConsumerName(commands[1]);
                    break;
                case "/showConsumersByProductTitle":
                    db.getConsumersByGoodName(commands[1]);
                    break;
                case "/deleteConsumer":
                    db.deleteConsumer(commands[1]);
                    break;
                case "/deleteProduct":
                    db.deleteGood(commands[1]);
                    break;
                case "/buy":
                    db.buy(Long.parseLong(commands[1]), Long.parseLong(commands[2]));
                    break;
                case "/exit":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Wrong command, try again");
                    break;
            }
            db.endSession();
        }
    }
}

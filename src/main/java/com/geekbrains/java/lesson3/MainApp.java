package com.geekbrains.java.lesson3;

public class MainApp {
    public static void main(String[] args) {
        Participant[] participants = new Participant[6];
        participants[0] = new Cat(100, 1000);
        participants[1] = new Human(200, 900);
        participants[2] = new Robot(50, 500);
        participants[3] = new Cat(150, 1500);
        participants[4] = new Human(150, 1700);
        participants[5] = new Robot(300, Integer.MAX_VALUE);

        Obstacle[] obstacles = new Obstacle[3];
        obstacles[0] = new Wall(100);
        obstacles[1] = new RunningTrack(1400);
        obstacles[2] = new Wall(170);

        for (int i = 0; i < participants.length; i++) {
            int obstacleCount = 0;
            for (int j = 0; j < obstacles.length; j++) {
                if (obstacles[j] instanceof Wall) {
                    Wall castedWall = (Wall) obstacles[j];
                    if (participants[i].jump(castedWall.getHeight()))
                        System.out.printf("Участник номер %d преодолел стену!\n", i);
                    else {
                        System.out.printf("Стена слишком высока, участник номер %d выбывает\n", i);
                        break;
                    }
                } else {
                    RunningTrack castedTrack = (RunningTrack) obstacles[j];
                    if (participants[i].run(castedTrack.getLength()))
                        System.out.printf("Участник номер %d преодолел беговую дорожку!\n", i);
                    else {
                        System.out.printf("Беговая дорожка слишком длинная, участник номер %d выбывает\n", i);
                        break;
                    }
                }
                obstacleCount++;
            }
            if (obstacleCount == obstacles.length)
                System.out.printf("Участник номер %d успешно преодолел все препятствия!", i);
        }
    }
}

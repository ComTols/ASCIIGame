import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TrainSpawner extends GameObject {

    private class Coroutine extends Thread {

        TrainSpawner spawner;

        Coroutine(TrainSpawner t) {
            spawner = t;
        }

        @Override
        public void run() {
            while (Program.RUN) {
                if(children.size() < Console.rows / 4 - 1) {
                    Train t;
                    if(new Random().nextBoolean()) {
                        t = new Train(new Vector2(-11,ThreadLocalRandom.current().nextInt(1, Console.rows-3)),true);
                    } else {
                        t = new Train(new Vector2(Console.columns,ThreadLocalRandom.current().nextInt(1, Console.rows-3)),false);
                    }
                    spawner.children.add(t);

                    MAX_TRAIN_SPEED++;
                    MAX_TRAIN_WAIT *= 0.95;
                    MAX_CAR_COUNT += 0.1;

                    if(MAX_TRAIN_WAIT < 500) MAX_TRAIN_WAIT=500;
                    if(MAX_CAR_COUNT > 9) MAX_CAR_COUNT = 9;
                    if(MAX_TRAIN_SPEED > 125) MAX_TRAIN_SPEED = 125;

                    try {
                        Thread.sleep(new Random().nextInt(MAX_TRAIN_WAIT));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    while (children.size() >= Console.rows / 4 - 1) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    public static int MAX_TRAIN_SPEED = 25;
    public static int MAX_TRAIN_WAIT = 10000;
    public static float MAX_CAR_COUNT = 3;

    TrainSpawner() {
        children = new ArrayList<>();
        Coroutine c = new Coroutine(this);
        c.start();
    }

    @Override
    public void Update() {
        for (int i = 0; i < children.size(); i++) {
            Train t = (Train) children.get(i);
            if(t.direction > 0) {
                int lastX = Console.columns;
                for (GameObject c: t.children) {
                    Car car = (Car) c;
                    lastX += car.length + 1;
                }
                if (lastX < t.position.x) {
                    children.remove(i);
                    i--;
                }
            } else {
                int lastX = -11;
                for (GameObject c: t.children) {
                    Car car = (Car) c;
                    lastX -= car.length - 1;
                }
                if (lastX > t.position.x) {
                    children.remove(i);
                    i--;
                }
            }
        }
    }
}

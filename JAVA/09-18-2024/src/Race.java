import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Race {
    public static AtomicLong startRaceTime = new AtomicLong();

    public static void startRace(List<Thread> cars) {
        new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println(i + "...");
                    Thread.sleep(500);
                }
                System.out.println("GO!!!");
                startRaceTime.set(System.currentTimeMillis());
                for (Thread car : cars) {
                    car.start();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        List<RaceCarRunnable> raceCars = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(3);

        raceCars.add(new RaceCarRunnable("Car1", 200, 1000, latch));
        raceCars.add(new RaceCarRunnable("Car2", 220, 1000, latch));
        raceCars.add(new RaceCarRunnable("Car3", 210, 1000, latch));

        List<Thread> carThreads = new ArrayList<>();
        for (RaceCarRunnable car : raceCars) {
            carThreads.add(new Thread(car));
        }

        startRace(carThreads);
        latch.await();

        RaceCarRunnable winner = raceCars.get(0);
        for (RaceCarRunnable car : raceCars) {
            if (car.getFinishTime() < winner.getFinishTime()) {
                winner = car;
            }
        }

        System.out.printf("The winner is %s with a finish time of %s!%n", winner.name, convertToTime(winner.getFinishTime()));
    }

    public static String convertToTime(long time) {
        return new java.text.SimpleDateFormat("mm:ss:SSS").format(new java.util.Date(time));
    }
}
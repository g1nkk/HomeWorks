import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceCarRunnable extends Car implements Runnable {
    private int passed = 0;
    private int distance;
    private boolean isFinish = false;
    private CountDownLatch latch;
    private long finishTime;

    public RaceCarRunnable(String name, int maxSpeed, int distance, CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.latch = latch;
    }

    private int getRandomSpeed() {
        int minSpeed = maxSpeed / 2;
        return new Random().nextInt(maxSpeed - minSpeed) + minSpeed;
    }

    @Override
    public void run() {
        while (!isFinish) {
            int speed = getRandomSpeed();
            passed += speed;
            System.out.printf("%s => speed: %d; progress: %d/%d%n", name, speed, passed, distance);

            if (passed >= distance) {
                isFinish = true;
                finishTime = System.currentTimeMillis() - Race.startRaceTime.get();
                latch.countDown();
                System.out.printf("%s FINISHED in %d ms!%n", name, finishTime);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getFinishTime() {
        return finishTime;
    }
}
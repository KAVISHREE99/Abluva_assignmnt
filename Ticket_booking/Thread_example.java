import java.util.*;
import java.util.concurrent.Semaphore;

class MultiThread {

    public static void main(String[] args) {

        int num_permit = 5;
        Semaphore_new semaphore = new Semaphore_new(num_permit);

        Thread_example t = new Thread_example(semaphore);

        for (int i = 0; i < 5; i++) {
            call_thread(t);
        }
    }

    public static void call_thread(Runnable run) {
        Thread t = new Thread(run);
        t.start();
    }

}

class Semaphore_new {
    private int permit;

    public Semaphore_new(int permit) {
        this.permit = permit;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permit <= 0) {
            wait();
        }
        permit--;
    }

    public synchronized void release() {
        permit++;
        notify();
    }
}

public class Thread_example implements Runnable {
    static int total = 90;
    private Semaphore_new semaphore;

    public Thread_example(Semaphore_new semaphore) {
        this.semaphore = semaphore;
    }

    public synchronized void run() {
        try {
            semaphore.acquire();
            total = bookTickets(1);
            total = withDraw(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        System.out.println("Tickets avail: " + total + " after " + Thread.currentThread().getName());
    }

    public static int bookTickets(int n) {

        if (total > n) {
            System.out.println("book tickets!! Done by " + Thread.currentThread().getName() + "\n");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tickets booked! by " + Thread.currentThread().getName() + "\n");
            total = total - n;
            System.out.println("Tickets avail:" + total + " after " + Thread.currentThread().getName() + "\n");
        } else {
            System.out.println("Sorry,Limited tickets only available!!" + "\n");
        }
        return total;
    }

    public static int withDraw(int m) {
        System.out.println("Withdrawal!! by " + Thread.currentThread().getName() + "\n");
        try {
            Thread.sleep(800);
            total = total + m;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tickets avail:" + total + " after " + Thread.currentThread().getName() + "\n");
        return total;
    }

}

import java.util.*;

class MultiThread {

    public static void main(String[] args) {

        Thread_example t = new Thread_example();

        for (int i = 0; i < 5; i++) {
            call_thread(t);
        }
    }

    public static void call_thread(Runnable run) {
        Thread t = new Thread(run);
        t.start();
    }

}

public class Thread_example implements Runnable {
    static int total = 90;

    public synchronized void run() {

        total = bookTickets(1);
        // System.out.println("Tickets avail:" + total + "" +
        // Thread.currentThread().getName());
        total = withDraw(2);
        System.out.println("Tickets avail: " + total + " after " + Thread.currentThread().getName());
    }

    public static int bookTickets(int n) {

        if (total > n) {
            System.out.println("book tickets!! Done by " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tickets booked! by " + Thread.currentThread().getName());
            total = total - n;
            System.out.println("Tickets avail:" + total + " after " + Thread.currentThread().getName());
        } else {
            System.out.println("Sorry,Limited tickets only available!!");
        }
        return total;
    }

    public static int withDraw(int m) {
        System.out.println("Withdrawal!! by " + Thread.currentThread().getName());
        try {
            Thread.sleep(800);
            total = total + m;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tickets avail:" + total + " after " + Thread.currentThread().getName());
        return total;
    }

}

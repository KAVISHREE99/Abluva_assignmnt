import java.util.*;

class Msgpass2 {
    private static final int MAX_QUE_SIZ = 10;
    private final int max_cust = 20;
    private final Queue<String> qu = new LinkedList<>();

    public synchronized void prod_msg(String str, String type, int count) {
        try {
            while (qu.size() == MAX_QUE_SIZ) {
                wait();
            }
            switch (type.toLowerCase()) {
                case "broadcast":
                    broadcastMessage(str);
                    break;
                case "atleast":
                    if (count > max_cust) {
                        System.out.println("Not enough consumers to meet the requirement of at least " + count);
                    } else {
                        sendMessageAtLeastN(str, count);
                    }
                    break;
                case "atmost":
                    if (count < 1) {
                        System.out.println("Number of consumers must be at least 1");
                    } else {
                        sendMessageAtMostN(str, count);
                    }
                    break;
                default:
                    System.out.println("Unknown condition");

            }
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void broadcastMessage(String str) {
        for (int i = 0; i < max_cust; i++) {
            qu.add(str);
        }
        System.out.println("Broadcasted msg : " + str);
    }

    private void sendMessageAtLeastN(String str, int count) {
        for (int i = 0; i < count; i++) {
            qu.add(str);
        }
        System.out.println("Msg sent to " + count + " consumers: " + str);
    }

    private void sendMessageAtMostN(String str, int count) {
        for (int i = 0; i < Math.min(count, max_cust); i++) {
            qu.add(str);
        }
        System.out.println("At most " + count + "customers received: " + str);
    }

    public synchronized void consume_msg() {
        try {
            while (qu.isEmpty()) {
                wait();
            }
            String message = qu.poll();
            System.out.println(Thread.currentThread().getName() + " received message: " + message);
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Msgpass2 msg = new Msgpass2();
        String str = "This is message from producer";
        String type = sc.next();
        int count = sc.nextInt();

        Runnable producerTask = new Runnable() {
            public void run() {
                try {
                    msg.prod_msg(str, type, count);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    // e.printStackTrace();
                }
            }
        };
        Runnable consumerTask = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        msg.consume_msg();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        };

        Thread producer = new Thread(producerTask);
        producer.start();
        for (int i = 0; i < 5; i++) {
            Thread consumer = new Thread(consumerTask);
            consumer.start();
        }

    }
}

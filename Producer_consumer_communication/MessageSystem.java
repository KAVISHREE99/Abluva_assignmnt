//SOLUTION 2
// Num of subscribers given as 5 in the program itself

import java.util.ArrayList;
import java.util.List;

public class MessageSystem {
    private final List<Subscriber> consumers = new ArrayList<>();

    public MessageSystem(int numberOfSubscribers) {
        for (int i = 0; i < numberOfSubscribers; i++) {
            consumers.add(new Subscriber("Subscriber-" + (i + 1)));
        }
    }

    public synchronized void broadcastMessage(String message) {
        for (Subscriber subscriber : consumers) {
            subscriber.receiveMessage(message);
        }
    }

    public synchronized void sendMessageAtLeastN(String message, int n) {
        int activeCount = 0;
        for (Subscriber subscriber : consumers) {
            if (subscriber.isActive()) {
                activeCount++;
            }
        }
        if (activeCount >= n) {
            System.out.println("=====================AT LEAST OF N====================");
            broadcastMessage(message);
        } else {
            System.out.println("=====================AT LEAST OF N====================");
            System.out.println("Cannot send message: Not enough active subscribers (" + activeCount + ")");
        }
    }

    public synchronized void sendMessageNeverMoreThanN(String message, int n) {
        int activeCount = 0;
        for (Subscriber subscriber : consumers) {
            if (subscriber.isActive()) {
                activeCount++;
            }
        }
        if (activeCount <= n) {
            System.out.println("=====================AT MOST OF N====================");
            broadcastMessage(message); // as i have given the number of subscribers as 2 and the active subscribers
                                       // currently are 0 so everyone recieve the message
        } else {
            System.out.println("=====================AT MOST OF N====================");
            System.out.println("Cannot send message: More than " + n + " active subscribers");
        }
    }

    public static void main(String[] args) {
        int numberOfSubscribers = 5;
        MessageSystem messageSystem = new MessageSystem(numberOfSubscribers);

        for (Subscriber subscriber : messageSystem.consumers) {
            new Thread(subscriber).start();
        }

        try {
            Thread.sleep(2000); // Wait to ensure some subscribers become inactive
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate sending messages
        messageSystem.broadcastMessage("Hello everyone!");
        messageSystem.sendMessageAtLeastN("Message requiring at least 3 active subscribers", 3);
        messageSystem.sendMessageNeverMoreThanN("Message requiring never more than 2 active subscribers", 2);
    }
}

class Subscriber implements Runnable {
    private final String name;
    private boolean active = true;

    public Subscriber(String name) {
        this.name = name;
    }

    public void receiveMessage(String message) {
        System.out.println(name + " received message: " + message);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            setActive(false);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

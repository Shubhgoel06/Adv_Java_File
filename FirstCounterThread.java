import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 1. Thread Synchronization Example
class Counter {
    private int count = 0;

    // Synchronized method to ensure thread safety
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SyncThread extends Thread {
    private Counter counter;

    public SyncThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) { // Changed from 1000 to 500
            counter.increment();
        }
    }
}

// 2. Inter-Thread Communication Example
class SharedResource {
    private int value = 0;
    private boolean available = false;

    public synchronized void produce(int newValue) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        value = newValue;
        available = true;
        System.out.println("Produced: " + value);
        notify();
    }

    public synchronized void consume() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed: " + value);
        available = false;
        notify();
    }
}

class Producer extends Thread {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) { // Changed from 5 to 3
            resource.produce(i);
        }
    }
}

class Consumer extends Thread {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) { // Changed from 5 to 3
            resource.consume();
        }
    }
}

// 3. Thread Pooling Example
class Task implements Runnable {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Executing task: " + taskName + " - " + Thread.currentThread().getName());
    }
}

public class FirstCounterThread {
    public static void main(String[] args) {
        // Thread Synchronization
        Counter counter = new Counter();
        SyncThread thread1 = new SyncThread(counter);
        SyncThread thread2 = new SyncThread(counter);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Counter Value: " + counter.getCount());

        // Inter-Thread Communication
        SharedResource resource = new SharedResource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Thread Pooling
        ExecutorService executorService = Executors.newFixedThreadPool(2); // Changed to 2 threads
        for (int i = 1; i <= 5; i++) {
            executorService.execute(new Task("Task-" + i));
        }
        executorService.shutdown();
        try {
            // Wait for all tasks to complete
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // Final message with your name
        System.out.println("Brahmjot Singh AIML 03913211621");
    }
}

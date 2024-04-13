package org.example;

public class PingPong {
    private static final int MAX_ITERATIONS = 10;
    private static final Object lock = new Object();
    private static boolean isPingTurn = true;
    private static int iteration = 0;

    public static void main(String[] args) {
        Thread pingThread = new Thread(PingPong::ping);
        Thread pongThread = new Thread(PingPong::pong);

        pingThread.start();
        pongThread.start();
    }

    private static void ping() {
        while (iteration < MAX_ITERATIONS) {
            synchronized (lock) {
                if (isPingTurn) {
                    System.out.println("PING");
                    isPingTurn = false;
                    iteration++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void pong() {
        while (iteration < MAX_ITERATIONS) {
            synchronized (lock) {
                if (!isPingTurn) {
                    System.out.println("PONG");
                    isPingTurn = true;
                    iteration++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

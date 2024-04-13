package org.example;

import java.util.concurrent.Semaphore;

public class PingPong {
    private static final int MAX_ITERATIONS = 10;
    private static final Semaphore pingSemaphore = new Semaphore(1);
    private static final Semaphore pongSemaphore = new Semaphore(0);

    public static void main(String[] args) {
        Thread pingThread = new Thread(PingPong::ping);
        Thread pongThread = new Thread(PingPong::pong);

        pingThread.start();
        pongThread.start();
    }

    private static void ping() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            try {
                pingSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("PING");
            pongSemaphore.release();
        }
    }

    private static void pong() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            try {
                pongSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("PONG");
            pingSemaphore.release();
        }
    }
}

package org.example;

public class PingPong {
    private static volatile int count = 0;
    private static final int MAX_ITERATIONS = 1000;

    public static void main(String[] args) {
        Thread pingThread = new Thread(PingPong::ping);
        Thread pongThread = new Thread(PingPong::pong);

        pingThread.start();
        pongThread.start();
    }

    private static void ping() {
        while (count < MAX_ITERATIONS) {
            if (count % 2 == 0) {
                System.out.println("PING");
                count++;
            }
        }
    }

    private static void pong() {
        while (count < MAX_ITERATIONS) {
            if (count % 2 != 0) {
                System.out.println("PONG");
                count++;
            }
        }
    }
}
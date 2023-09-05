/*
 * This code is sample code, provided AS-IS, and we make NO
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.thread;

// extend the Thread class
public class MessagePrinter extends Thread {
    private static final String threadNamePrefix = "message-printer-";
    private static int id = 0;

    private String message;
    private int count;
    private long interval;

    public MessagePrinter(String message) {
        this.message = message;
        setName("Message Printer");     // set the thread name [important when debugging]
    }

    public MessagePrinter(String message, int count, long interval) {
        this.message = message;
        this.count = count;
        this.interval = interval;
        setName(threadNamePrefix + ++id);
    }

    /**
     * It should loop 10 times, printing the 'message' field to stdout,
     * then pausing for some interval (in millis) that you choose.
     * <p>
     * Use a try/catch to call Thread.sleep(interval) for the pause.
     * The sleep() method throws InterruptedException, which you need to catch.
     * You can either leave the catch block empty, or print the exception to stdout.
     */
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(getName() + ": " + message);

            try {
                Thread.sleep(interval);     // pause for interval time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
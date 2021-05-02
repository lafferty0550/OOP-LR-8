package com.company;

import java.util.LinkedList;

public class URLPool {

    private LinkedList<URLDepthPair> watchedList;

    private LinkedList<URLDepthPair> notWatchedList;

    private LinkedList<URLDepthPair> blockedList;

    public int waitingThreads;

    private int depth;


    public URLPool(int depth) {
        waitingThreads = 0;
        watchedList = new LinkedList<URLDepthPair>();
        notWatchedList = new LinkedList<URLDepthPair>();
        blockedList = new LinkedList<URLDepthPair>();
        this.depth = depth;
    }

    public synchronized int getWaitThread() {
        return waitingThreads;
    }

    public synchronized boolean put(URLDepthPair depthPair) {
        boolean added = false;

        if (depthPair.getDepth() < this.depth) {
            notWatchedList.addLast(depthPair);
            added = true;

            if (waitingThreads > 0) waitingThreads--;
            this.notify();
        } else {
            blockedList.add(depthPair);
        }

        return added;
    }

    public synchronized URLDepthPair get() {

        URLDepthPair myDepthPair = null;

        if (notWatchedList.size() == 0) {
            waitingThreads++;
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }

        myDepthPair = notWatchedList.removeFirst();
        watchedList.add(myDepthPair);

        return myDepthPair;
    }


    public LinkedList<URLDepthPair> getWatchedList() {
        return this.watchedList;
    }

    public LinkedList<URLDepthPair> getNotWatchedList() {
        return this.notWatchedList;
    }

    public LinkedList<URLDepthPair> getBlockedList() {
        return this.blockedList;
    }

}
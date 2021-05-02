package com.company;

import java.util.*;

public class CrawlerTask implements Runnable {

    private URLDepthPair element;

    private URLPool myPool;

    public CrawlerTask(URLPool pool) {
        this.myPool = pool;
    }

    @Override
    public void run() {
        element = myPool.get();
        LinkedList<URLDepthPair> linksList;
        linksList = Crawler.parsePage(element);

        if (linksList != null && !linksList.isEmpty()) {
            Crawler.showResults(element, linksList);
            for (URLDepthPair pair : linksList) {
                myPool.put(pair);
            }
        }
    }
}
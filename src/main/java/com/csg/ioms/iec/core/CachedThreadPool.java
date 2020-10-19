package com.csg.ioms.iec.core;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
public final class CachedThreadPool {

    private  static CachedThreadPool cachedThreadPool  = new CachedThreadPool();
    private ExecutorService executorService;


    private CachedThreadPool() {
        executorService = Executors.newCachedThreadPool();
    }


    public static CachedThreadPool getCachedThreadPool() {
        return  cachedThreadPool;
    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

}

package com.eldarst.resourcepool.calculation;


import com.eldarst.resourcepool.resources.FileFactory;
import com.eldarst.resourcepool.resources.ResourcesPool;
import com.eldarst.resourcepool.resources.ThreadFactory;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        int poolSize = Runtime.getRuntime().availableProcessors();
        int maxWaitingTime = 1000;
        double[] a = new double[10000];
        double[] b = new double[10000];
        double[] c = new double[10000];

        for (int i = 0; i < 10000; i++) {
            a[i] = Math.random() * (10000);
            b[i] = Math.random() * (10000);
            c[i] = Math.random() * (10000);
        }

        ResourcesPool<File> filePool = new ResourcesPool<>(new FileFactory(), maxWaitingTime, poolSize);
        ResourcesPool<Thread> threadPool = new ResourcesPool<>(new ThreadFactory(), maxWaitingTime, poolSize);

        for (int i = 0; i < poolSize; i++) {
            Solver solverThread = new Solver(a, b, c, threadPool, filePool);
            solverThread.execute(solverThread);
        }
    }
}

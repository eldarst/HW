package com.eldarst.resourcepool.calculation;


import com.eldarst.resourcepool.resources.ResourcesPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;

public class Solver implements Runnable {
    double[] a, b, c;
    ResourcesPool<File> filePool;
    ResourcesPool<Thread> threadPool;

    public Solver(double[] a, double[] b, double[] c, ResourcesPool<Thread> threadPool, ResourcesPool<File> filePool) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.filePool = filePool;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        File file = null;
        try {
            file = filePool.getResource();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(file,true)) {

            for (int i = 0; i < a.length; i++) {
                String roots = getRoots(a[i], b[i], c[i]);
                writer.write(roots);
            }
            writer.close();
            filePool.releaseResource(file);
        } catch (IOException e) {
            filePool.releaseResource(file);
            System.out.println(e.getMessage());
        }
    }

    public void execute(Runnable runnable) throws InterruptedException {
        Thread worker = threadPool.getResource();
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.releaseResource(worker);
        }
    }


    public static String getRoots(double a, double b, double c) {
        double discriminant = pow(b, 2) - 4 * a * c;
        if (a == 0) {
            double x1 = -c / b;
            return String.format("%f" + '\n', x1);
        }

        double sqrt_val = sqrt(abs(discriminant));

        if (discriminant > 0) {
            double x1 = (-b + sqrt_val) / (2 * a);
            double x2 = (-b - sqrt_val) / (2 * a);
            return String.format("%f, %f" + '\n', x1, x2);
        } else if (discriminant == 0) {
            double x1 = -(double) b / (2 * a);
            return String.format("%f" + '\n', x1);
        } else {
            return "D < 0" + '\n';
        }
    }
}

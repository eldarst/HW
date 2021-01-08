package com.eldarst.resourcepool.resources;

import com.eldarst.resourcepool.calculation.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static java.lang.StrictMath.sqrt;

public class ResourcePoolTest {
    @Test
    public void testGetRoots() {
        double a = 1, b = 4, c = 1;
        double discriminant = sqrt(b * b - 4 * a * c);
        double x1 = (-b + discriminant) / (2 * a);
        double x2 = (-b - discriminant) / (2 * a);
        Assertions.assertEquals(String.format("%f, %f", x1, x2), Solver.getRoots(a, b, c));
    }
    @Test
    void testPoolInitialization() throws IOException {
        ResourcesPool<Thread> threadPool = new ResourcesPool<>(new ThreadFactory(), 1000, Runtime.getRuntime().availableProcessors());
        Assertions.assertEquals(Runtime.getRuntime().availableProcessors(), threadPool.getPoolSize());
        int size = 10;
        ResourcesPool<File> filePool = new ResourcesPool<>(new FileFactory(), 1000, size);
        Assertions.assertEquals(10, filePool.getPoolSize());
    }

    @Test
    void testReleaseResource() {
        ResourcesPool<Thread> threadPool = Mockito.mock(ResourcesPool.class);
        Thread thread = new Thread();
        threadPool.releaseResource(thread);
        Mockito.verify(threadPool, Mockito.times(1)).releaseResource(thread);
    }

    @Test
    void testShutdown() throws InterruptedException {
        ResourcesPool<Thread> threadPool = Mockito.mock(ResourcesPool.class);
        threadPool.shutdown();
        Mockito.verify(threadPool, Mockito.times(1)).shutdown();
    }

    @Test
    void testExceptionThrowGetResourceForTerminatedPool() throws IOException, InterruptedException {
        ResourcesPool<Thread> threadPool = new ResourcesPool<>(new ThreadFactory(), 1000, Runtime.getRuntime().availableProcessors());
        threadPool.shutdown();
        Assertions.assertThrows(IllegalStateException.class, () -> threadPool.getResource());
    }

    @Test
    void testExceptionThrowReleaseResourceForTerminatedPool() throws InterruptedException, IOException {
        ResourcesPool<Thread> threadPool = new ResourcesPool<>(new ThreadFactory(), 1000, Runtime.getRuntime().availableProcessors());
        Thread worker = threadPool.getResource();
        threadPool.shutdown();
        Assertions.assertThrows(IllegalStateException.class, () -> threadPool.releaseResource(worker));
    }


}

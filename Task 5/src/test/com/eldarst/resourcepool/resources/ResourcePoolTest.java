package com.eldarst.resourcepool.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

public class ResourcePoolTest {
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
        ResourcesPool<Thread> pool = Mockito.mock(ResourcesPool.class);
        Thread thread = new Thread();
        pool.releaseResource(thread);
        Mockito.verify(pool, Mockito.times(1)).releaseResource(thread);
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

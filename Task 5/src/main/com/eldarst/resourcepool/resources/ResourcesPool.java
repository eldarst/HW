package com.eldarst.resourcepool.resources;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ResourcesPool<T> {
    private LinkedBlockingQueue<Resource<T>> resourcePool;
    private LinkedBlockingQueue<Resource<T>> workingResources;

    private int poolSize;
    private boolean isTerminated;
    private ResourceFactory<T> resourceFactory;
    private ExecutorService executor;
    long lifeTime;

    public ResourcesPool(ResourceFactory<T> resourceFactory, long lifeTime) throws IOException {
        this(resourceFactory, lifeTime, Runtime.getRuntime().availableProcessors());
    }

    public ResourcesPool(ResourceFactory<T> resourceFactory, long lifeTime, int poolSize) throws IOException {
        this.resourceFactory = resourceFactory;
        this.lifeTime = lifeTime;
        this.isTerminated = false;
        this.resourcePool = new LinkedBlockingQueue<>();
        this.workingResources = new LinkedBlockingQueue<>();
        this.executor = Executors.newWorkStealingPool();
        this.poolSize = poolSize;
        setPool();
    }

    public int getPoolSize() {
        if(this.isTerminated) {
            throw new IllegalStateException("Pool is already terminated!");
        }
        return poolSize;
    }

    public T getResource() throws InterruptedException {
        if (!this.isTerminated) {
            try {
                Resource<T> resource = this.resourcePool.take();
                if (resource.alive(this.lifeTime)) {
                    this.workingResources.offer(resource);
                    return resource.getValue();
                } else {
                    long now = System.currentTimeMillis();
                    Resource<T> newResource = new Resource<>(now, this.resourceFactory.create());
                    this.workingResources.offer(newResource);
                }
            } catch (InterruptedException | IOException ex) {
                throw new InterruptedException();
            }
        }
        else {
            throw new IllegalStateException("Pool is already terminated");
        }
        return null;
    }

    public void releaseResource(T value) {
        if ( !this.isTerminated) {
            this.workingResources.remove(value);
            long now = System.currentTimeMillis();
            this.resourcePool.offer(new Resource<>(now, value));
        } else {
            if(value == null) {
                throw new IllegalArgumentException("Value is NULL");
            }
            throw new IllegalStateException("Pool is already terminated");
        }
    }

    public void shutdown() throws InterruptedException {
        this.isTerminated = true;
        stop(executor);
    }

    private boolean setPool() throws IOException {

        for (int i = 0; i < this.poolSize; ++i) {
            if (!this.isTerminated) {
                this.resourcePool.offer(new Resource<>(System.currentTimeMillis(), this.resourceFactory.create()));
            } else {
                return false;
            }
        }
        return true;
    }

    private void stop(ExecutorService executor) throws InterruptedException {
        try {
            executor.shutdown();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            throw new InterruptedException();
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Not all processes finished!");
            }
            executor.shutdownNow();
        }
    }
}

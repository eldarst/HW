package com.eldarst.resourcepool.resources;

public class ThreadFactory implements ResourceFactory<Thread> {
    @Override
    public Thread create() {
        Thread thread = new Thread();
        return thread;
    }
}

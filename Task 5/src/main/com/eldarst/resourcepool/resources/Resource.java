package com.eldarst.resourcepool.resources;

public class Resource<K> {
    private K source;
    private long lifeTime;

    Resource(long time, K val) {
        this.lifeTime = time;
        this.source = val;
    }

    public K getValue() {
        return this.source;
    }

    public boolean alive(long lifeTime) {
        long currentTime = System.currentTimeMillis();
        return currentTime - this.lifeTime < lifeTime;
    }
}

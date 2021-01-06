package com.eldarst.resourcepool.resources;

import java.io.IOException;

public interface ResourceFactory<T>  {
    public T create() throws IOException;
}

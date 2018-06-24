package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    private LRUCache lruCache;
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
        lruCache = new LRUCache(10);
    }

    @Override
    public Object retrieve(long id) {
        if(lruCache.find(id)==null){
            Object o = originalRetriever.retrieve(id);
            lruCache.set(id, o);
            return o;
        }else {
            System.out.println("Getting a value for id #" + id + " from Cache...");
            return lruCache.find(id);
        }
    }
}

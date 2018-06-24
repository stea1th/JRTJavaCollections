package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private int size;
    private long maxBucketSize = Long.MAX_VALUE;


    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public FileStorageStrategy() {
        for (int i = 0; i <table.length ; i++) {
            table[i] = new FileBucket();
        }
    }

    public int hash(Long k){
        return k.hashCode();

    }

    public int indexFor(int hash, int length){
        return hash & (length-1);
    }

    public Entry getEntry(Long key){
        return null;

    }

    public void resize(int newCapacity){


    }

    public void transfer(FileBucket[] newTable){
        int newCapacity = newTable.length;
        for(FileBucket bucket : table){

        }

    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        for(FileBucket bucket : table){
            if((bucket.getFileSize()>bucketSizeLimit)&&(null != table[bucketIndex])){
                resize(2 * table.length);
                hash = (null!=key)? hash(key) : 0;
                bucketIndex = indexFor(hash, table.length);
            }
        }
        createEntry(hash, key, value, bucketIndex);

    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {

    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }
}

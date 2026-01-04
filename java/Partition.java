package java;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class Partition implements StorageEngine {
    private Integer id;
    private String name;
    private ArrayList<Integer> data;
    private AtomicInteger next_offset;
    private ArrayList<Message> msgs;
    private final ReadWriteLock rwLock
        = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Integer> getData() {
        return data;
    }
    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }
    public Partition(Integer id, String name){
        this.id=id;
        this.name=name;
        this.data=new ArrayList<Integer>();
        this.msgs=new ArrayList<Message>();
        this.next_offset= new AtomicInteger();
        this.next_offset.set(-1);
    }
    public void AddData(Integer data){
        this.data.add(data);
    }
    public Integer Publish(byte[] payload){
        writeLock.lock();
        this.next_offset.addAndGet(1);
        
        Message m=new Message(payload,next_offset,1);

        
        this.msgs.add(m);
        writeLock.unlock();
        return 1;

    }
    @Override
    public void append(long offset, Message msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'append'");
    }
    @Override
    public Message read(long offset) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

}

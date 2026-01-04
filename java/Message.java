package java;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private byte[] payload;
    private AtomicInteger offset;
    private int timestamp;
    public Message(byte[] payload, AtomicInteger offset, int timestamp){
        this.payload=payload;
        this.offset=offset;
        this.timestamp=timestamp;
    }
}

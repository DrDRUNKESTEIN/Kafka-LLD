package java;

public class Message{
    private byte[] message;
    private Integer offset=-1;
    private long timestamp;
    public Message(byte[] message, Integer offset, long timestamp){
        this.message=message;
        this.offset=offset;
        this.timestamp=timestamp;
    }
    public Integer getOffset() {
        return offset;
    }
    public byte[] getMessageBytes() {
        return this.message;
    }
}
package java;

import java.util.ArrayList;

public class Partition{
    private Integer partition_id;
    private final ArrayList<Message> data=new ArrayList<Message>();
    private Topic topic;
    public Partition(Integer partition_id, Topic topic){
        this.partition_id=partition_id;
        this.topic=topic;
    }
    public synchronized void add_to_partition(Message message){
        this.data.add(message);
        
    }
    public Integer get_offset_in_partition(){
        return this.data.size();
    }

    /**
     * Return the message bytes at the given offset (0-based). Returns null if offset out of range.
     */
    public synchronized byte[] getMessageAtOffset(Integer offset) {
        if (offset == null) return null;
        if (offset < 0 || offset >= this.data.size()) return null;
        Message m = this.data.get(offset);
        if (m == null) return null;
        return m.getMessageBytes();
    }
}
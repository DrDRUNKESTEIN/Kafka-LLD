package src;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePartition extends Partition {
    private Integer queueLength;
    private Queue<Integer> queue;
    // simple monotonically increasing offset for this partition
    private long nextOffset = 0L;
    public Integer getQueueLength() {
        return queueLength;
    }
    public QueuePartition(Integer id, String name) {
        this.setId(id);
        this.setName(name);
        this.queueLength = 0;
        this.queue = new LinkedList<>();
    }
    public Queue<Integer> getQueue() {
        return queue;
    }
    public void setQueue(Queue<Integer> queue) {
        this.queue = queue;
    }
    public void setQueueLength(Integer queueLength) {
        this.queueLength = queueLength;
    }
    public void enqueue(Integer item){
        this.queue.add(item);
        this.queueLength += 1;
        this.nextOffset += 1;
    }
    private Integer dequeue(){
        if(this.queueLength == 0){
            return null;
        }
        this.queueLength -= 1;
        return this.queue.poll();
    }

    @Override
    public Integer GetValue() {
        return this.dequeue();
    }
    public Integer AddValue(Integer value) {
        this.enqueue(value);
        // Return the offset (use Integer if small, but offset is long; cast to Integer if safe)
        // We'll return the offset as Integer if within Integer range, else return -1 to signal overflow.
        if (this.nextOffset <= Integer.MAX_VALUE) {
            return (int)(this.nextOffset - 1);
        } else {
            return -1;
        }
    } 

    public long getNextOffset() {
        return nextOffset;
    }
}

package src;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class QueuePartition extends Partition {
    private Queue<Integer> queue;
    // simple monotonically increasing offset for this partition
    private AtomicLong nextOffset = new AtomicLong(0L);
    public Integer getQueueLength() {
        return this.queue.size();
    }
    public QueuePartition(Integer id, String name) {
        this.setId(id);
        this.setName(name);
        this.queue = new ConcurrentLinkedQueue<>();
    }
    public Queue<Integer> getQueue() {
        return queue;
    }
    public void setQueue(Queue<Integer> queue) {
        this.queue = queue;
    }
    public void setQueueLength(Integer queueLength) {
        // not used with concurrent queue
    }
    public void enqueue(Integer item){
        this.queue.add(item);
        this.nextOffset.incrementAndGet();
    }
    private Integer dequeue(){
        return this.queue.poll();
    }

    @Override
    public Integer GetValue() {
        return this.dequeue();
    }
    public Integer AddValue(Integer value) {
        this.enqueue(value);
        long assigned = this.nextOffset.get() - 1;
        if (assigned <= Integer.MAX_VALUE && assigned >= Integer.MIN_VALUE) {
            return (int) assigned;
        } else {
            return -1;
        }
    } 

    public long getNextOffset() {
        return nextOffset.get();
    }
}

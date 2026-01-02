package src;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePartition extends Partition {
    private Integer queueLength;
    private Queue<Integer> queue;
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
    public void AddValue(Integer value) {
        this.enqueue(value);
    } 
}

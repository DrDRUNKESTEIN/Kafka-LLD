package java;

import java.util.ArrayList;

public class Topic{
    private Integer topic_id;
    private String topic_name;
    private Integer last_used_partition=-1;
    private final ArrayList<Partition> topic_partitions= new ArrayList<Partition>(5);
    public Topic(Integer topic_id, String topic_name){
        this.topic_id=topic_id;
        this.topic_name=topic_name;
        for (int i = 0; i < 5; i++) {
            this.topic_partitions.add(new Partition(i, this));
        }
    }
    public Integer getTopicId() {
        return topic_id;
    }

    public String getTopicName() {
        return topic_name;
    }
    public void setLast_used_partition(Integer last_used_partition) {
        this.last_used_partition = last_used_partition;
    }
    public Integer getLast_used_partition() {
        return last_used_partition;
    }
    public Integer publish_to_partition(Message message, Integer partition_number){
        Partition current_partition=this.topic_partitions.get(partition_number);
        current_partition.add_to_partition(message);
        Integer message_offset=message.getOffset();
        return message_offset;
    }
    public Partition get_partiton_by_index(Integer index){
        return this.topic_partitions.get(index);
    }

    public ArrayList<Partition> getPartitions() {
        return this.topic_partitions;
    }
}
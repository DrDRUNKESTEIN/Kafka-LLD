package src;

public class Topic {
    private Integer id;
    private String name;
    private QueuePartition partition;
    
    public Topic(Integer id, String name, QueuePartition partition){
        this.id = id;
        this.name = name;
        this.partition = partition;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public QueuePartition getPartition() {
        return partition;
    }
    public void setPartition(QueuePartition partition) {
        this.partition = partition;
    }

}

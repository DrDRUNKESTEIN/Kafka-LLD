package java;

import java.util.ArrayList;
public class Topic {
    private Integer id;
    private String name;
    private ArrayList<Partition> partitions;
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
    public ArrayList<Partition> getPartitions() {
        return partitions;
    }
    public void setPartitions(ArrayList<Partition> partitions) {
        this.partitions = partitions;
    }
    public void AddPartition(Partition p){
        this.partitions.add(p);
    }
    public void RemovePartition(Partition p){
        this.partitions.remove(p);
    }
    public Topic(Integer id,String name){
        this.id=id;
        this.name=name;
        this.partitions=new ArrayList<Partition>();
    }
    
}

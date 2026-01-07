package java;

public class Publisher {
    private Integer publisher_id;
    public Broker broker;
    public Publisher(Integer publisher_id, Broker broker){
        this.publisher_id=publisher_id;
        this.broker=broker;
    }
    public Integer publish(byte[] text, Topic topic){
        return this.broker.publish_message(topic, text);
    }
}

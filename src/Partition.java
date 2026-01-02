package src;
abstract public class Partition {
    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public abstract Integer GetValue();
    public abstract void AddValue(Integer value);
}

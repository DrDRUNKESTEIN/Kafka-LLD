package java;
public interface StorageEngine {
    void append(long offset, Message msg);
    Message read(long offset);
}
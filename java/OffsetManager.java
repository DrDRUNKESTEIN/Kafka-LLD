package java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OffsetManager implements Storage {
    private final Integer manager_id;
    private final java.util.concurrent.ConcurrentMap<ConsumerTopicPartitionKey, Long> offsetCommitted = new java.util.concurrent.ConcurrentHashMap<>();

    // simple persistent file (written in working directory)
    private static final String DEFAULT_PERSIST_FILE = "offsets.txt";
    private final Path persistFile;

    public OffsetManager(Integer manager_id) {
        this.manager_id = manager_id;
    this.persistFile = Paths.get(DEFAULT_PERSIST_FILE);
        loadFromStorage();
    }

    
    public Long getCommittedOffsetForConsumer(Topic topic, Consumer consumer, Integer partitionIndex) {
        ConsumerTopicPartitionKey key = new ConsumerTopicPartitionKey(topic.getTopicId(), consumer.getConsumerId(), partitionIndex);
        return this.offsetCommitted.get(key);
    }

    
    public void setCommittedOffsetForConsumer(Topic topic, Consumer consumer, Integer partitionIndex, Long offset) {
        ConsumerTopicPartitionKey key = new ConsumerTopicPartitionKey(topic.getTopicId(), consumer.getConsumerId(), partitionIndex);
        this.offsetCommitted.put(key, offset);
    }

    
    public boolean commitOffsetIfGreater(Topic topic, Consumer consumer, Integer partitionIndex, Long offset) {
        ConsumerTopicPartitionKey key = new ConsumerTopicPartitionKey(topic.getTopicId(), consumer.getConsumerId(), partitionIndex);
        Long updated = this.offsetCommitted.compute(key, (k, old) -> {
            if (old == null) return offset;
            return offset > old ? offset : old;
        });
        return updated != null && updated.equals(offset);
    }

    /**
     * Persist the offsets to disk (simple text format). Implemented from Storage interface.
     */
    @Override
    public void add_to_storage() {
        System.out.println("Flushing Offsets to disk: " + persistFile.toAbsolutePath());
        // write to temp file then move into place
        Path tmp = persistFile.resolveSibling(persistFile.getFileName() + ".tmp");
        try (BufferedWriter w = new BufferedWriter(new FileWriter(tmp.toFile()))) {
            for (java.util.Map.Entry<ConsumerTopicPartitionKey, Long> entry : offsetCommitted.entrySet()) {
                ConsumerTopicPartitionKey k = entry.getKey();
                Long v = entry.getValue();
                w.write(k.getTopicId() + "," + k.getConsumerId() + "," + k.getPartitionIndex() + "," + v);
                w.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write offsets to temp file: " + e.getMessage());
            return;
        }
        try {
            Files.move(tmp, persistFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            // fallback to non-atomic replace
            try {
                Files.move(tmp, persistFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                System.err.println("Failed to move temp offsets file into place: " + ex.getMessage());
            }
        }
    }

    private void loadFromStorage() {
        File f = persistFile.toFile();
        if (!f.exists()) return;
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                try {
                    if (parts.length == 3) {
                        // legacy format: topicId,consumerId,offset -> assume partition 0
                        Integer topicId = Integer.valueOf(parts[0]);
                        Integer consumerId = Integer.valueOf(parts[1]);
                        Long offset = Long.valueOf(parts[2]);
                        ConsumerTopicPartitionKey key = new ConsumerTopicPartitionKey(topicId, consumerId, 0);
                        this.offsetCommitted.put(key, offset);
                    } else if (parts.length == 4) {
                        Integer topicId = Integer.valueOf(parts[0]);
                        Integer consumerId = Integer.valueOf(parts[1]);
                        Integer partitionIdx = Integer.valueOf(parts[2]);
                        Long offset = Long.valueOf(parts[3]);
                        ConsumerTopicPartitionKey key = new ConsumerTopicPartitionKey(topicId, consumerId, partitionIdx);
                        this.offsetCommitted.put(key, offset);
                    }
                } catch (NumberFormatException nfe) {
                    // skip invalid line
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load offsets from disk: " + e.getMessage());
        }
    }

}

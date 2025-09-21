package bloom_filter_cs.strategy;

import bloom_filter_cs.interfaces.HashStrategy;

import java.nio.charset.StandardCharsets;

public class DJB2HashStrategy implements HashStrategy {
    @Override
    public long hash(String data) {
        long hash = 5381L;
        for(Byte b: data.getBytes(StandardCharsets.UTF_8)) {
            hash = ((hash << 5) + hash) + b;
        }
        return hash;
    }
}

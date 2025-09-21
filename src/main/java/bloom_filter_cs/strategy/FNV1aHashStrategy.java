package bloom_filter_cs.strategy;

import bloom_filter_cs.interfaces.HashStrategy;

import java.nio.charset.StandardCharsets;

public class FNV1aHashStrategy implements HashStrategy {
    private static final long FNV_PRIME = 0x100000001b3L;
    private static final long FNV_OFFSET_BASIS = 0xcbf29ce484222325L;
    @Override
    public long hash(String data) {
        long hash = FNV_OFFSET_BASIS;
        for(Byte b: data.getBytes(StandardCharsets.UTF_8)) {
            hash ^= b;
            hash *= FNV_PRIME;
        }
        return hash;
    }
}

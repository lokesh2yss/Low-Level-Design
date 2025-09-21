package bloom_filter_cs.strategy;

import bloom_filter_cs.enums.HashType;
import bloom_filter_cs.interfaces.HashStrategy;

public class HashStrategyFactory {
    public static HashStrategy create(HashType type) {
        return switch (type) {
            case FNV1A -> new FNV1aHashStrategy();
            case DJB2 -> new DJB2HashStrategy();
            default -> throw new IllegalArgumentException("Unsupported hash type");
        };
    }
}

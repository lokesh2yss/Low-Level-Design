package bloom_filter_cs;

import bloom_filter_cs.entities.BloomFilter;
import bloom_filter_cs.enums.HashType;
import bloom_filter_cs.interfaces.HashStrategy;
import bloom_filter_cs.strategy.HashStrategyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BloomFilterDemo {
    public static void main(String[] args) {
        // --- 1. Manually define parameters ---
        int bitSetSize = 10000;
        int numHashFunctions = 2;
        int expectedInsertions = 1000;

        // --- 2. Create a list of hash strategies at runtime ---
        // We use the Factory to get base strategies and the Decorator to create unique variations.
        List<HashStrategy> strategies = List.of(
                HashStrategyFactory.create(HashType.FNV1A),
                HashStrategyFactory.create(HashType.DJB2)
        );

        // --- 3. Build the filter using the new Builder syntax ---
        BloomFilter filter = new BloomFilter.Builder()
                .withBitSetSize(bitSetSize)
                .withNumHashFunctions(numHashFunctions)
                .withHashStrategies(strategies)
                .build();

        // --- 4. Add elements to the filter ---
        System.out.println("\n--- Adding elements to the filter ---");
        List<String> insertedElements = new ArrayList<>();
        for (int i = 0; i < expectedInsertions; i++) {
            String element = "user" + i + "@example.com";
            insertedElements.add(element);
            filter.add(element);
        }
        System.out.println(expectedInsertions + " elements have been added.");

        // --- 5. Test for presence (no false negatives) ---
        System.out.println("\n--- Verifying no false negatives ---");
        boolean hasFalseNegatives = false;
        for (String element : insertedElements) {
            if (!filter.mightContain(element)) {
                System.err.println("FALSE NEGATIVE DETECTED FOR: " + element);
                hasFalseNegatives = true;
                break;
            }
        }
        if (!hasFalseNegatives) {
            System.out.println("Success! No false negatives found. All inserted elements were detected.");
        }

        // --- 6. Test for false positives ---
        System.out.println("\n--- Testing for false positives ---");
        int testSetSize = 10000;
        int falsePositivesCount = 0;
        for (int i = 0; i < testSetSize; i++) {
            String randomElement = UUID.randomUUID().toString();
            if (filter.mightContain(randomElement)) {
                falsePositivesCount++;
            }
        }
        System.out.println("Number of false positives found: " + falsePositivesCount + " out of " + testSetSize + " random items.");

    }
}

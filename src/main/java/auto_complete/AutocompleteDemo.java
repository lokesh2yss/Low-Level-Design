package auto_complete;

import auto_complete.builder.AutocompleteSystemBuilder;
import auto_complete.entities.AutocompleteSystem;
import auto_complete.strategy.AlphabeticalRanking;
import auto_complete.strategy.FrequencyBasedStrategy;


import java.util.List;

public class AutocompleteDemo {
    public static void main(String[] args) {
        System.out.println("----------- SCENARIO 1: Frequency-based Ranking -----------");

        // 1. Build the system with the default frequency-based strategy
        AutocompleteSystem systemByFrequency = new AutocompleteSystemBuilder()
                .withMaxSuggestions(5)
                .withRankingStrategy(new FrequencyBasedStrategy())
                .build();

        // 2. Feed data into the system
        // 'canada' is added most frequently, followed by 'car'
        List<String> dictionary = List.of(
                "car", "cat", "cart", "cartoon", "canada", "candy",
                "car", "canada", "canada", "car", "canada", "canopy", "captain"
        );
        systemByFrequency.addWords(dictionary);

        // 3. Get suggestions for a prefix
        String prefix1 = "ca";
        List<String> suggestions1 = systemByFrequency.getSuggestions(prefix1);
        System.out.println("Suggestions for '" + prefix1 + "': " + suggestions1);

        String prefix2 = "car";
        List<String> suggestions2 = systemByFrequency.getSuggestions(prefix2);
        System.out.println("Suggestions for '" + prefix2 + "': " + suggestions2);


        System.out.println("\n----------- SCENARIO 2: Alphabetical Ranking -----------");

        // 1. Build a new system with the alphabetical strategy
        AutocompleteSystem systemAlphabetical = new AutocompleteSystemBuilder()
                .withRankingStrategy(new AlphabeticalRanking())
                .build();

        // 2. Feed the same data
        systemAlphabetical.addWords(dictionary);

        // 3. Get suggestions for the same prefix
        List<String> suggestions3 = systemAlphabetical.getSuggestions(prefix1);
        System.out.println("Suggestions for '" + prefix1 + "' (alphabetical): " + suggestions3);
    }
}
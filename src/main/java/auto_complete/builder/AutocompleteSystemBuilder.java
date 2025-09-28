package auto_complete.builder;

import auto_complete.entities.AutocompleteSystem;
import auto_complete.strategy.FrequencyBasedStrategy;
import auto_complete.strategy.interfaces.RankingStrategy;

public class AutocompleteSystemBuilder {
    private RankingStrategy rankingStrategy = new FrequencyBasedStrategy();
    private int maxSuggestions = 10;

    public AutocompleteSystemBuilder withRankingStrategy(RankingStrategy strategy) {
        this.rankingStrategy = strategy;
        return this;
    }
    public AutocompleteSystemBuilder withMaxSuggestions(int maxSuggestions) {
        this.maxSuggestions  = maxSuggestions;
        return this;
    }

    public AutocompleteSystem build() {
        return new AutocompleteSystem(rankingStrategy, maxSuggestions);
    }
}

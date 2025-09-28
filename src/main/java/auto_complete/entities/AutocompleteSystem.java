package auto_complete.entities;

import auto_complete.strategy.interfaces.RankingStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutocompleteSystem {
    private final Trie trie;
    private final RankingStrategy strategy;
    private final int maxSuggestions;


    public AutocompleteSystem(RankingStrategy strategy, int maxSuggestions) {
        this.trie = new Trie();
        this.strategy = strategy;
        this.maxSuggestions = maxSuggestions;
    }
    public void addWord(String word) {
        this.trie.insert(word.toLowerCase());
    }

    public void addWords(List<String> words) {
        words.forEach(this::addWord);
    }

    public List<String> getSuggestions(String prefix) {
        TrieNode prefixNode = trie.searchPrefix(prefix.toLowerCase());
        if(prefixNode == null) {
            return Collections.emptyList();
        }
        List<Suggestion> rawSuggestions = trie.collectSuggestions(prefixNode, prefix);
        List<Suggestion> rankedSuggestions = strategy.rank(rawSuggestions);

        return rankedSuggestions.stream()
                .limit(maxSuggestions)
                .map(Suggestion::getWord)
                .collect(Collectors.toList());
    }
}

package auto_complete.entities;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(char ch: word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
        }
        current.setEndOfWord(true);
        current.incrementFrequency();
    }
    public TrieNode searchPrefix(String prefix) {
        TrieNode current = root;
        for(char ch: prefix.toCharArray()) {
            TrieNode node = current.getChildren().get(ch);
            if(node == null) return null;

            current = node;
        }
        return current;
    }

    public List<Suggestion> collectSuggestions(TrieNode startNode, String prefix) {
        List<Suggestion> suggestions = new ArrayList<>();
        collect(startNode, prefix, suggestions);
        return suggestions;
    }
    private void collect(TrieNode node, String currentPrefix, List<Suggestion> suggestions) {
        if(node.isEndOfWord()) {
            suggestions.add(new Suggestion(currentPrefix, node.getFrequency()));
        }
        for(char ch: node.getChildren().keySet()) {
            collect(node.getChildren().get(ch), currentPrefix+ch, suggestions);
        }
    }
}

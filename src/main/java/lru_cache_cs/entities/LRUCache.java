package lru_cache_cs.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;

    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dll = new DoublyLinkedList<>();
    }

    public synchronized void remove(K key) {
        if(!cache.containsKey(key)) return;
        Node<K, V> node = cache.get(key);
        cache.remove(key);
        dll.remove(node);
    }
    public synchronized V get(K key) {
        if(!cache.containsKey(key)) return null;
        Node<K, V> node = cache.get(key);
        dll.moveToFront(node);
        return node.value;
    }
    public synchronized void put(K key, V value) {
        if(cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            dll.moveToFront(node);
        }
        else {
            if(cache.size() == capacity) {
                Node<K, V> lru = dll.removeLast();
                if(lru != null) {
                    cache.remove(lru.key);
                }
            }
            Node<K, V> node = new Node<>(key, value);
            dll.addFirst(node);
            cache.put(key, node);
        }
    }

}

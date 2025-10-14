package url_shortener.repository;

import url_shortener.entities.ShortenedURL;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryURLRepository implements URLRepository{
    private final Map<String, ShortenedURL> keyToURLMap = new ConcurrentHashMap<>();
    private final Map<String, String> longURLToKeyMap = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1); //starts at 1001;
    @Override
    public void save(ShortenedURL url) {
        keyToURLMap.put(url.getShortKey(), url);
        longURLToKeyMap.put(url.getLongURL(), url.getShortKey());
    }

    @Override
    public Optional<ShortenedURL> findByKey(String key) {
        ShortenedURL url = keyToURLMap.get(key);
        return Optional.ofNullable(url);
    }

    @Override
    public Optional<String> findByLongURL(String longURL) {
        return Optional.ofNullable(longURLToKeyMap.get(longURL));
    }

    @Override
    public long getNextId() {
        return idCounter.getAndIncrement();
    }

    @Override
    public boolean existsByKey(String key) {
        return keyToURLMap.containsKey(key);
    }
}

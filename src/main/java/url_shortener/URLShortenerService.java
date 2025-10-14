package url_shortener;

import url_shortener.entities.ShortenedURL;
import url_shortener.enums.EventType;
import url_shortener.observer.Observer;
import url_shortener.repository.URLRepository;
import url_shortener.strategy.KeyGenerationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static url_shortener.enums.EventType.URL_CREATED;

public class URLShortenerService {
    private static URLShortenerService instance;
    private URLRepository urlRepository;
    private KeyGenerationStrategy keyGenerationStrategy;
    private String domain;
    private static final int MAX_RETRIES = 10;
    private List<Observer> observers = new ArrayList<>();
    private URLShortenerService() {}

    public static synchronized URLShortenerService getInstance() {
        if(instance == null) {
            instance = new URLShortenerService();
        }
        return instance;
    }
    public void configure(String domain, URLRepository repository, KeyGenerationStrategy strategy) {
        this.domain = domain;
        this.urlRepository = repository;
        this.keyGenerationStrategy = strategy;
    }
    public String shorten(String longURL) {
        // Check if we've already shortened this URL
        Optional<String> existingKey = urlRepository.findByLongURL(longURL);
        if(existingKey.isPresent()) {
            return domain+existingKey.get();
        }
        // Generate a new key, handling potential collisions
        String shortKey = generateUniqueKey();
        ShortenedURL shortenedURL = new ShortenedURL.Builder(longURL,shortKey).build();
        urlRepository.save(shortenedURL);
        notifyObservers(URL_CREATED, shortenedURL);
        return domain+shortKey;
    }

    private String generateUniqueKey() {
        for(int i=0; i< MAX_RETRIES; i++) {
            // The ID is passed but may be ignored by some strategies (like random)
            String potentialKey = keyGenerationStrategy.generateKey(urlRepository.getNextId());
            if(!urlRepository.existsByKey(potentialKey)) {
                //fount a unique key
                return potentialKey;
            }
        }
        throw new IllegalArgumentException("Could not generate a unique key");
    }
    public Optional<String> resolve(String shortURL) {
        if(!shortURL.startsWith(domain)) {
            return Optional.empty();
        }
        String shortKey = shortURL.replace(domain, "");
        if(urlRepository.existsByKey(shortKey)) {
            ShortenedURL shortenedURL = urlRepository.findByKey(shortKey).get();
            notifyObservers(EventType.URL_ACCESSED, shortenedURL);
            return Optional.of(shortKey);
        }
        return Optional.empty();
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    private void notifyObservers(EventType eventType, ShortenedURL shortenedURL) {
        for(Observer observer: observers) {
            observer.update(eventType, shortenedURL);
        }
    }


}

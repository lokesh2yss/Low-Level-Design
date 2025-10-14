package url_shortener.repository;

import url_shortener.entities.ShortenedURL;

import java.util.Optional;

public interface URLRepository {
    void save(ShortenedURL url);
    Optional<ShortenedURL> findByKey(String key);
    Optional<String> findByLongURL(String longURL);
    long getNextId();
    boolean existsByKey(String key);
}

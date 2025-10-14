package url_shortener.observer;

import url_shortener.entities.ShortenedURL;
import url_shortener.enums.EventType;

public interface Observer {
    void update(EventType type, ShortenedURL url);
}

package url_shortener.entities;

import java.time.LocalDateTime;

public class ShortenedURL {
    private final String longURL;
    private final String shortKey;
    private final LocalDateTime creationDate;
    public ShortenedURL(Builder builder) {
        this.longURL = builder.longURL;
        this.shortKey = builder.shortKey;
        this.creationDate = builder.creationDate;
    }

    public String getLongURL() {
        return longURL;
    }

    public String getShortKey() {
        return shortKey;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public static class Builder {
        private String longURL;
        private String shortKey;
        private LocalDateTime creationDate;
        public Builder(String longURL, String shortKey) {
            this.longURL = longURL;
            this.shortKey = shortKey;
            this.creationDate = LocalDateTime.now();
        }
        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }
        public ShortenedURL build() {
            return new ShortenedURL(this);
        }
    }
}

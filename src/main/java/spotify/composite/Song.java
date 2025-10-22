package spotify.composite;

import java.util.UUID;

public class Song implements PlayableEntity{
    private final String id;
    private final String title;
    private final String artist;

    private Song(String id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void play() {
        System.out.println("Playing song: "+title);
    }

    public String getArtist() {
        return artist;
    }

    public static class Builder {
        private String id;
        private String title;
        private String artist;

        public Builder() {
            this.id = UUID.randomUUID().toString();
        }
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }
        public Song build() {
            if (title == null || artist == null) {
                throw new IllegalStateException("Title and artist must be provided");
            }
            return new Song(id, title, artist);
        }
    }
}

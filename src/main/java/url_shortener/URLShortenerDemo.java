package url_shortener;

import url_shortener.observer.AnalyticsService;
import url_shortener.repository.InMemoryURLRepository;
import url_shortener.strategy.RandomStrategy;

import java.util.Optional;

public class URLShortenerDemo {
    public static void main(String[] args) {
        // --- 1. Setup Phase ---
        // Get the Singleton instance of our service
        URLShortenerService shortener = URLShortenerService.getInstance();

        // Configure the service with the chosen strategy and repository
        shortener.configure("http://short.ly/", new InMemoryURLRepository(), new RandomStrategy());
        shortener.addObserver(new AnalyticsService());

        System.out.println("--- URL Shortener Service Initialized ---\n");

        // --- 2. Usage Phase ---
        String originalUrl1 = "https://www.verylongurl.com/with/lots/of/path/segments/and/query/params?id=123&user=test";
        System.out.println("Shortening: " + originalUrl1);
        String shortUrl1 = shortener.shorten(originalUrl1);
        System.out.println("Generated Short URL: " + shortUrl1);
        System.out.println();

        // Shorten the same URL again
        System.out.println("Shortening the same URL again...");
        String shortUrl2 = shortener.shorten(originalUrl1);
        System.out.println("Generated Short URL: " + shortUrl2);
        if (shortUrl1.equals(shortUrl2)) {
            System.out.println("SUCCESS: The system correctly returned the existing short URL.\n");
        }

        // Shorten a different URL
        String originalUrl2 = "https://www.anotherdomain.com/page.html";
        System.out.println("Shortening: " + originalUrl2);
        String shortUrl3 = shortener.shorten(originalUrl2);
        System.out.println("Generated Short URL: " + shortUrl3);
        System.out.println();

        // --- 3. Resolution Phase ---
        System.out.println("--- Resolving and Tracking Clicks ---");

        // Resolve the first URL multiple times
        resolveAndPrint(shortener, shortUrl1);
        resolveAndPrint(shortener, shortUrl1);
        resolveAndPrint(shortener, shortUrl3);

        // Try to resolve a non-existent URL
        System.out.println("\nResolving a non-existent URL...");
        resolveAndPrint(shortener, "http://short.ly/nonexistent");
    }

    private static void resolveAndPrint(URLShortenerService shortener, String shortUrl) {
        Optional<String> resolvedUrl = shortener.resolve(shortUrl);
        if (resolvedUrl.isPresent()) {
            System.out.printf("Resolved %s -> %s%n", shortUrl, resolvedUrl.get());
        } else {
            System.out.printf("No original URL found for %s%n", shortUrl);
        }
    }
}

package url_shortener.strategy;

public class Base62Strategy implements KeyGenerationStrategy{
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = 62;
    /**
     * This is the smallest number that will produce a 6-character Base62 string.
     * It's calculated as 62^5.
     */
    private static final long MIN_6_CHAR_ID_OFFSET = 916_132_832L;
    @Override
    public String generateKey(long id) {
        if(id == 0) {
            return String.valueOf(BASE62_CHARS.charAt(0));
        }
        long idWithOffset = id + MIN_6_CHAR_ID_OFFSET;
        StringBuilder sb = new StringBuilder();
        while(idWithOffset > 0) {
            sb.append(BASE62_CHARS.charAt((int) idWithOffset%BASE));
            idWithOffset /= BASE;
        }
        return sb.reverse().toString();
    }
}

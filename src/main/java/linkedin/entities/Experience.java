package linkedin.entities;

public class Experience {
    private final String title;
    private final String company;
    private final int startYear;
    private final int endYear;

    public Experience(String title, String company, int startYear, int endYear) {
        this.title = title;
        this.company = company;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return String.format("%sm %s (%d - %d)", title, company, startYear, endYear);
    }
}

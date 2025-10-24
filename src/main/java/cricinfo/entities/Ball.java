package cricinfo.entities;

import cricinfo.enums.ExtraType;
import cricinfo.enums.PlayerRole;

public class Ball {
    private final int ballNumber;
    private final Player bowledBy;
    private final Player facedBy;
    private final int runsScored;
    private final Wicket wicket; // null if no wicket
    private final ExtraType extraType; //null if no extra type
    private final String commentary;

    private Ball(Builder builder) {
        this.ballNumber = builder.ballNumber;
        this.bowledBy = builder.bowledBy;
        this.facedBy = builder.facedBy;
        this.runsScored = builder.runsScored;
        this.wicket = builder.wicket;
        this.extraType = builder.extraType;
        this.commentary = builder.commentary;
    }

    public boolean isWicket() { return wicket != null; }
    public boolean isBoundary() { return runsScored == 4 || runsScored == 6; }

    public int getBallNumber() {
        return ballNumber;
    }

    public ExtraType getExtraType() {
        return extraType;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public Player getBowledBy() {
        return bowledBy;
    }

    public Player getFacedBy() {
        return facedBy;
    }

    public String getCommentary() {
        return commentary;
    }

    public Wicket getWicket() {
        return wicket;
    }

    public static class Builder {
        private int ballNumber;
        private Player bowledBy;
        private Player facedBy;
        private int runsScored;
        private Wicket wicket;
        private ExtraType extraType;
        private String commentary;

        public Builder withBallNumber(int ballNumber) {
            this.ballNumber = ballNumber;
            return this;
        }
        public Builder bowledBy(Player player) {
            this.bowledBy = player;
            return this;
        }
        public Builder facedBy(Player player) {
            this.facedBy = player;
            return this;
        }
        public Builder withRuns(int runsScored) {
            this.runsScored = runsScored;
            return this;
        }
        public Builder withWicket(Wicket wicket) {
            this.wicket = wicket;
            return this;
        }
        public Builder withExtraType(ExtraType type) {
            this.extraType = extraType;
            return this;
        }
        public Builder withCommentary(String commentary) {
            this.commentary = commentary;
            return this;
        }
        public Ball build() {
            Ball tempBall = new Ball(this);

            if (this.commentary == null) {
                this.commentary = CommentaryManager.getInstance().generateCommentary(tempBall);
            }

            return new Ball(this);
        }

    }

}

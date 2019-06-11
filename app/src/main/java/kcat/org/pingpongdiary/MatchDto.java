package kcat.org.pingpongdiary;

public class MatchDto {
    private int id;
    private String name;
    private String clubName;
    private int rank;
    private int handy;
    private int racket_type;
    private int frontRubber;
    private int backRubber;
    private int winSet;
    private int roseSet;
    private String matchDate;
    private String review;


    MatchDto()
    {

    }

    MatchDto(String name, String clubName, int rank, int handy, int racket_type, int frontRubber, int backRubber, int winSet, int roseSet, String matchDate, String review)
    {
        this.name = name;
        this.clubName =clubName;
        this.rank = rank;
        this.handy = handy;
        this.racket_type = racket_type;
        this.frontRubber = frontRubber;
        this.backRubber = backRubber;
        this.winSet = winSet;
        this.roseSet = roseSet;
        this. matchDate = matchDate;
        this.review = review;
    }



    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setHandy(int handy) {
        this.handy = handy;
    }

    public void setFrontRubber(int frontRubber) {
        this.frontRubber = frontRubber;
    }

    public void setBackRubber(int backRubber) {
        this.backRubber = backRubber;
    }

    public void setWinSet(int winSet) {
        this.winSet = winSet;
    }

    public void setRoseSet(int roseSet) {
        this.roseSet = roseSet;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClubName() {
        return clubName;
    }

    public int getRank() {
        return rank;
    }

    public int getHandy() {
        return handy;
    }

    public int getFrontRubber() {
        return frontRubber;
    }

    public int getBackRubber() {
        return backRubber;
    }

    public int getWinSet() {
        return winSet;
    }

    public int getRoseSet() {
        return roseSet;
    }

    public String getReview() {
        return review;
    }

    public int getRacket_type() {
        return racket_type;
    }

    public void setRacket_type(int racket_type) {
        this.racket_type = racket_type;
    }
}

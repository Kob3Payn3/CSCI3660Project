package school.myapplication;

public class Score {
    private int playerId;
    private String playerName;
    private int playerScore;

    public Score() {}

    public Score(int id, String name, int score) {
        playerId = id;
        playerName = name;
        playerScore = score;
    }

    public int getId() { return playerId; }

    public void setId(int id) { playerId = id; }

    public String getName() { return playerName; }

    public void setName(String name) { playerName = name; }

    public int getScore() { return playerScore; }

    public void setScore(int score) { playerScore = score; }
}

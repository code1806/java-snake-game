public class SnakeGameScore {
    private int score;

    public SnakeGameScore() {
        score = 0;
    }

    public void increment() {
        score++;
    }

    public void reset() {
        score = 0;
    }

    public int getScore() {
        return score;
    }
}

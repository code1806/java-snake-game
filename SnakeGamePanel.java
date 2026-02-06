import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SnakeGamePanel extends JPanel {
	private LinkedList<Point> snake;
	private Point food;
	private String direction = "RIGHT";
	private boolean gameOver = false;
	private SnakeGameScore score;

	private final int CELL_SIZE = 20;
	private final int ROWS = 24;
	private final int COLS = 31;

	private Color snakeColor = Color.GREEN;
	private Color foodColor = Color.RED;

	private int difficulty = 1;

	public SnakeGamePanel() {
		setBackground(Color.black);
		score = new SnakeGameScore();
		resetGame();
	}

	public void setDifficulty(int difficulty) {
		if (difficulty < 1) {
			difficulty = 1;
		}
		if (difficulty > 3) {
			difficulty = 3;
		}
		this.difficulty = difficulty;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public int getMoveDelay() {
		switch (difficulty) {
		case 1: return 200;
		case 2: return 125;
		case 3: return 50;
		default: return 150;
		}
	}

	public void resetGame() {
		snake = new LinkedList<>();
		snake.add(new Point(5, 5));
		direction = "RIGHT";
		spawnFood();
		score.reset();
		gameOver = false;
		repaint();
	}

	public void moveSnake() {
		if (gameOver) return;

		Point head = new Point(snake.getFirst());

		switch (direction) {
		case "UP": 
			head.y--; 
			break;
		case "DOWN": 
			head.y++; 
			break;
		case "LEFT": 
			head.x--; 
			break;
		case "RIGHT": 
			head.x++; // updating head position 
			break;
		}

		if (head.x < 0 || head.x >= COLS || head.y < 0 || head.y >= ROWS || snake.contains(head)) {
			gameOver = true;
			repaint();
			return;
		}

		snake.addFirst(head);

		if (head.equals(food)) {
			score.increment();
			spawnFood();
		} else {
			snake.removeLast();
		}

		repaint();
	}

	public void setDirection(String newDirection) {
		if ((direction.equals("UP") && newDirection.equals("DOWN")) ||
				(direction.equals("DOWN") && newDirection.equals("UP")) ||
				(direction.equals("LEFT") && newDirection.equals("RIGHT")) ||
				(direction.equals("RIGHT") && newDirection.equals("LEFT"))) {
			return;
		}
		direction = newDirection;
	}

	private void spawnFood() {
		int x, y;
		do {
			x = (int) (Math.random() * COLS);
			y = (int) (Math.random() * ROWS);
			food = new Point(x, y);
		} while (snake.contains(food));
	}

	public Color getSnakeColor() {
		return snakeColor;
	}

	public void setSnakeColor(Color snakeColor) {
		this.snakeColor = snakeColor;
		repaint();
	}

	public Color getFoodColor() {
		return foodColor;
	}

	public void setFoodColor(Color foodColor) {
		this.foodColor = foodColor;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.darkGray);
		for (int i = 0; i <= ROWS; i++) {
			g.drawLine(0, i * CELL_SIZE, COLS * CELL_SIZE, i * CELL_SIZE);
		}
		for (int i = 0; i <= COLS; i++) {
			g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, ROWS * CELL_SIZE);
		}
		for (Point p : snake) {
			g.setColor(snakeColor);
			g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
		g.setColor(foodColor);
		g.fillOval(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

		g.setColor(Color.white);
		g.drawString("Score: " + score.getScore(), 10, 20);

		if (gameOver) {
			Font font = new Font("Arial", Font.BOLD, 24);
			g.setFont(font);
			String message = "Game Over! Press 'R' to Restart";
			FontMetrics metrics = g.getFontMetrics(font);
			int x = (getWidth() - metrics.stringWidth(message)) / 2;
			int y = getHeight() / 2;
			g.drawString(message, x, y);
		}
	}
}
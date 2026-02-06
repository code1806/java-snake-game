import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SnakeGameWindow extends JFrame {
    private SnakeGamePanel snakePanel;
    private Timer timer;
	Clip clip;
    
    public SnakeGameWindow() {
    	playMusic("best-game-console-301284.wav");
    	snakePanel = new SnakeGamePanel();
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new SnakeGameMenu(snakePanel), BorderLayout.NORTH);
        showStartMenu();
    }

    private void showStartMenu() {
        JPanel mainMenu = new JPanel(new GridLayout(5, 1));
        JLabel title = new JLabel("Snake Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");
        JButton instructionsButton = new JButton("Instructions");

        mainMenu.add(title);
        mainMenu.add(instructionsButton);
        mainMenu.add(easyButton);
        mainMenu.add(mediumButton);
        mainMenu.add(hardButton);

        add(mainMenu);

        easyButton.addActionListener(e -> startGame(1));
        mediumButton.addActionListener(e -> startGame(2));
        hardButton.addActionListener(e -> startGame(3));
        instructionsButton.addActionListener(e -> showInstructions());

        setVisible(true);
    }

    private void startGame(int difficulty) {
        snakePanel.setDifficulty(difficulty);
        new SnakeGameListener(snakePanel);

        int delay = snakePanel.getMoveDelay();
        timer = new Timer(delay, e -> snakePanel.moveSnake());
        timer.start();

        getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(snakePanel, BorderLayout.CENTER);
        add(new SnakeGameMenu(snakePanel), BorderLayout.NORTH);
        revalidate();
        repaint();
        SwingUtilities.invokeLater(() -> snakePanel.requestFocusInWindow());
    }

    private void showInstructions() {
        JOptionPane.showMessageDialog(this,
                "Instructions:\n" +
                        "- Use arrow keys to control the snake.\n" +
                        "- Eat food to grow and increase score.\n" +
                        "- Avoid crashing into walls or yourself.\n" +
                        "- Press 'R' to restart the game.\n \n" +
                        "You can change color of the snake or the food using the menu bar.\n" + 
                        "Note that if you restart, it will immediately start again at the same difficulty.",
                "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public void playMusic(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
    
    public static void main(String[] args) {
        new SnakeGameWindow();
    }
    
    public void pauseGame() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void resumeGame() {
        if (timer != null) {
            timer.start();
        }
    }
}



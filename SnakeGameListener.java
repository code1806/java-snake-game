import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGameListener implements KeyListener {

    private SnakeGamePanel snakePanel;

    public SnakeGameListener(SnakeGamePanel panel) {
        snakePanel = panel;
        panel.setFocusable(true);
        panel.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                snakePanel.setDirection("UP");
                break;
            case KeyEvent.VK_DOWN:
                snakePanel.setDirection("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                snakePanel.setDirection("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                snakePanel.setDirection("RIGHT");
                break;
            case KeyEvent.VK_R:
                snakePanel.resetGame();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
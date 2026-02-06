import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGameMenu extends JMenuBar {
    private final JMenu settingsMenu;
    private final JMenuItem changeSnakeColor;
    private final JMenuItem changeFoodColor;
    private final JMenuItem quit;
    private final SnakeGamePanel snakePanel;

    public SnakeGameMenu(SnakeGamePanel panel) {
        this.snakePanel = panel;
        settingsMenu = new JMenu("Settings");
        changeSnakeColor = new JMenuItem("Change Snake Color");
        changeFoodColor = new JMenuItem("Change Food Color");
        quit = new JMenuItem("Quit");

        changeSnakeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SnakeGameWindow window = (SnakeGameWindow) SwingUtilities.getWindowAncestor(snakePanel);
            	if (window != null) {
            		window.pauseGame();
            	}
                Color newColor = JColorChooser.showDialog(null, "Choose Snake Color", snakePanel.getSnakeColor());
                if (newColor != null) {
                    snakePanel.setSnakeColor(newColor);
                }
            	if (window != null) {
            		window.resumeGame();
            	}
            }
        });
       
        changeFoodColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGameWindow window = (SnakeGameWindow) SwingUtilities.getWindowAncestor(snakePanel);
            	if (window != null) {
            		window.pauseGame();
            	}
                Color newColor = JColorChooser.showDialog(null, "Choose Food Color", snakePanel.getFoodColor());
                if (newColor != null) {
                    snakePanel.setFoodColor(newColor);
                }
            	if (window != null) {
            		window.resumeGame();
            	}
            }
        });
        
        quit.addActionListener(e -> System.exit(0));

        settingsMenu.add(changeSnakeColor);
        settingsMenu.add(changeFoodColor);
        settingsMenu.add(quit);
        this.add(settingsMenu);
    }
}

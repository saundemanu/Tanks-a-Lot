package gameEngine.RenderingUtil;

import gameEngine.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame;

    public GameWindow(int width, int height, String name, Game game){

        //https://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html
        //https://www.geeksforgeeks.org/synchronized-in-java/

        //makes actual window in OS
        frame = new JFrame(name);
        Dimension maxDims = new Dimension(width, height);

        //setting window size
        frame.setPreferredSize(maxDims);
        frame.setMaximumSize(maxDims);
        frame.setMinimumSize(maxDims);
        //prevents resizing TODO: Add resizing menu
        frame.setResizable(false);

        //sets location of gui in center
        frame.setLocationRelativeTo(null);
        //links close button to exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add game JPanel to frame
        frame.add(game);
        frame.toFront();
        frame.pack();
        frame.setAlwaysOnTop(true);
        //makes the frame viewable/allows user to see
        frame.setVisible(true);
    }

}

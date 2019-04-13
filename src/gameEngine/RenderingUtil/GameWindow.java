package gameEngine.RenderingUtil;


import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame {

    public GameWindow(int width, int height, String name, JPanel game) {

        //https://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html
        //https://www.geeksforgeeks.org/synchronized-in-java/

        //makes actual window in OS
        super(name);
        Dimension maxDims = new Dimension(width, height);

        //setting window size
        this.setPreferredSize(maxDims);
        this.setMaximumSize(maxDims);
        this.setMinimumSize(maxDims);
        //prevents resizing TODO: Add resizing menu
        this.setResizable(false);

        //sets location of gui in center
        this.setLocationRelativeTo(null);
        //links close button to exit
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //add game JPanel to frame
        this.add(game);
        this.toFront();
        this.pack();
        this.setAlwaysOnTop(true);
        //makes the frame viewable/allows user to see
        this.setVisible(true);

    }

}

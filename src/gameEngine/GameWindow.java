package gameEngine;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    Canvas p1;
    Canvas p2;
    public GameWindow(int width, int height, String name, Game game){

        //https://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html
        //https://www.geeksforgeeks.org/synchronized-in-java/

        //makes actual window in OS
        JFrame frame = new JFrame(name);
        Dimension maxDims = new Dimension(width, height);
        Dimension splitDims = new Dimension(width/2, height);

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
        //adding the game to the frame
        JPanel layoutComponent = new JPanel();
        layoutComponent.setLayout(new BorderLayout());
        layoutComponent.setPreferredSize(maxDims);
        layoutComponent.setMaximumSize(maxDims);
        layoutComponent.setMinimumSize(maxDims);
        p1 = game;
        p1.setPreferredSize(splitDims);
        p1.setMaximumSize(splitDims);
        p1.setMinimumSize(splitDims);
        p2 = game;
        p2.setPreferredSize(splitDims);
        p2.setMaximumSize(splitDims);
        p2.setMinimumSize(splitDims);
        layoutComponent.add(p1, BorderLayout.WEST);
        layoutComponent.add(p2, BorderLayout.EAST);
        frame.add(layoutComponent);
        frame.pack();
        //makes the frame viewable/allows user to see
        frame.setVisible(true);

    }



}

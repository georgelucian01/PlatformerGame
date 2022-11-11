package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



import static utilities.Constants.PlayerConstants.*;
import static utilities.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    private Game game;
    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();

        keyboardInputs = new KeyboardInputs(this);
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);

    }


    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size: " + GAME_WIDTH + " x " + GAME_HEIGHT);
    }


    public void updateGame(){

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}

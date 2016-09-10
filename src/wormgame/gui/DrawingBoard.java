/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JPanel;
import wormgame.game.WormGame;

/**
 *
 * @author Blake_Fudge
 */
public class DrawingBoard extends JPanel implements Updatable{
    private WormGame wormGame;
    private int pieceLength;
    
    public DrawingBoard(WormGame wormGame, int pieceLength) {
        super.setBackground(Color.GRAY);
        
        this.wormGame = wormGame;
        this.pieceLength = pieceLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        //draw worm 
        //Cycle through all segements starting with the head.
        //get x, y coords of each and store as local ints
        //draw segment rect
        for(int i = wormGame.getWorm().getPieces().size() - 1; i >= 0; i--) {
            int pX = wormGame.getWorm().getPieces().get(i).getX();
            int pY = wormGame.getWorm().getPieces().get(i).getY();
            
            g.setColor(Color.BLACK);
            g.fill3DRect(pX * pieceLength, pY * pieceLength, pieceLength, pieceLength, true);
        }

        //draw apple
        g.setColor(Color.RED);
        g.fillOval(wormGame.getApple().getX() * pieceLength, wormGame.getApple().getY() * pieceLength,
                pieceLength, pieceLength);

    }
    
    @Override
    public void update() {
        this.repaint();
    }

}

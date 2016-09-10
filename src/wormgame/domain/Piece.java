/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

/**
 *
 * @author Blake_Fudge
 */
public class Piece {
    private int x;
    private int y;
    
    
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean runsInto(Piece piece) {
        if(piece.x == x && piece.y == y) return true;
        return false;
    }
    
    public String toString(){
        //returns (x,y)
        return "(" + this.x + "," + this.y + ")";
    }
}

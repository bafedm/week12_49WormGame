/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import wormgame.Direction;

/**
 *
 * @author Blake_Fudge
 */
public class Worm{
    private List<Piece> pieces;
    private Direction direction;
    private boolean hasGrown;
    private int wormSize;
    
    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.direction = originalDirection;
        
        this.pieces = new ArrayList<Piece>();
        pieces.add(new Piece(originalX, originalY));
        
        //worm automatically grows until it reaches wormSize segments
        this.wormSize = 3;       
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction dir){
        direction = dir;
    }
    
    public int getLength(){
        return pieces.size();
    }
    
    public List<Piece> getPieces(){
        return pieces;
    }
    
    public void move(){
        //intialize movement deltas
        int dx = 0;
        int dy = 0;
        //get last piece for convience sake
        Piece headPiece = pieces.get(pieces.size() - 1);
        
        //Depending on current direction create new piece relative to last piece
        if(direction == Direction.UP) {
            //dx = 0, y = dy - 1
            dx = headPiece.getX() + 0;
            dy = headPiece.getY() + (-1);
            pieces.add(new Piece(dx, dy));
            
        } else if(direction == Direction.RIGHT) {
            //dx = x + 1, dy = 0
            dx = headPiece.getX() + 1;
            dy = headPiece.getY() + 0;
            pieces.add(new Piece(dx, dy));   
            
        } else if(direction == Direction.DOWN) {
            //dx = 0, dy = y + 1
            dx = headPiece.getX() + 0;
            dy = headPiece.getY() + 1;
            pieces.add(new Piece(dx, dy));
            
        } else /*direction == Direction.LEFT*/ {
            //dx = x - 1, dy = 0
            dx = headPiece.getX() + (-1);
            dy = headPiece.getY() + 0;
            pieces.add(new Piece(dx, dy));

        }
        
        //if number of pieces is greater than worm size then remove excess
        if(pieces.size() > wormSize) {
            pieces.remove(0);
        }
       
    }
    
    public void grow(){
        //wormSize is used to track how big the worm is.
        //if the worm is in intial growth stage (seg 1 & 2) method grow has no effect
        if(pieces.size() > 2) wormSize++;
    }
    
    public boolean runsInto(Piece piece) {
        //checks to see if there is of piece against any part of worm 
        //cycle through all worm segments to see if coords match piece
        for(Piece p : pieces) {
            if(p.runsInto(piece)) return true;
        } 
      
        //return false if coords not same
        return false;
    }
    
    public boolean runsIntoItself() {
        //checks to see if worm runs into itself.  Im going to assume that this is the
        //head running into any part of the body.
        //Cycle through array and check to see if head piece coords match body piece coords
        //** head is .size() - 1
        
        //get last piece for convience sake
        Piece headPiece = pieces.get(pieces.size() - 1);
        
        for(Piece p : pieces) {
            //check to see if we are checking the head
            if(p == headPiece) continue;
            //check to see if head piece has same coords as body piece "p"
            if(headPiece.runsInto(p)) return true;
        }
        return false;
    }
}

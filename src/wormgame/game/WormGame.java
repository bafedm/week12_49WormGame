package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.DrawingBoard;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random rand;

    public WormGame(int width, int height) {
        super(1000, null);
        super.start();
        rand = new Random();
        
        this.width = width;
        this.height = height;
        this.continues = true;
      
        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);
        apple = new Apple(rand.nextInt(width), rand.nextInt(height));
        if(this.worm.runsInto(apple)) this.apple = new Apple(apple.getX()-1, apple.getY()-1);

        addActionListener(this);
        setInitialDelay(2000);

    }

    public Worm getWorm() {
        return this.worm;
    }
    
    public void setWorm(Worm worm) {
        if(this.worm != null) this.worm = worm;
    }

    public Apple getApple() {
        return apple;
    }
    
    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public boolean checkIfRunsIntoBorder() {
        //get worm head coords
        //check if head coords match borders
        //if yes return true
        
        Piece wormHead = this.worm.getPieces().get(this.worm.getPieces().size() - 1);
        int headX = wormHead.getX();
        int headY = wormHead.getY();
        
        if(headX == 0 || headX == width){
            return true;
        }
        
        if(headY == 0 || headY == height){
            return true;
        }
        
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        
        this.worm.move();
        
        if(this.worm.runsInto(apple)) {
            apple = new Apple(rand.nextInt(width), rand.nextInt(height));
            if(this.worm.runsInto(apple)) this.apple = new Apple(apple.getX()-1, apple.getY()-1);
            worm.grow();
        }
        
        if(worm.runsIntoItself()) continues = false;
        
        if(checkIfRunsIntoBorder()) continues = false;
  
        
        
        super.setDelay(1000 / worm.getLength());
        
        this.updatable.update();

        
        
//Move the worm
//If the worm runs into the apple, it eats the apple and calls the grow method. A new apple is randomly created.
//If the worm runs into itself, the variable continue is assigned the value false
//Call update, which is a method of the variable updatable which implements the interface Updatable.
//Call the setDelay method which is inherited from the Timer class. The game velocity should grow with respect to the worm length. The call setDelay(1000 / worm.getLength()); will work for it: in the call we expect that you have defined the object variable worm.

    }

}

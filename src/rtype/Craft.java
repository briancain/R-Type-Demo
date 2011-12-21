/*
 * Programmed by: Brian Cain
 */

package rtype;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Craft {

    private String craft = "media/craft23.png";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private ArrayList missiles;
    private final int CRAFT_SIZE = 20;
    private boolean visible;
    
    private int lives = 3;
    
    public Craft() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        missiles = new ArrayList();
        visible = true;
        x = 40;
        y = 182;
    }


    public void move() {
        x += dx;
        y += dy;
        
        // Keep ship in bounds
        if (x < 1) {
        	x = 1;
        }
        
        if (x > 600) {
        	x = 1;
        }
        
        if (y < 1) {
        	y = 379;
        }
        
        if(y > 380) {
        	y = 1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public void setVisible(boolean visible) {
    	this.visible = visible;
    }
    
    public boolean isVisible() {
    	return visible;
    }
    
    public int getLives() {
    	return lives;
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(x, y, width, height);
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && Board.isGame()) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void fire() {
    	Sound.playerShoot.play();
        missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE/2));
    }
    
    public void hit() {
    	lives--;
    	x = 40;
    	y = 182;
    }
    
    public void reset() {
    	x = 40;
    	y = 182;
    	lives = 3;
    	visible = true;
    }

    public void replace() {
    	x = 40;
    	y = 182;
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
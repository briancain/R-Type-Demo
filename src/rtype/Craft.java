package rtype;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * 
 * @author briancain
 *
 */
public class Craft implements Being {
    private String craft = "media/craft23.png";
    private int dx, dy, x, y;
    private int width;
    private int height;
    private Image image;
    private ArrayList<Missile> missiles;
    private final int CRAFT_SIZE = 20;
    private boolean visible;
    private int lives = 3;
    private boolean poweredUp;
    
    public Craft() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        missiles = new ArrayList<Missile>();
        visible = true;
        x = 40;
        y = 182;
        poweredUp = false;
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

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public void setVisible(boolean visible) {
    	this.visible = visible;
    }
    
    public boolean isVisible() {
    	return visible;
    }
    
    public void setPoweredUp(boolean power) {
    	this.poweredUp = power;
    }
    
    public boolean isPoweredUp() {
    	return poweredUp;
    }
    
    public int getLives() {
    	return lives;
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(x, y, width, height);
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && Board.isGame() && !Board.paused) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
        	if (poweredUp) {
        		dx = -3;
        	} else {
        		dx = -1;
        	}
        }

        if (key == KeyEvent.VK_RIGHT) {
        	if (poweredUp) {
        		dx = 3;
        	} else {
        		dx = 1;
        	}
        }

        if (key == KeyEvent.VK_UP) {
        	if (poweredUp) {
        		dy = -3;
        	} else {
        		dy = -1;
        	}
        }

        if (key == KeyEvent.VK_DOWN) {
        	if (poweredUp) {
        		dy = 3;
        	} else {
        		dy = 1;
        	}
        }
    }

    public void fire() {
    	Sound.playerShoot.play();
    	if (poweredUp) {
    		missiles.add(new Missile(x + CRAFT_SIZE - 6, y + CRAFT_SIZE/2 - 6));
    		missiles.add(new Missile(x + CRAFT_SIZE + 3, y + CRAFT_SIZE/2 + 3));
    	} else {
    		missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE/2));
    	}
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
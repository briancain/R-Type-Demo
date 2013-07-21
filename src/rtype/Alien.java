package rtype;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * 
 * @author brian
 *
 */
public class Alien implements Being {
	private String alien = "media/enemy11.png";
	
	private int x, y, width, height;
	private boolean visible;
	private Image image;
	
	public Alien(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(alien));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		this.x = x;
		this.y = y;
	}
	
	public void move() {
		Random r = new Random();
		if (x < 0) {
			x = 600;
		}
		int dy;
		
		dy = r.nextInt(10);
		if (dy == 7 || dy == 4) {
			y++;
		}
		else if  (dy == 2 || dy == 5) {
			y--;
		}
		
		if (y < 1) {
        	y = 379;
        }
		//
        if(y > 380) {
        	y = 1;
        }
        
		x--;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}

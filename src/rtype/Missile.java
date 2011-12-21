/*
 * Programmed by: Brian Cain
 */

package rtype;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Missile {
	
	private int x, y, width, height;
	private Image image;
	boolean visible;
	
	private String msstr = "media/missile.png";
	
	private final int BOARD_WIDTH = 590;
	private final int MISSILE_SPEED = 2;
	
	public Missile(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(msstr));
		image = ii.getImage();
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		return image;
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
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void move() {
		x += MISSILE_SPEED;
		if (x > BOARD_WIDTH){
			visible = false;
		}
	}
}

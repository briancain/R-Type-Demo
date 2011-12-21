/*
 * Programmed by: Brian Cain
 */

package rtype;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Alien {
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
		if (x < 0) {
			x = 600;
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
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}

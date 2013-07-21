package rtype;

import java.awt.Image;

/**
 * 
 * @author brian
 *
 */
public interface Being {
	public void move();
	public int getX();
	public int getY();
	public boolean isVisible();
	public void setVisible(boolean visible);
	public Image getImage();
}

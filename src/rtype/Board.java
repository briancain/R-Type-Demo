package rtype;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author brian
 *
 */
public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String bg = "media/bg.png";
	private Timer timer;
	private Craft craft;
	private ArrayList<Alien> aliens;
	private PowerUp powerUp;
	private static boolean ingame;
	static boolean paused;
	private int B_WIDTH;
	private int B_HEIGHT;
	private int numAlien = 5;
	private Image image;
	private int score;
	private String level;
	private String level1 = "Level 1";
	private String level2 = "Level 2";
	private String level3 = "Level 3";
	private String level4 = "Level 4";
	private String level5 = "Level 5";
	private String level6 = "Level 6";
	
	private int[][] pos = { 
	        {2380, 29}, {2500, 59}, {1380, 89},
	        {780, 109}, {580, 139}, {680, 239}, 
	        {790, 259}, {760, 50}, {790, 150},
	        {980, 209}, {560, 45}, {510, 70},
	        {930, 159}, {590, 80}, {530, 300},
	        {940, 305}, {990, 180}, {920, 200},
	        {900, 259}, {660, 50}, {540, 90},
	        {810, 220}, {860, 315}, {740, 180},
	        {820, 128}, {490, 170}, {700, 30},
	        {2000, 300}, {200, 200}, {550, 250}
	     };
	
	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		ingame = true;
		paused = true;
		score = 0;
		
		level = level1;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));
		image = ii.getImage();
		
		setSize(600, 400);
		
		craft = new Craft();
		
		powerUp = new PowerUp(590, 80);
		
		initAliens();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public static boolean isGame() {
		return ingame;
	}
	
	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();
	}
	
	public void initAliens() {
		aliens = new ArrayList<Alien>();
		
		for (int i = 0; i < numAlien; i++) {
			aliens.add(new Alien(pos[i][0], pos[i][1]));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(image, 0, 0, this);
		
		if (ingame) {
			if (craft.isVisible() && !paused) {
				g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
				g2d.setColor(Color.WHITE);
				g2d.drawString("Ship X: " + craft.getX(), 5, 15);
				g2d.drawString("Ship Y: " + craft.getY(), 5, 30);
			}
			
			if (!paused) {
				ArrayList<?> ms = craft.getMissiles();
				
				for (int i = 0; i < ms.size(); i++){
					Missile m = (Missile) ms.get(i);
					g2d.drawImage(m.getImage(), m.getX(), m.getY(),  this);
				}
				
				for (int i = 0; i < aliens.size(); i++) {
					Alien a = (Alien)aliens.get(i);
					if (a.isVisible()) {
						g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
					}
				}
				
				if (powerUp.isVisible()) {
					g2d.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), this);
				}
			}
			
			g2d.setColor(Color.WHITE);
			g2d.drawString("Aliens Left: " + aliens.size(), 5, 370);
			g2d.drawString("Ships: " + craft.getLives(), 540, 15);
			g2d.drawString("Score: " + score, 270, 15);
			
			if (paused){
				g.drawString("Paused!!", 270, 175);
				g.drawString("Press 'P' to un-pause", 235, 200);
			}
			
			if (numAlien == 5){
				level = level1;
			} else if (numAlien == 10){
				level = level2;
			} else if (numAlien == 15){
				level = level3;
			} else if (numAlien == 20){
				level = level4;
			} else if (numAlien == 25){
				level = level5;
			} else if (numAlien == pos.length){
				level = level6;
			}
			
			if ((score == 1000 || score == 5550) && (!craft.isPoweredUp() && !powerUp.isVisible())) {
				powerUp.setVisible(true);
			}

			g2d.drawString(level, 270, 370);
		}
		else {
			String msg = "Game Over";
			String cnt = "Press Enter to Restart";
			Font medium = new Font("Helvetica", Font.BOLD, 16);
			Font small = new Font("Helvetica", Font.PLAIN, 14);
			FontMetrics metr = this.getFontMetrics(medium);
			FontMetrics metrsm = this.getFontMetrics(small);
			
			aliens.clear();
			
			g.setColor(Color.white);
			g.setFont(medium);
			g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
			g.setFont(small);
			g.drawString(cnt, ((B_WIDTH - metrsm.stringWidth(msg)) / 2) - 30, (B_HEIGHT / 2) + 30);
			String scorestr = "Score: " + score;
			g2d.drawString(scorestr, (B_WIDTH - metr.stringWidth(scorestr)) / 2, 15);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e){
		if (aliens.size() == 0){
			if (craft.getLives() >= 1 && numAlien < pos.length) {
				if (ingame){
					Sound.levelUp.play();
				}
				numAlien += 5;
				initAliens();
			}
			else {
				numAlien = 5;
				ingame = false;
			}
		}
		
		if (!paused) {
			ArrayList<?> ms = craft.getMissiles();

	        for (int i = 0; i < ms.size(); i++) {
	            Missile m = (Missile) ms.get(i);
	            if (m.isVisible()) {
	            	m.move();
	            }
	            else {
	            	ms.remove(i);
	            }
	        }
	        
	        for (int i = 0; i < aliens.size(); i++) {
	            Alien a = (Alien) aliens.get(i);
	            if (a.isVisible()) {
	            	a.move();
	            }
	            else {
	            	aliens.remove(i);
	            }
	        }
	        
	        if (powerUp.isVisible()) {
	        	powerUp.move();
	        }
			
			craft.move();
			checkCollisions();
		}
		
		repaint();
	}
	
	public void checkCollisions() {
		Rectangle r3 = craft.getBounds();
		
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = (Alien) aliens.get(i);
			Rectangle r2 = a.getBounds();
			
			if (r3.intersects(r2) && ingame) {
				if(craft.getLives() > 1) {
					Sound.playerDeath.play();
					a.setVisible(false);
					craft.hit();
					score += 50;
					craft.setPoweredUp(false);
					powerUp = new PowerUp(590, 80);
				}
				else {
					Sound.playerDeath.play();
					craft.setVisible(false);
					a.setVisible(false);
					score += 50;
					ingame = false;
				}
				
			}
		}
		
		ArrayList<?> ms = craft.getMissiles();
		
		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			Rectangle r1 = m.getBounds();
			
			for (int j = 0; j < aliens.size(); j++) {
				Alien a = (Alien) aliens.get(j);
				Rectangle r2 = a.getBounds();
				
				if (r1.intersects(r2)) {
					Sound.enemyDeath.play();
					m.setVisible(false);
					a.setVisible(false);
					score += 100;
				}
			}
		}
		
		if (powerUp.isVisible()) {
			Rectangle r = powerUp.getBounds();
			
			if (r.intersects(r3) && ingame) {
				Sound.powerup.play();
				craft.setPoweredUp(true);
				powerUp.setVisible(false);
			}
		}
	}
	
	public void boardKeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ENTER && !ingame){
			numAlien = 5;
			initAliens();
			craft.reset();
			score = 0;
			ingame = true;
		}
		
		if (key == KeyEvent.VK_P && ingame) {
			if (paused) {
				paused = false;
			}
			else if (!paused) {
				paused = true;
			}
		}
	}
	
	private class TAdapter extends KeyAdapter {
		
		public void keyReleased(KeyEvent e){
			craft.keyReleased(e);
		}
		 
		public void keyPressed(KeyEvent e) {
			craft.keyPressed(e);
			boardKeyPressed(e);
		}
	}
}

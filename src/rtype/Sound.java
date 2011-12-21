/*
 * Programmed by: Brian Cain
 */

package rtype;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound playerShoot = new Sound("media/shoot.wav");
	public static final Sound playerDeath = new Sound("media/bossdeath.wav");
	public static final Sound enemyDeath = new Sound("media/death.wav");
	public static final Sound gameMusic = new Sound("media/gamemusic.wav");
	public static final Sound levelUp = new Sound("media/levelup.wav");
	
	private AudioClip clip;
	
	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

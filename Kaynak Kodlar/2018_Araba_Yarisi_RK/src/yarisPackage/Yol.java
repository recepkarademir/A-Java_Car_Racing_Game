package yarisPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Yol extends JPanel implements ActionListener, Runnable, Sabitler 
{
	private static final long serialVersionUID = 1L;

	Timer mainTimer = new Timer(TICKS, this); // s�re tutabilmek i�in.
	
	Image img = new ImageIcon("img/yol.png").getImage();
	
	Player p = new Player();
	
	Thread enemyFactory = new Thread(this);
	
	List<Rakip> enemies = new ArrayList<Rakip>();
	
	public Yol() {
		mainTimer.start();
		enemyFactory.start();
		addKeyListener(new myKeyListener());
		setFocusable(true);
	}
	private class myKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			p.keyRealeased(e);
		}
	}
	
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1, 0, null);
		g.drawImage(img, p.layer2, 0, null);
		g.drawImage(p.img, p.x, p.y, null);
		
		double v = (190/Player.MAX_V) * p.v;
		double s = p.s / 1000;
		int kmpV=0;
		kmpV=(int)(v*1.609);
		g.setColor(Color.BLACK);
		
		Font font = new Font("Arial", Font.BOLD, 22);
		g.setFont(font);
		String str = "H�z : "+ kmpV +" Kmh "+" Gitti�in yol : " + s + " m   Biti� i�in: " + (ACHIEVE_S-s) + " m";
		g.drawString(str, 180, 30); // bilgilerin yaz�laca�� konumlar
		
		Iterator<Rakip> i = enemies.iterator();
		while(i.hasNext()) {
			Rakip e = new Rakip(kmpV, kmpV, kmpV, null);
			e=i.next();
			if(e.x >= 2500 || e.x <= -2500) // ara�lar�n ge�ildikten ne kadar sonra haritadan silinece�i
			{
				i.remove();
			} 
			else 
			{
				e.move();
				g.drawImage(e.img, e.x, e.y, null);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		p.move();
		repaint();
		testCollisionWithEnemies();
		testWin();
		
	}

	private void testWin() {
		if(p.s/1000 >= ACHIEVE_S) {
			JOptionPane.showMessageDialog(null, "Yar�� Bitti KAZANDIN ");
			System.exit(1);
		}
	}

	private void testCollisionWithEnemies() 
	{
		
		Iterator<Rakip> i = enemies.iterator();
		while(i.hasNext()) 
		{
			Rakip e = i.next();
			if(p.getRect().intersects(e.getRect())) 
			{
				JOptionPane.showMessageDialog(null, "Kaza Yapt�n!!!");
				
				System.exit(0);
			}
		}
		
	}

	@Override
	public void run() 
	{
		while(true) 
		{
			Random rand = new Random();
			try 
			{
				
				Thread.sleep(rand.nextInt(1400));// 1000 rakip do�ma s�kl���
				
				
				enemies.add(new Rakip(1200,rand.nextInt(500)+20 ,rand.nextInt(20), this));
				// 1200m uzakl�kta yeni yar����lar do�uyor.
				// yolun 520px ile +20px pikselleri aras�nda rakipler do�acaklar.
	
			}
			catch (InterruptedException e) 
			{
				break;
			}
			
			
		}
		
	}
	
	
}

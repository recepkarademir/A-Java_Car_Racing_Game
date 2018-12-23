package yarisPackage;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Rakip 
{

	int x;
	int y;
	int v;
	
	Image img = new ImageIcon("img/Dusman.png").getImage(); // rakip yar����lar�n g�rseli
	Yol road;
	
	public Rectangle getRect() 
	{
		return new Rectangle(x, y, 138, 39);  // d�zman ara�lar�n�n �arp��ma testinde kullan�lacak boyutlar�.
	}
	
	public Rakip(int x, int y, int v, Yol road) {
		this.x = x;
		this.y = y;
		if(v<=2) // �ok yava� olan rakiplerin h�zlanmas� i�in.
		{
			v=4;
		}
		this.v = v*9; // 
		this.road = road; //
		if(this.v>45) // �ok h�zl� giden d��man farkl� bir ara� olacak.
		{
			img=new ImageIcon("img/dusman_hizli.png").getImage();
		}
	}
	
	public void move() 
	{
		x = x - road.p.v + v;
	}
	
}

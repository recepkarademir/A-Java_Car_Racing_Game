package yarisPackage;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Rakip 
{

	int x;
	int y;
	int v;
	
	Image img = new ImageIcon("img/Dusman.png").getImage(); // rakip yarýþçýlarýn görseli
	Yol road;
	
	public Rectangle getRect() 
	{
		return new Rectangle(x, y, 138, 39);  // düzman araçlarýnýn çarpýþma testinde kullanýlacak boyutlarý.
	}
	
	public Rakip(int x, int y, int v, Yol road) {
		this.x = x;
		this.y = y;
		if(v<=2) // çok yavaþ olan rakiplerin hýzlanmasý için.
		{
			v=4;
		}
		this.v = v*9; // 
		this.road = road; //
		if(this.v>45) // çok hýzlý giden düþman farklý bir araç olacak.
		{
			img=new ImageIcon("img/dusman_hizli.png").getImage();
		}
	}
	
	public void move() 
	{
		x = x - road.p.v + v;
	}
	
}

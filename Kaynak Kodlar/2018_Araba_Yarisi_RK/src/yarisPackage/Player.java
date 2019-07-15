package yarisPackage;

/**

 * @author recepkarademir
 */

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player implements Sabitler {
	
	Image img_c = new ImageIcon("img/oyuncu.png").getImage();
	Image img_l = new ImageIcon("img/oyuncu_sol.png").getImage();// oyuncu yön belirlediðinde araba görseli bu olacak
	Image img_r = new ImageIcon("img/oyuncu_sag.png").getImage(); // oyuncu yön belirlediðinde araba görseli bu olacak
	Image img_speed = new ImageIcon("img/oyuncu_hiz.png").getImage(); // oyuncu belli hýza ulaþýnca.
	Image img_Maxspeed = new ImageIcon("img/oyuncu_Maxhiz.png").getImage(); // oyuncu en yüksek hýza ulaþýnca.

	Image img = img_c; 	// baþlangýç olarak oyuncu görseli sabit bir araba.
	
	int v = 0; 
	double dv = 0;
	int s = 0;
	
	int x = 50;		// x ekseni	50px  de
	int y = 275;	// y ekseni 275.px de oyuncu doðacak
	int dy = 0;
	
	int layer1 = 0;		// yol görselinin arka arkaya birleþtirilmesi
	int layer2 = 1200;
	
	public Rectangle getRect() // çarpýþma algýlama için.
	{
		return new Rectangle(x, y, 165, 40); // çarpýþma algýlanacak araçlarýn boyutlarý
	}
	
	public void move() 
	{
		
		s += (v*3);	// aracýn katettiði yol hesplamasý
		v += dv;	// aracýn hýzýnýn artýrýlma komutu
		if(v <= 0) v = 0;	//	araç hýzý negatif olamaz
		if(v >= MAX_V) v = MAX_V;	// araç hýzý maksimum belirlenen bir sabite eþit olmalýdýr.
		y -= dy;	//	rakiplerin doðacaðý konum aralýðý px olarak belirleniyor.
		if(y <= MAX_TOP) y = MAX_TOP;		// belirlenen sabitler arasýnda yeni konumlarda rakipler doðmalý.
		if(y >= MAX_BOTTOM) y = MAX_BOTTOM; // belirlenen sabitler arasýnda yeni konumlarda rakipler doðmalý.
		if( layer2 - v <= 0 ){
			layer1 = 0;
			layer2 = 1200;
		}else{
			layer1 -= v;
			layer2 -= v;
		}
		dv -= 0.00000000000001;	// bu komut aracýn hýzlanma tuþuna basýlmadýðýnda yavaþlamasýný saðlar.
		if((v<2 && dy==0) || (v<2 && dy==-0))		//	 bu if bloðunu ise araç durduðunda görüntüsünün duran bir araç görüntüsü olmasýný saðlar.
		{
			img = img_c;		// duran araç görüntüsü anlýk olarak oyuna yansýtýlýr.
		}
		
	}
	
	public void keyPressed(KeyEvent e) // yön tuþlarýný dinleyen dinleyici sýnýf
	{
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)  // key deðeri sað tuþa basýldýðýnda 
		{
			dv = 1.02; // araç hýzlanma katsayýsý
			
			if(v>2)
			{
				if(v>45)// kullanýcý çok hýzlýysa arabasý bu görüntüyle deðiþtirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2) // araç çok yavaþsa bu görüntüyü yükleriz
			{
				img = img_c;// orijinal duran araç gçrüntüsü
			}
			
		}
		else if(key == KeyEvent.VK_LEFT) 
		{
			v *= (0.70); // araç fren yapýnca yavaþlama olacak
			if(v>2)
			{
				if(v>45) // kullanýcý çok hýzlýysa arabasý bu görüntüyle deðiþtirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2)// araç çok yavaþsa bu görüntüyü yükleriz
			{
				img = img_c; // orijinal duran araç gçrüntüsü
			}
		}
		else if(key == KeyEvent.VK_UP) 
		{
			
			if(v!=0) // araç durmamýþsa yön deðitirebilir.
			{
				v *= (0.90); // araç yön deðiþtirince yavaþlama olacak
				dy = 7; // 5 px yön deðiþtirir
			}
			img = img_l;
			
		}
		else if(key == KeyEvent.VK_DOWN) 
		{
			
			if(v!=0)// araç durmamýþsa yön deðitirebilir.
			{
				v *= (0.90); // araç yön deðiþtirince yavaþlama olacak
				dy = -7; // 5 px yön deðiþtirir
			}
			img = img_r;
		}
		
	}
	
	public void keyRealeased(KeyEvent e) // yön belirlendikten sonra tuþun devamlýlýðýný saðlayan dinleyici.
	{

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) 
		{
			if(key == KeyEvent.VK_RIGHT)
			{
				dv=0; // sadece hýzlanma devam ediyorsa hýz katsayýsý artýtýlmaz.
			}
			
			
			if(v>2)
			{
				if(v>45) // kullanýcý çok hýzlýysa arabasý bu görüntüyle deðiþtirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2)// araç çok yavaþsa bu görüntüyü yükleriz
			{
				img = img_c;
			}
		}
		else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) 
		{
			dy = 0;
			if(v>2)
			{
				if(v>45) // kullanýcý çok hýzlýysa arabasý bu görüntüyle deðiþtirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2)// araç çok yavaþsa bu görüntüyü yükleriz
			{
				img = img_c;
			}
		}
	}
	
	
	
}

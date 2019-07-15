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
	Image img_l = new ImageIcon("img/oyuncu_sol.png").getImage();// oyuncu y�n belirledi�inde araba g�rseli bu olacak
	Image img_r = new ImageIcon("img/oyuncu_sag.png").getImage(); // oyuncu y�n belirledi�inde araba g�rseli bu olacak
	Image img_speed = new ImageIcon("img/oyuncu_hiz.png").getImage(); // oyuncu belli h�za ula��nca.
	Image img_Maxspeed = new ImageIcon("img/oyuncu_Maxhiz.png").getImage(); // oyuncu en y�ksek h�za ula��nca.

	Image img = img_c; 	// ba�lang�� olarak oyuncu g�rseli sabit bir araba.
	
	int v = 0; 
	double dv = 0;
	int s = 0;
	
	int x = 50;		// x ekseni	50px  de
	int y = 275;	// y ekseni 275.px de oyuncu do�acak
	int dy = 0;
	
	int layer1 = 0;		// yol g�rselinin arka arkaya birle�tirilmesi
	int layer2 = 1200;
	
	public Rectangle getRect() // �arp��ma alg�lama i�in.
	{
		return new Rectangle(x, y, 165, 40); // �arp��ma alg�lanacak ara�lar�n boyutlar�
	}
	
	public void move() 
	{
		
		s += (v*3);	// arac�n katetti�i yol hesplamas�
		v += dv;	// arac�n h�z�n�n art�r�lma komutu
		if(v <= 0) v = 0;	//	ara� h�z� negatif olamaz
		if(v >= MAX_V) v = MAX_V;	// ara� h�z� maksimum belirlenen bir sabite e�it olmal�d�r.
		y -= dy;	//	rakiplerin do�aca�� konum aral��� px olarak belirleniyor.
		if(y <= MAX_TOP) y = MAX_TOP;		// belirlenen sabitler aras�nda yeni konumlarda rakipler do�mal�.
		if(y >= MAX_BOTTOM) y = MAX_BOTTOM; // belirlenen sabitler aras�nda yeni konumlarda rakipler do�mal�.
		if( layer2 - v <= 0 ){
			layer1 = 0;
			layer2 = 1200;
		}else{
			layer1 -= v;
			layer2 -= v;
		}
		dv -= 0.00000000000001;	// bu komut arac�n h�zlanma tu�una bas�lmad���nda yava�lamas�n� sa�lar.
		if((v<2 && dy==0) || (v<2 && dy==-0))		//	 bu if blo�unu ise ara� durdu�unda g�r�nt�s�n�n duran bir ara� g�r�nt�s� olmas�n� sa�lar.
		{
			img = img_c;		// duran ara� g�r�nt�s� anl�k olarak oyuna yans�t�l�r.
		}
		
	}
	
	public void keyPressed(KeyEvent e) // y�n tu�lar�n� dinleyen dinleyici s�n�f
	{
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)  // key de�eri sa� tu�a bas�ld���nda 
		{
			dv = 1.02; // ara� h�zlanma katsay�s�
			
			if(v>2)
			{
				if(v>45)// kullan�c� �ok h�zl�ysa arabas� bu g�r�nt�yle de�i�tirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2) // ara� �ok yava�sa bu g�r�nt�y� y�kleriz
			{
				img = img_c;// orijinal duran ara� g�r�nt�s�
			}
			
		}
		else if(key == KeyEvent.VK_LEFT) 
		{
			v *= (0.70); // ara� fren yap�nca yava�lama olacak
			if(v>2)
			{
				if(v>45) // kullan�c� �ok h�zl�ysa arabas� bu g�r�nt�yle de�i�tirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2)// ara� �ok yava�sa bu g�r�nt�y� y�kleriz
			{
				img = img_c; // orijinal duran ara� g�r�nt�s�
			}
		}
		else if(key == KeyEvent.VK_UP) 
		{
			
			if(v!=0) // ara� durmam��sa y�n de�itirebilir.
			{
				v *= (0.90); // ara� y�n de�i�tirince yava�lama olacak
				dy = 7; // 5 px y�n de�i�tirir
			}
			img = img_l;
			
		}
		else if(key == KeyEvent.VK_DOWN) 
		{
			
			if(v!=0)// ara� durmam��sa y�n de�itirebilir.
			{
				v *= (0.90); // ara� y�n de�i�tirince yava�lama olacak
				dy = -7; // 5 px y�n de�i�tirir
			}
			img = img_r;
		}
		
	}
	
	public void keyRealeased(KeyEvent e) // y�n belirlendikten sonra tu�un devaml�l���n� sa�layan dinleyici.
	{

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) 
		{
			if(key == KeyEvent.VK_RIGHT)
			{
				dv=0; // sadece h�zlanma devam ediyorsa h�z katsay�s� art�t�lmaz.
			}
			
			
			if(v>2)
			{
				if(v>45) // kullan�c� �ok h�zl�ysa arabas� bu g�r�nt�yle de�i�tirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2)// ara� �ok yava�sa bu g�r�nt�y� y�kleriz
			{
				img = img_c;
			}
		}
		else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) 
		{
			dy = 0;
			if(v>2)
			{
				if(v>45) // kullan�c� �ok h�zl�ysa arabas� bu g�r�nt�yle de�i�tirilir.
				{
					img = img_Maxspeed;
				}
				else
				{
					img = img_speed;
				}
				
			}
			else if(v<2)// ara� �ok yava�sa bu g�r�nt�y� y�kleriz
			{
				img = img_c;
			}
		}
	}
	
	
	
}

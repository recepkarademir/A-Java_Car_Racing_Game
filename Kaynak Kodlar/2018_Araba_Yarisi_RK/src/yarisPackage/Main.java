package yarisPackage;

import javax.swing.JFrame;

public class Main implements Sabitler 
{
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT); // pencere boyutu ayarlanýyor. 
		frame.setResizable(false);// pencere boyutu sabit
		frame.setLocationRelativeTo(null);
		frame.add(new Yol());
		frame.setVisible(true);
		frame.setTitle("CAR RACING");
	}

}

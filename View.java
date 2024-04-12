//Len Huang 3/8/22 Assignment 4
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.Iterator;

class View extends JPanel
{
	// BufferedImage turtle_image;
	BufferedImage brick_img;
	JButton b1;
	Model model;		//member variable to access Model
	static int scrollPosX = 0;
	static int scrollPosY = 0;
	static int scrollPosW = 500;
	static int scrollPosH = 500;
	BufferedImage[] images = null;

	

	View(Controller c, Model m) //This is the View constructor, by adding Model m, you're initiallizing the model variable
	{
		model = m;		//this is passing the model object to the View constructor
		
		// this is making sure that the brick.jpg is getting loaded into memory
		try
		{
			brick_img = ImageIO.read(new File("brick.jpg")); //this loads the brick.jpg into the memory but hve not told it to draw yet
		} 
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		c.setView(this);
	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
			System.out.println(filename + " loaded.");
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

	public void paintComponent(Graphics g)//this draws the turtle from the memmory
	{
		g.setColor(new Color(230, 230, 250));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());//this re-paints the background 
		//Iterator for brick
		for(Iterator<Sprite> spriteIterator = model.sprites.iterator(); spriteIterator.hasNext();)
		{
			Sprite s = spriteIterator.next();
			s.draw(g);
		}

	}

	public void scroll(int x, int y)
	{

	}

	void removeButton()//method to remove the button using an existing pre-writen package
	{
		this.remove(b1);
		this.repaint();
	}
}

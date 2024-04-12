//Len Huang 3/8/22 Assignment 4
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;		//member variable to access View
	Model model;	//member variable to access Model
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	boolean keyW;
	boolean keyA;
	boolean keyX;
	boolean keyD;
	boolean keyE;
	boolean brickEditMode = false;
	boolean potEditMode = false;

	Controller(Model m) //initializing the Model variable (This is the parameter)
	{
		model = m;	//make sure to include this so that it is passing an object to the constructor
		Json j = Json.load("map.json");
			model.unmarshal(j);
	}

	void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
		view.removeButton();
	}

	public void mousePressed(MouseEvent e)
	{
		//switch for brick edit mode
		if (brickEditMode == true)
		{
			model.amIClickingABrick(e.getX() + View.scrollPosX, e.getY() + View.scrollPosY);
			System.out.println("its being placed");
		}

		//switch for pot edit mode
		if (potEditMode == true)
		{
			model.amIClickingAPot(e.getX() + View.scrollPosX, e.getY() + View.scrollPosY);
			System.out.println("its being placed");
		}
	}
	

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }

	public void mouseClicked(MouseEvent e) 
	{
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			

			case KeyEvent.VK_E: keyE = true; break;

			case KeyEvent.VK_ESCAPE: System.exit(0);
		}
		
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_CONTROL: model.throwBoomerang(); break;

			case KeyEvent.VK_E: keyE = false; break;
		}
		char c = e.getKeyChar();

		//brick edit key
		if(c == 'e'  || c =='E')
		{
			brickEditMode = !brickEditMode;
			System.out.println("Brick Edit mode is " + brickEditMode);
		}

		//pot edit key
		if(c == 'p'  || c =='P')
		{
			potEditMode = !potEditMode;
			System.out.println("Pot Edit mode is " + potEditMode);
		}

		//save map key
		if(c == 's'  || c =='S') {
			model.marshal().save("map.json");
			System.out.println("The map has been saved");
		}

		//load map key
		if(c == 'l' || c == 'L') {
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("loaded map.json");
		}

	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyDown)
		{
			model.link.updateImageNumDown();
			model.link.preY = model.link.y;
			model.link.y += 1 * model.link.speed;
			model.link.linkDir = 0;

			if((model.link.y + model.link.h > View.scrollPosH) && (View.scrollPosY == 0))
			{
				View.scrollPosY += View.scrollPosH;
			}
		}
		else if(keyLeft)
		{
			model.link.updateImageNumLeft();
			model.link.preX = model.link.x;
			model.link.x -= 1 * model.link.speed;
			model.link.linkDir = 1;

			if((model.link.x + model.link.w < View.scrollPosW) && (View.scrollPosX == View.scrollPosW))
			{
				model.link.x = model.link.x + model.link.w;
				View.scrollPosX -= View.scrollPosW;
			}
		}
		else if(keyUp)
		{
			model.link.updateImageNumUp();
			model.link.preY = model.link.y;
			model.link.y -= 1 * model.link.speed;
			model.link.linkDir = 2;

			if((model.link.y + model.link.h < View.scrollPosH) && (View.scrollPosY == View.scrollPosH))
			{
				model.link.y = model.link.y + model.link.h;
				View.scrollPosY -= View.scrollPosH;
			}
		}
		else if(keyRight)
		{
			model.link.updateImageNumRight();
			model.link.preX = model.link.x;
			model.link.x += 1 * model.link.speed;
			model.link.linkDir = 3;

			if((model.link.x + model.link.w > View.scrollPosW) && (View.scrollPosX == 0))
			{
				model.link.x = model.link.x - View.scrollPosW;
				View.scrollPosX += View.scrollPosW;
			}
		}
	}

}

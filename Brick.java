//Len Huang 3/8/22 Assignment 4
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Brick extends Sprite
{
    static int bw = 50;
    static int bh = 50;
    int userx;
    int usery;
    static BufferedImage img = View.loadImage("brick.jpg");

    public Brick(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = 50;
        h = 50;
        loadImage();
    }

    public boolean amIClickingOnYou(int userx, int usery)
    {
    
    if(userx >= x && userx <= x + w && usery >= y && usery <= y + h)
        return true;
    else
        return false;
    }

    @Override 
    public String toString()
    {
	    return "Brick (x,y) = (" + x + ", " + y + ")";
    }

    void loadImage()
    {
        if(img == null)
        {
            img = View.loadImage("brick.jpg");
        }
    }

    void draw(Graphics g)
    {
        g.drawImage(img, x - View.scrollPosX, y - View.scrollPosY, null);
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
	//unmarshal
	public Brick(Json obj) 
    {
		x = (int)obj.getLong("x");
		y = (int)obj.getLong("y");
        w = 50;
        h = 50;
	}

    @Override
	boolean isBrick()
	{
		return true;
	}

    boolean update()
    {
        return true;
    }
}

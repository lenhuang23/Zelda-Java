import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Pot extends Sprite
{
    static BufferedImage image;
    int speed = 10;
    int xdir = 1;
    int ydir = 1;
    // static int pw = 25;
    // static int ph = 25;
    boolean notBroken = true;
    static BufferedImage pimg = View.loadImage("pot.png");

    public Pot(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = 25;
        h = 25;
       loadImage();

    }

    @Override 
    public String toString()
    {
	    return "Pot (x,y) = (" + x + ", " + y + ")";
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
    //unmarshal
	public Pot(Json obj) 
    {
		x = (int)obj.getLong("x");
		y = (int)obj.getLong("y");
        w = 25;
        h = 25;
        loadImage();
	}

    boolean update()
    {
        return true;
    }

    @Override
	boolean isPot()
	{
		return true;
	}

    void loadImage()
    {
        if(pimg == null)
        {
            pimg = View.loadImage("pot.png");
        }
    }

    void draw(Graphics g)
    {
        g.drawImage(pimg, x - View.scrollPosX, y - View.scrollPosY, null);
    }
}
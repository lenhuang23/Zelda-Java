import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Boomerang extends Sprite
{
    int speed = 10;
    int xDir;
    int yDir;
    BufferedImage imgBoom;

    public Boomerang()
    {
        w = 8;
        h = 12;
        active = true;
        loadImage();
    }

    void collided()
    {
        active = false;
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        return ob;
    }

    boolean update()
    {
        x = x + (xDir * speed);
        y = y + (yDir * speed);
        return true;
    }

    void draw(Graphics g)
	{
		// System.out.println(imageNum);
        g.drawImage(imgBoom, x - View.scrollPosX, y - View.scrollPosY, null);
	}

    void loadImage()
    {
        if(imgBoom == null)
        {
            imgBoom = View.loadImage("boomerang1.png");
        }
    }

    @Override
	boolean isBoomerang()
	{
		return true;
	}

    // //Boomerang hits the pot
    // void bHitPot(Sprite b)
	// {
	// 	if(x + w >= b.x) //right side of Boomerang
	// 	{
	// 		return false;
	// 	}
	// 	if(x <= b.x + b.w) //left side of Boomerang
	// 	{
	// 		return false;
	// 	}
	// 	if(y <= b.y + b.h) //top of Boomerang
	// 	{
	// 		return false;
	// 	}
	// 	if(y + h >= b.y) //bottom of Boomerang
	// 	{
	// 		return false;
	// 	}
	// }
 
}

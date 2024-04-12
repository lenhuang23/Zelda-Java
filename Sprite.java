import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite
{
    int x, y, w, h;
    boolean active = false;
    abstract void draw(Graphics g);
    abstract boolean update();
    abstract Json marshal();

    boolean isPosInside(int posX, int posY)
    {
        if(posX < x || posY < y)
            return false;
        if(posX > x + w || posY > y + h)
            return false;

        return true;
    }


    boolean isLink()
    {
        return false;
    }

    boolean isBrick()
    {
        return false;
    }

    boolean isPot()
    {
        return false;
    }

    boolean isBoomerang()
    {
        return false;
    }

    @Override 
    public String toString()
    {
	    return "Sprite (x,y) = (" + x + ", " + y + "),w = " + w + ", h = " + h; 
    }
}
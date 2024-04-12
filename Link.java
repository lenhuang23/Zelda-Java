//Len Huang 3/8/22 Assignment 4
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Link extends Sprite
{
    double speed = 7.5;
    int preX;
    int preY;
    static int imageNum = 0;
    static BufferedImage[] images = null;
	int linkDir;

    public Link(int x, int y)
    {
        this.x = x;
		this.y = x;
		preX = x;
		preY = y;
		w = 74;
    	h = 80;

        if(images == null)
		{
			images = new BufferedImage[40];
			// images[0] = View.loadImage("Link01.png");
			// for(int i = 1; i < images.length; i++)
			// 	images[i] = View.loadImage("Link" + i + ".png");
			images[0] = View.loadImage("Link01.png");
			images[1] = View.loadImage("Link02.png");
			images[2] = View.loadImage("Link03.png");
			images[3] = View.loadImage("Link04.png");
			images[4] = View.loadImage("Link05.png");
            images[5] = View.loadImage("Link06.png");
			images[6] = View.loadImage("Link07.png");
			images[7] = View.loadImage("Link08.png");
			images[8] = View.loadImage("Link09.png");
			images[9] = View.loadImage("Link10.png");
            images[10] = View.loadImage("Link11.png");
			images[11] = View.loadImage("Link12.png");
			images[12] = View.loadImage("Link13.png");
			images[13] = View.loadImage("Link14.png");
            images[14] = View.loadImage("Link15.png");
			images[15] = View.loadImage("Link16.png");
			images[16] = View.loadImage("Link17.png");
            images[17] = View.loadImage("Link18.png");
			images[18] = View.loadImage("Link19.png");
			images[19] = View.loadImage("Link20.png");
            images[20] = View.loadImage("Link21.png");
			images[21] = View.loadImage("Link22.png");
			images[22] = View.loadImage("Link23.png");
			images[23] = View.loadImage("Link24.png");
            images[24] = View.loadImage("Link25.png");
			images[25] = View.loadImage("Link26.png");
			images[26] = View.loadImage("Link27.png");
            images[27] = View.loadImage("Link28.png");
			images[28] = View.loadImage("Link29.png");
			images[29] = View.loadImage("Link30.png");
            images[30] = View.loadImage("Link31.png");
			images[31] = View.loadImage("Link32.png");
			images[32] = View.loadImage("Link33.png");
			images[33] = View.loadImage("Link34.png");
            images[34] = View.loadImage("Link35.png");
			images[35] = View.loadImage("Link36.png");
			images[36] = View.loadImage("Link37.png");
            images[37] = View.loadImage("Link38.png");
			images[38] = View.loadImage("Link39.png");
			images[39] = View.loadImage("Link40.png");
		}
    }

    public void updateImageNumDown()
	{
		if(imageNum >= 9)
			imageNum = 0;
            imageNum++;
	}

    public void updateImageNumLeft()
	{
        if((imageNum <= 10) || (imageNum >= 19))
        imageNum = 10;
		imageNum++;
	}

    public void updateImageNumUp()
	{
		if((imageNum <= 20) || (imageNum >= 29))
        imageNum = 20;
		imageNum++;
	}

    public void updateImageNumRight()
	{
		if((imageNum <= 30) || (imageNum >= 39))
        imageNum = 30;
		imageNum++;
	}

    @Override 
    public String toString()
    {
	    return "Link (x,y) = (" + x + ", " + y + ")";
    }

	//Collision for brick
	void pullOut(Sprite b)
	{
		if(x + w >= b.x && preX <= b.x) //right side of Link
		{
			x = preX;
		}
		if(x <= b.x + b.w && preX >= b.x + b.w) //left side of Link
		{
			x = preX;
		}
		if(y <= b.y + b.h && preY >= b.y + b.h) //top of Link
		{
			y = preY;
		}
		if(y + h >= b.y && preY <= b.y) //bottom of Link
		{
			y = preY;
		}
	}

	//collision for pots
	void kickPot(Sprite b)
	{
		if(x + w >= b.x && preX <= b.x) //right side of Link
		{
			b.x += 5;
		}
		if(x <= b.x + b.w && preX >= b.x + b.w) //left side of Link
		{
			b.x -= 5;
		}
		if(y <= b.y + b.h && preY >= b.y + b.h) //top of Link
		{
			b.y -= 5;
		}
		if(y + h >= b.y && preY <= b.y) //bottom of Link
		{
			b.y += 5;
		}
	}

	Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    boolean update()
    {
        return true;
    }

	@Override
	boolean isLink()
	{
		return true;
	}

    void draw(Graphics g)
	{
		// System.out.println(imageNum);
		g.drawImage(images[imageNum], x - View.scrollPosX, y - View.scrollPosY, w, h, null);
	}
    
	public int getLinkDir() 
	{
		return linkDir;
	}

}
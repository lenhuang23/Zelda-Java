//Len Huang 3/8/22 Assignment 4
import java.util.ArrayList;

class Model
{
	int num = 0;
	Link link; //instance of Link
	Boomerang boomerang;
	ArrayList<Sprite> sprites; //member variable

	Model()	//default constructor
	{
		link = new Link(250, 250);
		sprites = new ArrayList<Sprite>(); //initialize
		sprites.add(link);
		boomerang = new Boomerang();
		sprites.add(boomerang);
	}

	
//update for collision detection
	public void update()
	{
		// link.update();
		// boomerang.update();
			for (int i = 1; i < sprites.size(); i++)
			{
				Sprite s = sprites.get(i);
				s.update();
				if (collisionDetection(link, sprites.get(i)))
				{
					if (sprites.get(i).isBrick())
					{
						System.out.println(link);
						System.out.println(sprites.get(i));
						link.pullOut(sprites.get(i));
					}
					if (sprites.get(i).isPot())
					{
						link.kickPot(sprites.get(i));
						//pot slide method here
					}
				}
				else if (boomCollisionDetection(boomerang, sprites.get(i)))
				{
					if (sprites.get(i).isPot())
					{
						sprites.remove(sprites.get(i));
					}
				}
			}
	}

	public void amIClickingABrick(int x, int y)
	{
		boolean found = false;
		for(int i = 0; i < sprites.size(); i++)//looping to see if brick already exist
		{
			Sprite curSprite = sprites.get(i);
			if(curSprite.isPosInside(x, y) && curSprite.isBrick())
			{	
				found = true;
				sprites.remove(i);
				break;
			}
		}
		if(!found)
		{
			sprites.add(new Brick(x - x % 50, y - y % 50));
		}
	}

	public void amIClickingAPot(int x, int y)
	{
		boolean found = false;
		for(int i = 0; i < sprites.size(); i++)//looping to see if pot already exist
		{
			Sprite curSprite = sprites.get(i);
			if(curSprite.isPosInside(x, y) && curSprite.isPot())
			{	
				found = true;
				sprites.remove(i);
				break;
			}
		}
		if(!found)
		{
			sprites.add(new Pot(x - x % 25, y - y % 25));
		}
	}

	Json marshal()
  {
      Json ob = Json.newObject();
      Json tmpList = Json.newList();
      ob.add("brick", tmpList);
	  Json tmpListP = Json.newList();
	  ob.add("pot", tmpListP);
	  Json tmpListB = Json.newList();
	  ob.add("boomerang", tmpListB);
      for(int i = 0; i < sprites.size(); i++)
	  {
		  if(sprites.get(i).isBrick())
		  {
          tmpList.add(sprites.get(i).marshal());
		  }
		  if(sprites.get(i).isPot())
		  {
          tmpListP.add(sprites.get(i).marshal());
		  }
		  if(sprites.get(i).isBoomerang())
		  {
          tmpListB.add(sprites.get(i).marshal());
		  }
	  }
	  return ob;
	//   for(int i = 0; i < sprites.size(); i++)
	//   {
    //       tmpListP.add(sprites.get(i).marshal());
      
	//   }
	//   return ob;
	  
  }
  void unmarshal(Json obj) 
  {
	  sprites = new ArrayList<Sprite>();
	  sprites.add(link);
	  Json tmpList = obj.get("brick");
	  for(int i = 0; i < tmpList.size(); i++)
	  {
		  sprites.add(new Brick(tmpList.get(i)));
	  }

	  Json tmpListP = obj.get("pot");
	  for(int i = 0; i < tmpListP.size(); i++)
	  {
		  sprites.add(new Pot(tmpListP.get(i)));
	  }
	//   Json tmpList = obj.get("brick");
	//   for(int i = 0; i < tmpList.size(); i++)
	//   {
	// 	  sprites.add(new Pot(tmpList.get(i)));
	//   }
		  
  }

  //collision between link and bricks
  boolean collisionDetection(Sprite l, Sprite b)
  {
	if(l.x + l.w < b.x) //right of Link
	{
		return false;
	}
	if(l.x > b.x + b.w) //Left of Link
	{
		return false;
	}
	if(l.y + l.h < b.y) //Bottom of Link
	{
		return false;
	}
	if(l.y > b.y  + b.h) //Top of Link
	{
		return false;
	}

	System.out.println("collision detected " + num);
	num++;
	return true;
  }

  public void throwBoomerang()
  {
	Boomerang b = new Boomerang();
	if (link.getLinkDir() == 0)//down
	{
		b.xDir = 0;
		b.yDir = 1;
		b.x = link.x + (link.w / 2);
		b.y = link.y + link.h;
	}
	else if (link.getLinkDir() == 1)//left
	{
		b.xDir = -1;
		b.yDir = 0;
		b.x = link.x;
		b.y = link.y + (link.h / 2);
	}
	else if (link.getLinkDir() == 2)//up
	{
		b.xDir = 0;
		b.yDir = -1;
		b.x = link.x + (link.w / 2);
		b.y = link.y;
	}
	else if (link.getLinkDir() == 3)//right
	{
		b.xDir = 1;
		b.yDir = 0;
		b.x = link.x + link.w;
		b.y = link.y + (link.h / 2);
	}
	sprites.add(b);
}

// //collision between boomerang and pot
	boolean boomCollisionDetection(Sprite l, Sprite b)
	{
	if(l.x + l.w < b.x) //right of boomerang
	{
		return false;
	}
	if(l.x > b.x + b.w) //Left of Link
	{
		return false;
	}
	if(l.y + l.h < b.y) //Bottom of Link
	{
		return false;
	}
	if(l.y > b.y  + b.h) //Top of Link
	{
		return false;
	}
	return true;
	}
}
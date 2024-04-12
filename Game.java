//Len Huang 3/8/22 Assignment 4
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Controller controller;	//member variable to access Controller
	Model model;			//member variable to access Model
	View view;				//member variable to access View

	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);//passing the controller and model object into the constructor
		this.setTitle("A4 - Zelda");
		this.setSize(500, 525);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
	}

	public void run()
	{
		while(true)
			{
				controller.update();
				model.update();	//Indirectly calls the Model.update
				view.repaint(); // Indirectly calls View.paintComponent
				Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 50 miliseconds
				try
				{
					Thread.sleep(40);
				}	
				catch(Exception e) 
				{
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
public static void main(String[] args)
{
	Game g = new Game();
	g.run();
}
}

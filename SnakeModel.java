import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

/*
Algorithm for the snake: Use dynamic array to make the snake model,
each part of the snake body all follows the snake head.
(which in this case is the first element.)
Every time the method repaint() is called, count the length of dynamic array.
Every time the food gets eaten, one element is added in front of the snake head,
And then all the body parts follows the movement.
 */

public class SnakeModel extends Canvas implements ActionListener
{
    private snakePoint[] snakePoint; //Dynamic array for the snake
    private static Timer timer; //timer
    private static int score = 0; //score
    private int Dx, Dy; //the coordinate of the snake's head
    private int Px, Py; //the coordinate of the food
    static final int LEFT = 1;
    static final int UP = 2;
    static final int DOWN = 4;
    static final int RIGHT = 3; //assign values to four directions
    int direction = 4; //set the initial derection downward
    boolean isFrist = true; //control the position of the place that food first appears
    boolean isOne = true; //to make sure that the keyboard can only be pressed once every time the snake gets updated,
    //no faster than this
    boolean food; //to make sure that only update the position of the food when it gets eaten

    class snakePoint //a class for snake body (one snake may have more than one bodies)
    {
		int x, y;
	
		public snakePoint (int x, int y)
		{
		    this.x = x;
		    this.y = y;
		}
	
		snakePoint (snakePoint p)
		{
		    this.x = p.x;
		    this.y = p.y;
		}
    } 


    public SnakeModel (int delay)  //used to construct the model of the snake
    {
		this.snakePoint = new snakePoint [300]; //to store snake's head
		snakePoint [1] = new snakePoint (100, 100); //draw snake's head
	
		//Use the Timer
		timer = new Timer (delay, this);
		timer.start ();
    } 


    public static int getScore ()  //obtain the score
    {
    		return score;
    } 

    
    public void paint (Graphics g)
    {

		// randomize the location of the food
		creatFood ();
	
		// make the dynamic array move once automatically after every movement
		if (score != 0)
		{
		    for (int i = score + 2 ; i > 1 ; i--)
		    {
		    		snakePoint [i] = new snakePoint (snakePoint [i - 1]);
		    }
		} 
		switch (direction) //determine the direction of the snake's head according to keyBoard input
		{
		    case UP:
			snakePoint [1].y -= 10;
			break;
		    case DOWN:
			snakePoint [1].y += 10;
			break;
		    case LEFT:
			snakePoint [1].x -= 10;
			break;
		    case RIGHT:
			snakePoint [1].x += 10;
			break;
		} 
	
		//food gets eaten
		/*Important note: the coordinate of the food cannot be the same thing as the snake's head 
		 * so the snake will have to move to get the food. */
		if (snakePoint [1].x <= Px + 7 && snakePoint [1].x >= Px - 5
			&& snakePoint [1].y <= Py + 7 && snakePoint [1].y >= Py - 5)
		{
		    score++; //score gets one more when the food gets eaten
		    food = true; //update the food
		    SnakeMain.re (); //update the score
		    timer.setDelay ((timer.getDelay () - 4) <= 0 ? 20
			    :
		    timer.getDelay () - 4); //the speed of the snake gets faster every time after the food gets eaten
	
		    //the length gets increased at the snake's head every time after the food gets eaten
		    for (int j = score + 1 ; j > 1 ; j--)
		    {
		    		snakePoint [j] = new snakePoint (snakePoint [j - 1]);
		    } 
	
		    // move one step towards the movement direction
		    switch (direction) //detect direction
		    {
			case UP:
			    snakePoint [1].y -= 10;
			    break;
			case DOWN:
			    snakePoint [1].y += 10;
			    break;
			case LEFT:
			    snakePoint [1].x -= 10;
			    break;
			case RIGHT:
			    snakePoint [1].x += 10;
			    break;
		    } 
		} 
	
		g.setColor (Color.RED); //set the color of the food red
		g.fillRect (Px, Py, 10, 10); // draw the food in the output window
	
		g.setColor (Color.BLACK); //set the color of the snake to be black
		for (int i = 1 ; i <= score + 1 ; i++)
		{
		    g.fillRect (snakePoint [i].x, snakePoint [i].y, 10, 10);
		} //draw the snake from based on the dynamic array
    } //end of paint method


    private void creatFood ()
    {
		if (isFrist)
		{
		    Px = rand (380); //get randomized x value
		    Py = rand (330); //get randomized y value, which makes the food not overlap the snake
		    isFrist = false;
		}
		//coordinate of the next food
		if (food)
		{
		    Px = rand (380);
		    Py = rand (330); 
		    food = false;
		}
    }


    private int rand (int i)
    {
		Random r = new Random (); //randomize
		if (i == 380) //produce the y-coordinate of the food
		{
		    while (true)
		    {
			int n = (r.nextInt (37) + 1) * 10;
			for (int j = 1 ; j <= score + 1 ; j++) //check that the food is not on the snake
			    //need to check every time and same thing applies to the program bellow
			    {
				if (snakePoint [j].x != n && j == (score + 1))
				{
				    return n;
				}
			    } 
		    } 
		} 
		if (i == 330) //produce the x-coordinate of the food, same thing as above
		{
		    while (true)
		    {
				int n = (r.nextInt (32) + 1) * 10;
				for (int j = 1 ; j <= score + 1 ; j++)
				{
				    if (snakePoint [j].y != n && j == (score + 1))
				    {
				    		return n;
				    } 
				}
		    } 
		} 
		return 0;
    } 


    public void actionPerformed (ActionEvent e)
    {
	isOver (); //determine whether the game should be finished or not
	repaint ();
	isOne = true;
	// System.out.println(snakePoint[1].x+"  "+snakePoint[1].y);
    } //end of actionPerformed method


    private void isOver ()
    {
		if (isCondition ())
		{
		    timer.stop ();
		    SnakeMain.gameOver (); //stop the timer and pop out the Game Over window
		} 
    } 


    private boolean isCondition ()  //check all the conditions that will end the game
    {
		//out of bound (go into the wall)
		if (snakePoint [1].x <= 0 || snakePoint [1].x >= 380 || snakePoint [1].y <= 0 || snakePoint [1].y >= 330)
		{
		    return true;
		}
		//snake body gets eaten by the head
		for (int i = 2 ; i <= score + 1 ; i++)
		{
		    if (snakePoint [1].x == snakePoint [i].x && snakePoint [1].y == snakePoint [i].y)
		    {
		    		return true;
		    } 
		}
		return false;
    } 


    public void changeDirection (int newDirection)  //change directions
    {
		if (direction % 2 != newDirection % 2 && isOne) //check that the new direction cannot be opposite to the original direction
		{
		    direction = newDirection;
		    isOne = false;
		} 
    }


    public static void stop ()
    {
    		timer.stop ();
    } 


    public static void start ()
    {
    		timer.start ();
    } 


    public void reStart ()  //reset all data
    {
		score = 0;
		direction = 4;
		isFrist = true;
		isOne = true;
		food = false;
		snakePoint=null;
		snakePoint [1].x = 100;
		snakePoint [1].y = 100;
		creatFood ();
		timer.start ();
    } 
} 

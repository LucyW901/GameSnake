//This is a class for KeyBoard Controlling 
//almost all settings are kept as default (that is as known)
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener //the method to capture keyboard input in a really short amount of time
{
    SnakeModel snake = null;
    public Control (SnakeModel snake)
    {
    		this.snake = snake;
    }


    public void keyPressed (KeyEvent e)
    {
		if (e.getKeyCode () == KeyEvent.VK_UP)
		{
		    snake.changeDirection (SnakeModel.UP);
		}
		if (e.getKeyCode () == KeyEvent.VK_DOWN) //when downward key is captured, move downward
		{
		    snake.changeDirection (SnakeModel.DOWN);
		}
		if (e.getKeyCode () == KeyEvent.VK_LEFT) //when left key is captured, move to the left
		{
		    snake.changeDirection (SnakeModel.LEFT);
		}
		if (e.getKeyCode () == KeyEvent.VK_UP) //when upward key is captured, move upward
		{
		    snake.changeDirection (SnakeModel.UP);
		}
		if (e.getKeyCode () == KeyEvent.VK_RIGHT) //when right key is captured, move to the right
		{
		    snake.changeDirection (SnakeModel.RIGHT);
		}
		if (e.getKeyCode () == KeyEvent.VK_R) //when 'R' key is captured, reset
		{
		    snake.reStart ();
		}
    }


    public void keyTyped (KeyEvent e){}


    public void keyReleased (KeyEvent e) {}
    
} 

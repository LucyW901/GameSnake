// The "Rules" class using applet 
import java.applet.*;

import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JApplet;

public class Rules extends Applet
{
	private static final long serialVersionUID = 1L;
	Button start;
    
     public void init ()
    {
		start = new Button ("Start");
		add (start);
		start.setBounds(700,50,100,100);
		start.setBackground(Color.CYAN);
    } 


    public void paint (Graphics g)
    {
		g.setFont (new Font ("Arial", Font.BOLD, 18));
		g.drawString ("Introduction to the GAME Greedy Snake", 5, 20);
		g.setFont (new Font ("Arial", Font.PLAIN, 12));
		g.drawString ("Rules:", 5, 40);
		g.drawString ("1.The game starts, use the arrows on the keyboard to control the snake in black.", 5, 60);
		g.drawString ("2.The snake can freely move within the grey area.", 5, 80);
		g.drawString ("3.To score points, the black snake have to eat the red-dot food to form a longer snake.", 5, 100);
		g.drawString ("4.IF the snake moves LEFT or RIGHT, Up or Down, a sudden turn in the opposite direction on the same line is INEFFECTIVE.", 5, 120);
		g.drawString ("5.As the score increase, the snake will move faster. ", 5, 140);
		g.drawString ("6.The game is over when the snake reaches the edges of the grey area OR when the head of the snake touches the tail of the snake. ", 5, 160);
		g.drawString ("7.To Restart the game, press R key from the keyboard. ", 5, 180);
    }

    
    public boolean action (Event e, Object o)  //make the game window appears to when start button is clicked
    {
		if (e.target instanceof Button)
		{
		    if (e.target == start)
		    {
				new SnakeMain ();
				return true;
		    }
		}
		return true;
    }
}



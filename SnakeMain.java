//This is the main file of the project
//This is a game project for the snake game 
//This project is done in May 2016

import java.awt.*;

import java.applet.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SnakeMain extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static JTextField scoreField; //text field for score recording
    private JButton pause, resume; //create the buttons to control when to start or stop the game
    Graphics gSnake;
    boolean startG = false;
    
    public static void main (String[] args)
    {
    		JFrame frame = new JFrame ("Introduction for the game");
    		Rules rule = new Rules ();
    		frame.add(rule);
		frame.pack();
		frame.setSize(800,250);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		rule.init();
    } 
    

    public SnakeMain ()
    {
    		//frame.setVisble(false);
    		//initialize all the frame settings from JFrame
    		this.setTitle("Greedy Snake");
		this.setBounds (300, 100, 400, 400);
		this.setDefaultCloseOperation (EXIT_ON_CLOSE);
		this.setResizable (false);
	
		//initialize all the settings of the panel
		JPanel p = new JPanel ();
		
		this.getContentPane ().add (p, "North");
		p.add (new JLabel ("Score:"));
		scoreField = new JTextField ("0", 5);
		scoreField.setEditable (false);
	
		//initialization of the Buttons
		pause = new JButton ("pause");
		resume = new JButton ("resume");
	
		//override the default focusability
		scoreField.setFocusable (false);
		pause.setFocusable (false);
		resume.setFocusable (false);
	
		//add buttons and score text field to the panel
		p.add (scoreField);
		p.add (pause);
		p.add (resume);
	
		SnakeModel snake = new SnakeModel (200); // set the time of updating
		this.getContentPane ().add (snake); //add snake to the output window
		//Range of the snake, note that it must satisfies: 0<=x<=380 0<=y<=330
		Control control = new Control (snake); // Control the snake with Control class
	
		//capture actions
		pause.addActionListener (this);
		resume.addActionListener (this);
		this.addKeyListener (control);
	
		//display
		this.setVisible (true);
    } 


    public static void re ()  //update the "score" field
    {
    		scoreField.setText ("" + SnakeModel.getScore ());
    } 


    public static void gameOver ()  //Game over window
    {
    		JOptionPane.showMessageDialog (null, "Game Over!!! You will do better next time!");
    } 


    public void actionPerformed (ActionEvent e)
    {
		if (e.getSource () == pause)
		{
		    SnakeModel.stop ();
		} 
		if (e.getSource () == resume)
		{
		    SnakeModel.start ();
		} 
    } 
} 
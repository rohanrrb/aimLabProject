
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/*
 * AIM TRAINER PROJECT - ROHAN BOPARDIKAR
 * HONORS CONTRACT, LAST UPDATED 11/29
 * Aim Trainer with randomly spawning targets and a timer with a score. 
 */

public class aimLab {
	
	static int score = 0;//score variable
	static boolean isTarget = false; //is there a target variable
	static int countdown = 26; //starting time + 1
	static boolean isGame = true; //has game started

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		JFrame frame = new JFrame ("Game"); //creates JFrame
		JPanel panel = new JPanel (); //creates JPanel
		
		frame.getContentPane(); //allows components to be added to frame
		

		JButton start = new JButton("CLICK TO START");//create start button
		Dimension startSize = start.getPreferredSize();//accesses size of the start button
		
		start.setBounds(300,200,startSize.width,startSize.height); //sets position of start button
		start.setSize(200,100); //sets size of start position
		panel.add(start); //adds start button to panel
		
		start.addActionListener(new ActionListener() {//on start button click
			@Override
			public void actionPerformed(ActionEvent e) { //checks for click
				
				isGame = true; //sets game state to true

                panel.remove(start); //remove start button
                panel.revalidate(); //re-initialize panel
                panel.repaint(); //re-initialize panel
                
                //creates score label and adds to panel
                JLabel scoreLabel = new JLabel("Score: " + score); 
        		panel.add(scoreLabel);
        		scoreLabel.setBounds(350, 625, 200, 200);
        		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        	
        		//creates timer label and adds to panel
        		JLabel timerLabel = new JLabel("" + countdown);
        		panel.add(timerLabel);
        		timerLabel.setBounds(350,600,200,200);
        		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        		
                //calls spawnTarget method
                spawnTarget(panel, score, scoreLabel, timerLabel); //call 
                
                //countdown timer, increments each second
                Timer t = new Timer();
        		t.schedule(new TimerTask() {
        		    @Override
        		    public void run() {
        		       countdown--;
        		       timerLabel.setText("" + countdown);//updates time label
        		       
        		       if (countdown < 1) {
        		    	   isGame = false; //sets game state to false when timer finishes
        		       }
        		       
        		    }
        		}, 0, 1000); //timer increment in milliseconds (1000 -> 1 second)
               
                
            }
		});
		
		//initialization of panel
	
		panel.setLayout(null); //no layout
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //what to do when closed
	    frame.add(panel); //adds panel to the frame
	    frame.setSize(800, 800); //size of the frame
	    frame.setVisible(true); 

	}
	
	//random number function for x and y for spawnTarget()
	public static int randomBound(int min, int max) {
		int randomBound = (int)Math.floor(Math.random()*(max-min+1)+min); 
		return randomBound;

	}
	
	/**
	 * Spawns target at a random location and handles onClick
	 * 
	 * @param panel: Jpanel to spawn the targets on
	 * @param score
	 * @param scoreLabel: visual for score
	 * @param timerLabel: visual for timer
	 */
	private static void spawnTarget(JPanel panel, int score, JLabel scoreLabel, JLabel timerLabel){
		isTarget = true; 
		
		//spawn target button at random location 
		JButton button = new JButton ("click"); //creates button
		Dimension size = button.getPreferredSize(); //gives button dimensions
		button.setBounds(randomBound(100,600), randomBound(100,600), size.width, size.height); //sets button location, bounds = location
		button.setSize(80, 80); //sets size of button
		panel.add(button); //adds button to panel
		
		button.addActionListener(new ActionListener() {//add actionListener to button created

			//on target button click: 
			@Override
			public void actionPerformed(ActionEvent e) { //checks for click
				
				isTarget = false;//updates isTarget
                
				//clear screen
                panel.remove(button);
                panel.revalidate();
                panel.repaint();
                

                if (isGame) { //if time is left spawn a new target and update score
                	addScore(scoreLabel);
                    spawnTarget(panel, score, scoreLabel, timerLabel);
        		}
                else { //if time is up clear screen and display scoreLabel, but bigger
                    
                	//clear panel
                    panel.remove(button);
        			panel.remove(timerLabel);
        			
        			//re-initialize
                    panel.revalidate();
                    panel.repaint();
                    
                    //show final score
            		scoreLabel.setBounds(300, 325, 200, 200);
            		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 40));
                } 
            }
			
		});
	}
	
	/**
	 * Updates score value 
	 * @param scoreLabel
	 */
	public static void addScore(JLabel scoreLabel) {
		score++;//increments score
		scoreLabel.setText("Score: " + score); //Updates score label
		

	}

	
}
	
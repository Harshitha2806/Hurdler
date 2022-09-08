import javax.swing.plaf.TreeUI;

import kareltherobot.*;

public class JumpThoseHurdles {
	// This is the Robot we will use to jump the hurdles
	Robot hurdler = new Robot(1,1,Directions.East, 0);

	int tallest = 0;
	int smallest = 0;
	int amount = 0;
	int gap = 0;
	int largeGap = 0;
	
	
	public static void main(String[] args) {
		new JumpThoseHurdles().start();
	}

	public void start() {
		loadWorld();
		
		
		var times = 0;
		while (times<5) 
		{
			int w = findHurdle();
			if (w == -1)
			{
				break;
			}
			int h = climbHurdle();
			clearHurdle();

			System.out.println( "the tallest hurdle is " + tallest + " units high");
			System.out.println("the shortest hurdle is " + smallest + " units high");
			System.out.println("There are " + amount + " hurdlers");
			System.out.println("The largest gap between hurdles is " + largeGap + " units");
			
		}
	
		
	}

	/**
	 * This method assumes the Robot is named hurdler and is facing East
	 * This moves hurdler to the next wall (hurdle). It returns the number
	 * of moves it took to get to the hurdle
	 */
	private int findHurdle() {

		
		while(hurdler.frontIsClear())
		{
			if (hurdler.nextToABeeper())
			{
				return -1;
			}
			hurdler.move();
			gap++;
			

		}

		if (gap > largeGap)
		{
			largeGap = gap; 
		}

		
		return gap;
	}
	/**
	 * This method assumes the Robot is named hurdler, is facing East and
	 * is at the base of the hurdle. 
	 * This moves the Robot to the top of the hurdle so that it can clear
	 * the wall.  
	 * @return The number of steps to get above the hurdle (height)
	 */
	private int climbHurdle() {

		int height = 0;
		
		while(!hurdler.frontIsClear())
		{
			hurdler.turnLeft();
			hurdler.move();
			height++;
			hurdler.turnLeft();
			hurdler.turnLeft();
			hurdler.turnLeft();

			if(height > tallest)
		{
			tallest = height;
		} 

		if ( height < tallest)
		{
			smallest = height;
		}



		}
		
		return height;
		


		

			
		}
		
	

		

	/** 
	 * Moves the Robot (hurdler) over the wall and moves it to the ground so 
	 * that the Robot has its back to the hurdle and is facing the next one.
	 */
	private void clearHurdle() {
	//TODO Auto-generated method stub

		
		hurdler.move();
		hurdler.turnLeft();
		hurdler.turnLeft();
		hurdler.turnLeft();
	
	
	while(hurdler.frontIsClear())
	{
		hurdler.move();
		
	}
	amount++;
	
	hurdler.turnLeft();

	
		
	}

	private void loadWorld() {
		// line below "hardcodes" this to use one specific world
		// it would be better to ask the user...
		String worldName = "worldb.wld";
		World.readWorld(worldName);
		World.setVisible(true);
		World.setDelay(5);
	}

	
		
		
		


		
	
}

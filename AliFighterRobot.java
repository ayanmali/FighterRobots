package robotwar;

import becker.robots.*;
import java.awt.Color;
import java.lang.Math;

/**
 * Fighter robot that can attack others in an arena.
 * @author ayan_
 * @version 01/24/2022
 *
 */

public class AliFighterRobot extends FighterRobot {
	// constants
	private static final int ATK = 5;
	private static final int DEF = 3;
	private static final int NM = 2;
	private static final int MOVES_ENERGY_COST = 5;
	private static final int LOW_HEALTH = 10;
	private static final int WIDTH = 20;
	private static final int HEIGHT = 12;
	private static final int NUM_PLAYERS = BattleManagerTest.NUM_PLAYERS;
	
	// instance variables
	private int health;
	private int id;
	private int[] numWins;
	private int[] numLosses;
	private int[] numTies;
	
	// Array to store a NewOppData object for each robot
	private AliOppData [] newData = new AliOppData[NUM_PLAYERS];
	
	/**
	 * Constructor method that sets up information for the robot
	 * @param c - the city the robot is in
	 * @param ave - the avenue the robot is on
	 * @param str - the street the robot is on
	 * @param d - the direction the robot is facing initially
	 * @param id - the robot's ID number
	 * @param health - the robot's initial health
	 */
	public AliFighterRobot(City c, int ave, int str, Direction d, int id, int health) {
		super(c, ave, str, d, id, ATK, DEF, NM);
		this.health = health;
		this.id = id;
		
		// Creates the label and gives the robot its colour
		this.setLabel();
		
		// Sets up arrays to store each robot's fight record
		this.numWins = new int[NUM_PLAYERS];
		this.numLosses = new int[NUM_PLAYERS];
		this.numTies = new int[NUM_PLAYERS];
        
	}
	
	/**
	 * Attaches a label to the robot displaying its ID and health. Turns the robot's colour to black once its health reaches zero.
	 */
	public void setLabel() {
		// Sets colour to black once robot is dead
		this.setColor(new Color(0, 0, 0));
		
		// Colour remains as long as robot is alive
		if (this.health > 0) {
			this.setColor(new Color(255, 0, 0));
		}
		
		// Displays ID and current health
		this.setLabel(this.id + " " + this.health);
		
	}

	@Override
	/**
	 * Moves the robot to the designated location once the turnRequest has been validated.
	 * 
	 */
	public void goToLocation(int a, int s) {
		// TODO Auto-generated method stub
		
		// If the street is less than the desired street, go north
		if (this.getStreet() > s) {
			this.goNorth(s);

		}
		
		// If the street is greater than the desired street, go south
		else if (this.getStreet() < s) {
			this.goSouth(s);

		}

		// If the avenue is less than the desired avenue, go east
		if (this.getAvenue() < a) {
			this.goEast(a);
		}

		// If the avenue is less than the desired avenue, go west
		else if (this.getAvenue() > a) {
			this.goWest(a);
		}

	}
	/**
	 * Turns the robot so that it faces west and then moves it accordingly.
	 * @param a - the avenue to go to
	 */
	private void goWest(int a) {

		// TODO Auto-generated method stub
		if (this.getDirection() == Direction.EAST) {
			this.turnAround();
		}

		if (this.getDirection() == Direction.NORTH) {
			this.turnLeft();
		}

		if (this.getDirection() == Direction.SOUTH) {
			this.turnRight();
		}

		while (this.getAvenue() != a) {
			this.move();
		}
		
	}
	
	/**
	 * Turns the robot so that it faces east and then moves it accordingly.
	 * @param a - the avenue to go to
	 */
	
	private void goEast(int a) {
		// TODO Auto-generated method stub
		if (this.getDirection() == Direction.WEST) {
			this.turnAround();
		}

		if (this.getDirection() == Direction.NORTH) {
			this.turnRight();
		}

		if (this.getDirection() == Direction.SOUTH) {
			this.turnLeft();
		}

		while (this.getAvenue() != a) {
			this.move();
		}

	}

	/**
	 * Turns the robot so that it faces south and then moves it accordingly.
	 * @param s - the street to go to
	 */
	private void goSouth(int s) {
		// TODO Auto-generated method stub
		if (this.getDirection() == Direction.NORTH) {
			this.turnAround();
		}

		if (this.getDirection() == Direction.EAST) {
			this.turnRight();
		}

		if (this.getDirection() == Direction.WEST) {
			this.turnLeft();
		}

		while (this.getStreet() != s) {
			this.move();
		}

	}

	/**
	 * Turns the robot so that it faces north and then moves it accordingly.
	 * @param s - the street to go to
	 */
	private void goNorth(int s) {
		// TODO Auto-generated method stub
		if (this.getDirection() == Direction.SOUTH) {
			this.turnAround();
		}

		if (this.getDirection() == Direction.EAST) {
			this.turnLeft();
		}

		if (this.getDirection() == Direction.WEST) {
			this.turnRight();
		}

		while (this.getStreet() != s) {
			this.move();
		}
		
	}
	/**
	 * Decides whether to go after other robots or avoid them on the current turn
	 * @param data - the OppData array to check the distance to ensure no robots are nearby while the health is also low
	 * @param target - the robot with the highest preference on the current turn
	 * @return - whether to be offensive or defensive on this turn
	 * 
	 */
	private boolean determineStrategy(OppData[] data, AliOppData target) {
		boolean offensive;
		if (this.health <= LOW_HEALTH) {
			// defensive strategy
			offensive = false;
		}
		
		else {
			// offensive strategy
			offensive = true;
		}
		
		// returns a true or false value to represent the decision for the current turn
		return offensive;
	}
	
	/**
	 * Sets up the newData array using information from data, and adds the fight record information to each instance's variables
	 * @param data - the OppData array to take in values from
	 * @param newData - the NewOppData array to hold information from data as well as fight record
	 * 
	 */
	private void setupData(OppData[] data, AliOppData[] newData) {
		for (int i = 0; i < data.length; i++) {
			newData[i] = new AliOppData(data[i].getID(), data[i].getAvenue(), data[i].getStreet(), data[i].getHealth());
			newData[i].setWins(numWins[i]);
			newData[i].setLosses(numLosses[i]);
			newData[i].setTies(numTies[i]);
		}
	}

	@Override
	/**
	 * Decides what to do on the robot's turn.
	 * param energy - the robot's energy level at the start of the turn
	 * param data - the array that holds information for every robot
	 * 
	 */
	public TurnRequest takeTurn(int energy, OppData[] data) {
		// TODO Auto-generated method stub
		// How much distance to travel in each direction
		int ave = 0;
		int str = 0;
		
		// The ID of the robot that will be fought this turn
		int fightID = -1;
		
		// Decides whether to go after other robots or avoid them on the current turn
		//boolean offensive;
		
		this.setupData(data, newData);
		
		// Updates the robot's health
		this.health = newData[this.id].getHealth();

		// Updates the robot's label
		this.setLabel();

		// Represents the robot that is going to be targeted on the current turn
		AliOppData target = this.findTargetRobot(newData);
		System.out.println("Target is Robot # " + target.getID());

		// If the robot's health is safe,
		if (determineStrategy(data, target)) {
			System.out.println("offensive");
			// If there is a robot on top of this robot, it will be attacked
			if (this.getAvenue() == target.getAvenue() && this.getStreet() == target.getStreet()) {
				fightID = target.getID();
			}
			
			// As long as the energy is sufficient,
			else if (energy > NM * MOVES_ENERGY_COST) {
				// If avenues are the same
				if (this.getAvenue() == target.getAvenue()) {
					// If the streets are within the robot's number of moves
					if (Math.abs(this.getStreet() - target.getStreet()) == 1 || Math.abs(this.getStreet() - target.getStreet()) == 2) {
						fightID = target.getID();
						str = target.getStreet() - this.getStreet();
					}
					
					// If the avenues are beyond range, the robot will move as close as it can
					
					else if (this.getStreet() - target.getStreet() > NM) {
						str = -NM;
					}

					else if (this.getStreet() - target.getStreet() < -NM) {
						str = NM;
					}

				}
				
				// If the streets are the same
				else if (this.getStreet() == target.getStreet()) {
					
					// If the avenues are within the robot's number of moves
					if (Math.abs(this.getAvenue() - target.getAvenue()) == 1 || Math.abs(this.getAvenue() - target.getAvenue()) == 2) {
						ave = target.getAvenue() - this.getAvenue();
						fightID = target.getID();
					}
					
					// If the avenues are beyond range, the robot will move as close as it can
					
					else if(this.getAvenue() - target.getAvenue() > NM) {
						ave = -NM;
					}

					else if (this.getAvenue() - target.getAvenue() < -NM) {
						ave = NM;
					}

				}
				
				// If the avenues and streets are both different
				else if (this.getStreet() != target.getStreet() && this.getAvenue() != target.getAvenue()) {
					// if the distance from the avenues is less than that of the streets
					if (Math.abs(this.getAvenue() - target.getAvenue()) < Math.abs(this.getStreet() - target.getStreet())) {
						
						// If the avenues are 1 space away
						if (Math.abs(this.getAvenue() - target.getAvenue()) == 1) {
							
							// Goes to the targets avenue
							ave = target.getAvenue() - this.getAvenue();
							
							// Determines which street to go to
							
							if (this.getStreet() > target.getStreet()) {
								str = -1;
							}

							else if (this.getStreet() < target.getStreet()) {
								str = 1;
							}

						}
						
						// if the avenues are more than 1 space away
						else if (Math.abs(this.getAvenue() - target.getAvenue()) > 1) {
							
							// Move as close as possible to the target's avenue
							
							if (this.getAvenue() > target.getAvenue()) {
								ave = -NM;
							}

							else if (this.getAvenue() < target.getAvenue()) {
								ave = NM;
							}

						}

					}
					
					// if the distance between the streets is less than that of the avenues
					else if (Math.abs(this.getAvenue() - target.getAvenue()) > Math.abs(this.getStreet() - target.getStreet())) {
						
						// If the streets are 1 space away
						if (Math.abs(this.getStreet() - target.getStreet()) == 1) {
							
							// Goes to the targets street
							str = target.getStreet() - this.getStreet();
							
							// Determines which avenue to go to
							
							if (this.getAvenue() > target.getAvenue()) {
								ave = -1;
							}

							else if (this.getAvenue() < target.getAvenue()) {
								ave = 1;
							}
							
						}
						
						// If the streets are more than 1 space away
						else if (Math.abs(this.getStreet() - target.getStreet()) > 1) {
							
							// Move as close as possible to the target's street
							if (this.getStreet() > target.getStreet()) {
								str = -NM;
							}

							else if (this.getStreet() < target.getStreet()) {
								str = NM;
							}

						}

					}
					
					// If the distances between the avenues and streets are the same
					else if (Math.abs(this.getAvenue() - target.getAvenue()) == Math.abs(this.getStreet() - target.getStreet())) {
						
						// If the robots are within range 
						if (this.distanceToRobot(data, target.getID()) == 2) {
							// Goes to the target's location and will fight them
							ave = target.getAvenue() - this.getAvenue();
							str = target.getStreet() - this.getStreet();
							fightID = target.getID();

						}
						
						// Determines which avenue to go
						
						else if (this.getAvenue() < target.getAvenue()) {
							ave = NM;

						}

						else if (this.getAvenue() > target.getAvenue()) {
							ave = -NM;

						}

					}

				}

			}
			
			// Prints if the robot has low energy
			else if (energy < NM * MOVES_ENERGY_COST) {
				System.out.println("Robot #" + this.id + " has low energy");
			}
			
		}
		
		// if the robots health is low
		else {
			System.out.println("defensive");
			// moves if there is enough energy
			if (energy > NM * MOVES_ENERGY_COST) {
				
				// if the avenues are the same
				if (this.getAvenue() == target.getAvenue()) {

					if (this.getStreet() > target.getStreet()) {
						if (this.getStreet() == HEIGHT - 1) {
							
						}
						
						else if (this.getStreet() == HEIGHT - 2) {
							str = NM-1;
						}
						
						else {
							str = NM;
						}
												
					}

					else if (this.getStreet() < target.getStreet()) {
						
						if (this.getStreet() == 0) {
							
						}
						
						else if (this.getStreet() == 1) {
							str = -(NM-1);
						}
						
						else {
							str = NM;
						}
						
						//str = -NM;
						
					}

				}
				
				// if the streets are the same
				else if (this.getStreet() == target.getStreet()) {

					if(this.getAvenue() > target.getAvenue()) {
						ave = NM;
					}

					else if (this.getAvenue() < target.getAvenue()) {
						ave = -NM;
					}

				}

				// if the avenues and streets are different
				else if (this.getStreet() != target.getStreet() && this.getAvenue() != target.getAvenue()) {
					
					// if the distances between the streets is greater than that of the avenues
					if (Math.abs(this.getAvenue() - target.getAvenue()) < Math.abs(this.getStreet() - target.getStreet())) {
						
						// Determines which street to go to
						
						if (this.getStreet() > target.getStreet()) {
							str = NM;
						}

						else if (this.getStreet() < target.getStreet()) {
							str = -NM;
						}

					}

					// if the distances between the avenues is greater than that of the streets
					else if (Math.abs(this.getAvenue() - target.getAvenue()) > Math.abs(this.getStreet() - target.getStreet())) {
						
						// Determines which avenue to go to
						if (this.getAvenue() > target.getAvenue()) {
							ave = NM;
						}

						else if (this.getAvenue() < target.getAvenue()) {
							ave = -NM;
						}

					}
					
					// if the distances between the avenues and streets are the same
					else if (Math.abs(this.getAvenue() - target.getAvenue()) == Math.abs(this.getStreet() - target.getStreet())) {
						
						// Determines the avenue to go to
						if (this.getAvenue() < target.getAvenue()) {
							ave = 1;
						}

						else if (this.getAvenue() > target.getAvenue()) {
							ave = -1;
						}

						// Determines the street to go to
						if (this.getStreet() < target.getStreet()) {
							str = -1;
						}

						else if (this.getStreet() > target.getStreet()) {
							str = 1;
						}
						
					}
					
				}
				
			}
			
			// Ensures that the robot will not initiate a fight against another robot
			fightID = -1;
			// If the robot happens to land on an intersection with another robot on it, initiate a fight
			/*
			for (int i = 0; i < newData.length; i++) {
				if (this.getAvenue() == newData[i].getAvenue() && this.getStreet() == newData[i].getStreet()) {
					fightID = newData[i].getID();
				}
				
			}
			*/
			
		}
		
		// returns the turnRequest to move to the desired location and fight the appropriate target
		return new TurnRequest(this.getAvenue()+ave, this.getStreet()+str, fightID, ATK);
	
	}

	/**
	 * Decides which robot is the best to go after on the current turn
	 * @param newData - the array to check to gather information to make the decision
	 * @return - the index of the robot with the highest preference to go after, not including those that are dead or the robot itself.
	 * 
	 */
	private AliOppData findTargetRobot(AliOppData[] newData) {
		// Used to find which index has the highest preference and is valid
		int counter = 0;
		AliOppData tempData;
		
		// Selection sort to sort newData from lowest to highest preference
		for (int i = 0; i < newData.length-1; i++) {
			int num = i;
			// Nested loop starts one index after the "sorted" portion
			for (int j = i + 1; j < newData.length; j++) {
				// Compares preferences between indices
				if (this.distanceToRobot(newData, j) + newData[j].getHealth() + newData[j].getLosses() - newData[j].getWins() - newData[j].getTies() 
						< this.distanceToRobot(newData, num) + newData[num].getHealth() + newData[j].getLosses() - newData[j].getWins() - newData[j].getTies()) {
					num = j;
				}
			}
			
			// Swaps the index of lowest preference with the first index of the "unsorted" portion
			tempData = newData[i];

			newData[i] = newData[num];
			newData[num] = tempData; 
		}
		
		// Checks to make sure a valid index is being taken
		while (newData[counter].getHealth() <= 0 || newData[counter].getID() == this.id) {
			counter++;
		}
		
		// returns a valid index of the robot with the highest preference
		return newData[counter];

	}
	
	/**
	 * Calculates the distance between this robot and another robot.
	 * @param data - the array that has information on the other robot's avenue and street
	 * @param oppID - the ID number of the robot whose distance is being checked
	 * @return the distance away the two robots are
	 * 
	 */
	private int distanceToRobot(OppData[] data, int oppID) {
		// TODO Auto-generated method stub
		// Initializing a variable to store indexes from the data array
		OppData robotsDist = data[0];
		
		// Goes through data to find the robot of the specified ID
		for (int i = 0; i < data.length; i++) {
			if (oppID == data[i].getID()) {
				robotsDist = data[i];
			}
		}
		
		// Stores that robot's avenue and street
		int a = robotsDist.getAvenue();
		int s = robotsDist.getStreet();
		
		// Finds the sum of the avenue and street differences
		int dist = Math.abs(this.getAvenue() - a) + Math.abs(this.getStreet() - s); 
		
		// returns the number of spaces away they are
		return dist;
		
	}

	@Override
	/**
	 * Updates the robot's health and label after a fight, and keeps track of who won, lost, or got a tie
	 * param healthLost - the amount of health this robot lost during the fight
	 * param oppID - the ID number of the robot that was being fought
	 * param oppHealthLost - the amount of health the opponent lost during the fight
	 * param numRoundsFought - the number of rounds that were fought during the fight.
	 * 
	 */
	public void battleResult(int healthLost, int oppID, int oppHealthLost, int numRoundsFought) {
		// TODO Auto-generated method stub
		// Updates health and label
		this.health -= healthLost; 
		this.setLabel();  
		
		// If there was a fight,
		if (oppID >= 0) { 
			// If this robot won,
			if (healthLost > oppHealthLost) { 
				// Adjusts array values
				this.numWins[oppID]++; 
				this.numLosses[this.id]++; 
			
			} 
			
			// Else if this robot lost,
			else if (healthLost < oppHealthLost) { 
				// Adjusts array values
				this.numWins[this.id]++; 
				this.numLosses[oppID]++;

			}

			// Else there was tie
			else {
				// Adjusts array values
				this.numTies[this.id]++;
				this.numTies[oppID]++;
				
			}
		
		}
		
	}

}

package robotwar;

import becker.robots.*;
//import java.util.Random;

import java.awt.Color;
import java.lang.Math;

public class DoubleRobot extends FighterRobot {

	private static final int ATK = 4;
	private static final int DEF = 4;
	private static final int NM = 2;
	private static final int MOVES_ENERGY_COST = 5;
	private static final int LOW_HEALTH = 10;
	// private final static int STEP_ENERGY = 5;

	private int health;
	private int id;

	public DoubleRobot(City c, int ave, int str, Direction d, int id, int health) {
		super(c, ave, str, d, id, ATK, DEF, NM);
		this.health = health;
		this.id = id;
		this.setLabel();

		//this.setColor(new Color(0, 0, 0));
		//this.moveRand();
	}
	/*
	private void moveRand() {
		// TODO Auto-generated method stub
		Random randNum = new Random();
		while (true) {
			int bruh = randNum.nextInt(4);

			for (int i = 0; i < bruh; i++) {
				this.turnRight();
			}
			for (int j = 0; j < NM; j++) {
				if (this.frontIsClear()) {
					this.move();
				}
			}
		}
	}
	 */
	public void setLabel() {
		this.setColor(new Color(0, 0, 0));

		if (this.health > 0) {
			this.setColor(new Color(255, 255, 255));
		}
	
		this.setLabel(this.id + " " + this.health);
		//System.out.println("ID: " + this.id + " Current health: " + this.health);
	}

	@Override
	public void goToLocation(int a, int s) {
		// TODO Auto-generated method stub
		// * can make more efficient by determining current direction within helper methods

		if (this.getStreet() > s) {
			this.goNorth(s);

		}

		else if (this.getStreet() < s) {
			this.goSouth(s);

		}


		if (this.getAvenue() < a) {
			this.goEast(a);
		}

		else if (this.getAvenue() > a) {
			this.goWest(a);
		}

	}

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
		
		/*
		while (this.getDirection() != Direction.WEST) {
			this.turnRight();
		}
		*/
		
		while (this.getAvenue() != a) {
			this.move();
		}
	}

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
		
		/*
		while (this.getDirection() != Direction.EAST) {
			this.turnRight();
		}
		*/
		
		while (this.getAvenue() != a) {
			this.move();
		}

	}

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
		
		/*
		while (this.getDirection() != Direction.SOUTH) {
			this.turnRight();
		}
		*/
		
		while (this.getStreet() != s) {
			this.move();
		}

	}

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
		
		/*
		while (this.getDirection() != Direction.NORTH) {
			this.turnRight();
		}
		*/

		while (this.getStreet() != s) {
			this.move();
		}
	}
	
	@Override
	// *use variables then only return a single turnrequest at the end?*
	// *attack mode and defense mode?*
	// *use helper methods?*
	// *energy efficient mode where it will only do one move max per turn if energy is too low?*
	// *should turn more efficiently*
	
	public TurnRequest takeTurn(int energy, OppData[] data) {
		// TODO Auto-generated method stub
		//int counter = 1;
		int ave = 0;
		int str = 0;
		int fightID = -1;
		
		// Updates the robot's health
		this.health = data[this.id].getHealth();
		
		// Updates the robot's label
		this.setLabel();
		
		/*
		while (this.findClosestRobot(data)[counter].getHealth() <= 0) {
			counter++;
		}
		*/
		
		// Represents the robot that is going to be targeted on the current turn
		//OppData target = this.findClosestRobot(data)[counter];
		OppData target = this.findTargetRobot(data);

		if (this.getAvenue() == target.getAvenue() && this.getStreet() == target.getStreet()) {
			fightID = target.getID();
			///return new TurnRequest(this.getAvenue(), this.getStreet(), target.getID(), ATK);
		}

		else if (energy > NM * MOVES_ENERGY_COST) {
			if (this.getAvenue() == target.getAvenue()) {

				if (Math.abs(this.getStreet() - target.getStreet()) == 1 || Math.abs(this.getStreet() - target.getStreet()) == 2) {
					fightID = target.getID();
					str = target.getStreet() - this.getStreet();
					///return new TurnRequest(this.getAvenue(), target.getStreet(), target.getID(), ATK);
				}
				/*
			if (this.getStreet() - target.getStreet() == 1) {
				return new TurnRequest(this.getAvenue(), this.getStreet() - 1, target.getID(), ATK);
			}

			else if (this.getStreet() - target.getStreet() == -1) {
				return new TurnRequest(this.getAvenue(), this.getStreet() + 1, target.getID(), ATK);
			}


			if (this.getStreet() - target.getStreet() == 2) {
				return new TurnRequest(this.getAvenue(), this.getStreet() - 2, target.getID(), ATK);
			}

			else if (this.getStreet() - target.getStreet() == -2) {
				return new TurnRequest(this.getAvenue(), this.getStreet() + 2, target.getID(), ATK);
			}
				 */

				else if (this.getStreet() - target.getStreet() > NM) {
					str = -NM;
					///return new TurnRequest(this.getAvenue(), this.getStreet() - NM, -1, 0);
				}

				else if (this.getStreet() - target.getStreet() < -NM) {
					str = NM;
					///return new TurnRequest(this.getAvenue(), this.getStreet() + NM, -1, 0);
				}

			}

			else if (this.getStreet() == target.getStreet()) {

				if (Math.abs(this.getAvenue() - target.getAvenue()) == 1 || Math.abs(this.getAvenue() - target.getAvenue()) == 2) {
					ave = target.getAvenue() - this.getAvenue();
					fightID = target.getID();
					///return new TurnRequest(target.getAvenue(), this.getStreet(), target.getID(), ATK);
				}
				/*
			if (this.getStreet() == target.getStreet()) {
				if (this.getAvenue() - target.getAvenue() == 1) {
					return new TurnRequest(this.getAvenue() - 1, this.getStreet(), target.getID(), ATK);
				}

				else if(this.getAvenue() - target.getAvenue() == -1) {
					return new TurnRequest(this.getAvenue() + 1, this.getStreet(), target.getID(), ATK);
				}
			}
				 */
				else if(this.getAvenue() - target.getAvenue() > NM) {
					ave = -NM;
					///return new TurnRequest(this.getAvenue() - NM, this.getStreet(), -1, 0);
				}

				else if (this.getAvenue() - target.getAvenue() < -NM) {
					ave = NM;
					///return new TurnRequest(this.getAvenue() + NM, this.getStreet(), -1, 0);
				}

			}

			else if (this.getStreet() != target.getStreet() && this.getAvenue() != target.getAvenue()) {
				if (Math.abs(this.getAvenue() - target.getAvenue()) < Math.abs(this.getStreet() - target.getStreet())) {
					if (Math.abs(this.getAvenue() - target.getAvenue()) == 1) {
						if (this.getStreet() > target.getStreet()) {
							ave = target.getAvenue() - this.getAvenue();
							str = -1;
							//return new TurnRequest(target.getAvenue(), this.getStreet() - 1, -1, 0);
						}

						else if (this.getStreet() < target.getStreet()) {
							ave = target.getAvenue() - this.getAvenue();
							str = 1;
							///return new TurnRequest(target.getAvenue(), this.getStreet() + 1, -1, 0);
						}

					}

					else if (Math.abs(this.getAvenue() - target.getAvenue()) > 1) {
						if (this.getAvenue() > target.getAvenue()) {
							ave = -NM;
							///return new TurnRequest(this.getAvenue() - NM, this.getStreet(), -1, 0);
						}

						else if (this.getAvenue() < target.getAvenue()) {
							ave = NM;
							///return new TurnRequest(this.getAvenue() + NM, this.getStreet(), -1, 0);
						}

					}

				}

				else if (Math.abs(this.getAvenue() - target.getAvenue()) > Math.abs(this.getStreet() - target.getStreet())) {
					if (Math.abs(this.getStreet() - target.getStreet()) == 1) {
						if (this.getAvenue() > target.getAvenue()) {
							ave = -1;
							str = target.getStreet() - this.getStreet();
							///return new TurnRequest(this.getAvenue() - 1, target.getStreet(), -1, 0);
						}

						else if (this.getAvenue() < target.getAvenue()) {
							ave = 1;
							str = target.getStreet() - this.getStreet();
							///return new TurnRequest(this.getAvenue() + 1, target.getStreet(), -1, 0);
						}
					}

					else if (Math.abs(this.getStreet() - target.getStreet()) > 1) {
						if (this.getStreet() > target.getStreet()) {
							str = -NM;
							///return new TurnRequest(this.getAvenue(), this.getStreet() - NM, -1, 0);
						}

						else if (this.getStreet() < target.getStreet()) {
							str = NM;
							///return new TurnRequest(this.getAvenue(), this.getStreet() + NM, -1, 0);
						}

					}

				}

				else if (Math.abs(this.getAvenue() - target.getAvenue()) == Math.abs(this.getStreet() - target.getStreet())) {
					if (this.distanceToRobot(data, target.getID()) == 2) {
						ave = target.getAvenue() - this.getAvenue();
						str = target.getStreet() - this.getStreet();
						fightID = target.getID();
						///return new TurnRequest(target.getAvenue(), target.getStreet(), target.getID(), ATK);

					}

					else if (this.getAvenue() < target.getAvenue()) {
						ave = 2;
						///return new TurnRequest(this.getAvenue() + 2, this.getStreet(), -1, 0);

					}

					else if (this.getAvenue() > target.getAvenue()) {
						ave = -2;
						///return new TurnRequest(this.getAvenue() - 2, this.getStreet(), -1, 0);

					}

				}

			}

		}

		return new TurnRequest(this.getAvenue()+ave, this.getStreet()+str, fightID, ATK);

		/*
		else if (Math.abs(this.getStreet() - target.getStreet()) == 1) {

		}
		 */
		/*
		else if robot is two or more avenues away {

		}

		if robot is on the same street {

		}

		else if robot is one street away {

		}

		else if robot is two or more streets away {

		}
		 */
		// target;
		// this.findLowestHealth(data);
		// return new TurnRequest(target.getAvenue(), 

		//return new TurnRequest(this.getAvenue() + 1, this.getStreet() + 1, -1, 0);
	}
	
	private OppData findTargetRobot(OppData[] data) {
		//OppData[] bots = this.findClosestRobot(data);
		int count = 1;

		int [] preference = new int[data.length];
		
		for (int i = 0; i < data.length; i++) {
			if (this.distanceToRobot(data, data[i].getID()) == 0) {
				preference[i] += 10;
			}
			
			else if (this.distanceToRobot(data, data[i].getID()) <= NM && this.distanceToRobot(data, data[i].getID()) > 0) {
				preference[i] += 8;
			}
			
			else if (this.distanceToRobot(data, data[i].getID()) <= NM * 2 && this.distanceToRobot(data, data[i].getID()) > NM) {
				preference[i] += 6;
			}
			
			else if (this.distanceToRobot(data, data[i].getID()) <= NM * 3 && this.distanceToRobot(data, data[i].getID()) > NM * 2) {
				preference[i] += 4;
			}
			
			else if (this.distanceToRobot(data, data[i].getID()) <= NM * 4 && this.distanceToRobot(data, data[i].getID()) > NM * 3) {
				preference[i] += 2;
			}
			
			else if (this.distanceToRobot(data, data[i].getID()) > NM * 4) {
				preference[i]++;
			}
			/////
			if (data[i].getHealth() <= 10) {
				preference[i] += 8;
			}
			
			else if (data[i].getHealth() <= 20 && data[i].getHealth() > 10) {
				preference[i] += 6;
			}
			
			else if (data[i].getHealth() <= 30 && data[i].getHealth() > 20) {
				preference[i] += 4;
			}
			
			else if (data[i].getHealth() <= 40 && data[i].getHealth() > 30) {
				preference[i] += 2;
			}
			
			else if (data[i].getHealth() >= 50) {
				preference[i]--;
			}

		}
		
		OppData temp;
		int counter = 0;

		for (int i = 0; i < data.length-1; i++) {
			int num = i;
			for (int j = i + 1; j < data.length; j++) {
				if (preference[j] < preference[num]) {
					num = j;
				}
			}

			temp = data[i];

			data[i] = data[num];
			data[num] = temp; 
		}
		while (data[data.length-count].getHealth() <= 0 || data[data.length-count].getID() == this.id) {
			count++;
		}
		// returns last index for the robot w/ highest preference
		return data[data.length-count];
		// returns last index for the robot w/ highest preference
		
			/*
			else if (bots[i].getHealth() > 40) {
				preference[i] += 3;
			}
			*/
			/*
			prefScore[i] = this.distanceToRobot(data, bots[i].getID());
			
			prefScore[i] += (this.health) / 5;
			*/
		/*
		int counter = 1;
		
		while (this.findClosestRobot(data)[counter].getHealth() <= 0) {
			counter++;
		}
		
		OppData[] distances = this.findClosestRobot(data);
		
		
		
		// Represents the robot that is going to be targeted on the current turn
		//OppData target = this.findClosestRobot(data)[counter];
		*/
	}
	
	private OppData[] findLowestHealth(OppData[] data) {
		// Selection sort
		OppData temp;
		int counter = 0;
		
		for (int i = 0; i < data.length-1; i++) {
			int num = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j].getHealth() < data[num].getHealth()) {
					num = j;
				}
			}

			temp = data[i];

			data[i] = data[num];
			data[num] = temp; 
		} 
		/*
		while (data[counter].getID() == -1 || data[counter].getID() == this.getID()) {
			counter++;
		}
		
		return data[counter];
		*/
		return data;

	}
	
	private void findClosestRobot(OppData[] data) {
		// TODO Auto-generated method stub
		// int [] distances = new int[BattleManagerTest.NUM_PLAYERS];
		//OppData temp;
		//int counter = 1;
		/*
		for (int i = 0; i < BattleManagerTest.NUM_PLAYERS; i++) {
			distances[i] = this.distanceToRobot(data, i);
		}
		 */

		// Insertion sort
		/*
		for (int j = 1; j < BattleManagerTest.NUM_PLAYERS; j++) {
			while (this.distanceToRobot(data, j) < this.distanceToRobot(data, j-1)) {
				temp = data[j];

				data[j] = data[j-1];
				data[j-1] = temp;
			}
		}
		
		
		*/
		/*
		OppData temp;
		int counter = 0;

		for (int i = 0; i < data.length-1; i++) {
			int num = i;
			for (int j = i + 1; j < data.length; j++) {
				if (this.distanceToRobot(data, data[j].getID()) < this.distanceToRobot(data, data[num].getID())) {
					num = j;
				}
			}

			temp = data[i];

			data[i] = data[num];
			data[num] = temp; 
		*/
		/*
		int distBase = 100;
		OppData objBase;
		
		for (int i = 0; i < data.length; i++) {
			if (this.distanceToRobot(data, data[i].getID()) < distBase) {
				distBase = this.distanceToRobot(data, data[i].getID());
				objBase = data[i];
			}
		}
		
		return objBase;
		 */
		/*
		// Loops through every index
		for (int j = 1; j < data.length; j++) {
			// variables
			OppData key = data[j];
			int k = j - 1;
			
			// Will swap positions as long as the first ID number is greater than the second
			while (k >= 0 && this.distanceToRobot(data, j) < this.distanceToRobot(data, k)) {
				// continuously moves number towards the left if it is smaller than the one before it
				data[j] = data[k];
				k--;

			}
			// puts the key directly after the number smaller than it
			data[k + 1] = key;
		}

		// robot with the shortest distance away, besides the robot itself and any dead robots
		
		while (data[counter].getID() == -1) {
			counter++;
		}

		return data[counter];
		*/
		
		//return data;
	}
	

	private int distanceToRobot(OppData[] data, int oppID) {
		// TODO Auto-generated method stub
		// Initializing a variable to store the 
		OppData robotsDist = data[0];
		
		for (int i = 0; i < data.length; i++) {
			if (oppID == data[i].getID()) {
				robotsDist = data[i];
			}
		}
		
		int a = robotsDist.getAvenue();
		int s = robotsDist.getStreet();

		int dist = Math.abs(this.getAvenue() - a) + Math.abs(this.getStreet() - s); 

		return dist;
	}

	@Override
	public void battleResult(int healthLost, int oppID, int oppHealthLost, int numRoundsFought) {
		// TODO Auto-generated method stub
		this.health = this.health - healthLost;
		this.setLabel();
	}

}

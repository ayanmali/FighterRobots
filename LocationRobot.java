package robotwar;

import becker.robots.*;
//import java.util.Random;

import java.awt.Color;
import java.lang.Math;

public class LocationRobot extends FighterRobot {

	private static final int ATK = 3;
	private static final int DEF = 5;
	private static final int NM = 2;
	private static final int MOVES_ENERGY_COST = 5;
	// private final static int STEP_ENERGY = 5;

	private int health;
	private int id;
	private int movesCount;

	public LocationRobot(City c, int ave, int str, Direction d, int id, int health) {
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
		if (this.health <= 0) {
			this.setColor(new Color(0, 0, 0));
		}

		else {
			this.setColor(new Color(0, 0, 255));
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
		while (this.getDirection() != Direction.WEST) {
			this.turnRight();
		}

		while (this.getAvenue() != a) {
			this.move();
		}
	}

	private void goEast(int a) {
		// TODO Auto-generated method stub
		while (this.getDirection() != Direction.EAST) {
			this.turnRight();
		}

		while (this.getAvenue() != a) {
			this.move();
		}

	}

	private void goSouth(int s) {
		// TODO Auto-generated method stub
		while (this.getDirection() != Direction.SOUTH) {
			this.turnRight();
		}

		while (this.getStreet() != s) {
			this.move();
		}

	}

	private void goNorth(int s) {
		// TODO Auto-generated method stub
		while (this.getDirection() != Direction.NORTH) {
			this.turnRight();
		}

		while (this.getStreet() != s) {
			this.move();
		}
	}

	@Override
	// *use variables then only return a single turnrequest at the end?*
	// *attack mode and defense mode?*
	// *use helper methods?*
	// *determine the robot to go after based on mainly distance, but health as well?*
	// *findClosestRobot and findLowestHealth should return the sorted array*
	
	public TurnRequest takeTurn(int energy, OppData[] data) {
		// TODO Auto-generated method stub
		this.health = data[this.id].getHealth();

		this.setLabel();

		if (this.getAvenue() == this.findClosestRobot(data).getAvenue() && this.getStreet() == this.findClosestRobot(data).getStreet()) {
			return new TurnRequest(this.getAvenue(), this.getStreet(), this.findClosestRobot(data).getID(), ATK);
		}

		else if (energy > NM * MOVES_ENERGY_COST) {
			if (this.getAvenue() == this.findClosestRobot(data).getAvenue()) {

				if (Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet()) == 1 || Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet()) == 2) {
					return new TurnRequest(this.getAvenue(), this.findClosestRobot(data).getStreet(), this.findClosestRobot(data).getID(), ATK);
				}
				/*
			if (this.getStreet() - this.findClosestRobot(data).getStreet() == 1) {
				return new TurnRequest(this.getAvenue(), this.getStreet() - 1, this.findClosestRobot(data).getID(), ATK);
			}

			else if (this.getStreet() - this.findClosestRobot(data).getStreet() == -1) {
				return new TurnRequest(this.getAvenue(), this.getStreet() + 1, this.findClosestRobot(data).getID(), ATK);
			}


			if (this.getStreet() - this.findClosestRobot(data).getStreet() == 2) {
				return new TurnRequest(this.getAvenue(), this.getStreet() - 2, this.findClosestRobot(data).getID(), ATK);
			}

			else if (this.getStreet() - this.findClosestRobot(data).getStreet() == -2) {
				return new TurnRequest(this.getAvenue(), this.getStreet() + 2, this.findClosestRobot(data).getID(), ATK);
			}
				 */

				else if (this.getStreet() - this.findClosestRobot(data).getStreet() > NM) {
					return new TurnRequest(this.getAvenue(), this.getStreet() - NM, -1, 0);
				}

				else if (this.getStreet() - this.findClosestRobot(data).getStreet() < -NM) {
					return new TurnRequest(this.getAvenue(), this.getStreet() + NM, -1, 0);
				}

			}

			else if (this.getStreet() == this.findClosestRobot(data).getStreet()) {

				if (Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) == 1 || Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) == 2) {
					return new TurnRequest(this.findClosestRobot(data).getAvenue(), this.getStreet(), this.findClosestRobot(data).getID(), ATK);
				}
				/*
			if (this.getStreet() == this.findClosestRobot(data).getStreet()) {
				if (this.getAvenue() - this.findClosestRobot(data).getAvenue() == 1) {
					return new TurnRequest(this.getAvenue() - 1, this.getStreet(), this.findClosestRobot(data).getID(), ATK);
				}

				else if(this.getAvenue() - this.findClosestRobot(data).getAvenue() == -1) {
					return new TurnRequest(this.getAvenue() + 1, this.getStreet(), this.findClosestRobot(data).getID(), ATK);
				}
			}
				 */
				else if(this.getAvenue() - this.findClosestRobot(data).getAvenue() > NM) {
					return new TurnRequest(this.getAvenue() - NM, this.getStreet(), -1, 0);
				}

				else if (this.getAvenue() - this.findClosestRobot(data).getAvenue() < -NM) {
					return new TurnRequest(this.getAvenue() + NM, this.getStreet(), -1, 0);
				}

			}

			else if (this.getStreet() != this.findClosestRobot(data).getStreet() && this.getAvenue() != this.findClosestRobot(data).getAvenue()) {
				if (Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) < Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet())) {
					if (Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) == 1) {
						if (this.getStreet() > this.findClosestRobot(data).getStreet()) {
							return new TurnRequest(this.findClosestRobot(data).getAvenue(), this.getStreet() - 1, -1, 0);
						}

						else if (this.getStreet() < this.findClosestRobot(data).getStreet()) {
							return new TurnRequest(this.findClosestRobot(data).getAvenue(), this.getStreet() + 1, -1, 0);
						}

					}

					else if (Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) > 1) {
						if (this.getAvenue() > this.findClosestRobot(data).getAvenue()) {
							return new TurnRequest(this.getAvenue() - NM, this.getStreet(), -1, 0);
						}

						else if (this.getAvenue() < this.findClosestRobot(data).getAvenue()) {
							return new TurnRequest(this.getAvenue() + NM, this.getStreet(), -1, 0);
						}

					}

				}

				else if (Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) > Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet())) {
					if (Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet()) == 1) {
						if (this.getAvenue() > this.findClosestRobot(data).getAvenue()) {
							return new TurnRequest(this.getAvenue() - 1, this.findClosestRobot(data).getStreet(), -1, 0);
						}

						else if (this.getAvenue() < this.findClosestRobot(data).getAvenue()) {
							return new TurnRequest(this.getAvenue() + 1, this.findClosestRobot(data).getStreet(), -1, 0);
						}
					}

					else if (Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet()) > 1) {
						if (this.getStreet() > this.findClosestRobot(data).getStreet()) {
							return new TurnRequest(this.getAvenue(), this.getStreet() - NM, -1, 0);
						}

						else if (this.getStreet() < this.findClosestRobot(data).getStreet()) {
							return new TurnRequest(this.getAvenue(), this.getStreet() + NM, -1, 0);
						}

					}

				}

				else if (Math.abs(this.getAvenue() - this.findClosestRobot(data).getAvenue()) == Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet())) {
					if (this.distanceToRobot(data, this.findClosestRobot(data).getID()) == 2) {
						return new TurnRequest(this.findClosestRobot(data).getAvenue(), this.findClosestRobot(data).getStreet(), this.findClosestRobot(data).getID(), ATK);

					}

					else if (this.getAvenue() < this.findClosestRobot(data).getAvenue()) {
						return new TurnRequest(this.getAvenue() + 2, this.getStreet(), -1, 0);

					}

					else if (this.getAvenue() > this.findClosestRobot(data).getAvenue()) {
						return new TurnRequest(this.getAvenue() - 2, this.getStreet(), -1, 0);

					}

				}

			}

		}

		return new TurnRequest(this.getAvenue(), this.getStreet(), -1, 0);


		/*
		else if (Math.abs(this.getStreet() - this.findClosestRobot(data).getStreet()) == 1) {

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
		// this.findClosestRobot(data);
		// this.findLowestHealth(data);
		// return new TurnRequest(this.findClosestRobot(data).getAvenue(), 

		//return new TurnRequest(this.getAvenue() + 1, this.getStreet() + 1, -1, 0);
	}

	private OppData findLowestHealth(OppData[] data) {
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

		while (data[counter].getID() == -1 || data[counter].getID() == this.getID()) {
			counter++;
		}

		return data[counter];

	}

	private OppData findClosestRobot(OppData[] data) {
		// TODO Auto-generated method stub
		// int [] distances = new int[BattleManagerTest.NUM_PLAYERS];
		OppData temp;
		int counter = 1;
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

	}

	private int distanceToRobot(OppData[] data, int oppID) {
		// TODO Auto-generated method stub
		int a = data[oppID].getAvenue();
		int s = data[oppID].getStreet();

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

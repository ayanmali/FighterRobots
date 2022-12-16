package robotwar;

import becker.robots.*;
import java.util.Random;
import java.awt.*;

public class SimpleFighterRobot extends FighterRobot {
	
	private final static int ATK = 3;
	private final static int DEF = 4;
	private final static int NM = 3;
	private int health;
	private int id;
	
	public SimpleFighterRobot(City c, int ave, int str, Direction d, int id, int health) {
		super(c, ave, str, d, id, ATK, DEF, NM);
		this.health = health;
		this.id = id;
		this.setLabel();
		
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
			this.setColor(new Color(0, 255, 0));
		}
		/*
		else {
			this.setColor(new Color(255, 0, 0));
		}
		*/
		this.setLabel(this.id + " " + this.health);
		//System.out.println("ID: " + this.id + " Current health: " + this.health);

	}

	@Override
	public void goToLocation(int a, int s) {
		// TODO Auto-generated method stub
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
	public TurnRequest takeTurn(int energy, OppData[] data) {
		// TODO Auto-generated method stub
		this.health = data[this.id].getHealth();
		this.setLabel();
		
		int fightID = -1;
		Random generator = new Random();
		
		int str = 0;
		int evenorodd = generator.nextInt(1)+1;
		int randNum = generator.nextInt(7) - 3;
		if (evenorodd == 1) {
			str = -3 + Math.abs(randNum);
		}
		else if (evenorodd == 2) {
			str = 3 - Math.abs(randNum);
		}
		
		while (this.getAvenue() + randNum >= 12 || this.getStreet() + str >= 20 || this.getAvenue() + randNum <= 0 || this.getStreet() + str <= 0) {
			evenorodd = generator.nextInt(1)+1;
			randNum = generator.nextInt(7) - 3;
			if (evenorodd == 1) {
				str = -3 + Math.abs(randNum);
			}
			else if (evenorodd == 2) {
				str = 3 - Math.abs(randNum);
			}
			
		}
		
		for (int i = 0; i < data.length; i++) {
			if (data[i].getAvenue() == this.getAvenue() + randNum && data[i].getStreet() == this.getStreet() + str) {
				fightID = data[i].getID();
			}
		}
		
		return new TurnRequest(this.getAvenue()+randNum, this.getStreet()+str, fightID, 0);
	}
	
	@Override
	public void battleResult(int healthLost, int oppID, int oppHealthLost, int numRoundsFought) {
		// TODO Auto-generated method stub
		this.health = this.health - healthLost;
		this.setLabel();
	}

}

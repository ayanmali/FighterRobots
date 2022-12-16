package robotwar;

public class AliOppData extends OppData {
	
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	
	public AliOppData(int id, int a, int s, int health) {
		super(id, a, s, health);

	}
	
	public void setWins(int amount) {
		this.wins = amount;
		//this.wins++;
	}
	
	public void setLosses(int amount) {
		this.losses = amount;
		//this.losses++;
	}
	
	public void setTies(int amount) {
		this.ties = amount;
		//this.ties++;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public int getLosses() {
		return this.losses;
	}
	
	public int getTies() {
		return this.ties;
	}
	
}

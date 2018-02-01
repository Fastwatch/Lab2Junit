
public class Frame {
	private int score, bowls;
	
	public Frame() {
		score = bowls = 0;
	}

	public int getScore() {
		return score;
	}
	
	public void addToScore(int add) {
		if(add < 0 || add > 10) throw new IllegalArgumentException("Illegal Score to add");
		score += add;
	}
	
	public void bowl(int pins) {
		if(pins < 0 || (pins + score) > 10) {
			throw new IllegalArgumentException("illegal number of pins");
		}
		if(bowls > 1) {
			throw new IllegalStateException("Can't bowl more on this frame");
		}
		++bowls;
		score += pins;
		
	}
	
	public boolean canBowl() {
		return (bowls < 2 && score < 10);
	}
	
	public boolean isStrike() {
		return (bowls == 1 && score == 10);
	}
	
}

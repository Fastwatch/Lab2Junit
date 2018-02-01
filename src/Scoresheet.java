
public class Scoresheet {
	private Frame[] frames;
	private int currentFrame;
	private Frame add2;
	private Frame[] add1 = new Frame[2];
	
	public Scoresheet() {
		frames = new Frame[10];
		currentFrame = 0;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public int getFrameScore(int frameNumber) {
		return frames[frameNumber].getScore();
	}
	
	public int getGameScore() {
		int total = 0;
		for (Frame f : frames) {
			total += f.getScore();
		}
		return total;
	}
	
	public void bowl(int pins) {
		
		if(!canPlay()) throw new IllegalStateException("Cannot Play, the game is finished");
		frames[currentFrame].bowl(pins);
		
		if(!frames[currentFrame].canBowl()) {
			updateScores();
			
			if(frames[currentFrame].isStrike()) {
				add2 = frames[currentFrame];
			} else if(frames[currentFrame].getScore() == 10) {
				add1[1] = frames[currentFrame];
			}
			
			++currentFrame;
		}
	}
	
	public void newGame() {
		for (int i = 0; i < 10; ++i) {
			frames[i] = new Frame();
		}
		currentFrame = 0;
		add1 = new Frame[2];
		add2 = null;
	}
	
	private boolean canPlay() {
		if(currentFrame < 10)
			return true;
		return false;
	}
	
	private void updateScores() {
		
		int score = frames[currentFrame].getScore();
		
		if(add2 != null)
			add2.addToScore(score);
		for (Frame f : add1) {
			if(f != null)
				f.addToScore(score);
		}
		
		add1 = new Frame[2];
		add1[0] = add2;
		add2 = null;
	}
	
	
}

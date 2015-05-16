package lambda;

public class DriedApple extends CutApple {
	private int humidity;

	public DriedApple(CutApple apple, int humidity) {
		super(apple, apple.getPieceNum());
		this.humidity = humidity;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return "DriedApple [humidity=" + humidity + "]";
	}

}
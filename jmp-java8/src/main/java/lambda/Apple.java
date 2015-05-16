package lambda;

public class Apple {
	private int weight;
	private String color;
	private String country;

	public Apple(int weight, String color, String country) {
		this.weight = weight;
		this.color = color;
		this.country = country;
	}

	public Apple(int weight, String color) {
		super();
		this.weight = weight;
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Apple [weight=" + weight + ", color=" + color + ", country=" + country + "]";
	}
}
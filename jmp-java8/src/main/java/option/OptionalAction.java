package option;

import java.util.Optional;

public final class OptionalAction {

	public static void main(String[] args) {
		// Person with car that have no insurance
		Person p = new Person();
		Car c = new Car();
		c.setInsurance(Optional.empty());
		p.setCar(Optional.of(c));

		// shows how to fetch value from a chain of Optional values
		String insuranceName = p.getCar()
				.flatMap(Car::getInsurance)
				.map(Insurance::getName)
				.orElse("Unknown");
		System.out.println(insuranceName);
	}

}

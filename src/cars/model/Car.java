package cars.model;

import java.util.Objects;

public class Car {
	protected String regNumber;
	protected String model;
	protected String company;
	protected double engine;
	protected String color;

	public Car(String regNumber, String model, String company, double engine, String color) {
		this.regNumber = regNumber;
		this.model = model;
		this.company = company;
		this.engine = engine;
		this.color = color;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getEngine() {
		return engine;
	}

	public void setEngine(double engine) {
		this.engine = engine;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(regNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return regNumber == other.regNumber;
	}

	@Override
	public String toString() {
		return "Car [regNumber=" + regNumber + ", model=" + model + ", company=" + company + ", engine=" + engine
				+ ", color=" + color + "]";
	}

}

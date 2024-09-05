package cars.dao;

import java.util.Iterator;
import java.util.function.Predicate;

import cars.model.Car;

public class GarageImpl implements Garage {
	Car[] cars;
	int size;
	private static final String DELIMETER = ",\n";

	public GarageImpl(int capacity) {
		cars = new Car[capacity];
	}

	@Override
	public boolean addCar(Car car) {
		if (car == null || size == cars.length || findCarByRegNumber(car.getRegNumber()) != null) {
			return false;
		}

		cars[size] = car;
		size++;
		return true;
	}

	@Override
	public Car removeCar(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (cars[i].getRegNumber() == regNumber) {
				Car victim = cars[i];
				cars[i] = cars[size - 1];
				cars[size - 1] = null;
				size--;
				return victim;

			}

		}
		return null;
	}

	@Override
	public Car findCarByRegNumber(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (cars[i].getRegNumber() == regNumber) {
				return cars[i];
			}

		}
		return null;
	}

	@Override
	public Car[] findCarsByModel(String model) {
		Predicate<Car> predicateForModel = new Predicate<Car>() {

			@Override
			public boolean test(Car t) {
				return t.getModel() == model;
			}
		};
		return findCarsByPredicate(predicateForModel);
	}

	@Override
	public Car[] findCarsByCompany(String company) {

		Predicate<Car> predicateForCompany = new Predicate<Car>() {

			@Override
			public boolean test(Car t) {
				return t.getCompany() == company;
			}
		};

		return findCarsByPredicate(predicateForCompany);
	}

	@Override
	public Car[] findCarsByEngine(double min, double max) {
		Predicate<Car> predicateForEngine = new Predicate<Car>() {

			@Override
			public boolean test(Car t) {

				return t.getEngine() >= min && t.getEngine() < max;
			}
		};

		return findCarsByPredicate(predicateForEngine);

	}

	@Override
	public Car[] findCarsByColor(String color) {
		Predicate<Car> predicateForColor = new Predicate<Car>() {

			@Override
			public boolean test(Car t) {
				return t.getColor().equals(color);
			}
		};

		return findCarsByPredicate(predicateForColor);
	}

	private Car[] findCarsByPredicate(Predicate<Car> predicate) {
		Car[] res;
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (predicate.test(cars[i])) {
				counter++;
			}
		}

		res = new Car[counter];
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (predicate.test(cars[i])) {
				res[j++] = cars[i];
			}
		}

		return res;

	}

	@Override
	public int size() {
		return size;
	}

}

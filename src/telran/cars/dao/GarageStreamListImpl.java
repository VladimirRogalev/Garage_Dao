package telran.cars.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import telran.cars.model.Car;

public class GarageStreamListImpl implements Garage {
	private List<Car> cars;
	private int capacity;

	public GarageStreamListImpl(int capacity) {
		this.capacity = capacity;
		cars = new ArrayList<Car>();
	}


	@Override
	public boolean addCar(Car car) {
		if (car == null || capacity == cars.size() || findCarByRegNumber(car.getRegNumber()) != null) {
			return false;
		}
		cars.add(car);
		return true;
	}

	@Override
	public Car removeCar(String regNumber) {
		Car victim = findCarByRegNumber(regNumber);
		cars.remove(victim);
		return victim;
	}

	@Override
	public Car findCarByRegNumber(String regNumber) {
	return cars.stream()
						.filter(car-> car.getRegNumber().equals(regNumber))
						.findFirst()
						.orElse(null);
	}

	@Override
	public Car[] findCarsByModel(String model) {
		return findCarsByPredicate(c -> model.equals(c.getModel()));
	}

	@Override
	public Car[] findCarsByCompany(String company) {
		return findCarsByPredicate(c -> company.equals(c.getCompany()));
	}

	@Override
	public Car[] findCarsByEngine(double min, double max) {
		return findCarsByPredicate(c -> c.getEngine() >= min && c.getEngine() < max);
	}

	@Override
	public Car[] findCarsByColor(String color) {
		return findCarsByPredicate(c -> color.equals(c.getColor()));
	}

	private Car[] findCarsByPredicate(Predicate<Car> predicate) {
		return cars.stream()
							.filter(predicate)
							.toArray(Car[]::new);
	}

}

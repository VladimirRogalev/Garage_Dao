package telran.cars.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import telran.cars.model.Car;

public class GarageListImpl implements Garage {
	private List<Car> cars;
	private int capacity;

	public GarageListImpl(int capacity) {
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
	for (Car car : cars) {
		if (car.getRegNumber().equals(regNumber)) {
			return car;
		}
	}
		return null;
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
		List<Car> res = new ArrayList<Car>();
		for (Car car : cars) {
			if(predicate.test(car)) {
				res.add(car);
				}
		}
		
		return  res.toArray(new Car[0]);
	}

}

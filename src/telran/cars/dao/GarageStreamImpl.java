package telran.cars.dao;

import java.util.Arrays;
import java.util.function.Predicate;

import telran.cars.model.Car;

public class GarageStreamImpl implements Garage {
	private Car[] cars;
	private int size;

	public GarageStreamImpl(int capacity) {
		cars = new Car[capacity];
	}

	@Override
	public boolean addCar(Car car) {
		if (car == null || size == cars.length || findCarByRegNumber(car.getRegNumber()) != null) {
			return false;
		}
		cars[size++] = car;
		return true;
	}

	@Override
	public Car removeCar(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (regNumber.equals(cars[i].getRegNumber())) {
				Car temp = cars[i];
				cars[i] = cars[--size];
				return temp;
			}

		}
		return null;
	}

	@Override
	public Car findCarByRegNumber(String regNumber) {
		return Arrays.stream(cars)
									.filter(car -> car !=null && car.getRegNumber().equals(regNumber))
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
		return Arrays.stream(cars, 0, size)
				.filter(predicate)
				.toArray(Car[]::new);
	}

}

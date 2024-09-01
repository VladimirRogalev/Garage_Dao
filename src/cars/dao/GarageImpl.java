package cars.dao;

import java.util.Iterator;

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
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < size; i++) {
			if (cars[i].getModel() == model) {
				res.append(i).append(DELIMETER);

			}
		}
		String[] arr = res.toString().split(DELIMETER);
		Car[] matchCars = new Car[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int index = Integer.parseInt(arr[i]);
			matchCars[i] = cars[index];

		}
		return matchCars;

	}

	@Override
	public Car[] findCarsByCompany(String company) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < size; i++) {
			if (cars[i].getCompany() == company) {
				res.append(i).append(DELIMETER);

			}
		}
		String[] arr = res.toString().split(DELIMETER);
		Car[] matchCars = new Car[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int index = Integer.parseInt(arr[i]);
			matchCars[i] = cars[index];

		}
		return matchCars;
	}

	@Override
	public Car[] findCarsByEngine(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car[] findCarsByColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}

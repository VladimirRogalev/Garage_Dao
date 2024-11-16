package telran.cars.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import telran.cars.model.Car;

public class GarageTreeSetImpl implements Garage {
	private TreeSet<Car> cars;

	private static Comparator<Car> regNumberComparator = (o1, o2) -> o1.getRegNumber()
			.compareToIgnoreCase(o2.getRegNumber());

	public GarageTreeSetImpl() {

		cars = new TreeSet<Car>(regNumberComparator);
	}

	public GarageTreeSetImpl(List<Car> cars) {
		this();
		cars.forEach(t -> addCar(t));
	}

	@Override
	public boolean addCar(Car car) {
		if (car == null || cars.contains(car)) {
			return false;
		}
		cars.add(car);
		return true;
	}

	@Override
	public Car removeCar(String regNumber) {
		Car victim = findCarByRegNumber(regNumber);
		if (victim != null) {
			cars.remove(victim);
		}

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
		TreeSet<Car> res = new TreeSet<Car>(regNumberComparator);
		for (Car car : cars) {
			if (predicate.test(car)) {
				res.add(car);
			}
		}

		return res.toArray(new Car[0]);
	}

}

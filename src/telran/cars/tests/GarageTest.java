package telran.cars.tests;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.cars.dao.Garage;


import telran.cars.dao.GarageTreeSetImpl;
import telran.cars.model.Car;

public class GarageTest {
	Garage garage;
	Car[] cars;

	@BeforeEach
	public void setUp() throws Exception {
		
		cars = new Car[] {new Car("Number1", "Model1", "Company1", 1.5, "Red"),
				new Car("Number2", "Model2", "Company1", 2.5, "Green"),
				new Car("Number3", "Model3", "Company2", 1.5, "Red"), 
				 new Car("Number4", "Model4", "Company2", 2.0, "Green")	};
				 garage = new GarageTreeSetImpl(Arrays.asList(cars));
	}

	@Test
	public void testAddCar() {
		 assertFalse(garage.addCar(cars[0]));  
		    Car car = new Car("Number5", "Model4", "Company2", 2.0, "Green");
		    assertTrue(garage.addCar(car));
		    Car duplicate = new Car("Number5", "AnotherModel", "AnotherCompany", 3.0, "Blue");
		    assertFalse(garage.addCar(duplicate)); 
		}

	@Test
	public void testRemoveCar() {
		assertEquals(cars[0], garage.removeCar("Number1"));
		assertNull(garage.removeCar("Number1"));
	}

	@Test
	public void testFindCarByRegNumber() {
		Car car = garage.findCarByRegNumber("Number4");
		assertEquals(cars[3], car);
		assertEquals(cars[3].getColor(), car.getColor());
		assertEquals(cars[3].getEngine(), car.getEngine());
		assertEquals(cars[3], garage.findCarByRegNumber(new String("Number4")));
	}

	@Test
	public void testFindCarsByModel() {
		Car[] expecteds = { cars[2] };
		Car[] actuals = garage.findCarsByModel(new String("Model3"));
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testFindCarsByCompany() {
		Car[] expecteds = { cars[2], cars[3] };
		Car[] actuals = garage.findCarsByCompany("Company2");
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testFindCarsByEngine() {
		Car[] expecteds = { cars[1], cars[3] };
		Car[] actuals = garage.findCarsByEngine(1.9, 2.6);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testFindCarsByColor() {
		Car[] expecteds = { cars[0], cars[2] };
		Car[] actuals = garage.findCarsByColor(new String("Red"));
		assertArrayEquals(expecteds, actuals);
	}

}

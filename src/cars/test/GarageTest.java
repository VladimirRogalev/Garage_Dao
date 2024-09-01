package cars.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cars.dao.Garage;
import cars.dao.GarageImpl;
import cars.model.Car;

class GarageTest {

	Garage garage;
	Car[] cars;

	@BeforeEach
	void setUp() throws Exception {
		garage = new GarageImpl(10);
		cars = new Car[9];
		cars[0] = new Car("692-67-202", "2", "Mazda", 1.6, "Black");
		cars[1] = new Car("123-45-678", "Corolla", "Toyota", 2.0, "White");
		cars[2] = new Car("987-65-432", "Civic", "Honda", 1.8, "Red");
		cars[3] = new Car("456-78-901", "3 Series", "BMW", 3.0, "Blue");
		cars[4] = new Car("234-56-789", "Focus", "Ford", 2.5, "Green");
		cars[5] = new Car("678-90-123", "Altima", "Nissan", 2.2, "Silver");
		cars[6] = new Car("345-67-890", "Malibu", "Chevrolet", 1.4, "Yellow");
		cars[7] = new Car("789-01-234", "Golf", "Volkswagen", 1.8, "Grey");
		cars[8] = new Car("111-22-333", "A4", "Audi", 2.8, "Black");
		for (int i = 0; i < cars.length; i++) {
			garage.addCar(cars[i]);
		}
	}

	@Test
	void testAddCar() {
		assertFalse(garage.addCar(cars[2]));
		Car car = new Car("999-88-777", "Mustang", "Ford", 5.0, "Red");
		assertTrue(garage.addCar(car));
		car = new Car("999-88-877", "Mustang", "Ford", 5.0, "Red");
		assertFalse(garage.addCar(car));
	}

	@Test
	void testRemoveCar() {
		Car car = garage.removeCar("678-90-123");
		assertEquals(8, garage.size());
		assertEquals(cars[5], car);
		assertNull(garage.removeCar("678-90-123"));

	}

	@Test
	void testFindCarByRegNumber() {
		assertEquals(cars[6], garage.findCarByRegNumber("345-67-890"));
		assertNull(garage.findCarByRegNumber("656-55-478"));
	}

	@Test
	void testFindCarsByModel() {
		Car[] car = { cars[8] };
		assertArrayEquals(car, garage.findCarsByModel("A4"));
		assertNotNull(garage.findCarsByModel("Malibu"));

	}

	@Test
	void testFindCarsByCompany() {
		Car[] car = { cars[2] };
		assertArrayEquals(car, garage.findCarsByCompany("Honda"));
		assertNotNull(garage.findCarsByCompany("Audi"));
	}

	@Test
	void testFindCarsByEngine() {
		Car[] car = { cars[6] };
		assertArrayEquals(car, garage.findCarsByEngine(1.4, 1.6));
		assertNotNull(garage.findCarsByCompany("Audi"));
	}

	@Test
	void testFindCarsByColor() {
		fail("Not yet implemented");
	}

}

package ua.lviv.iot.dblabs.view;

import ua.lviv.iot.dblabs.controller.*;
import ua.lviv.iot.dblabs.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Component
public class MyView {

    @Autowired
    private CarController carController;
    @Autowired
    private CityController cityController;
    @Autowired
    private CountryController countryController;
    @Autowired
    private DriverController driverController;
    @Autowired
    private FineController fineController;
    @Autowired
    private ParkingController parkingController;
    @Autowired
    private RentController rentController;
    @Autowired
    private TransactionController transactionController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Car nullCar = new Car(null, null, null, null, null, null, null, null, null, null, null);
    private final City nullCity = new City(null, null, null);
    private final Country nullCountry = new Country(null, null);
    private final Driver nullDriver = new Driver(null, null, null, null, null, null);
    private final Fine nullFine = new Fine(null, null, null);
    private final Parking nullParking = new Parking(null, null, null, null);
    private final Rent nullRent = new Rent(null, null, null, null, null, null, null);
    private final Transaction nullTransaction = new Transaction(null, null);


    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Car");
        menu.put("11", "  11 - Create Car");
        menu.put("12", "  12 - Update Car");
        menu.put("13", "  13 - Delete from Car");
        menu.put("14", "  14 - Find all Cars");
        menu.put("15", "  15 - Find Car by ID");
        menu.put("16", "  16 - Find Car by model");
        menu.put("17", "  17 - Find Car by fuel_type");
        menu.put("18", "  18 - Find Car by parking_id");
        menu.put("19", "  19 - Find Car in range from _ to _");

        menu.put("2", "   2 - Table: City");
        menu.put("21", "  21 - Create City");
        menu.put("22", "  22 - Update City");
        menu.put("23", "  23 - Delete from City");
        menu.put("24", "  24 - Find all Cities");
        menu.put("25", "  25 - Find City by ID");

        menu.put("3", "   3 - Table: Country");
        menu.put("31", "  31 - Create Country");
        menu.put("32", "  32 - Update Country");
        menu.put("33", "  33 - Delete from Country");
        menu.put("34", "  34 - Find all Countries");
        menu.put("35", "  35 - Find Country by ID");

        menu.put("4", "   4 - Table: Driver");
        menu.put("41", "  41 - Create Driver");
        menu.put("42", "  42 - Update Driver");
        menu.put("43", "  43 - Delete from Driver");
        menu.put("44", "  44 - Find all Drivers");
        menu.put("45", "  45 - Find Driver by license_number");
        menu.put("46", "  46 - Find Drivers by surname");

        menu.put("5", "   5 - Table: Fine");
        menu.put("51", "  51 - Create Fine");
        menu.put("52", "  52 - Update Fine");
        menu.put("53", "  53 - Delete from Fine");
        menu.put("54", "  54 - Find all Fines");
        menu.put("55", "  55 - Find Fine by ID");
        menu.put("56", "  56 - Find Fines by driver_license_number");

        menu.put("6", "   6 - Table: Parking");
        menu.put("61", "  61 - Create Parking");
        menu.put("62", "  62 - Update Parking");
        menu.put("63", "  63 - Delete from Parking");
        menu.put("64", "  64 - Find all Parkings");
        menu.put("65", "  65 - Find Parking by ID");
        menu.put("66", "  66 - Find Parkings by city_id");

        menu.put("7", "   7 - Table: Rent");
        menu.put("71", "  71 - Create Rent");
        menu.put("72", "  72 - Update Rent");
        menu.put("73", "  73 - Delete from Rent");
        menu.put("74", "  74 - Find all Rents");
        menu.put("75", "  75 - Find Rent by ID");
        menu.put("76", "  76 - Find Rents by car_id");
        menu.put("77", "  77 - Find Rents by driver_license_number");
        menu.put("78", "  78 - Find Rents in date range from _ to _");

        menu.put("8", "   8 - Table: Transaction");
        menu.put("81", "  81 - Create Transaction");
        menu.put("82", "  82 - Update Transaction");
        menu.put("83", "  83 - Delete from Transaction");
        menu.put("84", "  84 - Find all Transactions");
        menu.put("85", "  85 - Find Transaction by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createCar);
        methodsMenu.put("12", this::updateCar);
        methodsMenu.put("13", this::deleteFromCar);
        methodsMenu.put("14", this::findAllCars);
        methodsMenu.put("15", this::findCarById);
        methodsMenu.put("16", this::findCarsByModel);
        methodsMenu.put("17", this::findCarsByFuelType);
        methodsMenu.put("18", this::findCarsByParkingId);
        methodsMenu.put("19", this::findCarsInPriceRange);

        methodsMenu.put("21", this::createCity);
        methodsMenu.put("22", this::updateCity);
        methodsMenu.put("23", this::deleteFromCity);
        methodsMenu.put("24", this::findAllCities);
        methodsMenu.put("25", this::findCityById);

        methodsMenu.put("31", this::createCountry);
        methodsMenu.put("32", this::updateCountry);
        methodsMenu.put("33", this::deleteFromCountry);
        methodsMenu.put("34", this::findAllCountries);
        methodsMenu.put("35", this::findCountryById);

        methodsMenu.put("41", this::createDriver);
        methodsMenu.put("42", this::updateDriver);
        methodsMenu.put("43", this::deleteFromDriver);
        methodsMenu.put("44", this::findAllDrivers);
        methodsMenu.put("45", this::findDriverById);
        methodsMenu.put("46", this::findDriversBySurname);

        methodsMenu.put("51", this::createFine);
        methodsMenu.put("52", this::updateFine);
        methodsMenu.put("53", this::deleteFromFine);
        methodsMenu.put("54", this::findAllFines);
        methodsMenu.put("55", this::findFineById);
        methodsMenu.put("56", this::findFinesByDriverLicenseNumber);

        methodsMenu.put("61", this::createParking);
        methodsMenu.put("62", this::updateParking);
        methodsMenu.put("63", this::deleteFromParking);
        methodsMenu.put("64", this::findAllParkings);
        methodsMenu.put("65", this::findParkingById);
        methodsMenu.put("66", this::findParkingsByCityId);

        methodsMenu.put("71", this::createRent);
        methodsMenu.put("72", this::updateRent);
        methodsMenu.put("73", this::deleteFromRent);
        methodsMenu.put("74", this::findAllRents);
        methodsMenu.put("75", this::findRentById);
        methodsMenu.put("76", this::findRentsByCarId);
        methodsMenu.put("77", this::findRentsByDriverLicenseNumber);
        methodsMenu.put("78", this::findRentsInDateRangeFromTo);

        methodsMenu.put("81", this::createTransaction);
        methodsMenu.put("82", this::updateTransaction);
        methodsMenu.put("83", this::deleteFromTransaction);
        methodsMenu.put("84", this::findAllTransactions);
        methodsMenu.put("85", this::findTransactionById);
    }

    private void selectAllTable() {
        findAllCars();
        findAllCities();
        findAllCountries();
        findAllDrivers();
        findAllFines();
        findAllParkings();
        findAllRents();
        findAllTransactions();
    }

    // CAR
    private void createCar() {
        System.out.println("Input 'model': ");
        String model = input.nextLine();
        System.out.println("Input 'color': ");
        String color = input.nextLine();
        System.out.println("Input 'transmission_type' ('manual' or 'automatic'): ");
        String transmissionType = input.nextLine();
        if (!Objects.equals(transmissionType, "manual") && !Objects.equals(transmissionType, "automatic")) {
            System.out.println("Wrong transmission type!");
            return;
        }
        System.out.println("Input 'seats_count': ");
        Integer seatsCount = Integer.valueOf(input.nextLine());
        System.out.println("Input 'has_ac' ('true' or 'false'): ");
        Integer hasAC = Objects.equals(input.nextLine(), "true") ? 1 : 0;
        System.out.println("Input 'baggage_capacity_kg': ");
        Float baggageCapacityKG = Float.valueOf(input.nextLine());
        System.out.println("Input 'rent_cost_per_day_usd': ");
        Float rentCostPerDayUSD = Float.valueOf(input.nextLine());
        System.out.println("Input 'fuel_type' ('gas', 'petrol', 'diesel', 'electric', or 'other'): ");
        String fuelType = input.nextLine();
        if (!Objects.equals(fuelType, "gas") && !Objects.equals(fuelType, "petrol") &&
                !Objects.equals(fuelType, "diesel") && !Objects.equals(fuelType, "electric")) {
            fuelType = "other";
        }
        System.out.println("Input 'additional_info': ");
        String additionalInfo = input.nextLine();
        System.out.println("Input 'parking_id': ");
        Integer parkingId =Integer.valueOf(input.nextLine());
        Car car = new Car(null, model, color, transmissionType, seatsCount, hasAC, baggageCapacityKG, rentCostPerDayUSD, fuelType, additionalInfo, parkingId);

        int count = carController.create(car);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCar() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'model': ");
        String model = input.nextLine();
        System.out.println("Input new 'color': ");
        String color = input.nextLine();
        System.out.println("Input new 'transmission_type' ('manual' or 'automatic'): ");
        String transmissionType = input.nextLine();
        if (!Objects.equals(transmissionType, "manual") && !Objects.equals(transmissionType, "automatic")) {
            System.out.println("Wrong transmission type!");
            return;
        }
        System.out.println("Input new 'seats_count': ");
        Integer seatsCount = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'has_ac' ('true' or 'false'): ");
        Integer hasAC = Objects.equals(input.nextLine(), "true") ? 1 : 0;
        System.out.println("Input new 'baggage_capacity_kg': ");
        Float baggageCapacityKG = Float.valueOf(input.nextLine());
        System.out.println("Input new 'rent_cost_per_day_usd': ");
        Float rentCostPerDayUSD = Float.valueOf(input.nextLine());
        System.out.println("Input new 'fuel_type' ('gas', 'petrol', 'diesel', 'electric', or 'other'): ");
        String fuelType = input.nextLine();
        if (!Objects.equals(fuelType, "gas") && !Objects.equals(fuelType, "petrol") &&
                !Objects.equals(fuelType, "diesel") && !Objects.equals(fuelType, "electric")) {
            fuelType = "other";
        }
        System.out.println("Input new 'additional_info': ");
        String additionalInfo = input.nextLine();
        System.out.println("Input new 'parking_id': ");
        Integer parkingId =Integer.valueOf(input.nextLine());
        Car car = new Car(null, model, color, transmissionType, seatsCount, hasAC, baggageCapacityKG, rentCostPerDayUSD, fuelType, additionalInfo, parkingId);

        int count = carController.update(id, car);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCar() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = carController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCars() {
        System.out.println("\nTable: CAR");
        List<Car> cars = carController.findAll();
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void findCarById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Car> car = carController.findById(id);
        System.out.println(car.orElse(nullCar));
    }

    private void findCarsByModel() {
        System.out.println("Input 'model': ");
        String model = input.nextLine();

        List<Car> cars = carController.findByModel(model);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void findCarsByFuelType() {
        System.out.println("Input 'fuel_type': ");
        String fuelType = input.nextLine();

        List<Car> cars = carController.findByFuelType(fuelType);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void findCarsByParkingId() {
        System.out.println("Input 'parking_id': ");
        Integer parkingId = Integer.valueOf(input.nextLine());

        List<Car> cars = carController.findByParkingId(parkingId);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void findCarsInPriceRange() {
        System.out.println("From (in USD): ");
        Float from = Float.valueOf(input.nextLine());
        System.out.println("To (in USD): ");
        Float to = Float.valueOf(input.nextLine());

        List<Car> cars = carController.findInPriceRange(from, to);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    // CITY
    private void createCity() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'country_id': ");
        Integer countryId = Integer.valueOf(input.nextLine());

        City city = new City(null, name, countryId);

        int count = cityController.create(city);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCity() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'country_id': ");
        Integer countryId = Integer.valueOf(input.nextLine());

        City city = new City(null, name, countryId);

        int count = cityController.update(id, city);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCity() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        int count = cityController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCities() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }

    private void findCityById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<City> city = cityController.findById(id);
        System.out.println(city.orElse(nullCity));
    }

    // COUNTRY

    private void createCountry() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();

        Country country = new Country(null, name);

        int count = countryController.create(country);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCountry() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new 'name': ");
        String name = input.nextLine();

        Country country = new Country(null, name);

        int count = countryController.update(id, country);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCountry() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        int count = countryController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCountries() {
        System.out.println("\nTable: COUNTRY");
        List<Country> countries = countryController.findAll();
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    private void findCountryById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<Country> country = countryController.findById(id);
        System.out.println(country.orElse(nullCountry));
    }

    // DRIVER

    private void createDriver() {
        System.out.println("Input 'license_number': ");
        String licenseNumber = input.nextLine();
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'middlename': ");
        String middlename = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();
        System.out.println("Input 'phone_number': ");
        String phoneNumber = input.nextLine();

        Driver driver = new Driver(licenseNumber, name, surname, middlename, email, phoneNumber);

        int count = driverController.create(driver);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateDriver() {
        System.out.println("Input 'license_number': ");
        String licenseNumber = input.nextLine();

        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input new 'middlename': ");
        String middlename = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();
        System.out.println("Input new 'phone_number': ");
        String phoneNumber = input.nextLine();


        Driver driver = new Driver(null, name, surname, middlename, email, phoneNumber);

        int count = driverController.update(licenseNumber, driver);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromDriver() {
        System.out.println("Input 'license_number': ");
        String licenseNumber = input.nextLine();

        int count = driverController.delete(licenseNumber);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllDrivers() {
        System.out.println("\nTable: DRIVER");
        List<Driver> drivers = driverController.findAll();
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    private void findDriverById() {
        System.out.println("Input 'license_number': ");
        String licenseNumber = input.nextLine();

        Optional<Driver> driver = driverController.findById(licenseNumber);
        System.out.println(driver.orElse(nullDriver));
    }

    private void findDriversBySurname() {
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();

        List<Driver> drivers = driverController.findBySurname(surname);
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    // FINE

    private void createFine() {
        System.out.println("Input 'violation_type': ");
        String violationType = input.nextLine();
        System.out.println("Input 'driver_license_number': ");
        String driverLicenseNumber = input.nextLine();

        Fine fine = new Fine(null, violationType, driverLicenseNumber);

        int count = fineController.create(fine);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateFine() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new 'violation_type': ");
        String violationType = input.nextLine();
        System.out.println("Input new 'driver_license_number': ");
        String driverLicenseNumber = input.nextLine();

        Fine fine = new Fine(null, violationType, driverLicenseNumber);

        int count = fineController.update(id, fine);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromFine() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        int count = fineController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllFines() {
        System.out.println("\nTable: FINE");
        List<Fine> fines = fineController.findAll();
        for (Fine fine : fines) {
            System.out.println(fine);
        }
    }

    private void findFineById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<Fine> fine = fineController.findById(id);
        System.out.println(fine.orElse(nullFine));
    }

    private void findFinesByDriverLicenseNumber() {
        System.out.println("Input 'driver_license_number': ");
        String driverLicenseNumber = input.nextLine();

        List<Fine> fines = fineController.findByDriverLicenseNumber(driverLicenseNumber);
        for (Fine fine : fines) {
            System.out.println(fine);
        }
    }

    // PARKING

    private void createParking() {
        System.out.println("Input 'location': ");
        String location = input.nextLine();
        System.out.println("Input 'type': ");
        String type = input.nextLine();
        System.out.println("Input 'city_id': ");
        Integer cityId = Integer.valueOf(input.nextLine());

        Parking parking = new Parking(null, location, type, cityId);

        int count = parkingController.create(parking);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateParking() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new 'location': ");
        String location = input.nextLine();
        System.out.println("Input new 'type': ");
        String type = input.nextLine();
        System.out.println("Input new 'city_id': ");
        Integer cityId = Integer.valueOf(input.nextLine());

        Parking parking = new Parking(null, location, type, cityId);

        int count = parkingController.update(id, parking);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromParking() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        int count = parkingController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllParkings() {
        System.out.println("\nTable: PARKING");
        List<Parking> parkings = parkingController.findAll();
        for (Parking parking : parkings) {
            System.out.println(parking);
        }
    }

    private void findParkingById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<Parking> parking = parkingController.findById(id);
        System.out.println(parking.orElse(nullParking));
    }

    private void findParkingsByCityId() {
        System.out.println("Input 'city_id': ");
        Integer cityId = Integer.valueOf(input.nextLine());

        List<Parking> parkings = parkingController.findByCityId(cityId);
        for (Parking parking : parkings) {
            System.out.println(parking);
        }
    }

    // RENT

    private void createRent() {
        System.out.println("Input 'date_of_rent': ");
        Timestamp dateOfRent = Timestamp.valueOf(input.nextLine());
        System.out.println("Input 'end_date_of_rent': ");
        Timestamp endDateOfRent = Timestamp.valueOf(input.nextLine());
        System.out.println("Input 'payment_type': ");
        String paymentType = input.nextLine();
        System.out.println("Input 'transaction_id': ");
        String transactionId = input.nextLine();
        System.out.println("Input 'car_id': ");
        Integer carId = Integer.valueOf(input.nextLine());
        System.out.println("Input 'driver_license_number': ");
        String driverLicenseNumber = input.nextLine();


        Rent rent = new Rent(null, dateOfRent, endDateOfRent, paymentType, transactionId, carId, driverLicenseNumber);

        int count = rentController.create(rent);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateRent() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        System.out.println("Input new 'date_of_rent': ");
        Timestamp dateOfRent = Timestamp.valueOf(input.nextLine());
        System.out.println("Input new 'end_date_of_rent': ");
        Timestamp endDateOfRent = Timestamp.valueOf(input.nextLine());
        System.out.println("Input new 'payment_type': ");
        String paymentType = input.nextLine();
        System.out.println("Input new 'transaction_id': ");
        String transactionId = input.nextLine();
        System.out.println("Input new 'car_id': ");
        Integer carId = Integer.valueOf(input.nextLine());
        System.out.println("Input new 'driver_license_number': ");
        String driverLicenseNumber = input.nextLine();

        Rent rent = new Rent(null, dateOfRent, endDateOfRent, paymentType, transactionId, carId, driverLicenseNumber);

        int count = rentController.update(id, rent);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromRent() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        int count = rentController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllRents() {
        System.out.println("\nTable: RENT");
        List<Rent> rents = rentController.findAll();
        for (Rent rent : rents) {
            System.out.println(rent);
        }
    }

    private void findRentById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<Rent> rent = rentController.findById(id);
        System.out.println(rent.orElse(nullRent));
    }

    private void findRentsByCarId() {
        System.out.println("Input 'car_id': ");
        Integer carId = Integer.valueOf(input.nextLine());

        List<Rent> rents = rentController.findByCarId(carId);
        for (Rent rent : rents) {
            System.out.println(rent);
        }
    }

    private void findRentsByDriverLicenseNumber() {
        System.out.println("Input 'driver_license_number': ");
        String driverLicenseNumber = input.nextLine();

        List<Rent> rents = rentController.findByDriverLicenseNumber(driverLicenseNumber);
        for (Rent rent : rents) {
            System.out.println(rent);
        }
    }

    private void findRentsInDateRangeFromTo() {
        System.out.println("From: ");
        Timestamp from = Timestamp.valueOf(input.nextLine());
        System.out.println("To: ");
        Timestamp to = Timestamp.valueOf(input.nextLine());

        List<Rent> rents = rentController.findInDateRange(from, to);
        for (Rent rent : rents) {
            System.out.println(rent);
        }
    }

    // TRANSACTION

    private void createTransaction() {
        System.out.println("Input 'id': ");
        String id = input.nextLine();
        System.out.println("Input 'total_usd': ");
        Float totalUSD = Float.valueOf(input.nextLine());

        Transaction transaction = new Transaction(id, totalUSD);

        int count = transactionController.create(transaction);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateTransaction() {
        System.out.println("Input 'id': ");
        String id = input.nextLine();

        System.out.println("Input new 'total_usd': ");
        Float totalUSD = Float.valueOf(input.nextLine());

        Transaction transaction = new Transaction(id, totalUSD);

        int count = transactionController.update(id, transaction);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromTransaction() {
        System.out.println("Input 'id': ");
        String id = input.nextLine();

        int count = transactionController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllTransactions() {
        System.out.println("\nTable: TRANSACTION");
        List<Transaction> transactions = transactionController.findAll();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void findTransactionById() {
        System.out.println("Input 'id': ");
        String id = input.nextLine();

        Optional<Transaction> transaction = transactionController.findById(id);
        System.out.println(transaction.orElse(nullTransaction));
    }

    // OUTPUT
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //endregion
}


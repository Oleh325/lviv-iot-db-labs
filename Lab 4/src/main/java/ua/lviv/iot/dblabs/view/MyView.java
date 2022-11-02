package ua.lviv.iot.dblabs.view;

import ua.lviv.iot.dblabs.controller.*;
import ua.lviv.iot.dblabs.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private final Car nullBook = new Car(null, null, null, null, null, null, null, null, null, null);
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
        menu.put("45", "  45 - Find Driver by ID");
        menu.put("46", "  46 - Find Drivers by surname");

        menu.put("5", "   5 - Table: Fine");
        menu.put("51", "  51 - Create Fine");
        menu.put("52", "  52 - Update Fine");
        menu.put("53", "  53 - Delete from Fine");
        menu.put("54", "  54 - Find all Fines");
        menu.put("55", "  55 - Find Fine by ID");
        menu.put("56", "  56 - Find Fine by driver_license_number");

        menu.put("6", "   6 - Table: Parking");
        menu.put("61", "  61 - Create Parking");
        menu.put("62", "  62 - Update Parking");
        menu.put("63", "  63 - Delete from Parking");
        menu.put("64", "  64 - Find all Parkings");
        menu.put("65", "  65 - Find Parking by ID");
        menu.put("66", "  66 - Find Parking by city_id");

        menu.put("7", "   7 - Table: Rent");
        menu.put("71", "  71 - Create Rent");
        menu.put("72", "  72 - Update Rent");
        menu.put("73", "  73 - Delete from Rent");
        menu.put("74", "  74 - Find all Rents");
        menu.put("75", "  75 - Find Rent by ID");
        menu.put("76", "  76 - Find Rent by car_id");
        menu.put("77", "  77 - Find Rent by driver_license_number");
        menu.put("78", "  78 - Find Rent in date range from _ to _");

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

        methodsMenu.put("31", this::createPerson);
        methodsMenu.put("32", this::updatePerson);
        methodsMenu.put("33", this::deleteFromPerson);
        methodsMenu.put("34", this::findAllPersons);
        methodsMenu.put("35", this::findPersonById);
        methodsMenu.put("36", this::findAllBooksById);
        methodsMenu.put("37", this::addBookByNameToPersonBySurname);
    }

    private void selectAllTable() {
        findAllBooks();
        findAllCities();
        findAllPersons();
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
        System.out.println(car.orElse(nullBook));
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

    //endregion
    // region CITY ---------------------------------------------------
    private void createCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        City city = new City(cityName);

        int count = cityController.create(city);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String newCityName = input.nextLine();

        City city = new City(newCityName);

        int count = cityController.update(cityName, city);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        int count = cityController.delete(cityName);
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
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        Optional<City> city = cityController.findById(cityName);
        System.out.println(city.orElse(nullCity));
    }

    // endregion
    // region PERSON -------------------------------------------------
    private void createPerson() {
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'city': ");
        String city = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();

        Person person = new Person(null, surname, name, city, email);

        int count = personController.create(person);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updatePerson() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'city': ");
        String city = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();

        Person person = new Person(null, surname, name, city, email);

        int count = personController.update(id, person);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromPerson() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = personController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllPersons() {
        System.out.println("\nTable: PERSON");
        List<Person> people = personController.findAll();
        for (Person person : people) {
            System.out.println(person);
        }
    }

    private void findPersonById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Person> person = personController.findById(id);
        System.out.println(person.orElse(nullPerson));
    }

    private void findAllBooksById() {
        System.out.println("Input 'person id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        List<Book> books = personController.findAllBooksBy(id);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addBookByNameToPersonBySurname() {
        System.out.println("Input 'person surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'book name': ");
        String bookName = input.nextLine();

        String msg = personController.addBookByNameToPersonBySurname(bookName, surname);
        System.out.println(msg);
    }
    //endregion

    //-------------------------------------------------------------------------
    // region output
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


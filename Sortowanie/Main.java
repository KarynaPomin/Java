import Classes.Car;
import Classes.ElectricCar;
import Classes.GasolineCar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Car> Cars = new ArrayList<>();
        Cars.add(new ElectricCar("Tesla", "Model S", 2020, "Jan Kowalski"));
        Cars.add(new GasolineCar("Ford", "Mustang", 2018, "Anna Nowak"));
        Cars.add(new ElectricCar("Nissan", "Leaf", 2019, " Piotr Wiśniewski"));
        Cars.add(new GasolineCar("BMW", "X5", 2017, "Katarzyna Zielińska"));

        // Sortowanie tylko według roku (Comparable<Car>)
        Collections.sort(Cars);

        // Wypisuje wszystkie elementy z listy za pomocą metody foreach
        // Cars.forEach(System.out::println);
        // cars.forEach(car -> System.out.println(car));
        System.out.println("\nSortowanie według roku: ");
        Cars.forEach(Car::StartEngine);

        // Sortowanie według więcej niż jeden element
        // reversed --> wartości malejące
        Cars.sort(Comparator.comparing(Car::getYear).reversed().thenComparing(Car::getModel));

        System.out.println("\nSortowanie według roku malejąco i modelu alfabetycznie: ");
        Cars.forEach(Car::StartEngine);
    }
}

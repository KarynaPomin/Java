package Classes;

public class ElectricCar extends Car{

    public ElectricCar(String Brand, String Model, int Year, String Owner){
        super(Brand, Model, Year, Owner);
    }

    @Override
    public void Drive() {
        System.out.println("Jazda na elektryczności!");
    }
}

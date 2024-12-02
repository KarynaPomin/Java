package Classes;

public class GasolineCar extends Car{
    public GasolineCar(String Brand, String Model, int Year, String Owner){
        super(Brand, Model, Year, Owner);
    }

    @Override
    public void Drive() {
        System.out.println("Jazda na benzynie!");
    }
}

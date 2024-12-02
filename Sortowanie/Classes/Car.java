package Classes;

// Klasa implementująca interfejs
public abstract class Car implements ICar, Comparable<Car>{

    public String Brand;
    public String Model;
    public int Year;
    public String Owner;

    Car(String Brand, String Model, int Year, String Owner){
        this.Brand = Brand;
        this.Model = Model;
        this.Year = Year;
        this.Owner = Owner;
    }

    @Override
    public void StartEngine(){
        // String.format("%s %s %d") --> %s dla symboli, %d dla liczb
        System.out.println(String.format("%s %s %d %s", Brand, Model, Year, Owner));
    }

    @Override
    public abstract void Drive();

    public int compareTo(Car other){
        if (other == null) return 1;
        return Double.compare(this.Year, other.Year);
    }

    public String getModel(){
        return Model;
    }

    public String getBrand() {
        return Brand;
    }

    public int getYear() {
        return Year;
    }

    public String getOwner() {
        return Owner;
    }
}

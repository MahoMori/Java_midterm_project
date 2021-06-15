package animal_types;

import parent.Animal;

public class Fish extends Animal{
    private String live;
    private String gills;


    public Fish() {
        this.live = "live in water";
        this.gills = "has gills";
    }

    public Fish(String live, String gills, int height, int weight, String animalType, String bloodType) {
        super(height, weight, animalType, bloodType);
        this.live = live;
        this.gills = gills;
    }

    public String getLive() {
        return live;
    }
    public String getGills() {
        return gills;
    }
}

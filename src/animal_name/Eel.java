package animal_name;

import animal_types.Fish;

public class Eel extends Fish{
    private String characteristic;
    
    public Eel() {
        this.characteristic = "release electric charge";
    }

    public Eel(String characteristic, String live, String gills, int height, int weight, String animalType, String bloodType) {
        super(live, gills, height, weight, animalType, bloodType);
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }
}

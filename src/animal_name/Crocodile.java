package animal_name;

import animal_types.Reptile;

public class Crocodile extends Reptile{
    private String eggs;

    public Crocodile() {
        this.eggs = "hard-shelled eggs";
    }

    public Crocodile(String skin, String bone, String eggs, int height, int weight, String animalType, String bloodType) {
        super(skin, bone, eggs,height, weight, animalType, bloodType);
        this.eggs = eggs;
    }

    public String getEggs() {
        return eggs;
    }
    
}

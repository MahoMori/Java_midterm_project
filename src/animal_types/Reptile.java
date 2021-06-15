package animal_types;

import parent.Animal;

public class Reptile extends Animal {
    private String skin;
    private String bone;
    private String eggs;

    public Reptile() {
        this.skin = "dry skin";
        this.bone = "backbone";
        this.eggs = "soft-shelled eggs";
    }

    public Reptile(String skin, String bone, String eggs, int height, int weight, String animalType, String bloodType) {
        super(height, weight, animalType, bloodType);
        this.skin = skin;
        this.bone = bone;
        this.eggs = eggs;
    }

    public String getSkin() {
        return skin;
    }
    public String getBone() {
        return bone;
    }
    public String getEggs() {
        return eggs;
    }
}

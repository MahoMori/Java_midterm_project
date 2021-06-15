package animal_types;

import parent.Animal;

public class Bird extends Animal{
    private String feathers;
    private String fly;


    public Bird() {
        this.feathers = "with feathers";
        this.fly = "can fly";
    }

    public Bird(String feathers, String fly, int height, int weight, String animalType, String bloodType) {
        super(height, weight, animalType, bloodType);
        this.feathers = feathers;
        this.fly = fly;
    }

    public String getFeathers() {
        return feathers;
    }
    public String getFly() {
        return fly;
    }
}

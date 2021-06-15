import animal_name.Crocodile;
import animal_name.Crocodile_bird;
import animal_name.Eel;

public class Demo {
    public static void main(String[] args) {
        
        Crocodile crocodile = new Crocodile("dry skin", "backbone", "soft-shelled eggs", 123, 123, "reptile", "cold-blooded");

        Eel eel = new Eel("release electric charge", "live in water", "has gills", 123, 123, "fish", "cold-blooded");

        Crocodile_bird crocodile_bird = new Crocodile_bird("with feathers", "can fly", 123, 123, "bird", "warm-blooded");

        System.out.println("Crocodile: " + crocodile.getSkin() + ", " + crocodile.getBone() + ", " + crocodile.getEggs() + ", " + crocodile.getHeight() + ", " + crocodile.getWeight() + ", " + crocodile.getAnimalType() + ", " + crocodile.getBloodType());

        System.out.println("Eel: " + eel.getCharacteristic() + ", " + eel.getHeight() + ", " + eel.getWeight() + ", " + eel.getAnimalType() + ", " + eel.getBloodType());

        System.out.println("Crocodile_bird: " + crocodile_bird.getFeathers() + ", " + crocodile_bird.getFly() + ", " + crocodile_bird.getHeight() + ", " + crocodile_bird.getWeight() + ", " + crocodile_bird.getAnimalType() + ", " + crocodile_bird.getBloodType());
    }
}

import animal_name.Crocodile;
import animal_name.Crocodile_bird;
import animal_name.Eel;

public class Demo {
    public static void main(String[] args) {
        
        Crocodile crocodile = new Crocodile();

        Eel eel = new Eel();

        Crocodile_bird crocodile_bird = new Crocodile_bird();

        System.out.println("Crocodile: " + crocodile.getSkin() + ", " + crocodile.getBone() + ", " + crocodile.getEggs() + ", " + crocodile.getHeight() + ", " + crocodile.getWeight() + ", " + crocodile.getAnimalType() + ", " + crocodile.getBloodType());

        System.out.println("Eel: " + eel.getCharacteristic() + ", " + eel.getHeight() + ", " + eel.getWeight() + ", " + eel.getAnimalType() + ", " + eel.getBloodType());

        System.out.println("Crocodile_bird: " + crocodile_bird.getFeathers() + ", " + crocodile_bird.getFly() + ", " + crocodile_bird.getHeight() + ", " + crocodile_bird.getWeight() + ", " + crocodile_bird.getAnimalType() + ", " + crocodile_bird.getBloodType());
    }
}

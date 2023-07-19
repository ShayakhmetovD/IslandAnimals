package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Pig extends Herbivore {
    public Pig() {
        super(400, 50, "Кабан", 2, 50);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Мышь" -> 0.5;
            case "Гусеница" -> 0.9;
            case "Растение" -> 1;
            default -> 0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Pig){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Pig(),locations.getX(),locations.getY());
        }
    }
}

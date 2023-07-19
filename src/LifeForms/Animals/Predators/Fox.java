package LifeForms.Animals.Predators;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Fox extends Predator {
    public Fox() {
        super(8, 30, "Лиса", 2, 2);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Гусеница" -> 0.4;
            case "Утка" -> 0.6;
            case "Кролик" -> 0.7;
            case "Мышь" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Fox){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Fox(),locations.getX(),locations.getY());
        }
    }
}

package LifeForms.Animals.Predators;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 20, "Орел", 3, 1);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Лиса" -> 0.1;
            case "Утка" -> 0.8;
            case "Кролик", "Мышь" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Eagle){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Eagle(),locations.getX(),locations.getY());
        }
    }
}

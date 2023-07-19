package LifeForms.Animals.Predators;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Snake extends Predator {
    public Snake() {
        super(15, 30, "Змея", 1, 3);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Утка" -> 0.1;
            case "Лиса" -> 0.15;
            case "Кролик" -> 0.2;
            case "Мышь" -> 0.4;
            default -> 0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Snake){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Snake(),locations.getX(),locations.getY());
        }
    }
}

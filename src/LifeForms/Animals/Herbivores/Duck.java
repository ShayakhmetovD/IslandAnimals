package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 200, "Утка", 4, 0.15);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Гусеница" -> 0.9;
            case "Растение" ->  1;
            default ->  0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Duck){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Duck(),locations.getX(),locations.getY());
        }
    }
}

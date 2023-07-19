package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;
import LifeForms.LifeForm;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 500, "Мышь", 1, 0.01);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Гусеница" -> 0.9;
            case "Растение" -> 1;
            default -> 0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Mouse){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Mouse(),locations.getX(),locations.getY());
        }
    }
}

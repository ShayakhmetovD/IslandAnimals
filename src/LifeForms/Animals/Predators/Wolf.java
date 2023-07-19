package LifeForms.Animals.Predators;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 30, "Волк", 3, 8);
    }


    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Лошадь", "Буйвол" -> 0.1;
            case "Олень", "Кабан" -> 0.15;
            case "Утка"-> 0.4;
            case "Коза","Кролик" -> 0.6;
            case "Овца" -> 0.7;
            case "Мышь" -> 0.8;
            default -> 0;
        };
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Wolf){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Wolf(),locations.getX(),locations.getY());
        }
    }
}

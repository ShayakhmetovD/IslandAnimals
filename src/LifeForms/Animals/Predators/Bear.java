package LifeForms.Animals.Predators;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Bear extends Predator {
    public Bear() {
        super(500, 5, "Медведь", 2, 80);
    }


    @Override
    public double chanceToEat(String foodName) {

        return switch (foodName){
            case "Утка" -> 0.1;
            case "Буйвол" -> 0.2;
            case "Лошадь" -> 0.4;
            case "Кабан" -> 0.5;
            case "Коза", "Овца" -> 0.7;
            case "Олень", "Кролик", "Змея" -> 0.8;
            case "Мышь" -> 0.9;
            default -> 0;
        };
    }



    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Bear){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Bear(),locations.getX(),locations.getY());
        }
    }
}

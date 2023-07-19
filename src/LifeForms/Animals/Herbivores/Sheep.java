package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Sheep extends Herbivore {
    public Sheep() {
        super(70, 140, "Овца", 3, 15);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Sheep){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Sheep(),locations.getX(),locations.getY());
        }
    }
}

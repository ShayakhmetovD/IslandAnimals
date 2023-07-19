package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;
import LifeForms.LifeForm;

public class Horse extends Herbivore {

    public Horse() {
        super(400, 20, "Лошадь", 4, 60);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Horse){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Horse(),locations.getX(),locations.getY());
        }
    }
}

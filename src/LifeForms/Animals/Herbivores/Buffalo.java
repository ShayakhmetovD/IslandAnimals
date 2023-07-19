package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;


public class Buffalo extends Herbivore {
    public Buffalo() {
        super(700, 10, "Буйвол", 3, 100);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Buffalo){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Buffalo(),locations.getX(),locations.getY());
        }
    }
}

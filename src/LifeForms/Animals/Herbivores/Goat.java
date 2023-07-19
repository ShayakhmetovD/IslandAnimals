package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;


public class Goat extends Herbivore {
    public Goat() {
        super(60, 140, "Коза", 3, 10);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Goat){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Goat(),locations.getX(),locations.getY());
        }
    }
}

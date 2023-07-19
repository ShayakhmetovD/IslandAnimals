package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;


public class Deer extends Herbivore {
    public Deer() {
        super(300, 20, "Олень", 4, 50);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Deer){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Deer(),locations.getX(),locations.getY());
        }
    }
}

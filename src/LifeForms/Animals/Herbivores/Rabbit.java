package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 150, "Кролик", 2, 0.45);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Rabbit){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Rabbit(),locations.getX(),locations.getY());
        }
    }
}

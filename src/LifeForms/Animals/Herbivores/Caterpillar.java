package LifeForms.Animals.Herbivores;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;
import LifeForms.LifeForm;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(0.01, 1000, "Гусеница", 0, 0);
    }


    @Override
    public void reproduction(Animal animal) {
        if(animal instanceof Caterpillar){
            Locations locations = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
            IslandModel.getInstance().addAnimal(new Caterpillar(),locations.getX(),locations.getY());
        }
    }
}

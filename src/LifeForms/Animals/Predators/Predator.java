package LifeForms.Animals.Predators;

import LifeForms.Animals.Animal;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxPopulationForm, String name, int step, double maxHp) {
        super(weight, maxPopulationForm, name, step, maxHp);
    }
}

package LifeForms.Animals.Herbivores;

import LifeForms.Animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxPopulationForm, String name, int step, double maxHp) {
        super(weight, maxPopulationForm, name, step, maxHp);
    }

    @Override
    public double chanceToEat(String foodName) {
        return switch (foodName) {
            case "Растение" -> 1;
            default -> 0;
        };
    }
}

package Simulation.Threads.TasksOfLifeAnimals.Tasks;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;
import Simulation.Simulation;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/** Данный класс является реализацией многопоточности по процессу уменьшения здоровья животных на острове */
public class TaskOfReducingEnergyHP implements Runnable{
    private double reducingHP = 10;
    private int animalsDiedByHungry;
    private final CountDownLatch latch;

    public TaskOfReducingEnergyHP(CountDownLatch latch){
        this.latch = latch;
    }


    @Override
    public void run() {
        List<Animal> animalList = IslandModel.getInstance().getAllAnimal().stream().
                filter(c -> c.getMaxHp() > 0).toList();


        if (Simulation.getInstance().getTimeNow() / 60 >= 3) {
            reducingHP = reducingHP * 2;
        }

        for (Animal animal:animalList) {
            double newHP = animal.getHp() - reducingHP;
            if (newHP > 0){
                animal.setHp(newHP);
            } else {
                Locations location = IslandModel.getInstance().getLocation(animal.getX(),animal.getY());
                IslandModel.getInstance().removeAnimal(animal,location.getX(),location.getY());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }

    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}

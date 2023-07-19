package Simulation.Threads.TasksOfLifeAnimals.Tasks;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;
import Simulation.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/** Данный класс является реализацией многопоточности по процессу размножения животных на острове */
public class TaskOfReproduction implements Runnable{
    private int babies;
    private CountDownLatch latch;

    public TaskOfReproduction(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("START_OF_REPRODUCTION");
        List<Animal> animals = IslandModel.getInstance().getAllAnimal();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {   //проверка на умерли ли все животные

            for (Animal currentAnimal : animals) {

                if (!(animalsMultiplied.contains(currentAnimal))) {
                    Locations location = IslandModel.getInstance().getLocation(currentAnimal.getX(), currentAnimal.getY());
                    List<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!(animalsMultiplied.contains(partner))) {
                                currentAnimal.reproduction(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
        System.out.println("Babies: " + babies);
        System.out.println("REPRODUCTION_THE_END");
    }

    public int getBabies() {
        return babies;
    }
}

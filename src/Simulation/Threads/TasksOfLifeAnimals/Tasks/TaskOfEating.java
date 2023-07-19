package Simulation.Threads.TasksOfLifeAnimals.Tasks;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Animal;
import LifeForms.LifeForm;
import LifeForms.Plants.Plant;
import Simulation.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/** Данный класс является реализацией многопоточности по процессу получения еды животными на острове */
public class TaskOfEating implements Runnable{
    private final CountDownLatch latch;
    private int animalsEaten;

    public TaskOfEating(CountDownLatch latch){
        this.latch = latch;
    }


    @Override
    public void run() {

        List<Animal> animalList = IslandModel.getInstance().getAllAnimal();
        List<LifeForm> lifeFormEatenList = new ArrayList<>();
        List<Animal> filterCaterpillar = animalList.stream().filter(c -> !(c.getName().equals("Гусеница"))).toList();

        if (animalList.size() > 0 && filterCaterpillar.size() > 0) {

            for (Animal animal : animalList) {
                if (animal.getMaxHp() > 0) {
                    Locations location = IslandModel.getInstance().getLocation(animal.getX(), animal.getY());
                    List<LifeForm> locationLifeForms = location.getLifeForms();

                    if(!locationLifeForms.isEmpty()){
                        for (LifeForm lifeForm:locationLifeForms) {
                            if(animal.chanceToEat(lifeForm.getName()) > 0
                                    && !(lifeFormEatenList.contains(lifeForm))){
                                boolean isEaten = animal.eat(lifeForm);

                                if (isEaten){
                                    if(lifeForm instanceof Animal animalEaten){
                                        if (location.getAnimals().contains(animalEaten)){
                                            IslandModel.getInstance().removeAnimal(animalEaten, lifeForm.getX(), location.getY());
                                        }
                                        lifeFormEatenList.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) lifeForm;
                                        if(location.getPlants().size() > 0){
                                            IslandModel.getInstance().removePlant(plant, plant.getX(), plant.getY());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("ВСЕ ЖИВОТНЫЕ УМЕРЛИ !");
            Simulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }

    public int getAnimalsEaten() {
        return animalsEaten;
    }
}

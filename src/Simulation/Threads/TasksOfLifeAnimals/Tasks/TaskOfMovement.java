package Simulation.Threads.TasksOfLifeAnimals.Tasks;


import Fields.IslandModel;
import LifeForms.Animals.Animal;

import java.util.List;

/*
* Данный класс, необходим для выполнения задач потоками по перемещению животных по острову.
* */

/** Данный класс является реализацией многопоточности по процессу передвижения животных на острове */

public class TaskOfMovement implements Runnable{
    @Override
    public void run() {


        List<Animal> allAnimals = IslandModel.getInstance().getAllAnimal().
                stream().filter(animal -> animal.getStep() > 0).toList();
        allAnimals.forEach(Animal::move);


    }
}

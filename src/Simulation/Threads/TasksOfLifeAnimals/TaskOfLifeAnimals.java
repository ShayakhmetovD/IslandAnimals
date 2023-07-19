package Simulation.Threads.TasksOfLifeAnimals;


import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfEating;
import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfMovement;
import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfReducingEnergyHP;
import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfReproduction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/** Данный класс является реализацией многопоточности по процессу жизненного цикла животных на острове */
public class TaskOfLifeAnimals implements Runnable{
    private final TaskOfEating eating;
    private final TaskOfReproduction reproduction;
    private final TaskOfReducingEnergyHP reducingEnergyHP;
    private final CountDownLatch latch; // класс, реализующий процесс ожидания завершения некоторых операций

    public TaskOfLifeAnimals() {
        latch = new CountDownLatch(3);
        eating = new TaskOfEating(latch);
        reproduction = new TaskOfReproduction(latch);
        reducingEnergyHP = new TaskOfReducingEnergyHP(latch);
    }


    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(eating); // получение еды
        executorService.submit(reproduction); // размножение
        executorService.submit(reducingEnergyHP); // уменьшение здоровья в случае голодания или смерть

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.submit(new TaskOfMovement()); // процесс движения
    }

    public TaskOfEating getEating() {
        return eating;
    }

    public TaskOfReproduction getReproduction() {
        return reproduction;
    }

    public TaskOfReducingEnergyHP getReducingEnergyHP() {
        return reducingEnergyHP;
    }
}

package Simulation.Threads;

import Simulation.Simulation;


/** Данный класс является реализацией многопоточности по процессу роста растений */
public class TaskOfMovementPlants implements Runnable{


    @Override
    public void run() {

        if(Simulation.getInstance().getTimeNow() >= 4){
            Simulation.getInstance().placePlants(5);
        } else Simulation.getInstance().placePlants(2);
    }
}

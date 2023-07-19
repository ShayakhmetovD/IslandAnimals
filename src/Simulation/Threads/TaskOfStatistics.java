package Simulation.Threads;

import Fields.IslandModel;
import Simulation.Simulation;
import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfEating;
import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfReducingEnergyHP;
import Simulation.Threads.TasksOfLifeAnimals.Tasks.TaskOfReproduction;

/** Данный класс является реализацией многопоточности по процессу сбора статистики на острове */

public class TaskOfStatistics implements Runnable{
    private boolean isTimeOver;
    private int babies; // Количество детенышей
    private int animalsEaten; // Количество детенышей
    private int animalsDiedByHungry; // Количество умерших животных
    private int countAnimalsEnd; // Количество животных
    private int countPlants; // Количество растений
    private static int cycle = 0;
    private final TaskOfReproduction animalReproduction; // Задача по размножению
    private final TaskOfEating animalEating; // Задача по получению еды
    private final TaskOfReducingEnergyHP animalReducingHP; // Задача по уменьшению здоровья

    public TaskOfStatistics(TaskOfEating animalEating, TaskOfReducingEnergyHP animalReducingHP, TaskOfReproduction animalReproduction) {
        this.animalEating = animalEating;
        this.animalReducingHP = animalReducingHP;
        this.animalReproduction = animalReproduction;
    }

    @Override
    public void run() {
        long timeNow = Simulation.getInstance().getTimeNow();
        isTimeOver = checkTime(timeNow);

        babies = animalReproduction.getBabies();
        countAnimalsEnd = IslandModel.getInstance().getAllAnimal().size(); // кол-во животных на острове
        animalsEaten = animalEating.getAnimalsEaten(); // кол-во животных умерло
        animalsDiedByHungry = animalReducingHP.getAnimalsDiedByHungry(); // кол-во животных умерло
        countPlants = IslandModel.getInstance().getAllPlants().size();
        printStats();

        if (isTimeOver) {
            Simulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }

    /**
     * Проверить, истекло ли заданное время моделирования
     *
     * @param timeNow Текущее время моделирования
     * @return isTimeOver true, если время истекло, иначе - false
     */
    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 5;
    }

    // Вывести статистику моделирования
    private void printStats() {
        if (isTimeOver) {
            System.out.println("ПОБЕДА!!! ВЫ ПРОДЕРЖАЛИСЬ 5 МИНУТ!");
            System.out.println("----------------------------------");
        } else {
            System.out.printf("--- ДЕНЬ %d ---", cycle);
            cycle++;
            System.out.println();
        }
        System.out.println();
        System.out.println("СТАТИСТИКА ПО ОСТРОВУ");
        System.out.println();

        System.out.print("Животных на острове: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Животных умерло от голода: ");
        System.out.println(animalsDiedByHungry);

        System.out.print("Животных съедено: ");
        System.out.println(animalsEaten);

        System.out.print("Детенышей родилось: ");
        System.out.println(babies);

        System.out.print("Растений на острове: ");
        System.out.println(countPlants);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

}

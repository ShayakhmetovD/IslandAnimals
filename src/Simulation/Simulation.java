package Simulation;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.Animals.Herbivores.*;
import LifeForms.Animals.Predators.*;
import LifeForms.Plants.Plant;
import Simulation.Threads.TasksOfLifeAnimals.TaskOfLifeAnimals;
import Simulation.Threads.TaskOfMovementPlants;
import Simulation.Threads.TaskOfStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


/** Данный класс является реализацией симулиции процесса жизни на острове */
public class Simulation {
    private final long startTime;
    private final int countHerbivores = 20; // Количество травоядных
    private final int countPlants = 20; // Количество растений
    private final int countPredators = 10; // Количество хищников
    private static volatile Simulation instance;
    private volatile ScheduledExecutorService executorService;

    private Simulation() {
        startTime = System.currentTimeMillis();
    }


    public static Simulation getInstance() {
        if (instance == null) {
            synchronized (Simulation.class) {
                if (instance == null) {
                    instance = new Simulation();
                }
            }
        }
        return instance;
    }

//    Создаем модель острова со всеми видами животных и растений
    public void createIslandModel(){

        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }


//     Запуск острова
    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        TaskOfLifeAnimals animalLifecycleTask = new TaskOfLifeAnimals();
        TaskOfMovementPlants taskOfMovementPlants = new TaskOfMovementPlants();
        TaskOfStatistics taskOfStatistics = new TaskOfStatistics(animalLifecycleTask.getEating(), animalLifecycleTask.getReducingEnergyHP(), animalLifecycleTask.getReproduction());

        executorService.scheduleAtFixedRate(animalLifecycleTask, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(taskOfMovementPlants, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(taskOfStatistics, 0, 8, TimeUnit.SECONDS);
    }


//     Создаем список травоядных с заданным количеством
    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();

        // Создаем по одному животному каждого вида
        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new Pig());

        // Генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                // Создаем экземпляр животного через рефлексию
                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }

    //     Создаем список хищников с заданным количеством
    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();

        // Создаем по одному животному каждого вида
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());

        // Генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {
                // Создаем экземпляр животного через рефлексию
                Predator newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return predators;
    }

    //     Создаем список растений с заданным количеством
    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    //     Размещаем травоядных на острове
    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placedHerbivores = false;
            while (!placedHerbivores) {
                int x = random.nextInt(IslandModel.getInstance().getX());
                int y = random.nextInt(IslandModel.getInstance().getY());
                Locations location = IslandModel.getInstance().getLocation(x, y);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulationForm()) {
                    IslandModel.getInstance().addAnimal(herbivore, x, y);
                    placedHerbivores = true;
                }
            }
        }
    }

    //     Размещаем хищников на острове
    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placedPredators = false;
            while (!placedPredators) {
                int x = random.nextInt(IslandModel.getInstance().getX());
                int y = random.nextInt(IslandModel.getInstance().getY());
                Locations location = IslandModel.getInstance().getLocation(x, y);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(predator.getName())).toList().size() <= predator.getMaxPopulationForm()) {
                    IslandModel.getInstance().addAnimal(predator, x, y);
                    placedPredators = true;
                }
            }
        }
    }

    //     Размещаем растения на острове
    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placedPlants = false;
            while (!placedPlants) {
                int x = random.nextInt(IslandModel.getInstance().getX());
                int y = random.nextInt(IslandModel.getInstance().getY());
                Locations location = IslandModel.getInstance().getLocation(x, y);
                if (location.getPlants().size() <= plant.getMaxPopulationForm()) {
                    IslandModel.getInstance().addPlant(plant, x, y);
                    placedPlants = true;
                }
            }
        }
    }

    // Получение текущего времени
    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountPredators() {
        return countPredators;
    }
}
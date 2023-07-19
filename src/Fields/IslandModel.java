package Fields;

import LifeForms.Animals.Animal;
import LifeForms.Plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/** Данный классс реализует модель острова с установленными параметрами */

public class IslandModel {

    private Locations[][] locations;
    private final int x;
    private final int y;
    private static volatile IslandModel instance;


    public IslandModel(int x, int y) {
        this.x = x;
        this.y = y;
        this.locations = new Locations[x][y];
    }

    public void initializeLocations(){
        locations = new Locations[x][y];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Locations(i,j);
            }
        }
    }

    public static IslandModel getInstance(){
        if(instance == null){
            synchronized (IslandModel.class){
                if(instance == null){
                    instance = new IslandModel(100, 20);
                }
            }
        }
        return instance;
    }

    // Получение локации
    public Locations getLocation(int x, int y){
        return locations[x][y];
    }

    // Добавление животного
    public void addAnimal(Animal animal, int x, int y){
        Locations locations = getLocation(x,y);
        locations.addAnimal(animal);
    }

    // Удаление животного
    public void removeAnimal(Animal animal, int x, int y){
        Locations locations = getLocation(x, y);
        locations.removeAnimal(animal);
    }

    // Добавление растения
    public void addPlant(Plant plant, int x, int y){
        Locations locations = getLocation(x, y);
        locations.addPlant(plant);
    }


    // Удаление растения
    public void removePlant(Plant plant, int x, int y){
        Locations locations = getLocation(x, y);
        locations.removePlant(plant);
    }

    // Получение списка животных
    public synchronized List<Animal> getAllAnimal(){
        List<Animal> animals = new ArrayList<>();
        for (Locations[] x :locations) {
            for (Locations location : x) {
                animals.addAll(location.getAnimals());
            }
        }
        return animals;
    }

    // Получение списка растений
    public List<Plant> getAllPlants() {
        List<Plant> plants = new ArrayList<>();
        for (Locations[] x : locations) {
            for (Locations location : x) {
                plants.addAll(location.getPlants());
            }
        }
        return plants;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

}

package Fields;

import LifeForms.Animals.Animal;
import LifeForms.LifeForm;
import LifeForms.Plants.Plant;

import java.util.ArrayList;
import java.util.List;

/** Данный класс реализует инициализацию локаций и основные методы работы с животными/растениями */

public class Locations {
    private int x;
    private int y;
    List<Animal> animals;
    List<Plant> plants;

    public Locations(int x, int y){
        this.x = x;
        this.y = y;
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    // Добавление животного
    public void addAnimal(Animal animal){
        animal.setX(x);
        animal.setY(y);
        animals.add(animal);
    }

    // Удаление животного
    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }

    // Получение списка животных
    public List<Animal> getAnimals() {
        return animals;
    }

    // Добавление растения
    public void addPlant(Plant plant){
        plant.setX(x);
        plant.setY(y);
        plants.add(plant);
    }

    // Удаление растения
    public void removePlant(Plant plant){
        plants.remove(plant);
    }

    // Получение списка растений
    public List<Plant> getPlants() {
        return plants;
    }

    // Получение общего списка животных/растений
    public List<LifeForm> getLifeForms(){
        List<LifeForm> allLifeForms = new ArrayList<>();
        allLifeForms.addAll(animals);
        allLifeForms.addAll(plants);

        return allLifeForms;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

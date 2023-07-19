import Fields.IslandModel;
import LifeForms.Animals.Animal;
import LifeForms.Animals.Herbivores.Deer;
import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        System.out.println("Запуск острова");

        IslandModel.getInstance().initializeLocations(); //Создание и инициализация локаций
        Simulation.getInstance().createIslandModel(); // Симуляция острова
    }
}
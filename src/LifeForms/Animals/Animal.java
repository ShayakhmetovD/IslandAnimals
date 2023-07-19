package LifeForms.Animals;

import Fields.IslandModel;
import Fields.Locations;
import LifeForms.LifeForm;
import LifeForms.Plants.Plant;
import LifeForms.Reproduction;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/** Данный абстрактный класс является родителем для всех видов животных */
public abstract class Animal extends LifeForm implements Reproduction {
    private final int step; // Скорость перемещения, не более чем, клеток за ход
    private final double maxHp; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    private double Hp; // Количество здоровья животного


    public Animal(double weight, int maxPopulationForm, String name, int step, double maxHp) {
        super(weight, maxPopulationForm, name);
        this.step = step;
        this.maxHp = maxHp;
        this.Hp = maxHp;
    }

    // Метод реализующий процесс поедания еды
    public boolean eat(Object forms){

        double chanceToEat;
        LifeForm lifeForm = null;
        boolean animalEatFood;

        if (forms instanceof LifeForm) {
            lifeForm = (LifeForm) forms;
        } else {
            System.out.println("Объект не является животным или растением");
        }

        String foodName = lifeForm.getName();
        chanceToEat = chanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения
            Locations location = IslandModel.getInstance().getLocation(lifeForm.getX(), lifeForm.getY()); // Животное/растение удаляется из списка обиталей локации после съедения
            if (lifeForm instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    IslandModel.getInstance().removeAnimal(animal, location.getX(), location.getY());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) lifeForm;
                if (location.getPlants().size() > 0) {
                    IslandModel.getInstance().removePlant(plant, location.getX(), location.getY());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }

    // Метод реализующий процесс передвижения
    public void move(){
        Random random = new Random();
        int randomCells = random.nextInt(getStep()) + 1;

        // Генерируем случайное направление (вверх, вниз, влево или вправо)
        int direction = random.nextInt(4);

        // Вычисляем новые координаты в зависимости от направления
        int newX = getX();
        int newY = getY();

        switch (direction) {
            case 0 -> // Вверх
                    newX -= randomCells;
            case 1 -> // Вниз
                    newX += randomCells;
            case 2 -> // Влево
                    newY -= randomCells;
            case 3 -> // Вправо
                    newY += randomCells;
        }

        // Проверяем, чтобы новые координаты не выходили за границы поля
        while (newX < 0 || IslandModel.getInstance().getX() >= 20
                || newY < 0 || newY >= IslandModel.getInstance().getY()) {
            randomCells = random.nextInt(getStep() + 1);
            direction = random.nextInt(4);

            newX = getX();
            newY = getY();

            switch (direction) {
                case 0 -> // Вверх
                        newX -= randomCells;
                case 1 -> // Вниз
                        newX += randomCells;
                case 2 -> // Влево
                        newY -= randomCells;
                case 3 -> // Вправо
                        newY += randomCells;
            }
        }

        //Удаляем животное с ячейки со старыми координатами и добавляем на ячейку с новыми
        IslandModel.getInstance().removeAnimal(this,getX(),getY());

        setX(newX);
        setY(newY);
        IslandModel.getInstance().addAnimal(this,newX,newY);
    }
    public int getStep(){
        return step;
    }

    public double getHp() {
        return Hp;
    }

    public void setHp(double hp) {
        Hp = hp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public abstract double chanceToEat(String foodName);
}

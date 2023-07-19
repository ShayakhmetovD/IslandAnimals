package LifeForms;


/** Данный класс является родителем для всех видов существ на острове */

public class LifeForm {
    private final double weight; //вес животного/растения
    private final int maxPopulationForm; //максимальное количество вида на 1 клетке
    private final String name; //имя животного/растения
    public int x;
    public int y;

    public LifeForm(double weight, int maxPopulationForm, String name){
        this.weight = weight;
        this.maxPopulationForm = maxPopulationForm;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPopulationForm() {
        return maxPopulationForm;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

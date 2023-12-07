package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        boolean proteins = foodstuffs.stream().anyMatch(Food::getProteins);
        boolean fats = foodstuffs.stream().anyMatch(Food::getFats);
        boolean carbohydrates = foodstuffs.stream().anyMatch(Food::getCarbohydrates);
    
        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }
    
        List<T> thingsToAdd = market.getThings(clazz).stream()
                .filter(thing -> (!proteins && thing.getProteins())
                        || (!fats && thing.getFats())
                        || (!carbohydrates && thing.getCarbohydrates()))
                .collect(Collectors.toList());
    
        foodstuffs.addAll(thingsToAdd);
    
        proteins = foodstuffs.stream().anyMatch(Food::getProteins);
        fats = foodstuffs.stream().anyMatch(Food::getFats);
        carbohydrates = foodstuffs.stream().anyMatch(Food::getCarbohydrates);
    
        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }


    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }

}

package com.company.enums;

import java.util.ArrayList;
import java.util.List;

public enum Pizzas {
    Margherita {
        @Override
        public List<Ingredients> getIngredients() {
            return new ArrayList<>(List.of(Ingredients.dough, Ingredients.flour, Ingredients.cheese));
        }
    },
    Capriciosa {
        @Override
        public List<Ingredients> getIngredients() {
            return new ArrayList<>(List.of(Ingredients.dough, Ingredients.flour, Ingredients.cheese, Ingredients.ham,
                    Ingredients.basil, Ingredients.olives));
        }
    },
    Calzone {
        @Override
        public List<Ingredients> getIngredients() {
            return new ArrayList<>(List.of(Ingredients.dough, Ingredients.flour, Ingredients.cheese, Ingredients.salami,
                    Ingredients.tomato));
        }
    };

    public abstract List<Ingredients> getIngredients();
}

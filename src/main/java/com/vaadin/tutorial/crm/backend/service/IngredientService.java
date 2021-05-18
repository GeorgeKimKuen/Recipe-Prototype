package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.ingredient;
import com.vaadin.tutorial.crm.backend.entity.recipe;
import com.vaadin.tutorial.crm.backend.repository.IngredientsRepository;
import com.vaadin.tutorial.crm.backend.repository.RecipesRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IngredientService {

    private RecipesRepository recipesRepository;
    private IngredientsRepository ingredientsRepository;
    private static final Logger LOGGER = Logger.getLogger(RecipesService.class.getName());


    public IngredientService(RecipesRepository recipesRepository,
                             IngredientsRepository ingredientsRepository){
        this.recipesRepository = recipesRepository;
        this.ingredientsRepository = ingredientsRepository;
    }
    public List<ingredient> findAll(){
        return ingredientsRepository.findAll();
    }
    public Long count(){
        return ingredientsRepository.count();
    }
    public void delete(ingredient ingredient){
        ingredientsRepository.delete(ingredient);
    }
    public void save(ingredient ingredient){
        if(ingredient==null){
            LOGGER.log(Level.SEVERE,
                    "There is no name for this recipe, please input a name");
            return;
        }
        ingredientsRepository.save(ingredient);
    }
    @PostConstruct
    public void populateTestData() {
        if(recipesRepository.count()==0){
            recipesRepository.saveAll(Stream.of("y","n","w","l")
                    .map(recipe::new)
                    .collect(Collectors.toList()));
        }
        if(ingredientsRepository.count()==0){
            Random r = new Random(0);
            List<recipe> recipes = recipesRepository.findAll();
            ingredientsRepository.saveAll(
                    Stream.of("rice","water","pasta","mango")
            .map(name->{
                ingredient ingredient = new ingredient();
                ingredient.setIngredient(name);
                ingredient.setRecipe(recipes.get(r.nextInt(recipes.size())));
                return ingredient;
            })
                            .collect(Collectors.toList()));
        }

    }

}

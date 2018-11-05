package guru.springframework.services;

import java.util.List;

import guru.springframework.domain.Recipe;

public interface RecipeService {

	List<Recipe> getRecipes();
}

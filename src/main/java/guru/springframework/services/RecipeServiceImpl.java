package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public List<Recipe> getRecipes() {

		Iterable<Recipe> recipes = recipeRepository.findAll();
		List<Recipe> recipeList = new ArrayList<Recipe>();
		recipes.forEach(recipe -> {
			recipeList.add(recipe);
		});
		return recipeList;
	}

}

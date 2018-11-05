package guru.springframework.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({ "/recipes" })
	public String getRecipes(Model model) {

		List<Recipe> recipes = recipeService.getRecipes();
		model.addAttribute("recipes", recipes);
		return "recipes";
	}
}

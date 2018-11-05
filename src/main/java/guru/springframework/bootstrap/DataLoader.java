package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		loadData();

	}

	private void loadData() {
		createCategory("American");
		Category mexicanCategory = createCategory("Mexican");
		createCategory("Italian");
		createCategory("Fast Food");

		UnitOfMeasure uomTablespoon = unitOfMeasureRepository.findByDescription("tablespoon").get();
		UnitOfMeasure uomTeaspoon = unitOfMeasureRepository.findByDescription("tablespoon").get();

		Notes guacamoleNotes = createNotes("A lot of notes for Guacamole");

		Recipe guacamoleRecipe = createRecipe("Guacamole", "some directions", mexicanCategory, guacamoleNotes,
				"Elisa Bauer", "https://www.simplyrecipes.com/recipes/perfect_guacamole/");

		guacamoleRecipe.addIngredient(createIngredient("ripe Avocados", 2, null));

		guacamoleRecipe.addIngredient(createIngredient("Kosher salt", 0.5, uomTeaspoon));
		guacamoleRecipe.addIngredient(createIngredient("fresh lime juice or lemon juice", 1, uomTablespoon));
		guacamoleRecipe
				.addIngredient(createIngredient("minced red onion or thinly sliced green onion", 2, uomTablespoon));
		recipeRepository.save(guacamoleRecipe);

		Notes tacoNotes = createNotes("A lot of notes for Tacos");

		Recipe tacoRecipe = createRecipe("Spicy Grilled Chicken Tacos", "some directions for Tacos", mexicanCategory,
				tacoNotes, "Sally Vargas", "https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		tacoRecipe.addIngredient(createIngredient("Tacos", 2, null));
		tacoRecipe.addIngredient(createIngredient("Chicken", 0.5, uomTeaspoon));

		recipeRepository.save(tacoRecipe);

	}

	private Ingredient createIngredient(String description, double amount, UnitOfMeasure uom) {
		Ingredient ingredient = new Ingredient();
		ingredient.setDescription(description);
		ingredient.setAmount(BigDecimal.valueOf(amount));
		ingredient.setUom(uom);
		return ingredient;
	}

	private Notes createNotes(String recipeNotes) {
		Notes notes = new Notes();
		notes.setRecipeNotes(recipeNotes);
		return notes;
	}

	private Recipe createRecipe(String description, String directions, Category mexicanCategory, Notes notes,
			String source, String url) {
		Recipe recipe = new Recipe();
		recipe.setCategories((new HashSet<Category>(Arrays.asList(mexicanCategory))));
		recipe.setPrepTime(10);
		recipe.setCookTime(0);
		recipe.setDescription(description);
		recipe.setDifficulty(Difficulty.EASY);
		recipe.setDirections(directions);
		recipe.setNotes(notes);

		recipe.setServings(4);
		recipe.setSource(source);
		recipe.setUrl(url);
		return recipe;
	}

	private Category createCategory(String description) {
		Category c = new Category();
		c.setDescription(description);
		return categoryRepository.save(c);
	}

}
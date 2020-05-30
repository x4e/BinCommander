package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("prismarine-recipe")
external class PrismarineRecipe {
	class Recipe {
		var result: RecipeItem
		var inShape: Array<Array<RecipeItem>>
		var outShape: Array<Array<RecipeItem>>
		var ingredients: Array<RecipeItem>
		var delta: Array<RecipeItem>
		var requiresTable: Boolean
		
		companion object {
			fun find(itemType: Number, metadata: Number?): Array<Recipe>
		}
		class RecipeItem {
			var id: Number
			var metadata: Number?
			var count: Number
			
			companion object {
				fun fromEnum(itemFromRecipeEnum: Any): RecipeItem
				fun clone(recipeItem: RecipeItem): RecipeItem
			}
		}
	}
}

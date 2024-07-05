package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.StoneVariantItems;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class TerrestriaRecipeProvider extends FabricRecipeProvider {
	protected TerrestriaRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		// misc. recipes
		new ShapelessRecipeJsonBuilder(RecipeCategory.DECORATIONS, TerrestriaItems.BRYCE_SAPLING, 1)
			.input(Items.OAK_SAPLING)
			.input(Items.STICK)
			.criterion("has_bryce_sapling", InventoryChangedCriterion.Conditions.items(TerrestriaItems.BRYCE_SAPLING))
			.offerTo(exporter, Identifier.of(Terrestria.MOD_ID, "bryce_sapling_from_oak_sapling"));

		CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(TerrestriaItems.SAGUARO_CACTUS), RecipeCategory.MISC, Items.GREEN_DYE, 1.0f, 200)
			.criterion("has_cactus", InventoryChangedCriterion.Conditions.items(TerrestriaItems.SAGUARO_CACTUS))
			.offerTo(exporter);

		CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(TerrestriaItems.TINY_CACTUS), RecipeCategory.MISC, Items.LIME_DYE, 1.0f, 200)
			.criterion("has_tiny_cactus", InventoryChangedCriterion.Conditions.items(TerrestriaItems.TINY_CACTUS))
			.offerTo(exporter);

		new ShapedRecipeJsonBuilder(RecipeCategory.TOOLS, TerrestriaItems.LOG_TURNER, 1)
			.pattern("ss")
			.pattern(" s")
			.pattern("ss")
			.input('s', Items.STICK)
			.criterion("has_sticks", InventoryChangedCriterion.Conditions.items(Items.STICK))
			.offerTo(exporter);

		offerSingleOutputShapelessRecipe(exporter, Items.RED_DYE, TerrestriaItems.INDIAN_PAINTBRUSH, "dyes");


		// wood building block recipes
		generateWood(exporter, TerrestriaItems.CYPRESS, TerrestriaItemTags.CYPRESS_LOGS);
		generateWood(exporter, TerrestriaItems.HEMLOCK, TerrestriaItemTags.HEMLOCK_LOGS);
		generateWood(exporter, TerrestriaItems.RAINBOW_EUCALYPTUS, TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS);
		generateWood(exporter, TerrestriaItems.REDWOOD, TerrestriaItemTags.REDWOOD_LOGS);
		generateWood(exporter, TerrestriaItems.RUBBER, TerrestriaItemTags.RUBBER_LOGS);
		generateWood(exporter, TerrestriaItems.WILLOW, TerrestriaItemTags.WILLOW_LOGS);
		generateWood(exporter, TerrestriaItems.YUCCA_PALM, TerrestriaItemTags.YUCCA_PALM_LOGS);

		// stone building block recipes
		generateStone(exporter, TerrestriaItems.VOLCANIC_ROCK);
	}

	private void generateWood(RecipeExporter exporter, WoodItems woodItem, TagKey<Item> logsTag) {
		if (woodItem.fallbackPlanks != null) {
			ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, woodItem.fallbackPlanks, 4)
					.input(logsTag).group("planks").criterion("has_logs", conditionsFromTag(logsTag)).offerTo(exporter, Identifier.of("terrestria", getItemPath(woodItem.log) + "_to_planks"));
		}
		// leaf piles are an optional wood feature

		// some woodItem with no real wood have wood set to log
		if (woodItem.hasWood()) {
			assert (woodItem.wood != null);  // it's not null; this is just for IDEA
			new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, woodItem.wood, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.input('L', woodItem.log)
				.criterion("has_logs", InventoryChangedCriterion.Conditions.items(woodItem.log))
				.offerTo(exporter);

			assert (woodItem.strippedWood != null);  // it's not null; this is just for IDEA
			new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, woodItem.strippedWood, 3)
				.group("bark")
				.pattern("LL")
				.pattern("LL")
				.input('L', woodItem.strippedLog)
				.criterion("has_logs", InventoryChangedCriterion.Conditions.items(woodItem.strippedLog))
				.offerTo(exporter);
		}
	}

	private void generateStone(RecipeExporter exporter, StoneItems stoneItem) {
		if (stoneItem.bricks != null) {
			generateStoneVariant(exporter, stoneItem.bricks, stoneItem.plain.full);

			new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, 4)
				.group("bricks")
				.pattern("SS")
				.pattern("SS")
				.input('S', stoneItem.plain.full)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.plain.full))
				.offerTo(exporter);
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, stoneItem.plain.full);

			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.full);
			offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.plain.full);

			offerCrackingRecipe(exporter, stoneItem.crackedBricks, stoneItem.bricks.full);
		}
		if (stoneItem.cobblestone != null) {
			generateStoneVariant(exporter, stoneItem.cobblestone, null);
		}
		if (stoneItem.mossyBricks != null) {
			generateStoneVariant(exporter, stoneItem.mossyBricks, null);

			new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyBricks.full, 1)
				.group("mossy_bricks")
				.input(stoneItem.bricks.full)
				.input(TerrestriaItemTags.MOSSY_INGREDIENTS)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.bricks.full))
				.offerTo(exporter);
		}
		if (stoneItem.mossyCobblestone != null) {
			generateStoneVariant(exporter, stoneItem.mossyCobblestone, null);

			new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyCobblestone.full, 1)
				.group("mossy_cobblestone")
				.input(stoneItem.cobblestone.full)
				.input(TerrestriaItemTags.MOSSY_INGREDIENTS)
				.criterion("has_stone", InventoryChangedCriterion.Conditions.items(stoneItem.cobblestone.full))
				.offerTo(exporter);
		}
		if (stoneItem.plain != null) {
			generateStoneVariant(exporter, stoneItem.plain, null);

			if (stoneItem.cobblestone != null) {
				offerSmelting(exporter,
					Collections.singletonList(stoneItem.cobblestone.full),
					RecipeCategory.BUILDING_BLOCKS,
					stoneItem.plain.full,
					0.1f, 200, "stone");
			}
		}
		if (stoneItem.smooth != null) {
			generateStoneVariant(exporter, stoneItem.smooth, null);

			if (stoneItem.plain != null) {
				offerSmelting(exporter,
					Collections.singletonList(stoneItem.plain.full),
					RecipeCategory.BUILDING_BLOCKS,
					stoneItem.smooth.full,
					0.1f, 200, "stone");
			}
		}
	}

	private void generateStoneVariant(RecipeExporter exporter, StoneVariantItems stoneVariantItem, @Nullable BlockItem cutPlainItem) {

	}

	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return Identifier.of(Terrestria.MOD_ID, identifier.getPath());
	}
}

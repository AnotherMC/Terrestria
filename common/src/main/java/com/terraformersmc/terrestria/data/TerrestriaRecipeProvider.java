package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlockFamilies;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.StoneVariantItems;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class TerrestriaRecipeProvider extends FabricRecipeProvider {
	protected TerrestriaRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
		return new RecipeGenerator(registryLookup, exporter) {
			@Override
			public void generate() {
				// misc. recipes
				createShapeless(RecipeCategory.DECORATIONS, TerrestriaItems.BRYCE_SAPLING, 1)
						.input(Items.OAK_SAPLING)
						.input(Items.STICK)
						.criterion("has_bryce_sapling", this.conditionsFromItem(TerrestriaItems.BRYCE_SAPLING))
						.offerTo(exporter, "bryce_sapling_from_oak_sapling");

				CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(TerrestriaItems.SAGUARO_CACTUS), RecipeCategory.MISC, Items.GREEN_DYE, 1.0f, 200)
						.criterion("has_cactus", this.conditionsFromItem(TerrestriaItems.SAGUARO_CACTUS))
						.offerTo(exporter);

				CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(TerrestriaItems.TINY_CACTUS), RecipeCategory.MISC, Items.LIME_DYE, 1.0f, 200)
						.criterion("has_tiny_cactus", this.conditionsFromItem(TerrestriaItems.TINY_CACTUS))
						.offerTo(exporter);

				createShaped(RecipeCategory.TOOLS, TerrestriaItems.LOG_TURNER, 1)
						.pattern("ss")
						.pattern(" s")
						.pattern("ss")
						.input('s', Items.STICK)
						.criterion("has_sticks", this.conditionsFromItem(Items.STICK))
						.offerTo(exporter);

				offerSingleOutputShapelessRecipe(Items.RED_DYE, TerrestriaItems.INDIAN_PAINTBRUSH, "dyes");


				// wood building block recipes
				generateWood(exporter, TerrestriaBlockFamilies.CYPRESS, TerrestriaItems.CYPRESS, TerrestriaItemTags.CYPRESS_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.HEMLOCK, TerrestriaItems.HEMLOCK, TerrestriaItemTags.HEMLOCK_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.RAINBOW_EUCALYPTUS, TerrestriaItems.RAINBOW_EUCALYPTUS, TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.REDWOOD, TerrestriaItems.REDWOOD, TerrestriaItemTags.REDWOOD_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.RUBBER, TerrestriaItems.RUBBER, TerrestriaItemTags.RUBBER_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.WILLOW, TerrestriaItems.WILLOW, TerrestriaItemTags.WILLOW_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.YUCCA_PALM, TerrestriaItems.YUCCA_PALM, TerrestriaItemTags.YUCCA_PALM_LOGS);

				// stone building block recipes
				generateStone(exporter, TerrestriaItems.VOLCANIC_ROCK);
			}

		private void generateWood(RecipeExporter exporter, BlockFamily family, WoodItems woodItem, TagKey<Item> logsTag) {
			if (woodItem.fallbackPlanks != null) {
				createShapeless(RecipeCategory.BUILDING_BLOCKS, woodItem.fallbackPlanks, 4)
						.input(logsTag).group("planks").criterion("has_logs", conditionsFromTag(logsTag))
					.offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of("terrestria", getItemPath(woodItem.log) + "_to_planks")));
			}
			// leaf piles are an optional wood feature

				// some woodItem with no real wood have wood set to log
				if (woodItem.hasWood()) {
					createShaped(RecipeCategory.BUILDING_BLOCKS, woodItem.wood, 3)
							.group("bark")
							.pattern("LL")
							.pattern("LL")
							.input('L', woodItem.log)
							.criterion("has_logs", InventoryChangedCriterion.Conditions.items(woodItem.log))
							.offerTo(exporter);

					createShaped(RecipeCategory.BUILDING_BLOCKS, woodItem.strippedWood, 3)
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

					createShaped(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, 4)
							.group("bricks")
							.pattern("SS")
							.pattern("SS")
							.input('S', stoneItem.plain.full)
							.criterion("has_stone", this.conditionsFromItem(stoneItem.plain.full))
							.offerTo(exporter);
					offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, stoneItem.plain.full);

			offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.full);
			offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.plain.full);

					offerCrackingRecipe(stoneItem.crackedBricks, stoneItem.bricks.full);
				}
				if (stoneItem.cobblestone != null) {
					generateStoneVariant(exporter, stoneItem.cobblestone, null);
				}
				if (stoneItem.mossyBricks != null) {
					generateStoneVariant(exporter, stoneItem.mossyBricks, null);

					createShapeless(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyBricks.full, 1)
							.group("mossy_bricks")
							.input(stoneItem.bricks.full)
							.input(TerrestriaItemTags.MOSSY_INGREDIENTS)
							.criterion("has_mossy_ingredients", this.conditionsFromTag(TerrestriaItemTags.MOSSY_INGREDIENTS))
							.offerTo(exporter);
				}
				if (stoneItem.mossyCobblestone != null) {
					generateStoneVariant(exporter, stoneItem.mossyCobblestone, null);

					createShapeless(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyCobblestone.full, 1)
							.group("mossy_cobblestone")
							.input(stoneItem.cobblestone.full)
							.input(TerrestriaItemTags.MOSSY_INGREDIENTS)
							.criterion("has_mossy_ingredients", this.conditionsFromTag(TerrestriaItemTags.MOSSY_INGREDIENTS))
							.offerTo(exporter);
				}
				if (stoneItem.plain != null) {
					generateStoneVariant(exporter, stoneItem.plain, null);

			if (stoneItem.cobblestone != null) {
				offerSmelting(
					Collections.singletonList(stoneItem.cobblestone.full),
					RecipeCategory.BUILDING_BLOCKS,
					stoneItem.plain.full,
					0.1f, 200, "stone");
			}
		}
		if (stoneItem.smooth != null) {
			generateStoneVariant(exporter, stoneItem.smooth, null);

					if (stoneItem.plain != null) {
						offerSmelting(Collections.singletonList(stoneItem.plain.full),
								RecipeCategory.BUILDING_BLOCKS,
								stoneItem.smooth.full,
								0.1f, 200, "stone");
					}
				}
			}

		private void generateStoneVariant(RecipeExporter exporter, StoneVariantItems stoneVariantItem, @Nullable BlockItem cutPlainItem) {

		}
		};
	}
	@Override
	public String getName() {
		return "Terrestria Recipes";
	}

	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return Identifier.of(Terrestria.MOD_ID, identifier.getPath());
	}
}

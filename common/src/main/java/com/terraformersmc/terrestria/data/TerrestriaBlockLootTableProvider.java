package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.SaplingBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TerrestriaBlockLootTableProvider extends FabricBlockLootTableProvider {
	protected TerrestriaBlockLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate() {
		RegistryWrapper.Impl<Enchantment> enchantmentRegistry = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);

		// simple blocks
		addDrop(TerrestriaBlocks.AGAVE);
		addDrop(TerrestriaBlocks.ALOE_VERA);
		addDrop(TerrestriaBlocks.VOLCANIC_SAND);
		addDrop(TerrestriaBlocks.BRYCE_SAPLING);
		addDrop(TerrestriaBlocks.CATTAIL, this::dropsWithShears);
		addDrop(TerrestriaBlocks.CYPRESS_SAPLING);
		addDrop(TerrestriaBlocks.DEAD_GRASS);
		addDrop(TerrestriaBlocks.HEMLOCK_SAPLING);
		addDrop(TerrestriaBlocks.INDIAN_PAINTBRUSH);
		addDrop(TerrestriaBlocks.JUNGLE_PALM_SAPLING);
		addDrop(TerrestriaBlocks.MONSTERAS, this::shortPlantDrops);
		addDrop(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		addDrop(TerrestriaBlocks.REDWOOD_SAPLING);
		addDrop(TerrestriaBlocks.RUBBER_SAPLING);
		addDrop(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING);
		addDrop(TerrestriaBlocks.SAKURA_SAPLING);
		addDrop(TerrestriaBlocks.TALL_CATTAIL, dropsWithShears(TerrestriaBlocks.CATTAIL));
		addDrop(TerrestriaBlocks.TINY_CACTUS);
		addDrop(TerrestriaBlocks.WILLOW_SAPLING);
		addDrop(TerrestriaBlocks.YUCCA_PALM_SAPLING);

		// dirt blocks
		addDirtDrops(TerrestriaBlocks.ANDISOL);

		// stone building blocks
		addStoneDrops(TerrestriaBlocks.VOLCANIC_ROCK);

		// wood building blocks
		addWoodDrops(TerrestriaBlocks.CYPRESS, TerrestriaBlocks.CYPRESS_SAPLING);
		addWoodDrops(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING);
		addWoodDrops(TerrestriaBlocks.RAINBOW_EUCALYPTUS, TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		addWoodDrops(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING);
		addWoodDrops(TerrestriaBlocks.RUBBER, TerrestriaBlocks.RUBBER_SAPLING);
		addWoodDrops(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING);
		addWoodDrops(TerrestriaBlocks.YUCCA_PALM, null);

		// potted things
		addPottedPlantDrops(TerrestriaBlocks.POTTED_AGAVE);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_ALOE_VERA);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_BRYCE_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_CYPRESS_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_HEMLOCK_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_MONSTERAS);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_REDWOOD_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_RUBBER_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_SAKURA_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_TINY_CACTUS);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_WILLOW_SAPLING);
		addPottedPlantDrops(TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING);

		// specialty tree leaves
		addDrop(TerrestriaBlocks.JUNGLE_PALM_LEAVES, leavesDrops(TerrestriaBlocks.JUNGLE_PALM_LEAVES, TerrestriaBlocks.JUNGLE_PALM_SAPLING, 0.07f, 0.0875f, 0.116666667f, 0.14f));
		addDrop(TerrestriaBlocks.YUCCA_PALM.leaves, leavesDrops(TerrestriaBlocks.YUCCA_PALM.leaves, TerrestriaBlocks.YUCCA_PALM_SAPLING, 0.15f, 0.1875f, 0.24f, 0.333333333f));

		// even more specialty leaf-like drop thingy
		addDrop(TerrestriaBlocks.SAGUARO_CACTUS,
				dropsWithSilkTouch(TerrestriaBlocks.SAGUARO_CACTUS,
						addSurvivesExplosionCondition(TerrestriaBlocks.SAGUARO_CACTUS,
								ItemEntry.builder(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING))
								.conditionally(TableBonusLootCondition.builder(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 0.2f, 0.24285715f, 0.5f, 2.0f))));
	}

	private void addDirtDrops(DirtBlocks dirtBlock) {
		if (dirtBlock.getDirt() != null) {
			addDrop(dirtBlock.getDirt());
			if (dirtBlock.getDirtPath() != null) {
				addDrop(TerrestriaBlocks.ANDISOL.getDirtPath(), TerrestriaBlocks.ANDISOL.getDirt());
			}
			if (dirtBlock.getFarmland() != null) {
				addDrop(TerrestriaBlocks.ANDISOL.getFarmland(), TerrestriaBlocks.ANDISOL.getDirt());
			}
			if (dirtBlock.getGrassBlock() != null) {
				addDrop(TerrestriaBlocks.ANDISOL.getGrassBlock(), TerrestriaBlocks.ANDISOL.getDirt());
			}
		}
		if (dirtBlock.getPodzol() != null) {
			addDrop(TerrestriaBlocks.ANDISOL.getPodzol());
		}
	}

	private void addStoneDrops(StoneBlocks stoneBlock) {
		if (stoneBlock.bricks != null) {
			addDrop(stoneBlock.bricks.full);

			addDrop(stoneBlock.chiseledBricks);
			addDrop(stoneBlock.crackedBricks);
		}
		if (stoneBlock.cobblestone != null) {
			addDrop(stoneBlock.cobblestone.full);
		}
		if (stoneBlock.mossyBricks != null) {
			addDrop(stoneBlock.mossyBricks.full);
		}
		if (stoneBlock.mossyCobblestone != null) {
			addDrop(stoneBlock.mossyCobblestone.full);
		}
		if (stoneBlock.plain != null) {
			if (stoneBlock.cobblestone != null) {
				addDrop(stoneBlock.plain.full, drops(stoneBlock.plain.full, stoneBlock.cobblestone.full));
			} else {
				addDrop(stoneBlock.plain.full);
			}
		}
		if (stoneBlock.smooth != null) {
			addDrop(stoneBlock.smooth.full);
		}

	}

	private void addWoodDrops(WoodBlocks woodBlock, @Nullable SaplingBlock sapling) {
		addDrop(woodBlock.log);
		addDrop(woodBlock.strippedLog);

		if (woodBlock.hasWood()) {
			addDrop(woodBlock.wood);
			addDrop(woodBlock.strippedWood);
		}

		if (sapling != null) {
			addDrop(woodBlock.leaves, leavesDrops(woodBlock.leaves, sapling, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		}
	}

	@Override
	public String getName() {
		return "Terrestria Block Loot Tables";
	}
}

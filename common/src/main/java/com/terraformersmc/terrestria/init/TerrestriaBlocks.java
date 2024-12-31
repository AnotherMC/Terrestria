package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terraform.dirt.api.block.TerraformDirtPathBlock;
import com.terraformersmc.terraform.dirt.api.block.TerraformFarmlandBlock;
import com.terraformersmc.terraform.dirt.api.block.TerraformSnowyBlock;
import com.terraformersmc.terraform.dirt.api.registry.TerraformDirtRegistry;
import com.terraformersmc.terraform.tree.api.block.TerraformDesertSaplingBlock;
import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.api.block.PillarLogHelper;
import com.terraformersmc.terraform.wood.api.block.SmallLogBlock;
import com.terraformersmc.terrestria.block.*;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodColors;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Optional;

// This class exports public block constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaBlocks {
	public static WoodBlocks REDWOOD;
	public static WoodBlocks HEMLOCK;
	public static WoodBlocks RUBBER;
	public static WoodBlocks CYPRESS;
	public static WoodBlocks WILLOW;
	public static WoodBlocks RAINBOW_EUCALYPTUS;
	public static WoodBlocks YUCCA_PALM;

	public static BareSmallLogBlock SAGUARO_CACTUS;

	public static LeavesBlock JUNGLE_PALM_LEAVES;

	public static SeagrassBlock CATTAIL;
	public static TallSeagrassBlock TALL_CATTAIL;

	public static SaplingBlock BRYCE_SAPLING;
	public static SaplingBlock REDWOOD_SAPLING;
	public static SaplingBlock HEMLOCK_SAPLING;
	public static SaplingBlock RUBBER_SAPLING;
	public static SaplingBlock CYPRESS_SAPLING;
	public static SaplingBlock WILLOW_SAPLING;
	public static SaplingBlock RAINBOW_EUCALYPTUS_SAPLING;
	public static SaplingBlock SAKURA_SAPLING;
	public static SaplingBlock JUNGLE_PALM_SAPLING;
	public static SaplingBlock SAGUARO_CACTUS_SAPLING;
	public static SaplingBlock YUCCA_PALM_SAPLING;

	public static FlowerPotBlock POTTED_BRYCE_SAPLING;
	public static FlowerPotBlock POTTED_REDWOOD_SAPLING;
	public static FlowerPotBlock POTTED_HEMLOCK_SAPLING;
	public static FlowerPotBlock POTTED_JUNGLE_PALM_SAPLING;
	public static FlowerPotBlock POTTED_RUBBER_SAPLING;
	public static FlowerPotBlock POTTED_CYPRESS_SAPLING;
	public static FlowerPotBlock POTTED_WILLOW_SAPLING;
	public static FlowerPotBlock POTTED_RAINBOW_EUCALYPTUS_SAPLING;
	public static FlowerPotBlock POTTED_SAKURA_SAPLING;
	public static FlowerPotBlock POTTED_SAGUARO_CACTUS_SAPLING;
	public static FlowerPotBlock POTTED_YUCCA_PALM_SAPLING;

	// Volcanic Island blocks
	public static ColoredFallingBlock BLACK_SAND;
	public static DirtBlocks ANDISOL;
	public static StoneBlocks VOLCANIC_ROCK;
	public static PlantBlock INDIAN_PAINTBRUSH;
	public static PlantBlock MONSTERAS;
	public static FlowerPotBlock POTTED_INDIAN_PAINTBRUSH;
	public static FlowerPotBlock POTTED_MONSTERAS;

	// Desert Plants
	public static PlantBlock TINY_CACTUS;
	public static PlantBlock AGAVE;
	public static PlantBlock ALOE_VERA;
	public static PlantBlock DEAD_GRASS;
	public static FlowerPotBlock POTTED_TINY_CACTUS;
	public static FlowerPotBlock POTTED_AGAVE;
	public static FlowerPotBlock POTTED_ALOE_VERA;

	public static void init() {
		// quartered mega
		CYPRESS = WoodBlocks.register("cypress", WoodColors.CYPRESS, WoodBlocks.LogSize.NORMAL, false, true, false);
		HEMLOCK = WoodBlocks.register("hemlock", WoodColors.HEMLOCK, WoodBlocks.LogSize.NORMAL, false, true, true);
		RAINBOW_EUCALYPTUS = WoodBlocks.register("rainbow_eucalyptus", WoodColors.RAINBOW_EUCALYPTUS, WoodBlocks.LogSize.NORMAL, false, true, false);
		REDWOOD = WoodBlocks.register("redwood", WoodColors.REDWOOD, WoodBlocks.LogSize.NORMAL, false, true, true);

		// normal trunk
		RUBBER = WoodBlocks.register("rubber", WoodColors.RUBBER);
		WILLOW = WoodBlocks.register("willow", WoodColors.WILLOW);

		// small trunk
		YUCCA_PALM = WoodBlocks.register("yucca_palm", WoodColors.YUCCA_PALM, WoodBlocks.LogSize.NORMAL);

		SAGUARO_CACTUS = TerrestriaRegistry.register("saguaro_cactus", SaguaroCactusBlock::new, SaguaroCactusBlock.createSettings(Blocks.CACTUS.getDefaultMapColor()));

		// strange leaves
		JUNGLE_PALM_LEAVES = TerrestriaRegistry.register("jungle_palm_leaves", LeavesBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never));

		// swamp blocks
		CATTAIL = TerrestriaRegistry.register("cattail", CattailBlock::new, AbstractBlock.Settings.copy(Blocks.SEAGRASS));
		TALL_CATTAIL = TerrestriaRegistry.register("tall_cattail", TallCattailBlock::new, AbstractBlock.Settings.copy(Blocks.TALL_SEAGRASS));

		// Saplings
		AbstractBlock.Settings saplingSettings = AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY);

		BRYCE_SAPLING = TerrestriaRegistry.register("bryce_sapling", settings -> new TerraformDesertSaplingBlock(new SaplingGenerator("bryce", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.BRYCE_TREE), Optional.empty()), settings), saplingSettings);
		CYPRESS_SAPLING = TerrestriaRegistry.register("cypress_sapling", settings -> new SaplingBlock(new SaplingGenerator("cypress", Optional.of(TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE), Optional.of(TerrestriaConfiguredFeatures.CYPRESS_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.CYPRESS.leaves));
		HEMLOCK_SAPLING = TerrestriaRegistry.register("hemlock_sapling", settings -> new SaplingBlock(new SaplingGenerator("hemlock", Optional.of(TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE), Optional.of(TerrestriaConfiguredFeatures.HEMLOCK_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.HEMLOCK.leaves));
		JUNGLE_PALM_SAPLING = TerrestriaRegistry.register("jungle_palm_sapling", settings -> new TerraformDesertSaplingBlock(new SaplingGenerator("jungle_palm", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE), Optional.empty()), settings), saplingSettings);
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register("rainbow_eucalyptus_sapling", settings -> new SaplingBlock(new SaplingGenerator("rainbow_eucalyptus", Optional.of(TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE), Optional.of(TerrestriaConfiguredFeatures.SMALL_RAINBOW_EUCALYPTUS_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.RAINBOW_EUCALYPTUS.leaves));
		REDWOOD_SAPLING = TerrestriaRegistry.register("redwood_sapling", settings -> new SaplingBlock(new SaplingGenerator("redwood", Optional.of(TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE), Optional.of(TerrestriaConfiguredFeatures.REDWOOD_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.REDWOOD.leaves));
		RUBBER_SAPLING = TerrestriaRegistry.register("rubber_sapling", settings -> new SaplingBlock(new SaplingGenerator("rubber", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.RUBBER_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.RUBBER.leaves));
		SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.register("saguaro_cactus_sapling", settings -> new TerraformDesertSaplingBlock(new SaplingGenerator("saguaro_cactus", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.SAGUARO_CACTUS), Optional.empty()), true, settings), saplingSettings.mapColor(Blocks.CACTUS.getDefaultMapColor()));
		SAKURA_SAPLING = TerrestriaRegistry.register("sakura_sapling", settings -> new SaplingBlock(new SaplingGenerator("sakura", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.SAKURA_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.SAKURA.leaves));
		WILLOW_SAPLING = TerrestriaRegistry.register("willow_sapling", settings -> new SaplingBlock(new SaplingGenerator("willow", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.WILLOW_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.WILLOW.leaves));
		YUCCA_PALM_SAPLING = TerrestriaRegistry.register("yucca_palm_sapling", settings -> new TerraformDesertSaplingBlock(new SaplingGenerator("yucca_palm", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.YUCCA_PALM_TREE), Optional.empty()), settings), saplingSettings.mapColor(WoodColors.YUCCA_PALM.leaves));

		// Volcanic Island blocks

		BLACK_SAND = TerrestriaRegistry.register("basalt_sand", settings -> new ColoredFallingBlock(new ColorCode(0x202020), settings), AbstractBlock.Settings.copy(Blocks.SAND).mapColor(MapColor.BLACK));

		Block andisolDirt = TerrestriaRegistry.register("basalt_dirt", Block::new, AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.BLACK));
		ANDISOL = TerraformDirtRegistry.register(new DirtBlocks (
			andisolDirt,
			TerrestriaRegistry.register("basalt_grass_block", settings -> new BasaltGrassBlock(andisolDirt, () -> ANDISOL.getDirtPath(), settings), AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)),
			TerrestriaRegistry.register("basalt_grass_path", TerraformDirtPathBlock::new, AbstractBlock.Settings.copy(Blocks.DIRT_PATH)),
			TerrestriaRegistry.register("basalt_podzol", TerraformSnowyBlock::new, AbstractBlock.Settings.copy(Blocks.PODZOL)),
			TerrestriaRegistry.register("andisol_farmland", TerraformFarmlandBlock::new, AbstractBlock.Settings.copy(Blocks.FARMLAND).mapColor(MapColor.BLACK))
		));
		VOLCANIC_ROCK = StoneBlocks.register("basalt", MapColor.BLACK);

		INDIAN_PAINTBRUSH = TerrestriaRegistry.register("indian_paintbrush", settings -> new BasaltFlowerBlock(StatusEffects.SATURATION, 4, settings), AbstractBlock.Settings.copy(Blocks.POPPY).mapColor(MapColor.TERRACOTTA_ORANGE));
		MONSTERAS = TerrestriaRegistry.register("monsteras", settings -> new BasaltFlowerBlock(StatusEffects.REGENERATION, 2, settings), AbstractBlock.Settings.copy(Blocks.FERN).mapColor(MapColor.GREEN));

		POTTED_INDIAN_PAINTBRUSH = TerrestriaRegistry.register("potted_indian_paintbrush", settings -> new FlowerPotBlock(INDIAN_PAINTBRUSH, settings), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY));
		POTTED_MONSTERAS = TerrestriaRegistry.register("potted_monsteras", settings -> new FlowerPotBlock(MONSTERAS, settings), AbstractBlock.Settings.copy(Blocks.POTTED_FERN));

		// potted saplings
		POTTED_BRYCE_SAPLING = TerrestriaRegistry.register("potted_bryce_sapling", settings -> new FlowerPotBlock(BRYCE_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_REDWOOD_SAPLING = TerrestriaRegistry.register("potted_redwood_sapling", settings -> new FlowerPotBlock(REDWOOD_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_HEMLOCK_SAPLING = TerrestriaRegistry.register("potted_hemlock_sapling", settings -> new FlowerPotBlock(HEMLOCK_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_RUBBER_SAPLING = TerrestriaRegistry.register("potted_rubber_sapling", settings -> new FlowerPotBlock(RUBBER_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_CYPRESS_SAPLING = TerrestriaRegistry.register("potted_cypress_sapling", settings -> new FlowerPotBlock(CYPRESS_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_WILLOW_SAPLING = TerrestriaRegistry.register("potted_willow_sapling", settings -> new FlowerPotBlock(WILLOW_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register("potted_rainbow_eucalyptus_sapling", settings -> new FlowerPotBlock(RAINBOW_EUCALYPTUS_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_SAKURA_SAPLING = TerrestriaRegistry.register("potted_sakura_sapling", settings -> new FlowerPotBlock(SAKURA_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_JUNGLE_PALM_SAPLING = TerrestriaRegistry.register("potted_jungle_palm_sapling", settings -> new FlowerPotBlock(JUNGLE_PALM_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.register("potted_saguaro_cactus_sapling", settings -> new FlowerPotBlock(SAGUARO_CACTUS_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		POTTED_YUCCA_PALM_SAPLING = TerrestriaRegistry.register("potted_yucca_palm_sapling", settings -> new FlowerPotBlock(YUCCA_PALM_SAPLING, settings), AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));

		// desert plants
		TINY_CACTUS = TerrestriaRegistry.register("tiny_cactus", settings -> new PricklyDesertPlantBlock(true, settings), AbstractBlock.Settings.copy(Blocks.DEAD_BUSH).mapColor(MapColor.PALE_GREEN));
		AGAVE = TerrestriaRegistry.register("agave", TerraformDesertPlantBlock::new, AbstractBlock.Settings.copy(Blocks.POPPY).mapColor(MapColor.PALE_GREEN));
		ALOE_VERA = TerrestriaRegistry.register("aloe_vera", TerraformDesertPlantBlock::new, AbstractBlock.Settings.copy(Blocks.POPPY).mapColor(MapColor.PALE_GREEN));
		DEAD_GRASS = TerrestriaRegistry.register("dead_grass", settings -> new TerraformDesertPlantBlock(true, settings), AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).mapColor(MapColor.TERRACOTTA_BROWN));

		POTTED_TINY_CACTUS = TerrestriaRegistry.register("potted_tiny_cactus", settings -> new FlowerPotBlock(TINY_CACTUS, settings), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY));
		POTTED_AGAVE = TerrestriaRegistry.register("potted_agave", settings -> new FlowerPotBlock(AGAVE, settings), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY));
		POTTED_ALOE_VERA = TerrestriaRegistry.register("potted_aloe_vera", settings -> new FlowerPotBlock(ALOE_VERA, settings), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY));

		addFlammables();
		addFlattenables();
		addStrippables();
	}

	private static void addFlammables() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();

		flammableRegistry.add(JUNGLE_PALM_LEAVES, 30, 60);

		flammableRegistry.add(DEAD_GRASS, 30, 60);
	}

	private static void addFlattenables() {
		FlattenableBlockRegistry.register(ANDISOL.getDirt(), ANDISOL.getDirtPath().getDefaultState());
		FlattenableBlockRegistry.register(ANDISOL.getGrassBlock(), ANDISOL.getDirtPath().getDefaultState());
		FlattenableBlockRegistry.register(ANDISOL.getPodzol(), ANDISOL.getDirtPath().getDefaultState());
	}

	private static void addStrippables() {
	}

	public static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}
	public static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
		return type == EntityType.OCELOT || type == EntityType.PARROT;
	}
}

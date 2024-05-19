package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.terraformersmc.terraform.dirt.TerraformDirtRegistry;
import com.terraformersmc.terraform.dirt.block.TerraformDirtPathBlock;
import com.terraformersmc.terraform.dirt.block.TerraformFarmlandBlock;
import com.terraformersmc.terraform.dirt.block.TerraformSnowyBlock;
import com.terraformersmc.terraform.tree.block.TerraformDesertSaplingBlock;
import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.block.SmallLogBlock;
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

		SAGUARO_CACTUS = TerrestriaRegistry.register("saguaro_cactus", SaguaroCactusBlock.of(Blocks.CACTUS.getDefaultMapColor()));
		// strange leaves
		JUNGLE_PALM_LEAVES = TerrestriaRegistry.register("jungle_palm_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));

		// swamp blocks
		CATTAIL = TerrestriaRegistry.register("cattail", new CattailBlock(AbstractBlock.Settings.copy(Blocks.SEAGRASS)));
		TALL_CATTAIL = TerrestriaRegistry.register("tall_cattail", new TallCattailBlock(AbstractBlock.Settings.copy(Blocks.TALL_SEAGRASS)));

		// Saplings
		AbstractBlock.Settings saplingSettings = AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY);

		BRYCE_SAPLING = TerrestriaRegistry.register("bryce_sapling", new TerraformDesertSaplingBlock(new SaplingGenerator("bryce", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.BRYCE_TREE), Optional.empty()), saplingSettings));
		CYPRESS_SAPLING = TerrestriaRegistry.register("cypress_sapling", new SaplingBlock(new SaplingGenerator("cypress", Optional.of(TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE), Optional.of(TerrestriaConfiguredFeatures.CYPRESS_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.CYPRESS.leaves)));
		HEMLOCK_SAPLING = TerrestriaRegistry.register("hemlock_sapling", new SaplingBlock(new SaplingGenerator("hemlock", Optional.of(TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE), Optional.of(TerrestriaConfiguredFeatures.HEMLOCK_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.HEMLOCK.leaves)));
		JUNGLE_PALM_SAPLING = TerrestriaRegistry.register("jungle_palm_sapling", new TerraformDesertSaplingBlock(new SaplingGenerator("jungle_palm", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE), Optional.empty()), saplingSettings));
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register("rainbow_eucalyptus_sapling", new SaplingBlock(new SaplingGenerator("rainbow_eucalyptus", Optional.of(TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE), Optional.of(TerrestriaConfiguredFeatures.SMALL_RAINBOW_EUCALYPTUS_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.RAINBOW_EUCALYPTUS.leaves)));
		REDWOOD_SAPLING = TerrestriaRegistry.register("redwood_sapling", new SaplingBlock(new SaplingGenerator("redwood", Optional.of(TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE), Optional.of(TerrestriaConfiguredFeatures.REDWOOD_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.REDWOOD.leaves)));
		RUBBER_SAPLING = TerrestriaRegistry.register("rubber_sapling", new SaplingBlock(new SaplingGenerator("rubber", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.RUBBER_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.RUBBER.leaves)));
		SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.register("saguaro_cactus_sapling", new TerraformDesertSaplingBlock(new SaplingGenerator("saguaro_cactus", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.SAGUARO_CACTUS), Optional.empty()), saplingSettings.mapColor(Blocks.CACTUS.getDefaultMapColor()), true));
		SAKURA_SAPLING = TerrestriaRegistry.register("sakura_sapling", new SaplingBlock(new SaplingGenerator("sakura", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.SAKURA_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.SAKURA.leaves)));
		WILLOW_SAPLING = TerrestriaRegistry.register("willow_sapling", new SaplingBlock(new SaplingGenerator("willow", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.WILLOW_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.WILLOW.leaves)));
		YUCCA_PALM_SAPLING = TerrestriaRegistry.register("yucca_palm_sapling", new TerraformDesertSaplingBlock(new SaplingGenerator("yucca_palm", Optional.empty(), Optional.of(TerrestriaConfiguredFeatures.YUCCA_PALM_TREE), Optional.empty()), saplingSettings.mapColor(WoodColors.YUCCA_PALM.leaves)));

		// Volcanic Island blocks

		BLACK_SAND = TerrestriaRegistry.register("basalt_sand", new ColoredFallingBlock(new ColorCode(0x202020), AbstractBlock.Settings.copy(Blocks.SAND).mapColor(MapColor.BLACK)));

		Block andisolDirt = TerrestriaRegistry.register("basalt_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.BLACK)));
		ANDISOL = TerraformDirtRegistry.register(new DirtBlocks (
			andisolDirt,
			TerrestriaRegistry.register("basalt_grass_block", new BasaltGrassBlock(andisolDirt, () -> ANDISOL.getDirtPath(), AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK))),
			TerrestriaRegistry.register("basalt_grass_path", new TerraformDirtPathBlock(AbstractBlock.Settings.copy(Blocks.DIRT_PATH))),
			TerrestriaRegistry.register("basalt_podzol", new TerraformSnowyBlock(AbstractBlock.Settings.copy(Blocks.PODZOL))),
			TerrestriaRegistry.register("andisol_farmland", new TerraformFarmlandBlock(AbstractBlock.Settings.copy(Blocks.FARMLAND).mapColor(MapColor.BLACK)))
		));
		VOLCANIC_ROCK = StoneBlocks.register("basalt", MapColor.BLACK);

		INDIAN_PAINTBRUSH = TerrestriaRegistry.register("indian_paintbrush", new BasaltFlowerBlock(StatusEffects.SATURATION, 4, AbstractBlock.Settings.copy(Blocks.POPPY)));
		MONSTERAS = TerrestriaRegistry.register("monsteras", new BasaltFlowerBlock(StatusEffects.REGENERATION, 2, AbstractBlock.Settings.copy(Blocks.FERN)));

		POTTED_INDIAN_PAINTBRUSH = TerrestriaRegistry.register("potted_indian_paintbrush", new FlowerPotBlock(INDIAN_PAINTBRUSH, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_MONSTERAS = TerrestriaRegistry.register("potted_monsteras", new FlowerPotBlock(MONSTERAS, AbstractBlock.Settings.copy(Blocks.POTTED_FERN)));

		// potted saplings
		POTTED_BRYCE_SAPLING = TerrestriaRegistry.register("potted_bryce_sapling", new FlowerPotBlock(BRYCE_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_REDWOOD_SAPLING = TerrestriaRegistry.register("potted_redwood_sapling", new FlowerPotBlock(REDWOOD_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_HEMLOCK_SAPLING = TerrestriaRegistry.register("potted_hemlock_sapling", new FlowerPotBlock(HEMLOCK_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RUBBER_SAPLING = TerrestriaRegistry.register("potted_rubber_sapling", new FlowerPotBlock(RUBBER_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_CYPRESS_SAPLING = TerrestriaRegistry.register("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_WILLOW_SAPLING = TerrestriaRegistry.register("potted_willow_sapling", new FlowerPotBlock(WILLOW_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register("potted_rainbow_eucalyptus_sapling", new FlowerPotBlock(RAINBOW_EUCALYPTUS_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_SAKURA_SAPLING = TerrestriaRegistry.register("potted_sakura_sapling", new FlowerPotBlock(SAKURA_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JUNGLE_PALM_SAPLING = TerrestriaRegistry.register("potted_jungle_palm_sapling", new FlowerPotBlock(JUNGLE_PALM_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.register("potted_saguaro_cactus_sapling", new FlowerPotBlock(SAGUARO_CACTUS_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_YUCCA_PALM_SAPLING = TerrestriaRegistry.register("potted_yucca_palm_sapling", new FlowerPotBlock(YUCCA_PALM_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));

		// desert plants
		TINY_CACTUS = TerrestriaRegistry.register("tiny_cactus", new PricklyDesertPlantBlock(AbstractBlock.Settings.copy(Blocks.POPPY), true));
		AGAVE = TerrestriaRegistry.register("agave", new TerraformDesertPlantBlock(AbstractBlock.Settings.copy(Blocks.POPPY)));
		ALOE_VERA = TerrestriaRegistry.register("aloe_vera", new TerraformDesertPlantBlock(AbstractBlock.Settings.copy(Blocks.POPPY)));
		DEAD_GRASS = TerrestriaRegistry.register("dead_grass", new TerraformDesertPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS), true));
		POTTED_TINY_CACTUS = TerrestriaRegistry.register("potted_tiny_cactus", new FlowerPotBlock(TINY_CACTUS, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_AGAVE = TerrestriaRegistry.register("potted_agave", new FlowerPotBlock(AGAVE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_ALOE_VERA = TerrestriaRegistry.register("potted_aloe_vera", new FlowerPotBlock(ALOE_VERA, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));

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

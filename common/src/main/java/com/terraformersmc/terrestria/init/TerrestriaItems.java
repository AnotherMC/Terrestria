package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodItems;

import com.terraformersmc.terrestria.item.LogTurnerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

// This class exports public item constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaItems {
	public static WoodItems REDWOOD;
	public static WoodItems HEMLOCK;
	public static WoodItems RUBBER;
	public static WoodItems CYPRESS;
	public static WoodItems WILLOW;
	public static WoodItems JAPANESE_MAPLE;
	public static WoodItems RAINBOW_EUCALYPTUS;
	public static WoodItems SAKURA;
	public static WoodItems YUCCA_PALM;

	public static BlockItem SAGUARO_CACTUS;

	public static BlockItem JAPANESE_MAPLE_SHRUB_LEAVES;
	public static BlockItem DARK_JAPANESE_MAPLE_LEAVES;
	public static BlockItem JUNGLE_PALM_LEAVES;
	public static BlockItem SAKURA_LEAF_PILE;

	public static BlockItem CATTAIL;

	public static BlockItem REDWOOD_QUARTER_LOG;
	public static BlockItem HEMLOCK_QUARTER_LOG;
	public static BlockItem CYPRESS_QUARTER_LOG;
	public static BlockItem RAINBOW_EUCALYPTUS_QUARTER_LOG;
	public static BlockItem STRIPPED_REDWOOD_QUARTER_LOG;
	public static BlockItem STRIPPED_HEMLOCK_QUARTER_LOG;
	public static BlockItem STRIPPED_CYPRESS_QUARTER_LOG;
	public static BlockItem STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG;

	public static BlockItem BRYCE_SAPLING;
	public static BlockItem REDWOOD_SAPLING;
	public static BlockItem HEMLOCK_SAPLING;
	public static BlockItem RUBBER_SAPLING;
	public static BlockItem CYPRESS_SAPLING;
	public static BlockItem WILLOW_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SHRUB_SAPLING;
	public static BlockItem DARK_JAPANESE_MAPLE_SAPLING;
	public static BlockItem RAINBOW_EUCALYPTUS_SAPLING;
	public static BlockItem SAKURA_SAPLING;
	public static BlockItem JUNGLE_PALM_SAPLING;
	public static BlockItem SAGUARO_CACTUS_SAPLING;
	public static BlockItem YUCCA_PALM_SAPLING;

	public static StoneItems VOLCANIC_ROCK;
	public static BlockItem BLACK_SAND;
	public static BlockItem ANDISOL;
	public static BlockItem ANDISOL_GRASS_BLOCK;
	public static BlockItem ANDISOL_GRASS_PATH;
	public static BlockItem ANDISOL_PODZOL;
	public static BlockItem ANDISOL_FARMLAND;
	public static BlockItem INDIAN_PAINTBRUSH;
	public static BlockItem MONSTERAS;

	public static BlockItem TINY_CACTUS;
	public static BlockItem AGAVE;
	public static BlockItem ALOE_VERA;
	public static BlockItem DEAD_GRASS;

	public static LogTurnerItem LOG_TURNER;

	public static void init() {

		REDWOOD = WoodItems.register("redwood", TerrestriaBlocks.REDWOOD);
		HEMLOCK = WoodItems.register("hemlock", TerrestriaBlocks.HEMLOCK);
		RUBBER = WoodItems.register("rubber", TerrestriaBlocks.RUBBER);
		CYPRESS = WoodItems.register("cypress", TerrestriaBlocks.CYPRESS);
		WILLOW = WoodItems.register("willow", TerrestriaBlocks.WILLOW);
		JAPANESE_MAPLE = WoodItems.register("japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE);
		RAINBOW_EUCALYPTUS = WoodItems.register("rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS);
		SAKURA = WoodItems.register("sakura", TerrestriaBlocks.SAKURA);
		YUCCA_PALM = WoodItems.register("yucca_palm", TerrestriaBlocks.YUCCA_PALM);

		SAGUARO_CACTUS = TerrestriaRegistry.registerBuildingBlockItem("saguaro_cactus", TerrestriaBlocks.SAGUARO_CACTUS);

		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.registerDecorationBlockItem("japanese_maple_shrub_leaves", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES);
		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.registerDecorationBlockItem("dark_japanese_maple_leaves", TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES);
		JUNGLE_PALM_LEAVES = TerrestriaRegistry.registerDecorationBlockItem("jungle_palm_leaves", TerrestriaBlocks.JUNGLE_PALM_LEAVES);
		SAKURA_LEAF_PILE = TerrestriaRegistry.registerDecorationBlockItem("sakura_leaf_pile", TerrestriaBlocks.SAKURA_LEAF_PILE);

		CATTAIL = TerrestriaRegistry.registerDecorationBlockItem("cattail", TerrestriaBlocks.CATTAIL);

		BRYCE_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("bryce_sapling", TerrestriaBlocks.BRYCE_SAPLING);
		REDWOOD_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("redwood_sapling", TerrestriaBlocks.REDWOOD_SAPLING);
		HEMLOCK_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("hemlock_sapling", TerrestriaBlocks.HEMLOCK_SAPLING);
		RUBBER_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("rubber_sapling", TerrestriaBlocks.RUBBER_SAPLING);
		CYPRESS_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("cypress_sapling", TerrestriaBlocks.CYPRESS_SAPLING);
		WILLOW_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("willow_sapling", TerrestriaBlocks.WILLOW_SAPLING);
		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("japanese_maple_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("japanese_maple_shrub_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("dark_japanese_maple_sapling", TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING);
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("rainbow_eucalyptus_sapling", TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		SAKURA_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("sakura_sapling", TerrestriaBlocks.SAKURA_SAPLING);
		JUNGLE_PALM_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("jungle_palm_sapling", TerrestriaBlocks.JUNGLE_PALM_SAPLING);
		SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("saguaro_cactus_sapling", TerrestriaBlocks.SAGUARO_CACTUS_SAPLING);
		YUCCA_PALM_SAPLING = TerrestriaRegistry.registerDecorationBlockItem("yucca_palm_sapling", TerrestriaBlocks.YUCCA_PALM_SAPLING);


		VOLCANIC_ROCK = StoneItems.register("basalt", TerrestriaBlocks.VOLCANIC_ROCK);
		BLACK_SAND = TerrestriaRegistry.registerBuildingBlockItem("basalt_sand", TerrestriaBlocks.BLACK_SAND);
		ANDISOL = TerrestriaRegistry.registerBuildingBlockItem("basalt_dirt", TerrestriaBlocks.ANDISOL.getDirt());
		ANDISOL_FARMLAND = TerrestriaRegistry.registerDecorationBlockItem("andisol_farmland", TerrestriaBlocks.ANDISOL.getFarmland());
		ANDISOL_GRASS_BLOCK = TerrestriaRegistry.registerBuildingBlockItem("basalt_grass_block", TerrestriaBlocks.ANDISOL.getGrassBlock());
		ANDISOL_GRASS_PATH = TerrestriaRegistry.registerDecorationBlockItem("andisol_grass_path", TerrestriaBlocks.ANDISOL.getDirtPath());
		ANDISOL_PODZOL = TerrestriaRegistry.registerBuildingBlockItem("basalt_podzol", TerrestriaBlocks.ANDISOL.getPodzol());
		INDIAN_PAINTBRUSH = TerrestriaRegistry.registerDecorationBlockItem("indian_paintbrush", TerrestriaBlocks.INDIAN_PAINTBRUSH);
		MONSTERAS = TerrestriaRegistry.registerDecorationBlockItem("monsteras", TerrestriaBlocks.MONSTERAS);

		TINY_CACTUS = TerrestriaRegistry.registerDecorationBlockItem("tiny_cactus", TerrestriaBlocks.TINY_CACTUS);
		AGAVE = TerrestriaRegistry.registerDecorationBlockItem("agave", TerrestriaBlocks.AGAVE);
		ALOE_VERA = TerrestriaRegistry.registerDecorationBlockItem("aloe_vera", TerrestriaBlocks.ALOE_VERA);
		DEAD_GRASS = TerrestriaRegistry.registerDecorationBlockItem("dead_grass", TerrestriaBlocks.DEAD_GRASS);

		LOG_TURNER = TerrestriaRegistry.registerItem("log_turner", new LogTurnerItem(new Item.Settings().group(ItemGroup.TOOLS)));
	}
}

package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneVariantBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;

public class TerrestriaBlockFamilies {
	// Wood
	public static final BlockFamily CYPRESS = fromWoodBlocks(TerrestriaBlocks.CYPRESS);
	public static final BlockFamily HEMLOCK = fromWoodBlocks(TerrestriaBlocks.HEMLOCK);
	public static final BlockFamily RAINBOW_EUCALYPTUS = fromWoodBlocks(TerrestriaBlocks.RAINBOW_EUCALYPTUS);
	public static final BlockFamily REDWOOD = fromWoodBlocks(TerrestriaBlocks.REDWOOD);
	public static final BlockFamily RUBBER = fromWoodBlocks(TerrestriaBlocks.RUBBER);
	public static final BlockFamily WILLOW = fromWoodBlocks(TerrestriaBlocks.WILLOW);
	public static final BlockFamily YUCCA_PALM = fromWoodBlocks(TerrestriaBlocks.YUCCA_PALM);

	// Stone
	public static final BlockFamily VOLCANIC_ROCK = plainFromStoneBlocks(TerrestriaBlocks.VOLCANIC_ROCK);
	public static final BlockFamily VOLCANIC_ROCK_BRICK = brickFromStoneBlocks(TerrestriaBlocks.VOLCANIC_ROCK);
	public static final BlockFamily VOLCANIC_MOSSY_ROCK_BRICK = fromStoneVariantBlocks(TerrestriaBlocks.VOLCANIC_ROCK.mossyBricks);
	public static final BlockFamily VOLCANIC_COBBLESTONE = fromStoneVariantBlocks(TerrestriaBlocks.VOLCANIC_ROCK.cobblestone);
	public static final BlockFamily VOLCANIC_MOSSY_COBBLESTONE = fromStoneVariantBlocks(TerrestriaBlocks.VOLCANIC_ROCK.mossyCobblestone);


	private static BlockFamily fromWoodBlocks(WoodBlocks woodBlocks) {
		return null;
	}

	private static BlockFamily plainFromStoneBlocks(StoneBlocks stoneBlocks) {
		return BlockFamilies.register(stoneBlocks.plain.full)
				.build();
	}

	private static BlockFamily brickFromStoneBlocks(StoneBlocks stoneBlocks) {
		return BlockFamilies.register(stoneBlocks.bricks.full)
				.chiseled(stoneBlocks.chiseledBricks)
				.cracked(stoneBlocks.crackedBricks)
				.build();
	}

	private static BlockFamily fromStoneVariantBlocks(StoneVariantBlocks stoneVarientBlocks) {
		return BlockFamilies.register(stoneVarientBlocks.full)
				.build();
	}
}

package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.block.*;

public class StoneVariantBlocks {
	public Block full;


	private StoneVariantBlocks() {}

	public static StoneVariantBlocks register(String name, MapColor color, Block family) {
		return register(name, name, color, family);
	}

	public static StoneVariantBlocks register(String name, String shapedName, MapColor color, Block family) {
		StoneVariantBlocks blocks = new StoneVariantBlocks();

		blocks.full = TerrestriaRegistry.register(name, Block::new, AbstractBlock.Settings.copy(family).mapColor(color));

		return blocks;
	}
}

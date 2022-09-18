package com.terraformersmc.terrestria.init.helpers;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;

public class StoneVariantBlocks {
	public Block full;

	private StoneVariantBlocks() {}

	public static StoneVariantBlocks register(String name, MapColor color) {
		return register(name, name, color);
	}

	public static StoneVariantBlocks register(String name, String shapedName, MapColor color) {
		StoneVariantBlocks blocks = new StoneVariantBlocks();

		blocks.full = TerrestriaRegistry.register(name, new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(color)));

		return blocks;
	}
}

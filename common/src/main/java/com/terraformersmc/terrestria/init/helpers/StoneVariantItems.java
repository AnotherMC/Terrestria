package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.item.BlockItem;

public class StoneVariantItems {
	public BlockItem full;

	private StoneVariantItems() {}

	public static StoneVariantItems register(String name, StoneVariantBlocks blocks) {
		return register(name, name, blocks);
	}

	public static StoneVariantItems register(String name, String shapedName, StoneVariantBlocks blocks) {
		StoneVariantItems items = new StoneVariantItems();

		items.full = TerrestriaRegistry.registerBlockItem(name, blocks.full);

		return items;
	}
}

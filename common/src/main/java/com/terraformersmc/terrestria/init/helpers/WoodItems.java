package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.SignItem;

public class WoodItems {

	public BlockItem log;
	public BlockItem wood;
	public BlockItem leaves;
	public BlockItem planks;
	public BlockItem strippedLog;
	public BlockItem strippedWood;

	private WoodItems() {
	}

	public static WoodItems register(String name, WoodBlocks blocks) {
		WoodItems items = new WoodItems();

		items.log = TerrestriaRegistry.registerBuildingBlockItem(name + "_log", blocks.log);
		items.leaves = TerrestriaRegistry.registerDecorationBlockItem(name + "_leaves", blocks.leaves);
		items.planks = TerrestriaRegistry.registerBuildingBlockItem(name + "_planks", blocks.planks);
		items.strippedLog = TerrestriaRegistry.registerBuildingBlockItem("stripped_" + name + "_log", blocks.strippedLog);

		if (blocks.log != blocks.wood) {
			items.wood = TerrestriaRegistry.registerBuildingBlockItem(name + "_wood", blocks.wood);
		} else {
			items.wood = items.log;
		}

		if (blocks.strippedLog != blocks.strippedWood) {
			items.strippedWood = TerrestriaRegistry.registerBuildingBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		} else {
			items.strippedWood = items.strippedLog;
		}

		return items;
	}
}

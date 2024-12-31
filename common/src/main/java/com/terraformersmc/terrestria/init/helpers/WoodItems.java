package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class WoodItems {
	private final String NAME;

	public final BlockItem log;
	public final BlockItem wood;
	public final BlockItem leaves;
	public final BlockItem strippedLog;
	public final BlockItem strippedWood;

	public ItemConvertible fallbackPlanks;


	private WoodItems(String name, WoodBlocks blocks) {
		this.NAME = name;

		log = TerrestriaRegistry.registerBlockItem(name + "_log", blocks.log);
		leaves = TerrestriaRegistry.registerBlockItem(name + "_leaves", blocks.leaves);
		strippedLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_log", blocks.strippedLog);

		if (blocks.hasWood()) {
			wood = TerrestriaRegistry.registerBlockItem(name + "_wood", blocks.wood);
			strippedWood = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		} else {
			wood = null;
			strippedWood = null;
		}
	}


	public static WoodItems register(String name, WoodBlocks blocks) {
		WoodItems items = new WoodItems(name, blocks);

		items.addCompostables();
		items.addFuels();

		return items;
	}

	protected void addCompostables() {
		CompostingChanceRegistry compostingRegistry = CompostingChanceRegistry.INSTANCE;
		float LEAVES_CHANCE = compostingRegistry.get(Items.OAK_LEAVES);

		compostingRegistry.add(leaves, LEAVES_CHANCE);
	}

	protected void addFuels() {
	}

	public String getName() {
		return NAME;
	}

	public boolean hasQuarterLog() {
		return false;
	}

	public boolean hasLeafPile() {
		return false;
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public boolean hasBoat() {
		return false;
	}
}

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
	public final BlockItem quarterLog;
	public final BlockItem wood;
	public final BlockItem leaves;
	public final BlockItem leafPile;
	public final BlockItem planks;
	public final BlockItem slab;
	public final BlockItem stairs;
	public final BlockItem fence;
	public final BlockItem fenceGate;
	public final BlockItem door;
	public final BlockItem button;
	public final BlockItem pressurePlate;
	public final SignItem sign;
	public final HangingSignItem hangingSign;
	public final BlockItem trapdoor;
	public final BlockItem strippedLog;
	public final BlockItem strippedQuarterLog;
	public final BlockItem strippedWood;
	public final Item boat;
	public final Item chestBoat;

	private WoodItems(String name, WoodBlocks blocks) {
		this.NAME = name;

		log = TerrestriaRegistry.registerBlockItem(name + "_log", blocks.log);
		leaves = TerrestriaRegistry.registerBlockItem(name + "_leaves", blocks.leaves);
		if (blocks.hasLeafPile()) {
			leafPile = TerrestriaRegistry.registerBlockItem(name + "_leaf_pile", blocks.leafPile);
		} else {
			leafPile = null;
		}
		planks = TerrestriaRegistry.registerBlockItem(name + "_planks", blocks.planks);
		slab = TerrestriaRegistry.registerBlockItem(name + "_slab", blocks.slab);
		stairs = TerrestriaRegistry.registerBlockItem(name + "_stairs", blocks.stairs);
		fence = TerrestriaRegistry.registerBlockItem(name + "_fence", blocks.fence);
		fenceGate = TerrestriaRegistry.registerBlockItem(name + "_fence_gate", blocks.fenceGate);
		door = TerrestriaRegistry.registerBlockItem(name + "_door", blocks.door);
		button = TerrestriaRegistry.registerBlockItem(name + "_button", blocks.button);
		pressurePlate = TerrestriaRegistry.registerBlockItem(name + "_pressure_plate", blocks.pressurePlate);
		trapdoor = TerrestriaRegistry.registerBlockItem(name + "_trapdoor", blocks.trapdoor);
		sign = TerrestriaRegistry.register(name + "_sign", settings -> new SignItem(blocks.sign, blocks.wallSign, settings), new Item.Settings().maxCount(16));
		hangingSign = TerrestriaRegistry.register(name + "_hanging_sign", settings -> new HangingSignItem(blocks.hangingSign, blocks.wallHangingSign, settings), new Item.Settings().maxCount(16));
		strippedLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_log", blocks.strippedLog);

		Identifier family = Identifier.of(Terrestria.MOD_ID, name);
		boat = TerraformBoatItemHelper.registerBoatItem(family, false);
		chestBoat = TerraformBoatItemHelper.registerBoatItem(family, true);

		if (blocks.hasWood()) {
			wood = TerrestriaRegistry.registerBlockItem(name + "_wood", blocks.wood);
			strippedWood = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_wood", blocks.strippedWood);
		} else {
			wood = null;
			strippedWood = null;
		}

		if (blocks.hasQuarterLog()) {
			quarterLog = TerrestriaRegistry.registerBlockItem(name + "_quarter_log", blocks.quarterLog);
			strippedQuarterLog = TerrestriaRegistry.registerBlockItem("stripped_" + name + "_quarter_log", blocks.strippedQuarterLog);
		} else {
			quarterLog = null;
			strippedQuarterLog = null;
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
		if (hasLeafPile()) {
			compostingRegistry.add(leafPile, LEAVES_CHANCE);
		}
	}

	protected void addFuels() {
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(fence, 300);
			builder.add(fenceGate, 300);
		});
	}

	public String getName() {
		return NAME;
	}

	public boolean hasQuarterLog() {
		return (quarterLog != null && strippedQuarterLog != null);
	}

	public boolean hasLeafPile() {
		return (leafPile != null);
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public boolean hasBoat() {
		return (boat != null && chestBoat != null);
	}
}

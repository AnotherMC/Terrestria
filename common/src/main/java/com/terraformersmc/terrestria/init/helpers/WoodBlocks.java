package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.leaves.block.LeafPileBlock;
import com.terraformersmc.terraform.wood.block.PillarLogHelper;
import com.terraformersmc.terrestria.block.TerrestriaOptiLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;

public class WoodBlocks {
	public final Block log;
	public final Block wood;
	public final Block leaves;
	public final LeafPileBlock leafPile;
	public final Block strippedLog;
	public final Block strippedWood;
	private final String NAME;
	private final WoodColors COLORS;
	private final LogSize SIZE;

	private WoodBlocks(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarterLog, boolean usesExtendedLeaves) {
		this.NAME = name;
		this.COLORS = colors;
		this.SIZE = size;

		// register manufactured blocks

		if (usesExtendedLeaves) {
			leaves = TerrestriaRegistry.register(name + "_leaves", new TerrestriaOptiLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
		} else {
			leaves = TerrestriaRegistry.register(name + "_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
		}

		if (hasLeafPile) {
			leafPile = TerrestriaRegistry.register(name + "_leaf_pile", new LeafPileBlock(AbstractBlock.Settings.copy(Blocks.PINK_PETALS).mapColor(colors.leaves)));
		} else {
			leafPile = null;
		}


		log = TerrestriaRegistry.register(name + "_log", PillarLogHelper.of(colors.planks, colors.bark));
		strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", PillarLogHelper.of(colors.planks));

		wood = TerrestriaRegistry.register(name + "_wood", PillarLogHelper.of(colors.bark));
		strippedWood = TerrestriaRegistry.register("stripped_" + name + "_wood", PillarLogHelper.of(colors.planks));

	}

	public static WoodBlocks register(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarteredLog, boolean usesExtendedLeaves) {
		WoodBlocks blocks = new WoodBlocks(name, colors, size, hasLeafPile, hasQuarteredLog, usesExtendedLeaves);

		blocks.addFlammables();
		blocks.addStrippables();

		return blocks;
	}

	public static WoodBlocks register(String name, WoodColors colors, LogSize size) {
		return register(name, colors, size, false, false, false);
	}

	public static WoodBlocks register(String name, WoodColors colors) {
		return register(name, colors, LogSize.NORMAL);
	}

	private void addFlammables() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();

		// tree
		flammableRegistry.add(log, 5, 5);
		flammableRegistry.add(strippedLog, 5, 5);
		if (hasWood()) {
			flammableRegistry.add(wood, 5, 5);
			flammableRegistry.add(strippedWood, 5, 5);
		}

		flammableRegistry.add(leaves, 30, 60);
		if (hasLeafPile()) {
			flammableRegistry.add(leafPile, 30, 60);
		}
	}

	private void addStrippables() {
		if (log != null && strippedLog != null) {
			StrippableBlockRegistry.register(log, strippedLog);
		}
		if (wood != null && strippedWood != null) {
			StrippableBlockRegistry.register(wood, strippedWood);
		}
	}

	public String getName() {
		return NAME;
	}

	public WoodColors getColors() {
		return COLORS;
	}

	public LogSize getSize() {
		return SIZE;
	}

	public boolean hasQuarterLog() {
		return false;
	}

	public boolean hasLeafPile() {
		return (leafPile != null);
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public enum LogSize {
		NORMAL("normal"),
		SMALL("small");

		private final String name;

		LogSize(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}

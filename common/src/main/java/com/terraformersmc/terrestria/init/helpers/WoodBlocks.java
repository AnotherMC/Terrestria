package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.wood.api.block.PillarLogHelper;
import com.terraformersmc.terrestria.block.TerrestriaOptiLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;

public class WoodBlocks {
	private final String NAME;
	private final WoodColors COLORS;
	private final LogSize SIZE;

	private final boolean tintable;

	public final Block log;
	public final Block wood;
	public final Block leaves;
	public final Block strippedLog;
	public final Block strippedWood;

	private WoodBlocks(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarterLog, boolean usesExtendedLeaves, boolean isTintable) {
		this.tintable = isTintable;

		this.NAME = name;
		this.COLORS = colors;
		this.SIZE = size;

		// register manufactured blocks

		if (usesExtendedLeaves) {
			leaves = TerrestriaRegistry.register(name + "_leaves", TerrestriaOptiLeavesBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never));
		} else {
			leaves = TerrestriaRegistry.register(name + "_leaves", LeavesBlock::new, (AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
		}


		log = TerrestriaRegistry.register(name + "_log", PillarBlock::new, PillarLogHelper.createSettings(colors.planks, colors.bark));
		strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", PillarBlock::new, PillarLogHelper.createSettings(colors.planks));

		wood = TerrestriaRegistry.register(name + "_wood", PillarBlock::new, PillarLogHelper.createSettings(colors.bark));
		strippedWood = TerrestriaRegistry.register("stripped_" + name + "_wood", PillarBlock::new, PillarLogHelper.createSettings(colors.planks));
	}

	public static WoodBlocks register(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarteredLog, boolean usesExtendedLeaves, boolean isTintable) {
		WoodBlocks blocks = new WoodBlocks(name, colors, size, hasLeafPile, hasQuarteredLog, usesExtendedLeaves, isTintable);

		blocks.addFlammables();
		blocks.addStrippables();

		return blocks;
	}

	public static WoodBlocks register(String name, WoodColors colors, LogSize size) {
		return register(name, colors, size, false, false, false, true);
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
		return false;
	}

	public boolean hasWood() {
		return (wood != null && strippedWood != null);
	}

	public boolean isTintable() {
		return tintable;
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

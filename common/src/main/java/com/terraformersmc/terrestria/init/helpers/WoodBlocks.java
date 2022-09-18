package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.leaves.block.TransparentLeavesBlock;
import com.terraformersmc.terraform.wood.block.*;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.block.TerrestriaOptiLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;

public class WoodBlocks {
	public Block log;
	public Block wood;
	public Block leaves;
	public Block planks;
	public Block strippedLog;
	public Block strippedWood;
	protected String name;
	protected WoodColors colors;

	public WoodBlocks() {
	}

	protected static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, LogSize size, boolean useExtendedLeaves) {
		WoodBlocks blocks = registerManufactured(name, colors, registry);

		if (useExtendedLeaves) {
			if (size.equals(LogSize.SMALL)) {
				throw new IllegalArgumentException("Small log trees are not compatible with extended leaves, I'm not sure how you even did this...");
			}
			blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new TerrestriaOptiLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
		} else {
			if (size.equals(LogSize.SMALL)) {
				blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new TransparentLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
			} else {
				blocks.leaves = TerrestriaRegistry.register(name + "_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never)));
			}
		}


			blocks.strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			blocks.strippedWood = TerrestriaRegistry.register("stripped_" + name + "_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.planks)));
			blocks.log = TerrestriaRegistry.register(name + "_log", new StrippableLogBlock(() -> blocks.strippedLog, colors.planks, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));
			blocks.wood = TerrestriaRegistry.register(name + "_wood", new StrippableLogBlock(() -> blocks.strippedWood, colors.bark, FabricBlockSettings.copyOf(Blocks.OAK_LOG).mapColor(colors.bark)));


		blocks.addTreeFireInfo(registry);

		return blocks;
	}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry, LogSize size) {
		return register(name, colors, registry, size, false);
	}

	public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry) {
		return register(name, colors, registry, LogSize.NORMAL, false);
	}

	public static WoodBlocks registerManufactured(String name, WoodColors colors, FlammableBlockRegistry registry) {
		WoodBlocks blocks = new WoodBlocks();
		blocks.name = name;
		blocks.colors = colors;

		blocks.planks = TerrestriaRegistry.register(name + "_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).mapColor(colors.planks)));

		Identifier signTexture = new Identifier(Terrestria.MOD_ID, "entity/signs/" + name);

		blocks.addManufacturedFireInfo(registry);


		return blocks;
	}

	public void addTreeFireInfo(FlammableBlockRegistry registry) {
		registry.add(log, 5, 5);
		registry.add(strippedLog, 5, 5);

		if (wood != log) {
			registry.add(wood, 5, 5);
		}

		if (strippedWood != strippedLog) {
			registry.add(strippedWood, 5, 5);
		}

		registry.add(leaves, 30, 60);
	}

	public void addManufacturedFireInfo(FlammableBlockRegistry registry) {
		registry.add(planks, 5, 20);
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

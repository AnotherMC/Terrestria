package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.leaves.api.block.LeafPileBlock;
import com.terraformersmc.terraform.leaves.api.block.TransparentLeavesBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import com.terraformersmc.terraform.wood.api.block.PillarLogHelper;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terraform.wood.api.block.SmallLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.block.TerrestriaOptiLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;

public class WoodBlocks {
	private final String NAME;
	private final WoodColors COLORS;
	private final LogSize SIZE;

	private final boolean tintable;

	public final Block log;
	public final Block quarterLog;
	public final Block wood;
	public final Block leaves;
	public final LeafPileBlock leafPile;
	public final Block planks;
	public final SlabBlock slab;
	public final StairsBlock stairs;
	public final FenceBlock fence;
	public final FenceGateBlock fenceGate;
	public final DoorBlock door;
	public final ButtonBlock button;
	public final PressurePlateBlock pressurePlate;
	public final TerraformSignBlock sign;
	public final TerraformWallSignBlock wallSign;
	public final TerraformHangingSignBlock hangingSign;
	public final TerraformWallHangingSignBlock wallHangingSign;
	public final TrapdoorBlock trapdoor;
	public final Block strippedLog;
	public final Block strippedQuarterLog;
	public final Block strippedWood;

	private WoodBlocks(String name, WoodColors colors, LogSize size, boolean hasLeafPile, boolean hasQuarterLog, boolean usesExtendedLeaves, boolean isTintable) {
		this.tintable = isTintable;

		this.NAME = name;
		this.COLORS = colors;
		this.SIZE = size;

		// register manufactured blocks

		planks = TerrestriaRegistry.register(name + "_planks", Block::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor(colors.planks));
		slab = TerrestriaRegistry.register(name + "_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_SLAB).mapColor(colors.planks));
		stairs = TerrestriaRegistry.register(name + "_stairs", settings -> new StairsBlock(planks.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS).mapColor(colors.planks));
		fence = TerrestriaRegistry.register(name + "_fence", FenceBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_FENCE).mapColor(colors.planks));
		fenceGate = TerrestriaRegistry.register(name + "_fence_gate", settings -> new FenceGateBlock(WoodType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor(colors.planks));
		door = TerrestriaRegistry.register(name + "_door", settings -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_DOOR).mapColor(colors.planks));
		button = TerrestriaRegistry.register(name + "_button", settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).mapColor(colors.planks));
		pressurePlate = TerrestriaRegistry.register(name + "_pressure_plate", settings -> new PressurePlateBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(colors.planks));
		trapdoor = TerrestriaRegistry.register(name + "_trapdoor", settings -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor(colors.planks));

		Identifier signTexture = Identifier.of(Terrestria.MOD_ID, "entity/signs/" + name);
		sign = TerrestriaRegistry.register(name + "_sign", settings -> new TerraformSignBlock(signTexture, settings), AbstractBlock.Settings.copy(Blocks.OAK_SIGN).mapColor(colors.planks));
		wallSign = TerrestriaRegistry.register(name + "_wall_sign", settings -> new TerraformWallSignBlock(signTexture, settings), AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN).mapColor(colors.planks).lootTable(sign.getLootTableKey()));

		Identifier hangingSignTexture = Identifier.of(Terrestria.MOD_ID, "entity/signs/hanging/" + name);
		Identifier hangingSignGuiTexture = Identifier.of(Terrestria.MOD_ID, "textures/gui/hanging_signs/" + name);
		hangingSign = TerrestriaRegistry.register(name + "_hanging_sign", settings -> new TerraformHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, settings), AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN).mapColor(colors.planks));
		wallHangingSign = TerrestriaRegistry.register(name + "_wall_hanging_sign", settings -> new TerraformWallHangingSignBlock(hangingSignTexture, hangingSignGuiTexture, settings), AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(colors.planks).lootTable(hangingSign.getLootTableKey()));

		// register natural and stripped blocks

		if (usesExtendedLeaves) {
			if (size.equals(LogSize.SMALL)) {
				throw new IllegalArgumentException("Small log trees are not compatible with extended leaves, I'm not sure how you even did this...");
			}
			leaves = TerrestriaRegistry.register(name + "_leaves", TerrestriaOptiLeavesBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never));
		} else {
			if (size.equals(LogSize.SMALL)) {
				leaves = TerrestriaRegistry.register(name + "_leaves", TransparentLeavesBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never));
			} else {
				leaves = TerrestriaRegistry.register(name + "_leaves", LeavesBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(colors.leaves).allowsSpawning(TerrestriaBlocks::canSpawnOnLeaves).suffocates(TerrestriaBlocks::never).blockVision(TerrestriaBlocks::never));
			}
		}

		if (hasLeafPile) {
			leafPile = TerrestriaRegistry.register(name + "_leaf_pile", LeafPileBlock::new, AbstractBlock.Settings.copy(Blocks.PINK_PETALS).mapColor(colors.leaves));
		} else {
			leafPile = null;
		}

		if (size.equals(LogSize.SMALL)) {
			// Small logs have neither wood nor quarter logs.
			log = TerrestriaRegistry.register(name + "_log", settings -> new SmallLogBlock(leaves, settings), PillarLogHelper.createSmallLogSettings(leaves, colors.planks, colors.bark));
			strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", settings -> new SmallLogBlock(leaves, settings), PillarLogHelper.createSmallLogSettings(leaves, colors.planks));

			wood = null;
			strippedWood = null;

			quarterLog = null;
			strippedQuarterLog = null;
		} else {
			log = TerrestriaRegistry.register(name + "_log", PillarBlock::new, PillarLogHelper.createSettings(colors.planks, colors.bark));
			strippedLog = TerrestriaRegistry.register("stripped_" + name + "_log", PillarBlock::new, PillarLogHelper.createSettings(colors.planks));

			wood = TerrestriaRegistry.register(name + "_wood", PillarBlock::new, PillarLogHelper.createSettings(colors.bark));
			strippedWood = TerrestriaRegistry.register("stripped_" + name + "_wood", PillarBlock::new, PillarLogHelper.createSettings(colors.planks));

			if (hasQuarterLog) {
				quarterLog = TerrestriaRegistry.register(name + "_quarter_log", QuarterLogBlock::new, PillarLogHelper.createQuarterLogSettings(colors.planks, colors.bark));
				strippedQuarterLog = TerrestriaRegistry.register("stripped_" + name + "_quarter_log", QuarterLogBlock::new, PillarLogHelper.createSettings(colors.planks));
			} else {
				quarterLog = null;
				strippedQuarterLog = null;
			}
		}
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

		// manufactured
		flammableRegistry.add(fence, 5, 20);
		flammableRegistry.add(fenceGate, 5, 20);
		flammableRegistry.add(planks, 5, 20);
		flammableRegistry.add(slab, 5, 20);
		flammableRegistry.add(stairs, 5, 20);

		// tree
		flammableRegistry.add(log, 5, 5);
		flammableRegistry.add(strippedLog, 5, 5);
		if (hasWood()) {
			flammableRegistry.add(wood, 5, 5);
			flammableRegistry.add(strippedWood, 5, 5);
		}
		if (hasQuarterLog()) {
			flammableRegistry.add(quarterLog, 5, 5);
			flammableRegistry.add(strippedQuarterLog, 5, 5);
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
		if (quarterLog != null && strippedQuarterLog != null) {
			StrippableBlockRegistry.register(quarterLog, strippedQuarterLog);
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
		return (quarterLog != null && strippedQuarterLog != null);
	}

	public boolean hasLeafPile() {
		return (leafPile != null);
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

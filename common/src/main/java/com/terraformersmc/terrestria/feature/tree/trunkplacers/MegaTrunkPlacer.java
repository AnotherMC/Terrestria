package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;

import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class MegaTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<MegaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
		fillTrunkPlacerFields(instance).apply(instance, MegaTrunkPlacer::new)
	);

	public MegaTrunkPlacer(int height, int randomHeight, int extraRandomHeight) {
		super(height, randomHeight, extraRandomHeight);
	}

	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.MEGA;
	}

	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int trunkHeight, BlockPos pos, TreeFeatureConfig treeFeatureConfig) {
		// Set the blocks below the trunk to dirt
		BlockPos down = pos.down();
		setToDirt(world, replacer, random, down, treeFeatureConfig);
		setToDirt(world, replacer, random, down.east(), treeFeatureConfig);
		setToDirt(world, replacer, random, down.south(), treeFeatureConfig);
		setToDirt(world, replacer, random, down.south().east(), treeFeatureConfig);

		// Place the trunk
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		//var dir = Direction.Type.HORIZONTAL.random(random);

		//var curvedTree = random.nextFloat() < 0.7;

		int xOff = 0;
		int zOff = 0;
		//int height = random.nextBetween(14, 16);

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, xOff, i, zOff);
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1 + xOff, i, zOff);
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1 + xOff, i, 1 + zOff);
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, xOff, i, 1 + zOff);

			/*if (curvedTree && i == height) {
				xOff += dir.getOffsetX();
				zOff += dir.getOffsetZ();
				for (int y = 0; y < 3; y++) {
					setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, xOff, i + y, zOff);
					setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1 + xOff, i + y, zOff);
					setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1 + xOff, i + y, 1 + zOff);
					setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, xOff, i + y, 1 + zOff);
				}
				height = random.nextBetween(14, trunkHeight);
			}*/

		}

		BlockStateProvider rootsProvider = treeFeatureConfig.trunkProvider;

		if (treeFeatureConfig instanceof QuarteredMegaTreeConfig) {
			rootsProvider = ((QuarteredMegaTreeConfig) treeFeatureConfig).rootsProvider;
		}

		growRoots(replacer, world, pos.mutableCopy(), random, rootsProvider);

		return ImmutableList.of(new FoliagePlacer.TreeNode(pos.up(trunkHeight), 0, true));
	}

	static BlockState getState(Random random, BlockPos pos, TreeFeatureConfig config, QuarterLogBlock.BarkSide side) {
		if (config instanceof QuarteredMegaTreeConfig && Terrestria.getConfigManager().getGeneralConfig().areQuarterLogsEnabled()) {
			return ((QuarteredMegaTreeConfig) config).quarteredTrunkProvider.get(random, pos).with(QuarterLogBlock.BARK_SIDE, side);
		} else {
			return config.trunkProvider.get(random, pos);
		}
	}

	private static void setLog(TestableWorld testableWorld, BlockPos.Mutable mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state, BlockPos blockPos, int i, int j, int k) {
		mutable.set(blockPos, i, j, k);

		setLog(testableWorld, mutable, replacer, state);
	}

	protected static void setLog(TestableWorld testableWorld, BlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state) {
		if (TreeFeature.canReplace(testableWorld, mutable)) {
			replacer.accept(mutable.toImmutable(), state);
		}
	}

	public void growRoots(BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos.Mutable pos, Random random, BlockStateProvider wood) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(replacer, world, pos.set(x - 1, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, world, pos.set(x + 2, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, world, pos.set(x + random.nextInt(2), y, z - 1), random, wood);
		tryGrowRoot(replacer, world, pos.set(x + random.nextInt(2), y, z + 2), random, wood);
	}

	public void tryGrowRoot(BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos.Mutable bottom, Random random, BlockStateProvider wood) {
		// Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		// Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		// Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.canReplace(world, bottom) || TreeFeature.canReplace(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				replacer.accept(bottom.toImmutable(), wood.get(random, bottom));
			}

			bottom.move(Direction.UP);
		}
	}
}

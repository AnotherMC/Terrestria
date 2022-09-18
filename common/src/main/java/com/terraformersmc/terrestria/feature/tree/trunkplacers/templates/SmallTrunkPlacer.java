package com.terraformersmc.terrestria.feature.tree.trunkplacers.templates;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.function.BiConsumer;

public abstract class SmallTrunkPlacer extends TrunkPlacer {

	public SmallTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	protected void setBlockStateAndUpdate(TreeFeatureConfig config, Random random, BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos origin, Direction direction) {
		//Place the block
		checkAndPlaceSpecificBlockState(world, origin, replacer, config.trunkProvider.getBlockState(random, origin).with(PillarBlock.AXIS, direction.getAxis()));

		// Fix the one behind it to connect if it's a BareSmallLogBlock
		addSmallLogConnection(config, random, replacer, world, origin.offset(direction.getOpposite()), direction);
	}

	protected void addSmallLogConnection(TreeFeatureConfig config, Random random, BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos origin, Direction direction) {
		if (world.testBlockState(origin, tester -> tester.getBlock() instanceof PillarBlock)) {
			placeSpecificBlockState(world, replacer, origin, getOriginalState(config, world, origin, random).with(PillarBlock.AXIS, direction.getAxis()));
		}
	}

	protected static void checkAndPlaceSpecificBlockState(TestableWorld testableWorld, BlockPos blockPos, BiConsumer<BlockPos, BlockState> replacer, BlockState blockState) {
		if (TreeFeature.canReplace(testableWorld, blockPos)) {
			placeSpecificBlockState(testableWorld, replacer, blockPos, blockState);
		}
	}

	protected static void placeSpecificBlockState(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> replacer, BlockPos blockPos, BlockState blockState) {
		replacer.accept(blockPos.toImmutable(), blockState);
	}

	protected BlockState getOriginalState(TreeFeatureConfig config, TestableWorld world, BlockPos pos, Random random) {

		if (!world.testBlockState(pos, tester -> tester.getBlock() instanceof PillarBlock)) {
			return config.trunkProvider.getBlockState(random, pos);
		}

		var mut = pos.mutableCopy();

		Direction.Axis axis;
		if (
				world.testBlockState(mut.set(pos).move(Direction.NORTH, 1), test -> test.getBlock() instanceof PillarBlock)
						|| world.testBlockState(mut.set(pos).move(Direction.SOUTH, 1), test -> test.getBlock() instanceof PillarBlock)
		) {
			axis = Direction.Axis.Z;
		} else if (
				world.testBlockState(mut.set(pos).move(Direction.WEST, 1), test -> test.getBlock() instanceof PillarBlock)
						|| world.testBlockState(mut.set(pos).move(Direction.EAST, 1), test -> test.getBlock() instanceof PillarBlock)
		) {
			axis = Direction.Axis.Z;
		} else {
			axis = Direction.Axis.Y;
		}

		return config.trunkProvider.getBlockState(random, pos).with(PillarBlock.AXIS, axis);
	}
}

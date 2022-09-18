package com.terraformersmc.terrestria.feature.tree.foliageplacers.templates;

import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.block.SmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.function.BiConsumer;

public abstract class SmallFoliagePlacer extends FoliagePlacer {

	public SmallFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	protected void tryPlaceLeaves(TestableWorld world, BlockPos pos, Random random, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config) {
		if (world.testBlockState(pos, BlockState::isAir)) {
			replacer.accept(pos, config.foliageProvider.getBlockState(random, pos));
		}
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

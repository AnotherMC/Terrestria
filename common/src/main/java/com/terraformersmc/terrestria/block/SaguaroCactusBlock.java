package com.terraformersmc.terrestria.block;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.function.Supplier;

public class SaguaroCactusBlock extends PillarBlock {

	public SaguaroCactusBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0F);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	// For Lithium - https://github.com/jellysquid3/lithium-fabric/blob/1.16.x/dev/src/main/java/me/jellysquid/mods/lithium/api/pathing/BlockPathingBehavior.java
	public PathNodeType getPathNodeType(BlockState state) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	// For Lithium - https://github.com/jellysquid3/lithium-fabric/blob/1.16.x/dev/src/main/java/me/jellysquid/mods/lithium/api/pathing/BlockPathingBehavior.java
	public PathNodeType getNeighborPathNodeType(BlockState state) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!isSupported(state, world, pos)) {
			world.breakBlock(pos, true);
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (!isSupported(state, world, pos)) {
			world.createAndScheduleBlockTick(pos, this, 1);
		}

		return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	public boolean isSupportedBlock(Block block) {
		return block == TerrestriaBlocks.SAGUARO_CACTUS || block.getDefaultState().isIn(BlockTags.SAND);
	}

	private boolean isSupported(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockState;
		if (isSupportedBlock(world.getBlockState(pos.down()).getBlock())) {
			return true;
		}

		return canBeSupported(world, pos);
	}

	private boolean canBeSupported(WorldView world, BlockPos pos) {
		return world.getBlockState(pos.north()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS ||
				world.getBlockState(pos.south()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS ||
				world.getBlockState(pos.east()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS ||
				world.getBlockState(pos.west()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS;
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return isSupportedBlock(world.getBlockState(pos.down()).getBlock()) || canBeSupported(world, pos);
	}
}

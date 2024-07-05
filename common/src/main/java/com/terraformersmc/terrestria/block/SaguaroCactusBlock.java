package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class SaguaroCactusBlock extends BareSmallLogBlock {
	public SaguaroCactusBlock(Settings settings) {
		super(settings);
	}

	public static SaguaroCactusBlock of(MapColor color) {
		return new SaguaroCactusBlock(AbstractBlock.Settings.create()
				.mapColor(color)
				.strength(0.4F)
				.sounds(BlockSoundGroup.WOOL)
				.pistonBehavior(PistonBehavior.DESTROY)
		);
	}

	public static SaguaroCactusBlock of(MapColor flesh, MapColor skin) {
		return new SaguaroCactusBlock(AbstractBlock.Settings.create()
				.mapColor((state) -> state.get(UP) ? flesh : skin)
				.strength(0.4F)
				.sounds(BlockSoundGroup.WOOL)
				.pistonBehavior(PistonBehavior.DESTROY)
		);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(world.getDamageSources().cactus(), 1.0f);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
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
			world.scheduleBlockTick(pos, this, 1);
		}

		return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	private boolean isSupportedBlock(BlockState state) {
		return state.isOf(TerrestriaBlocks.SAGUARO_CACTUS) || state.isIn(BlockTags.SAND);
	}

	private boolean isSupported(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockState;

		if (isSupportedBlock(world.getBlockState(pos.down()))) {
			return true;
		}

		if (state.get(BareSmallLogBlock.DOWN)) {
			blockState = world.getBlockState(pos.down());
			return (blockState.isOf(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.get(BareSmallLogBlock.UP));
		}
		if (state.get(BareSmallLogBlock.SOUTH)) {
			blockState = world.getBlockState(pos.south());
			return (blockState.isOf(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.get(BareSmallLogBlock.NORTH));
		}
		if (state.get(BareSmallLogBlock.NORTH)) {
			blockState = world.getBlockState(pos.north());
			return (blockState.isOf(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.get(BareSmallLogBlock.SOUTH));
		}
		if (state.get(BareSmallLogBlock.WEST)) {
			blockState = world.getBlockState(pos.west());
			return (blockState.isOf(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.get(BareSmallLogBlock.EAST));
		}
		if (state.get(BareSmallLogBlock.EAST)) {
			blockState = world.getBlockState(pos.east());
			return (blockState.isOf(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.get(BareSmallLogBlock.WEST));
		}

		return false;
	}

	private boolean canBeSupported(WorldView world, BlockPos pos) {
		return world.getBlockState(pos.north()).isOf(TerrestriaBlocks.SAGUARO_CACTUS) ||
				world.getBlockState(pos.south()).isOf(TerrestriaBlocks.SAGUARO_CACTUS) ||
				world.getBlockState(pos.east()).isOf(TerrestriaBlocks.SAGUARO_CACTUS) ||
				world.getBlockState(pos.west()).isOf(TerrestriaBlocks.SAGUARO_CACTUS);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return isSupportedBlock(world.getBlockState(pos.down())) || canBeSupported(world, pos);
	}
}

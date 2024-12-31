package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.FoliageColors;
import net.minecraft.world.biome.GrassColors;

// This class is an entrypoint
@Environment(EnvType.CLIENT)
public class TerrestriaClient implements ClientModInitializer {
	@SuppressWarnings("unused")
	private static final RenderLayer LEAVES_ITEM_LAYER = TexturedRenderLayers.getEntityCutout();
	private static final RenderLayer GRASS_BLOCK_LAYER = RenderLayer.getCutoutMipped();
	private static final RenderLayer PLANT_BLOCK_LAYER = RenderLayer.getCutout();
	private static final RenderLayer DOOR_BLOCK_LAYER = RenderLayer.getCutout();

	private static final BlockColorProvider FOLIAGE_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor();
	private static final BlockColorProvider GRASS_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
	private static final ItemColorProvider FOLIAGE_ITEM_COLORS =
			(item, layer) -> FoliageColors.getColor(0.5, 1.0);
	private static final ItemColorProvider GRASS_ITEM_COLORS =
			(item, layer) -> GrassColors.getColor(0.5, 1.0);

	@Override
	public void onInitializeClient() {
		// Load the client config if it hasn't been loaded already
		Terrestria.getConfigManager().getClientConfig();
	}

	private void registerEntityRenderers() {
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "redwood"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "hemlock"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "rubber"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "cypress"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "willow"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "japanese_maple"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "rainbow_eucalyptus"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "sakura"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.of(Terrestria.MOD_ID, "yucca_palm"));
	}

	private void addColoredGrass(Block grass) {
		BlockRenderLayerMap.INSTANCE.putBlock(grass, GRASS_BLOCK_LAYER);
		ColorProviderRegistry.BLOCK.register(GRASS_BLOCK_COLORS, grass);
	}
}

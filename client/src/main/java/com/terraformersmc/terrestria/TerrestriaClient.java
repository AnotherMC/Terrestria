package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

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
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "redwood"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "hemlock"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "rubber"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "cypress"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "willow"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "japanese_maple"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "rainbow_eucalyptus"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "sakura"), false);
		TerraformBoatClientHelper.registerModelLayers(new Identifier(Terrestria.MOD_ID, "yucca_palm"), false);
	}

	@SafeVarargs
	private <S extends AbstractSignBlock> void addSigns(S... signs) {
		for (AbstractSignBlock maybeSign : signs) {
			if (maybeSign instanceof TerraformSignBlock sign) {
				addSignTexture(sign.getTexture());
			} else if(maybeSign instanceof TerraformHangingSignBlock sign) {
				addSignTexture(sign.getTexture());
			} else {
				throw new IllegalArgumentException("Only Terraform API signs may be registered via this method.");
			}
		}
	}

	private void addSignTexture(Identifier texture) {
		SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, texture));
	}

	private void addColoredGrass(Block grass) {
		BlockRenderLayerMap.INSTANCE.putBlock(grass, GRASS_BLOCK_LAYER);
		ColorProviderRegistry.BLOCK.register(GRASS_BLOCK_COLORS, grass);
	}
}

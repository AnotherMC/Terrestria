package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.tree.api.merchant.TerraformSaplingTradeHelper;

public class TerrestriaTrades {
	public static void register() {
		TerraformSaplingTradeHelper.registerWanderingTraderSaplingTrades(
				TerrestriaBlocks.BRYCE_SAPLING,
				TerrestriaBlocks.CYPRESS_SAPLING,
				TerrestriaBlocks.HEMLOCK_SAPLING,
				TerrestriaBlocks.JUNGLE_PALM_SAPLING,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING,
				TerrestriaBlocks.REDWOOD_SAPLING,
				TerrestriaBlocks.RUBBER_SAPLING,
				TerrestriaBlocks.SAGUARO_CACTUS_SAPLING,
				TerrestriaBlocks.WILLOW_SAPLING,
				TerrestriaBlocks.YUCCA_PALM_SAPLING
		);
	}
}

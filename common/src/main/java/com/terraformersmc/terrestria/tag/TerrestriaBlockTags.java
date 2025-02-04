package com.terraformersmc.terrestria.tag;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class TerrestriaBlockTags {
	public static final TagKey<Block> BASALTS = TerrestriaBlockTags.of(Identifier.of("c", "basalts"));
	public static final TagKey<Block> BLACK_SANDS = TerrestriaBlockTags.of(Identifier.of("c", "black_sands"));
	public static final TagKey<Block> DIRTS = TerrestriaBlockTags.of(Identifier.of("c", "dirts"));
	public static final TagKey<Block> PLANKS_THAT_BURN = TerrestriaBlockTags.of(Identifier.of("c", "planks_that_burn"));
	public static final TagKey<Block> PODZOLS = TerrestriaBlockTags.of(Identifier.of("c", "podzols"));
	public static final TagKey<Block> SANDS = TerrestriaBlockTags.of(Identifier.of("c", "sands"));

	public static final TagKey<Block> CYPRESS_LOGS = TerrestriaBlockTags.of("cypress_logs");
	public static final TagKey<Block> HEMLOCK_LOGS = TerrestriaBlockTags.of("hemlock_logs");
	public static final TagKey<Block> RAINBOW_EUCALYPTUS_LOGS = TerrestriaBlockTags.of("rainbow_eucalyptus_logs");
	public static final TagKey<Block> REDWOOD_LOGS = TerrestriaBlockTags.of("redwood_logs");
	public static final TagKey<Block> RUBBER_LOGS = TerrestriaBlockTags.of("rubber_logs");
	public static final TagKey<Block> SAKURA_LOGS = TerrestriaBlockTags.of("sakura_logs");
	public static final TagKey<Block> SMALL_OAK_LOGS = TerrestriaBlockTags.of("small_oak_logs");
	public static final TagKey<Block> WILLOW_LOGS = TerrestriaBlockTags.of("willow_logs");
	public static final TagKey<Block> YUCCA_PALM_LOGS = TerrestriaBlockTags.of("yucca_palm_logs");

	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaBlockTags() {
		return;
	}

	private static TagKey<Block> of(String path) {
		return TerrestriaBlockTags.of(Identifier.of(Terrestria.MOD_ID, path));
	}

	private static TagKey<Block> of(Identifier id) {
		return TagKey.of(RegistryKeys.BLOCK, id);
	}
}

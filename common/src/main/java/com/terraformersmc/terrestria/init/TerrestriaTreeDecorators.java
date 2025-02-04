package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.tree.api.decorator.DecoratorTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class TerrestriaTreeDecorators {

	public static TreeDecoratorType<DanglingLeavesTreeDecorator> DANGLING_LEAVES;
	public static TreeDecoratorType<SakuraTreeDecorator> SAKURA;

	public static void init() {
		DANGLING_LEAVES = register("dangling_leaves_tree_decorator", DanglingLeavesTreeDecorator.CODEC);
	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String name, MapCodec<P> codec) {
		return DecoratorTypes.registerTreeDecorator(Identifier.of(Terrestria.MOD_ID, name), codec);
	}
}

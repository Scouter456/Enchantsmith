package com.scouter.enchantsmith.datagen;

import com.mojang.logging.LogUtils;
import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class BlockstateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int DEFAULT_ANGLE_OFFSET = 180;

    public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, EnchantSmith.MODID, exFileHelper);
    }

    private String blockName(Block block) {
        return block.getLootTable().getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(EnchantSmith.MODID, "block/" + path);
    }

    private String getName(Block block) {
        return key(block).toString().replace(EnchantSmith.MODID + ":", "");
    }

    public ModelFile existingModel(Block block) {
        return new ModelFile.ExistingModelFile(resourceBlock(getName(block)), models().existingFileHelper);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }
    @Override
    protected void registerStatesAndModels(){


    }

    public void createCrossBlock(RegistryObject<Block> block){
        String baseName = getName(block.get());
        ResourceLocation name = key(block.get());
        createBlockState(block.get());
        this.models().cross(baseName, resourceBlock(baseName));
    }

    private void createBlockState(Block block) {
        getVariantBuilder(block).forAllStatesExcept((state) -> ConfiguredModel.builder().modelFile(existingModel(block))
                .build());
    }


  // private void createSimpleFlatItemModel(Block pFlatBlock) {
  //     Item item = pFlatBlock.asItem();
  //     if (item != Items.AIR) {
  //         ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(pFlatBlock), this.modelOutput);
  //     }

  // }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private BlockModelBuilder fromBlock(String name, ResourceLocation... layers) {
        BlockModelBuilder builder = models().withExistingParent("item/" + name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }

    //private void toBlock(RegistryObject<Block> b) {
    //    toBlockModel(b, b.getId().getPath());
    //}
//
    //private void toBlockModel(RegistryObject<Block> b, String model) {
    //    toBlockModel(b, prefix("block/" + model));
    //}
//
    //private void toBlockModel(RegistryObject<Block> b, ResourceLocation model) {
    //    this.models().withExistingParent(b.getId().getPath(), model);
    //}

    private BlockModelBuilder generated(String name, ResourceLocation... layers) {
        BlockModelBuilder builder = models().withExistingParent("item/" + name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }

    public void tomatoBlock(Block block, ResourceLocation texture, String renderType) {
        tomatoBlock(block, key(block).toString(), texture, renderType);
    }

    public void tomatoBlock(Block block, String baseName, ResourceLocation texture, String  renderType) {
        ModelFile age1 = models().cross(baseName + "_stage0", new ResourceLocation(texture.toString() + "_stage0")).renderType(renderType);
        ModelFile age2 = models().cross(baseName + "_stage1",  new ResourceLocation(texture.toString() + "_stage1")).renderType(renderType);
        ModelFile age3 = models().cross(baseName + "_stage2",  new ResourceLocation(texture.toString() + "_stage2")).renderType(renderType);
        ModelFile age4 = models().cross(baseName + "_stage3",  new ResourceLocation(texture.toString() + "_stage3")).renderType(renderType);

        tomatoBlock(block, age1, age2, age3, age4);
    }
    private void tomatoBlock(Block block, ModelFile age1, ModelFile age2, ModelFile age3, ModelFile age4){
        getVariantBuilder(block).forAllStatesExcept((state -> {
            switch ( state.getValue(BlockStateProperties.AGE_3)){
                default: return ConfiguredModel.builder().modelFile(age1).build();
                case 1:return ConfiguredModel.builder().modelFile(age2).build();
                case 2:return ConfiguredModel.builder().modelFile(age3).build();
                case 3:return ConfiguredModel.builder().modelFile(age4).build();
            }
        }));
    }



    @Override
    public String getName() {
        return "Block States: " + EnchantSmith.MODID;
    }
}

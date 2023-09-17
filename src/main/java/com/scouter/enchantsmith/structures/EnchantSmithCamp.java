package com.scouter.enchantsmith.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.enchantsmith.EnchantSmith;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.material.Fluids;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Random;

public class EnchantSmithCamp extends Structure {

    static Random rand = new Random();
    public static final Codec<EnchantSmithCamp> CODEC =  RecordCodecBuilder.<EnchantSmithCamp>mapCodec(instance ->
            instance.group(EnchantSmithCamp.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, EnchantSmithCamp::new)).codec();

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;


    public EnchantSmithCamp(StructureSettings config,
                            Holder<StructureTemplatePool> startPool,
                            Optional<ResourceLocation> startJigsawName,
                            int size,
                            HeightProvider startHeight,
                            Optional<Heightmap.Types> projectStartToHeightmap,
                            int maxDistanceFromCenter)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    //private static boolean isFeatureChunk(Structure.GenerationContext context, BlockPos pos) {
    //    // Grabs the chunk position we are at
    //    ChunkPos chunkpos = context.chunkPos();
    //      // Checks to make sure our structure does not spawn within 10 chunks of an Ocean Monument
    //// to demonstrate how this method is good for checking extra conditions for spawning
    //     return !context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.VILLAGES., context.randomState(), context.seed(), chunkpos.getMiddleBlockX(), chunkpos.getMinBlockZ(),0);
    //}
    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        ChunkPos chunkpos = context.chunkPos();

        int startY = context.chunkGenerator().getFirstOccupiedHeight(
                chunkpos.getMinBlockX(),
                chunkpos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState());
        BlockPos centerPos = new BlockPos(context.chunkPos().getMinBlockX(), startY, context.chunkPos().getMinBlockZ());

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        blockpos = blockpos.offset(0,startY,0);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        mutable = mutable.set(centerPos);
        //NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), context.heightAccessor(), context.randomState());
        //if(columnOfBlocks.getBlock(mutable.below().getY()).is(Blocks.AIR)){
        //    return Optional.empty();
        //}
        //mutable = mutable.move(-40,0,0);
        //if(columnOfBlocks.getBlock(mutable.below().getY()).is(Blocks.AIR)){
        //    return Optional.empty();
        //}
        //mutable = mutable.move(0,0,40);
        //if(columnOfBlocks.getBlock(mutable.below().getY()).is(Blocks.AIR)){
        //    return Optional.empty();
        //}
        //mutable = mutable.move(40,0,0);

        for(int i = 0; i < 40; i++){
            for(int j = 0; j < 40; j++){
                NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), context.heightAccessor(), context.randomState());
                if(columnOfBlocks.getBlock(mutable.below().getY()).is(Blocks.AIR)){
                    return Optional.empty();
                }
                mutable = mutable.move(i,0,j);
            }
        }


        System.out.println("I am working!, at " + blockpos);
        // Set's our spawning blockpos's y offset to be 60 blocks up.
        // Since we are going to have heightmap/terrain height spawning set to true further down, this will make it so we spawn 60 blocks above terrain.
        // If we wanted to spawn on ocean floor, we would set heightmap/terrain height spawning to false and the grab the y value of the terrain with OCEAN_FLOOR_WG heightmap.

        Optional<GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context, // Used for JigsawPlacement to get all the proper behaviors done.
                        this.startPool, // The starting pool to use to create the structure layout from
                        this.startJigsawName, // Can be used to only spawn from one Jigsaw block. But we don't need to worry about this.
                        this.size, // How deep a branch of pieces can go away from center piece. (5 means branches cannot be longer than 5 pieces from center piece)
                        blockpos, // Where to spawn the structure.
                        false, // "useExpansionHack" This is for legacy villages to generate properly. You should keep this false always.
                        this.projectStartToHeightmap, // Adds the terrain height's y value to the passed in blockpos's y value. (This uses WORLD_SURFACE_WG heightmap which stops at top water too)
                        // Here, blockpos's y value is 60 which means the structure spawn 60 blocks above terrain height.
                        // Set this to false for structure to be place only at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                        this.maxDistanceFromCenter); // Maximum limit for how far pieces can spawn from center. You cannot set this bigger than 128 or else pieces gets cutoff.


        /*
         * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
         *
         * An example of a custom JigsawPlacement.addPieces in action can be found here (warning, it is using Mojmap mappings):
         * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18.2/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */

        if(structurePiecesGenerator.isPresent()) {
            // I use to debug and quickly find out if the structure is spawning or not and where it is.
            // This is returning the coordinates of the center starting piece.
            //LOGGER.info("Fortress piece at {}", blockpos);
        }
        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> type() {
        return ESStructures.ENCHANTSMITH_CAMP.get(); // Helps the game know how to turn this structure back to json to save to chunks
    }
}

package net.akneey.litestoragemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class PipeBlock extends Block {

    public static final Map<Direction, BooleanProperty> FACING = new EnumMap<>(Direction.class);

    static {
        for (Direction dir : Direction.values()) {
            FACING.put(dir, BooleanProperty.create(dir.getName()));
        }
    }

    public PipeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        BlockState defaultState = this.defaultBlockState();
        for (BooleanProperty prop : FACING.values()) {
            defaultState = defaultState.setValue(prop, false);
        }
        this.registerDefaultState(defaultState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        FACING.values().forEach(builder::add);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return updateConnections(level, pos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos currentPos, BlockPos neighborPos) {
        return updateConnections(world, currentPos);
    }

    private BlockState updateConnections(BlockGetter world, BlockPos pos) {
        BlockState state = this.defaultBlockState();
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            BlockState neighbor = world.getBlockState(neighborPos);
            boolean connects = canConnectTo(neighbor);
            state = state.setValue(FACING.get(dir), connects);
        }
        return state;
    }

    private boolean canConnectTo(BlockState state) {
        Block block = state.getBlock();
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        if (id == null) return false;

        return block instanceof PipeBlock
                || id.getPath().equals("storage_block")
                || id.getPath().equals("chest");
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape core = box(6, 6, 6, 10, 10, 10); // центральная часть
        VoxelShape shape = core;

        for (Direction dir : Direction.values()) {
            if (state.getValue(FACING.get(dir))) {
                switch (dir) {
                    case NORTH -> shape = Shapes.or(shape, box(6, 6, 0, 10, 10, 6));
                    case SOUTH -> shape = Shapes.or(shape, box(6, 6, 10, 10, 10, 16));
                    case WEST ->  shape = Shapes.or(shape, box(0, 6, 6, 6, 10, 10));
                    case EAST ->  shape = Shapes.or(shape, box(10, 6, 6, 16, 10, 10));
                    case UP ->    shape = Shapes.or(shape, box(6, 10, 6, 10, 16, 10));
                    case DOWN ->  shape = Shapes.or(shape, box(6, 0, 6, 10, 6, 10));
                }
            }
        }

        return shape;
    }
}

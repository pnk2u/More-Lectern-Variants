package de.pnku.mlv.poi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.pnku.mlv.init.MlvBlockInit;
import de.pnku.mlv.mixin.PoiTypesAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MlvPointOfInterestTypes {
    public static void init() {
        Map<BlockState, Holder<PoiType>> poiStatesToType = PoiTypesAccessor
                .getPointOfInterestStatesToType();

        Holder<PoiType> librarianEntry = BuiltInRegistries.POINT_OF_INTEREST_TYPE
                .get(PoiTypes.LIBRARIAN).get();

        PoiType librarianPoiType = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getValue(PoiTypes.LIBRARIAN);

        List<BlockState> librarianBlockStates = new ArrayList<BlockState>(librarianPoiType.matchingStates);

        for (Block block : MlvBlockInit.more_lecterns) {
            ImmutableList<BlockState> blockStates = block.getStateDefinition().getPossibleStates();

            for (BlockState blockState : blockStates) {
                poiStatesToType.putIfAbsent(blockState, librarianEntry);
            }

            librarianBlockStates.addAll(blockStates);
        }

        librarianPoiType.matchingStates = ImmutableSet.copyOf(librarianBlockStates);
    }
}
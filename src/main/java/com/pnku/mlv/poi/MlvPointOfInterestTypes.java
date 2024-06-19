package com.pnku.mlv.poi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.pnku.mlv.init.MlvBlockInit;
import com.pnku.mlv.mixin.PointOfInterestTypesAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MlvPointOfInterestTypes {
    public static void init() {
        Map<BlockState, RegistryEntry<PointOfInterestType>> poiStatesToType = PointOfInterestTypesAccessor
                .getPointOfInterestStatesToType();

        RegistryEntry<PointOfInterestType> librarianEntry = Registries.POINT_OF_INTEREST_TYPE
                .getEntry(PointOfInterestTypes.LIBRARIAN).get();

        PointOfInterestType librarianPoiType = Registries.POINT_OF_INTEREST_TYPE.get(PointOfInterestTypes.LIBRARIAN);

        List<BlockState> librarianBlockStates = new ArrayList<BlockState>(librarianPoiType.blockStates);

        for (Block block : MlvBlockInit.more_lecterns) {
            ImmutableList<BlockState> blockStates = block.getStateManager().getStates();

            for (BlockState blockState : blockStates) {
                poiStatesToType.putIfAbsent(blockState, librarianEntry);
            }

            librarianBlockStates.addAll(blockStates);
        }

        librarianPoiType.blockStates = ImmutableSet.copyOf(librarianBlockStates);
    }
}
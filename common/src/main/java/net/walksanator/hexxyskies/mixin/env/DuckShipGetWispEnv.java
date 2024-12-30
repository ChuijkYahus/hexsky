package net.walksanator.hexxyskies.mixin.env;

import net.minecraft.core.BlockPos;
import net.walksanator.hexxyskies.duck.ShipGetterEnvironment;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.api.ValkyrienSkies;
import ram.talia.hexal.api.casting.eval.env.WispCastEnv;
import ram.talia.hexal.common.entities.BaseCastingWisp;

@Pseudo
@Mixin(WispCastEnv.class)
public class DuckShipGetWispEnv implements ShipGetterEnvironment {
    @Final
    @Shadow(remap = false)
    private BaseCastingWisp wisp;

    @Override
    public @Nullable ServerShip hexsky$getShip() {
        return (ServerShip) ValkyrienSkies.getShipManagingBlock(wisp.level(), BlockPos.containing(wisp.position()));
    }
}

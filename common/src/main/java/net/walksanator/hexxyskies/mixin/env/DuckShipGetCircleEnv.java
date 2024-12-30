package net.walksanator.hexxyskies.mixin.env;

import at.petrak.hexcasting.api.casting.circles.BlockEntityAbstractImpetus;
import at.petrak.hexcasting.api.casting.circles.CircleExecutionState;
import at.petrak.hexcasting.api.casting.eval.env.CircleCastEnv;
import net.walksanator.hexxyskies.duck.ShipGetterEnvironment;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.api.ValkyrienSkies;

@Mixin(CircleCastEnv.class)
public abstract class DuckShipGetCircleEnv implements ShipGetterEnvironment {
    @Final
    @Shadow(remap = false)
    protected CircleExecutionState execState;

    @Shadow(remap = false)
    public abstract BlockEntityAbstractImpetus getImpetus();

    @Override
    public @Nullable ServerShip hexsky$getShip() {
        return (ServerShip) ValkyrienSkies.getShipManagingBlock(getImpetus().getLevel(), execState.impetusPos);
    }
}

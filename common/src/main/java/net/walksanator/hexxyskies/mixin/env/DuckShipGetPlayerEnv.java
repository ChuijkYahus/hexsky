package net.walksanator.hexxyskies.mixin.env;

import at.petrak.hexcasting.api.casting.eval.env.PlayerBasedCastEnv;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.walksanator.hexxyskies.duck.ShipGetterEnvironment;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.api.ValkyrienSkies;

@Mixin(PlayerBasedCastEnv.class)
public class DuckShipGetPlayerEnv  implements ShipGetterEnvironment {
    @Final
    @Shadow
    protected ServerPlayer caster;

    @Override
    public @Nullable ServerShip hexsky$getShip() {
        return (ServerShip) ValkyrienSkies.getShipManagingBlock(caster.serverLevel(), BlockPos.containing(caster.position()));
    }
}

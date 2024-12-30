package net.walksanator.hexxyskies.casting.patterns

import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.NullIota
import at.petrak.hexcasting.api.misc.MediaConstants
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.walksanator.hexxyskies.casting.iotas.ShipIota
import net.walksanator.hexxyskies.ship.getShipDataHolder
import org.valkyrienskies.core.api.ships.LoadedServerShip
import org.valkyrienskies.mod.api.getShipManagingBlock
import org.valkyrienskies.mod.common.getShipObjectManagingPos
import org.valkyrienskies.mod.common.util.toJOML

object OpShipRaycast: ConstMediaAction {
    override val argc = 2
    override val mediaCost: Long = MediaConstants.DUST_UNIT / 100
    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val origin = args.getVec3(0, argc)
        val look = args.getVec3(1, argc)

        env.assertVecInRange(origin)

        val blockHitResult = env.world.clip(
            ClipContext(
                origin,
                Action.raycastEnd(origin, look),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                env.castingEntity as Entity
            )
        )

        return listOf(when(blockHitResult.type) {
            HitResult.Type.BLOCK -> getShipIotaOrNull(env, Vec3.atCenterOf(blockHitResult.blockPos))
            else -> NullIota()
        })
    }

    private fun getShipIotaOrNull(env: CastingEnvironment, pos: Vec3): Iota {
        if (!env.isVecInRange(pos)) return NullIota()
        val ship = env.world.getShipManagingBlock(BlockPos.containing(pos)) as? LoadedServerShip ?: return NullIota()
        if (ship.getShipDataHolder().cloaked) return NullIota()
        return ShipIota(ship.id, ship.slug)
    }
}
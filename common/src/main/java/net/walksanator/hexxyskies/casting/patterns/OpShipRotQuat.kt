package net.walksanator.hexxyskies.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import dev.kineticcat.complexhex.api.casting.iota.QuaternionIota
import dev.kineticcat.complexhex.stuff.Quaternion
import net.walksanator.hexxyskies.getShip
import org.joml.Quaterniondc
import org.valkyrienskies.core.api.ships.LoadedServerShip

object OpShipRotQuat : ConstMediaAction {
    override val argc: Int = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val ship = args.getShip(0,env.world,argc) as LoadedServerShip
        val quat = ship.transform.shipToWorldRotation.toComplexHex()
        return listOf(QuaternionIota(quat))
    }
}

fun Quaterniondc.toComplexHex(): Quaternion =
    Quaternion(this.w(), this.x(), this.y(), this.z())
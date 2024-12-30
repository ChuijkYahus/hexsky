package net.walksanator.hexxyskies.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.BooleanIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import net.walksanator.hexxyskies.getShip
import net.walksanator.hexxyskies.ship.getShipDataHolder
import org.valkyrienskies.core.api.ships.LoadedServerShip

object OpCloak : ConstMediaAction {
    override val argc: Int = 1
    override val mediaCost: Long = MediaConstants.CRYSTAL_UNIT * 3

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val ship = args.getShip(0,env.world,argc) as LoadedServerShip
        val dh = ship.getShipDataHolder()
        dh.cloaked = !dh.cloaked;
        return listOf(BooleanIota(dh.cloaked))
    }
}
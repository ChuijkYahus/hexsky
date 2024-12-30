package net.walksanator.hexxyskies.util

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.valkyrienskies.mod.api.getShipManagingBlock
import org.valkyrienskies.mod.common.util.toJOML
import org.valkyrienskies.mod.common.util.toMinecraft

fun Vec3.worldly(level: Level): Vec3 =
    level.getShipManagingBlock(BlockPos.containing(this))?.shipToWorld?.transformPosition(this.toJOML())?.toMinecraft() ?: this

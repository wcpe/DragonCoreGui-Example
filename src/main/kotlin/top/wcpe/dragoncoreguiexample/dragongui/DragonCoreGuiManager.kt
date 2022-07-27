package top.wcpe.dragoncoreguiexample.dragongui

import org.bukkit.entity.Player

/**
 * 由 WCPE 在 2022/7/27 2:35 创建
 *
 * Created by WCPE on 2022/7/27 2:35
 *
 * GitHub  : https://github.com/wcpe
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.0.0
 */
object DragonCoreGuiManager {
    fun reload() {
        exampleGui.reload()
    }

    private val exampleGui = ExampleDragonCoreGui()

    fun openExample(player: Player) {
        exampleGui.openExample(player)
    }

}
package top.wcpe.dragoncoreguiexample.dragongui

import org.bukkit.entity.Player
import top.wcpe.dragoncoregui.compose.DragonCoreComposeAction
import top.wcpe.dragoncoregui.compose.DragonCoreTextBoxCompose
import top.wcpe.dragoncoregui.gui.AbstractDragonCoreGui

/**
 * 由 WCPE 在 2022/7/27 2:36 创建
 *
 * Created by WCPE on 2022/7/27 2:36
 *
 * GitHub  : https://github.com/wcpe
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.0.0
 */
class ExampleDragonCoreGui : AbstractDragonCoreGui("DragonCoreGui-Example/DragonCoreGui/", "example") {
    fun openExample(player: Player) {
        //清理缓存组件
        clearCompose()

        //...添加组件
        addCompose(DragonCoreTextBoxCompose("example_view").apply {
            extends = "example"
            addActionCallBack(
                DragonCoreComposeAction.TEXTCHANGE,
                listOf("方法.取组件值('example_view','text')")
            ) { data, player ->
                println("${player.name} -> $data")
            }
            addActionCallBack(DragonCoreComposeAction.CLICK) { key, player ->
                println("${player.name} -> $key")
            }

        })

        //打开 Gui
        openGui(player)
    }
}
package top.wcpe.dragoncoreguiexample

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import top.wcpe.dragoncoregui.extend.dragonCoreGui
import top.wcpe.dragoncoregui.extend.textbox
import top.wcpe.dragoncoreguiexample.dragongui.DragonCoreGuiManager
import java.io.File

/**
 * 由 WCPE 在 2022/7/27 2:33 创建
 *
 * Created by WCPE on 2022/7/27 2:33
 *
 * GitHub  : https://github.com/wcpe
 * QQ      : 1837019522
 * @author : WCPE
 * @since  : v1.0.0
 */
class DragonCoreGuiExample : JavaPlugin() {
    companion object {
        @JvmStatic
        lateinit var instance: DragonCoreGuiExample

    }

    private val dragonCoreGuiDirFile = File(dataFolder, "DragonGui")

    override fun saveDefaultConfig() {
        super.saveDefaultConfig()
        if (!dragonCoreGuiDirFile.exists()) {
            saveResource("DragonCoreGui/example.yml", false)
        }
    }

    fun reloadOtherConfig() {
        DragonCoreGuiManager.reload()
    }


    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        getCommand("dcge").executor = this
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        when (args.size) {
            in 1..2 -> {
                when (args[0]) {
                    "reload" -> {
                        reloadConfig()
                        reloadOtherConfig()
                        sender.sendMessage("重载配置文件完成")
                    }

                    "test" -> {
                        val player = Bukkit.getPlayerExact(args[1])
                        if (player == null || !player.isOnline) {
                            sender.sendMessage("§c玩家 §e${args[1]} §c不存在或不在线!")
                            return true
                        }
                        DragonCoreGuiManager.openExample(player)
                    }

                    "test2" -> {
                        val player = Bukkit.getPlayerExact(args[1])
                        if (player == null || !player.isOnline) {
                            sender.sendMessage("§c玩家 §e${args[1]} §c不存在或不在线!")
                            return true
                        }
                        dragonCoreGui("/t", "t") {
                            allowEscClose = "false"
                            through = "true"
                            addCustomFunction(
                                "keyPress",
                                "(方法.取当前按下键=='ESCAPE')?{方法.异步执行方法('closeFunction');}:0"
                            )
                            addCustomFunction("open", "方法.异步执行方法('perSecondUpdateTask')")
                            addCustomFunction("closeFunction", "方法.关闭界面")
                            addCustomFunction(
                                "perSecondUpdateTask",
                                listOf(
                                    "界面变量.x=(方法.取屏幕宽度-方法.取组件值('background_texture','width'))/2",
                                    "界面变量.y=(方法.取屏幕高度-方法.取组件值('background_texture','height'))/2",
                                    "方法.延时(1000)",
                                    "方法.异步执行方法('perSecondUpdateTask')"
                                )

                            )
                            textbox("background") {
                                x = "界面变量.x"
                                y = "界面变量.y"
                                width = "260"
                                height = "300"
                            }
                        }.openGui(player)
                    }
                }
            }
        }
        return true
    }

}
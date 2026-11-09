package study.coco.project_code.handler.gamecontrolls

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save


@Component
@Order(1)
class OpenInventoryHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "i" || verb == "inventar" || verb == "inventory" || verb == "item" || verb == "bauplaene"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        if (save.spieler.inventar.isNotEmpty()) {
            val inventar = mutableListOf<String>()
            inventar.add(Text.get(OrtText.HELP, "inventar"))
            for (i in save.spieler.inventar) {
                inventar.add("-${i.name}")
            }
            return inventar.joinToString("\n")
        }
        return Text.get(OrtText.HELP, "leer_inventar")
    }
}
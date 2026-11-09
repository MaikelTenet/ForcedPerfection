package study.coco.project_code.handler.gamecontrolls

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class StatsHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "stat" || verb == "stats" || verb == "status" || verb == "saturn"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        return if (save.spieler.waffeAusgerüstet == null) Text.get(OrtText.HELP, "stats_no_waffe", save.spieler.leben)
        else Text.get(
            OrtText.HELP,
            "stats",
            save.spieler.leben,
            save.spieler.waffeAusgerüstet!!.name,
            save.spieler.schaden
        )
    }
}
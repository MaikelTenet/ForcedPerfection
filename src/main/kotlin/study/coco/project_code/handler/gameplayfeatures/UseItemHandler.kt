package study.coco.project_code.handler.gameplayfeatures

import bauplaene.OrtText
import bauplaene.WaffeItem
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class UseItemHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "use" || verb == "u" || verb == "verwende" || verb == "aktiviere" || verb == "equip" || verb == "eat" || verb == "esse" || verb == "essen" || verb == "read" || verb == "lese" || verb == "lesen"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        if (ding == null) return Text.get(OrtText.ERRORS, "kein_item_angegeben")
        for (i in save.spieler.inventar) {
            for (i2 in i.ansprechbareNamen) {
                if (i2 == ding) {
                    return when (verb) {
                        "equiq", "ausrüsten", "ausrüste", "rüsten" -> {
                            if (i is WaffeItem) i.use(save)
                            else Text.get(OrtText.ERRORS, "keine_waffe")
                        }

                        "eat", "esse", "essen" -> {
                            if (i.eatable) i.use(save)
                            else Text.get(OrtText.ERRORS, "kein_essen")
                        }

                        "read", "lese", "lesen" -> {
                            if (i.readable) i.use(save)
                            else Text.get(OrtText.ERRORS, "kein_buch")
                        }

                        else -> i.use(save)
                    }
                }
            }
        }
        if (save.welcherRaum() == save.zuhause) {
            for (n in save.bett.ansprechbareNamen) {
                if (n == ding) return save.bett.use(save)
            }
            for (n in save.computer.ansprechbareNamen) {
                if (n == ding) return save.computer.use(save)
            }
        }

        return Text.get(OrtText.ERRORS, "item_none", ding)
    }
}
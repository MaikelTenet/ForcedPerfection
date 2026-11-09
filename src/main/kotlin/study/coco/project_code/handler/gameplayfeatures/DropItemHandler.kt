package study.coco.project_code.handler.gameplayfeatures

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class DropItemHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "drop" || verb == "ablegen" || verb == "d"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        for (i in save.spieler.inventar) {
            for (i2 in i.ansprechbareNamen) {
                if (i2 == ding) return i.drop(save)
            }
        }
        return if (ding == null) Text.get(OrtText.ERRORS, "kein_item_angegeben")
        else Text.get(OrtText.ERRORS, "item_none", ding)
    }
}
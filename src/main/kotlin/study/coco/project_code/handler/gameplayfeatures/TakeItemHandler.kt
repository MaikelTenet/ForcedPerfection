package study.coco.project_code.handler.gameplayfeatures

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class TakeItemHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "take" || verb == "grab" || verb == "t" || verb == "nimm"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        val raum = save.welcherRaum()
        if (ding == null) return Text.get(OrtText.ERRORS, "kein_item_angegeben")
        for (i in raum.herumliegend) {
            for (i2 in i.ansprechbareNamen) {
                if (i2 == ding) {
                    return i.wichTake(save, raum)
                }
            }
        }
        return Text.get(OrtText.ERRORS, "item_none", ding)
    }
}
package study.coco.project_code.handler.gameplayfeatures

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class SprechenHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "speak" || verb == "talk" || verb == "sprechen" || verb == "sp" || verb == "sprich" || verb == "sprichst"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        if (ding == null) return Text.get(OrtText.ERRORS, "keine_person_angegeben")
        for (c in save.welcherRaum().interaktionen) {
            for (c2 in c.ansprechbareNamen) {
                if (c2 == ding) return c.unterhaltung(save, 0)
            }
        }
        return Text.get(OrtText.ERRORS, "peron_none", ding)
    }
}
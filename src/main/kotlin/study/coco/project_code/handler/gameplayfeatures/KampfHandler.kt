package study.coco.project_code.handler.gameplayfeatures

import bauplaene.OrtText
import study.coco.project_code.handler.gamecontrolls.BeendenHandler
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class KampfHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "attack" || verb == "angriff" || verb == "attak" || verb == "kampf" || verb == "kill" || verb == "fight" || verb == "atack" || verb == "töte" || verb == "hit" || verb == "schlag" || verb == "assault"
    }

    override fun handeln(
        verb: String, ding: String?, save: Save
    ): String {
        for (c in save.welcherRaum().interaktionen) {
            for (c2 in c.ansprechbareNamen) {
                if (c2 == ding) return c.kampf(save, "0")
            }
        }
        return when (ding) {
            "yourself" -> return BeendenHandler().handeln(verb, ding, save)
            null -> Text.get(OrtText.ERRORS, "keine_person_angegeben")
            else -> Text.get(OrtText.ERRORS, "peron_none", ding)
        }
    }
}
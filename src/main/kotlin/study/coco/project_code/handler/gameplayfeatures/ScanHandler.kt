package study.coco.project_code.handler.gameplayfeatures

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class ScanHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "scan" || verb == "scannen"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        if (save.spieler.inventar.contains(save.scanner)) {
            if (ding == null) return Text.get(OrtText.ERRORS, "keine_person_angegeben")
            for (p in save.welcherRaum().interaktionen) {
                for (p2 in p.ansprechbareNamen) {
                    if (p2 == ding) {
                        return p.scan(save)
                    }
                }
            }
            return Text.get(OrtText.ERRORS, "peron_none", ding)
        }
        return Text.get(OrtText.ERRORS, "kein_scanner")
    }
}
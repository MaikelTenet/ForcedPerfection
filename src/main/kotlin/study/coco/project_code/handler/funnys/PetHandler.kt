package study.coco.project_code.handler.funnys

import bauplaene.OrtText
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save
import study.coco.project_code.handler.Handler

@Component
@Order(1)
class PetHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "pet" || verb == "streichel" || verb == "streicheln"
    }

    override fun handeln(verb: String, ding: String?, save: Save): String {
        if (ding == null) return Text.get(OrtText.MEME, "kein_streichel_angegeben")
        for (p in save.welcherRaum().interaktionen) {
            for (p2 in p.ansprechbareNamen) {
                if (p2 == ding) {
                    if (p == save.hundNPC) {
                        if (save.hundNPC.anfangsDialog == -1 && !save.hundNPC.befreundet) {
                            save.spieler.changeHealth(
                                save,
                                save.hundNPC.schaden,
                                true,
                                Text.get(OrtText.MEME, "hund_streichel_tod")
                            )
                                ?.let { return it }
                            return Text.get(OrtText.MEME, "hund_streichel_sauer", save.hundNPC.schaden)
                        } else return Text.get(OrtText.MEME, "hund_streichel")
                    } else return Text.get(OrtText.MEME, "mensch_streichel", p.name)
                }
            }
        }
        return Text.get(OrtText.MEME, "kein_steichel", ding)
    }
}
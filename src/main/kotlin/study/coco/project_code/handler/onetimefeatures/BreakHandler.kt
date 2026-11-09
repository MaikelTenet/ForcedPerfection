package study.coco.project_code.handler.onetimefeatures

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class BreakHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "break" || verb == "zerstöre" || verb == "destroy" || verb == "schneide" || verb == "zerschneide"
    }

    override fun handeln(verb: String, ding: String?, save: Save): String {
        if (save.welcherRaum() == save.farm && ding in save.glascontainerItem.ansprechbareNamen) return Text.get(
            OrtText.ERRORS,
            "glas_einschlagen"
        )
        if (save.welcherRaum() == save.baustelle && ding in save.ranken.ansprechbareNamen && !save.ranken.durchgeschnitten) {
            val obWaffe: Item? = save.ranken.durchschneiden(save)
            return if (obWaffe != null) Text.get(OrtText.ERRORS, "ranken_zerschneiden", obWaffe.name)
            else Text.get(OrtText.ERRORS, "ranken_nicht_scharf")
        }

        return Text.get(OrtText.ERRORS, "none_break")
    }
}
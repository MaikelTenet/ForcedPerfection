package study.coco.project_code.handler.onetimefeatures

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class SchlafHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "schlaf" || verb == "sleep" || verb == "slep" || verb == "schlafen" || verb == "nap"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        if (save.welcherRaum() == save.zuhause) {
            return save.bett.use(save)
        }
        return Text.get(OrtText.ROOM, "no_splep")
    }
}
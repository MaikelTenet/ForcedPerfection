package study.coco.project_code.handler.funnys

import bauplaene.OrtText
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.handler.Handler
import study.coco.project_code.Save

@Component
@Order(1)
class WartenHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "wait" || verb == "warten" || verb == "warte"
    }

    override fun handeln(verb: String, ding: String?, save: Save): String {
        return Text.get(OrtText.MEME, "warten")
    }
}
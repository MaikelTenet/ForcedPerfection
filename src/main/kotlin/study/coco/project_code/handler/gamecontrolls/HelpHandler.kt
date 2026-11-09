package study.coco.project_code.handler.gamecontrolls

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class HelpHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "help" || verb == "hilfe" || verb == "h"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        return Text.get(OrtText.HELP, "help")
    }
}
package study.coco.project_code.handler.gamecontrolls

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class MehrCommandsHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "mehr" || verb == "more" || verb == "merh"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        return Text.get(OrtText.HELP, "mehr_command")
    }
}
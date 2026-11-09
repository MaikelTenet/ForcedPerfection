package study.coco.project_code.handler.funnys

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save
import study.coco.project_code.handler.Handler

@Component
@Order(1)
class YoHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "yo" || verb == "jo"
    }

    override fun handeln(verb: String, ding: String?, save: Save): String {
        return "gurt"
    }
}
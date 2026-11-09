package study.coco.project_code.handler.funnys

import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class SadHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == ":("
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        return ":("
    }
}
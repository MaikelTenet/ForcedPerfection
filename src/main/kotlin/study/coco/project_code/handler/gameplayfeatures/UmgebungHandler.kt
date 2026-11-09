package study.coco.project_code.handler.gameplayfeatures

import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class UmgebungHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "look" || verb == "watch" || verb == "umsehen" || verb == "analysieren" || verb == "l" || verb == "umschauen" || verb == "schau" || verb == "schauen" || verb == "umsehen/look/l"
    }

    override fun handeln(
        verb: String,
        ding: String?,
        save: Save
    ): String {
        return save.welcherRaum().tippReader(save)
    }
}
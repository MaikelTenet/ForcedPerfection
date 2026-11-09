package study.coco.project_code.handler.funnys

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class BeleidigungenHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "hurensohn" || verb == "leck" || verb == "hs" || verb == "bastard" || verb == "opfer" || verb == "kian" || verb == "doofmann" || verb == "muggel" || verb == "malaria" || verb == "fuck" || verb == "fotze" || verb == "bitch" || verb == "rüsselnase"
    }

    override fun handeln(
        verb: String, ding: String?, save: Save
    ): String {
        return Text.get(OrtText.MEME, "beleidigung")
    }
}
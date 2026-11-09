package study.coco.project_code.handler.gamecontrolls

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save


@Component
@Order(1)
class BeendenHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return (verb == "exit" || verb == "quit" || verb == "q" || verb == "stop")
    }

    override fun handeln(verb: String, ding: String?, save: Save): String {
        if (verb == "kill") {
            save.memeBeenden = true
            save.ideeZuBeenden = true
            return Text.get(OrtText.MEME, "kill_self")
        }
        if (!save.ideeZuBeenden) {
            save.ideeZuBeenden = true
            return Text.get(OrtText.STORYFEEDS, "1_beenden")
        }
        when (verb) {
            "1" -> {
                save.nochLaufen = false
                save.ideeZuBeenden = false
                if (save.memeBeenden) {
                    save.memeBeenden = false
                    return Text.get(OrtText.MEME, "kill_self_true")
                }
                return Text.get(OrtText.STORYFEEDS, "beendet") + Text.get(OrtText.STORYFEEDS, "tod")
            }

            "2" -> {
                save.ideeZuBeenden = false
                if (save.memeBeenden) {
                    save.memeBeenden = false
                    return Text.get(OrtText.MEME, "not_kill")
                }
                return Text.get(OrtText.STORYFEEDS, "not_beenden")
            }

            else -> return Text.get(OrtText.ERRORS, "falsche_antwort")
        }
    }
}
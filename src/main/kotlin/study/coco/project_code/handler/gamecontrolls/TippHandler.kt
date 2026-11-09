package study.coco.project_code.handler.gamecontrolls

import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class TippHandler : Handler {
    override fun funktionsCheck(verb: String): Boolean {
        return verb == "tipp" || verb == "tip"
    }

    override fun handeln(verb: String, ding: String?, save: Save): String {
        val alleTipps: MutableList<String> = mutableListOf<String>(
            Text.get(OrtText.HELP, "tipp_1"),
            Text.get(OrtText.HELP, "tipp_2"),
            Text.get(OrtText.HELP, "tipp_3"),
            Text.get(OrtText.HELP, "tipp_4"),
            Text.get(OrtText.HELP, "tipp_5"),
            Text.get(OrtText.HELP, "tipp_6"),
            Text.get(OrtText.HELP, "tipp_7"),
        )

        for (t in save.welcherRaum().tipps) {
            //raumspezifische Tipps sollen häufiger kommen

            //Das hab ich vergessen zu machen...
            alleTipps.add(t)
            alleTipps.add(t)
        }
        return alleTipps.random()
    }
}
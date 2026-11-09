package study.coco.project_code

import Text
import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import study.coco.project_code.handler.gamecontrolls.*
import study.coco.project_code.handler.funnys.*
import study.coco.project_code.handler.onetimefeatures.*
import study.coco.project_code.handler.gameplayfeatures.*
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.SessionScope

@Component
@SessionScope
class Game(private val alleHandlers: List<Handler>) {

    private var save = Save()

    //alle bisherigen 1 Wort Abkürzungen: q, h, w, i, s, sp, t, g, l
    //val f = listOf<Handler>(BeendenHandler(), DropItemHandler(), MehrCommandsHandler(), HelpHandler(), LaufenHandler(), KampfHandler(), TakeItemHandler(), SadHandler(), StatsHandler(), SchlafHandler(), SprechenHandler(), OpenInventoryHandler(), BeleidigungenHandler(), ScanHandler(), UseItemHandler(), UmgebungHandler(), CommandHandler(), BreakHandler(), WartenHandler(), TippHandler())


    fun gameplay(input: String): String {
        val ergebnis: List<String> = command(input)

        val verb: String = ergebnis[0].lowercase()
        var ding: String? = null
        if (ergebnis.size == 2) {
            ding = ergebnis[1].trim().lowercase()
        }
        /*
                //falls ich beim playtesten an einer stelle feststecke
                if (verb == "debug"){
                    save.ideeZuBeenden = false
                    save.memeBeenden = false
                    for (d in save.allePersonen) {
                        d.activDialog = false
                        d.activeFight = false
                    }
                    save.spieler.xVariable = 1
                    save.spieler.yVariable = -1
                }
         */

        if (save.nochLaufen) {

            val zwischenevent: String? = save.zwischenEvents(verb, ding)
            if (zwischenevent != null) return zwischenevent

            for (h in alleHandlers) {
                if (h.funktionsCheck(verb)) {
                    return h.handeln(verb, ding, save)
                }
            }
            return Text.get(OrtText.ERRORS, "nicht_existent")
        }
        if (verb == "start" || verb == "reset") {
            return neustarten()
        }
        return Text.get(OrtText.STORYFEEDS, "start_screen")
    }

    private fun command(input: String): List<String> {
        val eingabe: String = input.trim()
        if (eingabe.isNotEmpty() && eingabe.contains(" ")) {
            val alleWorte: List<String> = eingabe.split(" ", limit = 2)

            return alleWorte
        }
        return eingabe.split(" ")
    }

    private fun neustarten(): String {
        save = Save()
        save.nochLaufen = true
        return Text.get(OrtText.STORYFEEDS, "intro_text")
    }
}
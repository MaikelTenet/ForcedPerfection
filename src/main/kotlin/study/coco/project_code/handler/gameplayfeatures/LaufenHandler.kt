package study.coco.project_code.handler.gameplayfeatures


import bauplaene.OrtText
import study.coco.project_code.handler.Handler
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import study.coco.project_code.Save

@Component
@Order(1)
class LaufenHandler : Handler {

    override fun funktionsCheck(verb: String): Boolean {
        return verb == "walk" || verb == "gehe" || verb == "lauf" || verb == "laufe" || verb == "w" || verb == "renn" || verb == "go" || verb == "move" || verb == "g" || verb == "s" || verb == "e" || verb == "o" || verb == "n"
    }

    override fun handeln(
        verb: String, ding: String?, save: Save
    ): String {
        when (verb) {
            "n" -> return betreteRaum(1, save)
            "o", "e" -> return betreteRaum(2, save)
            "s" -> return betreteRaum(3, save)
            "w" -> return betreteRaum(4, save)
        }

        when (ding?.lowercase()) {
            "n", "norden", "north", "vorne", "gerade", "gerade aus" -> return betreteRaum(1, save)
            "o", "e", "osten", "east", "rechts", "right" -> return betreteRaum(2, save)
            "s", "süden", "south", "hinten", "nach hinten" -> return betreteRaum(3, save)
            "w", "west", "westen", "links", "left" -> return betreteRaum(4, save)
            else -> {
                return Text.get(OrtText.ERRORS, "keine_richtung")

            }
        }
    }

    private fun betreteRaum(richtung: Int, save: Save): String { //1: Norden, 2: Osten, 3 Süden, 4: Weseten
        val failwalk = save.failWalk(richtung)

        if (failwalk != null) {
            return failwalk
        }

        var x = save.spieler.xVariable
        var y = save.spieler.yVariable
        when (richtung) {
            1 -> y++
            2 -> x++
            3 -> y--
            4 -> x--
        }

        val neuerRaum = save.alleRäume.find {
            it.xKoordinate == x && it.yKoordinate == y
        }
        if (neuerRaum != null) {
            save.spieler.xVariable = x
            save.spieler.yVariable = y
            return neuerRaum.opening
        }
        return Text.get(OrtText.ERRORS, "abgesperrt")
    }
}
package bauplaene

import study.coco.project_code.Save

open class Raum(
    val xKoordinate: Int,
    val yKoordinate: Int,
    val opening: String,
    val interaktionen: MutableList<NPC>,
    val herumliegend: MutableList<Item>,
    val tipps: List<String> = listOf()
) {
    open fun tippReader(save: Save): String {
        /*
        val umschauText = mutableListOf<String>()

        umschauText.add(tippExtras(listOf(save.stock, save.hund)))
        return umschauText.joinToString("\n")
         */
        return "Hier fehlt noch ein Tipp Reader"
    }

    internal fun tippExtras(nichtBetrachtete: List<Item>, umschauText: MutableList<String>, save: Save): String {
        if (save.arthurNPC.scannerAbgegeben) {
            val randomZahl = (1..10).random()
            if (randomZahl == 1) umschauText.add(Text.get(OrtText.ROOM, "beobachtet"))
        }

        val liegendeItems =
            herumliegend.filterNot { item -> nichtBetrachtete.any { it === item } || item == save.häufchen }
        if (liegendeItems.isNotEmpty()) {
            umschauText.add(Text.get(OrtText.ROOM, "dropped_item"))
            for (i in liegendeItems) {
                umschauText.add("-${i.name}")
            }
        }
        return umschauText.joinToString("\n")
    }
}
package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class ShopKeeperNPC(
    save: Save,
    val sortiment: MutableList<String> = mutableListOf<String>(
        save.axt.name,
        save.medikamente.name,
        save.polizeikleidung.name
    )
) : NPC(
    Text.get(OrtText.NAME, "shooper"),
    100,
    15,
    Text.get(OrtText.SHOPPER, "scan"),
    Text.get(OrtText.SHOPPER, "kampfscan"),
    Text.get(OrtText.SHOPPER, "final"),
    mutableListOf("verkäufer", "salesman", "salesman", "verkäuferin", "shop", "shopkeeper", "guard"),
    mapOf(

        0 to Dialog(
            Text.get(OrtText.SHOPPER, "willkommen"),
            listOf(AlleAntworten.Shopper.kaufen, AlleAntworten.Shopper.nichtKaufen)
        ),
        1 to Dialog(
            Text.get(OrtText.SHOPPER, "vor_erklärung"),
            listOf(AlleAntworten.Shopper.erklärBitte, AlleAntworten.Shopper.sortiment)
        ),
        2 to Dialog(
            Text.get(OrtText.SHOPPER, "erklären"),
            listOf(AlleAntworten.Shopper.sortiment, AlleAntworten.Shopper.nichtKaufen)
        ),
        3 to Dialog(
            Text.get(OrtText.SHOPPER, "sortiment_anbieten", sortiment.joinToString(", ")),
            AlleAntworten.Shopper.sortimentMöglichkeiten
        ),
        4 to Dialog(
            Text.get(OrtText.SHOPPER, "axt_verlangen", save.axt.name),
            listOf(AlleAntworten.Shopper.kanoneHaben, AlleAntworten.Shopper.besorgen, AlleAntworten.Shopper.sortiment)
        ),
        101 to Dialog(Text.get(OrtText.SHOPPER, "axt_geben", save.kanone.name), listOf()),
        5 to Dialog(
            Text.get(OrtText.SHOPPER, "gold_verlangen", save.medikamente.name, save.gold.name),
            listOf(AlleAntworten.Shopper.goldHaben, AlleAntworten.Shopper.besorgen, AlleAntworten.Shopper.sortiment)
        ),
        102 to Dialog(Text.get(OrtText.SHOPPER, "gold_geben"), listOf()),
        6 to Dialog(
            Text.get(OrtText.SHOPPER, "hund_verlangen", save.polizeikleidung.name),
            listOf(AlleAntworten.Shopper.hundHaben, AlleAntworten.Shopper.besorgen, AlleAntworten.Shopper.sortiment)
        ),
        103 to Dialog(Text.get(OrtText.SHOPPER, "hund_geben", save.polizeikleidung.name), listOf()),
        100 to Dialog(Text.get(OrtText.SHOPPER, "aufwiedersehen"), listOf()),
        -1 to Dialog(Text.get(OrtText.SHOPPER, "erneut_besuchen"), AlleAntworten.Shopper.sortimentMöglichkeiten),
    ),
    mutableListOf(save.axt, save.medikamente, save.polizeikleidung),
    30,
    20,
    50,
) {

    var axtBekommen = false
        private set
    var verkleidungBekommen = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            3 -> anfangsDialog = -1
            101 -> {
                save.spieler.inventar.remove(save.kanone)
                if (save.spieler.waffeAusgerüstet == save.kanone) save.spieler.deleteWeapon()
                val itemAusgabe = gibItem(save, save.axt)
                sortiment.remove(save.axt.name)
                axtBekommen = true
                return "\n" + itemAusgabe
            }

            102 -> {
                save.spieler.inventar.remove(save.gold)
                save.spieler.inventar.add(save.medikamente)
                return "\n" + Text.get(OrtText.ACTIONS, "default_take", save.medikamente.name)
            }

            103 -> {
                if (!save.welcherRaum().herumliegend.contains(save.hund)) save.hund.drop(save)
                val itemAusgabe = gibItem(save, save.polizeikleidung)
                sortiment.remove(save.polizeikleidung.name)
                verkleidungBekommen = true
                save.hundNPC.anfangsDialog = -3
                //save.welcherRaum().herumliegend.remove(save.hund)
                return "\n" + itemAusgabe
            }
        }

        return null
    }
}
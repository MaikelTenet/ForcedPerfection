package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class BaustelleRaum(save: Save) : Raum(
    1,
    1,
    Text.get(OrtText.ROOM, "bau_opening"),
    mutableListOf(save.sandra),
    mutableListOf(save.pipe, save.kabel, save.ranken),
    listOf(Text.get(OrtText.HELP, "baustelle_tipp1"))
) {

    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "bau_l1"))

        if (save.sandra.questBeenden) umschauText.add(Text.get(OrtText.ROOM, "bau_sandra3"))
        else {
            if (save.sandra.questAnnehmen) umschauText.add(Text.get(OrtText.ROOM, "bau_sandra2"))
            else umschauText.add(Text.get(OrtText.ROOM, "bau_sandra1"))
        }

        if (!save.ranken.durchgeschnitten) umschauText.add(Text.get(OrtText.ROOM, "bau_ranken"))
        else {
            if (herumliegend.contains(save.pipe)) {
                if (herumliegend.contains(save.kabel)) umschauText.add(Text.get(OrtText.ROOM, "bau_keine_ranken"))
                else umschauText.add(Text.get(OrtText.ROOM, "bau_only_pipe"))
            } else {
                if (herumliegend.contains(save.kabel)) umschauText.add(Text.get(OrtText.ROOM, "bau_only_kabel"))
                else umschauText.add(Text.get(OrtText.ROOM, "bau_leer"))
            }
        }

        return tippExtras(listOf(save.pipe, save.kabel, save.ranken), umschauText, save)
    }
}
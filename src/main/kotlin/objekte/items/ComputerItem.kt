package objekte.items

import bauplaene.OrtText
import bauplaene.StuckItem
import study.coco.project_code.Save

class ComputerItem : StuckItem(
    Text.get(OrtText.NAME, "computer"),
    Text.get(OrtText.ITEM, "take_computer"),
    listOf("computer", "laptop", "lap-top", "pc", "elektrische kiste"),

    ) {
    var computerHeile: Boolean = false
        private set
    var gotItem: Boolean = false
        private set

    override fun use(save: Save): String {
        if (gotItem) return Text.get(OrtText.ERRORS, "computer_leer")
        if (save.computer.computerHeile) {
            return getMaterial(save)
        }
        if (save.spieler.inventar.contains(save.kabel)) {
            computerFix(save)
            return "${Text.get(OrtText.ITEM, "reapier_computer_simple")}\n${getMaterial(save)}"
        }
        return Text.get(OrtText.ITEM, "use_computer")
    }

    internal fun computerFix(save: Save) {
        computerHeile = true
        save.spieler.inventar.remove(save.kabel)
    }

    private fun getMaterial(save: Save): String {
        gotItem = true
        save.spieler.inventar.add(save.chipPläne)
        return "${Text.get(OrtText.ITEM, "find_data")}\n${
            Text.get(
                OrtText.ACTIONS,
                "default_take",
                save.chipPläne.name
            )
        }"
    }
}
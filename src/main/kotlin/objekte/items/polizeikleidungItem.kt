package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class polizeikleidungItem : Item(
    Text.get(OrtText.NAME, "verkleidung"),
    "",
    listOf(
        "verkleidung",
        "polizeikleidung",
        "polizei",
        "polizeiverkleidung",
        "polizei verkleidung",
        "polizei kleidung",
        "polizei uniform",
        "uniform"
    ),
    Text.get(OrtText.ITEM, "use_verkleidung"),
) {
    override fun use(save: Save): String {
        save.spieler.verkleiden(save)
        return useItem
    }
}
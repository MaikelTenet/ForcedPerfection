package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class HundNPC : NPC(
    Text.get(OrtText.NAME, "hund"),
    670000000,
    3,
    Text.get(OrtText.HUND, "hund_scan"),
    Text.get(OrtText.HUND, "hund_kampf_scan"),
    Text.get(OrtText.HUND, "hund_final"),
    listOf("hund", "wau wau", "dog"),
    mapOf(
        0 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf(AlleAntworten.Hund.begruss, AlleAntworten.Hund.tschau)),
        1 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf(AlleAntworten.Hund.bitten, AlleAntworten.Hund.tschau)),
        2 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf(AlleAntworten.Hund.beteln, AlleAntworten.Hund.tschau)),
        3 to Dialog(
            Text.get(OrtText.HUND, "hund_talk"),
            listOf(AlleAntworten.Hund.erpressen, AlleAntworten.Hund.tschau)
        ),

        -1 to Dialog(
            Text.get(OrtText.HUND, "hund_talk"),
            listOf(AlleAntworten.Hund.tretten, AlleAntworten.Hund.selberSchuld, AlleAntworten.Hund.tschau)
        ),
        4 to Dialog(
            Text.get(OrtText.HUND, "hund_talk"),
            listOf(AlleAntworten.Hund.gutMachen1, AlleAntworten.Hund.böserJunge)
        ),
        5 to Dialog(
            Text.get(OrtText.HUND, "hund_talk"),
            listOf(AlleAntworten.Hund.gutMachen2, AlleAntworten.Hund.böserJunge)
        ),
        6 to Dialog(Text.get(OrtText.HUND, "hund_talk"), AlleAntworten.Hund.verzeihenBedingungen),

        -2 to Dialog(
            Text.get(OrtText.HUND, "hund_talk"),
            listOf(
                AlleAntworten.Hund.guterBesitzer,
                AlleAntworten.Hund.trick,
                AlleAntworten.Hund.angriff,
                AlleAntworten.Hund.stockchenWärfen2
            )
        ),
        -3 to Dialog(
            Text.get(OrtText.HUND, "hund_talk"),
            listOf(
                AlleAntworten.Hund.neuesBesitzer,
                AlleAntworten.Hund.trick,
                AlleAntworten.Hund.angriff,
                AlleAntworten.Hund.stockchenWärfen2
            )
        ),

        100 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
        101 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
        102 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
        103 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
        104 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
        105 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
        106 to Dialog(Text.get(OrtText.HUND, "hund_talk"), listOf()),
    ),
    mutableListOf(),
) {
    var keinFrieden: Boolean = false
        private set
    var keinDate: Boolean = false
        private set
    var keinKatze: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            101 -> {
                keinFrieden = true
                return "\n" + Text.get(OrtText.HUND, "nicht_verziehen")
            }

            102 -> return "\n" + adoptHund(save)
            103 -> {
                keinDate = true
                return "\n" + Text.get(OrtText.HUND, "nicht_verliebt")
            }

            104 -> return "\n" + Text.get(OrtText.HUND, "schlafen")
            105 -> save.stock.drop(save)
            106 -> {
                keinKatze = true
                return "\n" + Text.get(OrtText.HUND, "nicht_verziehen")
            }
        }
        return null
    }

    override fun kampf(save: Save, verb: String): String {
        return Text.get(OrtText.HUND, "hund_kampf")
    }

    var befreundet: Boolean = false
        private set

    private fun adoptHund(save: Save): String {
        befreundet = true
        anfangsDialog = -2
        save.stock.drop(save)
        return save.hund.take(save, save.welcherRaum())
    }
}
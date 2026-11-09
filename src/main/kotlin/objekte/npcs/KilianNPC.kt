package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class KilianNPC(save: Save) : NPC(
    Text.get(OrtText.NAME, "kian"),
    30,
    save.spielSchwert.schaden,
    Text.get(OrtText.KILIAN, "scan"),
    Text.get(OrtText.KILIAN, "kampfscan"),
    Text.get(OrtText.KILIAN, "final"),
    listOf("kian", "kind", "einsames kind", "junge", "kleiner junge", "boy", "kilian"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.KILIAN, "dia_freundsein"),
            listOf(
                AlleAntworten.Kilian.freundWerden,
                AlleAntworten.Kilian.feindWerden,
                AlleAntworten.Kilian.direktSchwerFragen
            )
        ),
        1 to Dialog(
            Text.get(OrtText.KILIAN, "dia_freund_statt_schwert"),
            listOf(AlleAntworten.Kilian.freundWerden, AlleAntworten.Kilian.feindWerden)
        ),
        2 to Dialog(
            Text.get(OrtText.KILIAN, "dia_freund"),
            listOf(
                AlleAntworten.Kilian.nettwhyNoFriend,
                AlleAntworten.Kilian.nettSchwert,
                AlleAntworten.Kilian.nettAbhauen
            )
        ),
        3 to Dialog(
            Text.get(OrtText.KILIAN, "dia_feind"),
            listOf(
                AlleAntworten.Kilian.gemeinwhyNoFriend,
                AlleAntworten.Kilian.gemeinSchwert,
                AlleAntworten.Kilian.gemeinAbhauen
            )
        ),
        4 to Dialog(
            Text.get(OrtText.KILIAN, "dia_whynofriends"),
            listOf(AlleAntworten.Kilian.hilfeAnbieten, AlleAntworten.Kilian.nettAbhauen)
        ),
        5 to Dialog(
            Text.get(OrtText.KILIAN, "dia_whynofriends"),
            listOf(
                AlleAntworten.Kilian.beleidigen,
                AlleAntworten.Kilian.gemeinSchwert,
                AlleAntworten.Kilian.gemeinAbhauen
            )
        ),
        6 to Dialog(
            Text.get(OrtText.KILIAN, "dia_schwerverhandeln"),
            listOf(AlleAntworten.Kilian.annehmen, AlleAntworten.Kilian.ablehnen)
        ),
        7 to Dialog(
            Text.get(OrtText.KILIAN, "dia_hilfe_bei_freunden"),
            listOf(AlleAntworten.Kilian.annehmen, AlleAntworten.Kilian.ablehnen)
        ),
        8 to Dialog(
            Text.get(OrtText.KILIAN, "dia_schwert_origin"),
            listOf(AlleAntworten.Kilian.schön, AlleAntworten.Kilian.langweilig, AlleAntworten.Kilian.omaTod)
        ),
        100 to Dialog(Text.get(OrtText.KILIAN, "dia_spiel_spielen"), listOf()),
        101 to Dialog(Text.get(OrtText.KILIAN, "dia_verabschieden"), listOf()),
        102 to Dialog(Text.get(OrtText.KILIAN, "dia_quest"), listOf()),
        103 to Dialog(Text.get(OrtText.KILIAN, "dia_keinequest"), listOf()),
        104 to Dialog(Text.get(OrtText.KILIAN, "dia_lisa_hilfe"), listOf()),
        105 to Dialog(Text.get(OrtText.KILIAN, "dia_aufwiedersehen"), listOf()),
        106 to Dialog(Text.get(OrtText.KILIAN, "dia_geschichte_positiv"), listOf()),
        107 to Dialog(Text.get(OrtText.KILIAN, "dia_geschichte_negativ"), listOf()),
        108 to Dialog(Text.get(OrtText.KILIAN, "dia_geschichte_omatod"), listOf()),
        109 to Dialog(Text.get(OrtText.KILIAN, "dia_scjwert_abgeben"), listOf()),
        110 to Dialog(Text.get(OrtText.KILIAN, "dia_wegstecken"), listOf()),
        111 to Dialog(Text.get(OrtText.KILIAN, "dia_nicht_wegstecken"), listOf()),

        -1 to Dialog(
            Text.get(OrtText.KILIAN, "dia_freund_wiedersehen"),
            listOf(
                AlleAntworten.Kilian.nettwhyNoFriend,
                AlleAntworten.Kilian.nettSchwert,
                AlleAntworten.Kilian.nettAbhauen
            )
        ),
        -2 to Dialog(
            Text.get(OrtText.KILIAN, "dia_feind_wiedersehen"),
            listOf(
                AlleAntworten.Kilian.gemeinwhyNoFriend,
                AlleAntworten.Kilian.gemeinSchwert,
                AlleAntworten.Kilian.gemeinAbhauen
            )
        ),
        -3 to Dialog(
            Text.get(OrtText.KILIAN, "dia_frage_nachquest"),
            listOf(AlleAntworten.Kilian.tipps, AlleAntworten.Kilian.herkunft, AlleAntworten.Kilian.wegQuest)
        ),
        -4 to Dialog(
            Text.get(OrtText.KILIAN, "dia_mission_erfolgreich"),
            listOf(AlleAntworten.Kilian.schwertFinalHöfflich, AlleAntworten.Kilian.schwertFinalUnhöfflich)
        ),
        -5 to Dialog(
            Text.get(OrtText.KILIAN, "dia_lisa_befreundet"),
            listOf(AlleAntworten.Kilian.wegstecken, AlleAntworten.Kilian.eingreifen)
        ),
    ),
    mutableListOf(save.spielSchwert),
    30,
    10,
    60
) {
    var lisaBefreundetQuest: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            2 -> anfangsDialog = -1
            3 -> anfangsDialog = -2
            4, 5, 104, 106 -> save.finalArthur.brutalSwitch()
            102 -> {
                lisaBefreundetQuest = true
                anfangsDialog = -3
            }

            109 -> {
                spielSchwertAbgeben(save)
                return "\n" + Text.get(OrtText.ACTIONS, "abgezogen_take", save.spielSchwert.name)
            }
        }
        return null
    }

    override fun scan(save: Save): String {
        save.finalArthur.brutalSwitch()
        return super.scan(save)
    }

    override fun sterbeEreignis(save: Save): String {
        save.lisaNPC.anfangsDialog = -3
        return super.sterbeEreignis(save)
    }

    fun spielSchwertAbgeben(save: Save) {
        gibItem(save, save.spielSchwert)
        save.kindergarten.herumliegend.remove(save.spielSchwert)
        anfangsDialog = -5
    }
}
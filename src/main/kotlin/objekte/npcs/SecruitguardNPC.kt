package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import objekte.items.SubstanzItem
import study.coco.project_code.Save

class SecruitguardNPC() : NPC(
    Text.get(OrtText.NAME, "wache"),
    125,
    10,
    Text.get(OrtText.WACHE, "scan"),
    Text.get(OrtText.WACHE, "kampfscan"),
    Text.get(OrtText.WACHE, "final"),
    listOf(
        "security",
        "securityguard",
        "wachmann",
        "wachman",
        "sicherheitsman",
        "sicherheitsmann",
        "wache",
        "mann",
        "wächter",
        ""
    ),
    mutableMapOf(
        0 to Dialog(
            Text.get(OrtText.WACHE, "begrüssung"),
            listOf(
                AlleAntworten.Wache.schicksal,
                AlleAntworten.Wache.bugFix,
                AlleAntworten.Wache.bedingungen,
                AlleAntworten.Wache.warumBewachen
            )
        ),
        100 to Dialog(Text.get(OrtText.WACHE, "musst_nicht"), listOf()),
        101 to Dialog(Text.get(OrtText.WACHE, "kein_bug"), listOf()),
        102 to Dialog(Text.get(OrtText.WACHE, "bewachen_antwort"), listOf()),

        1 to Dialog(Text.get(OrtText.WACHE, "nicht_bestechbar"), AlleAntworten.Wache.durchlassenMöglichkeiten),
        103 to Dialog(Text.get(OrtText.WACHE, "kochen_antwort"), listOf()),
        104 to Dialog(Text.get(OrtText.WACHE, "tanz_antwort"), listOf()),
        105 to Dialog(Text.get(OrtText.WACHE, "pflanze_antwort"), listOf()),
        106 to Dialog(Text.get(OrtText.WACHE, "massieren_antwort"), listOf()),
        110 to Dialog(Text.get(OrtText.WACHE, "ideen_geben"), listOf()),

        2 to Dialog(Text.get(OrtText.WACHE, "rätsel_antwort"), AlleAntworten.Wache.alleRätsel),
        107 to Dialog(Text.get(OrtText.WACHE, "rätsel1_antwort"), listOf()),
        108 to Dialog(Text.get(OrtText.WACHE, "rätsel2_antwort"), listOf()),
        109 to Dialog(Text.get(OrtText.WACHE, "rätsel3_antwort"), listOf()),

        -1 to Dialog(
            Text.get(OrtText.WACHE, "begrüssung_gewächs"),
            listOf(AlleAntworten.Wache.abgeben, AlleAntworten.Wache.hinterfragen, AlleAntworten.Wache.holen)
        ),
        111 to Dialog(Text.get(OrtText.WACHE, "pflanze_bekommen"), listOf()),
        112 to Dialog(Text.get(OrtText.WACHE, "warum_hintergehen"), listOf()),
        113 to Dialog(Text.get(OrtText.WACHE, "beeilen"), listOf()),

        -2 to Dialog(
            Text.get(OrtText.WACHE, "begrüssung_high"),
            listOf(AlleAntworten.Wache.gefühlslage, AlleAntworten.Wache.denken, AlleAntworten.Wache.erinnerungen)
        ),
        114 to Dialog(Text.get(OrtText.WACHE, "furchtbar"), listOf()),
        115 to Dialog(Text.get(OrtText.WACHE, "kopf_klar"), listOf()),
        116 to Dialog(Text.get(OrtText.WACHE, "keine_erinnerungen"), listOf()),
    ),
    mutableListOf(),
    35,
    45,
    20
) {
    var kochen: Boolean = false
        private set
    var tanzen: Boolean = false
        private set
    var massieren: Boolean = false
        private set
    var rätsel1: Boolean = false
        private set
    var rätsel2: Boolean = false
        private set
    var rätsel3: Boolean = false
        private set

    var guardBenebelt: Boolean = true
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            103 -> kochen = true
            104 -> tanzen = true
            105 -> anfangsDialog = -1
            106 -> massieren = true
            107 -> rätsel1 = true
            108 -> rätsel2 = true
            109 -> rätsel3 = true
            111 -> {
                save.spieler.inventar.removeAll { it is SubstanzItem }
                save.wachsendePflanze.ausWachsen()
                guardBenebelt = true
                anfangsDialog = -2
            }
        }
        return null
    }

    override fun kampf(save: Save, verb: String): String {
        return if (guardBenebelt) dialogAusgabe(listOf(Text.get(OrtText.WACHE, "wache_angriff_high"))) + Text.get(
            OrtText.NPC,
            "gespräch_ende"
        )
        else super.kampf(save, verb)
    }
}
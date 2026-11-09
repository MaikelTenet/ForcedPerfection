package study.coco.project_code

import bauplaene.*
import bauplaene.OrtText
import study.coco.project_code.handler.gamecontrolls.BeendenHandler
import objekte.items.*
import objekte.raume.*
import objekte.npcs.*

class Save {
    var nochLaufen: Boolean = false
    var ideeZuBeenden: Boolean = false
    var memeBeenden: Boolean = false
    val spieler: Spieler = Spieler(mutableListOf())

    private var firstItem: Boolean = false
    val stock: StockItem = StockItem()
    val häufchen: Item = Item(
        Text.get(OrtText.NAME, "haufen"),
        Text.get(OrtText.ITEM, "take_haufen"),
        listOf("häufchen", "haufen"),
        Text.get(OrtText.ITEM, "use_haufen")
    )
    val hund: HundItem = HundItem()
    val scanner: Item = Item(
        Text.get(OrtText.NAME, "scanner"),
        Text.get(OrtText.ITEM, "take_scanner"),
        listOf("scanner", "scan", "scaner"),
        Text.get(OrtText.ITEM, "use_scanner")
    )
    val hundNPC: HundNPC = HundNPC()
    val arthurNPC: ArthurNPC = ArthurNPC(this)
    val omaNPC: OmaNPC = OmaNPC()
    val park: ParkRaum = ParkRaum(this)

    val spielSchwert: SpielschwertItem = SpielschwertItem()

    val kilianNPC: KilianNPC = KilianNPC(this)
    val lisaNPC: LisaNPC = LisaNPC()
    val kindergarten: KindergartenRaum = KindergartenRaum(this)

    val wachsendePflanze: SubstanzWachsend = SubstanzWachsend()
    val glascontainerItem: StuckItem = StuckItem(
        Text.get(OrtText.NAME, "glas"),
        Text.get(OrtText.ITEM, "take_glas"),
        listOf("tresor", "glascontainer", "glaskasten", "glas", "container", "kasten", "truhe"),
    )
    val kaputterChip: ChipItem = ChipItem(
        Text.get(OrtText.NAME, "chip1"),
        Text.get(OrtText.ITEM, "take_chip"),
        listOf("kaputter chip", "kaputter computerchip", "kaputter computer chip", "kaputter computer-chip"),
        Text.get(OrtText.ITEM, "use_chip")
    )
    val knopf: KnopfItem = KnopfItem()
    val markusesBrief: BriefItem = BriefItem()
    val markus: MarkusNPC = MarkusNPC(this)
    val farm: FarmRaum = FarmRaum(this)
    val pipe: PipeItem = PipeItem()
    val kabel: KabelItem = KabelItem()
    val ranken: RankenItem = RankenItem(this)
    val sandra: SandraNPC = SandraNPC(this)
    val reparierterChip: ChipItem = ChipItem(
        Text.get(OrtText.NAME, "chip_heile"),
        Text.get(OrtText.ITEM, "take_reparierter_chip"),
        listOf(
            "reparierter chip",
            "reparierter computerchip",
            "reparierter computer chip",
            "reparierter computer-chip"
        ),
        Text.get(OrtText.ITEM, "use_chip_heile"),
        true
    )
    val baustelle: BaustelleRaum = BaustelleRaum(this)

    val substanz: SubstanzKonves = SubstanzKonves()
    val kanone: WaffeItem = WaffeItem(
        Text.get(OrtText.NAME, "kanone"),
        "",
        listOf("kanone", "waffe", "gewähr", "canon", "pistole", "pistol"),
        12
    )
    val box: StuckItem = StuckItem(
        Text.get(OrtText.NAME, "box"),
        Text.get(OrtText.ITEM, "take_box"),
        listOf("box", "polizei box", "aufbewahrungsbox", "black box", "blackbox", "schwarze box", "schwarzebox")
    )
    val polizist: PolizistNPC = PolizistNPC(this)
    val polizeiStation: PolizeiStationRaum = PolizeiStationRaum(this)

    val axt: WaffeItem = WaffeItem(
        Text.get(OrtText.NAME, "axt"),
        "",
        listOf("axt"),
        15
    )
    val medikamente: MedikamenteItem = MedikamenteItem(Text.get(OrtText.NAME, "medikamente"), "")
    val polizeikleidung: polizeikleidungItem = polizeikleidungItem()
    val gold: GoldItem = GoldItem()
    val jogurt: JogurtItem = JogurtItem()
    val gabel: WaffeItem = WaffeItem(
        Text.get(OrtText.NAME, "gabel"),
        "",
        listOf("gabel", "fork"),
        5
    )
    val bett: BettItem = BettItem()
    val tageBuch: TageBuchItem = TageBuchItem()
    val chipPläne: Item = Item(
        Text.get(OrtText.NAME, "plan"),
        "",
        listOf(
            "plan",
            "bauplan",
            "baupläne",
            "plans",
            "chip-pläne",
            "chip-plan",
            "usb",
            "usbstick",
            "usb stick",
            "usb-stick",
            "usb-stick mit chip-plänen",
            "programmcode"
        ),
        Text.get(OrtText.ITEM, "use_plan"),
    )
    val computer: ComputerItem = ComputerItem()
    val zuhause: ZuhauseRaum = ZuhauseRaum(this)

    val shopkeaper: ShopKeeperNPC = ShopKeeperNPC(this)
    val notiz: Item = Item(
        Text.get(OrtText.NAME, "notiz"),
        "",
        listOf("notiz", "zettel", "note", "geheime notiz", "tagebuch passwort"),
        Text.get(OrtText.ITEM, "use_notiz", tageBuch.passwort),
    )
    val magier: Magier = Magier(this)
    val hauptstraße: HauptstraßeRaum = HauptstraßeRaum(this)

    val strongheal: MedikamenteItem = MedikamenteItem(Text.get(OrtText.NAME, "medikamente2"), "", spieler.startLeben)
    val securityguard: SecruitguardNPC = SecruitguardNPC()
    val eingang: EingangRaum = EingangRaum(this)

    val finalArthur: FinalArthurNPC = FinalArthurNPC()
    val server: StuckItem = StuckItem(
        Text.get(OrtText.NAME, "server"),
        Text.get(OrtText.ITEM, "take_server"),
        listOf("server"),
    )
    val akte: AktenItem = AktenItem()
    val finalChip: FinalChipItem = FinalChipItem()
    val labor: LaborRaum = LaborRaum(this)

    val allePersonen: List<NPC> =
        listOf(
            hundNPC,
            arthurNPC,
            omaNPC,
            kilianNPC,
            lisaNPC,
            markus,
            securityguard,
            shopkeaper,
            magier,
            polizist,
            sandra,
            finalArthur
        )
    val alleRäume: List<Raum> =
        listOf(park, kindergarten, farm, polizeiStation, baustelle, hauptstraße, zuhause, eingang, labor)

    fun welcherRaum(): Raum {
        for (r in alleRäume) {
            if (r.xKoordinate == spieler.xVariable && r.yKoordinate == spieler.yVariable) return r
        }
        //failsafe
        spieler.xVariable = 1
        spieler.yVariable = -1
        return zuhause
    }

    fun zwischenEvents(verb: String, ding: String?): String? {
        for (s in SubstanzItem.alleSubstanzen) {
            if (s.benebeltWert != 0) return s.use(this)
        }
        if (tageBuch.anfangenLesen) return tageBuch.lesen(verb, false, this)
        //if (markusesBrief.lesenWert != 0) return markusesBrief.lesen(this)
        if (verb == "") {
            return Text.get(OrtText.ERRORS, "nur_enter")
        }

        if (magier.currentGambling) return magier.gambling(this, verb)

        for (d in allePersonen) {
            if (d.activeFight) return d.kampf(this, verb)
            if (d.activDialog) {
                /*if (verb == "stop" || verb == "exit" || verb == "leave" || verb == "verlassen") {
                    if (d != finalArthur) {
                        d.activDialog = false
                        return Text.get(OrtText.NPC, "dialog_weg_rennen", d.name)
                    }
                }
                */
                try {
                    val neuVerb: Int = verb.toInt()
                    return d.unterhaltung(this, neuVerb)
                } catch (e: Exception) {
                    return Text.get(OrtText.ERRORS, "falsche_antwort")
                }
            }
        }

        if (ideeZuBeenden) return BeendenHandler().handeln(verb, ding, this)

        if (finalChip.finalCheck) return finalChip.finalStart(verb, this)
        if (markusesBrief.überlegenZuLesen) return markusesBrief.lesenCheck(verb, this)
        if (akte.akteOpen) return akte.welcheAkte(verb)

        return null
    }

    fun failWalk(richtung: Int): String? {
        if (welcherRaum() == baustelle && richtung == 2) {
            return Text.get(OrtText.ERRORS, "wandlauf")
        }

        if (welcherRaum() == labor && richtung != 3) {
            return Text.get(OrtText.ERRORS, "vilian_ausgang")
        }

        if (welcherRaum() == eingang && richtung == 1 && eingang.interaktionen.any { it === securityguard && it.lebendig } && !securityguard.guardBenebelt) {
            return Text.get(OrtText.ERRORS, "strong_guard")
        }
        return null
    }

    fun firstItemCheck(): String {
        return if (!firstItem) {
            firstItem = true
            "\n" + Text.get(OrtText.ITEM, "use_first")
        } else ""
    }
}
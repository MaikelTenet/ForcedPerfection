package objekte.npcs

import bauplaene.NPC.Antworten
import bauplaene.OrtText
import objekte.items.SubstanzItem

object AlleAntworten {
    object Arthur {
        val anstarren = Antworten(Text.get(OrtText.ARTHUR, "antwort1"), 1)
        val entschuldigen = Antworten(Text.get(OrtText.ARTHUR, "antwort2"), 100)
        val konfront = Antworten(Text.get(OrtText.ARTHUR, "antwort3"),2)
        val befreunden = Antworten(Text.get(OrtText.ARTHUR, "antwort4"), 101)
        val verwirrt = Antworten(Text.get(OrtText.ARTHUR, "antwort5"),2)
        val erIstSeltsam = Antworten(Text.get(OrtText.ARTHUR, "antwort6"),3)
        val warumLange = Antworten(Text.get(OrtText.ARTHUR, "antwort7"),4)
        val freierWille = Antworten(Text.get(OrtText.ARTHUR, "freien_willen_hiterfragen"), 102)
        val jederFreierWille = Antworten(Text.get(OrtText.ARTHUR, "jeder_freier_wille"), 102)

        val infoMarkus = Antworten(Text.get(OrtText.ARTHUR, "antwort12"),7) { save -> save.markus.scannerInfo && save.markus.arthurInfo }
        val infoOma = Antworten(Text.get(OrtText.ARTHUR, "oma_erzählt"),12) { save -> save.omaNPC.omaErzähltScanner }
        val sandraInfo = Antworten(Text.get(OrtText.ARTHUR, "sandra_erzählt"),15) { save -> save.sandra.scannerSichtung }
        val stalkerScanner = Antworten(Text.get(OrtText.ARTHUR, "antwort13"),8)
        val kampf = Antworten(Text.get(OrtText.ARTHUR, "antwort14"),10) { save -> !save.arthurNPC.kampfAbgelehnt}
        val sideQuest = Antworten(Text.get(OrtText.ARTHUR, "antwort15"),11) { save -> !save.arthurNPC.questAbgelehnt}
        val schenken = Antworten(Text.get(OrtText.ARTHUR, "antwort16"),110)
        val massieren = Antworten(Text.get(OrtText.ARTHUR, "massieren"),14) { save -> !save.arthurNPC.massierenAbgelehnt }
        val best_buddies = Antworten(Text.get(OrtText.ARTHUR, "best_buddies"),13) { save -> !save.arthurNPC.buddyAblehnen }
        val toThePoint = Antworten(Text.get(OrtText.ARTHUR, "antwort17"),9)
        val aufgeben = Antworten(Text.get(OrtText.ARTHUR, "antwort18"),109)
        val erIstGemein = Antworten(Text.get(OrtText.ARTHUR, "antwort19"),2)
        val planLos = Antworten(Text.get(OrtText.ARTHUR, "antwort20"),103)
        val nerven = Antworten(Text.get(OrtText.ARTHUR, "antwort21"),104)
        val scannerHinterfragen = Antworten(Text.get(OrtText.ARTHUR, "antwort22"),111)
        val scannerAufgeben = Antworten(Text.get(OrtText.ARTHUR, "antwort23"),112)
        val markusBrief = Antworten(Text.get(OrtText.ARTHUR, "antwort24"),113, { save -> save.spieler.inventar.contains(save.markusesBrief) && !save.arthurNPC.markusAbgelehnt })
        val omaTod = Antworten(Text.get(OrtText.ARTHUR, "antwort25"),105) { save -> !save.omaNPC.lebendig }
        val chipHinterfragen = Antworten(Text.get(OrtText.ARTHUR, "antwort26"),108) { save -> save.markus.chipInfo && save.markus.arthurInfo }
        val scannerNehmen1 = Antworten(Text.get(OrtText.ARTHUR, "antwort27"), 107) { save -> save.arthurNPC.scannerKnowlege }
        val scannerNehmen2 = Antworten(Text.get(OrtText.ARTHUR, "antwort27"), 6) { save -> save.arthurNPC.scannerKnowlege }
        val scannerAntworten: List<Antworten> = listOf(kampf, sideQuest, schenken, massieren, best_buddies)

        val freierWilleDiscovery: List<Antworten> = listOf(freierWille, jederFreierWille)
    }

    object Hund {
        val begruss = Antworten(Text.get(OrtText.HUND, "hund_antwort1"), 1)
        val tschau = Antworten(Text.get(OrtText.HUND, "hund_antwort2"), 100)
        val bitten = Antworten(Text.get(OrtText.HUND, "hund_antwort3"), 2) { save -> save.welcherRaum().herumliegend.contains(save.stock)}
        val beteln = Antworten(Text.get(OrtText.HUND, "hund_antwort4"), 3)
        val erpressen = Antworten(Text.get(OrtText.HUND, "hund_antwort5"), 2)
        val tretten = Antworten(Text.get(OrtText.HUND, "tretten"), 4)
        val selberSchuld = Antworten(Text.get(OrtText.HUND, "trit_blame"), 5)
        val gutMachen1 = Antworten(Text.get(OrtText.HUND, "gut_machen1"), 6)
        val gutMachen2 = Antworten("${Text.get(OrtText.HUND, "gut_machen2")} ${Text.get(OrtText.HUND, "gut_machen1")}", 6)
        val böserJunge = Antworten(Text.get(OrtText.HUND, "böser_hund"), 100)
        val frieden = Antworten(Text.get(OrtText.HUND, "frieden"), 101) { save -> !save.hundNPC.keinFrieden }
        val stockchenWärfen1 = Antworten(Text.get(OrtText.HUND, "stock_werfen"), 102) { save -> save.spieler.inventar.contains(save.stock) || save.welcherRaum().herumliegend.contains(save.stock) || save.spieler.waffeAusgerüstet == save.stock }
        val date = Antworten(Text.get(OrtText.HUND, "date"), 103) { save -> !save.hundNPC.keinDate }
        val katze = Antworten(Text.get(OrtText.HUND, "katze"), 106) { save -> !save.hundNPC.keinKatze }
        val guterBesitzer = Antworten(Text.get(OrtText.HUND, "gut_besitzer"), 100)
        val trick = Antworten(Text.get(OrtText.HUND, "trick"), 104)
        val angriff = Antworten(Text.get(OrtText.HUND, "angriff"), 104)
        val neuesBesitzer = Antworten(Text.get(OrtText.HUND, "neuer_besitzer"), 100)
        val stockchenWärfen2 = Antworten(Text.get(OrtText.HUND, "stock_werfen"), 105) { save -> save.spieler.inventar.contains(save.stock) || save.welcherRaum().herumliegend.contains(save.stock) || save.spieler.waffeAusgerüstet == save.stock }
        val verzeihenBedingungen: List<Antworten> = listOf(frieden, stockchenWärfen1, date, katze)
    }

    object Oma {
        val hundFan = Antworten(Text.get(OrtText.OMA, "antwort1"), 1)
        val hundHater = Antworten(Text.get(OrtText.OMA, "antwort2"), 2)
        val guteWelt = Antworten(Text.get(OrtText.OMA, "antwort3"), 100)
        val schlechteWelt = Antworten(Text.get(OrtText.OMA, "antwort4"), 4)
        val jaTraining = Antworten(Text.get(OrtText.OMA, "antwort5"), 101)
        val neinTraining = Antworten(Text.get(OrtText.OMA, "antwort6"), 102)
        val hundWollte = Antworten(Text.get(OrtText.OMA, "antwort7"), 3)
        val warWerAnders = Antworten(Text.get(OrtText.OMA, "antwort8"), 2)
        val drohung1 = Antworten(Text.get(OrtText.OMA, "antwort9"), 104)
        val drohung2 = Antworten(Text.get(OrtText.OMA, "antwort10"), 104)
        val drohung3 = Antworten(Text.get(OrtText.OMA, "antwort11"), 104)
        val drohung4 = Antworten(Text.get(OrtText.OMA, "antwort12"), 104)
        val hilfeFragen = Antworten(Text.get(OrtText.OMA, "antwort13"), 103) { save -> save.omaNPC.beibringenVorschlag }
    }

    object Markus {
        val kennDich = Antworten(Text.get(OrtText.MARKUS, "antwort1"), 1)
        val kennDichNicht = Antworten(Text.get(OrtText.MARKUS, "antwort2"), 2)
        val chipFrage = Antworten(Text.get(OrtText.MARKUS, "chipFrage"), 5)  { save -> !save.markus.questAnnehmen }
        val wasScanner = Antworten(Text.get(OrtText.MARKUS, "scannerFrage"), 3)
        val keinScannerHaben = Antworten(Text.get(OrtText.MARKUS, "noScanner"), 4)
        val nichtArthur = Antworten(Text.get(OrtText.MARKUS, "verwechseln"), 100)
        val nichtArthur2 = Antworten(Text.get(OrtText.MARKUS, "verwechseln"), 106)
        val werBinIch = Antworten(Text.get(OrtText.MARKUS, "wer_ich"), 6)
        val werBinIch2 = Antworten(Text.get(OrtText.MARKUS, "wer_ich"), 105)
        val questAnnehmen = Antworten(Text.get(OrtText.MARKUS, "chip_bekommen"), 7)
        val anlügen = Antworten(Text.get(OrtText.MARKUS, "erfinder_des_Chips"), 7) { save -> !save.markus.questAnnehmen }
        val annehmen = Antworten(Text.get(OrtText.MARKUS, "annehmen"), 101)
        val ablehnen = Antworten(Text.get(OrtText.MARKUS, "ablehnen"), 102)
        val besorgen = Antworten(Text.get(OrtText.MARKUS, "besorgen"), 103)
        val abgeben = Antworten(Text.get(OrtText.MARKUS, "abgeben"), 104) { save -> save.spieler.inventar.any { it is SubstanzItem } }
        val schönheit = Antworten(Text.get(OrtText.MARKUS, "op"), 107)
        val codeHerkunft = Antworten(Text.get(OrtText.MARKUS, "wo_code"), 108) { save -> save.sandra.questAnnehmen && !save.computer.gotItem }

    }

    object Kilian {
        val freundWerden = Antworten(Text.get(OrtText.KILIAN, "freund_werden"), 2)
        val feindWerden = Antworten(Text.get(OrtText.KILIAN, "feind_werden"), 3)
        val direktSchwerFragen = Antworten(Text.get(OrtText.KILIAN, "schwert_unhöfflich"), 1)
        val nettwhyNoFriend = Antworten(Text.get(OrtText.KILIAN, "nofriedn_frage"), 4)
        val gemeinwhyNoFriend = Antworten(Text.get(OrtText.KILIAN, "nofriedn_frage"), 5)
        val nettAbhauen = Antworten(Text.get(OrtText.KILIAN, "spiel_spielen"), 100)
        val gemeinAbhauen = Antworten(Text.get(OrtText.KILIAN, "gehen"), 101)
        val nettSchwert = Antworten(Text.get(OrtText.KILIAN, "schwer_nett"), 6)
        val gemeinSchwert = Antworten(Text.get(OrtText.KILIAN, "schwert_unhöfflich"), 6)
        val hilfeAnbieten = Antworten(Text.get(OrtText.KILIAN, "mitleid"), 7)
        val beleidigen = Antworten(Text.get(OrtText.KILIAN, "auslachen"), 6)
        val annehmen = Antworten(Text.get(OrtText.KILIAN, "annehmen"), 102)
        val ablehnen = Antworten(Text.get(OrtText.KILIAN, "ablehnen"), 103)
        val tipps = Antworten(Text.get(OrtText.KILIAN, "hilfe"), 104)
        val herkunft = Antworten(Text.get(OrtText.KILIAN, "schwert_herkunft"), 8)
        val wegQuest = Antworten(Text.get(OrtText.KILIAN, "nicht_fertig"), 105)
        val schön = Antworten(Text.get(OrtText.KILIAN, "schön"), 106)
        val langweilig = Antworten(Text.get(OrtText.KILIAN, "langweilig"), 107)
        val omaTod = Antworten(Text.get(OrtText.KILIAN, "tod"), 108) { save -> !save.omaNPC.lebendig }
        val schwertFinalHöfflich = Antworten(Text.get(OrtText.KILIAN, "schwert_höfflich_final"), 109)
        val schwertFinalUnhöfflich = Antworten(Text.get(OrtText.KILIAN, "schwert_unhöfflich"), 109)
        val wegstecken = Antworten(Text.get(OrtText.KILIAN, "männlich"), 110)
        val eingreifen = Antworten(Text.get(OrtText.KILIAN, "etwas_gegen_sagen"), 111)
    }

    object Lisa {
        val duWirstGros = Antworten(Text.get(OrtText.LISA, "anfang_kompliment"), 100)
        val ichBinCool = Antworten(Text.get(OrtText.LISA, "ich_bin_groß"), 1)
        val alsKleinBeleidigen = Antworten(Text.get(OrtText.LISA, "anfang_abhauen"), 101)
        val anfangKilian = Antworten(Text.get(OrtText.LISA, "kilian_gut_reden"), 102) { save -> save.kilianNPC.lisaBefreundetQuest }
        val seiStark = Antworten(Text.get(OrtText.LISA, "willenskraft"), 101)
        val gehWachsen = Antworten(Text.get(OrtText.LISA, "geh_wachsen"), 2)
        val tretten = Antworten(Text.get(OrtText.LISA, "kian_tretten"), 104)
        val alter = Antworten(Text.get(OrtText.LISA, "zu_alt"), 103)
        val freundschaftAnbieten = Antworten(Text.get(OrtText.LISA, "kian_freundschaft_anbieten"), 3) { save -> save.kilianNPC.lisaBefreundetQuest }
        val gutReden = Antworten(Text.get(OrtText.LISA, "er_cool1"), 4)
        val switchup = Antworten(Text.get(OrtText.LISA, "switchup"), 105)
        val aufgeben = Antworten(Text.get(OrtText.LISA, "witze_machen"), 106)
        val gutReden2 = Antworten(Text.get(OrtText.LISA, "cool2"), 5)
        val gutReden3 = Antworten(Text.get(OrtText.LISA, "cool3"), 6)
        val gutReden4 = Antworten(Text.get(OrtText.LISA, "cool4"), 4)
        val whymobbing = Antworten(Text.get(OrtText.LISA, "whymobbing"), 107)
        val why_klein = Antworten(Text.get(OrtText.LISA, "why_klein"), 108)
        val why_kind = Antworten(Text.get(OrtText.LISA, "why_kind"), 109)
        val gutFürDich = Antworten(Text.get(OrtText.LISA, "freuen"), 110)
        val fiesTipps = Antworten(Text.get(OrtText.LISA, "beine"), 111)
        val drohen = Antworten(Text.get(OrtText.LISA, "drohen"), 112)
        val anlügen = Antworten(Text.get(OrtText.LISA, "war_ich_nicht"), 113)
    }

    object Shopper {
        val kaufen = Antworten(Text.get(OrtText.SHOPPER, "will_kaufen"), 1)
        val nichtKaufen = Antworten(Text.get(OrtText.SHOPPER, "nicht_kaufen"), 100)
        val erklärBitte = Antworten(Text.get(OrtText.SHOPPER, "was_meinen_du"), 2)
        val sortiment = Antworten(Text.get(OrtText.SHOPPER, "sortiment"), 3)
        val axt = Antworten(Text.get(OrtText.NAME, "axt"), 4) { save -> !save.shopkeaper.axtBekommen }
        val medikamente = Antworten(Text.get(OrtText.NAME, "medikamente"), 5)
        val polizeikleidung = Antworten(Text.get(OrtText.NAME, "verkleidung"), 6) { save -> !save.shopkeaper.verkleidungBekommen }
        val kanoneHaben = Antworten(Text.get(OrtText.SHOPPER, "besitzen"), 101) { save -> save.spieler.inventar.contains(save.kanone) || save.spieler.waffeAusgerüstet == save.kanone }
        val goldHaben = Antworten(Text.get(OrtText.SHOPPER, "besitzen"), 102) { save -> save.spieler.inventar.contains(save.gold) }
        val hundHaben = Antworten(Text.get(OrtText.SHOPPER, "besitzen"), 103) { save -> save.spieler.inventar.contains(save.hund) || save.welcherRaum().herumliegend.contains(save.hund) }
        val besorgen = Antworten(Text.get(OrtText.SHOPPER, "besorgen"), 100)
        val sortimentMöglichkeiten: List<Antworten> = listOf(axt, medikamente, polizeikleidung, nichtKaufen)
    }

    object Magier {
        val annehmen = Antworten(Text.get(OrtText.MAGIER, "magier_annhmen"), 1)
        val ablehnen = Antworten(Text.get(OrtText.MAGIER, "magier_ablehnen"), 100)
        val regelnNachfrage = Antworten(Text.get(OrtText.MAGIER, "welche_regeln"), 2)
        val spielNachfrage = Antworten(Text.get(OrtText.MAGIER, "welches_spiel"), 3) { save -> !save.magier.glorboErklärt }
        val belohnung = Antworten(Text.get(OrtText.MAGIER, "win_stake"), 4)
        val starten = Antworten(Text.get(OrtText.MAGIER, "starten"), 5) { save -> save.magier.glorboErklärt }
        val nichtStarten = Antworten(Text.get(OrtText.MAGIER, "doch_nicht"), 101)
        val nochEinKampf = Antworten(Text.get(OrtText.MAGIER, "filler"), 5)
        val aufhören = Antworten(Text.get(OrtText.MAGIER, "filler"), 102)
        val gesprächStarten = Antworten(Text.get(OrtText.MAGIER, "reden_über_dich"), 6)
        val seltsam = Antworten(Text.get(OrtText.MAGIER, "seltsam"), 103)
        val ripoff = Antworten(Text.get(OrtText.MAGIER, "ripoff"), 104)
        val freunde = Antworten(Text.get(OrtText.MAGIER, "freunde"), 105)
        val passwort = Antworten(Text.get(OrtText.MAGIER, "passwort"), 7) { save -> save.sandra.tagebuchPasswortInfo && !save.magier.passwortBekommen }

        val startOption = listOf<Antworten>(starten, nichtStarten)
        val persönlicheFragen = listOf<Antworten>(seltsam, ripoff, freunde)
    }

    object Polizist {
        val abhauen = Antworten(Text.get(OrtText.POLIZIST, "gehen"), 100)
        val bewerben = Antworten(Text.get(OrtText.POLIZIST, "bewerben"), 101)
        val sündenGestehen = Antworten(Text.get(OrtText.POLIZIST, "verbrecher"), 1)
        val keineSünden = Antworten(Text.get(OrtText.POLIZIST, "sünden_abbrechen"), 102)
        val hundGetretten = Antworten(Text.get(OrtText.POLIZIST, "hund"), 103) { save -> save.stock.hundGetretten }
        val koGeschlagen = Antworten(Text.get(OrtText.POLIZIST, "ko"), 104) { save -> save.allePersonen.any { !it.lebendig } }
        val falscheVerkleidung = Antworten(Text.get(OrtText.POLIZIST, "lügner"), 105) { save -> save.spieler.verkleidet }
        val weedGeklaut = Antworten(Text.get(OrtText.POLIZIST, "weed_geklaut"), 106) { save -> !save.welcherRaum().herumliegend.contains(save.substanz) }
        val dichGeschlagen = Antworten(Text.get(OrtText.POLIZIST, "dich_geschlagen"), 107) { save -> save.polizist.leben != save.polizist.startLeben }
        val warumDuHier = Antworten(Text.get(OrtText.POLIZIST, "sinn"), 108)
        val nachWeedFragen = Antworten(Text.get(OrtText.POLIZIST, "weed_bekommen"), 109) { save -> save.welcherRaum().herumliegend.contains(save.substanz) }
        val nachWeedFragenPolize = Antworten(Text.get(OrtText.POLIZIST, "weed_bekommen"), 110) { save -> save.welcherRaum().herumliegend.contains(save.substanz) }
        val pistoleFragen = Antworten(Text.get(OrtText.POLIZIST, "kanone_wollen"), 2) { save -> !save.polizist.mitleidCheck }
        val selbstverteidigung = Antworten(Text.get(OrtText.POLIZIST, "selbstverteidigung"), 111) { save -> !save.polizist.selbstverteidigungCheck }
        val gefeuert = Antworten(Text.get(OrtText.POLIZIST, "gefeuert"), 112) { save -> !save.polizist.gefeuertCheck }
        val erschießen = Antworten(Text.get(OrtText.POLIZIST, "erschießen"), 113) { save -> !save.polizist.erschießenCheck }
        val gesetz = Antworten(Text.get(OrtText.POLIZIST, "gesetz"), 114) { save -> !save.polizist.gesetzCheck }
        val mitleid = Antworten(Text.get(OrtText.POLIZIST, "mitleid"), 115)
        val vierte_wand = Antworten(Text.get(OrtText.POLIZIST, "vierte_wand"), 116) { save -> !save.polizist.viertewandCheck }
        val gaslight = Antworten(Text.get(OrtText.POLIZIST, "gaslight"), 117) { save -> !save.polizist.gaslightCheck }
        val reality = Antworten(Text.get(OrtText.POLIZIST, "reality"), 118) { save -> !save.polizist.realityCheck }

        val kanoneGebenGründen: List<Antworten> = listOf(selbstverteidigung, gefeuert, erschießen, gesetz, mitleid, vierte_wand, gaslight, reality)
        val verbrechenBegangen: List<Antworten> = listOf(weedGeklaut, falscheVerkleidung, dichGeschlagen, hundGetretten, koGeschlagen, keineSünden)
    }

    object Wache {
        val schicksal = Antworten(Text.get(OrtText.WACHE, "ich_muss"), 100)
        val bugFix = Antworten(Text.get(OrtText.WACHE, "berechtigung"), 101)
        val warumBewachen = Antworten(Text.get(OrtText.WACHE, "warum_hier"), 102)
        val bedingungen = Antworten(Text.get(OrtText.WACHE, "was_wollen"), 1)
        val kochen = Antworten(Text.get(OrtText.WACHE, "kochen"), 103) { save -> !save.securityguard.kochen }
        val tanzduell = Antworten(Text.get(OrtText.WACHE, "tanzduell"), 104) { save -> !save.securityguard.tanzen }
        val rätsel = Antworten(Text.get(OrtText.WACHE, "rätsel"), 2) { save -> !save.securityguard.rätsel1 || !save.securityguard.rätsel2 || !save.securityguard.rätsel3 }
        val pflanze = Antworten(Text.get(OrtText.WACHE, "pflanze"), 105) { save -> save.markus.questAnnehmen || save.spieler.inventar.any { it is SubstanzItem } }
        val massieren = Antworten(Text.get(OrtText.WACHE, "massieren"), 106) { save -> !save.securityguard.massieren }
        val rätsel1 = Antworten(Text.get(OrtText.WACHE, "rätsel1"), 107) { save -> !save.securityguard.rätsel1 }
        val rästel2 = Antworten(Text.get(OrtText.WACHE, "rästel2"), 108) { save -> !save.securityguard.rätsel2 }
        val rätsel3 = Antworten(Text.get(OrtText.WACHE, "rätsel3"), 109) { save -> !save.securityguard.rätsel3 }
        val ideen_leer = Antworten(Text.get(OrtText.WACHE, "ideen_leer"), 110) { save -> save.securityguard.kochen && save.securityguard.tanzen && save.securityguard.rätsel1 && save.securityguard.rätsel2 && save.securityguard.rätsel3 && save.securityguard.massieren && !save.markus.questAnnehmen && !save.spieler.inventar.any { it is SubstanzItem } }

        val abgeben = Antworten(Text.get(OrtText.WACHE, "pflanze_besitz"), 111) { save -> save.spieler.inventar.any { it is SubstanzItem } }
        val hinterfragen = Antworten(Text.get(OrtText.WACHE, "hinterfragen"), 112)
        val holen = Antworten(Text.get(OrtText.WACHE, "holen"), 113)
        val gefühlslage = Antworten(Text.get(OrtText.WACHE, "fragen"), 114)
        val denken = Antworten(Text.get(OrtText.WACHE, "denken"), 115)
        val erinnerungen = Antworten(Text.get(OrtText.WACHE, "erinnerungen"), 116)

        val alleRätsel: List<Antworten> = listOf(rätsel1, rästel2, rätsel3)
        val durchlassenMöglichkeiten: List<Antworten> = listOf(kochen, tanzduell, rätsel, pflanze, massieren, ideen_leer)
    }

    object Sandra {
        val pipeHinterfragen = Antworten(Text.get(OrtText.SANDRA, "hinterfragen"), 100)
        val wasBauen = Antworten(Text.get(OrtText.SANDRA, "was_bauen"), 1)
        val helfen = Antworten(Text.get(OrtText.SANDRA, "helfen"), 2)
        val stess = Antworten(Text.get(OrtText.SANDRA, "stess"), 101)
        val biching = Antworten(Text.get(OrtText.SANDRA, "beschwären"), 102)
        val teammates = Antworten(Text.get(OrtText.SANDRA, "teammates"), 3)
        var wasSeltsam = Antworten(Text.get(OrtText.SANDRA, "was_seltsam"), 4) { save -> !save.sandra.seltsamBeantwortet }
        var freierWille = Antworten(Text.get(OrtText.SANDRA, "freier_wille"), 5) { save -> !save.sandra.freierWilleBeantworten && save.arthurNPC.tutorialAbgeschlossen }
        val übrigeErinnerungen = Antworten(Text.get(OrtText.SANDRA, "warum_erinnerung_seltsam"), 6)
        var beschäftigungen = Antworten(Text.get(OrtText.SANDRA, "beschäftigung"), 7) { save -> !save.sandra.bescjäftigungBeantworten }
        val bauenAnfrage = Antworten(Text.get(OrtText.SANDRA, "bauen"), 8)
        val bauenNachfrage = Antworten(Text.get(OrtText.SANDRA, "willst_du_bauen"), 9)
        val keinBauen = Antworten(Text.get(OrtText.SANDRA, "nichts_bauen"), 103)
        var wasSeltsam2 = Antworten(Text.get(OrtText.SANDRA, "was_seltsam"), 104) { save -> !save.sandra.seltsamBeantwortet }
        var freierWille2 = Antworten(Text.get(OrtText.SANDRA, "freier_wille"), 105) { save -> !save.sandra.freierWilleBeantworten && save.arthurNPC.tutorialAbgeschlossen }
        var beschäftigungen2 = Antworten(Text.get(OrtText.SANDRA, "beschäftigung"), 106) { save -> !save.sandra.bescjäftigungBeantworten }

        val tagebuch = Antworten(Text.get(OrtText.SANDRA, "buch_öffnen"), 107) { save -> !save.sandra.tagebuchPasswortInfo && save.spieler.inventar.contains(save.tageBuch) }
        val chipAbgabe = Antworten(Text.get(OrtText.SANDRA, "chip_geben"), 10) { save -> save.spieler.inventar.contains(save.kaputterChip) }
        val queastAnnehmen = Antworten(Text.get(OrtText.SANDRA, "annehmen"), 108)
        val queastAblehnen = Antworten(Text.get(OrtText.SANDRA, "ablehnen"), 109)
        val abgeben = Antworten(Text.get(OrtText.SANDRA, "alles_abgeben"), 11) { save -> save.spieler.inventar.contains(save.chipPläne) && save.spieler.inventar.contains(save.scanner) }
        val scannerOrigin = Antworten(Text.get(OrtText.SANDRA, "woher_scanner"), 110) { save -> !save.arthurNPC.scannerAbgegeben }
        val scannerprove = Antworten(Text.get(OrtText.SANDRA, "scanner_zeigen"), 111) { save -> save.spieler.inventar.contains(save.scanner) }
        val codeOrigin = Antworten(Text.get(OrtText.SANDRA, "woher_code"), 112) { save -> !save.computer.gotItem }
        val codeProve = Antworten(Text.get(OrtText.SANDRA, "code_zeigen"), 113) { save -> save.spieler.inventar.contains(save.chipPläne) }
        val chipInfos = Antworten(Text.get(OrtText.SANDRA, "chip_infos"), 12)
        val fehlfunktion = Antworten(Text.get(OrtText.SANDRA, "fehlfunktion"), 114)
        val welcheAntworten = Antworten(Text.get(OrtText.SANDRA, "wie_antworten"), 115)
        val warten = Antworten(Text.get(OrtText.SANDRA, "warten"), 116)
        val chipOrigin = Antworten(Text.get(OrtText.SANDRA, "chip_origin"), 117)
        val verarbeitung = Antworten(Text.get(OrtText.SANDRA, "verarbeitung"), 118)
        val austauschOrt = Antworten(Text.get(OrtText.SANDRA, "wo_austauschen"), 119)

        val anfangsAntworten: List<Antworten> = listOf(pipeHinterfragen, wasBauen, helfen, biching)
        val überforderungsAntworten: List<Antworten> = listOf(wasSeltsam, freierWille, übrigeErinnerungen, beschäftigungen)
        val bauMöglichkeiten: List<Antworten> = listOf(chipAbgabe, tagebuch, keinBauen)
        val überforderungNachfragen: List<Antworten> = listOf(wasSeltsam2, freierWille2, beschäftigungen2)
    }

    object FinalArthur {
        val hinterfragen = Antworten(Text.get(OrtText.FINALARTHUR, "hinterfragen"), 1)
        val bitteHilfe = Antworten(Text.get(OrtText.FINALARTHUR, "hilfe_fragen"), 2)
        val ja = Antworten(Text.get(OrtText.FINALARTHUR, "ja"), 3)
        val nein = Antworten(Text.get(OrtText.FINALARTHUR, "nein"), 4)
        val hören = Antworten(Text.get(OrtText.FINALARTHUR, "erzählen"), 6)
        val nichtHören = Antworten(Text.get(OrtText.FINALARTHUR, "nicht_erzählen"), 5)
        val dochHören = Antworten(Text.get(OrtText.FINALARTHUR, "nicht_sicher"), 6)
        val sicherNichtHören = Antworten(Text.get(OrtText.FINALARTHUR, "sicher"), 17)
        val wieIch = Antworten(Text.get(OrtText.FINALARTHUR, "so_wie_ich"), 7)
        val verständnis = Antworten(Text.get(OrtText.FINALARTHUR, "verständnis"), 8)
        val willenVerdienen = Antworten(Text.get(OrtText.FINALARTHUR, "freien_willen_verdienen"), 9)
        val noHappy = Antworten(Text.get(OrtText.FINALARTHUR, "nicht_happy"), 10)
        val nichtRichtig = Antworten(Text.get(OrtText.FINALARTHUR, "nicht_richtig"), 11)
        val unkonsistent = Antworten(Text.get(OrtText.FINALARTHUR, "unkonsistent"), 12)
        val ausrede = Antworten(Text.get(OrtText.FINALARTHUR, "ausrede"), 13)
        val nichtOpfern = Antworten(Text.get(OrtText.FINALARTHUR, "nichts_opfern"), 14)
        val krank = Antworten(Text.get(OrtText.FINALARTHUR, "krank"), 15)
        val arbeit = Antworten(Text.get(OrtText.FINALARTHUR, "arbeit"), 16)
        val trauer = Antworten(Text.get(OrtText.FINALARTHUR, "kein_glück_ohne_trauer"), 18) { save -> save.finalArthur.trauerDenia }
        val kinder = Antworten(Text.get(OrtText.FINALARTHUR, "kinder"), 19) { save -> save.finalArthur.brutalAnzeige }
        val freie = Antworten(Text.get(OrtText.FINALARTHUR, "freie"), 20) { save -> save.finalArthur.chipBrokenAnzeige }
        val scam = Antworten(Text.get(OrtText.FINALARTHUR, "scam"), 21) { save -> save.finalArthur.scamDAnzeige }
        val addict = Antworten(Text.get(OrtText.FINALARTHUR, "drug_addict"), 22) { save -> save.finalArthur.addictAnzeige }
        val schuldgefühl = Antworten(Text.get(OrtText.FINALARTHUR, "schuldgefühl"), 23) { save -> save.finalArthur.schuldgefühlAnzeige }
        val erinnerungen = Antworten(Text.get(OrtText.FINALARTHUR, "erinnerungen_verlieren"), 24) { save -> save.finalArthur.erinnerungAnzeige }
        val kämpfen = Antworten(Text.get(OrtText.FINALARTHUR, "kämpfen"), 25)

        val endeEntfernen = Antworten(Text.get(OrtText.FINALARTHUR, "raus_nehmen"), 100)
        val endeKaputt = Antworten(Text.get(OrtText.FINALARTHUR, "rein_legen_kaputt"), 101) { save -> save.spieler.inventar.contains(save.kaputterChip) }
        val endeHeiler = Antworten(Text.get(OrtText.FINALARTHUR, "rein_legen_heile"), 102) { save -> save.spieler.inventar.contains(save.reparierterChip) }
        val endeSoLassen = Antworten(Text.get(OrtText.FINALARTHUR, "aufgeben"), 103)

        val anfangsAntworten: List<Antworten> = listOf(hinterfragen, bitteHilfe)
        val jaNein: List<Antworten> = listOf(ja, nein)
        val geschichteFragen: List<Antworten> = listOf(hören, nichtHören)
        val falscheSachen: List<Antworten> = listOf(trauer, kinder, freie, scam, addict, schuldgefühl, erinnerungen, kämpfen)
        val finalEnden: List<Antworten> = listOf(endeEntfernen, endeKaputt, endeHeiler, endeSoLassen)
    }
}
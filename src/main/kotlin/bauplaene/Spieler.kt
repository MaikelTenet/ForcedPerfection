package bauplaene

import study.coco.project_code.Save
import kotlin.Int

class Spieler(
    val inventar: MutableList<Item> = mutableListOf(),
    val startLeben: Int = 100,
    val standartSchaden: Int = 1,
    var xVariable: Int = 0,
    var yVariable: Int = 0,
    val anfangsChargeCooldown: Int = 5,
    val chargeMultiplyer: Int = 3
) {
    var schaden = standartSchaden
        private set

    var leben: Int = startLeben
        private set(value) {
            if (value < 0) {
                field = 0
            } else {
                if (value > startLeben) {
                    field = startLeben
                } else {
                    field = value
                }
            }
        }

    var chargeCooldown: Int = 0
        set(value) {
            if (value <= 0) {
                field = 0
            } else {
                if (value > anfangsChargeCooldown) {
                    field = anfangsChargeCooldown
                } else {
                    field = value
                }
            }
        }

    var waffeAusgerüstet: WaffeItem? = null
        private set

    fun equipWaffe(item: WaffeItem) {
        schaden = item.schaden
        if (waffeAusgerüstet != null) {
            inventar.add(waffeAusgerüstet!!)
        }
        waffeAusgerüstet = item
        inventar.remove(item)
    }

    fun deleteWeapon() {
        waffeAusgerüstet = null
        schaden = standartSchaden
    }

    fun changeHealth(save: Save, änderungsWert: Int, damage: Boolean, extraTodesNachricht: String = ""): String? {
        if (damage == true) leben -= änderungsWert
        else leben += änderungsWert
        if (save.spieler.leben <= 0) {
            save.nochLaufen = false
            return if (extraTodesNachricht == "") Text.get(OrtText.STORYFEEDS, "sterben") + Text.get(
                OrtText.STORYFEEDS,
                "tod"
            )
            else extraTodesNachricht + Text.get(OrtText.STORYFEEDS, "tod")
        }
        return null
    }

    var verkleidet: Boolean = false
        private set

    fun verkleiden(save: Save) {
        inventar.remove(save.polizeikleidung)
        verkleidet = true
        save.polizist.anfangsDialog = -1
    }
}
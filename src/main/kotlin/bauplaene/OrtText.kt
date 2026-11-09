package bauplaene

//erklärt und erschaffen mit der hilfe von Merlin
enum class OrtText(val namen: String) {
    STORYFEEDS("storyfeeds"),
    ERRORS("errors"),
    ACTIONS("actions"),
    NPC("objekte.npc"),
    ROOM("objekte.room"),
    MEME("meme"),
    HELP("help"),
    NAME("objekte.namen"),
    ITEM("objekte.item"),
    HUND("objekte.npcs.hund"),
    ARTHUR("objekte.npcs.arthur"),
    OMA("objekte.npcs.oma"),
    MARKUS("objekte.npcs.markus"),
    KILIAN("objekte.npcs.kilian"),
    LISA("objekte.npcs.lisa"),
    SHOPPER("objekte.npcs.shopper"),
    POLIZIST("objekte.npcs.polizist"),
    MAGIER("objekte.npcs.magier"),
    WACHE("objekte.npcs.wache"),
    SANDRA("objekte.npcs.sandra"),
    FINALARTHUR("objekte.npcs.finalarthur"),
    ;

    fun finalNamen(): String = "textfiles.$namen"
    /*
        companion object {
            fun fromNamen(namen: String): OrtText? = when (namen.lowercase()) {
                STORYFEEDS.namen -> STORYFEEDS
                ERRORS.namen -> ERRORS
                ACTIONS.namen -> ACTIONS
                else -> null
            }
        }
     */
}
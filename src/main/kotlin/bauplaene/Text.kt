import bauplaene.OrtText
import java.text.MessageFormat
import java.util.ResourceBundle

object Text {

    fun get(dateiName: OrtText, text: String, vararg args: Any): String {
        val bundle = ResourceBundle.getBundle(dateiName.finalNamen())
        val ausgabe = bundle.getString(text)
        return MessageFormat.format(ausgabe, *args)
    }
}
package study.coco.project_code

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(val game: Game) {
    @GetMapping("/game")
    @ResponseBody
    fun ausführen(@RequestParam("aktion") input: String): String {
        val befehl = game.gameplay(input)
        return befehl
    }
}
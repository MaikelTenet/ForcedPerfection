package study.coco.project_code.handler

import study.coco.project_code.Save

interface Handler {
    fun funktionsCheck(verb: String): Boolean
    fun handeln(verb: String, ding: String?, save: Save): String
}
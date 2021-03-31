package com.casadodev.domain.services

import com.google.gson.Gson
import java.io.File

class TestProcessData() {
    fun processLineByLine(csv: File, processor: (Map<String, String>) -> Unit) {
        val BOM = "\uFEFF"
        val header = csv.useLines { it.firstOrNull()?.replace(BOM, "")?.split(";") }
            ?: throw Exception("Sem um  header válido no csv")

        csv.useLines { linesSequence ->
            linesSequence
                .drop(1)
                .map { it.split(";") }
                .map { header.zip(it).toMap() }
                .forEach(processor)
        }
    }
}

fun main() {
    val processar = ProcessData()
    processar.processLineByLine(File("./subscriber-list.csv")) {
//        println(it)
        val gson = Gson()
        val dataJson = gson.toJson(it)
        println(dataJson)
    }
}

package com.casadodev.domain.services

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File

class ProcessData {

    private val logger: Logger = LoggerFactory.getLogger(ProcessData::class.java)

    fun processLineByLine(csv: File, processor: (Map<String, String>) -> Unit) {
        val BOM = "\uFEFF"
        val header = csv.useLines { it.firstOrNull()?.replace(BOM, "")?.split(";") }
            ?: throw Exception("Sem um  header válido no csv")

        logger.info("Colunas do csv: $header")

        csv.useLines { linesSequence ->
            linesSequence
                .drop(1)
                .map { it.split(";") }
                .map { header.zip(it).toMap() }
                .forEach(processor)

            logger.info("Dados do csv carregados")
        }
    }
}

package com.casadodev.application

import com.casadodev.domain.entities.SubsEntity
import com.casadodev.domain.services.ProcessData
import java.io.File


fun main() {
    var username: String?
    var date: String?
    var tier: String?
    var tenure: String?
    var streak: String?
    var subtype: String?
    var founder: String?

    val processar = ProcessData()

    processar.processLineByLine(File("./subscriber-list.csv")) {
//        Username;Subscribe Date;Current Tier;Tenure;Streak;Sub Type;Founder
        username = it["Username"]
        date = it["Subscribe Date"]
        tier = it["Current Tier"]
        tenure = it["Tenure"]
        streak = it["Streak"]
        subtype = it["Sub Type"]
        founder = it["Founder"]

//        print(it)
//        TODO: cirino
//         | Entender motivo de chamar "String?" e não identificar como "String"

        val subs = SubsEntity(
            1,
            username.toString(),
            date.toString(),
            tier.toString(),
            tenure.toString(),
            streak.toString(),
            subtype.toString(),
            founder.toString()
        )

        println(subs.username)

    }
}
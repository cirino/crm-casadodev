package com.casadodev.domain.repositories

import com.casadodev.domain.entities.SubsEntity


interface SubsRepository {

    fun save(entity: SubsEntity): SubsEntity?

    fun findAll(): List<SubsEntity>?

}

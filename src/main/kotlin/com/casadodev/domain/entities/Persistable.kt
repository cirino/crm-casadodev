package com.casadodev.domain.entities


interface Persistable<ID> {

    fun getID(): ID

    fun isNew(): Boolean

}

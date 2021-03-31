package com.casadodev.interfaces

import com.casadodev.domain.entities.SubsEntity
import com.casadodev.domain.repositories.SubsRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated


@Validated
@Controller("/")
class SubController(
    private val repository: SubsRepository
) {
    @Post("/salvar")
    fun saveSub(): HttpResponse<Any> {
        mutableListOf<SubsEntity>().apply {
            this.add(SubsEntity(null, "Teste", "-", "-", "-", "-", "-", "-"))
            this.add(SubsEntity(null, "Teste2", "-", "-", "-", "-", "-", "-"))
            this.add(SubsEntity(null, "Teste3", "-", "-", "-", "-", "-", "-"))
        }.forEach {
            repository.save(it)
        }
        return HttpResponse.created("Dados salvos!")
    }

    @Get
    fun list(): HttpResponse<Any> {
        return HttpResponse.ok(repository.findAll())
    }

}

package com.casadodev.domain.entities

import javax.persistence.*

/**
 * JPA Entity
 */
@Entity
@Table(name = "subs")
class SubsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val ID: Long?,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val date: String,

    @Column(nullable = false)
    val tier: String,

    @Column(nullable = false)
    val tenure: String,

    @Column(nullable = false)
    val streak: String,

    @Column(nullable = true)
    val subtype: String,

    @Column(nullable = true)
    val founder: String

)
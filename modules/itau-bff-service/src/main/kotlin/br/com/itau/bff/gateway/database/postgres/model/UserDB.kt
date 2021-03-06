package br.com.itau.bff.gateway.database.postgres.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UserDB(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    val publicId: UUID,

    @Column(length = 255)
    val name: String,

    @Column(unique = true, length = 100)
    val email: String,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime

)
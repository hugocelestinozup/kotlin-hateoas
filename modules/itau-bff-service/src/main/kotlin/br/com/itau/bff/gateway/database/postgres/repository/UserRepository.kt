package br.com.itau.bff.gateway.database.postgres.repository

import br.com.itau.bff.gateway.database.postgres.model.UserDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserDB, Long> {

    fun findByPublicId(publicId: UUID): Optional<UserDB>

    fun findByEmail(email: String): Optional<UserDB>

}
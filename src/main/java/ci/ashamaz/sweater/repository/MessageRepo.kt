package ci.ashamaz.sweater.repository

import ci.ashamaz.sweater.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepo : JpaRepository<Message, Int?> {

    fun findByTag(tag: String?): List<Message>
}
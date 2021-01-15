package ci.ashamaz.sweater.service

import ci.ashamaz.sweater.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {
    @Autowired
    private val userRepo: UserRepo? = null

    override fun loadUserByUsername(username: String?): UserDetails? {
        return userRepo?.findByUsername(username)
    }

}
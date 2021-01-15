package ci.ashamaz.sweater.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "usr")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        var id: Long? = null,
        private var username: String = "",
        private var password: String = "",
        var active: Boolean = true,
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
        @Enumerated(EnumType.STRING)
        var roles: MutableSet<Role> = HashSet()


) : UserDetails {

    fun setUsername(value: String) {
        username = value
    }

    fun setPassword(value: String) {
        password = value
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true

    }

    override fun isEnabled(): Boolean {
        return active
    }
}
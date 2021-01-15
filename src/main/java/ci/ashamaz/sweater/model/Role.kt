package ci.ashamaz.sweater.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {


    USER {
        override fun getAuthority(): String {
            return name
        }
    },
    ADMIN {
        override fun getAuthority(): String {
            return name
        }
    }
}
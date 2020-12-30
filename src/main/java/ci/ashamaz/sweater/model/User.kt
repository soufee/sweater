package ci.ashamaz.sweater.model

import javax.persistence.*

@Entity
@Table(name = "usr")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var username: String? = null,
        var password: String? = null,
        var active: Boolean = true,
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
        @Enumerated(EnumType.STRING)
        var roles: Set<Role>


)
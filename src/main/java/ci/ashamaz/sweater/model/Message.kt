package ci.ashamaz.sweater.model

import javax.persistence.*

@Entity
data class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        var id: Int? = null,
        var text: String? = null,
        var tag: String? = null,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var author: User? = null
) {
        fun  getAuthorName():String {
                return author?.username?:"<NO AUTHOR>"
        }
}
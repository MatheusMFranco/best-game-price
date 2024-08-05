package org.magalzim.database

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "gamers")
class GamerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    val name: String = "John Due",
    val email: String = "email@email.com",
    @ManyToOne
    val review: GameReviewEntity = GeneralReviewEntity()) {
}
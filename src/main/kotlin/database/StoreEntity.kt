package org.magalzim.database

import javax.persistence.*

@Entity
@Table(name = "stores")
class StoreEntity(
    @Id
    val id: Int = 0,
    val name: String = ""
)
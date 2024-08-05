package org.magalzim.model

data class Gamer(var name: String, var email: String) {
    var id = 0
    var review: GameReview = GeneralReview("LEGAL!")

    constructor(name: String, email: String, id: Int = 0):
            this(name, email) {
        this.id = id
    }

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name should not be empty")
        }
        this.email = verifyREmail()
    }

    override fun toString(): String {
        return "Gamer:\n" +
                "Nome: $name\n" +
                "Email: $email\n" +
                "Id: $id\n" +
                "Review: ${review.reviewTitle}"
    }

    fun verifyREmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email is not valid!")
        }
    }
}
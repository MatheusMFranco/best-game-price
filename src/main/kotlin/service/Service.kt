package org.magalzim.service

interface Service<T> {
    fun findById(id: Int): T
}
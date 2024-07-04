package org.magalzim.repository

import javax.persistence.EntityManager

abstract class Repository<M, E>(private val manager: EntityManager, private val entityType: Class<E>) {

    abstract fun toEntity(model: M): E
    abstract fun toModel(entity: E): M

    open fun list(): List<M> {
        val query = manager.createQuery("FROM ${entityType.simpleName}", entityType)
        return query.resultList.map { entity -> toModel(entity) }
    }

    open fun add(model: M) {
        val entity = toEntity(model)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    open fun findById(id: Int): M {
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        val entity = query.singleResult
        return toModel(entity)
    }
}
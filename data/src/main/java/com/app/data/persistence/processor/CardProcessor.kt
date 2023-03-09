package com.app.data.persistence.processor

import com.app.data.persistence.dao.CardDao
import com.app.data.persistence.entity.CardEntity
import com.app.data.persistence.processor.base.BaseProcessor
import com.app.domain.extensions.checkPersistenceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class CardProcessor(private val dao: CardDao) : BaseProcessor<CardEntity>(dao) {

    suspend fun get(name: String): Flow<CardEntity?> =
        flow { emit(dao.get(name)) }

    suspend fun getAll(): Flow<List<CardEntity>> =
        flow { emit(dao.getAll()) }

    suspend fun updateIsCheck(name: String, isCheck: Boolean): Flow<Unit> =
        flow { emit(dao.updateIsCheck(name, isCheck) == 1) }
            .checkPersistenceResult()
}

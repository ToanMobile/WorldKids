package com.app.domain.usecases

import com.app.domain.exception.PersistenceException
import com.app.domain.extensions.throwIfEmpty
import com.app.domain.model.Card
import com.app.domain.repository.CardRepository
import com.app.domain.usecases.base.FlowUseCase
import com.app.domain.usecases.base.Logger
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

/**
 * This class is an implementation of [FlowUseCase] that represents a use case for
 * retrieving a [Card].
 */
@Factory
class GetCacheCard(
    private val cardRepository: CardRepository,
    logger: Logger? = null
) : FlowUseCase<String, Card>(logger) {

    override suspend fun build(param: String): Flow<Card> =
        cardRepository.getCacheCard(param)
            .throwIfEmpty(PersistenceException)
}

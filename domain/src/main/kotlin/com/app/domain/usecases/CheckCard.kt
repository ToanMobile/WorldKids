package com.app.domain.usecases

import com.app.domain.model.Card
import com.app.domain.repository.CardRepository
import com.app.domain.usecases.base.FlowUseCase
import com.app.domain.usecases.base.Logger
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

/**
 * This class is an implementation of [FlowUseCase] that represents a use case
 * to check a [Card].
 */
@Factory
class CheckCard(
    private val cardRepository: CardRepository,
    logger: Logger? = null
) : FlowUseCase<Card, Unit>(logger) {

    override suspend fun build(param: Card): Flow<Unit> =
        cardRepository.checkCard(param)
}

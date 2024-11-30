package ru.itmo.es.customer

import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState

class CustomerAggregateState : AggregateState<String, CustomerAggregate> {
    private lateinit var nickname: String
    private lateinit var password: String
    override fun getId() = nickname

    @StateTransitionFunc
    fun customerCreatedApply(event: CustomerCreatedEvent) {
        nickname = event.nickname
        password = event.password
    }
}
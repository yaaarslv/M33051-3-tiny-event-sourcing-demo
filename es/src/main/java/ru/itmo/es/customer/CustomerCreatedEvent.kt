package ru.itmo.es.customer

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event

const val CUSTOMER_CREATED_EVENT = "CUSTOMER_CREATED_EVENT"

@DomainEvent(name = CUSTOMER_CREATED_EVENT)
class CustomerCreatedEvent(
    val nickname: String,
    val password: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<CustomerAggregate>(
    name = CUSTOMER_CREATED_EVENT,
    createdAt = createdAt
)
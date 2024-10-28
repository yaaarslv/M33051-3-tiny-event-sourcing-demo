package ru.itmo.es.customer

fun CustomerAggregateState.create(nickname: String, password: String): CustomerCreatedEvent {
    return CustomerCreatedEvent(
        nickname = nickname,
        password = password
    )
}

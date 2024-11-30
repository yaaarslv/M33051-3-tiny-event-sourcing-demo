package ru.itmo.es.customer

import ru.quipy.core.annotations.AggregateType
import ru.quipy.domain.Aggregate

@AggregateType(aggregateEventsTableName = "aggregate-customer")
class CustomerAggregate : Aggregate
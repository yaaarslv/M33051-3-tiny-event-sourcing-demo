package ru.itmo.es.customer

import org.springframework.web.bind.annotation.*
import ru.quipy.core.EventSourcingService

@RestController
@RequestMapping("/v1/customers")
class CustomerController(
    private val customerEsService: EventSourcingService<String, CustomerAggregate, CustomerAggregateState>
) {
    @GetMapping("/exists")
    fun existsById(@RequestParam nickname: String): Boolean {
        return customerEsService.getState(nickname) != null
    }

    @GetMapping("/{nickname}")
    fun findByNickname(@PathVariable nickname: String): CustomerAggregateState? {
        return customerEsService.getState(nickname)
    }

    @PostMapping
    fun createCustomer(@RequestBody customerDto: CustomerDto): CustomerCreatedEvent {
        return customerEsService.create { it.create(
            nickname = customerDto.nickname,
            password = customerDto.password
        ) }
    }
}
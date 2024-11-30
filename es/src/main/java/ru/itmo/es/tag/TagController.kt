package ru.itmo.es.tag

import org.springframework.web.bind.annotation.*
import ru.itmo.es.project.*
import ru.quipy.core.EventSourcingService
import java.util.*

@RestController
@RequestMapping("/v1/projects/{projectId}/tasks/tags")
class TagController (
    private val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {
    @PostMapping
    fun createTag(@RequestBody tagName: String, @PathVariable projectId: String): TagCreatedEvent {
        return projectEsService.create { it.createTag(tagName) }
    }
}
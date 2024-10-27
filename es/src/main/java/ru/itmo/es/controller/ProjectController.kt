package ru.itmo.es.controller

import org.springframework.web.bind.annotation.*
import ru.itmo.es.api.ProjectAggregate
import ru.itmo.es.api.ProjectCreatedEvent
import ru.itmo.es.api.TaskCreatedEvent
import ru.quipy.core.EventSourcingService
import ru.itmo.es.logic.ProjectAggregateState
import ru.itmo.es.logic.addTask
import ru.itmo.es.logic.create
import java.util.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {

    @PostMapping("/{projectTitle}")
    fun createProject(@PathVariable projectTitle: String, @RequestParam creatorId: String): ProjectCreatedEvent {
        return projectEsService.create { it.create(UUID.randomUUID(), projectTitle, creatorId) }
    }

    @GetMapping("/{projectId}")
    fun getAccount(@PathVariable projectId: UUID): ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }

    @PostMapping("/{projectId}/tasks/{taskName}")
    fun createTask(@PathVariable projectId: UUID, @PathVariable taskName: String): TaskCreatedEvent {
        return projectEsService.update(projectId) {
            it.addTask(taskName)
        }
    }
}
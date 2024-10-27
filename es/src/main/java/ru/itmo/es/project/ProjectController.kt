package ru.itmo.es.project

import org.springframework.web.bind.annotation.*
import ru.quipy.core.EventSourcingService
import ru.itmo.es.project.events.ProjectCreatedEvent
import ru.itmo.es.project.events.TaskCreatedEvent
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
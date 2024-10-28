package ru.itmo.es.task

import org.springframework.web.bind.annotation.*
import ru.itmo.es.project.ProjectAggregate
import ru.itmo.es.project.ProjectAggregateState
import ru.quipy.core.EventSourcingService
import java.util.*

@RestController
@RequestMapping("/v1/projects/{projectId}/tasks")
class TaskController(
    private val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {
    @PostMapping
    fun createTask(@PathVariable projectId: UUID, @RequestBody taskDto: TaskDto): TaskCreatedEvent {
        return projectEsService.update(projectId) {
            it.addTask(taskDto.title)
        }
    }
}

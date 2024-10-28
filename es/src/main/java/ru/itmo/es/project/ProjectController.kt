package ru.itmo.es.project

import org.springframework.web.bind.annotation.*
import ru.quipy.core.EventSourcingService
import java.util.*

@RestController
@RequestMapping("/v1/projects")
class ProjectController(
    private val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {

    @PostMapping
    fun createProject(@RequestBody projectDto: ProjectDto): ProjectCreatedEvent {
        return projectEsService.create { it.create(UUID.randomUUID(), projectDto.title, projectDto.creatorId) }
    }

    @GetMapping("/{projectId}")
    fun getAccount(@PathVariable projectId: UUID): ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }
}
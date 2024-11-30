package ru.itmo.es.task

import ru.itmo.es.project.ProjectAggregateState
import java.util.*

fun ProjectAggregateState.addTask(name: String): TaskCreatedEvent {
    return TaskCreatedEvent(
        projectId = this.getId(),
        taskId = UUID.randomUUID(),
        taskName = name
    )
}
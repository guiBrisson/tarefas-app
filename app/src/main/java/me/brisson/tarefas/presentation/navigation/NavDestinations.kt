package me.brisson.tarefas.presentation.navigation

object AppNavigationScreens {
    const val home_screen = "home"
    const val task_screen = "task"
}

object AppNavigationArgs {
    const val task_id_args = "taskId"
}

object AppNavigationRoutes {
    const val home_route = AppNavigationScreens.home_screen
    const val task_route =
        "${AppNavigationScreens.task_screen}?{${AppNavigationArgs.task_id_args}}"
}


package me.brisson.tarefas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.brisson.tarefas.presentation.screens.home.HomeRoute
import me.brisson.tarefas.presentation.screens.task.TaskRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavigationRoutes.home_route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppNavigationRoutes.home_route) {
            HomeRoute(
                onTaskClick = { taskId ->
                    navController.navigate(
                        route = "${AppNavigationScreens.task_screen}?$taskId"
                    )
                }
            )
        }

        composable(
            route = AppNavigationRoutes.task_route,
            arguments = listOf(navArgument(AppNavigationArgs.task_id_args) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            })
        ) {
            TaskRoute(
                onBackClick = { navController.navigateUp() }
            )
        }
    }

}
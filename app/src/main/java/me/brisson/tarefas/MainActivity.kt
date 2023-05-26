package me.brisson.tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.brisson.tarefas.presentation.design_system.theme.TarefasTheme
import me.brisson.tarefas.presentation.navigation.AppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TarefasTheme {
                AppNavigation()
            }
        }
    }
}

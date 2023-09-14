package com.raghul.todomission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raghul.todomission.todo_list.TodoListScreen
import com.raghul.todomission.ui.theme.TodoMissionTheme
import com.raghul.todomission.ui.theme.add_edit_todo.AddEditTodoScreen
import com.raghul.todomission.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoMissionTheme {
                val navcontroller = rememberNavController()
                NavHost(navController = navcontroller,
                    startDestination = Routes.TODO_LIST )
                {
                    composable(Routes.TODO_LIST){

                        TodoListScreen(onNavigate = {
                            navcontroller.navigate(it.route)

                        })
                    }
                    composable(
                        route=Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditTodoScreen(onPopBackStack = {
                            navcontroller.popBackStack()
                        })
                    }
                }
            }
        }
    }
}


package com.dogukan.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dogukan.todolistapp.di.Routes
import com.dogukan.todolistapp.ui.add_edit_todo.AddEditTodoScreen
import com.dogukan.todolistapp.ui.todolist.TodoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val  navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = Routes.TODO_LIST
            ){
                    composable(Routes.TODO_LIST){
                    TodoListScreen(onNavigate ={
                        navController.navigate(it.route)
                    }
                    )
                }
                composable(
                    route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                    arguments = listOf(
                        navArgument(name = "todoId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ){
                    AddEditTodoScreen(onPopBackStack = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}
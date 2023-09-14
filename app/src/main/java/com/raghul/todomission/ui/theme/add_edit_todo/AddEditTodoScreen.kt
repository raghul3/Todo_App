package com.raghul.todomission.ui.theme.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raghul.todomission.todo_list.TodoListEvent
import com.raghul.todomission.util.UiEvent
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
){
    val snackbarHostState=remember{
        SnackbarHostState()

    }
    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect{event->
            when (event){
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    // TODO: if something cause check on this for that scaffold for the todo screen

    FloatingActionButton(
        onClick = { viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick) },
    ) {
        Icon(Icons.Filled.Check, "Save")
    }
    Column(        modifier = Modifier.fillMaxSize()
        
    ) {
        TextField(value = viewModel.title, onValueChange = {
            viewModel.onEvent(AddEditTodoEvent.OnTitleChange(it))
        },
            placeholder = {
                Text(text="Title")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = viewModel.description, onValueChange = {
            viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(it))
        },
            placeholder = {
                Text(text="Description")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 10
        )
    }

}
package com.raghul.todomission.di

import android.app.Application
import androidx.room.Room
import com.raghul.todomission.data.TodoDatabase
import com.raghul.todomission.data.TodoRepository
import com.raghul.todomission.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatbase(app: Application):TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }
    @Provides
    @Singleton
    fun providesTodoRepository(db: TodoDatabase):TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }

}
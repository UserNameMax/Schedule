package ru.mishenko.maksim.schedule

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.mishenko.maksim.schedule.data.dataDiModule
import ru.mishenko.maksim.schedule.domain.domainDiModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(dataDiModule, domainDiModule))
        }
    }
}
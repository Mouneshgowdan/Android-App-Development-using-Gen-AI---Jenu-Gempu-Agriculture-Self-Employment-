package com.jenugumpu

import android.app.Application
import androidx.room.Room
import com.jenugumpu.data.local.AppDatabase
import com.jenugumpu.data.repository.HarvestRepository
import com.jenugumpu.data.repository.PriceRepository

class JenuGumpuApplication : Application() {
    lateinit var harvestRepository: HarvestRepository
        private set

    lateinit var priceRepository: PriceRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "jenu_gumpu.db"
        ).build()

        harvestRepository = HarvestRepository(database.harvestDao())
        priceRepository = PriceRepository(database.priceDao())
    }
}

package com.piyushprajpti.pyquote

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.room.Upsert

@Entity
data class QuoteEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "author")
    val author: String
)

@Entity
data class SaveEntity(
    @PrimaryKey
    val id: String,
)

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quoteEntity")
    fun getAllQuotesList(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuotes(quotes: List<QuoteEntity>)

    @Query("""
        SELECT q.* FROM QuoteEntity q
        INNER JOIN SaveEntity s ON q.id = s.id
    """)
    fun getAllSavedQuotes(): List<QuoteEntity>
}

@Dao
interface SaveDao {
    @Query("SELECT EXISTS(SELECT * FROM SaveEntity WHERE id = :id)")
    fun findIdInSaveTable(id: String): Boolean

    @Insert
    fun insertQuote(id: SaveEntity)

    @Delete
    fun deleteQuote(id: SaveEntity)
}

@Database(entities = [QuoteEntity::class, SaveEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
    abstract fun saveDao(): SaveDao
}



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Category::class, Expense::class, Budget::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): AppDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "budget_db"
                ).build()
            }
            return INSTANCE!!
        }
    }
}
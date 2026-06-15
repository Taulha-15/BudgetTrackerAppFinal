import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val minGoal: Double,
    val maxGoal: Double
)
    val minGoal: Double,
    val maxGoal: Double
)
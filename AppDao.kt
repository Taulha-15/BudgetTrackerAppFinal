import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE username = :u AND password = :p")
    suspend fun login(u: String, p: String): User?
}

@Insert
suspend fun insertCategory(category: Category)

@Query("SELECT * FROM Category")
suspend fun getCategories(): List<Category>

@Insert
suspend fun insertExpense(expense: Expense)

@Query("SELECT * FROM Expense")
suspend fun getExpenses(): List<Expense>

@Insert
suspend fun insertBudget(budget: Budget)

@Query("SELECT * FROM Budget")
suspend fun getBudgets(): List<Budget>

@Query("SELECT categoryName, SUM(amount) as total FROM Expense GROUP BY categoryName")
suspend fun getCategoryTotals(): List<CategoryTotal>


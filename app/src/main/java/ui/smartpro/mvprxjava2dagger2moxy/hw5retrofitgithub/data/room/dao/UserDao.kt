package ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ui.smartpro.mvprxjava2dagger2moxy.hw5retrofitgithub.data.room.entities.RoomGithubUser

@Dao
interface UserDao {
    /**
     * аннотации onConflict указываем, что при возникновении конфликта по первичному ключу надо
     * заменить старое значение новым.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(vararg users: RoomGithubUser)

    @Update
    fun update(users: List<RoomGithubUser>)

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg users: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    @Query("SELECT * FROM github_user")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM github_user")
    fun fetchUsers(): Single<List<RoomGithubUser>>

    /**
     * содержимое аргумента login функции findByLogin подставится в запрос вместо :login.
     */
    @Query("SELECT * FROM github_user WHERE login LIKE :login LIMIT 1")
    fun fetchUserByLogin(login: String): Single<RoomGithubUser>

//    @Query("SELECT * FROM github_user WHERE repos LIKE  :repos")
//    fun getUserListRepo(repos: String): Single<List<RoomGithubUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<RoomGithubUser>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retain(user: RoomGithubUser): Completable
}
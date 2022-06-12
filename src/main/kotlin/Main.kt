import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.dao.id.IntIdTable
object Cities : IntIdTable() {
    val name = varchar("name", 50)
}

object Users : IntIdTable() {
    val cityId = integer("cityId")
    val name = varchar("name", 50)
}
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var cityId by Users.cityId
    var name by Users.name
}


fun main() {
    Database.connect("jdbc:mysql://localhost:3306",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "password"  )
    transaction {
        SchemaUtils.create(Users)
        User.new {
            name = "Alice"
        }
        User.new {
            name = "Bob"
        }
        User.all().forEach {
            println(it.name)
        }
    }
}

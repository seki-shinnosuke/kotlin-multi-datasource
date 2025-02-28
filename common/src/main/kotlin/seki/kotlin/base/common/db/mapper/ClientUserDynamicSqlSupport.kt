/*
 * Auto-generated file. Created by MyBatis Generator
 */
package seki.kotlin.base.common.db.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ClientUserDynamicSqlSupport {
    val clientUser = ClientUser()

    val clientUserId = clientUser.clientUserId

    val userName = clientUser.userName

    class ClientUser : AliasableSqlTable<ClientUser>("client_user", ::ClientUser) {
        val clientUserId = column<Long>(name = "client_user_id", jdbcType = JDBCType.BIGINT)

        val userName = column<String>(name = "user_name", jdbcType = JDBCType.VARCHAR)
    }
}
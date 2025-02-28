package seki.kotlin.base.api.db.custom.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ClientUserCustomMapper {
    @Select(
        """
            SELECT
                user_name
            FROM
                client_user
        """,
    )
    fun selectClientUserName(): List<String>
}

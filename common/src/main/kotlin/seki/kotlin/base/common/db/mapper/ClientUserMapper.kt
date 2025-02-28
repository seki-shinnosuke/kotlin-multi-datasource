/*
 * Auto-generated file. Created by MyBatis Generator
 */
package seki.kotlin.base.common.db.mapper

import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.kotlin.CountCompleter
import org.mybatis.dynamic.sql.util.kotlin.DeleteCompleter
import org.mybatis.dynamic.sql.util.kotlin.KotlinUpdateBuilder
import org.mybatis.dynamic.sql.util.kotlin.SelectCompleter
import org.mybatis.dynamic.sql.util.kotlin.UpdateCompleter
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.countFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.deleteFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectDistinct
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectList
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectOne
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import seki.kotlin.base.common.db.mapper.ClientUserDynamicSqlSupport.clientUser
import seki.kotlin.base.common.db.mapper.ClientUserDynamicSqlSupport.clientUserId
import seki.kotlin.base.common.db.mapper.ClientUserDynamicSqlSupport.userName
import seki.kotlin.base.common.db.model.ClientUser

@Mapper
interface ClientUserMapper : CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    @SelectKey(statement=["SELECT LAST_INSERT_ID()"], keyProperty="row.clientUserId", before=false, resultType=Long::class)
    fun insert(insertStatement: InsertStatementProvider<ClientUser>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ClientUserResult", value = [
        Result(column="client_user_id", property="clientUserId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<ClientUser>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ClientUserResult")
    fun selectOne(selectStatement: SelectStatementProvider): ClientUser?
}

fun ClientUserMapper.count(completer: CountCompleter) =
    countFrom(this::count, clientUser, completer)

fun ClientUserMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, clientUser, completer)

fun ClientUserMapper.deleteByPrimaryKey(clientUserId_: Long) =
    delete {
        where { clientUserId isEqualTo clientUserId_ }
    }

fun ClientUserMapper.insert(row: ClientUser) =
    insert(this::insert, row, clientUser) {
        map(userName) toProperty "userName"
    }

fun ClientUserMapper.insertSelective(row: ClientUser) =
    insert(this::insert, row, clientUser) {
        map(userName).toPropertyWhenPresent("userName", row::userName)
    }

private val columnList = listOf(clientUserId, userName)

fun ClientUserMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, clientUser, completer)

fun ClientUserMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, clientUser, completer)

fun ClientUserMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, clientUser, completer)

fun ClientUserMapper.selectByPrimaryKey(clientUserId_: Long) =
    selectOne {
        where { clientUserId isEqualTo clientUserId_ }
    }

fun ClientUserMapper.update(completer: UpdateCompleter) =
    update(this::update, clientUser, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: ClientUser) =
    apply {
        set(userName) equalToOrNull row::userName
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: ClientUser) =
    apply {
        set(userName) equalToWhenPresent row::userName
    }

fun ClientUserMapper.updateByPrimaryKey(row: ClientUser) =
    update {
        set(userName) equalToOrNull row::userName
        where { clientUserId isEqualTo row.clientUserId!! }
    }

fun ClientUserMapper.updateByPrimaryKeySelective(row: ClientUser) =
    update {
        set(userName) equalToWhenPresent row::userName
        where { clientUserId isEqualTo row.clientUserId!! }
    }
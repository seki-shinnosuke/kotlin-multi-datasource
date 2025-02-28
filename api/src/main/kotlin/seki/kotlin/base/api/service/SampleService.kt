package seki.kotlin.base.api.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import seki.kotlin.base.api.db.custom.mapper.ClientUserCustomMapper
import seki.kotlin.base.common.db.mapper.ClientUserMapper
import seki.kotlin.base.common.db.mapper.insert
import seki.kotlin.base.common.db.model.ClientUser

@Service
class SampleService(
    private val clientUserMapper: ClientUserMapper,
    private val clientUserCustomMapper: ClientUserCustomMapper,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = [Throwable::class])
    fun primaryDb() {
        clientUserMapper.insert(ClientUser(userName = "RW書き込みテスト"))
        clientUserCustomMapper.selectClientUserName()
    }

    @Transactional(readOnly = true)
    fun secondaryDb() {
        val names = clientUserCustomMapper.selectClientUserName()
        println("RO DB内登録件数: ${names.size}")
        kotlin.runCatching {
            clientUserMapper.insert(ClientUser(userName = "RW書き込みテスト"))
        }.onFailure {
            println("RO DB接続なのでINSERTはエラーとなる: ${it.message}")
        }
    }
}

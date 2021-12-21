package ru.spbstu.trkpo.musicservice.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.spbstu.trkpo.musicservice.api.impl.MusicServiceApiAggregator
import ru.spbstu.trkpo.musicservice.dao.TokensInfoDao
import ru.spbstu.trkpo.musicservice.entity.TokensInfo
import ru.spbstu.trkpo.musicservice.service.AuthService
import java.util.*

@Service
class AuthServiceImpl: AuthService {
    @Autowired
    private lateinit var tokensInfo: TokensInfoDao

    @Autowired
    private lateinit var musicServiceApiAggregator: MusicServiceApiAggregator

    override fun getOAuthUrl(tgBotId: String): String {
        return musicServiceApiAggregator.musicServiceApi.getUrl(tgBotId)
    }

    override fun register(authCode: String): UUID? {
        val tokens = musicServiceApiAggregator.musicServiceApi.register(authCode) ?: return null
        val guid = UUID.randomUUID()
        val userInfo = TokensInfo().apply {
            userId = guid
            accessToken = tokens.accessToken
            refreshToken = tokens.refreshToken
        }
        tokensInfo.save(userInfo)

        return guid
    }

    /*
    * TODO: implement
    * */
    override fun authorize(authCode: String, uuid: UUID) {

    }
}
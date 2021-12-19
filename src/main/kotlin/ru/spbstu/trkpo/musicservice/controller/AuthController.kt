package ru.spbstu.trkpo.musicservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.spbstu.trkpo.musicservice.dto.AuthRequest
import ru.spbstu.trkpo.musicservice.service.AuthService
import java.util.*

@RestController
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @GetMapping("/authUrl")
    fun getOAuthUrl(): ResponseEntity<String> {
        /*
        * TODO: implement
        * */
        return ResponseEntity(authService.getOAuthUrl(), HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody authCode: String): ResponseEntity<UUID> {
        /*
        * TODO: implement
        * */
        return ResponseEntity(authService.authorize(authCode), HttpStatus.OK)
    }

    @PostMapping("/authorize")
    fun authorize(@RequestBody authRequest: AuthRequest): ResponseEntity<HttpStatus> {
        /*
        * TODO: implement
        * */
        authService.authorize(authRequest.authCode, authRequest.userId)
        return ResponseEntity.ok().build()
    }
}
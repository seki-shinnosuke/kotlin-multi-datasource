package seki.kotlin.base.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import seki.kotlin.base.api.service.SampleService

@RestController
class SampleController(
    private val sampleService: SampleService,
) {
    @PostMapping("/api/v1/primary")
    fun primaryDb(): ResponseEntity<Void> {
        sampleService.primaryDb()
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/api/v1/secondary")
    fun secondaryDb(): ResponseEntity<String> {
        sampleService.secondaryDb()
        return ResponseEntity("OK", HttpStatus.OK)
    }
}

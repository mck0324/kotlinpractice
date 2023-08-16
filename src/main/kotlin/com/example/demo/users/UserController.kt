package com.example.demo.users


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
        @Autowired private val userRepository: UserRepository
) {

    //get all users
    @GetMapping("")
    fun getAllusers(): List<User> =
            userRepository.findAll().toList()

    //create user
    @PostMapping("")
    fun createUser(@RequestBody user: User) : ResponseEntity<User> {
        val savedUser = userRepository.save(user)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }

    //get user by id
    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: Int) : ResponseEntity<User> {
        val user = userRepository.findById(userId)
        return if (user.isPresent) {
            ResponseEntity(user.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
package me.calciu.ghiblipicker

import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import kotlinx.serialization.*
import kotlinx.serialization.json.*

val movieList = mutableListOf(
    "Nausicaä of the Valley of the Wind",
    "Castle in the Sky",
    "My Neighbor Totoro",
    "Grave of the Fireflies",
    "Kiki's Delivery Service",
    "Only Yesterday",
    "Porco Rosso",
    "Ocean Waves",
    "Pom Poko",
    "Whisper of the Heart",
    "Princess Mononoke",
    "My Neighbors the Yamadas",
    "Spirited Away",
    "The Cat Returns",
    "Howl\'s Moving Castle",
    "Tales from Earthsea",
    "Ponyo",
    "Arrietty",
    "From Up on Poppy Hill",
    "The Wind Rises",
    "The Tale of the Princess Kaguya",
    "When Marnie Was There",
    "The Red Turtle",
    "Earwig and the Witch",
    "The Boy and the Heron",
)
fun pickMovie() {
    val movieListObjectCount = movieList.size - 1
    val randomMovieNum = (0..movieListObjectCount).random()
    println(movieList[randomMovieNum])
    Thread.sleep(2000)
    mainMenu()
}
fun excludeMovie() {
    print("\u001b[H\u001b[2J")
    //Print every movie in the list one by one in a numbered format
    for ((numberedList, movie) in movieList.withIndex()) {
        print(numberedList)
        print(": ")
        println(movie)
    }
    println("Enter the number of the movie you would like to exclude")
    println("Enter \"Exit\" to return to menu.")
    try {
        val getUserInput = readln()
        if (getUserInput == "Exit") {
            mainMenu()
        }
        movieList.removeAt(getUserInput.toInt())
        excludeMovie()
    }
    catch (e: NumberFormatException) {
        println("Invalid Input!")
        Thread.sleep(1000)
        excludeMovie()
    }
    catch (e: IndexOutOfBoundsException) {
        println("Invalid Input!")
        Thread.sleep(1000)
        excludeMovie()
    }
}
fun saveExclusion () {
    val json = Json {
        ignoreUnknownKeys = true
    }
    val jsonMovieString: String = json.encodeToString(movieList)
    try {
        val file = File("save.json")
        val writer = FileWriter("save.json")
        writer.write(jsonMovieString)
        writer.close()
    }
    catch (e: Exception) {
        println("File saving failed: ${e.message}")
        Thread.sleep(1000)
        mainMenu()
    }
    finally {
        println("Saved to save.json!")
        Thread.sleep(1000)
        mainMenu()
    }
}
fun loadExclusion () {
    val filePath = "save.json"
    try {
        val file = File(filePath)
        val content = file.readText()
        val json = Json {ignoreUnknownKeys = true}
        val deserializedJsonMovie = json.decodeFromString<MutableList<String>>(content)
        movieList.clear()
        for (entry in deserializedJsonMovie) {
            movieList.add(entry)
        }
    }
    catch (e: FileNotFoundException) {
        println("File not found! Try saving first.")
        Thread.sleep(1000)
        mainMenu()
    }
    catch (e: Exception) {
        println("File loading failed: ${e.message}")
        Thread.sleep(1000)
        mainMenu()
    }
    finally {
        println("Loaded from save.json!")
        Thread.sleep(1000)
        mainMenu()
    }
}

fun exit() {
    kotlin.system.exitProcess(0)
}
fun mainMenu() {
    //Fancy way to clear the terminal on *nix OS's.
    print("\u001b[H\u001b[2J")
    println("Studio Ghibli Picker v0.2.1 by calciume")
    println("-------------------------------------")
    println("1. Pick a movie for me!")
    println("2. Exclude a movie from being picked")
    println("3. Save")
    println("4. Load")
    println("5. Exit")
    //Make sure the program doesn't break from invalid user input
    try {
        val getUserInput = readln().toInt()
        //This is basically a switch statement but Kotlin
        when (getUserInput) {
            1 -> pickMovie()
            2 -> excludeMovie()
            3 -> saveExclusion()
            4 -> loadExclusion()
            5 -> exit()
            else -> println("Invalid number!")
        }
    }
    catch (e: NumberFormatException) {
        println("Invalid input!")
        Thread.sleep(1000)
        mainMenu()
    }
}
fun main() {
    mainMenu()
}
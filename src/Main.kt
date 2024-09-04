package me.calciu.ghiblipicker

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
    try {
        val getUserInput = readln().toInt()
        movieList.removeAt(getUserInput)
        mainMenu()
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
fun exit() {
    kotlin.system.exitProcess(0)
}
fun mainMenu() {
    //Fancy way to clear the terminal on *nix OS's.
    print("\u001b[H\u001b[2J")
    println("Studio Ghibli Picker by calciume")
    println("-------------------------------------")
    println("1. Pick a movie for me!")
    println("2. Exclude a movie from being picked")
    println("3. Exit")
    //Make sure the program doesn't break from invalid user input
    try {
        val getUserInput = readln().toInt()
        //This is basically a switch statement but Kotlin
        when (getUserInput) {
            1 -> pickMovie()
            2 -> excludeMovie()
            3 -> exit()
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

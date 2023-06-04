# World of cinema

<p align="left">
World Of Cinema is a sample Android project using <a href="https://imdb-api.com/api">IMDB-api</a> based on MVVM architecture. It showcases the latest Android tech stacks with well-designed architecture and best practices.

# Preview
<img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/login.png' width='25%'/><img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/main.png' width='25%'/><img src = 'https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/details.png' width='25%'/><img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/allfilmsincategory.png' width='25%'/><img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/actors.png' width='25%'/><img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/actor.png' width='25%'/><img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/favorites.png' width='25%'/><img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/search.png' width='25%'/>
## Features
* 100% Kotlin
* MVVM architecture
* Kotlin Coroutines + Flow
* Single activity pattern
* Dependency injection
* Shared Preferences

# Architecture
The World Of Cinema app follows the official architecture guidance.
<img src='https://github.com/Gerlenn/WorldOfCinema/blob/develop/screens/architecture.png' width='70%'/>

## Tech Stacks
* [Retrofit](http://square.github.io/retrofit/) + [OkHttp](http://square.github.io/okhttp/) - RESTful API and networking client.
* [Hilt](https://dagger.dev/hilt/) - Dependency injection.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - A collections of libraries that help you design rebust, testable and maintainable apps.
    * [Room](https://developer.android.com/training/data-storage/room) - Local persistence database.
    * [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) - UI related data holder, lifecycle aware.
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Observable data holder that notify views when underlying data changes.
    * [Navigation component](https://developer.android.com/guide/navigation) - Fragment routing handler.
* [Coroutine](https://developer.android.com/kotlin/coroutines) Concurrency design pattern for asynchronous programming.
* [Flow](https://developer.android.com/kotlin/flow) Stream of value that returns from suspend function.
* [Picasso](https://square.github.io/picasso/) - Image loading.
  
# Credits
* Design on <a href="https://www.figma.com/file/dHEcVu2y0vMAdZqC5wKjMS/Media?type=design&node-id=21-247">Figma</a>.
  

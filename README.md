
<h1 align="center">Yape Technical Test</h1>

<p align="center">
Yape Test Android
</p>


<p align="center">
<img src="/assets/img_1.png"/>
</p>

## Download
Go to the [Link](https://drive.google.com/drive/folders/1o8xsCGQYqGTT17y52cGiZyT8ryCxrU2B?usp=sharing) to download the APK.


## Tech stack & Open-source libraries

- [Kotlin](https://kotlinlang.org/) based
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html/) for asynchronous programming.
- Jetpack
    - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - DataBinding - Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model)
    - Repository Pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Material-Components](https://github.com/material-components/material-components-android)

- [Google Maps](https://developers.google.com/maps/documentation/android-sdk/overview) maps for android.
- [Glide](https://github.com/bumptech/glide) load images.
- [Mockk](https://mockk.io) Unit Test.
## Architecture
Is based on the MVVM architecture and the Repository pattern.

<p align="center">
<img src="/assets/img_2.png"/>
</p>


## Unit Tests
Testing is an essential part of the software development process. A unit test generally exercises the functionality of the smallest possible unit of code (which could be a method or class) in a repeatable way. You should add unit tests when you need to verify the logic of a specific code in your app.

```kotlin

class MainViewModelTest {

    @get:Rule
    val coroutineTestRule: MainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var repository: RecipeRepository
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(repository)

    }

    @Test
    fun getRecipes() = runTest {
        coEvery {
            repository.getRecipes()
        } returns Result.success(listOf())

        viewModel.getRecipes()
        viewModel.uiState.value.let {

            assert(it == MainUIState.Success(listOf()))
        }
    }
}
```
## License
MIT
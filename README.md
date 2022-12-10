# cleanArchitectureTutorial
This tutorial is to implement clean architecture in android.

- In `book_search_app` branch you can get implementation of MVVM pattern
- Dagger hilt is used for dependency injection with retrofit is singleton scope while other objects have respective scope

- In this app we are following the clean architecture(onion architecture) 
- Used clean architecture with packages - data, ui, usecase, viewmodel, di
- Data deals with the network call using retrofit through repositories. (Can be used for fetching data from local database or from any other source)
- Mapper will convert the network API response to domain model which is inside usecase package.

- For caching okhttp interceptor is implemented with caching logic. 

- LiveData is used for observing the changes in data and emitting the events when there is changes

- Databinding is used for populating the data in layouts and reclyclerview items
- Glide is used for managing the images, deals with caching
- Constraintlayout is used for keeping the layout light weight(to keep layout nesting at minimal level)
- appcompat widgets are used in layouts
- ktlint for keeping the check on lint warnings

- `ktlint -F` - for formatting the code and auto correct the warnings
- `./gradlew clean build` - for building the project

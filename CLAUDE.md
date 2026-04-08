# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Rick and Morty KMP is a Kotlin Multiplatform (KMP) project using Compose Multiplatform to target both Android and iOS. The app displays information about characters, locations, and episodes from the Rick and Morty API.

## Development Commands

### Gradle Build Tasks

Build variants:
- **Debug build (Android)**: `./gradlew :composeApp:assembleDebug`
- **Release build (Android)**: `./gradlew :composeApp:assembleRelease`
- **Build iOS framework**: `./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64` (simulator) or `./gradlew :composeApp:linkDebugFrameworkIosArm64` (device)

### Running Tests

- **Run all tests**: `./gradlew test`
- **Run common tests**: `./gradlew :composeApp:commonTest`
- **Run Android tests**: `./gradlew :composeApp:testDebug`

### IDE Run Configurations

Use the IDE's run widget to execute:
- Android app directly from the toolbar
- iOS app: open `/iosApp` in Xcode and run from there

## Architecture & Structure

### Source Layout

The project follows KMP conventions with platform-specific and shared code:

```
composeApp/src/
├── commonMain/    # Shared code (UI, logic, API calls)
├── androidMain/   # Android-specific implementations
├── iosMain/       # iOS-specific implementations
└── commonTest/    # Shared test code
```

### Key Architectural Patterns

**MVI State Management (Orbit)**
- State and side effects are managed through Orbit MVI containers
- Intents drive state changes; containers emit state updates
- Located in `presentation/` layer with screen-specific ViewModels

**Dependency Injection (Koin)**
- Composable DI configuration in `commonMain`
- Separate Android (`koin-android`) and iOS configurations
- Injected via `@Inject` or through Koin compose ViewModels

**Navigation**
- Type-safe navigation using Jetpack Navigation Compose
- Routes defined as sealed classes in `presentation/navigation/Screen.kt`
- Bottom navigation bar with three main screens: Characters, Locations, Episodes

**Image Loading (Coil)**
- Configured to use Ktor HTTP client for cross-platform image fetching
- Set up in `App.kt` via `setSingletonImageLoaderFactory`

**HTTP Client (Ktor)**
- Platform-specific clients: OkHttp (Android), Darwin (iOS)
- Shared configuration in `commonMain`

### Directory Organization

```
commonMain/
├── presentation/
│   ├── App.kt                    # Main composable root
│   ├── navigation/Screen.kt      # Route definitions
│   ├── screen/                   # Screen composables (CharacterScreen, etc.)
│   ├── model/                    # Presentation models
│   └── viewmodel/                # Orbit containers (if present)
├── data/                         # API clients, repositories
└── di/                           # Koin module definitions
```

## Key Technologies

- **Kotlin**: 2.3.20
- **Compose Multiplatform**: 1.10.3
- **Orbit MVI**: 11.0.0 (state management)
- **Koin**: 4.1.1 (DI)
- **Ktor**: 3.3.3 (HTTP client)
- **Coil**: 3.0.0 (image loading)
- **AndroidX Navigation**: 2.9.1 (routing)
- **Min SDK**: Android 24 | Target SDK: Android 36

## Common Development Tasks

### Adding a New Screen

1. Create a composable in `presentation/screen/`
2. Add a sealed class object to `Screen.kt` for navigation
3. Register the route in `NavHost` within `App.kt`
4. Add a `NavigationBarItemModel` entry if it should appear in bottom nav
5. Create an Orbit container/ViewModel if state management is needed

### Integrating with the Rick and Morty API

API calls should be:
- Made via Ktor HTTP client (configured in `data/` layer)
- Used through repositories or API service classes
- Injected via Koin in composables
- Connected to Orbit containers for state management

### Platform-Specific Code

- Android-specific logic: `composeApp/src/androidMain/`
- iOS-specific logic: `composeApp/src/iosMain/`
- Use `expect`/`actual` pattern for platform abstractions in `commonMain`
- Platform HTTP clients are pre-configured (OkHttp for Android, Darwin for iOS)

## Testing

- Unit tests belong in `commonTest` for shared logic
- Use `kotlin-test` framework
- Orbit includes testing utilities in `orbit-testing` library for container testing

## Notes

- The Android namespace is `org.carlosburuel.tickandmorty` (note: "tickandmorty" not "rickandmorty")
- Bottom navigation onClick handlers are currently stubbed and need navigation wiring
- The app uses Material 3 design system via `compose.material3`
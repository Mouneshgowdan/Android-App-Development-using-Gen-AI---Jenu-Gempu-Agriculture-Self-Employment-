# Jenu-Gumpu

Kannada-first Android MVP for a honey producer collective.

## MVP Features

- Harvest Log with batch ID, date, location, quantity, floral source, and grade.
- Room database for offline local persistence.
- Grading Tool with simulated moisture and color score.
- Stock Tracker with total quantity from saved harvest entries.
- Simulated Price Monitor for retail vs wholesale prices.
- Profit Calculator for revenue, costs, and profit.

## Run

1. Open this folder in Android Studio: `JenuGumpu`.
2. Let Gradle sync.
3. Select an emulator or connected Android phone.
4. Run the `app` configuration.

If Gradle sync complains about missing SDK 35, install it from Android Studio > SDK Manager, or change `compileSdk` and `targetSdk` in `app/build.gradle.kts` to an SDK you have installed.

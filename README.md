# CAPTAIN BOOST

Premium dark Android utility built with native Java views and no third-party runtime dependencies.

## Features
- Live refresh-rate readout from Android's display API.
- Battery percentage readout.
- Game launcher shortcuts for CODM Global, CODM Garena, and Roblox (launches only if installed).\n- Shortcuts to Android display, battery-saver, and app-details settings.
- Lightweight foreground-only operation; no background service, root access, game injection, or game-file changes.

## Important limits
The displayed refresh rate is the rate reported for this app's current display context. Android does not provide ordinary third-party apps with a universal API to force another app to render at 120 FPS. A 120 Hz display does not guarantee 120 FPS in a game. Game support, device capability, thermal state, and system policy determine the actual result.

## Build APK
1. Open this folder in Android Studio.
2. Install Android SDK Platform 35 and Android Gradle Plugin-compatible Gradle.
3. Sync the project.
4. Select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. The debug APK will be under `app/build/outputs/apk/debug/`.

Package: `com.grigore.gamebooster`\n\nGame launch support: CODM Global (`com.activision.callofduty.shooter`), CODM Garena (`com.garena.game.codm`), Roblox (`com.roblox.client`).
Min Android: 6.0 (API 23)
Target SDK: 35

# CAPTAIN BOOST - Build from an Android phone

This repository contains Android source code. It is not an APK until the cloud build finishes.

## Build without a PC
1. Sign in to GitHub in your phone browser and create a new **public or private repository** named `captain-boost`.
2. Upload the contents of this project folder to the repository root. Make sure `.github/workflows/build-apk.yml` is included. GitHub's web upload may hide dot-folders; if so, upload the workflow file using GitHub's **Add file > Create new file** and name it `.github/workflows/build-apk.yml`, then paste its contents from this project.
3. Open the repository's **Actions** tab.
4. Select **Build CAPTAIN BOOST APK** and tap **Run workflow** (or wait for the first push build).
5. When the run completes successfully, open it and download the `CAPTAIN-BOOST-APK` artifact.
6. Extract the artifact ZIP on your phone. Install `app-debug.apk` and allow Android's install-from-this-source prompt if shown.

## Build details
- Java 17, Gradle 8.9, Android Gradle Plugin 8.7.3, Android SDK 35.
- Output: `app/build/outputs/apk/debug/app-debug.apk`.
- This is a debug APK, not a Play Store release build.

## Limits
The app provides game launch shortcuts and Android settings shortcuts. It does not modify CODM or Roblox, change their internal FPS limits, or guarantee 120 FPS. Actual frame rate depends on the phone, display, game support, heat, and Android policy. No app can guarantee zero crashes on every device; test before relying on it.

HUD Mod (source, Minecraft 1.21.11, Fabric)

Repo layout (files must be in the repo ROOT, not in a subfolder):
  gradlew, gradlew.bat, build.gradle, gradle.properties, settings.gradle, gradle/ (from the Fabric template)
  src/ (this code)
  .github/workflows/build.yml (this build workflow)

Controls:
  ]  increase HUD size (1-10)
  [  decrease HUD size
Setting is saved to config/hudmod.json.

If the build fails, the log in the Actions tab names the failing line.

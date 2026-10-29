# HelloAab

Application Android minimale (Java) configurée pour produire un **Android App Bundle (.aab)**.

## Générer le .aab

Avec Android Studio : *Build > Generate Signed Bundle / APK > Android App Bundle*.

En ligne de commande (Gradle 8.9+, JDK 17, Android SDK) :

```bash
gradle :app:bundleRelease
```

Résultat : `app/build/outputs/bundle/release/app-release.aab`.

## Signature

Sans keystore, le bundle est non signé. Pour le signer, définis `KEYSTORE_FILE`,
`KEYSTORE_PASSWORD`, `KEY_ALIAS` et `KEY_PASSWORD` (variables d'environnement ou `gradle.properties` local).

Un workflow GitHub Actions (`.github/workflows/helloaab-bundle.yml`) construit le .aab (artefact `app-release-aab`) et un APK debug installable (artefact `app-debug-apk`).

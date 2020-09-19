# AndroidBites : How to Implement Dynamic Feature modules

Step by Step Guide to Implement android dynamic feature modules

## Steps

1. Add dependency for the support of dynamic module from play core services, adding core play library enables dynamic delivery support from playstore distribution. official docs [playcore](https://developer.android.com/guide/playcore)

```
dependencies {
    // For Kotlin users also import the Kotlin extensions library for Play Core:
    implementation 'com.google.android.play:core-ktx:1.8.1'
    ...
}
```

2. Create an application class which extends `SplitCompatApplication`, don't forget to register in Android Manifest.

```
class GitTrendApp : SplitCompatApplication() {
    ...
}

...
//Android Manifest

<application
        android:name=".GitTrendApp"
        ...
        ...
        />
```

3. Which ever activity needs to access to dynamic module activity needs to be marked with `installActivity()`

```
override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        // step 3 mark activity which need access to dynamic activity
        SplitCompat.installActivity(requireNotNull(newBase))
    }
```

4. Creating a dynamic module, update the module from `library` type to `dynamic-feature`

```
apply plugin: 'com.android.library' 
// to 
apply plugin: 'com.android.dynamic-feature'
```

5. Dont forget to include project into dynamic module for resource sharing

```
    dependencies {
        implementation project(":app")
        ...
    }
```
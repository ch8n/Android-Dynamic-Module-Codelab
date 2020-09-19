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

5. Include project into dynamic module for resource sharing, also you need to remove app module dependency on second module

```
    // app module build.gradle 
    dependencies {
        //remove 
        // implementation project(":secondfeature") 
        ...
    }
    
    // second module build.gradle 
    dependencies {
        //add 
        implementation project(":app") 
        ...
    }
```

6. Inside App module add a string resource which specify the title of the dynamic module, this will show up to user while downloading

```
 // in app module strings.xml
 <string name="module_second_title">Second feature module</string>

```

7. Adding module dynamic module attribute to manifest

```
    // add in android manifest of second feature module
    <!--step 7 add Distribution namespace and module attributes-->
    <dist:module
        dist:instant="false"
        dist:onDemand="true"
        dist:title="@string/module_second_title">

        <!-- tells gradle whether this module is included from beginning or not-->
        <dist:fusing dist:include="true" />
    </dist:module>
```

8. Create `SplitInstallManager`, it provides capability to install/remove dynamic module

```
    protected val splitManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(applicationContext)
    }
```

9. Add gradle module name into to app module string resource which will be useful in declaring module name while insalling

```
    // app module in strings.xml
    <string name="gradle_second_feature">secondfeature</string>
```

10. Create split install module download request using `SplitInstallManager#startInstall()`

```

     val moduleInstallRequest = SplitInstallRequest.newBuilder()
                .addModule(getString(R.string.gradle_second_feature))
                .build()

    splitManager.startInstall(moduleInstallRequest)
                .addOnSuccessListener {
                    toast("Module installed")
                }
                .addOnFailureListener {
                    toast("Module failed ${it.message}")
                }

```

11. When no longer have use of the module you can remove it using
    `SplitInstallManager#deferredUninstall()` 

```
    splitManager.deferredUninstall(
     listOf(
         getString(R.string.gradle_second_feature)
         )
    )
 ```

1.   add list of dynamic modules to you app module gradle, this tells gradle android pluign we are using dynamic modules

```
    // in app module
    android {
        // android plugin enable dynamic module and list of supported dynamic module
        dynamicFeatures = [":secondfeature"]
    }
```
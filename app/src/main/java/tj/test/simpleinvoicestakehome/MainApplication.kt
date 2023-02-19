package tj.test.simpleinvoicestakehome

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import tj.test.simpleinvoicestakehome.utils.ReleaseTree

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "Class: %s Line: %, Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }
            }
            )
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}
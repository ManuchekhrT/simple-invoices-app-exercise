package tj.test.simpleinvoicestakehome.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {
    /*
       As you can see, whenever there is an error, we can send the log to an online service such as
       Firebase CrashAnalytics or Crashlytics and not logging out on production.
     */
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
            //SEND ERROR REPORTS TO YOUR Crashlytics.
        }
    }
}
package pgreze.dagger2.ui.player

import android.content.Context
import android.widget.Toast

import javax.inject.Inject

import pgreze.dagger2.di.AndroidLifecycleScope

@AndroidLifecycleScope
class PlayerPresenter @Inject constructor(
        private val context: Context
) {
    enum class Status {
        PLAY, PAUSE, STOP
    }

    var status = Status.STOP
        private set

    fun onIndicatorClick(): Status {
        // Resolve new status
        val newStatus = if (status == Status.PLAY) Status.PAUSE else Status.PLAY

        // Process status
        Toast.makeText(context, "Process " + newStatus, Toast.LENGTH_SHORT).show()
        this.status = newStatus

        // Return new status
        return status
    }
}

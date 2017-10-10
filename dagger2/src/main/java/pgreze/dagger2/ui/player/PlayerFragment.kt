package pgreze.dagger2.ui.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import javax.inject.Inject

import pgreze.dagger2.R
import pgreze.dagger2.di.HasComponent
import pgreze.dagger2.ui.ActivityComponent

import pgreze.dagger2.ui.player.PlayerPresenter.Status

class PlayerFragment : Fragment() {

    @Inject
    internal lateinit var playerPresenter: PlayerPresenter

    private lateinit var statusBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HasComponent<ActivityComponent>).component.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_player, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBtn = view.findViewById<Button>(R.id.status_btn)
        statusBtn.setOnClickListener { v ->
            val status = playerPresenter.onIndicatorClick()
            updateStatusBtn(status)
        }

        updateStatusBtn(playerPresenter.status)
    }

    fun updateStatusBtn(status: Status) {
        when (status) {
            PlayerPresenter.Status.PLAY -> statusBtn.text = "Pause"
            else -> statusBtn.text = "Play"
        }
    }
}

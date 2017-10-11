package pgreze.dagger2.ui.hello

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

class HelloFragment : Fragment() {

    @Inject
    internal lateinit var presenter: HelloPresenter

    private lateinit var helloBtn: Button

    var helloText : CharSequence = ""
        get() = helloBtn.text
        set(value) {
            field = value
            helloBtn.text = field
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HasComponent<ActivityComponent>).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_hello, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helloBtn = view.findViewById<Button>(R.id.hello_btn)
        helloBtn.setOnClickListener { _ -> presenter.onHelloClick() }
    }
}

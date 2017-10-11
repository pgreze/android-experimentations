package pgreze.dagger2.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pgreze.dagger2.R
import pgreze.dagger2.app.Dagger2App
import pgreze.dagger2.di.AndroidLifecycleScope
import pgreze.dagger2.di.HasComponent

class MainActivity : AppCompatActivity(), HasComponent<ActivityComponent> {

    /**
     * This component is [AndroidLifecycleScope] annotated,
     * so it will be tight to the activity lifecycle.
     *
     * In other words, when activity will be destroyed,
     * all [AndroidLifecycleScope] objects will be destroyed as well (no memory leak).
     */
    override val component: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent(Dagger2App.getInstance(this).component)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}

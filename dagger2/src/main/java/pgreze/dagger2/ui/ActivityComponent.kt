package pgreze.dagger2.ui

import dagger.Component
import pgreze.dagger2.app.AppComponent
import pgreze.dagger2.di.AndroidLifecycleScope
import pgreze.dagger2.ui.hello.HelloFragment
import pgreze.dagger2.ui.player.PlayerFragment

/**
 * Component used by [MainActivity] dependents (see inject methods).
 *
 * Allowed dependencies are:
 *  * parent components objects: [AppComponent]
 *  * same scope dependencies: [AndroidLifecycleScope]
 *  * non scoped objects
 */
@AndroidLifecycleScope // <- Current scope
@Component(
        dependencies = arrayOf(AppComponent::class) // <- Parent components
        //modules = arrayOf() // <- Current component modules
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HelloFragment)
    fun inject(fragment: PlayerFragment)
}

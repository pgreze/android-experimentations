package pgreze.dagger2.di

interface HasComponent<out C> {
    val component: C
}

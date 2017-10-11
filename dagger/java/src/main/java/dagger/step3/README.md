# Step 3: module

Now we're using 2 closed-source SDKs:

- music: all classes are using constructor provided dependencies,
but without @Inject annotation :(
 
- hardware: old-school SDKs, 3 singletons are available and 
we should not forget to invoke init methods with good arguments.


See both MusicModule and HardwareModule to see how to declare them
as Dagger friendly dependencies.

AppComponent is still creating Application but our new modules now.

# Hello gomobile

2 ways of building a go mobile library:

- via command line (recommended)
- via gradle plugin

# Init

NDK should be installed via SDK manager.

Gomobile init Time: around 2mn.

```
# Define hello folder as custom GOPATH (not default $USER/go)
cd hello/
export GOPATH=$(pwd)
# Install and init gomobile
go get -v golang.org/x/mobile/cmd/gomobile
# Add gomobile to PATH
export PATH=$(go env GOPATH)/bin:$PATH
# Init gomobile wih Android NDK
gomobile init -v -ndk /usr/local/opt/android-sdk/ndk-bundle/
```

# Command line

Time: around 7sec.

```
# Define env
export ANDROID_HOME=/usr/local/opt/android-sdk/
# Build our lib
# Default output: module folder
# Gradle way: build/outputs/aar/hello.aar
# To import from app/libs folder: ../app/libs/hello.aar
gomobile bind -v -target=android -classpath fr.pgreze.golib -o ../goapp/libs/golib.aar github.com/pgreze/golib
```

# Gradle plugin

Time: 14sec (build + gradle time).

Execute in **root folder**!

```
# Generate aar
./gradlew gomobile:golib:gobind
# Gradle plugin generate aar in module folder.
# So, move result to app/libs folder
mv gomobile/golib/hello.aar gomobile/app/libs/
```

Note: not working as a gradle dependency:

```
compile project(":hello")
```

# App module

Gradle config:

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
dependencies {
    compile (name: "hello", ext: "aar")
}
```

Aar info:

- Size: 2.8M
- Support: armeabi-v7a, arm64-v8a, x86, x86_64
- Lib name: libgojni.so

# Sources

- [bind example](https://github.com/golang/mobile/tree/master/example/bind)
- [Command gobind](https://godoc.org/golang.org/x/mobile/cmd/gobind)

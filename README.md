# gdx-jam-template

This is just a quick template that includes Java launchers, some fonts for a few different genres, and the platforms
LWJGL3 (for desktop) and GWT (for web support).

Because **this is a template**, you will normally use it by forking it (if you don't already have access to it) and then
creating a **new repo** where you just select `gdx-jam-template` in the first dropdown for a template.
You can change packages to whatever you want after forking. You will probably want to find-and-replace any usages of the
name used for the template itself, `JamTemplate`, and replace it with the name of your game, like `MarchJam2025`.

Several dependencies are included by default; you can remove any you don't want to use before distributing.

- TextraTypist is used by the default behavior and fonts compatible with it are included here.
- RegExodus, the one dependency of TextraTypist, is used here for regular expressions that work on TeaVM the same as desktop.
- Box2D and Box2D-Lights are included by default, for physics and 2D lighting respectively.
- Gand is included by default (for pathfinding), along with its dependency gdcrux (which expands on point types like Vector2) and crux (which probably won't be used much on its own, but provides interfaces for points).
- Cringe is included by default for any random number, continuous noise, unique identifier, or other random tasks.

The default behavior is just to semi-randomly select a TextraTypist Font from four predefined ones, and write some
sample text with markup.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.
- `html`: Web platform using GWT and WebGL. Supports only Java projects.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `html:dist`: compiles GWT sources. The compiled application can be found at `html/build/dist`: you can use any HTTP server to deploy it.
- `html:superDev`: compiles GWT sources and runs the application in SuperDev mode. It will be available at [localhost:8080/html](http://localhost:8080/html). Use only during development.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

# `MindustryModTemplate`

[Mindustry](https://github.com/Anuken/Mindustry) Java mod template, complete with
[`EntityAnno`](https://github.com/GlennFolker/EntityAnno) and syntax downgrader
integration, works for both Android and PC.

## Using

Before going into using this template, be aware that a fair amount of Java knowledge
and Git *(GitHub Desktop is fine, but `git` CLI is a million times better)* is
**highly beneficial**. Going in blind isn't impossible, but you'll face a lot of
problems. Not that people on [the Discord](https://discord.gg/mindustry) won't help,
though, so be communicative!

1. Install JDK 17 or higher. Java 16 or lower will *not* work!
2. Click the `Use this template` button and create your repository.
3. Clone a local copy of the repository.

> [!IMPORTANT]
> A **local copy** is *not* a mere ZIP archive you obtain by downloading. This is
> where the Git knowledge comes to play. If you have GitHub CLI or GitHub Desktop
> installed, the site should provide instructions on how to use either, under the
> `<> Code` menu.
>
> `Download ZIP` is **not** a proper way to clone your repository.

4. Refactor namings to your preferences. The template is designed in such a way
   that this step should only require you to modify:
   - `gradle.properties`, the "Project configurations" section. For the "package"
     properties, if you don't know what you're doing, simply just change `template`
     to your preferred mod root package naming *(e.g. `mymod`, or `confictura` if
     your mod name is "confictura")*.
   - `mod.json`, which is the entire metadata of your mod.
   - `src/` folder and its contents, which is where your Java source files are
     stored. Rename folders, package, and class names as you prefer. Note that
     the main mod class' full name *(package + class)* must correspond to the
     `main` property in `mod.json`.
   - `.github/workflows/ci.yml`, which is the automated Continuous Integration
     that runs on your GitHub repository everytime you push a commit. This
     automates cross-platform builds which you might find useful. You should
     only edit the last 2 lines about `name` and `path`.

> [!NOTE]
> The project Gradle scripts check the correspondence of the `modName` property in
> `gradle.properties` and the `name` property in `mod.json`. These *must* be the
> same, but doesn't deliberately edit it out for you since potential loss of
> progress can be really annoying.
>
> In the case of a discrepancy, you should get a warning like this when compiling:
> ```
> Mod name mismatch: 'hello' (mod.json) != 'world' (gradle.properties)
> ```

5. Happy hacking! Refer to the **Building** section on how to deploy the mod.

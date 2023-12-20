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

1. Install JDK 17 or higher. Plain or terminal-based code editors are **completely
   *not* recommended!** Use an IDE like [IntelliJ IDEA](
   https://www.jetbrains.com/idea/download/); there are free Community Edition
   releases available, just scroll down a bit.
3. Click the `Use this template` button and create your repository.
4. Clone a local copy of the repository.

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

   Here's an example of a properly configured mod base from the template, assuming
   "confictura" as the name:
   ```mermaid
   ---
   title: Project Hierarchy
   ---
   graph LR;
   %%{init:{'flowchart':{'nodeSpacing': 10, 'rankSpacing': 10}}}%%;
   
   classDef folder fill:#465768,stroke:#bdcedf;
   classDef file fill:#468868,stroke:#bdffdf;

   root{{"/"}};
   github{{".github"}};
   workflows{{"workflows/"}};
   assets{{"assets/"}};
   gradle{{"gradle/"}};
   wrapper{{"wrapper/"}};
   src{{"src/"}};
   confictura{{"confictura"}};
   class root,github,workflows,assets,gradle,wrapper,src,confictura folder;

   ci(["ci.yml"]);
   wrapper-jar(["gradle-wrapper.jar"]);
   wrapper-prop(["gradle-wrapper.properties"]);
   main(["ConficturaMod.java"]);
   ignore([".gitignore"]);
   readme(["README.md"]);
   build(["build.gradle"]);
   prop(["gradle.properties"]);
   wrapper-unix(["gradlew"]);
   wrapper-windows(["gradlew.bat"]);
   icon(["icon.png"]);
   meta(["mod.json"]);
   settings(["settings.gradle"]);
   class ci,main,prop,meta file;

   root-->github-->workflows-->ci;
   root-->assets;
   root-->gradle-->wrapper-->wrapper-jar & wrapper-prop;
   root-->src-->confictura-->main;
   root-->ignore & readme & build & prop & wrapper-unix & wrapper-windows & icon & meta & settings;
   ```

   Here are the changes made to files highlighted above; red denotes removals, green
   denotes additions:
   `./github/workflows/ci.yml`:
   ```diff
   ...
          uses: actions/upload-artifact@v3
          with:
   -        name: ModTemplate (zipped)
   +        name: Confictura (zipped)
   -        path: build/libs/ModTemplate.jar
   +        path: build/libs/Confictura.jar
   ```

   `src/confictura/ConficturaMod.java`:
   ```diff
   - package template;
   + package confictura;

     import mindustry.mod.*;
   - import template.gen.*;
   + import confictura.gen.*;

   - public class ModTemplate extends Mod{
   + public class ConficturaMod extends Mod{
         @Override
         public void loadContent(){
             EntityRegistry.register();
         }
     }
   ```

   `gradle.properties`:
   ```diff
     ##### Project configurations.
     # The mod's internal name, corresponds to `name` in `mod.json`.
   - modName = mod-template
   + modName = confictura
     # The mod's fetched entity sources package.
   - modFetch = template.fetched
   + modFetch = confictura.fetched
     # The mod's input entity sources package.
   - modGenSrc = template.entities.comp
   + modGenSrc = confictura.entities.comp
     # The mod's generated sources package.
   - modGen = template.gen
   + modGen = confictura.gen
     # The mod's JAR file name. Desktop build is suffixed with `Desktop`.
   - modArtifact = ModTemplate
   + modArtifact = Confictura
   ...
   ```

   `mod.json`:
   ```diff
     {
   -     "name": "mod-template",
   +     "name": "confictura",
   -     "displayName": "Mod Template",
   +     "displayName": "Confictura",
   -     "description": "Mindustry Java mod template, complete with EntityAnno and syntax downgrader integration.",
   +     "description": "Dive into the past of a trauma-driven uprising.",
         "version": "1.0",
         "minGameVersion": "146",
         "author": "You",
         "java": true,
   -     "main": "template.ModTemplate"
   +     "main": "confictura.ConficturaMod"
     }
   ```

> [!NOTE]
> The project Gradle scripts check the correspondence of the `modName` property in
> `gradle.properties` and the `name` property in `mod.json`. These *must* be the
> same, but doesn't deliberately edit it out for you since potential loss of
> progress can be really annoying.
>
> In the case of a discrepancy, you should get an error like this when compiling:
> ```
> Mod name mismatch: 'hello' (mod.json) != 'world' (gradle.properties).
> ```

> [!TIP]
> The mod metadata file may be `mod.json` or `mod.hjson`, **but not both**. In case
> of a duplicate like that, you should get an error like this when compiling:
> ```
> Ambiguous mod meta: both `mod.json` and `mod.hjson` exist.
> ```

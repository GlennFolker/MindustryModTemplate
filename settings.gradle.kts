pluginManagement{
    repositories{
        gradlePluginPortal()
        maven("https://maven.pkg.github.com/GlennFolker/EntityAnno").credentials{
            // "Obfuscated" token for reading packages, since GitHub blocks tokens shared publicly for no reason.
            //
            // Do NOT deobfuscate this token (i.e., converting these 4 bytes into characters and writing them in the
            // source file) as that will get GitHub to block it, and make *literally everybody* unable to pull the
            // public packages.
            //
            // Alternatively, you could create your own GitHub classic Personal Access Token with `packages:read`
            // permission (and *only* that) and copy the string following the `ghp_` prefix, and replace the one
            // following the 4 bytes below with it.
            username = "GitHub Packages"
            password = "${String(byteArrayOf(0x67, 0x68, 0x70, 0x5f), Charsets.UTF_8)}o9b0qcWBMqta7TOpN0BggXBJtsYwyc4N4D0O"
        }
    }

    plugins{
        val entVersion: String by settings
        id("com.github.GlennFolker.EntityAnno") version(entVersion)
    }
}

if(JavaVersion.current().ordinal < JavaVersion.VERSION_17.ordinal){
    throw IllegalStateException("JDK 17 is a required minimum version. Yours: ${System.getProperty("java.version")}")
}

val modName: String by settings
rootProject.name = modName

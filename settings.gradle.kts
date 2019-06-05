include (Modules.app, Modules.nomics, Modules.domain, Modules.news, Modules.newsFeature, Modules.buildSrc, Modules.common_core, Modules.common_network, Modules.common_android)
project(Modules.common_network).projectDir = File("common/network")
project(Modules.common_core).projectDir = File("common/core")
project(Modules.common_android).projectDir = File("common/android")
rootProject.name = "Sandbox"
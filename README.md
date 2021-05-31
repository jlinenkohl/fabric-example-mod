# fabric-example-mod

## About
This mod was started from the base fabric-example-mod template here:
https://github.com/FabricMC/fabric-example-mod

Forked from this release on March 27, 2021:
https://github.com/FabricMC/fabric-example-mod/commit/29c522536fc16233833221e22eed3f106c0726bc

I was mainly tinkering and learning how to mod in FabricMC, so this is a rollup of a number of examples.  It is not creatively named, does not have the right versioning and doesn't have its own package namespace.  All things that I should change, but this started as a tutorial journey.

A little timeline of what I added:
  - A custom item following the Fabric Tutorial (https://fabricmc.net/wiki/tutorial:items)
  - Fiery Chickens
  - Exploding Pigs
  - A custom '/hub' command per a request from MrDPL (with static coordinates and a specific Yaw, not elegant)
  - Exploding Snowballs

At one point I did try to merge the upstream changes on May 15 moving to Gradle 7.0.2 and a newer Loom and Yarn but that broke things.  I think it has to do with the Yarn mappings and something I need to do in my build environment.  There is a specific migration process in maintaining Fabric code over time that I am not yet 100% clear on.

### Update Gradle to 7.0, Bump CI stuff (#95)
https://github.com/FabricMC/fabric-example-mod/pull/95
https://github.com/FabricMC/fabric-example-mod/commit/62a3d3ce3b205e8f2067839d70bc61cc44f5395b

### Updating Yarn mappings in a Java codebase
https://fabricmc.net/wiki/tutorial:migratemappings


## Setup
This mod was created for Fabric Loader (i.e. FabricMC), so you need Fabric installed. This has been tested under Fabric Loader 0.11.3 and fabric-api-0.34.2+1.16.

To use the mod, copy the .jar (mod) to your minecraft instance 'mods' folder. Note: the mod will also require fabric-api-0.34.x for Minecraft 1.16.

Fabric Installer 0.7.3:

https://maven.fabricmc.net/net/fabricmc/fabric-installer/0.7.3/fabric-installer-0.7.3.jar

Get Fabric API:

https://github.com/FabricMC/fabric/releases/download/0.34.6%2B1.16/fabric-api-0.34.2+1.16.jar


## License
This mod is provided under the CC0 license. Feel free to learn from it and incorporate it in your own projects.


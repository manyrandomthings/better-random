# Better Random

Server-side fabric mod that improves randomness by replacing the default random number generator

By default, `Xoroshiro128PlusPlus` is used. [Full list of possible algorithms here.](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html#algorithms)

### Features:
* Helps prevent RNG cracking for [enchanting](https://youtu.be/hfiTZF0hlzw), [fishing](https://youtu.be/-kJoNBWDBn0), etc
* Enchanting uses a 64 bit seed instead of 32 bit
* Creating a world with a random seed generates 64 bit seeds instead of 48 bit
* Containers with `LootTableSeed` use the full 64 bit seed instead of just the bottom 48 bits

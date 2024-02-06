# Adventurer

An implementation of a basic game where, given a map, an initial position, and a set of directions, a hero should be able to walk throught the forest, avoid obstacles and finish in a final position

### Uploading a map :

To upload a map, use the `Forest(String path)` constructor. The path should be either an absolute path, or a path to a file in the /app/src folder. Be sure to include the new file changes in the dockerfile as well in case of choosing another file, or simply add the new file in the same path as the old file under the same name "carte.txt".

An InvalidForestFileException is thrown in case of an error

### Adventurer :

To instinciate an adventurer, use the `Adventurer(int x, int y, Forest forest)` constructor, where x and y are his initial coordinates of adventurer, and the Forest is where he will be. An InvalidPositionException is thrown if the coordinates are out of range, or already occupied by a tree '#'.

Use the advance(directions) method to move the adventurer in the given directions, and you can print the hero to see where he stands on the map.

### Build a docker image

to build the docker image, run the command :

```bash
docker build -t <image-name> .
```

to run your image :

```bash
docker run <image-name> -p -d
```

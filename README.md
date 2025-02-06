Run `./run.sh` to spin the build until it fails. It takes ~20 iteration max for me. Make sure you have enough gradle
workers (`org.gradle.workers.max` property).

Can be run with a custom kotlin version:
```
./run.sh 2.0.21
```

The number of projects can be changed if needed:
```
./run.sh 2.0.21 64
```
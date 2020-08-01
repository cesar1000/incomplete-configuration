# Configuration containing a provided configuration are not resolved correctly

This is a repro for an issue where a configuration is not resolved correctly if it contains a configuration that's obtained from a `provider`:

```
implementation(provider { myConfiguration })
```

Run this to check the behavior when the configuration is added directly:

```
$ ./gradlew :publishToMavenLocal
```

This is the resulting scan: https://scans.gradle.com/s/bmh7tqku6qrxq/dependencies?toggled=W1swXSxbMCwzXSxbMCwwXV0

And then run the following to add the configuration through a provider:

```
$ ./gradlew :publishToMavenLocal -Plazy
```

This is the scan: https://scans.gradle.com/s/2cpbyg7on7vgs/dependencies?toggled=W1swXSxbMCwwXSxbMCw0XSxbMCwzXV0

Also, compare the resulting metadata:

```
diff  ~/.m2/repository/com/twitter/test/incomplete-configuration/0.1.0-direct/incomplete-configuration-0.1.0-direct.module ~/.m2/repository/com/twitter/test/incomplete-configuration/0.1.0-lazy/incomplete-configuration-0.1.0-lazy.module
```

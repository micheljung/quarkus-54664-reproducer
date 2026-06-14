# Reproducer for Quarkus issue #54664

Reproducer attempt for [quarkus#54664](https://github.com/quarkusio/quarkus/issues/54664):
any call to a `@RegisterRestClient` interface fails with `IllegalStateException: Trying to use a StorkClientRequestFilter but the quarkus-smallrye-stork extension is missing` in Quarkus 3.36.0.

## Project structure

This is a 3-module Gradle project mirroring the reporter's multi-module setup:

- **`security`** — library module with `quarkus-security`, `quarkus-rest-client-jackson` (mirrors `security-quarkus` module)
- **`rest-client`** — library module with `quarkus-rest-client-jackson`, depends on `:security`
- **`app`** — final Quarkus application, depends on `:rest-client`

Both `:security` and `:rest-client` apply the `io.quarkus` plugin (for Jandex indexing / code generation) but disable `quarkusBuild` and `quarkusAppPartsBuild` — they are library modules.

## How to run

```bash
./gradlew :app:test
```

The test injects `PingClient` and calls `ping()`. On the affected system this would fail with `IllegalStateException` about `StorkClientRequestFilter`.

## Notes

- No `quarkus-smallrye-stork` on the classpath
- The error occurs at REST client build time (`RestClientBuilderImpl.build()`) when iterating `AnnotationRegisteredProviders`
- `StorkClientRequestFilter` somehow ends up in the combined Jandex index in the reporter's 14-module project but not in this simplified reproducer

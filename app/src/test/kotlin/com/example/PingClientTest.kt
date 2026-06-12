package com.example

import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@QuarkusTest
class PingClientTest {

    @Inject
    @RestClient
    lateinit var pingClient: PingClient

    @Test
    fun `REST client call succeeds without quarkus-smallrye-stork on classpath`() {
        // On Quarkus 3.36.0, this throws:
        // IllegalStateException: Trying to use a StorkClientRequestFilter but the
        // quarkus-smallrye-stork extension is missing, please add the extension.
        val result = pingClient.ping()
        assertEquals("pong", result)
    }
}

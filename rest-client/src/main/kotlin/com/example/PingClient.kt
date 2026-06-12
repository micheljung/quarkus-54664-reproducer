package com.example

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(configKey = "ping-service")
interface PingClient {

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping(): String
}

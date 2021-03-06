/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.common.rest.api.service;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.syncope.common.lib.OIDCConstants;
import org.apache.syncope.common.lib.to.OIDCLoginRequestTO;
import org.apache.syncope.common.lib.to.OIDCLoginResponseTO;
import org.apache.syncope.common.lib.to.OIDCLogoutRequestTO;
import org.apache.syncope.common.rest.api.RESTHeaders;

/**
 * REST operations for OpenID Connect Clients.
 */
@Tag(name = "OIDCClients")
@SecurityRequirements({
    @SecurityRequirement(name = "BasicAuthentication"),
    @SecurityRequirement(name = "Bearer") })
@Path("oidcclient/clients")
public interface OIDCClientService extends JAXRSService {

    /**
     * Generates OpenID Connect authentication request for the Provider matching the provided op.
     *
     * @param redirectURI redirect URI
     * @param op OpenID Connect Provider
     * @return OpenID Connect authentication request
     */
    @POST
    @Path("loginRequest")
    @Produces({ MediaType.APPLICATION_JSON, RESTHeaders.APPLICATION_YAML, MediaType.APPLICATION_XML })
    OIDCLoginRequestTO createLoginRequest(
            @QueryParam(OIDCConstants.REDIRECT_URI) String redirectURI,
            @QueryParam(OIDCConstants.OP) String op);

    /**
     * Uses the provided authorization code to go through the OpenID Connect tokens process and finally creates JWT for
     * the matching user, if found.
     *
     * @param redirectURI redirect URI
     * @param authorizationCode authorization code generated by the remote OpenID Connect Provider
     * @param op OpenID Connect Provider
     * @return JWT for the matching user plus attributes returned in the response
     */
    @POST
    @Path("login")
    @Produces({ MediaType.APPLICATION_JSON, RESTHeaders.APPLICATION_YAML, MediaType.APPLICATION_XML })
    OIDCLoginResponseTO login(
            @QueryParam(OIDCConstants.REDIRECT_URI) String redirectURI,
            @QueryParam("authorizationCode") String authorizationCode,
            @QueryParam(OIDCConstants.OP) String op);

    /**
     * Returns the endSession endpoint for the provided op.
     *
     * @param op OpenID Connect Provider
     * @return endSession endpoint for the provided op
     */
    @POST
    @Path("logout")
    @Produces({ MediaType.APPLICATION_JSON, RESTHeaders.APPLICATION_YAML, MediaType.APPLICATION_XML })
    OIDCLogoutRequestTO createLogoutRequest(@QueryParam(OIDCConstants.OP) String op);

}

/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.viewservices.serverauthor.server.spring;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.viewservices.serverauthor.api.rest.ServerAuthorConfigurationResponse;
import org.odpi.openmetadata.viewservices.serverauthor.services.ServerAuthorViewRESTServices;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ConfigViewServicesResource provides the configuration for setting up the Open Metadata View
 * Services (OMVSs).
 */
@RestController
@RequestMapping("/servers/{serverName}/open-metadata/view-services/server-author/users/{userId}/servers/{serverToBeConfiguredName}")

@Tag(name="Server Author OMVS", description="The Server Author OMVS is for user interfaces supporting the creating and editing of OMAG Server Configuration Documents.",
     externalDocs=@ExternalDocumentation(description="Further information",
                                         url="https://egeria-project.org/services/omvs/server-author/overview"))

public class ConfigViewServicesViewResource
{
    private final ServerAuthorViewRESTServices adminAPI = new ServerAuthorViewRESTServices();



    /**
     * Enable a single view service.
     *
     * @param userId                   user that is issuing the request.
     * @param serverName               local server name.
     * @param serverToBeConfiguredName name of the server to be configured.
     * @param viewServiceOptions  property name/value pairs used to configure the view services
     * @param serviceURLMarker string indicating which view service it is configuring
     * @return the current stored configuration or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGConfigurationErrorException the event bus has not been configured or
     * OMAGInvalidParameterException invalid serverName parameter.
     */
    @PostMapping(path = "/view-services/{serviceURLMarker}")
    public ServerAuthorConfigurationResponse configureViewService(@PathVariable                   String              userId,
                                               @PathVariable                   String              serverName,
                                               @PathVariable                   String serverToBeConfiguredName,
                                               @PathVariable                   String              serviceURLMarker,
                                               @RequestBody(required = false)  Map<String, Object> viewServiceOptions)
    {
        return adminAPI.configureViewService(userId, serverName, serverToBeConfiguredName, serviceURLMarker, viewServiceOptions);
    }


    /**
     * Disable a single view service.
     *
     * @param userId                   user that is issuing the request.
     * @param serverName               local server name.
     * @param serverToBeConfiguredName name of the server to be configured.
     * @param serviceURLMarker string indicating which view service it is configuring
     * @return the current stored configuration or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGConfigurationErrorException the event bus has not been configured or
     * OMAGInvalidParameterException invalid serverName parameter.
     */
    @DeleteMapping(path = "/view-services/{serviceURLMarker}")
    public ServerAuthorConfigurationResponse disableViewService(    @PathVariable                   String              userId,
                                                                    @PathVariable                   String              serverName,
                                                                    @PathVariable                   String serverToBeConfiguredName,
                                                                    @PathVariable                   String              serviceURLMarker)
    {
        return adminAPI.disableViewService(userId, serverName, serverToBeConfiguredName, serviceURLMarker);
    }

}

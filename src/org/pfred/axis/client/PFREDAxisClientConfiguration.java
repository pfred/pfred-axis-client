/*
 *  PFRED: A computational tool for siRNA and antisense design
 *  Copyright (C) 2011 Pfizer, Inc.
 *
 *  This file is part of the PFRED software.
 *
 *  PFRED is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pfred.axis.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import org.pfred.service.PFREDServiceHttpBindingStub;
import org.pfred.service.PFREDServiceLocator;
import org.pfred.service.PFREDServicePortType;


public class PFREDAxisClientConfiguration {

    private static final Logger logger = Logger.getLogger(PFREDAxisClientConfiguration.class.getName());
    private static PFREDAxisClientConfiguration instance;
    private static String wsEndpoint;

    //one hour request time out
    public static final int TIMEOUT_IN_MILISECONDS =   60*60* 1000;
    /**
     * ****************** Constructors ************************
     */
    private PFREDAxisClientConfiguration() {
    }

    public static PFREDAxisClientConfiguration getInstance() {
        if (null == instance) {
            instance = new PFREDAxisClientConfiguration();
        }
        return instance;
    }

    public static PFREDAxisClientConfiguration getInstance(String wsEndpoint) {
        if (null == instance) {
            instance = new PFREDAxisClientConfiguration();
        }
        instance.setWSEndPoint(wsEndpoint);
        return instance;
    }

    public String getWSEndpoint() {
        return wsEndpoint;
    }

    public void setWSEndPoint(String endpoint) {
        wsEndpoint = endpoint;
    }

    public PFREDServicePortType getServicePortType() throws ServiceException {
        PFREDServiceLocator locator = new PFREDServiceLocator();
        locator.setPFREDServiceHttpPortEndpointAddress(getWSEndpoint());
        PFREDServicePortType portType = locator.getPFREDServiceHttpPort();
        logger.log(Level.INFO, "Service End Point: " + getWSEndpoint());
        
        ((PFREDServiceHttpBindingStub) portType).setTimeout(TIMEOUT_IN_MILISECONDS);
        logger.log(Level.INFO, "Customized Timeout: " + ((PFREDServiceHttpBindingStub) portType).getTimeout()/1000 + "s");
    
        return portType;
    }
}

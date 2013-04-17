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

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import org.pfred.service.PFREDServicePortType;

public class PFREDAxisClient {

    private static final Logger logger = Logger.getLogger(PFREDAxisClient.class.getName());

    public static String getOrthologs(String runName, String enseblID, String requestedSpecies, String species) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "Getting orthologs...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();
        String orthologs = service.getOrthologs(runName, enseblID, requestedSpecies, species);
        logger.log(Level.INFO, "Getting orthologs...done");
        return orthologs;
    }

    /**
     * returns enumeration result and sequence
     * @param runName
     * @param secondaryTranscriptIDs
     * @param primaryTranscriptID
     * @param oligoLen
     * @return String array of two elements, first one is enumeration result, second one is sequence
     * @throws ServiceException
     * @throws RemoteException 
     */
    public static String[] enumerate(String runName, String secondaryTranscriptIDs, String primaryTranscriptID, String oligoLen) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "enumerate...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();

        String[] results = service.enumerate(runName, secondaryTranscriptIDs, primaryTranscriptID, oligoLen);

        logger.log(Level.INFO, "enumerate...done");
        return results;
    }

    public static String runAntisenseOffTargetSearch(String runName, String species, String IDs, String missMatches) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "runAntisenseOffTargetSearch...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();
        String result = service.runAntisenseOffTargetSearch(runName, species, IDs, missMatches);
        logger.log(Level.INFO, "runAntisenseOffTargetSearch...done");
        return result;
    }

    public static String runsiOffTargetSearch(String runName, String species, String IDs, String missMatches) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "runsiOffTargetSearch...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();
        String result = service.runsiOffTargetSearch(runName, species, IDs, missMatches);
        logger.log(Level.INFO, "runsiOffTargetSearch...done");
        return result;
    }

    public static String runAntisenseActivityModel(String runName, String primarySequence, String oligoLen) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "runAntisenseActivityModel...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();
        String result = service.runAntisenseActivityModel(runName, primarySequence, oligoLen);
        logger.log(Level.INFO, "runAntisenseActivityModel...done");
        return result;
    }

    public static String runsiActivityModel(String runName, String primarySequence) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "runsiActivityModel...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();
        String result = service.runsiActivityModel(runName, primarySequence);
        logger.log(Level.INFO, "runsiActivityModel...done");
        return result;
    }

    public static void cleanRunDir(String runName) throws ServiceException, RemoteException {
        logger.log(Level.INFO, "cleanRunDir...");
        PFREDServicePortType service = PFREDAxisClientConfiguration.getInstance().getServicePortType();
        service.cleanRunDir(runName);
        logger.log(Level.INFO, "cleanRunDir...done");

    }
}

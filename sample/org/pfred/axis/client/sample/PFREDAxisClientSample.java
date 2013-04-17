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
package org.pfred.axis.client.sample;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pfred.axis.client.PFREDAxisClient;
import org.pfred.axis.client.PFREDAxisClientConfiguration;

public class PFREDAxisClientSample {

    private static final Logger logger = Logger.getLogger(PFREDAxisClientSample.class.getName());
    
    private static final String dir = String.valueOf(System.currentTimeMillis());
    private static final String seq = "ATCAGGCGAGAGGCGGAGCCACCCGGCGTGCCCCGCCCCCGCGGTAGAAAGGGTTGGGCG\n" +
"AGTGTTACTCGCGGTCAGGGCTGGGACCTTTTATCTGTGCTGCTGGAGGAGGTAGGAGGA\n" +
"GGAGACATCAGGGGTGGTCCTGGGCGCCTGGGACACCTTTCCCGGACTATAAATTGAGCA\n" +
"CCTGGAATGGGCAGGGGGCCGGAGCAACCACAGTCGCCCTTACTCACAGTCCGATCAGTG\n" +
"ACCGCAGCAGCGCCCTTGGGCAGCCACCGTCCGCAACGCAAGCACTGAGAACCAGGGGAT\n" +
"TTCGCAGTGCAAGAGCAGAAAAAGGCTAGACCCAGCCACCCACCGTCAATCCTGAGCCAA\n" +
"AGATAAGAGCAGCCGGGCCTCACGAAGGGCTGAGCTGAGAAAGAAGCAAGTTAGAGAGGG\n" +
"CGGAGAAGGATCTGGGAATCCCGTCACACCGGCTTCAAGCAGGCTCCCGGCATCAGCCTC\n" +
"TGAGAGCGCTTGAAGGCGGCATCGCCAGCGGTCTATCTCCGTGTACCAGCGTCCCTGTGT\n" +
"TTCCGCGCCCGCTCGGCCACCATGATGCAAATCTGCGACACATATAACCAGAAGCACTCG\n" +
"CTCTTTAACGCCATGAATCGCTTCATTGGCGCGGTGAACAACATGGACCAGACGGTGATG\n" +
"GTGCCCAGTCTGCTGCGCGACGTACCCCTGTCCGAGCCGGAGATAGACGAGGTCAGCGTG\n" +
"GAGGTAGGCGGCAGTGGCGGCTGCCTGGAGGAGCGCACGACCCCGGCCCCAAGCCCGGGC\n" +
"AGCGCCAATGAAAGCTTTTTCGCGCCCTCCCGGGACATGTACAGCCACTACGTGCTGCTC\n" +
"AAGTCCATCCGCAATGATATCGAGTGGGGAGTCCTGCACCAGCCTTCGTCTCCGCCGGCC\n" +
"GGGAGCGAGGAGAGCACCTGGAAGCCCAAGGACATCCTGGTGGGCCTGAGTCACTTGGAG\n" +
"AGCGCGGATGCGGGCGAGGAAGATCTGGAGCAGCAGTTCCACTACCACCTGCGCGGGCTG\n" +
"CACACCGTGCTCTCCAAACTCACCCGAAAAGCCAACATCCTCACCAATAGATACAAGCAG\n" +
"GAGATCGGCTTCAGTAATTGGGGCCACTGAGGCGGGGGCTGTCCCCGCTGCCCAGCACCC\n" +
"TCTCTCGGGTCGGCTCTACCACCCCTCTCTTTCCTCCAAGCTATTTTCTTCCTGGTTGTG\n" +
"GGGCGCGAAGGGCACACTGTAAAGTTGGGCTGTGTACTTGGTGGGGTTAGTGTGGAGAAG\n" +
"AGGGCCTCATCGCGAGAGCAGAGGAAAGTAGTCGCCAGAGAGGGGGGTTCAAAGACCCCC\n" +
"GGAGGGGGCCTACTCTGTGTTGGTGGGAATGGAACTGGGCCGATGTCCTTCATTCAGCCT\n" +
"GTGCCTTTCTTGGGGTTTCTTTTCTGTTTTTCTTTCCGGAAGAGAAGGGCCTGAGAAAGG\n" +
"GCCATGCCAGGGCACAGTGCTGGGTTGCCACACATGGGAGGGCAGCTTCTAGCCGGGTGC\n" +
"TTGGGGGAGGCGGGGCTCAGCCTCCTGCTGCCCTGCCTTGAGCTGCCAGAGGAGGCCTTG\n" +
"GCGTTGCTAGGATTGCGTCAGTTTTCCTGTTTGCACTATTTCTTTTTGTAACAGTGACCC\n" +
"TGTCTTAAGTCTTTCAGATCTCTTTGCTTTGAAACTTCGTCGATTCCATTGTGATAAGCG\n" +
"CACAAACAGCACTGTTGGTAACCGGTACTACTTTATTAATGATTTTCTGTTACACTGTAC\n" +
"AGTAGTCCTGTGGCACCCTATCCCTTTCACGCCACCCCTCCCCCGCCCGTGTGTGTAAAC\n" +
"TGGCGATGTGCCAGCTAGGATGAAGCTTGCCACTCGGCTAGCGAAAATAATTAACATTAT\n" +
"TATGAGAAAGTGGATTTATCTAAAGTGGAACCAGCTGACATTATATCTGTATCGTATGGA\n" +
"GAATGATGAAGGGCTCCACTGTTGTTATATGTCTTGTTTATTTAAAACTTTTTTTAATCC\n" +
"AGATGTAGACTATATTCTAAAAAATAAAAACGCAGATGTGTT";

    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                PFREDAxisClientConfiguration.getInstance(args[0]);
                logger.info("Service URL: " + PFREDAxisClientConfiguration.getInstance().getWSEndpoint());
            } else {
                logger.log(Level.WARNING, "Please provide service URL as the only argment");
                System.exit(1);
            }

            testOrthologs("ENSG00000165175", "rat", "human");

            testEnumerate("ENST00000378474,ENST00000336949", "ENST00000336949", 14);

            testAntisenseOffTargetSearch("human", "ENST00000378474", 1);
            
//            testAntisenseActivityModel();
            
//            testSirnaOffTargetSearch();
//            
//            testSirnaActivityModel();

            /*
            FO: Running Shell Command: getOrthologs.sh  ENSG00000165175 human rat
Apr 17, 2013 2:20:08 AM org.pfred.service.PFREDServiceImpl getOrthologs
INFO: Shell command run successfully
Apr 17, 2013 2:20:46 AM org.pfred.service.PFREDServiceImpl logRemoteHost
INFO: enumerate called from 10.134.194.200
Apr 17, 2013 2:20:46 AM org.pfred.service.PFREDServiceImpl runCommandThroughShell
INFO: Running Shell Command: Enumeration.sh ENST00000378474,ENST00000336949 ENST00000336949 14
Apr 17, 2013 2:21:09 AM org.pfred.service.PFREDServiceImpl enumerate
INFO: Shell command run successfully
Apr 17, 2013 2:21:11 AM org.pfred.service.PFREDServiceImpl logRemoteHost
INFO: runAntisenseOffTargetSearch called from 10.134.194.200
Apr 17, 2013 2:21:11 AM org.pfred.service.PFREDServiceImpl runCommandThroughShell
INFO: Running Shell Command: ASOOffTargetSearch.sh human ENST00000378474 1
* */

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Run error", ex);
        }
    }

    private static void testOrthologs(String ensemblID, String requestedSpecies, String species) {
        try {
            String result = PFREDAxisClient.getOrthologs(dir, ensemblID, requestedSpecies, species);
            logger.info(result);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "getorthologs error", ex);
        }
    }

    private static void testEnumerate(String secondaryIDs, String primaryID, int length) {
        try {
            String[] results = PFREDAxisClient.enumerate(dir, secondaryIDs, primaryID, String.valueOf(length));

            if (results != null && results.length >0) {
                for (String theResult : results) {
                    logger.info(theResult);
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "enumerate error", ex);
        }
    }

    private static void testAntisenseOffTargetSearch(String species, String ensemblIDs, int misMatch) {
        try {
            String result = PFREDAxisClient.runAntisenseOffTargetSearch(dir, species, ensemblIDs, String.valueOf(misMatch));
            logger.info(result);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "AntisenseOffTargetSearch Error", ex);
        }

    }
    
    private static void testAntisenseActivityModel() {
        try {
            String result = PFREDAxisClient.runAntisenseActivityModel(dir, seq, "14");
            logger.info(result);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "AntisenseActivityModel Error", ex);
        }

    }
    
    private static void testSirnaOffTargetSearch() {
        try {
            String result = PFREDAxisClient.runsiOffTargetSearch(dir, "human", "ENST00000378474", "2");
            logger.info(result);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "siRNAOffTargetSearch Error", ex);
        }

    }
    
    private static void testSirnaActivityModel() {
        try {
            String result = PFREDAxisClient.runsiActivityModel(dir, seq);
            logger.info(result);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "siRNAActivityModel Error", ex);
        }

    }
}

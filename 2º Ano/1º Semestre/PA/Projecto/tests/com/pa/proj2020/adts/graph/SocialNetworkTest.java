
package com.pa.proj2020.adts.graph;

import patterns.mvc.model.SocialNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {
    private SocialNetwork socialNetwork;

    @BeforeEach
    void setUp() {
        socialNetwork = new SocialNetwork();
    }

    @Test
    void readCsvFile() {
        assertTrue(
                socialNetwork.getFileReader().getSocialMap().keySet().size()!=0);
    }

    @Test
    void insertUsers() {
        // TODO
        /*socialNetwork.insertUsersGlobal();
        assertEquals(10, socialNetwork.getRelationships().numVertices());*/
    }

    @Test
    void getMap() {
        assertTrue(socialNetwork.getFileReader().getSocialMap().keySet().size()!=0);
        assertTrue(socialNetwork.getFileReader().getSocialMap().values().size()!=0);
    }

    @Test
    void insertRelations() {
        // TODO
        /*socialNetwork.insertUsersGlobal();
        socialNetwork.insertRelationsGlobal();
        assertEquals(16, socialNetwork.getRelationships().numEdges());*/
    }

    @Test
    void getRelationships() {
        // TODO
        /*socialNetwork.insertUsersGlobal();
        socialNetwork.insertRelationsGlobal();
        assertEquals(10, socialNetwork.getRelationships().numVertices());
        assertEquals(16, socialNetwork.getRelationships().numEdges());*/
    }
}

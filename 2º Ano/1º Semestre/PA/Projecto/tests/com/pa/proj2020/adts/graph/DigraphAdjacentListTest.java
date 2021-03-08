package com.pa.proj2020.adts.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.mvc.model.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DigraphAdjacentListTest {
    private SocialNetwork socialNetwork;
    private Digraph<User, Relation> adjacentList;
    private User user;
    private User user1;
    private User user2;
    Relation relation;

    @BeforeEach
    void setUp() {
        socialNetwork = new SocialNetwork();
        adjacentList = socialNetwork.getRelationships();
        user = new User(11, UserType.ADDED);
        user1 = new User(12, UserType.ADDED);
        user2 = new User(13, UserType.ADDED);
        relation = new Relation("", RelationType.DIRECT_NORMAL);
    }

    @Test
    void numVertices() {
        Assertions.assertEquals(10,adjacentList.numVertices());
        adjacentList.insertVertex(user);
        Assertions.assertEquals(11,adjacentList.numVertices());
    }

    @Test
    void numEdges() {
        Assertions.assertEquals(16,adjacentList.numEdges());
        adjacentList.insertVertex(user);
        adjacentList.insertVertex(user1);
        adjacentList.insertEdge(user, user1, relation);
        Assertions.assertEquals(17,adjacentList.numEdges());
    }

    @Test
    void vertices() {
        Collection<Vertex<User>> users = adjacentList.vertices();
        Assertions.assertEquals(users,adjacentList.vertices());
        adjacentList.insertVertex(user);
        Assertions.assertNotEquals(users,adjacentList.vertices());
    }

    @Test
    void edges() {
        Collection<Edge<Relation, User>> relations = adjacentList.edges();
        Assertions.assertEquals(relations,adjacentList.edges());
        adjacentList.insertVertex(user);
        adjacentList.insertVertex(user1);
        adjacentList.insertEdge(user, user1, relation);
        Assertions.assertNotEquals(relations,adjacentList.edges());
    }

    @Test
    void incidentEdges() {
        Vertex<User> u = adjacentList.insertVertex(user);
        adjacentList.insertVertex(user1);
        Edge<Relation, User> edge = adjacentList.insertEdge(user1, user, relation);
        Assertions.assertTrue(adjacentList.incidentEdges(u).contains(edge));
        Assertions.assertFalse(adjacentList.outboundEdges(u).contains(edge));
    }

    @Test
    void outboundEdges() {
        Vertex<User> u = adjacentList.insertVertex(user);
        adjacentList.insertVertex(user1);
        Edge<Relation, User> edge = adjacentList.insertEdge(user, user1, relation);
        Assertions.assertTrue(adjacentList.outboundEdges(u).contains(edge));
        Assertions.assertFalse(adjacentList.incidentEdges(u).contains(edge));
    }

    @Test
    void opposite() {
        Vertex<User> vertex1 = adjacentList.insertVertex(user);
        Vertex<User> vertex2 = adjacentList.insertVertex(user1);
        Edge<Relation, User> edge = adjacentList.insertEdge(user, user1, relation);
        Assertions.assertEquals(vertex2, adjacentList.opposite(vertex1, edge));
        Vertex<User> vertex3 = adjacentList.insertVertex(user2);
        Assertions.assertNotEquals(vertex3, adjacentList.opposite(vertex1, edge));
    }

    @Test
    void areAdjacent() {
        Vertex<User> vertex1 = adjacentList.insertVertex(user);
        Vertex<User> vertex2 = adjacentList.insertVertex(user1);
        Edge<Relation, User> edge = adjacentList.insertEdge(user, user1, relation);
        Assertions.assertEquals(true, adjacentList.areAdjacent(vertex1, vertex2));
        Vertex<User> vertex3 = adjacentList.insertVertex(user2);
        Assertions.assertEquals(false, adjacentList.areAdjacent(vertex1, vertex3));
    }

    @Test
    void insertVertex() {
        assertEquals(10,adjacentList.vertices().size());
        Vertex<User> vertex1 = adjacentList.insertVertex(user);
        assertTrue(adjacentList.vertices().contains(vertex1));
        assertEquals(11,adjacentList.vertices().size());
        assertThrows(InvalidVertexException.class, () -> {
            adjacentList.insertVertex(user);
        });
    }

    @Test
    void insertEdge() {
        adjacentList.insertVertex(user);
        adjacentList.insertVertex(user1);
        assertEquals(16,adjacentList.edges().size());
        Edge<Relation, User> edge = adjacentList.insertEdge(user,user1,relation);
        assertEquals(17,adjacentList.edges().size());
        assertTrue(adjacentList.edges().contains(edge));
        assertThrows(InvalidEdgeException.class, () -> {
            adjacentList.insertEdge(user,user1,relation);
        });
    }

    @Test
    void removeVertex() {
        Vertex<User> vertex = adjacentList.insertVertex(user);
        assertEquals(11,adjacentList.vertices().size());
        adjacentList.removeVertex(vertex);
        assertEquals(10,adjacentList.vertices().size());
    }

    @Test
    void removeEdge() {
        Vertex<User> vertex = adjacentList.insertVertex(user);
        Vertex<User> vertex1 = adjacentList.insertVertex(user1);
        Edge<Relation, User> edge = adjacentList.insertEdge(user,user1, relation);
        assertEquals(17,adjacentList.edges().size());
        adjacentList.removeEdge(edge);
        assertEquals(16,adjacentList.edges().size());
    }

    @Test
    void replaceVertex() {
        Vertex<User> vertex = adjacentList.insertVertex(user);
        assertEquals(user, adjacentList.replace(vertex,user1));
        assertThrows(InvalidVertexException.class, () -> {
            adjacentList.replace(vertex,user1);
        });
    }

    @Test
    void replaceEdge() {
        adjacentList.insertVertex(user);
        adjacentList.insertVertex(user1);
        Edge<Relation, User> edge = adjacentList.insertEdge(user,user1,relation);
        Relation relation2 = new Relation("", RelationType.DIRECT_NORMAL);
        assertEquals(relation, adjacentList.replace(edge,relation2));
        assertThrows(InvalidEdgeException.class, () -> {
            adjacentList.replace(edge,relation2);
        });
    }

}

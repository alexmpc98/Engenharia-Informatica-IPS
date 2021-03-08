package com.pa.patterns.memento.model;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ShoppingCartControllerTest {
    private ShoppingCartController ctrl;

    @BeforeEach
    void setUp() {
        ctrl = new ShoppingCartController();
    }

    @Test
    void addProduct() {
        assertEquals(0, ctrl.getProducts().size());
        ctrl.addProduct("Test product", 42.0);
        assertEquals(1, ctrl.getProducts().size());
    }

    @Test
    void reset() {
        this.ctrl.reset();
        assertEquals(0,ctrl.getProducts().size());
    }

    @Test
    void removeProduct() {
        ctrl.addProduct("Test product", 42.0);
        assertEquals(1, ctrl.getProducts().size());
        this.ctrl.removeProduct("Test product");
        assertEquals(0, ctrl.getProducts().size());
    }

    @Test
    void undo() {
        ctrl.addProduct("Test product", 42.0);
        assertEquals(1, ctrl.getProducts().size());
        this.ctrl.undo();
        assertEquals(0, ctrl.getProducts().size());
    }

    @Test
    void getProducts() {
        assertEquals(0, ctrl.getProducts().size());
        ctrl.addProduct("Test product", 42.0);
        ctrl.addProduct("Test product2", 42.0);
        ctrl.addProduct("Test product3", 42.0);
        assertEquals(3, ctrl.getProducts().size());
    }

    @Test
    void showAll() {
        ctrl.addProduct("Test product", 42.0);
        assertNotNull(ctrl.showAll());
    }
}
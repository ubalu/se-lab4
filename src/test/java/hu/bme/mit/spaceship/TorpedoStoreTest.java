package hu.bme.mit.spaceship;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class TorpedoStoreTest {

    @Test
    void TorpedoStore_smoke_test() {
        var store = new TorpedoStore(69);

        Assertions.assertEquals(69, store.getTorpedoCount(), "smoke test");
    }

    @Test
    void fire_success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        Assertions.assertEquals(true, result);
    }

    @Test
    void fire_success_2() {
        var store = new TorpedoStore(10, 0.0);
        
        Assertions.assertTrue(store.fire(3), "can fire multiple torpedoes");
        Assertions.assertEquals(7, store.getTorpedoCount(), "correct number of torpedoes remain");
    }

    @Test
    void fire_wrong_number() {
        var store = new TorpedoStore(10, 0.0);
        
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> store.fire(0),
            "cannot fire zero torpedoes");
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> store.fire(-85),
            "cannot fire a negative number of torpedoes");
    }

    @Test
    void fire_fail_empty() {
        var store = new TorpedoStore(3, 0.0);

        Assertions.assertTrue(store.fire(2), "can fire while has torpedoes");
        Assertions.assertEquals(1, store.getTorpedoCount(), "correct number of torpedoes remain");
        Assertions.assertThrows(
            IllegalArgumentException.class, 
            () -> store.fire(2), 
            "cannot fire more than remaining torpedoes");        
        Assertions.assertEquals(1, store.getTorpedoCount(), "failed firing does not consume torpedoes");
    }

    @Test
    void fire_fail_overheat() {
        var store = new TorpedoStore(10, 1.0);

        Assertions.assertFalse(store.fire(1), "overheated store cannot fire");
        Assertions.assertEquals(10, store.getTorpedoCount(), "failed firing does not consume torpedoes");
    }
}

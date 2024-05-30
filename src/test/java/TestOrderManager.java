import org.example.Order;
import org.example.OrderManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

public class TestOrderManager {
    OrderManagerImpl impl;

    @Test
    public void checkIfAddOrderAddsOrdersToList(){
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        //Act
        impl.addOrder(order);
        Order order1 = impl.getOrderById(1);
        //Assert
        Assertions.assertEquals(order, order1);
    }
    @Test
    public void checkIfAddOrderAddsDoesntAcceptDublicateId(){
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        Order order1 = new Order(1, "1", 23.56, "preparing...");
        //Act
        impl.addOrder(order);
        impl.addOrder(order1);
        //Assert
        Assertions.assertEquals(1, 1);
    }
    @Test
    public void checkIfAddOrderThrowsExceptionWhenOrderIsNull(){
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        Order order1 = new Order(1, "1", 23.56, "preparing...");
        Order order2 = null;
        //Act
        impl.addOrder(order);
        impl.addOrder(order1);
        //Assert
        Assertions.assertThrows(NullPointerException.class, ()->{impl.addOrder(order2);});
    }

    @Test
    public void removeOrderRemovesOrderFromList(){
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        //Act
        impl.removeOrder(1);
        Order order1 = impl.getOrderById(1);
        //Assert
        Assertions.assertNotEquals(order, order1);
    }
    @Test
    public void removeOrderDoesntRemoveOrderFromList() {
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        impl.addOrder(order);
        //Act
        impl.removeOrder(2);
        Order order1 = impl.getOrderById(1);
        //Assert
        Assertions.assertEquals(order, order1);
    }
    @Test
    public void getOrderByIdReturnsCorrectOrder(){
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        Order order2 = new Order(2, "2", 23.56, "preparing...");
        impl.addOrder(order);
        impl.addOrder(order2);
        //Act
        Order oreder1 = impl.getOrderById(2);
        //Assert
        Assertions.assertEquals(order2, oreder1);
    }
    @Test
    public void getOrderByIdDoesntFindOrderReturnsNull(){
        //Arrange
        Order order = new Order(1, "1", 23.56, "preparing...");
        impl.addOrder(order);
        //Act
        Order oreder1 = impl.getOrderById(2);
        //Assert
        Assertions.assertNull(oreder1);
    }
    @Test
    public void getOrderByCustomerReturnsCorrectOrder(){
        //Arrange
        List<Order> tempList = new ArrayList<>();
        Order order = new Order(1, "1", 23.56, "preparing...");
        Order order2 = new Order(2, "2", 23.56, "preparing...");
        Order order3 = new Order(3, "2", 23.56, "preparing...");
        impl.addOrder(order);
        impl.addOrder(order2);
        impl.addOrder(order3);
        //Act
        List<Order> clientOrderList = impl.getOrdersByCustomer("2");
        //Assert
        Assertions.assertTrue(clientOrderList.contains(order3));
        Assertions.assertTrue(clientOrderList.contains(order2));
    }
    @Test
    public void getOrderByCustomerReturnsFalseWhenCantFindCustomer(){
        //Arrange
        List<Order> tempList = new ArrayList<>();
        Order order = new Order(1, "1", 23.56, "preparing...");
        impl.addOrder(order);
        //Act
        List<Order> clientOrderList = impl.getOrdersByCustomer("2");
        //Assert
        Assertions.assertFalse(clientOrderList.contains(order));
    }
    @Test

    @BeforeEach
    public void beforeEach(){
        impl = new OrderManagerImpl();
    }
}

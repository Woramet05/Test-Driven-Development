import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return "+total1);
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return "+total2);
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct ("+total3+")");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: pirce หรือ quantity ติดลบ
        ArrayList<CartItem> NegativeCart = new ArrayList<>();
        NegativeCart.add(new CartItem("NORMAL", "Bread", -25.0, 2)); // skip
        NegativeCart.add(new CartItem("NORMAL", "Milk", 15.0, -1)); // skip
        NegativeCart.add(new CartItem("NORMAL", "Cake", 20.0, 2));      // 40
        double total4 = ShoppingCartCalculator.calculateTotalPrice(NegativeCart);
        if (total4 == 40) {
            System.out.println("PASSED: Skip Negative cart total is correct ("+total4+")");
            passedCount++;
        } else {
            System.out.println("FAILED: Negative cart total expected 40 but got " + total4);
            failedCount++;
        }

        // Test 5: ส่วนลด BOGO จำนวนสินค้าเป็นจำนวนคู่
        ArrayList<CartItem> BOGOevenCart = new ArrayList<>();
        BOGOevenCart.add(new CartItem("BOGO", "Bread", 25.0, 2)); // 25
        BOGOevenCart.add(new CartItem("BOGO", "Milk", 15.0, 2));      // 15
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BOGOevenCart);
        if (total5 == 40.0) {
            System.out.println("PASSED: BOGO cart total is correct ("+total5+")");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 40 but got " + total5);
            failedCount++;
        }

        // Test 6: ส่วนลด BOGO จำนวนสินค้าเป็นจำนวนคี่
        ArrayList<CartItem> BOGOoddCart = new ArrayList<>();
        BOGOoddCart.add(new CartItem("BOGO", "Bread", 25.0, 3)); // 50
        BOGOoddCart.add(new CartItem("BOGO", "Milk", 15.0, 2));      // 15
        double total6 = ShoppingCartCalculator.calculateTotalPrice(BOGOoddCart);
        if (total6 == 65) {
            System.out.println("PASSED: BOGO cart total is correct ("+total6+")");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart total expected 65 but got " + total6);
            failedCount++;
        }

        // Test 7: ส่วนลด BULK มากกว่า 6 ทั้งสอง สินค้า
        ArrayList<CartItem> BULK2Cart = new ArrayList<>();
        BULK2Cart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 135
        BULK2Cart.add(new CartItem("BULK", "Milk", 15.0, 6));      //81
        double total7 = ShoppingCartCalculator.calculateTotalPrice(BULK2Cart);
        if (total7 == 216) {
            System.out.println("PASSED: BULK cart total is correct ("+total7+")");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 216 but got " + total7);
            failedCount++;
        }

        // Test 8: ส่วนลด BULK มากกว่า 6 หนึ่งสินค้า
        ArrayList<CartItem> BULKCart = new ArrayList<>();
        BULKCart.add(new CartItem("BULK", "Bread", 25.0, 6)); // 135
        BULKCart.add(new CartItem("BULK", "Milk", 15.0, 3));      // 45
        double total8 = ShoppingCartCalculator.calculateTotalPrice(BULKCart);
        if (total8 == 180.0) {
            System.out.println("PASSED: BULK cart total is correct ("+total8+")");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart total expected 180 but got " + total8);
            failedCount++;
        }
        // Test 9: รวมทุกกรณี
        ArrayList<CartItem> AllCart = new ArrayList<>();
        AllCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        AllCart.add(new CartItem("NORMAL", "Pancake", 5.0, -1)); // skip
        AllCart.add(new CartItem("BOGO", "Milk", 15.0, 2));      // 15
        AllCart.add(new CartItem("BOGO", "Cake", 20.0, 3));      // 40
        AllCart.add(new CartItem("BULK", "Coke", 10.0, 6));      // 54
        AllCart.add(new CartItem("BULK", "Apple", 10.0, 3));      // 30
        double total9 = ShoppingCartCalculator.calculateTotalPrice(AllCart);
        if (total9 == 189) {
            System.out.println("PASSED: ALL cart total is correct ("+total9+")");
            passedCount++;
        } else {
            System.out.println("FAILED: ALL cart total expected 180 but got " + total9);
            failedCount++;
        }

        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}

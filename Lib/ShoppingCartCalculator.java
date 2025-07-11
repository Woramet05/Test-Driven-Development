import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * -ถ้า items เป็น null หรือ empty จะ return หรือคืนค่า 0.00 กลับไป
     * -ถ้า CartItem มี price หรือ quantity ติดลบ จะทำการข้ามสินค้านั้นไป
     * -ถ้าซื้อสินค้าแบบปกติ (NORMAL) จะคำนวณจำนวนสินค้า และราคาสินค้าตามปกติ
     * -ส่วนลด BOGO (ซื้อ 1 แถม 1) เช่น ถ้าซื้อสินค้าจำนวน 2 ชิ้น จะจ่ายเพียง 1 ชิ้น,
     * ถ้าซื้อสินค้าจำนวน 3 ชิ้น จะจ่าย 2 ชิ้น
     * -ส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%) ถ้าซื้อสินค้าชิ้นหนึ่งมากกว่า 6 ชิ้น จะทำการลดราคาสิ้นค้าชิ้นนั้น 10%
     * แต่ถ้าจำนวนสินค้านั้นมีไม่ถึง 6 ชิ้น ก็จะคำนวณราคาตามปกติ
     * @param item คือ สินค้าในตะกร้า
     * @return record CartItem int price (ราคาสินค้าที่ต้องจ่าย)
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        
        double Total = 0.0;

        if (items == null || items.isEmpty()) {
            return 0.0;
        }

        for(CartItem item : items){
            if (item.price() < 0 || item.quantity() < 0) {
                continue;
            }
            else if (item.sku() == "NORMAL"){
                Total += item.price() * item.quantity();
            }
            else if(item.sku() == "BOGO"){
                if (item.quantity() % 2 == 0) {
                    Total += item.price() * (item.quantity() / 2);
                } else
                Total += item.price() * (item.quantity() / 2 + 1);
            }
            else if(item.sku() == "BULK") {
                if (item.quantity() >= 6) {
                    Total += (item.price() * item.quantity()) * 0.9;
                } else 
                Total += item.price() * item.quantity();
            }
        }
        return Total;
    }
}
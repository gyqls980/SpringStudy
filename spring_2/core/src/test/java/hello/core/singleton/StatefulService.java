package hello.core.singleton;

public class StatefulService {
    // order() 호출 시 바로 price 반환
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}

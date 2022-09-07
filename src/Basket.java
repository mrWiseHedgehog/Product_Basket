import java.util.Scanner;

public class Basket {

    public static void main(String[] args) {

        String[] products = {"Яблоко", "Помидор", "Апельсин", "Груша"};
        int[] prices = {30, 50, 70, 40};
        int sumProducts = 0;

        Scanner scanner = new Scanner(System.in);
        int[] amountOfProductsInBasket = new int[products.length];

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            String product = products[i];
            int price = prices[i];
            int position = i + 1;
            System.out.println(position + ". " + product + " " + price + " руб/шт");
        }

        while (true) {
            int productNumber;
            int productCount;
            System.out.println("Выберите товар и количество или введите `end`");
            String inputString = scanner.nextLine();
            if (inputString.equals("end")) {
                break;
            }
            String[] input = inputString.split(" ");
            productNumber = Integer.parseInt(input[0]);
            productCount = Integer.parseInt(input[1]);
            amountOfProductsInBasket[productNumber - 1] += productCount;
        }

        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (amountOfProductsInBasket[i] != 0) {
                sumProducts = sumProducts + (prices[i] * amountOfProductsInBasket[i]);
                System.out.println(
                        products[i] + " " + amountOfProductsInBasket[i] + " шт. " + prices[i] + " руб/шт. "
                                + (amountOfProductsInBasket[i] * prices[i]) + " рублей в сумме");
            }
        }
        System.out.println("Итого: " + sumProducts + " рублей");
    }
}
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("basket.txt");

        String[] products = new String[]{"Яблоко", "Помидор", "Апельсин", "Груша"};
        int[] prices = new int[]{30, 50, 70, 40};
        Basket basket = new Basket(products, prices);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < basket.getProducts().length; i++) {
            String product = basket.getProducts()[i];
            int price = basket.getPrices()[i];
            int position = i + 1;
            System.out.println(position + ". " + product + " " + price + " руб/шт");
        }
        if (!file.createNewFile()) {
            String oldBasket = Basket.loadFromTxtFile(file);
            String[] oldBasketArr = oldBasket.split(";");
            for (int i = 0; i < oldBasketArr.length; i++) {
                basket.addToCart(i, Integer.parseInt(oldBasketArr[i]));
            }
            basket.printCart();
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            String[] inputArr = input.split(" ");
            if (inputArr.length != 2) {
                System.out.println("Ошибка ввода! Должно быть введено 2 числа!");
                continue;
            }
            int productNum = Integer.parseInt(inputArr[0]) - 1;
            int amount = Integer.parseInt(inputArr[1]);
            if (productNum > products.length || productNum < 0) {
                System.out.println(
                        "Ошибка! Товара с таким номером не существует! Внимательно ознакомьтесь со списком продуктов.");
                continue;
            }
            basket.addToCart(productNum, amount);
            basket.printCart();
            basket.saveTxt(file);
        }
    }
}

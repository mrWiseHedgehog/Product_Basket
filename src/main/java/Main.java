import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        File file = new File("basket.json");
        File csvFile = new File("client.csv");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String[] products = new String[]{"Яблоко", "Помидор", "Апельсин", "Груша"};
        int[] prices = new int[]{30, 50, 70, 40};
        Basket basket = new Basket(products, prices);
        ClientLog clientLog = new ClientLog();
//        if (!file.createNewFile()) {
//            try (JsonReader reader = new JsonReader(new FileReader(file))) {
//                basket = gson.fromJson(reader, Basket.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            basket.printCart();
//        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < basket.getProducts().length; i++) {
            String product = basket.getProducts()[i];
            int price = basket.getPrices()[i];
            int position = i + 1;
            System.out.println(position + ". " + product + " " + price + " руб/шт");
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
            try {
                basket.addToCart(productNum, amount);
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Товар с таким номером не найден!");
                continue;
            }
            clientLog.log(productNum + 1, amount);
            gson.toJson(basket);
            basket.printCart();
        }
        clientLog.exportAsCSV(csvFile);

        try (FileWriter jsonWriter = new FileWriter(file)) {
            jsonWriter.write(gson.toJson(basket));
            jsonWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder xmlBuilder = factory.newDocumentBuilder();
//        Document doc = xmlBuilder.parse(new File("shop.xml"));
//        Node root = doc.getDocumentElement();
//        System.out.println("Корневой элемент: " + root.getNodeName());
//        read(root);
//    }
//
//    private static void read(Node node) {
//        NodeList nodeList = node.getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node_ = nodeList.item(i);
//            if (Node.ELEMENT_NODE == node_.getNodeType()) {
//                System.out.println("Текущий узел: " + node_.getNodeName());
//                Element element = (Element) node_;
//                NamedNodeMap map = element.getAttributes();
//                for (int a = 0; a < map.getLength(); a++) {
//                    String attrName = map.item(a).getNodeName();
//                    String attrValue = map.item(a).getNodeValue();
//                    System.out.println("Атрибут: " + attrName + "; значение: " + attrValue);
//                }
//                read(node_);
//            }
//        }
//    }
    }
}
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private int humanCount;
    private static final Scanner scanner = new Scanner(System.in);
    private List<Product> productsList;
    private static double priceAllProducts = 0;

    public Calculator(){
        humanCount = divideBuild();
        this.productsList = addProducts(humanCount);

    }

    public static int divideBuild(){
        while (true){
            try{
                System.out.println("На скольких человек необходимо разделить счёт?");
            int count  = Integer.parseInt(scanner.nextLine());
        if(count < 1){
            System.out.println("Ошибка. Введено некорректное значение для подсчёта. Попробуйте снова");
        }
        else if(count == 1){
            System.out.println("Нет смысла ничего считать и делить. Попробуйте снова");
        }
        else {
            return count;
        }}catch (Exception e){
                System.out.println("Ошибка. Попробуйте снова.");
            }
        }
    }

    public static List<Product> addProducts(int humanCount){
        List<Product> list = new ArrayList<>();
        while(true){
            System.out.println("Введите название одного товара или слово \"Завершить\" для подсчета стоимости");
            String product = scanner.nextLine();
            if(product.equalsIgnoreCase("Завершить")){
                if(list.size() == 0){
                    System.out.println("Товар не был введен, программа завершена.");
                }
                else {
                    System.out.println("Добавленные товары:");
                    for(Product products : list){
                        System.out.println(products.getName());
                    }
                    double pay = priceAllProducts / humanCount;
                    //double pay2 = Math.floor(pay);
                    String payformat = String.format("%.2f", pay);
                    String rub = floor(pay);
                    System.out.println("Каждый человек из " + humanCount +" должен заплатить " + payformat + " " + rub);
                }
                break;}
            System.out.println("Введите стоимость товара в формате: 'рубли.копейки'");
             try{
            String price0 = scanner.nextLine();
            Double price = Double.parseDouble(price0);
            priceAllProducts += price;
            list.add(new Product(product,price));}
             catch (NumberFormatException e){
                 System.out.println("Задан неверный формат стоимости, начните заново");
             }
        }
        return list;
    }

    private static String floor(double pay){
        String rub = Integer.toString((int)Math.floor(pay));
        if(rub.length() == 1){
            switch (rub) {
                case "0":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    return "рублей";
                case "1":
                    return "рубль";
                case "2":
                case "3":
                case "4":
                    return "рубля";
            }
        }
        else if(rub.length() > 1){
            int lastDigit = Integer.parseInt(Character.toString(rub.charAt(rub.length()-1)));
            if(rub.length() == 2 && rub.charAt(0) == '1'){return "рублей!!";}
            else if(lastDigit >= 5 && lastDigit <= 9 )
                return "рублей";
            else if(lastDigit >= 2 && lastDigit <= 4 )
                return "рубля";
            else if(lastDigit == 1)
                return "рубль";
            else
                return "рублей";
        }
        return "Ошибка";
    }
}

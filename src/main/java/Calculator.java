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
        System.out.println("Введите название товара и его стоимость в формате:\n'рубли.копейки' [10.45, 11.40]");
        while(true){
           String products = scanner.nextLine();
           if(products.equalsIgnoreCase("Завершить")){
               if(list.size() == 0){
                   System.out.println("Товар не был введен, программа завершена.");
               }
               else {
                   System.out.println("Добавленные товары:");
                   for(Product product : list){
                       System.out.println(product.getName());
                   }
                   double pay = priceAllProducts / humanCount;
                   //double pay2 = Math.floor(pay);
                   String payformat = String.format("%.2f", pay);
                   String rub = floor(pay);
                   System.out.println("Каждый человек из " + humanCount +" должен заплатить " + payformat + " " + rub);
               }
               break;}
           else if(products.length() < 1){
               continue;}

            String[] names = products.substring(products.indexOf("'") + 1,products.indexOf("' ")).split("\\.");
            String[] prices = products.substring(products.indexOf("[")+1,products.indexOf("]")).split(",");
           if(names.length != prices.length) {
               System.out.println("Количество продуктов и стоимости не совпадает.");
               continue;
           }
           for(int i = 0; i < names.length; i++){
               list.add(new Product(names[i].trim(),Double.parseDouble(prices[i].trim())));
               priceAllProducts += Double.parseDouble(prices[i].trim());
               if(i == 0)
               System.out.print("Товар успешно добавлен: " + names[i]);
               else if(i == names.length-1)
                   System.out.print(", " + names[i] + "\n");
               else
                   System.out.print(", " + names[i]);
           }

            System.out.println("Хотите добавить ещё товары? Если нет, введите слово \"Завершить\"");
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

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в магазин игрушек!");
        PrizeDraw prizeDraw = new PrizeDraw();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    Сделайте свой выбор:
                    1 - Добавить новую игрушку в розыгрыш
                    2 - Изменить частоту выпадения
                    3 - Провести розыгрыш (с записью результатов)
                    0 - Выйти из магазина
                    >\s""");
            var select = scanner.next();
            switch (select) {
                case "1" -> prizeDraw.addToy();
                case "2" -> prizeDraw.createFrequency();
                case "3" -> prizeDraw.winPrize();
                case "0" -> {
                    System.out.println("До свидания!");
                    System.exit(0);
                }
                default -> System.out.println("Не верный ввод! Повторите попытку!");
            }
        }
    }
}

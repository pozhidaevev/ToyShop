import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class PrizeDraw {

    private static ArrayList<Toy> toys = new ArrayList<>();
    private static PriorityQueue<Toy> toysQueue = new PriorityQueue<>();
    private static int counterId = 0;

    public void addToy() {
        Scanner scanner = new Scanner(System.in);
        String name;
        int frequency;
        while (true) {
            System.out.print("Введите название игрушки: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Поле не должно быть пустым! Введите название!");
                break;
            }
            System.out.println("Введите частоту выпадения игрушки. ВНИМАНИЕ!!! Рекомендумое число в диапазоне 1-4: ");
            String value = scanner.nextLine();

            frequency = Integer.parseInt(value);
            if (frequency <= 0 || frequency > 9) {
                System.out.println("Число должно быть больше нуля и меньше 10! Попробуйте еще раз!");
            } else {
                Toy toy = new Toy(counterId, name, frequency);
                if (!toys.contains(toy) || toys.size() == 0) {
                    counterId++;
                    toys.add(toy);
                    System.out.println("Игрушка добавлена!");
                } else {
                    System.out.println("Добавьте что-нибудь новенькое, такая игрушка уже есть!");
                }
            }
            break;
        }
    }


    public void createFrequency() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер ID игрушки: ");
        String value = scanner.nextLine();
        int choiceId = Integer.parseInt(value);
        if (choiceId >= 0 && choiceId < toys.size()) {
            System.out.println("Игрушка " + toys.get(choiceId).getName() + " имеет частоту выпадения "
                    + toys.get(choiceId).getFrequency());
            System.out.println("Введите новую частоту выпадения игрушки. ВНИМАНИЕ!!! " +
                    "Рекомендумое число в диапазоне 1-4: ");
            value = scanner.nextLine();
            int newFrequency = Integer.parseInt(value);
            toys.get(choiceId).setFrequency(newFrequency);
            System.out.println("Частота выпадения изменена!");
        } else {
            System.out.println("Игрушки с таким ID не существует!");
        }
    }

    public Toy getPrize() {
        if (toysQueue.size() == 0) {
            Random random = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getFrequency(); i++) {
                    Toy toy1 = new Toy(toy.getId(), toy.getName(), random.nextInt(1, 10));
                    toysQueue.add(toy1);
                }
            }
        }
        return toysQueue.poll();
    }

    public void winPrize() {
        if (toys.size() >= 2) {
            Toy toy = getPrize();
            System.out.println("Выигрыш: " + toy.getName());
            saveResult(toy.getInfo());
        } else {
            System.out.println("В розыгрыше должно быть минимум 2 игрушки!");
        }
    }

    private void saveResult(String text) {
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fileWriter = new FileWriter("Result.txt", true)) {
            fileWriter.write(text + "\n");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}



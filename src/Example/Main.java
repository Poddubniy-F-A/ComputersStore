package Example;

import java.util.*;

import static Example.Computer.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Set<Computer> set = new HashSet<>();
        set.add(new Computer("name1", "mod1", 8, 32, false));
        set.add(new Computer("name2", "mod1", 8, 32, false));
        set.add(new Computer("name3", "mod2", 8, 64, false));
        set.add(new Computer("name4", "mod2", 16, 64, false));
        set.add(new Computer("name5", "mod3", 16, 64, true));

        findComputers(set);
    }

    private static void findComputers(Set<Computer> computers) {
        Map<Integer, Object> filters = getFilters();

        for (Integer filterCode : filters.keySet()) {
            Object filter = filters.get(filterCode);
            switch (filterCode) {
                case PROCESSOR_CODE -> computers.removeIf(computer -> !filter.equals(computer.processorModelName()));
                case RAM_CODE -> computers.removeIf(computer -> (Integer) filter > computer.RAMSize());
                case SYS_CODE -> computers.removeIf(computer -> !filter.equals(computer.systemType()));
                case TOUCH_INPUT_CODE -> computers.removeIf(computer -> !filter.equals(computer.touchInputCapability()));
            }
        }

        System.out.println("\nРезультат поиска:");
        for (Computer computer : computers) {
            System.out.println(computer.name());
        }
    }

    private static Map<Integer, Object> getFilters() {
        final int EXIT_CODE = 0;

        Map<Integer, Object> filters = new HashMap<>();

        int code;
        do {
            System.out.print("\nВведите:\n" +
                    PROCESSOR_CODE + " - чтобы указать название модели процессора\n" +
                    RAM_CODE + " - чтобы указать минимальный размер оперативной памяти (Гб)\n" +
                    SYS_CODE + " - чтобы указать разрядность системы\n" +
                    TOUCH_INPUT_CODE + " - чтобы указать наличие возможности сенсорного ввода (Y/N)\n" +
                    EXIT_CODE + " - чтобы закончить ввод фильтров\n");

            code = scanner.nextInt();
            switch (code) {
                case PROCESSOR_CODE -> filters.put(PROCESSOR_CODE, scanner.next());
                case RAM_CODE -> filters.put(RAM_CODE, scanner.nextInt());
                case SYS_CODE -> filters.put(SYS_CODE, scanner.nextInt());
                case TOUCH_INPUT_CODE -> filters.put(TOUCH_INPUT_CODE, scanner.next().equals("Y"));
                case EXIT_CODE -> {
                }
                default -> System.out.println("Некорректный ввод");
            }
        } while (code != EXIT_CODE);

        return filters;
    }
}

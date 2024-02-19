package Example;

import java.util.*;

public class Main {
    private static final int PROCESSOR_CODE = 1;
    private static final int RAM_CODE = 2;
    private static final int SYS_CODE = 3;
    private static final int TOUCH_INPUT_CODE = 4;

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

    private static void findComputers(Set<Computer> set) {
        Map<Integer, Object> filters = getFilters();

        for (Integer paramName : filters.keySet()) {
            Object param = filters.get(paramName);
            switch (paramName) {
                case PROCESSOR_CODE -> set.removeIf(computer -> !param.equals(computer.getProcessorModelName()));
                case RAM_CODE -> set.removeIf(computer -> (Integer) param > computer.getRAMSize());
                case SYS_CODE -> set.removeIf(computer -> !param.equals(computer.getSystemType()));
                case TOUCH_INPUT_CODE -> set.removeIf(computer -> !param.equals(computer.getTouchInputCapability()));
            }
        }

        System.out.println("\nРезультат поиска:");
        for (Computer computer : set) {
            System.out.println(computer.getName());
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
                case EXIT_CODE -> {}
                default -> System.out.println("Некорректный ввод");
            }
        } while (code != EXIT_CODE);

        return filters;
    }
}

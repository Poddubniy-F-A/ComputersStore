package Example;

public record Computer(String name, String processorModelName, int RAMSize, int systemType, Boolean touchInputCapability) {
    public static final int PROCESSOR_CODE = 1;
    public static final int RAM_CODE = 2;
    public static final int SYS_CODE = 3;
    public static final int TOUCH_INPUT_CODE = 4;
}

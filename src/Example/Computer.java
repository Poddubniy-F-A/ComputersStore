package Example;

public record Computer(String name, String processorModelName, int RAMSize, int systemType, Boolean touchInputCapability) {
    public String getName() {
        return name;
    }

    public String getProcessorModelName() {
        return processorModelName;
    }

    public int getRAMSize() {
        return RAMSize;
    }

    public int getSystemType() {
        return systemType;
    }

    public Boolean getTouchInputCapability() {
        return touchInputCapability;
    }
}

import java.util.Arrays;

public class Memory {
    private int[] memoryArray;

    public Memory(int size) {
        memoryArray = new int[size];
    }

    public int read(int address) {
        if (address < 0 || address >= memoryArray.length) {
            System.err.println("Memory access violation: Out of bounds");
            return 0; // Return default value or handle error
        }
        return memoryArray[address];
    }

    public void write(int address, int value) {
        if (address < 0 || address >= memoryArray.length) {
            System.err.println("Memory access violation: Out of bounds");
            return; // Or handle error
        }
        memoryArray[address] = value;
    }

    public void loadInstructions(int[] instructions) {
        if (instructions.length > memoryArray.length) {
            System.err.println("Error: Insufficient memory to load instructions");
            return; // Or handle error
        }
        System.arraycopy(instructions, 0, memoryArray, 0, instructions.length);
    }

    public void printMemoryContents(int startAddress, int endAddress) {
        if (startAddress < 0 || endAddress >= memoryArray.length || startAddress > endAddress) {
            System.err.println("Invalid memory range");
            return; // Or handle error
        }

        for (int i = startAddress; i <= endAddress; i++) {
            System.out.println("Memory[" + i + "]: " + memoryArray[i]);
        }
    }

    @Override
    public String toString() {
        return "Memory{" +
                "memoryArray=" + Arrays.toString(memoryArray) +
                '}';
    }
}

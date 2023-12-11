import java.util.HashMap;

public class Memory {
    private HashMap<Integer, Byte> memory;

    public Memory() {
        this.memory = new HashMap<>();
    }

    public void loadInstructions(int[] instructions) {
        for (int i = 0; i < instructions.length; i++) {
            writeWord(i * 4, instructions[i]);
        }
    }

    public byte readByte(int address) {
        validateAddress(address);
        return memory.getOrDefault(address, (byte) 0);
    }

    public int readWord(int address) {
        validateAddress(address);
        int word = 0;

        for (int i = 0; i < 4; i++) {
            word |= (readByte(address + i) & 0xFF) << (i * 8);
        }

        return word;
    }

    public void writeByte(int address, byte value) {
        validateAddress(address);
        memory.put(address, value);
    }

    public void writeWord(int address, int value) {
        validateAddress(address);

        for (int i = 0; i < 4; i++) {
            writeByte(address + i, (byte) (value >> (i * 8)));
        }
    }

    private void validateAddress(int address) {
        if (address < 0) {
            throw new IllegalArgumentException("Invalid memory address: " + address);
        }
    }
}

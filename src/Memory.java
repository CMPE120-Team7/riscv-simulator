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

    public short readShort(int address) {
        validateAddress(address);
        short shortValue = 0; 

        for(int i = 0; i < 2; i++) {
            shortValue |= (readByte(address + i) & 0xFF) << (i * 8);
        }

        return shortValue; 
    }

    public short readHalfword(int address) {
        validateAddress(address);
        short halfword = 0; 

        for(int i = 0; i < 2; i++) {
            halfword |= (readByte(address + i) & 0xFF) << (i * 8);
        }

        return halfword; 
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

    public void writeShort(int address, short value) {
        validateAddress(address);

        for(int i = 0; i < 2; i++) {
            writeByte(address + i, (byte) (value >> (i * 8)));
        }
    }

    public void writeHalfword(int address, short value) {
        validateAddress(address);
         for(int i = 0; i < 2; i++) {
            writeByte(address + i, (byte) (value >> (i * 8)));
        }
    }

    private void validateAddress(int address) {
        if (address < 0) {
            throw new IllegalArgumentException("Invalid memory address: " + address);
        }
    }
}

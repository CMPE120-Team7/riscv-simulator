
public class RegisterFiles {

    int[] registers = new int[32];

    public int readFromRegister(int index) {
        return registers[index];
    }

    public void writeToRegister(int index, int val) {
        registers[index] = val;
    }
    
    public void printRegisters() {
        for (int i = 0; i < 32; i++) {
            System.out.println("Register x" + i + ": " + registers[i]);
        }
    }
    
    public int getRegister(int registerNumber) {
        if (registerNumber >= 0 && registerNumber < 32) {
            return registers[registerNumber];
        } else {
            throw new IllegalArgumentException("Invalid register number");
        }
    }
    
}

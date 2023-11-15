
public class RegisterFiles {

    int[] registers = new int[32];

    public int readFromRegister(int index) {
        return registers[index];
    }

    public void writeToRegister(int index, int val) {
        registers[index] = val;
    }
}

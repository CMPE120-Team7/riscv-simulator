import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProgramLoader {
    private Memory memory;

    public ProgramLoader(Memory memory){
        this.memory = memory;
    }

    //load instructions from .dat file into memory
    public void loadInstructions(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int address = 0;

            while ((line = br.readLine()) != null) {
                // Assuming each line contains a single instruction in binary format
                // You may need to adjust this based on your .dat file format
                int instruction = Integer.parseInt(line, 2);
                memory.writeWord(address, instruction);
                address += 4; // Assuming each instruction is 4 bytes (32 bits)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(String file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int address = 0;

            while ((line = br.readLine()) != null) {
                // Assuming each line contains a single 32-bit word of data in hexadecimal format
                int data = Integer.parseInt(line, 2);
                memory.writeWord(address, data);
                address += 4; // Assuming each data word is 4 bytes (32 bits)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


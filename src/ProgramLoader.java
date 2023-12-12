import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramLoader {
    public int[] loadInstructionsFromFile(String fileName) {
        List<Integer> instructionsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int instruction = parseInstruction(line);
                instructionsList.add(instruction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertToIntArray(instructionsList);
    }

    private int parseInstruction(String line) {
        try {
            return Integer.parseInt(line.trim(), 16);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing instruction: " + line);
            return 0; // Or handle it accordingly
        }
    }

    private int[] convertToIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private RiscVSimulator simulator;
    private Map<Integer, Integer> breakpoints;
    private boolean continueExecution;

    public Main(RiscVSimulator simulator) {
        this.simulator = simulator;
        breakpoints = new HashMap<>();
        continueExecution = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: ");
            // Display options...

            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "r":
                    runProgram();
                    break;
                case "s":
                    runNextInstruction();
                    break;
                case "pc":
                    printPCValue();
                    break;
                case "insn":
                    printNextInstruction();
                    break;
                case "c":
                    continueExecution();
                    break;
                default:
                    if (input.startsWith("b ")) {
                        setBreakpoint(input);
                    } else if (input.startsWith("0x")) {
                        printMemoryContent(input);
                    } else if (input.startsWith("x")) {
                        printRegisterContent(input);
                    } else {
                        System.out.println("Invalid command. Please try again.");
                    }
                    break;
            }
        }
    }

    private void runProgram() {
        continueExecution = true;
        while (continueExecution) {
            if (breakpoints.containsKey(simulator.getPC())) {
                System.out.println("Hit breakpoint at PC: " + simulator.getPC());
                break;
            }
            simulator.runNextInstruction();
        }
    }

    private void runNextInstruction() {
        simulator.runNextInstruction();
    }

    private void printPCValue() {
        int pcValue = simulator.getPC();
        System.out.println("PC value: " + pcValue);
    }

    private void printNextInstruction() {
        String nextInstruction = simulator.getAssemblyOfNextInstruction();
        System.out.println("Next instruction: " + nextInstruction);
    }

    private void continueExecution() {
        continueExecution = true;
    }

    private void setBreakpoint(String input) {
        // Parse the input to get the specified [pc]
        // Add the breakpoint to the breakpoints map
    }

    private void printMemoryContent(String input) {
        // Parse the memory address from input and print its content
    }

    private void printRegisterContent(String input) {
        // Parse the register number from input and print its content
    }

    public static void main(String[] args) {
        Memory memory = new Memory(4096); // Initialize memory
        RegisterFiles registerFiles = new RegisterFiles(); // Initialize register files
        RiscVSimulator simulator = new RiscVSimulator(memory, registerFiles); // Create a simulator instance
		ProgramLoader programLoader = new ProgramLoader();
        int[] instructions = programLoader.loadInstructionsFromFile("i_type.dat");

        memory.loadInstructions(instructions);

        Main userPrompt = new Main(simulator);
        userPrompt.start();
    }
}

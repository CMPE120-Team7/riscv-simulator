import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		RegisterFiles reg = new RegisterFiles();
		Memory mem = new Memory();
		ProgramLoader loader = new ProgramLoader(mem);
		loader.loadInstructions("i_type.dat");
		SimulationLoop loop = new SimulationLoop(mem, reg);
		long time = System.currentTimeMillis();
		boolean breakPointSet = false;
		int breakPoint = 0;
		

		while (!loop.simulationDone()) {
			System.out.println("Type in instruction: ");
			String inst = scn.nextLine();
			if (inst.equals("r")) {
				//run the entire program in one go
				while(!loop.simulationDone()) {
					loop.runNextInstruction();
				}
				break;
			}
			else if (inst.equals("s")) {
				//run next instruction
				loop.runNextInstruction();
			}
			else if (inst.substring(0, 1).equals("x")) {
				int index = Integer.parseInt(inst.substring(1));
				if (index > 31 || index < 0) {
					System.out.println("Invalid register number.");
				}
				else {
					System.out.println(reg.readFromRegister(index));
				}
			} 
			else if (inst.substring(0,2).equals("0x")) {
				//TODO: when inst = memory address
				//reads content in the given address
				//loops back to execute next instruction
				int memIndex = Integer.parseInt(inst.substring(1));
				System.out.println(mem.readWord(memIndex));
			} 
			else if (inst.equals("pc")) {
				//TODO: when inst = pc
				//returns next instruction, and loops back
				System.out.println(mem.readWord(loop.getPc()));
			}
			else if (inst.equals("insn")) {
				//TODO: when inst = insn
				//returns next assembly of the instruction
				try {
					File asm = new File("i_type.asm");
					Scanner scanner = new Scanner(asm);
					int index = loop.getPc() / 4 + 1;
					String asmInst = "";
					for (int i = 0; i < index; i++) {
						asmInst = scanner.nextLine();
					}
					System.out.println(asmInst);
					scanner.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			else if (inst.substring(0, 1).equals("b")) {
				//TODO: when inst = b[pc]
				//sets the breakpoint
				breakPoint = Integer.parseInt(inst.substring(1, inst.length() - 1));
				breakPointSet = true;
			}
			else {
				//TODO: when inst = c
				//continues the execution, until the BP or end

				if (breakPointSet) {
					for (int i = loop.getPc(); i < breakPoint; i++) {
						loop.runNextInstruction();
					}
					breakPointSet = false;
				} else {
					while(!loop.simulationDone()) {
						loop.runNextInstruction();
					}
					break;
				}
				
			}
			time = System.currentTimeMillis() - time;
			System.out.println("Execution time: " + time);
			System.out.println("Your instruction is " + inst);
		}

		scn.close();
	}
}

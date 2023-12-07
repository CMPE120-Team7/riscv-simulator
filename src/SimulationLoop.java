
public class SimulationLoop {
	private Memory memory;
	private RegisterFiles registerFiles;
	private int pc;
	
	public SimulationLoop (Memory memory, RegisterFiles registerFiles) {
		this.memory = memory;
		this.registerFiles = registerFiles;
		pc = 0;
	}
	
	public void runNextInstruction() {
		//fetch instructions from memory
		int instruction = memory.read(pc);
			
		//decode instruction
		int opcode = (instruction >> 0) & 0x7F; // Extract opcode
        int rd = (instruction >> 7) & 0x1F; // Extract destination register
        int funct3 = (instruction >> 12) & 0x07; // Extract function code 3
        int funct7 = (instruction >> 25) & 0x7F; // Extract function code 7
        int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
        int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2
        int imm = ((instruction >> 20) & 0xFFF) | ((instruction & 0x80000000) >> 11); // Extract immediate value
			
		//execute instruction
		switch (opcode) {
			case InstructionSet.ADDI: 
					int result = registerFiles.getRegister(rs1) + imm;
					registerFiles.writeToRegister(rd, result);
					break;
					
			//add more cases of instruction opcodes
		
			default:
				System.out.println("invalid instruction: " + instruction);
				break;
		}
		//next instruction
		pc++;
	}
	
	public boolean simulationDone() {
		return pc >= memory.getSize();
	}
}



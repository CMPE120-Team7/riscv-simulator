
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
			case InstructionSet.LUI:  //load upper immediate 
				int luiRes = imm << 12;  
				registerFiles.writeToRegister(rd, luiRes);
				break; 

			case InstructionSet.AUIPC:  //add upper immediate to pc 
				int auipcRes = pc + (imm << 12); 
				registerFiles.writeToRegister(rd, auipcRes);
				break; 

			case InstructionSet.JAL: //jump and link
				int jalRes = pc + 4; 
				pc += imm; 
				break; 

			case InstructionSet.JALR: //jump and link register
				int jalrRes = pc + 4; 
				pc = (registerFiles.getRegister(rs1) + imm) & 0xFFFFFFFE; 
				break; 

			case InstructionSet.BEQ: //branch if equal
				if(registerFiles.getRegister(rs1) == registerFiles.getRegister(rs2)) {
					pc += imm; 
				} 
				break; 
		
			case InstructionSet.BNE: //branch if not equal
				if(registerFiles.getRegister(rs1) != registerFiles.getRegister(rs2)) {
					pc += imm; 
				}
				break; 
			
			case InstructionSet.BLT: //branch if less than
				if(registerFiles.getRegister(rs1) < registerFiles.getRegister(rs2)) {
					pc += imm; 
				}
				break; 

			case InstructionSet.BGE:  //branch if greater than or equal to 
				if(registerFiles.getRegister(rs1) >= registerFiles.getRegister(rs2)) {
					pc += imm; 
				}
				break; 

			case InstructionSet.BLTU:  //Branch if less than (unsigned) 
				if (Integer.compareUnsigned(registerFiles.getRegister(rs1), registerFiles.getRegister(rs2)) < 0) {
					pc += imm; 
				}
				break; 

			case InstructionSet.BGEU:  //Branch if greater than or equal to (unsigned)
				if (Integer.compareUnsigned(registerFiles.getRegister(rs1), registerFiles.getRegister(rs2)) >= 0) {
					pc += imm; 
				}
				break; 

			case InstructionSet.LB: //load byte  
				int lbAddress = registerFiles.getRegister(rs1) + imm; 
				byte lbValue = (byte) memory.readByte(lbAddress); //casting value as a byte 
				registerFiles.writeToRegister(rd, lbValue);
				break; 
			
			case InstructionSet.LH:  //LH: load halfword 
				int lhAddress = registerFiles.getRegister(rs1) + imm; 
				short lhValue = (short) memory.readShort(lhAddress); //casting value as a short 
				registerFiles.writeToRegister(rd, lhValue);
				break; 

			case InstructionSet.LW: //LW: load word  
				int lwAddress = registerFiles.getRegister(rs1) + imm; 
				int lwValue = memory.readWord(lwAddress); 
				registerFiles.writeToRegister(rd, lwValue);
				break; 

			case InstructionSet.LBU:   //LBU: load byte unsigned
				int lbuAddress = registerFiles.getRegister(rs1) + imm; 
				short lbuValue = (short) (memory.readByte(lbuAddress) & 0xFF); 
				registerFiles.writeToRegister(rd, lbuValue); 
				break; 

			case InstructionSet.LHU:  //LHU: load halfword unsigned
				int lhuAddress = registerFiles.getRegister(rs1) + imm; 
				int lhuValue = memory.readHalfword(lhuAddress) & 0xFFFF; 
				registerFiles.writeToRegister(rd, lhuValue);
				break; 

			case InstructionSet.SB:  //store byte
				int sbAddress = registerFiles.getRegister(rs1) + imm; 
				byte sbValue = (byte) (registerFiles.getRegister(rs2) & 0xFF); 
				memory.writeByte(sbAddress, sbValue); 
				break; 

			case InstructionSet.SH:  //store halfword
				int shAddress = registerFiles.getRegister(rs1) + imm; 
				short shValue = (short) (registerFiles.getRegister(rs2) & 0xFFFF); 
				memory.writeHalfword(shAddress, shValue);
				break; 

			case InstructionSet.SW:  //store word 
				int swAddress = registerFiles.getRegister(rs1) + imm; 
				int swValue = registerFiles.getRegister(rs2); 
				memory.writeWord(swAddress, swValue); 
				break; 
				

			case InstructionSet.ADDI: 
					int result = registerFiles.getRegister(rs1) + imm;
					registerFiles.writeToRegister(rd, result);
					break;
					

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



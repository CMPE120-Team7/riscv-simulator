public class RiscVSimulator {
    private Memory memory;
    private RegisterFiles registerFiles;
    private int pc;

    public RiscVSimulator(Memory memory, RegisterFiles registerFiles) {
        this.memory = memory;
        this.registerFiles = registerFiles;
        pc = 0;
    }

    public void runNextInstruction() {
        int instruction = fetchInstruction();
        int opcode = extractOpcode(instruction);
        int rd = (instruction >> 7) & 0x1F; // Extract destination register
        int funct3 = (instruction >> 12) & 0x07; // Extract function code 3
        int funct7 = (instruction >> 25) & 0x7F; // Extract function code 7
        int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
        int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2
        int imm = ((instruction >> 20) & 0xFFF) | ((instruction & 0x80000000) >> 11); // Extract immediate value

        switch (opcode) {
            case InstructionSet.LUI:
                executeLUI(instruction);
                break;
            case InstructionSet.AUIPC:
                executeAUIPC(instruction);
                break;
            case InstructionSet.JAL:
                executeJAL(instruction);
                break;
            case InstructionSet.JALR:
                executeJALR(instruction);
                break;
            case InstructionSet.BEQ:
                executeBEQ(instruction);
                break;
            case InstructionSet.BNE:
                executeBNE(instruction);
                break;
            case InstructionSet.BLT:
                executeBLT(instruction);
                break;
            case InstructionSet.BGE:
                executeBGE(instruction);
                break;
            case InstructionSet.BLTU:
                executeBLTU(instruction);
                break;
            case InstructionSet.BGEU:
                executeBGEU(instruction);
                break;
            case InstructionSet.LB:
                executeLB(instruction);
                break;
            case InstructionSet.LH:
                executeLH(instruction);
                break;
            case InstructionSet.LW:
                executeLW(instruction);
                break;
            case InstructionSet.LBU:
                executeLBU(instruction);
                break;
            case InstructionSet.LHU:
                executeLHU(instruction);
                break;
            case InstructionSet.SB:
                executeSB(instruction);
                break;
            case InstructionSet.SH:
                executeSH(instruction);
                break;
            case InstructionSet.SW:
                executeSW(instruction);
                break;
            case InstructionSet.ADDI:
                executeADDI(instruction);
                break;

            case InstructionSet.SLTI: // Set Less Than Immediate
                int sltiRes = (registerFiles.getRegister(rs1) < imm) ? 1 : 0;
                registerFiles.setRegister(rd, sltiRes);
                break;

            case InstructionSet.SLTIU: // Set Less Than Immediate Unsigned
                int sltiuRes = (Integer.compareUnsigned(registerFiles.getRegister(rs1), imm) < 0) ? 1 : 0;
                registerFiles.setRegister(rd, sltiuRes);
                break;

            case InstructionSet.XORI: // XOR Immediate
                int xoriRes = registerFiles.getRegister(rs1) ^ imm;
                registerFiles.setRegister(rd, xoriRes);
                break;

            case InstructionSet.ORI: // OR Immediate
                int oriRes = registerFiles.getRegister(rs1) | imm;
                registerFiles.setRegister(rd, oriRes);
                break;

            case InstructionSet.ANDI: // AND Immediate
                int andiRes = registerFiles.getRegister(rs1) & imm;
                registerFiles.setRegister(rd, andiRes);
                break;

            case InstructionSet.SLLI: // Shift Left Logical Immediate
                int shamt = imm & 0x1F; // Extracting lower 5 bits for shift amount
                int slliRes = registerFiles.getRegister(rs1) << shamt;
                registerFiles.setRegister(rd, slliRes);
                break;

            case InstructionSet.SRLI: // Shift Right Logical Immediate
                int shamtSRLI = imm & 0x1F; // Extracting lower 5 bits for shift amount
                int srliRes = registerFiles.getRegister(rs1) >>> shamtSRLI;
                registerFiles.setRegister(rd, srliRes);
                break;

            case InstructionSet.SRAI: // Shift Right Arithmetic Immediate
                int shamtSRAI = imm & 0x1F; // Extracting lower 5 bits for shift amount
                int sraiRes = registerFiles.getRegister(rs1) >> shamtSRAI;
                registerFiles.setRegister(rd, sraiRes);
                break;

            case InstructionSet.ADD: // ADD
                int addRes = registerFiles.getRegister(rs1) + registerFiles.getRegister(rs2);
                registerFiles.setRegister(rd, addRes);
                break;

            case InstructionSet.SUB: // SUB
                int subRes = registerFiles.getRegister(rs1) - registerFiles.getRegister(rs2);
                registerFiles.setRegister(rd, subRes);
                break;

            case InstructionSet.SLT: // Set Less Than
                int sltRes = (registerFiles.getRegister(rs1) < registerFiles.getRegister(rs2)) ? 1 : 0;
                registerFiles.setRegister(rd, sltRes);
                break;

            case InstructionSet.SLTU: // Set Less Than Unsigned
                int sltuRes = (Integer.compareUnsigned(registerFiles.getRegister(rs1), registerFiles.getRegister(rs2)) < 0) ? 1 : 0;
                registerFiles.setRegister(rd, sltuRes);
                break;

            case InstructionSet.XOR: // XOR
                int xorRes = registerFiles.getRegister(rs1) ^ registerFiles.getRegister(rs2);
                registerFiles.setRegister(rd, xorRes);
                break;

            case InstructionSet.SRA: // Shift Right Arithmetic
                int shamtSRA = registerFiles.getRegister(rs2) & 0x1F; // Extracting lower 5 bits for shift amount
                int sraRes = registerFiles.getRegister(rs1) >> shamtSRA;
                registerFiles.setRegister(rd, sraRes);
                break;

            case InstructionSet.OR: // OR
                int orRes = registerFiles.getRegister(rs1) | registerFiles.getRegister(rs2);
                registerFiles.setRegister(rd, orRes);
                break;

            case InstructionSet.AND: // AND
                int andRes = registerFiles.getRegister(rs1) & registerFiles.getRegister(rs2);
                registerFiles.setRegister(rd, andRes);
                break;

            default:
                System.out.println("Invalid instruction: " + instruction);
                break;
        }

        pc += 4; // Increment program counter (assuming 4-byte instructions)
    }

    public String getAssemblyOfNextInstruction() {
    int nextInstruction = fetchInstruction();
    int opcode = extractOpcode(nextInstruction);

    switch (opcode) {
        case InstructionSet.LUI:
            return "LUI"; // Replace with actual assembly representation
        case InstructionSet.AUIPC:
            return "AUIPC"; // Replace with actual assembly representation
        case InstructionSet.JAL:
            return "JAL"; // Replace with actual assembly representation
        case InstructionSet.JALR:
            return "JALR"; // Replace with actual assembly representation
        case InstructionSet.BEQ:
            return "BEQ"; // Replace with actual assembly representation
        case InstructionSet.BNE:
            return "BNE"; // Replace with actual assembly representation
        case InstructionSet.BLT:
            return "BLT"; // Replace with actual assembly representation
        case InstructionSet.BGE:
            return "BGE"; // Replace with actual assembly representation
        case InstructionSet.BLTU:
            return "BLTU"; // Replace with actual assembly representation
        case InstructionSet.BGEU:
            return "BGEU"; // Replace with actual assembly representation
        case InstructionSet.LB:
            return "LB"; // Replace with actual assembly representation
        case InstructionSet.LH:
            return "LH"; // Replace with actual assembly representation
        case InstructionSet.LW:
            return "LW"; // Replace with actual assembly representation
        case InstructionSet.LBU:
            return "LBU"; // Replace with actual assembly representation
        case InstructionSet.LHU:
            return "LHU"; // Replace with actual assembly representation
        case InstructionSet.SB:
            return "SB"; // Replace with actual assembly representation
        case InstructionSet.SH:
            return "SH"; // Replace with actual assembly representation
        case InstructionSet.SW:
            return "SW"; // Replace with actual assembly representation
        case InstructionSet.ADDI:
            return "ADDI"; // Replace with actual assembly representation
        // Include representations for other instructions
        default:
            return "Unknown Instruction";
    }
}


    // Implement methods for each instruction type following the provided logic

    // Method stubs for instructions
  private void executeLUI(int instruction) {
        int imm = instruction & 0xFFFFF000; // Extract immediate value
        int rd = (instruction >> 7) & 0x1F; // Extract destination register

        registerFiles.setRegister(rd, imm);
    }

    private void executeAUIPC(int instruction) {
        int imm = instruction & 0xFFFFF000; // Extract immediate value
        int rd = (instruction >> 7) & 0x1F; // Extract destination register

        registerFiles.setRegister(rd, pc + imm);
    }

    private void executeJAL(int instruction) {
        int imm = ((instruction >> 31) & 0x1) << 20 | ((instruction >> 21) & 0x3FF) << 1 | ((instruction >> 20) & 0x1) << 11 | ((instruction >> 12) & 0xFF) << 12;
        int rd = (instruction >> 7) & 0x1F; // Extract destination register

        registerFiles.setRegister(rd, pc + 4);
        pc += imm;
    }

    private void executeJALR(int instruction) {
        int imm = (instruction >> 20); // Extract immediate value
        int rd = (instruction >> 7) & 0x1F; // Extract destination register
        int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

        int target = (registerFiles.getRegister(rs1) + imm) & 0xFFFFFFFE;
        registerFiles.setRegister(rd, pc + 4);
        pc = target;
    }

    private void executeBEQ(int instruction) {
        int imm = ((instruction >> 31) & 0x1) << 12 | ((instruction >> 25) & 0x3F) << 5 | ((instruction >> 8) & 0xF) << 1 | ((instruction >> 7) & 0x1);
        int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
        int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

        if (registerFiles.getRegister(rs1) == registerFiles.getRegister(rs2)) {
            pc += imm;
        }
    }

    private void executeBNE(int instruction) {
    int imm = ((instruction >> 31) & 0x1) << 12 | ((instruction >> 25) & 0x3F) << 5 | ((instruction >> 8) & 0xF) << 1 | ((instruction >> 7) & 0x1);
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    if (registerFiles.getRegister(rs1) != registerFiles.getRegister(rs2)) {
        pc += imm;
    }
}

private void executeBLT(int instruction) {
    int imm = ((instruction >> 31) & 0x1) << 12 | ((instruction >> 25) & 0x3F) << 5 | ((instruction >> 8) & 0xF) << 1 | ((instruction >> 7) & 0x1);
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    if (registerFiles.getRegister(rs1) < registerFiles.getRegister(rs2)) {
        pc += imm;
    }
}

private void executeBGE(int instruction) {
    int imm = ((instruction >> 31) & 0x1) << 12 | ((instruction >> 25) & 0x3F) << 5 | ((instruction >> 8) & 0xF) << 1 | ((instruction >> 7) & 0x1);
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    if (registerFiles.getRegister(rs1) >= registerFiles.getRegister(rs2)) {
        pc += imm;
    }
}

private void executeBLTU(int instruction) {
    int imm = ((instruction >> 31) & 0x1) << 12 | ((instruction >> 25) & 0x3F) << 5 | ((instruction >> 8) & 0xF) << 1 | ((instruction >> 7) & 0x1);
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    if (Integer.compareUnsigned(registerFiles.getRegister(rs1), registerFiles.getRegister(rs2)) < 0) {
        pc += imm;
    }
}

private void executeBGEU(int instruction) {
    int imm = ((instruction >> 31) & 0x1) << 12 | ((instruction >> 25) & 0x3F) << 5 | ((instruction >> 8) & 0xF) << 1 | ((instruction >> 7) & 0x1);
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    if (Integer.compareUnsigned(registerFiles.getRegister(rs1), registerFiles.getRegister(rs2)) >= 0) {
        pc += imm;
    }
}

private void executeLB(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rd = (instruction >> 7) & 0x1F; // Extract destination register
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

    int address = registerFiles.getRegister(rs1) + imm;
    byte lbValue = (byte) memory.read(address);
    registerFiles.setRegister(rd, lbValue);
}

private void executeLH(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rd = (instruction >> 7) & 0x1F; // Extract destination register
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

    int lhAddress = registerFiles.getRegister(rs1) + imm;
    short lhValue = (short) memory.read(lhAddress);
    registerFiles.setRegister(rd, lhValue);
}

private void executeLW(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rd = (instruction >> 7) & 0x1F; // Extract destination register
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

    int lwAddress = registerFiles.getRegister(rs1) + imm;
    int lwValue = memory.read(lwAddress);
    registerFiles.setRegister(rd, lwValue);
}

private void executeLBU(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rd = (instruction >> 7) & 0x1F; // Extract destination register
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

    int lbuAddress = registerFiles.getRegister(rs1) + imm;
    short lbuValue = (short) (memory.read(lbuAddress) & 0xFF);
    registerFiles.setRegister(rd, lbuValue);
}

private void executeLHU(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rd = (instruction >> 7) & 0x1F; // Extract destination register
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

    int lhuAddress = registerFiles.getRegister(rs1) + imm;
    int lhuValue = memory.read(lhuAddress) & 0xFFFF;
    registerFiles.setRegister(rd, lhuValue);
}

private void executeSB(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    int sbAddress = registerFiles.getRegister(rs1) + imm;
    byte sbValue = (byte) (registerFiles.getRegister(rs2) & 0xFF);
    memory.write(sbAddress, sbValue);
}

private void executeSH(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    int shAddress = registerFiles.getRegister(rs1) + imm;
    short shValue = (short) (registerFiles.getRegister(rs2) & 0xFFFF);
    memory.write(shAddress, shValue);
}

private void executeSW(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1
    int rs2 = (instruction >> 20) & 0x1F; // Extract source register 2

    int swAddress = registerFiles.getRegister(rs1) + imm;
    int swValue = registerFiles.getRegister(rs2);
    memory.write(swAddress, swValue);
}

private void executeADDI(int instruction) {
    int imm = (instruction >> 20); // Extract immediate value
    int rd = (instruction >> 7) & 0x1F; // Extract destination register
    int rs1 = (instruction >> 15) & 0x1F; // Extract source register 1

    int result = registerFiles.getRegister(rs1) + imm;
    registerFiles.setRegister(rd, result);
}

    // Implement other instruction execution methods accordingly

    // Helper methods for instruction decoding and fetching
    private int fetchInstruction() {
        return memory.read(pc);
    }

    private int extractOpcode(int instruction) {
        return instruction & 0x7F;
    }

    public int getPC() {
        return pc;
    }
    // Other utility methods if required
}


public class InstructionSet {

	public static final int LUI = 0x37; 
	public static final int AUIPC = 0x17; 
	public static final int JAL = 0x6F; 
	public static final int JALR = 0x67; 

	public static final int BEQ = 0x63 | (0x0 << 12); //funct3 = 0
	public static final int BNE =  0x63 | (0x1 << 12); //funct3 = 1
	public static final int BLT =  0x63 | (0x4 << 12); //funct3 = 4
	public static final int BGE =  0x63 | (0x5 << 12); //funct3 = 5
	public static final int BLTU =  0x63 | (0x6 << 12); //funct3 = 6
	public static final int BGEU =  0x63 | (0x7 << 12); //funct3 = 7
	
	public static final int LB = 0x03 | (0x0 << 12); //funct3 = 0
	public static final int LH = 0x03 | (0x1 << 12); //funct3 = 1
	public static final int LW = 0x03 | (0x2 << 12); //funct3 = 2
	public static final int LBU = 0x03 | (0x4 << 12); //funct3 = 4
	public static final int LHU = 0x03 | (0x5 << 12); //funct3 = 5

	public static final int SB = 0x23 | (0x0 << 12); //funct3 = 0
	public static final int SH = 0x23  | (0x1 << 12); //funct3 = 1
	public static final int SW = 0x23 | (0x2 << 12);  //funct3 = 2

    public static final int ADD = 0x33; // ADD, SUB, SLL, SLT, SLTU, XOR, SRL, SRA, OR, AND
    public static final int SUB = 0x40000033;
    public static final int SLL = 0x1033;
    public static final int SLT = 0x2033;
    public static final int SLTU = 0x3033;
    public static final int XOR = 0x4033;
    public static final int SRL = 0x5033;
    public static final int SRA = 0x40005033;
    public static final int OR = 0x6033;
    public static final int AND = 0x7033;

    public static final int ADDI = 0x13; // ADDI, SLTI, SLTIU, XORI, ORI, ANDI, SLLI, SRLI, SRAI
    public static final int SLTI = 0x2013;
    public static final int SLTIU = 0x3013;
    public static final int XORI = 0x4013;
    public static final int ORI = 0x6013;
    public static final int ANDI = 0x7013;
    public static final int SLLI = 0x1013;
    public static final int SRLI = 0x5033;
    public static final int SRAI = 0x40005013;

}

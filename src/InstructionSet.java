
public class InstructionSet {

	public static final int LUI = 0x37; 
	public static final int AUIPC = 0x17; 
	public static final int JAL = 0x6F; 
	public static final int JALR = 0x67; 

	public static final int BEQ = 0x63 | (0x0 << 12); 
	public static final int BNE =  0x63 | (0x1 << 12); 
	public static final int BLT =  0x63 | (0x4 << 12); 
	public static final int BGE =  0x63 | (0x5 << 12); 
	public static final int BLTU =  0x63 | (0x6 << 12); 
	public static final int BGEU =  0x63 | (0x7 << 12); 
	
	public static final int LB = 0x03 | (0x0 << 12); 
	public static final int LH = 0x03 | (0x1 << 12); 
	public static final int LW = 0x03 | (0x2 << 12); 
	public static final int LBU = 0x03 | (0x4 << 12); 
	public static final int LHU = 0x03 | (0x5 << 12); 

	public static final int SB = 0x23 | (0x0 << 12); 
	public static final int SH = 0x23  | (0x1 << 12); 
	public static final int SW = 0x23 | (0x2 << 12); 

	public static final int ADDI = 0x13;
	public static final int ADD = 0x33;
    public static final int SUB = 0x40000033;

}

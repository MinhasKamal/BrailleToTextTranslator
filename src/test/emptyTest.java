package test;

public abstract class BrailleToTextTranslatorTest {
	
	public static final String DESKTOP = System.getenv("SystemDrive") + System.getenv("HOMEPATH") + "\\Desktop\\",
			RESOURCE = "src\\res\\imgs\\";
	
	public static final String START_MESSAGE = "<--Operation Started-->";
	public static final String SUCCESS_MESSAGE = "<--Operation Successful!-->";

	private long startTime;
	
	
	
	public BrailleToTextTranslatorTest() {
		startTime = 0;
	}
	
	
	
	public void runTest(){
		this.startTime = System.currentTimeMillis();
		
		try {
			System.out.println(START_MESSAGE);
			
			testMethod();
			
			System.out.println(SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("Time Elapsed: " + getElapsedTime() + "s");
		}
	}
	
	public double getElapsedTime(){
		return (double)(System.currentTimeMillis()-startTime)/1000;
	}
	
	public void print(double[][] array){
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array[i].length; j++){
				System.out.print(array[i][j]+", ");
			}
			System.out.println();
		}
	}
	
	public void print(int[][] array){
		for(int i=0; i<array.length; i++){
			System.out.print("(");
			for(int j=0; j<array[i].length; j++){
				System.out.print(array[i][j]+",");
			}
			System.out.print("), ");
		}
	}
	
	public void print(int[][][] array){
		for(int i=0; i<array.length; i++){
			print(array[i]);
			System.out.println();
		}
	}
	
	public abstract void testMethod() throws Exception;
}

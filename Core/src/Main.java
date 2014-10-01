package src;

public class Main {
	
	private textGUI textGui;
	
	private void init(){
		textGui = new textGUI();
		textGui.Alternativer("bruker");
	}
	
	private void run(){
		
	}
	
	public static void main(String[] args){
		Main program = new Main();
		program.init();
		program.run();
	}
}

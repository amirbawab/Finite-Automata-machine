import finiteAutomata.FiniteAutomata;
import finiteAutomata.State;
import finiteAutomata.Transition;

public class NoAB {
	public static void main(String[] args) {
		
		// L = {w | W in {a,b}*, W does not contain "ab" }
		FiniteAutomata FA = new FiniteAutomata('q');
		State q[] = new State[3];
		q[0] = FA.addState(State.INITIAL_FINAL);
		q[1] = FA.addState(State.FINAL);
		q[2] = FA.addState(State.NORMAL);
		
		FA.addTransition(q[0], q[0],'b');
		FA.addTransition(q[0], q[1],'a');
		FA.addTransition(q[1], q[1],'a');
		FA.addTransition(q[1], q[2],'b');
		FA.addTransition(q[2], q[2],'a');
		FA.addTransition(q[2], q[2],'b');
		
		System.out.println(FA);
		System.out.println("Is deterministic: " + FA.isDFA(new char[] {'a','b'}) + "\n");
		
		String input = "bba";
		if(FA.process(input)){
			System.out.println(input + " Accepted");
			
			System.out.println("Path for accepting " + input);
			for(Transition t : FA.getProcessTransitions())
				System.out.println(t);
			
		}else{
			System.out.println(input + " Rejected");
		}
	}
}

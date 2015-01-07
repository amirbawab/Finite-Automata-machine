Finite Automata Machine
==============

####Methods to build your Finite automata machine<br>

#####Finite automata machine methods and complexities:

| Method        | Return | Explanation | Complexity  |
| ------------- | ------ |------------ | :----------:|
| addState(status) | Added State | Add a state to the finite automata machine | O(1) |
| addTransition(...) | Added Transition | Add a transition between two states | O(1) |
| removeState(s) | void | Remove state and all related transitions | O(s deg) |
| removeTransition(t) | void | Remove a transition | O(1) |
| getStates() | Array of states | Get all states in the finite automata machine | O(\|S\|) |
| getTransitions() | Array of transitions | Get all transitions in the finite automata machine | O(\|T\|) |
| getInitialState() | State | Get the initial state in the finite automata machine | O(1) |
| process(string) | Boolean: True if accepted, False if rejected | Process a string with the finite automata machine | O(\|Γ\|.Moves) <br>Γ*: Tape alphabet* |
| isDFA(alphabet) | Boolean | Checks if a machine is deterministic | O(\|S\|.\|Γ\|+\|T\|.\|Γ\|) |
| chooseInitialState(state) | void | Choose the initial state for the finite automata machine | O(1) |
| addFinalState(state) | void | Make a state as final | O(1) |
| removeFinalState(state) | void | Remove the final status for a final state | O(1) |
| getProcessTransitions() | Array of transitions | Get the transitions for accepting a string | O(1) |
| export(filename) | void | Export finite automata machine to a text file readable by the Finite automata machine parser | O(\|S\|+\|T\|log \|S\|) |

####Populate your Finite automata machine

#####A) Use an input file:

######Input 1 : noab.txt

    prefix = q
    states = 3
    lambda = λ
    initial = 0
    final = 0
    final = 1
    ;
    0,0 : b
    0,1 : a
    1,1 : a
    1,2 : b
    2,2 : a
    2,2 : b
    ;

**Understand input file**

First line is the vertices prefix character:<br>
`prefix = C` where `C` is any character 

Second line is the number of states:<br>
`states = X` where `X` is the number of states

Third line is the lambda character:<br>
`lambda = L` where `L` is any character.

Fourth line is the ID of the initial state:<br>
`initial = I` where `I` is a number between `0` and `X-1`

The next K lines, where K ≥ 0, are the final states:<br>
`final = F` where `F` is a number between `0` and `X-1`

`;` is used to mark the end of state declaration and initialization <br>

The next lines are the transitions between states:<br>
`FROM, TO : READ` where `FROM` & `TO` are between `0` and `X-1`. `READ` is any character.<br>

`;` is used to mark the end of transitions declaration

*Note:* 
- White spaces in the above syntax are ignored.

**Language accepted by noab.txt machine**

L = {w | w in {a,b}*, w does not contain "ab" }<br><br>


#####B) Use methods:

**Example: NoAB.java**
```java
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
```	

**Output of NoAB.java :**

    States:
    →((q0)) ((q1)) (q2) 
    
    Transitions:
    [→((q0)), →((q0)) : b]
    [→((q0)), ((q1)) : a]
    [((q1)), ((q1)) : a]
    [((q1)), (q2) : b]
    [(q2), (q2) : a]
    [(q2), (q2) : b]
    
    Is deterministic: true
    
    bba Accepted
    Path for accepting bba
    [→((q0)), →((q0)) : b]
    [→((q0)), →((q0)) : b]
    [→((q0)), ((q1)) : a]


#####Library used:
https://github.com/amirbawab/GraphADT

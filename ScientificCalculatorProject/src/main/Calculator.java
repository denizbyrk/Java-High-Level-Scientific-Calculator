package main;

import java.text.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator implements ActionListener {

	// define window width and height
	private final int width = 800;
	private final int height = 600;

	// objects for window and visuals
	JFrame window = new JFrame();
	JTextField textField;
	JButton numbers[] = new JButton[10]; // array for numbers 0-9
	JButton operations[] = new JButton[30]; // array for operations like +, -...
	JButton add, sub, multi, divide, decim, negpos, equ, del, clear, leftP,
			rightP, leftP2, rightP2, facto, expo, expo2, mod, ln, log, sin,
			asin, cos, acos, tan, atan, cot, acot, sgn, exp, pi; // declaring operations
	JLabel opr; // create label for the operation after pressing the equal button
	JPanel panel = new JPanel(); // screen and button panel objects

	// create font
	Font font = new Font("Calibri", Font.BOLD, 22);

	// arraylist for storing elements
	ArrayList<String> element = new ArrayList<>();

	// storing the result
	private String result = "";

	// storing the operation
	private String operation = "";

	// checking if there is a division by 0
	private boolean zeroDivision = false;

	// checking if there are any invalid operations
	private boolean invalidOperation = false;

	// checking if the number has more than 15 digits
	private boolean veryLargeNumber = false;

	// check if the element is a number
	private boolean isNum = false;

	// check if the element has a dot (decimal point)
	private boolean hasDot = false;

	// check the number of parenthesis
	private int parenthesisCounter = 0;

	// check the number of square parenthesis
	private int squParenthesisCounter = 0;

	// constructor method
	public Calculator() {

		// panel properties
		panel.setPreferredSize(new Dimension(width, height)); // set the screen size
		panel.setBackground(Color.black); // set window background color to black

		// window properties
		window.setTitle("Calculator");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// textfield for displaying the operations
		textField = new JTextField(); // create textfield
		textField.setBounds(width / 2 - 300, 100, 600, 50); // set the size and location of the textfield
		textField.setFont(new Font("Arial", Font.BOLD, 36)); // set the font of the textfield
		textField.setHorizontalAlignment(SwingConstants.RIGHT); // align the text to the right side of the screen
		textField.setEditable(false); // make the textfield only get input from buttons, not keyboard
		textField.setBackground(Color.black); // set the background of textfield to black
		textField.setForeground(Color.white); // set the texts of the textfield to white

		// buttons
		add = new JButton("+"); // create addition button
		sub = new JButton("-"); // create subtraction button
		multi = new JButton("*"); // create multiplication button
		divide = new JButton("/"); // create division button
		decim = new JButton("."); // create decimal button
		negpos = new JButton("-/+"); // create negative-positive button
		equ = new JButton("="); // create equal button
		leftP = new JButton("("); // create left parenthesis button
		rightP = new JButton(")"); // create right parenthesis button
		leftP2 = new JButton("["); // create left square parenthesis button
		rightP2 = new JButton("]"); // create right square parenthesis button
		del = new JButton("Del"); // create delete button
		clear = new JButton("C"); // create clear button
		facto = new JButton("n!"); // create factorial button
		mod = new JButton("%"); // create modulo button
		expo = new JButton("n^m"); // create exponent button
		expo2 = new JButton("n^2"); // create square button
		ln = new JButton("ln"); // create ln button
		log = new JButton("log"); // create logarithm button
		asin = new JButton("asin"); // create arcsin button
		sin = new JButton("sin"); // create sin button
		acos = new JButton("acos"); // create arccos button
		cos = new JButton("cos"); // create cos button
		atan = new JButton("atan"); // create arctan button
		tan = new JButton("tan"); // create tan button
		acot = new JButton("acot"); // create arccot button
		cot = new JButton("cot"); // create cot button
		sgn = new JButton("sgn"); // create sign button
		exp = new JButton("exp"); // create e button
		pi = new JButton("π"); // create pi button

		operations[0] = ln; // set button to ln
		operations[1] = log; // set button to logarithm
		operations[2] = facto; // set button to factorial
		operations[3] = leftP; // set button to left parenthesis
		operations[4] = rightP; // set button to right parenthesis
		operations[5] = leftP2; // set button to left sqaure parenthesis
		operations[6] = rightP2; // set button to right square parenthesis
		operations[7] = del; // set button to delete
		operations[8] = asin; // set button to arcsin
		operations[9] = sin; // set button to sin
		operations[10] = mod; // set button to modulo
		operations[11] = divide; // set button to divide
		operations[12] = clear; // set button to clear
		operations[13] = acos; // set button to arccos
		operations[14] = cos; // set button to cos
		operations[15] = expo; // set button to exponential
		operations[16] = multi; // set button to multiply
		operations[17] = exp; // set button to exp
		operations[18] = atan; // set button to arctan
		operations[19] = tan; // set button to tan
		operations[20] = expo2; // set button to square
		operations[21] = sub; // set button to subtraction
		operations[22] = pi; // set button to pi
		operations[23] = acot; // set button to arccot
		operations[24] = cot; // set button to cot
		operations[25] = sgn; // set button to sign
		operations[26] = decim; // set button to decimal
		operations[27] = negpos; // set button to negative positive
		operations[28] = add; // set button to addition
		operations[29] = equ; // set button to equal

		// create panels
		JPanel titlePanel = new JPanel(); // create panel for the title
		titlePanel.setBounds(270, 0, 270, 110); // set location
		titlePanel.setLayout(new BorderLayout()); // set layout
		titlePanel.setBackground(Color.black); // set background color

		JPanel operationPanel = new JPanel(); // create panel for the operation to show above the text field after
												// pressing equal button
		operationPanel.setBounds(125, 80, 570, 20); // set location
		operationPanel.setLayout(new BorderLayout()); // set layout
		operationPanel.setBackground(Color.black); // set background color to black

		JPanel panelButton = new JPanel(); // create panel for the buttons
		panelButton.setBounds(25, 180, 750, 400); // set size
		panelButton.setLayout(new GridLayout(5, 8, 10, 10)); // set the grid (5 rows, 8 columns, and 10px space for
																// each)
		panelButton.setBackground(Color.black); // set background color to black

		// title lable
		JLabel title = new JLabel(); // title text object
		title.setFont(new Font("Consolas", Font.BOLD, 48)); // set font
		title.setText("CALCULATOR"); // set text
		title.setForeground(Color.white); // set color

		// operation lable
		opr = new JLabel(); // create the operation label object
		opr.setFont(new Font("Consolas", Font.PLAIN, 20)); // set the font
		opr.setHorizontalAlignment(SwingConstants.RIGHT); // set the alignment to the right
		opr.setForeground(Color.lightGray); // set color to light gray

		// create colors
		final Color lightRed = new Color(255, 60, 60); // create new color for light red
		final Color darkGreen = new Color(0, 100, 35); // create new color for dark green
		final Color lightGray = new Color(225, 225, 225); // create new color for light gray
		final Color darkGray = new Color(25, 25, 25); // create new color for dark gray

		// set button properties
		for (int i = 0; i < operations.length; i++) { // loop for setting all of the operation buttons to the same
														// property
			operations[i].addActionListener(this); // add action listener to detect mouse clicks
			operations[i].setFont(font); // set font
			operations[i].setFocusable(false); // makes the square around the text invisible when we click the button
			operations[i].setBackground(darkGray); // set background color to dark gray
			operations[i].setForeground(Color.white); // set text color to white
		}

		operations[7].setForeground(lightRed); // set the delete button text to red
		operations[12].setForeground(lightRed); // set the clear button text to red
		operations[29].setBackground(darkGreen); // set the equal button background to dark green
		operations[29].setForeground(Color.white); // set the equal button text to black

		// set number properties
		for (int i = 0; i < 10; i++) { // loop for setting all of the number buttons to the same property
			numbers[i] = new JButton("" + i); // assaign values by turning integers into strings
			numbers[i].addActionListener(this); // add action listener
			numbers[i].setFont(font); // set font
			numbers[i].setFocusable(false); // makes the square around the text invisible when we click the button
			numbers[i].setBackground(lightGray); // set background color to white
			numbers[i].setForeground(Color.black); // set text color to black
		}

		// adding buttons to the window, they go from left to right, 8 per row
		panelButton.add(ln); // add ln button to the panel
		panelButton.add(log); // add log button to the panel
		panelButton.add(facto); // add factorial button to the panel
		panelButton.add(leftP); // add left parenthesis button to the panel
		panelButton.add(rightP); // add right parenthesis button to the panel
		panelButton.add(leftP2); // add left square parenthesis button to the panel
		panelButton.add(rightP2); // add right square parenthesis button to the panel
		panelButton.add(del); // add delete button to the panel
		panelButton.add(asin); // add arcsin button to the panel
		panelButton.add(sin); // add sin button to the panel
		panelButton.add(mod); // add modulo button to the panel
		panelButton.add(numbers[7]); // add number 7 button to the panel
		panelButton.add(numbers[8]); // add number 8 button to the panel
		panelButton.add(numbers[9]); // add number 9 button to the panel
		panelButton.add(divide); // add division button to the panel
		panelButton.add(clear); // add clear button to the panel
		panelButton.add(acos); // add arccos button to the panel
		panelButton.add(cos); // add cos button to the panel
		panelButton.add(expo); // add exponent button to the panel
		panelButton.add(numbers[4]); // add number 4 button to the panel
		panelButton.add(numbers[5]); // add number 5 button to the panel
		panelButton.add(numbers[6]); // add number 6 button to the panel
		panelButton.add(multi); // add multiply button to the panel
		panelButton.add(exp); // add exp button to the panel
		panelButton.add(atan); // add arctan button to the panel
		panelButton.add(tan); // add tan button to the panel
		panelButton.add(expo2); // add square button to the panel
		panelButton.add(numbers[1]); // add number 1 button to the panel
		panelButton.add(numbers[2]); // add number 2 button to the panel
		panelButton.add(numbers[3]); // add number 3 button to the panel
		panelButton.add(sub); // add subtraction button to the panel
		panelButton.add(pi); // add pi button to the panel
		panelButton.add(acot); // add arccot button to the panel
		panelButton.add(cot); // add cot button to the panel
		panelButton.add(sgn); // add sign button to the panel
		panelButton.add(numbers[0]); // add number 0 button to the panel
		panelButton.add(decim); // add decimal button to the panel
		panelButton.add(negpos); // add negative positive button to the panel
		panelButton.add(add); // add addition button to the panel
		panelButton.add(equ); // add equal button to the panel

		// adding components to window
		window.add(titlePanel); // add title panel
		titlePanel.add(title); // add the title to the panel
		window.add(operationPanel); // add the post-operation panel
		operationPanel.add(opr); // add the operation to the panel
		window.add(textField); // add the textfield
		window.add(panelButton); // add the button panel
		window.add(panel); // add the window panel
		window.pack(); // pack the panels

		window.setLocationRelativeTo(null); // set location of the window to the center of screen
		window.setVisible(true); // make the window visible
	}

	@Override // method for doing mouse click actions, such as printing numbers and operations to the screen
	public void actionPerformed(ActionEvent e) {

		// clear statement
		if (e.getSource() == clear) { // check if clear button is pressed
			textField.setText(""); // make the operation field empty
			opr.setText(""); // set the opr field empty

			operation = ""; // set the operation empty
			element.clear(); // clear elements
			result = ""; // clear result

			zeroDivision = false; // set the zero division to false
			invalidOperation = false; // set the invalid operation to false
			veryLargeNumber = false; // set the very large number to false
			isNum = false; // set is number to false
			hasDot = false; // set has dot to false
			parenthesisCounter = 0; // set number of parenthesis to 0
		}
		
		// if there are any errors, the calculator wont let you press any buttons
		if (zeroDivision == false && invalidOperation == false && veryLargeNumber == false) { 
			
			// loop for printing numbers
			for (int i = 0; i < 10; i++) { // loop through 0 to 9
				
				if (e.getSource() == numbers[i]) { // check if any button from 0 to 9 is pressed
					
					String currentOpr = textField.getText();

					if (currentOpr.length() > 0) {
						
						// check if the previous characters are any of these
						if (currentOpr.charAt(currentOpr.length() - 1) == 'e' || currentOpr.charAt(currentOpr.length() - 1) == 'π' || currentOpr.charAt(currentOpr.length() - 1) == '!') { 
							
							textField.setText(textField.getText() + "*" + i); // add the pressed numbers to screen
							operation += ",*"; // add * to the operation
							operation += "," + i; // add the number to operation
							
						} else if (isNum) { // check if its a number
							textField.setText(textField.getText() + i); // add the pressed numbers to screen
							operation += i; // add number to the next number (this is for creating numbers with multiple digits)
						} else {
							textField.setText(textField.getText() + i); // add the pressed numbers to screen
							operation += "," + i; // else add the number as a single digit number
						}
					} else {
						textField.setText(textField.getText() + i); // add the pressed numbers to screen
						operation += "," + i; // else add the number as a single digit number
					}

					isNum = true; // set is number to true
				}
			}
			
			// delete statement
			if (e.getSource() == del) { // check if the delete button is pressed
				
				String currentOpr = textField.getText(); // get the current operation on the screen
				String[] numbers = currentOpr.split("[^0-9.']"); // get numbers from 0 to 9
				String lastNumberLength = "0"; // get the last number from string
				String longOpr1 = "", longOpr3 = "", longOpr4 = "", longOpr7 = ""; // for deleting operations like sin, cos, asin, ln...
				
				int deletedChars = 1; // the characters to be deleted is 1 at default
				int deletedOperations = 2; // the operations to be deleted is 2 at default
				String newOperation = operation; // set the new operation as operation

				// check if the string is larger than or equal to 1 character (for inc/dec parenthesis counters)
				if (currentOpr.length() >= 1) { 
					
					longOpr1 = currentOpr.substring(currentOpr.length() - 1);


					if (longOpr1.equals("(")) { 
						
						deletedChars = 1; // set the number of characters deleted to 1
						parenthesisCounter -= 1; // decrease number of parenthesis by one
					}
					
					if (longOpr1.equals(")")) { 
						
						deletedChars = 1; // set the number of characters deleted to 1
						parenthesisCounter += 1; // increase number of parenthesis by one
					}

					if (longOpr1.equals("[")) {
						
						deletedChars = 1; // set the number of characters deleted to 1
						squParenthesisCounter -= 1; // decrease number of sqaure parenthesis by one
					}
					
					if (longOpr1.equals("]")) {
						
						deletedChars = 1; // set the number of characters deleted to 1
						squParenthesisCounter += 1; // increase number of square parenthesis by one
					}

					if (currentOpr.length() > 2 && (currentOpr.charAt(currentOpr.length() - 2) == '+'
							|| currentOpr.charAt(currentOpr.length() - 2) == '-'
							|| currentOpr.charAt(currentOpr.length() - 2) == '*'
							|| currentOpr.charAt(currentOpr.length() - 2) == '/'
							|| currentOpr.charAt(currentOpr.length() - 2) == '^'
							|| currentOpr.charAt(currentOpr.length() - 2) == '%')) { // check the previous operator
						
						isNum = false;
					} else if (currentOpr.charAt(currentOpr.length() - 1) == '0'
							|| currentOpr.charAt(currentOpr.length() - 1) == '1'
							|| currentOpr.charAt(currentOpr.length() - 1) == '2'
							|| currentOpr.charAt(currentOpr.length() - 1) == '3'
							|| currentOpr.charAt(currentOpr.length() - 1) == '4'
							|| currentOpr.charAt(currentOpr.length() - 1) == '5'
							|| currentOpr.charAt(currentOpr.length() - 1) == '6'
							|| currentOpr.charAt(currentOpr.length() - 1) == '7'
							|| currentOpr.charAt(currentOpr.length() - 1) == '8'
							|| currentOpr.charAt(currentOpr.length() - 1) == '9') { // check if the last character is a number
						
						deletedOperations = 1; // set the deleted operations to 1

						lastNumberLength = numbers[numbers.length - 1]; // get the length of the last number
						
						// check if the number length equals to 1
						if (lastNumberLength.length() == 1) { 
							
							deletedOperations = 2; // set the deleted operations to 2
						}
					}
					
					// check if the string is larger than or equal to 3 characters (for deleting ln)
					if (currentOpr.length() >= 3) { 
						
						longOpr3 = currentOpr.substring(currentOpr.length() - 3); // get the last three characters
						
						if (longOpr3.equals("ln(")) { // check if they are equal to ln(

							deletedChars = 3; // set the number of characters deleted to 3
							deletedOperations = 5; // set the number of operations deleted to 5
						}
						
						if (currentOpr.length() >= 4) { // check if the string is larger than or equal to 4 characters (for deleting sin, cos, tan, cot, sgn, log)
							
							longOpr4 = currentOpr.substring(currentOpr.length() - 4); // get the last four characters
							
							// check if they match with 4 char operators
							if (longOpr4.equals("sin(") || longOpr4.equals("cos(") || longOpr4.equals("tan(") 
									|| longOpr4.equals("cot(") || longOpr4.equals("log(") || longOpr4.equals("sgn(")) {

								deletedChars = 4; // set the number of characters deleted to 4
								deletedOperations = 6; // set the number of operations deleted to 6
							}
							
							// check if the string is larger than or equal to 7 characters (for deleting arcsin, arccos, arctan, arccot)
							if (currentOpr.length() >= 7) { 
								
								longOpr7 = currentOpr.substring(currentOpr.length() - 7); // get the last seven characters
								
								// check if they match with 7 char operators
								if (longOpr7.equals("arcsin(") || longOpr7.equals("arccos(") || longOpr7.equals("arctan(") || longOpr7.equals("arccot(") || longOpr7.equals("signum(")) {

									deletedChars = 7; // set the number of characters deleted to 7
									deletedOperations = 7; // set the number of operations deleted to 7
								}
							}
						}
					}
				}

				textField.setText(""); // temporarily set the operation field empty
				
				// loop through all the current operation - deleted characters
				for (int i = 0; i < currentOpr.length() - deletedChars; i++) { 
					
					textField.setText(textField.getText() + currentOpr.charAt(i)); // delete number of characters choosed
				}

				// loop through all the current operation - deleted characters
				for (int i = 0; i < newOperation.length() - 1; i++) { 
					
					operation = newOperation.substring(0, newOperation.length() - deletedOperations); // delete last character of the string
					
					 // check if the operation only has 1 char
					if (operation.length() == 1) {
						
						operation = ""; // set the operation empty
					}
				}
			}
			
			// decimal statement
			if (e.getSource() == decim) { // check if the decimal button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					
					if (isNum && !hasDot) { // check if its a number and doesnt have dot, this prevents you from typing numbers like: 1.2.3.4
						
						textField.setText(currentOpr + "."); // add . to the string
						operation += "."; // add . to the operation, this prevents
					} else if (!isNum && !hasDot) { // check if both of the these statements are false
						
						operation += ",."; // add . to the operation
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = true; // set is number to true
				hasDot = true; // set has dot to true
			}
			
			// negative-positive statement
			if (e.getSource() == negpos) { // check if the negative-positive button is pressed
				
				String currentOpr = textField.getText(); // get the current operation on the screen
				String[] numbers = currentOpr.split("[^0-9.-]"); // get numbers from 0 to 9

				if (currentOpr.equals("")) { // check if the operation is empty
					
					textField.setText(currentOpr); // do nothing if it is empty
				} else if (numbers.length > 0) { // check if the length
					
					String lastNumber = numbers[numbers.length - 1]; // get the last number from the string
					int firstIndex = currentOpr.lastIndexOf(lastNumber); // get the first index

					String newNumber; // variable for new number
					if (lastNumber.startsWith("-")) { // check if the number is already negative
						
						newNumber = lastNumber.substring(0, lastNumber.length()); // remove the '-' to make the number
																					// positive
						operation = operation.substring(0, firstIndex + lastNumber.length() + 1) + "," + newNumber;
					} else { // if the number is positive make the number negative
						
						newNumber = "-" + lastNumber; // add '-' before the number
						operation = operation.substring(0, firstIndex + lastNumber.length() + 1) + "," + newNumber;
					}

					String newOpr = currentOpr.substring(0, firstIndex) + newNumber + currentOpr.substring(firstIndex + lastNumber.length()); // creating the string for the new operation
					textField.setText(newOpr); // print the new operation to the screen
				}

				isNum = true; // set is number to true
				hasDot = false; // set has dot to false
			}
			
			// left parenthesis statement
			if (e.getSource() == leftP) { // check if the left parenthesis is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					
					if (numberCheck(currentOpr)) { // check if the last characters are these
						
						textField.setText(currentOpr + "*("); // add *( to the string
						operation = operation.substring(0, operation.length()); // add to the operation
						operation += ",*"; // add * to the operation
						operation += ",("; // add ( to the operation
					} else {
						
						textField.setText(textField.getText() + "("); // add parenthesis if the previous ones are not there
						operation += ",("; // add ( to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "("); // add parenthesis even if the operation is empty
					operation += ",("; // add ( to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// right parenthesis statement
			if (e.getSource() == rightP) { // check if the right parenthesis is pressed
				
				if (parenthesisCounter > 0) { // check if the parenthesis counter is larger than 0
					
					textField.setText(textField.getText() + ")"); // add ) to the operation field
					operation += ",)"; // add right parenthesis to the operation

					parenthesisCounter -= 1; // decrease number of parenthesis by one
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false5
			}
			
			// left parenthesis statement
			if (e.getSource() == leftP2) { // check if the left parenthesis is pressed
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					
					if (numberCheck(currentOpr)) { // check if the last characters are not these
						
						textField.setText(currentOpr + "*["); // add *( to the string
						operation = operation.substring(0, operation.length()); // add to the operation
						operation += ",*"; // add * to the operation
						operation += ",("; // add ( to the operation
					} else {
						
						textField.setText(textField.getText() + "["); // add parenthesis if the previous ones are not there
						operation += ",("; // add ( to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "["); // add parenthesis even if the operation is empty
					operation += ",("; // add ( to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				squParenthesisCounter += 1; // increase number of square parenthesis by one
			}
			
			// right parenthesis statement
			if (e.getSource() == rightP2) { // check if the right parenthesis is pressed
				
				if (squParenthesisCounter > 0) { // check if the square parenthesis counter is larger than 0
					
					textField.setText(textField.getText() + "]"); // add ) to the operation field
					operation += ",)"; // add right parenthesis to the operation

					squParenthesisCounter -= 1; // decrease number of sqaure parenthesis by one
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false5
			}
			
			// addition statement
			if (e.getSource() == add) { // check if the addition button is pressed
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					
					if (currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "+"); // add + to the new string
						operation = operation.substring(0, operation.length() - 2) + ",+";
						
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '('
							|| currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not +, ( or .
						
						textField.setText(currentOpr + "+"); // add + to the string
						operation += ",+"; // add + to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// subtraction statement
			if (e.getSource() == sub) { // check if the subtraction button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					
					if (currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "-"); // add - to the new string
						operation = operation.substring(0, operation.length() - 2) + ",-";
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '-' || currentOpr.charAt(currentOpr.length() - 1) == '(' || currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not -, ( or .
						
						textField.setText(currentOpr += "-"); // set the textfield with the new operation
						operation += ",-"; // add - to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// multiplication statement
			if (e.getSource() == multi) { // check if the multiplication button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					if (currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "*"); // add * to the new string
						operation = operation.substring(0, operation.length() - 2) + ",*";
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '*' || currentOpr.charAt(currentOpr.length() - 1) == '(' || currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not * or (
						
						textField.setText(currentOpr += "*"); // add * to the string
						operation += ",*"; // add * to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// division statement
			if (e.getSource() == divide) { // check if the division button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					
					if (currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr += "/"); // set the operation as the new operation
						operation = operation.substring(0, operation.length() - 2) + ",/";
						
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '/' || currentOpr.charAt(currentOpr.length() - 1) == '(' || currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not /, ( or .
						
						textField.setText(currentOpr += "/"); // set the textfield with the new operation
						operation += ",/"; // add / to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// factorial statement
			if (e.getSource() == facto) { // check if the factorial button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					if (currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "!"); // add ! to the new string
						operation = operation.substring(0, operation.length() - 2) + ",!";
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '!' || currentOpr.charAt(currentOpr.length() - 1) == '(' || currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not !, ( or .
						
						textField.setText(currentOpr + "!"); // add ! to the string
						operation += ",!"; // add ! to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// modulo statement
			if (e.getSource() == mod) { // check if the modulo button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					if (currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "%"); // add % to the new string
						operation = operation.substring(0, operation.length() - 2) + ",%";
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '%' || currentOpr.charAt(currentOpr.length() - 1) == '(' || currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not %, ( or .
						
						textField.setText(currentOpr + "%"); // add % to the string
						operation += ",%"; // add % to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// exponent statement
			if (e.getSource() == expo) { // check if the exponent button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					if (currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "^"); // add ^ to the new string
						operation = operation.substring(0, operation.length() - 2) + ",^";
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '^' || currentOpr.charAt(currentOpr.length() - 1) == '(' || currentOpr.charAt(currentOpr.length() - 1) == '.')) { // check if the last character is not ^, ( or .
						
						textField.setText(currentOpr + "^"); // add ^ to the string
						operation += ",^"; // add ^ to the operation
					} else {
						
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// square statement
			if (e.getSource() == expo2) { // check if the square button is pressed
				
				String currentOpr = textField.getText(); // get the current operation

				if (currentOpr.length() >= 1) { // check if the operation size larger than or equal to 1
					if (currentOpr.charAt(currentOpr.length() - 1) == '-'
							|| currentOpr.charAt(currentOpr.length() - 1) == '*'
							|| currentOpr.charAt(currentOpr.length() - 1) == '/'
							|| currentOpr.charAt(currentOpr.length() - 1) == '+'
							|| currentOpr.charAt(currentOpr.length() - 1) == '%'
							|| currentOpr.charAt(currentOpr.length() - 1) == '^') { // check if there are any of these operators
						
						String newOpr = currentOpr.substring(0, currentOpr.length() - 1); // create new string
						textField.setText(newOpr + "^2"); // add ^2 to the new string
						operation = operation.substring(0, operation.length() - 2) + ",^" + ",2";
					} else if (!(currentOpr.charAt(currentOpr.length() - 1) == '(')) { // check if the last character is not ^
						
						textField.setText(currentOpr + "^2"); // add ^ to the string
						operation += ",^"; // add ^ to the operation
						operation += ",2"; // add 2 to the operation
					} else {
						textField.setText(currentOpr); // do nothing if the operation is empty
					}
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// ln statement
			if (e.getSource() == ln) { // check if the ln button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*ln("); // add ln to the operation field
						operation += ",*";
						operation += ",ln,("; // add ln to the operation
					} else {
						
						textField.setText(textField.getText() + "ln("); // add ln to the operation field
						operation += ",ln,("; // add ln to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "ln("); // add ln to the operation field
					operation += ",ln,("; // add ln to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// log statement
			if (e.getSource() == log) { // check if the logarithm button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*log("); // add log to the operation field
						operation += ",*";
						operation += ",log,("; // add log to the operation
					} else {
						
						textField.setText(textField.getText() + "log("); // add log to the operation field
						operation += ",log,("; // add log to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "log("); // add log to the operation field
					operation += ",log,("; // add log to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// sin statement
			if (e.getSource() == sin) { // check if the sin button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*sin("); // add sin to the operation field
						operation += ",*";
						operation += ",sin,("; // add sin to the operation
					} else {
						
						textField.setText(textField.getText() + "sin("); // add sin to the operation field
						operation += ",sin,("; // add sin to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "sin("); // add sin to the operation field
					operation += ",sin,("; // add sin to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// cos statement
			if (e.getSource() == cos) { // check if the cos button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*cos("); // add cos to the operation field
						operation += ",*";
						operation += ",cos,("; // add cos to the operation
					} else {
						
						textField.setText(textField.getText() + "cos("); // add cos to the operation field
						operation += ",cos,("; // add cos to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "cos("); // add cos to the operation field
					operation += ",cos,("; // add cos to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// tan statement
			if (e.getSource() == tan) { // check if the tan button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*tan("); // add tan to the operation field
						operation += ",*";
						operation += ",tan,("; // add tan to the operation
					} else {
						
						textField.setText(textField.getText() + "tan("); // add tan to the operation field
						operation += ",tan,("; // add tan to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "tan("); // add tan to the operation field
					operation += ",tan,("; // add tan to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one

			}
			
			// cot statement
			if (e.getSource() == cot) { // check if the cot button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*cot("); // add cot to the operation field
						operation += ",*";
						operation += ",cot,("; // add cot to the operation
					} else {
						
						textField.setText(textField.getText() + "cot("); // add cot to the operation field
						operation += ",cot,("; // add cot to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "cot("); // add cot to the operation field
					operation += ",cot,("; // add cot to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// asin statement
			if (e.getSource() == asin) { // check if the arcsin button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*arcsin("); // add arcsin to the operation field
						operation += ",*";
						operation += ",asin,("; // add asin to the operation
					} else {
						
						textField.setText(textField.getText() + "arcsin("); // add arcsin to the operation field
						operation += ",asin,("; // add asin to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "arcsin("); // add arcsin to the operation field
					operation += ",asin,("; // add asin to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// acos statement
			if (e.getSource() == acos) { // check if the arccos button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*arccos("); // add arccos to the operation field
						operation += ",*";
						operation += ",acos,("; // add acos to the operation
					} else {
						
						textField.setText(textField.getText() + "arccos("); // add arccos to the operation field
						operation += ",acos,("; // add acos to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "arccos("); // add arccos to the operation field
					operation += ",acos,("; // add acos to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// atan statement
			if (e.getSource() == atan) { // check if the arctan button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*arctan("); // add arctan to the operation field
						operation += ",*";
						operation += ",atan,("; // add atan to the operation
					} else {
						
						textField.setText(textField.getText() + "arctan("); // add arctan to the operation field
						operation += ",atan,("; // add atan to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "arctan("); // add arctan to the operation field
					operation += ",atan,("; // add atan to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// acot statement
			if (e.getSource() == acot) { // check if the arccot button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*arccot("); // add arccot to the operation field
						operation += ",*";
						operation += ",acot,("; // add acot to the operation
					} else {
						
						textField.setText(textField.getText() + "arccot("); // add arccot to the operation field
						operation += ",acot,("; // add acot to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "arccot("); // add arccot to the operation field
					operation += ",acot,("; // add acot to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// sign statement
			if (e.getSource() == sgn) { // check if the signum button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*signum("); // add signum to the operation field
						operation += ",*";
						operation += ",sign,("; // add sign to the operation
					} else {
						
						textField.setText(textField.getText() + "signum("); // add signum to the operation field
						operation += ",sign,("; // add sign to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "signum("); // add signum to the operation field
					operation += ",sign,("; // add sign to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
				parenthesisCounter += 1; // increase number of parenthesis by one
			}
			
			// pi statement
			if (e.getSource() == pi) { // check if the π button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*π"); // add pi to the operation field
						operation += ",*";
						operation += ",p"; // add p to the operation
					} else {
						
						textField.setText(textField.getText() + "π"); // add pi to the operation field
						operation += ",p"; // add p to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "π"); // add pi to the operation field
					operation += ",p"; // add p to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// exp statement
			if (e.getSource() == exp) { // check if the e button is pressed
				
				String currentOpr = textField.getText();

				if (currentOpr.length() > 0) {
					if (numberCheck(currentOpr)) {
						
						textField.setText(textField.getText() + "*e"); // add e to the operation field
						operation += ",*";
						operation += ",e"; // add e to the operation
					} else {
						
						textField.setText(textField.getText() + "e"); // add e to the operation field
						operation += ",e"; // add e to the operation
					}
				} else {
					
					textField.setText(textField.getText() + "e"); // add e to the operation field
					operation += ",e"; // add e to the operation
				}

				isNum = false; // set is number to false
				hasDot = false; // set has dot to false
			}
			
			// equal statement
			if (e.getSource() == equ) { // check if the equal button is pressed
				
				result = textField.getText(); // get the operation
				opr.setText(textField.getText() + "="); // type the operation at top of the panel

				calculateMain(); // calculations
				checkInvalidOperation(result); // check if there are any invalid operations

				textField.setText(""); // clear the textfield
				operation = ""; // set the operation empty
				element.clear(); // clear the elements

				if (zeroDivision == true) { // check if there are any divisions by zero
					
					textField.setText("Division by 0 is not possible");
				} else if (invalidOperation == true) { // check if there are any invalid operations
					
					textField.setText("Invalid Operation");
				} else if (veryLargeNumber == true) {
					
					textField.setText("Result is bigger than 15 digits");
				} else { // else print the result
					
					operation += "," + result; // add the result to the operation so we can continue operating with it
					textField.setText(result); // set the the text field as the result
				}

				isNum = true; // set is num to true

				parenthesisCounter = 0; // set parenthesis counter to 0
				squParenthesisCounter = 0; // set square parenthesis counter to 0
			}

		}
		
		System.out.println(operation); //display at console
	}

	// method for checking if the last character is a number
	public boolean numberCheck(String operation) { 
		
		if (operation.charAt(operation.length() - 1) == '0' || operation.charAt(operation.length() - 1) == '1'
				|| operation.charAt(operation.length() - 1) == '2' || operation.charAt(operation.length() - 1) == '3'
				|| operation.charAt(operation.length() - 1) == '4' || operation.charAt(operation.length() - 1) == '5'
				|| operation.charAt(operation.length() - 1) == '6' || operation.charAt(operation.length() - 1) == '7'
				|| operation.charAt(operation.length() - 1) == '8' || operation.charAt(operation.length() - 1) == '9'
				|| operation.charAt(operation.length() - 1) == '!' || operation.charAt(operation.length() - 1) == 'e'
				|| operation.charAt(operation.length() - 1) == 'π') { // check if the last characters are these)
			
			return true; // return true if it a number
		} else {
			
			return false; // return false if it is not
		}
	}

	// method for checking if we are using a valid operator
	public boolean operatorCheck(String operator) { 
		
		if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")
				|| operator.equals("%") || operator.equals("^") || operator.equals("!") || operator.equals("sign")
				|| operator.equals("sin") || operator.equals("cos") || operator.equals("tan") || operator.equals("cot")
				|| operator.equals("asin") || operator.equals("acos") || operator.equals("atan")
				|| operator.equals("acot") || operator.equals("ln") || operator.equals("log")) {
			
			return true; // return true if it is a valid operator
		} else {
			
			return false; // return false if it is not
		}
	}

	// method for setting priority for operators
	public int processPriority(String operator) { 
		
		int priority = -1;

		switch (operator) {
			case "+":
			case "-":
				priority = 1; // set the priority to 1 if + or -
				break;
			case "*":
			case "/":
			case "%":
				priority = 2; // set the priority to 2 if *, / or %
				break;
			case "ln":
			case "log":
			case "sin":
			case "cos":
			case "tan":
			case "cot":
			case "asin":
			case "acos":
			case "atan":
			case "acot":
			case "sign":
				priority = 3; // set the priority to 3 if any of the operations above
				break;
			case "^":
				priority = 4; // set the priority to 4 if ^
				break;
			case "!":
				priority = 5; // set the priority to 5 if !
				break;
		}

		return priority; // return the value
	}

	// calculation method for 2 numbers
	public double calculateValueTwoNumbers(double number1, double number2, String operator) { 
		double result = 0;

		switch (operator) {
			case "+": // addition
				result = number1 + number2;
				break;
			case "-": // subtraction
				result = number1 - number2;
				break;
			case "*": // multiplication
				result = number1 * number2;
				break;
			case "/": // division
				result = number1 / number2;
				break;
			case "^": // exponent
				result = Math.pow(number1, number2);
				break;
			case "%": // modulo
				result = number1 % number2;
				break;
			default:
				result = 0;
		}

		return result; // return the value
	}

	// calculation method for a single number
	public double calculateValueOneNumber(double number, String operator) {
		
		double result = 0;

		switch (operator) {
			case "sin": // sin
				result = Math.sin(Math.toRadians(number));
				break;
			case "cos": // cos
				result = Math.cos(Math.toRadians(number));
				break;
			case "tan": // tan
				result = Math.tan(Math.toRadians(number));
				break;
			case "cot": // cot
				result = 1 / Math.tan(Math.toRadians(number));
				break;
			case "asin": // asin
				result = Math.asin(Math.toRadians(number));
				break;
			case "acos": // acos
				result = Math.acos(Math.toRadians(number));
				break;
			case "atan": // atan
				result = Math.atan(Math.toRadians(number));
				break;
			case "acot": // acot
				result = Math.atan(1 / Math.toRadians(number));
				break;
			case "sign": // sign
				result = Math.signum(number);
				break;
			case "log": // log
				result = Math.log10(number);
				break;
			case "ln": // ln
				result = Math.log(number);
				break;
			case "!": // !
				result = calculateFactorial(number);
				break;
		}

		return result; // return the value
	}

	// Math library doesnt have factorial calculation, so we implement it
	public double calculateFactorial(double number) {
		
		double facto = 1;

		if (number == 0 || number == 1) { // if the number is equa to 1 or 0, return 1
			
			number = 1;
		} else {
			
			for (int i = 2; i <= number; i++) { // keep multiplying the number with itself - 1 until it reaches 2
				
				facto *= i;
			}
		}
		return facto; // return the value
	}

	// method for deleting the trailing zeros in decimal numbers, like 2.45200 will be seen as 2.45
	public String removeTrailingZeros(double number) {
		
		DecimalFormat decimalFormat = new DecimalFormat("0.#####"); // create a DecimalFormat with the desired format

		String newNum = decimalFormat.format(number); // format the number, removing trailing zeros
		String dottedNum = newNum.replace(',', '.'); // replace commas with dots

		isNum = true; // set is number to true

		return dottedNum; // return the number
	}

	// method for checking if there are any invalid operations
	public void checkInvalidOperation(String result) { 
		
		// check the result if at contains anything different than a number (excluding ,)
		for (char digit : result.toCharArray()) { 
			
			if (Character.isDigit(digit) == true) {
				
				invalidOperation = false; // set invalid operation to false
			} else {
				
				invalidOperation = true; // set invalid operation to true
			}
		}
	}

	// method for getting the finals value from the postfix expression
	public double getValue(String operation) { 

		String elements[] = operation.split(","); // split all the elements with ',' sign
		ArrayList<String> elements2 = new ArrayList<>(); // create new array list for elements

		// loop through all elements
		for (int i = 0; i < elements.length; i++) { 
			
			if (!elements[i].equals("")) { // check if the element is not empty
				
				elements2.add(elements[i]); // adding elements to element array list from operation
			}
		}

		Stack<Double> oprStack = new Stack<>(); // create stack for storing operators
		double num1, num2; // create numbers as doubles

		// loop through all elements
		for (int i = 0; i < elements2.size(); i++) { 
			
			String operator = elements2.get(i); // create new string to get the current operator

			if (operatorCheck(operator)) { // check if the operator is valid
				if (operator.equals("sin") || operator.equals("cos") || operator.equals("tan") || operator.equals("cot")
						|| operator.equals("asin") || operator.equals("acos") || operator.equals("atan")
						|| operator.equals("acot") || operator.equals("log") || operator.equals("ln")
						|| operator.equals("sign") || operator.equals("!")) { // check if the operation contains a
																				// single number
					num2 = oprStack.pop(); // get the last number from stack

					if (operator.equals("tan") && num2 == 90) {
						
						invalidOperation = true;
						System.out.println("TAN90 DETECTED	");
						break;
					}

					oprStack.push(calculateValueOneNumber(num2, operator)); // calculate the value
					
				} else { // operations for 2 numbers
					
					num2 = oprStack.pop(); // get the last number from stack
					num1 = oprStack.pop(); // get the second last number from stack

					if (operator.equals("/") && num2 == 0) { // check if there are any attempts to divide by zero
						
						zeroDivision = true; // set the zero division to true
						break; // break out of the loop
					}

					oprStack.push(calculateValueTwoNumbers(num1, num2, operator)); // push the calculation into the
																					// stack
				}
			} else {
				
				if (operator.equals("p")) { // check if the operator is pi
					
					oprStack.push(Math.PI); // push pi into the stack
				} else if (operator.equals("e")) { // check if the operator is e
					
					oprStack.push(Math.E); // push e into the stack
				} else {
					
					oprStack.push(Double.valueOf(operator)); // push the double value of the operator
				}
			}
		}

		double result = 0; // declare result as 0

		// loop until the stack is empty
		while (!oprStack.empty()) {
			
			result += oprStack.pop(); // update result
		}

		return result; // return the final result
	}

	// method for turning an infix expression into postfix
	public String infixToPostfix() { 
		
		Stack<String> operatorStack = new Stack<>(); // creating operator stack
		String currentOperator; // string for getting current operator
		String postfix = ""; // string for storing the postfix expression
		int label; // create label
		element.add(")"); // add ) to the elements
		operatorStack.push("("); // push ( to the stack

		// loop through all elements
		for (int i = 0; i < element.size(); i++) { 
			
			String oprToPush = element.get(i); // get the operator to be pushed

			if (oprToPush.equals("(")) { // check if the operator is equal to (
				
				operatorStack.push(oprToPush); // push it into the stack
			} else if (oprToPush.equals(")")) { // check if the operator is equal to )
				
				currentOperator = operatorStack.pop(); // get the current operator

				while (!currentOperator.equals("(")) { // loop until the current operator is not equal to (
					
					postfix += currentOperator + ","; // update the postfix expression
					currentOperator = operatorStack.pop(); // get the current operator from stack
				}
			} else if (operatorCheck(oprToPush)) { // check if the operator is a valid operator
				
				currentOperator = operatorStack.pop(); // get the current operator from stack
				label = 0; // set the label to 0
				if (operatorCheck(currentOperator) && processPriority(currentOperator) > processPriority(oprToPush)) { // check the process priority of operators
					
					postfix += currentOperator + ","; // update the postfix expression
					label = 1; // set the label to 1
				}

				if (label == 0) { // check if the label is equal to 0
					
					operatorStack.push(currentOperator); // push the operator into stack
				}

				operatorStack.push(oprToPush); // push the operator into stack
			} else {
				
				postfix += oprToPush + ","; // update the postfix expression
			}
		}

		// loop until the stack is empty
		while (!operatorStack.empty()) { 
			
			currentOperator = operatorStack.pop(); // get the last operator from the stack

			if (!currentOperator.equals("(") && !currentOperator.equals(")")) { // check if they are not equal to '('
																				// and ')'
				postfix += currentOperator + ","; // add current operator and ',' to the postfix expression
			}
		}

		return postfix; // return the postfix expression
	}

	// main method for calculations
	public void calculateMain() { 

		String elements[] = operation.split(","); // split all of the elements with ','
		
		for (int i = 0; i < elements.length; i++) { // iterate through all elements
			
			element.add(elements[i]); // adding elements to element array list from operation
		}

		try {
			
			double res = getValue(infixToPostfix()); // get the result with our methods
			result = removeTrailingZeros(res); // format the result so we get rid of decimal points
		} catch (Exception e) { };

		if (result.equals("-0") || result.equals("+0")) {
			
			result = "0";
		} else if (result == "Infinity") {
			
			invalidOperation = true;
		}
	}

	// main method
	public static void main(String[] args) {

		Calculator calculator = new Calculator();
		calculator.toString();
	}
}
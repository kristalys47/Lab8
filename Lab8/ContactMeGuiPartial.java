import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ContactMeGuiPartial extends JFrame
{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	private JLabel emailL, passwordL,reEnterPassL, messageL, completeName;
	private JTextField completeNameTF, emailTF, messageTF;
	private JTextField passwordTF,reEnterPassTF;
	private JButton sendB, exitB, clearB;

	private SendButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;

	private JMenuBar  menuBar;
	private JMenu make;
	private JMenuItem byEmail;
	private JMenuItem byFax, byCellPhone, byRegularMail;

	public ContactMeGuiPartial()
	{
		completeName = new JLabel ("Name: ", SwingConstants.CENTER);
		emailL = new JLabel("Email: ", SwingConstants.CENTER);
		passwordL = new JLabel("Password: ", SwingConstants.CENTER);
		reEnterPassL= new JLabel("Re-enter Password: ", SwingConstants.CENTER);
		messageL = new JLabel("Addtional Info: ", SwingConstants.CENTER);

		completeNameTF = new JTextField();
		emailTF = new JTextField();
		passwordTF = new JPasswordField();
		reEnterPassTF = new JPasswordField();
		//Practicing Inner Classes
		//Implemented when enter is hit
				reEnterPassTF.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if (!passwordTF.getText().equals(reEnterPassTF.getText())) {
							JOptionPane.showMessageDialog(null, "Passwords don't match."); 
						}
						if (passwordTF.getText().equals("") && reEnterPassTF.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Fill password fields.");
						}
					}
				});
				
		
		messageTF = new JTextField();
		//SPecify handlers for each button and add (register) ActionListeners to each button.
		sendB = new JButton("Send");
		sbHandler = new SendButtonHandler();
		sendB.addActionListener(sbHandler);

		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		clearB = new JButton("Clear");


		menuBar = new JMenuBar();
		make = new JMenu("            Contact Me                 ");
		byEmail = new JMenuItem("by Email");
		byFax = new JMenuItem("by Fax");
		byCellPhone = new JMenuItem("by Cellphone");
		byRegularMail = new JMenuItem("by Regular Mail");
		
		menuBar.add(make);
		make.add(byEmail);
		make.add(byFax);
		make.add(byCellPhone);
		make.add(byRegularMail);

		setTitle("Registration Form");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(7, 4));

		//Add things to the pane in the order you want them to appear (left to right, top to bottom)
		pane.add(completeName);
		pane.add(completeNameTF);
		pane.add(emailL);
		pane.add(emailTF);
		pane.add(passwordL);
		pane.add(passwordTF);
		pane.add(reEnterPassL);
		pane.add(reEnterPassTF);
		pane.add(messageL);
		pane.add(messageTF);

		pane.add(menuBar);

		pane.add(clearB);
		pane.add(sendB);
		pane.add(exitB);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private class SendButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//We use the getText & setText methods to manipulate the data entered into those fields.
			String inputnameComplete = completeNameTF.getText();
			String inputEmail= emailTF.getText();
			String inputPassword= passwordTF.getText();
			String reinputPassword= reEnterPassTF.getText();
			String inputMessage= messageTF.getText();
			


			if(inputEmail.equals("") || inputPassword.equals("") || reinputPassword.equals("") || inputnameComplete.equals("")){

				//Display error message
				JOptionPane.showMessageDialog(null,
						"Wrong input. Rememeber to fill all the spaces",
						"Something is missing!!!",
						JOptionPane.WARNING_MESSAGE);
			}

			//the password is too long
			else if(inputPassword.length() > 11){
				JOptionPane.showMessageDialog(null,"Wrong password. Rememeber it is 10 characters long",
						"Wrong Password Input!!!", JOptionPane.WARNING_MESSAGE);
			}

			//the message is too long
			else if(inputMessage.length() > 81){
				JOptionPane.showMessageDialog(null,"Wrong Message. Rememeber it is 80 characters long",
						"Wrong Message Input!!!", JOptionPane.WARNING_MESSAGE);
			}
			//all is correct
			else if (!emailHasAt())
				JOptionPane.showMessageDialog(null,"Email address is wrong.",
						"Wrong Message Input!!!", JOptionPane.WARNING_MESSAGE);
			else
			{
				JOptionPane.showMessageDialog(null,
						"Your message has been sent.");
			}

			//massageTF.setText("" + area);
		}
	}

	public boolean emailHasAt() {
		String inputEmail = emailTF.getText();
		for (int i = 0; i < inputEmail.length(); i++) {
			if (inputEmail.contains("@")) {
				return true;
			}
		}
		return false;
	}
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	
	public static void main(String[] args)
	{
		ContactMeGuiPartial contactMe = new ContactMeGuiPartial();
	}
}
package hotelReservation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;

public class MainFrame extends JFrame implements ActionListener {

	public static final int WIDTH = 450;
	public static final int HEIGHT = 400;
	public static final int XCOORD = 100;
	public static final int YCOORD = 80;

	JLabel checkInTitle;
	JLabel checkInLabel;
	JLabel checkOutLabel;
	JLabel typeOfRoomLabel;
	JLabel specificRoomLabel;
	JLabel locationOfRoomLabel;
	JLabel numberOfPeopleLabel;
	JLabel additionalChargesLabel;
	JLabel checkOutTitle;
	JLabel roomNumberLabel;
	JLabel guestInfoLabel;
	JLabel descriptionOfRoomLabel;
	JLabel firstNameOfGuestLabel;
	JLabel lastNameOfGuestLabel;
	JLabel address1Label;
	JLabel address2Label;
	JLabel cityLabel;
	JLabel stateLabel;
	JLabel zipCodeLabel;
	JLabel phoneLabel;
	JLabel emailLabel;

	JComboBox<String> typeOfRoom;
	JComboBox<String> specificRoom;
	JComboBox<String> locationOfRoom;
	JComboBox<Integer> numberOfPeople;

	final JTextField checkInTextField;
	final JTextField checkOutTextField;
	final JTextField roomNumberTextField;
	final JTextField telephoneTextField;
	final JTextField roomServiceTextField;
	final JTextField equestrianAdventureTextField;
	final JTextField restaurantTextField;
	final JTextField firstNameOfGuestTextField;
	final JTextField lastNameOfGuestTextField;
	final JTextField address1TextField;
	final JTextField address2TextField;
	final JTextField cityTextField;
	final JTextField stateTextField;
	final JTextField zipCodeTextField;
	final JTextField phoneTextField;
	final JTextField emailTextField;

	JButton CheckAvailabilityButton;
	JButton CheckInOption;
	JButton CheckOutOption;
	JButton toMainScreenButton;
	JButton proceedToCheckout;
	JButton generateBill;
	JButton makeReservation;

	JCheckBox telephoneCheckBox;
	JCheckBox roomServiceCheckBox;
	JCheckBox equestrianAdventureCheckBox;
	JCheckBox restaurantCheckBox;
	
	String toR;
	String chaR;
	String loR;
	String noP;
	String fName;
	String lName;
	String add1;
	String add2;
	String city;
	String state;
	String zip;
	String phone;
	String email;
	String ccNum;
	String checkIn;//date
	String checkOut;//date
	int roomID;
	int guestID;
	int roomNumber;
	

	public MainFrame() {

		checkInTitle = new JLabel("CHECK-IN:");
		checkInTitle.setFont(new Font("Courier", Font.BOLD, 17));
		checkInTitle.setBounds(10, 0, 100, 50);
		checkInTitle.setVisible(false);

		checkInLabel = new JLabel("Check-in Date:");
		checkInTextField = new JTextField(10);

		checkInLabel.setBounds(10, 30, 100, 50);
		checkInLabel.setVisible(false);
		checkInTextField.setBounds(100, 45, 100, 20);
		checkInTextField.setVisible(false);

		checkOutLabel = new JLabel("Check-out Date:");
		checkOutTextField = new JTextField(10);

		checkOutLabel.setBounds(10, 60, 100, 50);
		checkOutLabel.setVisible(false);
		checkOutTextField.setBounds(110, 75, 100, 20);
		checkOutTextField.setVisible(false);

		typeOfRoomLabel = new JLabel("Type of Room:");
		typeOfRoom = new JComboBox<String>();

		typeOfRoomLabel.setBounds(10, 90, 130, 50);
		typeOfRoomLabel.setVisible(false);
		typeOfRoom.setBounds(100, 105, 80, 20);
		typeOfRoom.setVisible(false);

		typeOfRoom.addItem("Luxury");
		typeOfRoom.addItem("Cottage");
		// typeOfRoom.addItem("Any");
		typeOfRoom.setSelectedItem(null); // Doesn't select any option by
											// default in the ComboBox.

		specificRoomLabel = new JLabel("Characteristics:");
		specificRoom = new JComboBox<String>();
		specificRoomLabel.setBounds(10, 120, 130, 50);
		specificRoomLabel.setVisible(false);
		specificRoom.setBounds(110, 135, 120, 20);
		specificRoom.setVisible(false);

		typeOfRoom.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				specificRoom.removeAllItems(); // Clears the ComboBox
				toR = typeOfRoom.getSelectedItem().toString(); // Gets the selected option from Type of Room.

				if (toR == "Luxury") {
					specificRoom.addItem("1 Queen Bed");
					specificRoom.addItem("2 Queen Beds");
					specificRoom.addItem("Two Room");
					specificRoom.addItem("Three Room");
					specificRoom.addItem("Bridal");

				} else if (toR == "Cottage") {
					specificRoom.addItem("Two Room");
					specificRoom.addItem("Three Room");
					specificRoom.addItem("Four Room");
				}
			}
		});

		locationOfRoomLabel = new JLabel("Location of Room:");
		locationOfRoom = new JComboBox<String>();

		locationOfRoomLabel.setBounds(10, 150, 130, 50);
		locationOfRoomLabel.setVisible(false);
		locationOfRoom.setBounds(120, 165, 75, 20);
		locationOfRoom.setVisible(false);

		locationOfRoom.addItem("Patio");
		locationOfRoom.addItem("Forest");
		// locationOfRoom.addItem("Any");
		locationOfRoom.setSelectedItem(null); // Doesn't select any option by
												// default in the ComboBox.

		numberOfPeopleLabel = new JLabel("Number of People:");
		numberOfPeople = new JComboBox<Integer>();

		numberOfPeopleLabel.setBounds(10, 180, 130, 50);
		numberOfPeopleLabel.setVisible(false);
		numberOfPeople.setBounds(120, 195, 45, 20);
		numberOfPeople.setVisible(false);

		numberOfPeople.addItem(1);
		numberOfPeople.addItem(2);
		numberOfPeople.addItem(3);
		numberOfPeople.addItem(4);
		numberOfPeople.addItem(5);
		numberOfPeople.addItem(6);
		numberOfPeople.addItem(7);
		numberOfPeople.setSelectedItem(null); // Doesn't select any option by default in the ComboBox.

		CheckAvailabilityButton = new JButton("Check Availability");
		CheckAvailabilityButton.setBounds(20, 240, 150, 25);
		CheckAvailabilityButton.setVisible(false);
		CheckAvailabilityButton.addActionListener(this);
		
		toMainScreenButton = new JButton("To Main Screen");
		toMainScreenButton.setBounds(300, 320, 125, 25);
		toMainScreenButton.setVisible(false);
		toMainScreenButton.addActionListener(this);

		CheckInOption = new JButton("Check-In");
		CheckInOption.setBounds(140, 110, 150, 25);
		CheckInOption.addActionListener(this);

		CheckOutOption = new JButton("Check-Out");
		CheckOutOption.setBounds(140, 160, 150, 25);
		CheckOutOption.addActionListener(this);

		checkOutTitle = new JLabel("CHECK-OUT:");
		checkOutTitle.setFont(new Font("Courier", Font.BOLD, 17));
		checkOutTitle.setBounds(10, 0, 100, 50);
		checkOutTitle.setVisible(false);

		roomNumberLabel = new JLabel("Room Number:");
		roomNumberTextField = new JTextField(10);

		roomNumberLabel.setBounds(10, 30, 100, 50);
		roomNumberLabel.setVisible(false);
		roomNumberTextField.setBounds(100, 45, 100, 20);
		roomNumberTextField.setVisible(false);

		proceedToCheckout = new JButton("Continue");
		proceedToCheckout.setBounds(10, 80, 120, 25);
		proceedToCheckout.setVisible(false);
		proceedToCheckout.addActionListener(this);

		additionalChargesLabel = new JLabel("Additional Charges:");
		additionalChargesLabel.setFont(new Font("Courier", Font.BOLD, 17));
		additionalChargesLabel.setBounds(120, 0, 200, 50);
		additionalChargesLabel.setVisible(false);

		telephoneCheckBox = new JCheckBox("Telephone");
		telephoneTextField = new JTextField(10);

		telephoneCheckBox.setBounds(10, 40, 90, 20);
		telephoneCheckBox.setVisible(false);
		telephoneTextField.setBounds(10, 60, 80, 20);
		telephoneTextField.setVisible(false);
		telephoneTextField.setText("0");

		roomServiceCheckBox = new JCheckBox("Room Service");
		roomServiceTextField = new JTextField(10);

		roomServiceCheckBox.setBounds(10, 40 * 2, 110, 20);
		roomServiceCheckBox.setVisible(false);
		roomServiceTextField.setBounds(10, 100, 80, 20);
		roomServiceTextField.setVisible(false);
		roomServiceTextField.setText("0");

		equestrianAdventureCheckBox = new JCheckBox("Equestrian Adventure");
		equestrianAdventureTextField = new JTextField(10);

		equestrianAdventureCheckBox.setBounds(10, 40 * 3, 150, 20);
		equestrianAdventureCheckBox.setVisible(false);
		equestrianAdventureTextField.setBounds(10, 140, 80, 20);
		equestrianAdventureTextField.setVisible(false);
		equestrianAdventureTextField.setText("0");

		restaurantCheckBox = new JCheckBox("Restaurant Service");
		restaurantTextField = new JTextField(10);

		restaurantCheckBox.setBounds(10, 40 * 4, 140, 20);
		restaurantCheckBox.setVisible(false);
		restaurantTextField.setBounds(10, 180, 80, 20);
		restaurantTextField.setVisible(false);
		restaurantTextField.setText("0");

		generateBill = new JButton("Generate Bill");
		generateBill.setBounds(10, 320, 120, 25);
		generateBill.setVisible(false);
		generateBill.addActionListener(this);
		
		guestInfoLabel = new JLabel("Guest Information:");
		guestInfoLabel.setFont(new Font("Courier", Font.BOLD, 17));
		guestInfoLabel.setBounds(100, 0, 200, 50);
		guestInfoLabel.setVisible(false);
		
		descriptionOfRoomLabel = new JLabel();
		descriptionOfRoomLabel.setBounds(10, 20, 200, 100);
		descriptionOfRoomLabel.setVisible(false);
			
		firstNameOfGuestLabel = new JLabel("First Name: ");
		firstNameOfGuestTextField = new JTextField(10);
		
		firstNameOfGuestLabel.setBounds(10, 70, 100, 100);
		firstNameOfGuestLabel.setVisible(false);
		firstNameOfGuestTextField.setBounds(80, 110, 110, 20);
		firstNameOfGuestTextField.setVisible(false);
		
		lastNameOfGuestLabel = new JLabel("Last Name: ");
		lastNameOfGuestTextField = new JTextField(10);
		
		lastNameOfGuestLabel.setBounds(10, 95, 100, 100);
		lastNameOfGuestLabel.setVisible(false);
		lastNameOfGuestTextField.setBounds(80, 135, 110, 20);
		lastNameOfGuestTextField.setVisible(false);
		
		address1Label = new JLabel("Address 1: ");
		address1TextField = new JTextField(10);
		
		address1Label.setBounds(10, 120, 100, 100);
		address1Label.setVisible(false);
		address1TextField.setBounds(80, 160, 110, 20);
		address1TextField.setVisible(false);
		
		address2Label = new JLabel("Address 2: ");
		address2TextField = new JTextField(10);
		
		address2Label.setBounds(10, 145, 100, 100);
		address2Label.setVisible(false);
		address2TextField.setBounds(80, 185, 110, 20);
		address2TextField.setVisible(false);
		
		cityLabel = new JLabel("City: ");
		cityTextField = new JTextField(10);
		
		cityLabel.setBounds(10, 170, 100, 100);
		cityLabel.setVisible(false);
		cityTextField.setBounds(80, 210, 110, 20);
		cityTextField.setVisible(false);
		
		stateLabel = new JLabel("State: ");
		stateTextField = new JTextField(10);
		
		stateLabel.setBounds(10, 195, 100, 100);
		stateLabel.setVisible(false);
		stateTextField.setBounds(80, 235, 110, 20);
		stateTextField.setVisible(false);
		
		phoneLabel = new JLabel("Phone: ");
		phoneTextField = new JTextField(10);
		
		phoneLabel.setBounds(10, 220, 100, 100);
		phoneLabel.setVisible(false);
		phoneTextField.setBounds(80, 260, 110, 20);
		phoneTextField.setVisible(false);
		
		zipCodeLabel = new JLabel("Zip Code: ");
		zipCodeTextField = new JTextField(10);
		
		zipCodeLabel.setBounds(10, 245, 100, 100);
		zipCodeLabel.setVisible(false);
		zipCodeTextField.setBounds(80, 285, 110, 20);
		zipCodeTextField.setVisible(false);
		
		emailLabel = new JLabel("Email: ");
		emailTextField = new JTextField(10);
		
		emailLabel.setBounds(10, 270, 100, 100);
		emailLabel.setVisible(false);
		emailTextField.setBounds(80, 310, 110, 20);
		emailTextField.setVisible(false);
		
		makeReservation = new JButton("Make Reservation");
		makeReservation.setBounds(235, 65, 160, 35);
		makeReservation.setVisible(false);
		makeReservation.addActionListener(this);
		
		

		JPanel p = new JPanel();
		p.setLayout(null);

		final JFrame frame = new JFrame();

		p.add(checkInTitle);
		p.add(checkInLabel);
		p.add(checkInTextField);
		p.add(checkOutLabel);
		p.add(checkOutTextField);
		p.add(typeOfRoom);
		p.add(typeOfRoomLabel);
		p.add(specificRoomLabel);
		p.add(specificRoom);
		p.add(locationOfRoomLabel);
		p.add(locationOfRoom);
		p.add(numberOfPeople);
		p.add(numberOfPeopleLabel);
		p.add(CheckAvailabilityButton);
		p.add(CheckOutOption);
		p.add(CheckInOption);
		p.add(toMainScreenButton);
		p.add(checkOutTitle);
		p.add(roomNumberLabel);
		p.add(roomNumberTextField);
		p.add(proceedToCheckout);
		p.add(additionalChargesLabel);
		p.add(telephoneCheckBox);
		p.add(telephoneTextField);
		p.add(roomServiceCheckBox);
		p.add(roomServiceTextField);
		p.add(equestrianAdventureCheckBox);
		p.add(equestrianAdventureTextField);
		p.add(restaurantCheckBox);
		p.add(restaurantTextField);
		p.add(generateBill);
		p.add(guestInfoLabel);
		p.add(descriptionOfRoomLabel);
		p.add(firstNameOfGuestLabel);
		p.add(firstNameOfGuestTextField);
		p.add(lastNameOfGuestLabel);
		p.add(lastNameOfGuestTextField);
		p.add(address1Label);
		p.add(address1TextField);
		p.add(address2Label);
		p.add(address2TextField);
		p.add(phoneLabel);
		p.add(phoneTextField);
		p.add(cityLabel);
		p.add(cityTextField);
		p.add(stateLabel);
		p.add(stateTextField);
		p.add(zipCodeLabel);
		p.add(zipCodeTextField);
		p.add(emailLabel);
		p.add(emailTextField);
		p.add(makeReservation);

		frame.add(p);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(XCOORD, YCOORD);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		checkInTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String selectedDate = new DatePicker(frame).setPickedDate();
				checkInTextField.setText(selectedDate);
			}
		});

		checkOutTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String selectedDate = new DatePicker(frame).setPickedDate();
				checkOutTextField.setText(selectedDate);
			}
		});

		telephoneCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (telephoneCheckBox.isSelected()) {
					telephoneTextField.setVisible(true);
				} else {
					telephoneTextField.setVisible(false);
				}
			}
		});

		roomServiceCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (roomServiceCheckBox.isSelected()) {
					roomServiceTextField.setVisible(true);
				} else {
					roomServiceTextField.setVisible(false);
				}
			}
		});

		equestrianAdventureCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equestrianAdventureCheckBox.isSelected()) {
					equestrianAdventureTextField.setVisible(true);
				} else {
					equestrianAdventureTextField.setVisible(false);
				}
			}
		});

		restaurantCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (restaurantCheckBox.isSelected()) {
					restaurantTextField.setVisible(true);
				} else {
					restaurantTextField.setVisible(false);
				}
			}
		});

		paintComponents(getGraphics());

	}

	public static void main(String[] args) {

		new MainFrame();

	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == CheckInOption) {
			checkInTitle.setVisible(true);
			checkInLabel.setVisible(true);
			checkOutLabel.setVisible(true);
			typeOfRoomLabel.setVisible(true);
			specificRoomLabel.setVisible(true);
			locationOfRoomLabel.setVisible(true);
			numberOfPeopleLabel.setVisible(true);

			typeOfRoom.setVisible(true);
			specificRoom.setVisible(true);
			locationOfRoom.setVisible(true);
			numberOfPeople.setVisible(true);

			checkInTextField.setVisible(true);
			checkOutTextField.setVisible(true);
			toMainScreenButton.setVisible(true);

			CheckAvailabilityButton.setVisible(true);

			CheckInOption.setVisible(false);
			CheckOutOption.setVisible(false);
		}

		if (event.getSource() == CheckOutOption) {

			checkOutTitle.setVisible(true);
			roomNumberLabel.setVisible(true);
			roomNumberTextField.setVisible(true);
			proceedToCheckout.setVisible(true);

			toMainScreenButton.setVisible(true);

			CheckInOption.setVisible(false);
			CheckOutOption.setVisible(false);

		}

		if (event.getSource() == toMainScreenButton) {

			checkInTitle.setVisible(false);
			checkInLabel.setVisible(false);
			checkOutLabel.setVisible(false);
			typeOfRoomLabel.setVisible(false);
			specificRoomLabel.setVisible(false);
			locationOfRoomLabel.setVisible(false);
			numberOfPeopleLabel.setVisible(false);

			typeOfRoom.setVisible(false);
			specificRoom.setVisible(false);
			locationOfRoom.setVisible(false);
			numberOfPeople.setVisible(false);

			checkInTextField.setVisible(false);
			checkOutTextField.setVisible(false);
			toMainScreenButton.setVisible(false);

			CheckAvailabilityButton.setVisible(false);

			checkOutTitle.setVisible(false);
			roomNumberLabel.setVisible(false);
			roomNumberTextField.setVisible(false);
			proceedToCheckout.setVisible(false);

			additionalChargesLabel.setVisible(false);
			telephoneCheckBox.setVisible(false);
			telephoneCheckBox.setSelected(false);
			telephoneTextField.setVisible(false);
			roomServiceCheckBox.setVisible(false);
			roomServiceCheckBox.setSelected(false);
			roomServiceTextField.setVisible(false);
			equestrianAdventureCheckBox.setVisible(false);
			equestrianAdventureCheckBox.setSelected(false);
			equestrianAdventureTextField.setVisible(false);
			restaurantCheckBox.setVisible(false);
			restaurantCheckBox.setSelected(false);
			restaurantTextField.setVisible(false);
			generateBill.setVisible(false);
			
			guestInfoLabel.setVisible(false);
			descriptionOfRoomLabel.setVisible(false);
			firstNameOfGuestLabel.setVisible(false);
			firstNameOfGuestTextField.setVisible(false);
			lastNameOfGuestLabel.setVisible(false);
			lastNameOfGuestTextField.setVisible(false);
			address1Label.setVisible(false);
			address1TextField.setVisible(false);
			address2Label.setVisible(false);
			address2TextField.setVisible(false);
			cityLabel.setVisible(false);
			cityTextField.setVisible(false);
			stateLabel.setVisible(false);
			stateTextField.setVisible(false);
			phoneLabel.setVisible(false);
			phoneTextField.setVisible(false);
			zipCodeLabel.setVisible(false);
			zipCodeTextField.setVisible(false);
			emailLabel.setVisible(false);
			emailTextField.setVisible(false);
			makeReservation.setVisible(false);

			CheckInOption.setVisible(true);
			CheckOutOption.setVisible(true);

		}

		if (event.getSource() == proceedToCheckout) {

			roomNumberLabel.setVisible(false);
			roomNumberTextField.setVisible(false);
			proceedToCheckout.setVisible(false);

			additionalChargesLabel.setVisible(true);
			telephoneCheckBox.setVisible(true);
			roomServiceCheckBox.setVisible(true);
			equestrianAdventureCheckBox.setVisible(true);
			restaurantCheckBox.setVisible(true);
			generateBill.setVisible(true);
		}

		if (event.getSource() == CheckAvailabilityButton) {

			if (typeOfRoom.getSelectedItem() != null
					&& specificRoom.getSelectedItem() != null
					&& locationOfRoom.getSelectedItem() != null
					&& numberOfPeople.getSelectedItem() != null) { // Checks if the fields are completed by the user.

				toR = typeOfRoom.getSelectedItem().toString();
				chaR = specificRoom.getSelectedItem().toString();
				loR = locationOfRoom.getSelectedItem().toString();
				noP = numberOfPeople.getSelectedItem().toString();

				roomID = checkAvailability(toR, loR, chaR);

			}else{
				JOptionPane.showMessageDialog(null,	"Please select an option from all fields.");
			}
		}
		
		if(event.getSource() == makeReservation){
			
			if(firstNameOfGuestTextField.getText() != null && lastNameOfGuestTextField.getText() != null 
					&& address1TextField.getText() != null && address2TextField.getText() != null
					&& cityTextField.getText() != null && stateTextField.getText() != null
					&& phoneTextField.getText() != null && zipCodeTextField.getText() != null
					&& emailTextField.getText() != null
					/*&& creditCardNumberTextField.getText() != null*/){ // Checks if the fields are completed by the user.
				
				fName = firstNameOfGuestTextField.getText();
				lName = lastNameOfGuestTextField.getText();
				add1 = address1TextField.getText();
				add2 = address2TextField.getText();
				city = cityTextField.getText();
				state = stateTextField.getText();
				zip = zipCodeTextField.getText();
				phone = phoneTextField.getText();
				email = emailTextField.getText();
				//ccNum = creditCardNumberTextField.getText();
				
				
				guestID = guestIn(fName, lName, add1, add2, city, state, zip, phone, email/*, ccNum*/);
				roomNumber = visitIn(roomID, guestID, Integer.parseInt(noP), checkIn, checkOut);
				String message = "Your room number is " + roomNumber;
				message = message + ". Please remember this number for checkout.";
				JOptionPane.showMessageDialog(null,	message);
				
			}else{
				JOptionPane.showMessageDialog(null,	"Please select an option from all fields.");
			}
			
		}
	}

	
	
	

	 /* 
	 * try { Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); Connection
	 * connection = DriverManager.getConnection("jdbc:odbc:S_P_SP"); Statement
	 * statement = connection.createStatement();
	 * 
	 * statement.executeUpdate(
	 * "INSERT INTO s VALUES ('"+name+"', 'Simpson', 20, 'Venezuela')" );
	 * text.setText("Insertion completed");
	 * 
	 * statement.close(); connection.close();
	 * 
	 * } catch ( SQLException sqlException ) { // detect problems interacting
	 * with the database JOptionPane.showMessageDialog( null,
	 * sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
	 * System.exit( 1 ); } catch ( ClassNotFoundException classNotFound ) { //
	 * detect problems loading database driver JOptionPane.showMessageDialog(
	 * null, classNotFound.getMessage(), "Driver Not Found",
	 * JOptionPane.ERROR_MESSAGE ); System.exit( 1 ); }
	 */

	
	
	
	// returns true if there is at least one room available meeting the
	// specifications
	// type t, location l, and characteristics ch
	private boolean isAvailable(String t, String l, String ch) {
		boolean available = false;

		try {

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "SELECT * FROM rooms where type = '" + t + "' AND location = '" + l + "' AND characteristics = '" + ch + "'";
			ResultSet resultSet1 = statement.executeQuery(s1);
			while (resultSet1.next()) {
				int numAvailable = resultSet1.getInt(5);
				// debugging
				System.out.println("numAvailable = " + numAvailable);
				if (numAvailable > 0){
					available = true;
				}
			}
			
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return available;
	}// end isAvailable method

	// returns the roomID if there is at least one room available meeting the specifications type toR, location loR, and characteristics chaR
	private int checkAvailability(String toR, String loR, String chaR) {

		int result, roomID = -1;
		if (isAvailable(toR, loR, chaR)) {
			result = JOptionPane.showConfirmDialog(null,"A room is available. Continue to booking?", "Room Available", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				
				checkIn = checkInTextField.getText();
				checkOut = checkOutTextField.getText();
				
				try {
					
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection connection = null;
					connection = DriverManager.getConnection("jdbc:odbc:hotelreservation");
					Statement statement = connection.createStatement();

					String s2 = "SELECT * FROM rooms where type = '" + toR + "' AND location = '" + loR + "' AND characteristics = '" + chaR + "'";
					ResultSet resultSet1 = statement.executeQuery(s2);
						while (resultSet1.next()) {
							roomID = resultSet1.getInt(1);
						}
					// for debugging
					System.out.println("roomID = " + roomID);
					
					statement.close();
					connection.close();
					
					guestInfo();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return roomID;

			} // END OF "IF ROOM IS AVAILABLE"
		} else {
			result = JOptionPane.showConfirmDialog(null, "No room is available. Try again?", "No Room Available", JOptionPane.YES_NO_OPTION);
		}

		return roomID; // returns -1 if no room available

	} // END OF checkAvailability METHOD

	// end of moving to booking room and retrieving roomID to identify type/loc/char

	private void guestInfo() {
		checkInTitle.setVisible(true);
		checkInLabel.setVisible(false);
		checkOutLabel.setVisible(false);
		typeOfRoomLabel.setVisible(false);
		specificRoomLabel.setVisible(false);
		locationOfRoomLabel.setVisible(false);
		numberOfPeopleLabel.setVisible(false);

		typeOfRoom.setVisible(false);
		specificRoom.setVisible(false);
		locationOfRoom.setVisible(false);
		numberOfPeople.setVisible(false);

		checkInTextField.setVisible(false);
		checkOutTextField.setVisible(false);
		toMainScreenButton.setVisible(true);
		
		CheckAvailabilityButton.setVisible(false);
		
		guestInfoLabel.setVisible(true);
		descriptionOfRoomLabel.setText("<html> Type of room: <font color='red'>"+ toR + "</font> <br> Characteristics: <font color='red'>"+ chaR + "</font> <br> Location of Room: <font color='red'>" + loR + "</font> <br> Number of People: <font color='red'>" + noP + "</font> </html>"); //html statement helps to use "end of line" and other html features with Jlabel.
		descriptionOfRoomLabel.setVisible(true);
		firstNameOfGuestLabel.setVisible(true);
		firstNameOfGuestTextField.setVisible(true);
		lastNameOfGuestLabel.setVisible(true);
		lastNameOfGuestTextField.setVisible(true);
		address1Label.setVisible(true);
		address1TextField.setVisible(true);
		address2Label.setVisible(true);
		address2TextField.setVisible(true);
		cityLabel.setVisible(true);
		cityTextField.setVisible(true);
		stateLabel.setVisible(true);
		stateTextField.setVisible(true);
		phoneLabel.setVisible(true);
		phoneTextField.setVisible(true);
		zipCodeLabel.setVisible(true);
		zipCodeTextField.setVisible(true);
		emailLabel.setVisible(true);
		emailTextField.setVisible(true);
		makeReservation.setVisible(true);
		
	}

	private void checkIn() {// need args (toR, loR, chaR, fName, lName, add1,
							// add2, city, state,
		// zip, phone, email, ccNum, NumOfPeople, inDate, outDate)

		// int rmID = checkAvailability(toR, loR, chaR);//fill in args
		// int gstID = guestIn(fName, lName, add1, add2, city, state, zip,
		// phone, email, ccNum);//need args
		// int rmNum = visitIn(rmID, gstID, numOfPeople, inDate, outDate);//need
		// args

	}// end checkIn method

	// inserts guest information into guest table
	// returns the guestID generated for use in inserting into visit table
	private int guestIn(String fName, String lName, String add1, String add2, String city, String state, String zip, String phone, String email/*, String ccNum*/) {

		int guestNum = 0;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "INSERT INTO guest(firstName, lastName, address1, address2, city, state, zipCode, phone, email, ccNum) ";
			s1 = s1 + "VALUES ('" + fName + "', '" + lName + "', '" + add1 + "', '" + add2 + "', '" + city;
			s1 = s1 + "', '" + state + "', '" + zip + "', '" + phone + "', '" + email + "', '1234567891234567')";//dummy cc value

			statement.addBatch(s1);
			statement.executeBatch();

			String i = "SELECT @@IDENTITY FROM guest";
			ResultSet resultSet = statement.executeQuery(i);
			resultSet.next();
			guestNum = resultSet.getInt(1);
			
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return guestNum;
	}// end of method to insert guest information

	// inserts visit information into visit table
	// returns the room Number for the check in confirmation
	private int visitIn(int roomID, int guestID, int numOfPeople, String inDate, String outDate) {

		int roomNum = 0;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "INSERT INTO visit(roomID, guestID, numOfPeople, checkIn, checkOut) ";
			s1 = s1 + "VALUES ('" + roomID + "', '" + guestID + "', '" + numOfPeople + "', '" + inDate;
			s1 = s1 + "', '" + outDate + "')";

			statement.addBatch(s1);
			statement.executeBatch();

			String i = "SELECT @@IDENTITY FROM visit";
			ResultSet resultSet = statement.executeQuery(i);
			resultSet.next();
			roomNum = resultSet.getInt(1);
			
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return roomNum;
	}// end of method to insert visit
	
	private void decrementRoomCount(int roomID){
		
		
		
	}//end of decrementRoomCount method

	private void incrementRoomCount(int roomID){
		
	}//end of incrementRoomCount method
	
}

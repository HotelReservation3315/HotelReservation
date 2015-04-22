package hotelReservation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

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
	JLabel checkOutSummaryLabel;
	JLabel BillLabel;
	JLabel BillSummaryLabel;
	JLabel BillSummaryLabel2;

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
	JButton printBillButton;

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
	String checkIn; // Date
	String checkOut; // Date
	String roomNumber; // Identifies each room with: roomID + numAvailable.
	float onePersonFee;
	float twoPersonFee;
	float extraPersonFee;
	float telephoneFee; // Saves the fee for telephone.
	float roomServiceFee; // Saves the fee for room service.
	float equestrianAdventureFee; // Saves the fee for equestrian adventure.
	float restaurantFee; // Saves the fee for restaurant.
	int roomID; // Identifies each type of room in the Database (1 to 16).
	int guestID;
	int numAvailable; // Indicates how many rooms are available.
	int numInBuilding; // Indicates how many rooms the hotel has.

	final float TAX = (float) 0.1; // 10% of TAX.

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

		typeOfRoomLabel = new JLabel("Type of Room:");
		typeOfRoom = new JComboBox<String>();

		typeOfRoomLabel.setBounds(10, 60, 130, 50);
		typeOfRoomLabel.setVisible(false);
		typeOfRoom.setBounds(100, 75, 80, 20);
		typeOfRoom.setVisible(false);

		typeOfRoom.addItem("Luxury");
		typeOfRoom.addItem("Cottage");
		typeOfRoom.setSelectedItem(null); // Doesn't select any option by
											// default in the ComboBox.

		specificRoomLabel = new JLabel("Characteristics:");
		specificRoom = new JComboBox<String>();
		specificRoomLabel.setBounds(10, 90, 130, 50);
		specificRoomLabel.setVisible(false);
		specificRoom.setBounds(110, 105, 120, 20);
		specificRoom.setVisible(false);

		typeOfRoom.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				specificRoom.removeAllItems(); // Clears the ComboBox
				toR = typeOfRoom.getSelectedItem().toString(); // Gets the
																// selected
																// option from
																// Type of Room.

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

		locationOfRoomLabel.setBounds(10, 120, 130, 50);
		locationOfRoomLabel.setVisible(false);
		locationOfRoom.setBounds(120, 135, 75, 20);
		locationOfRoom.setVisible(false);

		locationOfRoom.addItem("Patio");
		locationOfRoom.addItem("Forest");
		// locationOfRoom.addItem("Any");
		locationOfRoom.setSelectedItem(null); // Doesn't select any option by
												// default in the ComboBox.

		numberOfPeopleLabel = new JLabel("Number of People:");
		numberOfPeople = new JComboBox<Integer>();

		numberOfPeopleLabel.setBounds(10, 150, 130, 50);
		numberOfPeopleLabel.setVisible(false);
		numberOfPeople.setBounds(120, 165, 45, 20);
		numberOfPeople.setVisible(false);

		numberOfPeople.addItem(1);
		numberOfPeople.addItem(2);
		numberOfPeople.addItem(3);
		numberOfPeople.addItem(4);
		numberOfPeople.addItem(5);
		numberOfPeople.addItem(6);
		numberOfPeople.addItem(7);
		numberOfPeople.setSelectedItem(null); // Doesn't select any option by
												// default in the ComboBox.

		CheckAvailabilityButton = new JButton("Check Availability");
		CheckAvailabilityButton.setBounds(20, 210, 150, 25);
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

		checkOutSummaryLabel = new JLabel();
		checkOutSummaryLabel.setBounds(200, 40, 200, 260);
		checkOutSummaryLabel.setVisible(false);

		checkOutTextField = new JTextField(10);
		checkOutTextField.setBounds(293, 122, 100, 20);
		checkOutTextField.setVisible(false);

		BillLabel = new JLabel("Bill:");
		BillLabel.setFont(new Font("Courier", Font.BOLD, 17));
		BillLabel.setBounds(120, 0, 50, 50);
		BillLabel.setVisible(false);

		BillSummaryLabel = new JLabel();
		BillSummaryLabel.setBounds(200, 40, 200, 260);
		BillSummaryLabel.setVisible(false);

		BillSummaryLabel2 = new JLabel();
		BillSummaryLabel2.setBounds(10, 40, 200, 260);
		BillSummaryLabel2.setVisible(false);

		printBillButton = new JButton("Print");
		printBillButton.setBounds(10, 320, 120, 25);
		printBillButton.setVisible(false);
		printBillButton.addActionListener(this);

		JPanel p = new JPanel();
		p.setLayout(null);

		final JFrame frame = new JFrame();

		p.add(checkInTitle);
		p.add(checkInLabel);
		p.add(checkInTextField);
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
		p.add(checkOutSummaryLabel);
		p.add(BillLabel);
		p.add(BillSummaryLabel);
		p.add(BillSummaryLabel2);
		p.add(printBillButton);

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
			goToCheckInScreen();
		}

		if (event.getSource() == CheckOutOption) {
			goToCheckOutScreen();
		}

		if (event.getSource() == toMainScreenButton) {
			goToMainScreen();
		}

		if (event.getSource() == proceedToCheckout) {
			searchRoom();
		}

		if (event.getSource() == printBillButton) {
			print();
		}

		if (event.getSource() == CheckAvailabilityButton) {

			if (typeOfRoom.getSelectedItem() != null
					&& specificRoom.getSelectedItem() != null
					&& locationOfRoom.getSelectedItem() != null
					&& numberOfPeople.getSelectedItem() != null
					&& checkInTextField.getText().length() > 0) { // Checks if
																	// the
																	// fields
																	// are
																	// completed
																	// by the
																	// user.

				toR = typeOfRoom.getSelectedItem().toString();
				chaR = specificRoom.getSelectedItem().toString();
				loR = locationOfRoom.getSelectedItem().toString();
				noP = numberOfPeople.getSelectedItem().toString();

				roomID = checkAvailability(toR, loR, chaR);

			} else {
				JOptionPane.showMessageDialog(null,
						"Please select an option from all fields.");
			}
		}

		if (event.getSource() == makeReservation) {

			if (firstNameOfGuestTextField.getText().length() > 0
					&& lastNameOfGuestTextField.getText().length() > 0
					&& address1TextField.getText().length() > 0
					&& address2TextField.getText().length() > 0
					&& cityTextField.getText().length() > 0
					&& stateTextField.getText().length() > 0
					&& phoneTextField.getText().length() > 0
					&& zipCodeTextField.getText().length() > 0
					&& emailTextField.getText().length() > 0
			/* && creditCardNumberTextField.getText() != null */) { // Checks if
																	// the
																	// fields
																	// are
																	// completed
																	// by the
																	// user.

				fName = firstNameOfGuestTextField.getText();
				lName = lastNameOfGuestTextField.getText();
				add1 = address1TextField.getText();
				add2 = address2TextField.getText();
				city = cityTextField.getText();
				state = stateTextField.getText();
				zip = zipCodeTextField.getText();
				phone = phoneTextField.getText();
				email = emailTextField.getText();
				// ccNum = creditCardNumberTextField.getText();

				guestID = guestIn(fName, lName, add1, add2, city, state, zip,
						phone, email/* , ccNum */);
				roomNumber = visitIn(roomID, guestID, Integer.parseInt(noP),
						checkIn/* , checkOut */);
				String message = "Your room number is " + roomNumber;
				message = message
						+ ". Please remember this number for checkout.";
				goToMainScreen();
				goToCheckInScreen();
				JOptionPane.showMessageDialog(null, message);

			} else {
				JOptionPane.showMessageDialog(null,
						"Please select an option from all fields.");
			}
		}

		if (event.getSource() == generateBill) {
			if (checkOutTextField.getText().length() > 0) { // If a check-out
															// date is selected.
				goToBillScreen();
				incrementRoomCount(roomID);

				try {
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection connection = DriverManager
							.getConnection("jdbc:odbc:hotelreservation");
					Statement statement = connection.createStatement();

					checkOut = checkOutTextField.getText();

					String s1 = "UPDATE visit SET checkOut = '" + checkOut
							+ "' WHERE roomNumber = '" + roomNumber + "'";

					statement.addBatch(s1);
					statement.executeBatch();

					String s2 = "SELECT * FROM rooms WHERE roomID = " + roomID
							+ " ";
					ResultSet resultSet2 = statement.executeQuery(s2);
					while (resultSet2.next()) {
						onePersonFee = resultSet2.getFloat(6);
						twoPersonFee = resultSet2.getFloat(7);
						extraPersonFee = resultSet2.getFloat(8);
					}
					// System.out.println(onePersonFee);
					// System.out.println(noP);

//					String s3 = "DELETE FROM guest WHERE guestID = " + guestID
//							+ "";
//
//					statement.addBatch(s3);
//					statement.executeBatch();

					statement.close();
					connection.close();

					BillSummaryLabel
							.setText("<html> Type of Room: <font color='red'>"
									+ toR
									+ "</font> "
									+ "<br> Characteristics: <font color='red'>"
									+ chaR
									+ "</font>"
									+ " <br> Location of Room: <font color='red'>"
									+ loR
									+ "</font>"
									+ " <br> Number of People: <font color='red'>"
									+ noP
									+ "</font>"
									+ " <br> Check-in Date: <font color='red'>"
									+ checkIn
									+ "</font>"
									+ " <br> Check-out Date: <font color='red'>"
									+ checkOut
									+ "</font>"
									+ " <br><br> Name of Guest: <font color='red'>"
									+ fName
									+ "</font>"
									+ " <br> Last Name of Guest: <font color='red'>"
									+ lName + "</font>"
									+ " <br> Address 1: <font color='red'>"
									+ add1 + "</font>"
									+ " <br> Address 2: <font color='red'>"
									+ add2 + "</font>"
									+ " <br> City: <font color='red'>" + city
									+ "</font>"
									+ " <br> State: <font color='red'>" + state
									+ "</font>"
									+ " <br> Zip Code: <font color='red'>"
									+ zip + "</font>"
									+ " <br> Phone: <font color='red'>" + phone
									+ "</font>"
									+ " <br> Email: <font color='red'>" + email
									+ "</font></html>");

					Days lenghtOfStay = new Days();
					int numberOFDays = lenghtOfStay.numberOfDays(checkIn,
							checkOut); // Calculates the number of days between
										// 2 dates.

					if (telephoneCheckBox.isSelected()) {
						telephoneFee = Float.parseFloat(telephoneTextField
								.getText());
						recordSpecialCharge(roomNumber, "Telephone Fee", Float.toString(telephoneFee));
					}
					if (restaurantCheckBox.isSelected()) {
						restaurantFee = Float.parseFloat(restaurantTextField
								.getText());
						recordSpecialCharge(roomNumber, "Restaurant Fee", Float.toString(restaurantFee));
					}
					if (equestrianAdventureCheckBox.isSelected()) {
						equestrianAdventureFee = Float
								.parseFloat(equestrianAdventureTextField
										.getText());
						recordSpecialCharge(roomNumber, "Equestrian Adventure", Float.toString(equestrianAdventureFee));
					}
					if (roomServiceCheckBox.isSelected()) {
						roomServiceFee = Float.parseFloat(roomServiceTextField
								.getText());
						recordSpecialCharge(roomNumber, "Room Service", Float.toString(roomServiceFee));
					}

					float additionalChargesTotal = telephoneFee
							+ equestrianAdventureFee + roomServiceFee
							+ restaurantFee;

					float priceOfRoom = 0;
					String priceOfRoomAUX = "";
					int noPAUX = Integer.parseInt(noP);
					if (noPAUX == 1) {
						priceOfRoom = onePersonFee;
						priceOfRoomAUX = "1 person = "
								+ String.format("%.2f", onePersonFee);
					} else if (noPAUX == 2) {
						priceOfRoom = twoPersonFee;
						priceOfRoomAUX = "2 persons = "
								+ String.format("%.2f", twoPersonFee);
					} else {
						priceOfRoom = twoPersonFee + (noPAUX - 2) * extraPersonFee; // Total
																				// per
																				// night.
						priceOfRoomAUX = noP + " persons = "
								+ String.format("%.2f", twoPersonFee) + " + "
								+ Integer.toString(noPAUX - 2) + "*"
								+ String.format("%.2f", extraPersonFee);
					}

					float subTotalRoom = numberOFDays * priceOfRoom;
					float taxRoom = numberOFDays * priceOfRoom * TAX;
					float TOTAL = subTotalRoom + taxRoom
							+ additionalChargesTotal;

					BillSummaryLabel2
							.setText("<html> Price per night: <font color='red'> "
									+ String.format("%.2f", priceOfRoom)
									+ "<br>"
									+ priceOfRoomAUX
									+ "</font> "
									+ "<br> Days in the hotel: <font color='red'>"
									+ Integer.toString(numberOFDays)
									+ "</font>"
									+ "<br><br> ROOM SUB-TOTAL: <font color='red'>"
									+ String.format("%.2f", subTotalRoom)
									+ "<br>"
									+ String.format("%.2f", priceOfRoom)
									+ " * "
									+ Integer.toString(numberOFDays)
									+ "</font>"
									+ "<br> ROOM TAX: <font color='red'>"
									+ String.format("%.2f", taxRoom)
									+ "</font>"
									+ "<br> ADDITIONAL CHARGES: <font color='red'>"
									+ String.format("%.2f", additionalChargesTotal)
									+ "<br> telephone: "
									+ String.format("%.2f", telephoneFee)
									+ "<br> room service: "
									+ String.format("%.2f", roomServiceFee)
									+ "<br> restaurant: "
									+ String.format("%.2f", restaurantFee)
									+ "<br> equestrian adventure: "
									+ String.format("%.2f", equestrianAdventureFee)
									+ "</font>"
									+ "<br><br><br> TOTAL: <font color='red'>"
									+ String.format("%.2f", TOTAL)
									+ " $</font> </html>");

				} catch (SQLException sqlException) { // detect problems
														// interacting with the
														// database
					JOptionPane.showMessageDialog(null,
							sqlException.getMessage(), "Database Error",
							JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				} catch (ClassNotFoundException classNotFound) { // detect
																	// problems
																	// loading
																	// database
																	// driver
					JOptionPane.showMessageDialog(null,
							classNotFound.getMessage(), "Driver Not Found",
							JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Please indicate today's date (Check-out Date)");
			}
		}

	} // END OF Action Listeners

	private void searchRoom() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			roomNumber = null;
			String s1 = "SELECT * FROM visit WHERE roomNumber = '"
					+ roomNumberTextField.getText() + "'";
			ResultSet resultSet1 = statement.executeQuery(s1);

			while (resultSet1.next()) {
				roomNumber = resultSet1.getString(1);
				roomID = Integer.parseInt(resultSet1.getString(2));
				guestID = Integer.parseInt(resultSet1.getString(3));
				noP = resultSet1.getString(4);
				checkIn = resultSet1.getString(5);
				checkOut = resultSet1.getString(6);
			}

			if (roomNumber != null && checkOut == null) { // If the room exists
															// in the database
															// (the person
															// checked-in) and
															// the guest hasn't
															// checked-out yet.
				goToAdditionalChargesScreen();

				String s2 = "SELECT * FROM guest WHERE guestID = " + guestID
						+ " ";
				ResultSet resultSet2 = statement.executeQuery(s2);
				while (resultSet2.next()) {
					fName = resultSet2.getString(2);
					lName = resultSet2.getString(3);
					add1 = resultSet2.getString(4);
					add2 = resultSet2.getString(5);
					city = resultSet2.getString(6);
					state = resultSet2.getString(7);
					zip = resultSet2.getString(8);
					phone = resultSet2.getString(9);
					email = resultSet2.getString(10);
				}

				String s3 = "SELECT * FROM rooms WHERE roomID = " + roomID
						+ " ";
				ResultSet resultSet3 = statement.executeQuery(s3);
				while (resultSet3.next()) {
					toR = resultSet3.getString(2);
					loR = resultSet3.getString(3);
					chaR = resultSet3.getString(4);
				}

				checkOutSummaryLabel
						.setText("<html> Type of room: <font color='red'>"
								+ toR
								+ "</font> "
								+ "<br> Characteristics: <font color='red'>"
								+ chaR
								+ "</font>"
								+ " <br> Location of Room: <font color='red'>"
								+ loR
								+ "</font>"
								+ " <br> Number of People: <font color='red'>"
								+ noP
								+ "</font>"
								+ " <br> Check-in Date: <font color='red'>"
								+ checkIn
								+ "</font>"
								+ " <br> Check-out Date: "
								+ " <br><br> Name of Guest: <font color='red'>"
								+ fName
								+ "</font>"
								+ " <br> Last Name of Guest: <font color='red'>"
								+ lName + "</font>"
								+ " <br> Address 1: <font color='red'>" + add1
								+ "</font>"
								+ " <br> Address 2: <font color='red'>" + add2
								+ "</font>" + " <br> City: <font color='red'>"
								+ city + "</font>"
								+ " <br> State: <font color='red'>" + state
								+ "</font>"
								+ " <br> Zip Code: <font color='red'>" + zip
								+ "</font>" + " <br> Phone: <font color='red'>"
								+ phone + "</font>"
								+ " <br> Email: <font color='red'>" + email
								+ "</font></html>");
			} else { // If the room does not exist in the database (the person
						// did not checked-in)
				JOptionPane.showMessageDialog(null,
						"The room is not registered.");
			}

			statement.close();
			connection.close();

		} catch (SQLException sqlException) { // detect problems interacting
												// with the database
			JOptionPane.showMessageDialog(null, sqlException.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (ClassNotFoundException classNotFound) { // detect problems
															// loading database
															// driver
			JOptionPane.showMessageDialog(null, classNotFound.getMessage(),
					"Driver Not Found", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	} // END OF search METHOD

	/*
	 * Returns true if there is at least one room available meeting the
	 * specifications type t, location l, and characteristics ch.
	 */
	private boolean isAvailable(String t, String l, String ch) {
		boolean available = false;

		try {

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = null;
			connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "SELECT * FROM rooms where type = '" + t
					+ "' AND location = '" + l + "' AND characteristics = '"
					+ ch + "'";
			ResultSet resultSet1 = statement.executeQuery(s1);
			while (resultSet1.next()) {
				numAvailable = resultSet1.getInt(5);
				numInBuilding = resultSet1.getInt(9);
				// debugging
				// System.out.println("numAvailable = " + numAvailable);
				if (numAvailable > 0) {
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
	} // END OF isAvailable METHOD

	/*
	 * Returns the roomID if there is at least one room available meeting the
	 * specifications type toR, location loR, and characteristics chaR. Returns
	 * -1 as the roomID if the user chooses not to continue to booking.
	 */
	private int checkAvailability(String toR, String loR, String chaR) {

		int result, roomID = -1;
		if (isAvailable(toR, loR, chaR)) {
			result = JOptionPane.showConfirmDialog(null,
					"<html> Number of rooms available of this kind: <font color='red'>"
							+ numAvailable
							+ "</font> <br> Continue to booking? </html>",
					"Room Available", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {

				checkIn = checkInTextField.getText();
				// checkOut = checkOutTextField.getText();

				try {

					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection connection = null;
					connection = DriverManager
							.getConnection("jdbc:odbc:hotelreservation");
					Statement statement = connection.createStatement();

					String s2 = "SELECT * FROM rooms where type = '" + toR
							+ "' AND location = '" + loR
							+ "' AND characteristics = '" + chaR + "'";
					ResultSet resultSet1 = statement.executeQuery(s2);
					while (resultSet1.next()) {
						roomID = resultSet1.getInt(1);
					}
					// for debugging
					// System.out.println("roomID = " + roomID);

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
			result = JOptionPane.showConfirmDialog(null,
					"No room is available. Try again?", "No Room Available",
					JOptionPane.YES_NO_OPTION);
		}

		return roomID; // returns -1 if no room available

	} // END OF checkAvailability METHOD
		// END OF booking room and retrieving roomID to identify type/loc/char

	/*
	 * Inserts guest information into guest table. Returns the guestID generated
	 * for use in inserting into visit table.
	 */
	private int guestIn(String fName, String lName, String add1, String add2,
			String city, String state, String zip, String phone, String email/*
																			 * ,
																			 * String
																			 * ccNum
																			 */) {

		int guestNum = 0;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "INSERT INTO guest(firstName, lastName, address1, address2, city, state, zipCode, phone, email, ccNum) ";
			s1 = s1 + "VALUES ('" + fName + "', '" + lName + "', '" + add1
					+ "', '" + add2 + "', '" + city;
			s1 = s1 + "', '" + state + "', '" + zip + "', '" + phone + "', '"
					+ email + "', '1234567891234567')";// dummy cc value

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
	} // END OF method to insert guest information

	/*
	 * Inserts visit information into visit table. Returns the roomNumber for
	 * the check in confirmation.
	 */
	private String visitIn(int roomID, int guestID, int numOfPeople,
			String inDate) {

		String roomNum = "";

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			int i = 0;
			while (true) { // We have to check that a certain room is available
							// or not, in order to use it.

				roomNum = Integer.toString(roomID) + "-"
						+ Integer.toString(numInBuilding - i); // Generates a
																// room Number
																// that
																// identifies
																// each room
																// with: roomID
																// +
																// numAvailable.

				String s3 = "SELECT * FROM visit WHERE roomNumber = '"
						+ roomNum + "'";
				ResultSet resultSet2 = statement.executeQuery(s3);

				boolean c1 = false;

				if (!resultSet2.next()) { // We first check if the number of the
											// room is on the table "visit".
					// System.out.println("free");
					break; // If it is not, this means the room hasn't been
							// added previously to the database, so it is free.
				} else {
					c1 = true; // If it is, this means that the room was
								// previously rented and is still occupied.
				}

				if (c1 == true) {
					String s2 = "SELECT * FROM visit WHERE roomNumber = '"
							+ roomNum + "' AND checkOut = '' "; // So we see if
																// for that
																// room, a
																// check-out
																// date has been
																// entered.
					ResultSet resultSet1 = statement.executeQuery(s2);

					if (!resultSet1.next()) { // If the check-out field in the
												// database (for that specific
												// room) is empty, it means the
												// room is been used.
						// System.out.println("occupied");
						roomNum = Integer.toString(roomID) + "-"
								+ Integer.toString(numInBuilding - i);
						i++; // We try in the next iteration, with the next room
								// for that specific type of room.
					} else { // If not, it means the room is available.
						break;
					}
				}
			}

			String s1 = "INSERT INTO visit(roomNumber, roomID, guestID, numOfPeople, checkIn) ";
			s1 = s1 + "VALUES ('" + roomNum + "','" + roomID + "', '" + guestID
					+ "', '" + numOfPeople + "', '" + inDate + "')";

			statement.addBatch(s1);
			statement.executeBatch();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		decrementRoomCount(roomID);

		return roomNum;
	} // END OF method to insert visit

	// Decrements the number available for the room category
	// indicated by parameter roomID.
	private void decrementRoomCount(int roomID) {

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "SELECT * FROM rooms WHERE roomID = " + roomID + "";

			ResultSet resultSet = statement.executeQuery(s1);
			resultSet.next();
			int available = resultSet.getInt(5);

			available--;

			String s2 = "UPDATE rooms SET numAvailable = '" + available
					+ "' WHERE roomID = " + roomID + "";

			statement.addBatch(s2);
			statement.executeBatch();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // END OF decrementRoomCount METHOD

	// Increments the number available for the room category
	// indicated by parameter roomID.
	private void incrementRoomCount(int roomID) {

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s1 = "SELECT * FROM rooms WHERE roomID = " + roomID + "";

			ResultSet resultSet = statement.executeQuery(s1);
			resultSet.next();
			int available = resultSet.getInt(5);
			
			available++;

			String s2 = "UPDATE rooms SET numAvailable = '" + available
					+ "' WHERE roomID = " + roomID + "";

			statement.addBatch(s2);
			statement.executeBatch();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // END OF incrementRoomCount METHOD

	private void print() { // PRINT METHOD
		PrinterJob pj = PrinterJob.getPrinterJob();
		if (pj.printDialog()) {
			try {
				pj.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}
	} // END OF print METHOD

	private void goToCheckInScreen() {
		checkInTitle.setVisible(true);
		checkInLabel.setVisible(true);
		typeOfRoomLabel.setVisible(true);
		specificRoomLabel.setVisible(true);
		locationOfRoomLabel.setVisible(true);
		numberOfPeopleLabel.setVisible(true);

		typeOfRoom.setVisible(true);
		specificRoom.setVisible(true);
		locationOfRoom.setVisible(true);
		numberOfPeople.setVisible(true);

		checkInTextField.setVisible(true);
		toMainScreenButton.setVisible(true);

		CheckAvailabilityButton.setVisible(true);

		CheckInOption.setVisible(false);
		CheckOutOption.setVisible(false);
	}// END OF goToCheckInScreen METHOD

	private void goToCheckOutScreen() {
		checkOutTitle.setVisible(true);
		roomNumberLabel.setVisible(true);
		roomNumberTextField.setVisible(true);
		proceedToCheckout.setVisible(true);

		toMainScreenButton.setVisible(true);

		CheckInOption.setVisible(false);
		CheckOutOption.setVisible(false);
	}// END OF goToCheckOutScreen METHOD

	private void guestInfo() {
		checkInTitle.setVisible(true);
		checkInLabel.setVisible(false);
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
		descriptionOfRoomLabel
				.setText("<html> Type of room: <font color='red'>" + toR
						+ "</font> <br> Characteristics: <font color='red'>"
						+ chaR
						+ "</font> <br> Location of Room: <font color='red'>"
						+ loR
						+ "</font> <br> Number of People: <font color='red'>"
						+ noP + "</font> </html>"); // html statement helps to
													// use "end of line" and
													// other html features with
													// Jlabel.
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
	}// END OF guestInfo METHOD

	private void goToAdditionalChargesScreen() {
		roomNumberLabel.setVisible(false);
		roomNumberTextField.setVisible(false);
		proceedToCheckout.setVisible(false);

		additionalChargesLabel.setVisible(true);
		telephoneCheckBox.setVisible(true);
		roomServiceCheckBox.setVisible(true);
		equestrianAdventureCheckBox.setVisible(true);
		restaurantCheckBox.setVisible(true);
		generateBill.setVisible(true);
		checkOutSummaryLabel.setVisible(true);
		checkOutTextField.setVisible(true);
	}// END OF goToAdditionalChargesScreen METHOD

	private void goToMainScreen() {
		checkInTitle.setVisible(false);
		checkInLabel.setVisible(false);
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

		checkOutSummaryLabel.setVisible(false);
		BillLabel.setVisible(false);
		BillSummaryLabel.setVisible(false);
		BillSummaryLabel2.setVisible(false);
		printBillButton.setVisible(false);

		CheckInOption.setVisible(true);
		CheckOutOption.setVisible(true);
	}// END OF goToMainScreen METHOD

	private void goToBillScreen() {
		additionalChargesLabel.setVisible(false);
		telephoneCheckBox.setVisible(false);
		roomServiceCheckBox.setVisible(false);
		equestrianAdventureCheckBox.setVisible(false);
		restaurantCheckBox.setVisible(false);
		generateBill.setVisible(false);
		checkOutSummaryLabel.setVisible(false);
		checkOutTextField.setVisible(false);
		telephoneTextField.setVisible(false);
		roomServiceTextField.setVisible(false);
		equestrianAdventureTextField.setVisible(false);
		restaurantTextField.setVisible(false);

		BillLabel.setVisible(true);
		BillSummaryLabel.setVisible(true);
		BillSummaryLabel2.setVisible(true);
		printBillButton.setVisible(true);
	}// END OF goToBillScreen METHOD

	private void recordSpecialCharge(String roomNumber,
			String chargeDescription, String fee) {

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager
					.getConnection("jdbc:odbc:hotelreservation");
			Statement statement = connection.createStatement();

			String s = "INSERT INTO specialCharges(roomNumber, chargeDescription, fee) ";
			s = s + "VALUES ('" + roomNumber + "', '" + chargeDescription
					+ "', '" + fee + "')";

			statement.addBatch(s);
			statement.executeBatch();

			statement.close();
			connection.close();

		} catch (SQLException sqlException) { // detect problems interacting
												// with the database
			JOptionPane.showMessageDialog(null, sqlException.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (ClassNotFoundException classNotFound) { // detect problems
															// loading database
															// driver
			JOptionPane.showMessageDialog(null, classNotFound.getMessage(),
					"Driver Not Found", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}// END OF recordSpecialCharges METHOD

}

package hotelReservation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;


public class MainFrame extends JFrame implements ActionListener{
	
	public static final int WIDTH = 450;
	public static final int HEIGHT = 400;
	public static final int XCOORD = 100;
	public static final int YCOORD = 80;
	
	JLabel checkInLabel;
	JLabel checkOutLabel;
	JLabel typeOfRoomLabel;
	JLabel specificRoomLabel;
	JLabel locationOfRoomLabel;
	
	JComboBox <String> typeOfRoom;
	JComboBox <String> specificRoom;
	JComboBox <String> locationOfRoom;

	final JTextField checkInTextField;
	final JTextField checkOutTextField;
	
	
	
	public MainFrame(){
			
		 checkInLabel = new JLabel("Check-in:"); 
		 checkInTextField = new JTextField(10);
		 
		 	checkInLabel.setBounds(10, 10, 100, 50);
		 	checkInTextField.setBounds(80,25,100,20);
	 
		 checkOutLabel = new JLabel("Check-out:"); 
		 checkOutTextField = new JTextField(10);
		 
		 	checkOutLabel.setBounds(10, 50, 100, 50);
		 	checkOutTextField.setBounds(80,65,100,20);
		 
		 typeOfRoomLabel = new JLabel("Type of Room:");
		 typeOfRoom = new JComboBox <String> ();
		 			 
		 	typeOfRoomLabel.setBounds(10, 90, 130, 50);
		 	typeOfRoom.setBounds(100,105,80,20);
			 
		 		typeOfRoom.addItem("Luxury");
		 		typeOfRoom.addItem("Cottage");
		 		//typeOfRoom.addItem("Any");
		 		typeOfRoom.setSelectedItem(null); // Doesn't select any option by default in the ComboBox.
		 		
		 		specificRoomLabel = new JLabel("Characteristics:");
		 		specificRoom = new JComboBox <String> ();
		 		specificRoomLabel.setBounds(10, 120, 130, 50);
	 			specificRoom.setBounds(110,135,120,20);
		 
	 			typeOfRoom.addItemListener(new ItemListener() {

		            @Override
		            public void itemStateChanged(ItemEvent arg0) {
		            	specificRoom.removeAllItems(); // Clears the ComboBox
		            	String toR = typeOfRoom.getSelectedItem().toString(); // Gets the selected option from Type of Room.
				 		 
					 	if(toR == "Luxury"){
					 		specificRoom.addItem("1 Queen bed");
					 		specificRoom.addItem("2 Queen beds");
					 		specificRoom.addItem("Two room");
					 		specificRoom.addItem("Three room");
					 		specificRoom.addItem("Bridal");
					 		
					 	} else if(toR == "Cottage"){
					 		specificRoom.addItem("Two Room");
					 		specificRoom.addItem("Three Room");
					 		specificRoom.addItem("Four room");
					 	} 
		            }
		        });

	 			locationOfRoomLabel = new JLabel("Location of Room:");
	 			locationOfRoom = new JComboBox <String> ();
	 						 
	 			locationOfRoomLabel.setBounds(10, 150, 130, 50);
	 			locationOfRoom.setBounds(120,165,75,20);
	 				 
	 			locationOfRoom.addItem("Patio");
	 			locationOfRoom.addItem("Forest");
	 			//locationOfRoom.addItem("Any");
	 			locationOfRoom.setSelectedItem(null); // Doesn't select any option by default in the ComboBox.
		 	 				 	
		 /*JLabel numberOfPeopleLabel = new JLabel("Number of People:");
		 JComboBox <Integer> numberOfPeople = new JComboBox <Integer> ();
		 
		 	numberOfPeopleLabel.setBounds(10, 90, 130, 50);
		 	numberOfPeople.setBounds(125,105,45,20);
		 
		 		numberOfPeople.addItem(1);
		 		numberOfPeople.addItem(2);
		 		numberOfPeople.addItem(3);
		 		numberOfPeople.addItem(4);
		 		numberOfPeople.addItem(5);
		 		numberOfPeople.addItem(6);
		 		numberOfPeople.addItem(7);
		 		
		 JLabel numberOfRoomsLabel = new JLabel("Number of Rooms:");
		 JComboBox <Integer> numberOfRooms = new JComboBox <Integer> ();
		 
		 	numberOfRoomsLabel.setBounds(10, 120, 130, 50);
		 	numberOfRooms.setBounds(125,135,45,20);
		 
		 		numberOfRooms.addItem(1);
		 		numberOfRooms.addItem(2);
		 		numberOfRooms.addItem(3);
		 		numberOfRooms.addItem(4);
		 		numberOfRooms.addItem(5);
		 		numberOfRooms.addItem(6);
		 		numberOfRooms.addItem(7);*/
		 
		 JPanel p = new JPanel();
		 p.setLayout(null);
		 	
		 final JFrame frame = new JFrame(); 
		 	
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
		 		//p.add(numberOfPeople);
		 		//p.add(numberOfPeopleLabel);
		 		//p.add(numberOfRooms);
		 		//p.add(numberOfRoomsLabel);
		 	
		 
		 frame.add(p); 
		 frame.setSize(WIDTH, HEIGHT);
		 frame.setLocation(XCOORD, YCOORD);
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true); 
		 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 	checkInTextField.addMouseListener(new MouseAdapter(){ 
		 		public void mouseClicked(MouseEvent e){
		 			String selectedDate = new DatePicker(frame).setPickedDate();
		 			checkInTextField.setText(selectedDate); 
		 		} 
		 	});
		 
		 	checkOutTextField.addMouseListener(new MouseAdapter(){ 
		 		public void mouseClicked(MouseEvent e){
		 			String selectedDate = new DatePicker(frame).setPickedDate();
		 			checkOutTextField.setText(selectedDate); 
		 		} 
		 	});
		 			
				
		paintComponents(getGraphics());
		
	}
	


public static void main(String[] args){
	
	new MainFrame();
		
}


public void actionPerformed(ActionEvent event) {

	/*if(event.getSource() == button1){
		
		String name = NameField.getText();
	
		try {
			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
			Connection connection = DriverManager.getConnection("jdbc:odbc:S_P_SP");
			Statement statement =  connection.createStatement();
			
			statement.executeUpdate( "INSERT INTO s VALUES ('"+name+"', 'Simpson', 20, 'Venezuela')" );
			text.setText("Insertion completed");
			
			statement.close();
	        connection.close();
			 
		} 
		catch ( SQLException sqlException ) { // detect problems interacting with the database
	        JOptionPane.showMessageDialog( null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
	        System.exit( 1 );
	    }
	    catch ( ClassNotFoundException classNotFound ) { // detect problems loading database driver
	        JOptionPane.showMessageDialog( null, classNotFound.getMessage(), "Driver Not Found", JOptionPane.ERROR_MESSAGE );
	        System.exit( 1 );
	    }
		
	}*/
	
}

}

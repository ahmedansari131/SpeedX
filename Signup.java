// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import javax.swing.JOptionPane;

// public class Signup extends Frame{
// 	// Signup Modal Data entry to database code
// 	public Signup() {
// 		try {
// 			Class.forName("com.mysql.cj.jdbc.Driver");
// 			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "");
// 			String sql = "INSERT INTO signup (username, password)" +
// 					"VALUES (?, ?)";
// 			PreparedStatement ps = con.prepareStatement(sql);
// 			String user_name = tSignupUsername.getText();
// 			String pass = String.valueOf(tSignupPassword.getPassword());
// 			// ps.setString(1, tSignupUsername.getText());
// 			ps.setString(1, user_name);
// 			ps.setString(2, pass);
// 			System.out.println(user_name);
// 			// ps.setString(2, String.valueOf(tSignupPassword.getPassword()));

// 			if (String.valueOf(tSignupPassword.getPassword()).length() < 8) {
// 			JOptionPane.showMessageDialog(this,
// 			"Password must be greater than 8 characters",
// 			"Try again",
// 			JOptionPane.ERROR_MESSAGE);
// 			System.out.println("Error");
// 			return;
// 			}

// 			if (!String.valueOf(tSignupPassword.getPassword()).equals(String.valueOf(tcpassword.getPassword()))) {
// 				JOptionPane.showMessageDialog(this,
// 						"Confirm Password does not match",
// 						"Try again",
// 						JOptionPane.ERROR_MESSAGE);
// 				return;
// 			}

// 			if (tSignupUsername != null && tSignupPassword != null && tcpassword != null) {
// 				JOptionPane.showMessageDialog(this,
// 						"Registered Successfully",
// 						"Congratulations!",
// 						JOptionPane.PLAIN_MESSAGE);
// 				tSignupUsername.setText("");
// 				tSignupPassword.setText("");
// 				tcpassword.setText("");
// 			} else {
// 				JOptionPane.showMessageDialog(this,
// 						"Failed to register new user",
// 						"Try again",
// 						JOptionPane.ERROR_MESSAGE);
// 			}

// 			ps.executeUpdate();
// 			ps.close();
// 			con.close();
// 		} catch (Exception e1) {
// 			e1.printStackTrace();
// 		}
// 	}
// }

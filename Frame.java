import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener, KeyListener {

  JButton timeLimitSixty = new JButton("60");
  JButton timeLimitThirty = new JButton("30");
  String passage = ""; // Passage we get
  String typedPass = ""; // Passage the user types
  String message = ""; // Message to display at the end of the TypingTest
  String userName;
  String playerTwoAcc;
  String playerOneAcc;
  String timeString;

  int typed = 0; // typed stores till which character the user has typed
  int count = 0;
  int WPM;
  int second = 0;
  int incTyped = 0;
  int corTyped = 0;
  int allCharTyped = 0;
  int playerOneWpm;
  int playerTwoWpm;
  int start;
  int timeLimit;
  int i = 0;
  int j = 0;

  double end;
  double accuracy;
  double elapsed;
  double seconds;
  double playerOneAccSample;
  double playerTwoAccSample;

  boolean running; // If the person is typing
  boolean ended = false; // Whether the typing test has ended or not
  boolean timer_ = false;
  boolean login = false;
  boolean logout = false;
  boolean signUp = false;
  boolean competition = false;
  boolean normal = true;

  final int SCREEN_WIDTH;
  final int SCREEN_HEIGHT;
  final int DELAY = 1000;

  JButton typingStart;
  JButton restartTyping;
  Timer timer;
  JLabel label;

  JPanel p1 = new JPanel();
  JPanel dBoardPanel = new JPanel();
  JPanel signupPanel = new JPanel();
  JPanel loginPanel = new JPanel();
  JPanel detailsPanel = new JPanel();
  // JPanel resultPanel = new JPanel();
  JPanel hide = new JPanel();
  JPanel resultsPanel = new JPanel();
  JPanel timeLimitPanel = new JPanel();

  JLabel l1 = new JLabel();
  JLabel l2 = new JLabel();
  JLabel signupLabel = new JLabel();
  JLabel counterLabel = new JLabel();
  JLabel pressLabel = new JLabel();
  JLabel pressLabelYellow = new JLabel();
  JLabel welcTextLabel = new JLabel();
  JLabel welcUserLabel = new JLabel();
  JLabel resLabel = new JLabel();
  JLabel endResLabel = new JLabel();
  JLabel speedLabel = new JLabel();
  JLabel accuracyLabel = new JLabel();
  JLabel playerOneName = new JLabel();
  JLabel playerTwoName = new JLabel();
  JLabel playerWpmOne = new JLabel();
  JLabel playerWpmTwo = new JLabel();
  JLabel playerAccOne = new JLabel();
  JLabel playerAccTwo = new JLabel();
  JLabel greetingLabel = new JLabel();
  JLabel winnerLabel = new JLabel();
  JLabel loserLabel = new JLabel();
  JLabel timeLimitLabel = new JLabel();

  JButton signupBtn = new JButton();
  JButton loginBtn = new JButton();
  JButton signupModalBtn = new JButton();
  JButton loginModalBtn = new JButton();
  JButton logoutBtn = new JButton();
  JButton showPrevRec = new JButton();
  JButton home = new JButton();
  JButton closeBtn = new JButton();
  JButton closeSignupBtn = new JButton();
  JButton endTest = new JButton();
  JButton homeBtn = new JButton();
  JButton clickToStart = new JButton();
  JButton clickToEnter = new JButton();
  JButton clickToNext = new JButton();
  JButton clickToShowFinalRes = new JButton();
  JButton refreshBtn = new JButton();
  JButton showResult = new JButton();

  // Competition section
  JLabel compLabel = new JLabel();
  JLabel startCompLabel = new JLabel();
  JLabel detailsLabel = new JLabel();
  JLabel playerOneLabel = new JLabel();
  JLabel playerTwoLabel = new JLabel();
  JTextField playerOneField = new JTextField();
  JTextField playerTwoField = new JTextField();
  JButton enterDetailBtn = new JButton();
  JButton detCloseBtn = new JButton();

  // Normal Typing
  JLabel yellowTypingLabel = new JLabel();
  JLabel normalTypingLabel = new JLabel();
  JButton clickToStartTypingBtn = new JButton();

  JLabel loginUsernameLabel = new JLabel();
  JLabel loginLabel = new JLabel();
  JLabel loginLabel1 = new JLabel();
  JLabel loginYellow = new JLabel();
  JLabel loginPasswordLabel = new JLabel();
  JTextField tLoginUsername = new JTextField();
  JPasswordField tLoginPassword = new JPasswordField();

  JLabel signupUsernameLabel = new JLabel();
  JLabel signupPasswordLabel = new JLabel();
  JLabel cpasswordLabel = new JLabel();
  JTextField tSignupUsername = new JTextField();
  JPasswordField tSignupPassword = new JPasswordField();
  JPasswordField tcpassword = new JPasswordField();

  Font myFont = new Font("Roboto", Font.BOLD, 38);
  Font myFont1 = new Font("Roboto", Font.BOLD, 42);
  Font loginFont = new Font("Roboto", Font.PLAIN, 18);
  Font signupFont = new Font("Roboto", Font.PLAIN, 22);
  Font tUsernameFont = new Font("Roboto", Font.PLAIN, 15);
  Font StartBtnFont = new Font("Roboto", Font.PLAIN, 28);
  Font homeBtnFont = new Font("Roboto", Font.PLAIN, 25);
  Color yellow = new Color(255, 215, 0);
  Color white = new Color(255, 255, 255);
  Color headerColor = new Color(32, 32, 32);

  public Frame() {
    SCREEN_WIDTH = 1500;
    SCREEN_HEIGHT = 800;

    Image icon = Toolkit.getDefaultToolkit().getImage("X-01.png");
    this.setIconImage(icon);

    this.setVisible(true);
    this.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(null);
    this.setResizable(false);

    p1.setBounds(0, 0, 1500, 800);
    p1.setLayout(null);
    p1.setBackground(headerColor);
    p1.setVisible(true);

    // resultPanel.setBounds(0, 0, 1500, 800);
    // resultPanel.setLayout(null);
    // resultPanel.setBackground(headerColor);
    // resultPanel.setVisible(false);

    l1.setText("Speed");
    l1.setFont(new Font("Roboto", Font.BOLD, 37));
    l2.setText("X");
    l1.setFont(myFont);
    l2.setFont(myFont);
    l1.setBounds(25, 15, 500, 40);
    l2.setBounds(139, 15, 500, 40);
    l1.setForeground(white);
    l2.setForeground(yellow);

    signupBtn.addActionListener(this);
    signupBtn.setBounds(1230, 15, 100, 37);
    signupBtn.setVisible(true);
    signupBtn.setBackground(yellow);
    signupBtn.setText("Signup");
    signupBtn.setFont(loginFont);
    signupBtn.setBorder(null);
    signupBtn.setFocusable(false);
    signupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    signupBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            signupPanel.setVisible(true);
            clickToStart.setEnabled(false);
            clickToStartTypingBtn.setEnabled(false);
            loginBtn.setEnabled(false);
          }
        });

    signupPanel.setLayout(null);
    signupPanel.setBounds(500, 140, 500, 425);
    signupPanel.setBackground(white);
    signupPanel.setVisible(false);

    signupLabel.setBounds(190, 20, 500, 70);
    signupLabel.setText("Sign Up");
    signupLabel.setFont(myFont);
    signupLabel.setForeground(Color.BLACK);

    signupUsernameLabel.setForeground(headerColor);
    signupUsernameLabel.setFont(signupFont);
    signupUsernameLabel.setText("Username");
    signupUsernameLabel.setBounds(35, 100, 500, 100);

    signupPasswordLabel.setBounds(35, 165, 500, 100);
    signupPasswordLabel.setForeground(headerColor);
    signupPasswordLabel.setFont(signupFont);
    signupPasswordLabel.setText("Password");

    cpasswordLabel.setBounds(35, 230, 500, 100);
    cpasswordLabel.setForeground(headerColor);
    cpasswordLabel.setFont(signupFont);
    cpasswordLabel.setText("Confirm Password");

    tSignupUsername.setBounds(270, 135, 185, 28);
    tSignupUsername.setFont(tUsernameFont);
    tSignupPassword.setBounds(270, 200, 185, 28);
    tcpassword.setBounds(270, 267, 185, 28);

    signupModalBtn.setBounds(35, 342, 100, 37);
    signupModalBtn.setVisible(true);
    signupModalBtn.setBackground(yellow);
    signupModalBtn.setText("Signup");
    signupModalBtn.setFont(loginFont);
    signupModalBtn.setBorder(null);
    signupModalBtn.setFocusable(false);
    signupModalBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    signupModalBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            registerUser();
            if (signUp) {
              JLabel loginLabel1 = new JLabel();
              loginPanel.add(loginLabel1);
              loginLabel1.setBounds(325, 20, 500, 70);
              loginLabel1.setText("login");
              loginLabel1.setFont(myFont);
              loginLabel1.setForeground(yellow);
              signupPanel.setVisible(false);
              loginPanel.setVisible(true);
              loginLabel.setText("Now you can");
              loginLabel.setBounds(94, 20, 500, 70);
            }
          }
        });

    closeSignupBtn.setBounds(162, 342, 100, 37);
    closeSignupBtn.setVisible(true);
    closeSignupBtn.setForeground(Color.black);
    closeSignupBtn.setBackground(white);
    closeSignupBtn.setText("Close");
    closeSignupBtn.setFont(loginFont);
    closeSignupBtn.setBorder(null);
    closeSignupBtn.setFocusable(false);
    closeSignupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    closeSignupBtn.setBorder(BorderFactory.createLineBorder(yellow, 1));
    closeSignupBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            signupPanel.setVisible(false);
            clickToStartTypingBtn.setEnabled(true);
            clickToStart.setEnabled(true);
            loginBtn.setEnabled(true);
          }
        });

    loginBtn.setBounds(1355, 15, 100, 37);
    loginBtn.setVisible(true);
    loginBtn.setBackground(headerColor);
    loginBtn.setForeground(yellow);
    loginBtn.setText("Login");
    loginBtn.setFont(loginFont);
    loginBtn.setBorder(null);
    loginBtn.setFocusable(false);
    loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    loginBtn.addActionListener(this);
    loginBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            loginPanel.setVisible(true);
            clickToEnter.setEnabled(false);
            clickToStartTypingBtn.setEnabled(false);
            clickToStart.setEnabled(false);
            signupBtn.setEnabled(false);
            tLoginUsername.setText("");
            tLoginPassword.setText("");
          }
        });
    

    loginPanel.setLayout(null);
    loginPanel.setBounds(500, 140, 500, 385);
    loginPanel.setBackground(white);
    loginPanel.setVisible(false);

    loginLabel.setBounds(190, 20, 500, 70);
    loginLabel.setText("Login");
    loginLabel.setFont(myFont);
    loginLabel.setForeground(Color.BLACK);

    loginUsernameLabel.setForeground(headerColor);
    loginUsernameLabel.setFont(signupFont);
    loginUsernameLabel.setText("Username");
    loginUsernameLabel.setBounds(35, 105, 500, 100);

    loginPasswordLabel.setBounds(35, 180, 500, 100);
    loginPasswordLabel.setForeground(headerColor);
    loginPasswordLabel.setFont(signupFont);
    loginPasswordLabel.setText("Password");

    tLoginUsername.setBounds(270, 150, 185, 28);
    tLoginUsername.setFont(tUsernameFont);
    tLoginPassword.setBounds(270, 215, 185, 28);

    loginModalBtn.setBounds(35, 295, 100, 37);
    loginModalBtn.setVisible(true);
    loginModalBtn.setBackground(yellow);
    loginModalBtn.setText("Login");
    loginModalBtn.setFont(loginFont);
    loginModalBtn.setBorder(null);
    loginModalBtn.setFocusable(false);
    loginModalBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    loginModalBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            loginUser();
            clickToEnter.setEnabled(true);
            clickToStartTypingBtn.setEnabled(true);
            clickToStart.setEnabled(true);
            tLoginUsername.setText("");
            tLoginPassword.setText("");
          }
        });

    closeBtn.setBounds(170, 295, 100, 37);
    closeBtn.setVisible(true);
    closeBtn.setBackground(white);
    closeBtn.setForeground(Color.BLACK);
    closeBtn.setText("Close");
    closeBtn.setFont(new Font("Roboto", Font.PLAIN, 18));
    // closeBtn.setBorder(null);
    closeBtn.setBorder(BorderFactory.createLineBorder(yellow, 1));
    closeBtn.setFocusable(false);
    closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    closeBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            loginPanel.setVisible(false);
            signupBtn.setEnabled(true);
            loginBtn.setEnabled(true);
            clickToStartTypingBtn.setEnabled(true);
            clickToStart.setEnabled(true);
          }
        });

    logoutBtn.setBounds(1355, 15, 100, 37);
    logoutBtn.setVisible(false);
    logoutBtn.setBackground(yellow);
    logoutBtn.setText("Logout");
    logoutBtn.setFont(loginFont);
    logoutBtn.setBorder(null);
    logoutBtn.setFocusable(false);
    logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    logoutBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            logoutUser();
            logoutBtn.setVisible(false);
            showPrevRec.setVisible(false);
            endTest.setVisible(false);
            dispose();
            new Frame();
          }
        });

    typingStart = new JButton();
    typingStart.setBounds(25, 705, 1435, 45);
    typingStart.setBackground(yellow);
    typingStart.setText("Start");
    typingStart.setFocusable(false);
    typingStart.setFont(StartBtnFont);
    typingStart.setBorder(null);
    typingStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
    typingStart.addActionListener(this);
    typingStart.setVisible(false);

    restartTyping = new JButton();
    restartTyping.setBounds(25, 705, 1435, 45);
    restartTyping.setBackground(yellow);
    restartTyping.setText("Restart");
    restartTyping.setFocusable(false);
    restartTyping.setFont(StartBtnFont);
    restartTyping.setBorder(null);
    restartTyping.setCursor(new Cursor(Cursor.HAND_CURSOR));
    restartTyping.addActionListener(this);
    restartTyping.setFocusable(false);
    restartTyping.setVisible(false);

    endTest.setBounds(25, 705, 1435, 45);
    endTest.setBackground(yellow);
    endTest.setText("End Test");
    endTest.setFocusable(false);
    endTest.setFont(StartBtnFont);
    endTest.setBorder(null);
    endTest.setCursor(new Cursor(Cursor.HAND_CURSOR));
    endTest.setFocusable(false);
    endTest.setVisible(false);
    endTest.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (login) {
              welcTextLabel.setBounds(1065, 15, 500, 40);
              welcUserLabel.setBounds(1150, 15, 500, 40);
              logoutBtn.setVisible(true);
              endTest.setVisible(false);
              pressLabel.setVisible(false);
              pressLabelYellow.setVisible(false);
              homeBtn.setVisible(false);
              showPrevRec.setVisible(true);
              clickToStart.setVisible(true);
              compLabel.setVisible(true);
              yellowTypingLabel.setVisible(true);
              normalTypingLabel.setVisible(true);
              startCompLabel.setVisible(true);
              clickToStartTypingBtn.setVisible(true);
              insertResult();
              // typingStart.setVisible(true);
            }
            if (logout) {
              endTest.setVisible(false);
            }
          }
        });

    label = new JLabel();
    label.setText("Click the Start Button");
    label.setFont(new Font("MV Boli", Font.BOLD, 30));
    label.setVisible(true);
    label.setOpaque(true);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setBackground(Color.lightGray);

    counterLabel.setBounds(121, 208, 70, 50);
    counterLabel.setHorizontalAlignment(JLabel.CENTER);
    counterLabel.setFont(StartBtnFont);
    counterLabel.setForeground(white);

    pressLabel.setBounds(530, 325, 1000, 50);
    pressLabel.setText(" the start button to start typing");
    pressLabel.setFont(myFont);
    pressLabel.setForeground(white);
    pressLabel.setVisible(false);

    pressLabelYellow.setText("Press");
    pressLabelYellow.setBounds(420, 324, 500, 50);
    pressLabelYellow.setFont(myFont1);
    pressLabelYellow.setForeground(yellow);
    pressLabelYellow.setVisible(false);

    normalTypingLabel.setText("Normal");
    normalTypingLabel.setBounds(1135, 320, 200, 50);
    normalTypingLabel.setFont(new Font("ROBOTO", Font.PLAIN, 30));
    normalTypingLabel.setForeground(white);
    yellowTypingLabel.setText("Typing");
    yellowTypingLabel.setBounds(1250, 320, 200, 50);
    yellowTypingLabel.setFont(new Font("ROBOTO", Font.BOLD, 30));
    yellowTypingLabel.setForeground(yellow);

    startCompLabel.setText("Start");
    startCompLabel.setBounds(140, 320, 200, 50);
    startCompLabel.setFont(new Font("ROBOTO", Font.PLAIN, 30));
    startCompLabel.setForeground(white);
    compLabel.setText("Competition");
    compLabel.setBounds(215, 320, 200, 50);
    compLabel.setFont(new Font("ROBOTO", Font.BOLD, 30));
    compLabel.setForeground(yellow);

    clickToStartTypingBtn = new JButton();
    clickToStartTypingBtn.setBounds(1165, 395, 150, 40);
    clickToStartTypingBtn.setBackground(yellow);
    clickToStartTypingBtn.setText("Click to Start");
    clickToStartTypingBtn.setFocusable(false);
    clickToStartTypingBtn.setFont(loginFont);
    clickToStartTypingBtn.setBorder(null);
    clickToStartTypingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    // clickToStartTypingBtn.addActionListener(this);
    clickToStartTypingBtn.setVisible(true);
    clickToStartTypingBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            timeLimitPanel.setVisible(true);
          }
        });

    clickToStart.setText("Click to Start");
    clickToStart.setBounds(185, 395, 150, 40);
    clickToStart.setBackground(yellow);
    clickToStart.setFont(loginFont);
    clickToStart.setBorder(null);
    clickToStart.setFocusable(false);
    clickToStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
    clickToStart.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (login) {
              detailsPanel.setVisible(true);
              logoutBtn.setEnabled(false);
              showPrevRec.setEnabled(false);
              playerOneLabel.setText("Player 1:                          " + userName);

            } else {
              JOptionPane.showMessageDialog(p1,
                  "Please login to start",
                  "Try again",
                  JOptionPane.PLAIN_MESSAGE);
              return;
            }
            signupBtn.setEnabled(false);
            loginBtn.setEnabled(false);
            clickToStartTypingBtn.setEnabled(false);
          }
        });

    detailsPanel.setLayout(null);
    detailsPanel.setBounds(500, 170, 500, 385);
    detailsPanel.setBackground(white);
    detailsPanel.setVisible(false);

    detailsLabel.setBounds(110, 20, 500, 70);
    detailsLabel.setText("Enter the details");
    detailsLabel.setFont(myFont);
    detailsLabel.setForeground(Color.BLACK);
    playerOneLabel.setForeground(headerColor);
    playerOneLabel.setFont(signupFont);
    playerOneLabel.setBounds(35, 105, 500, 100);

    playerTwoLabel.setBounds(35, 180, 500, 100);
    playerTwoLabel.setForeground(headerColor);
    playerTwoLabel.setFont(signupFont);
    playerTwoLabel.setText("Player 2");
    playerTwoField.setBounds(270, 215, 185, 28);
    playerTwoField.setFont(tUsernameFont);

    clickToEnter.setBounds(35, 295, 100, 37);
    clickToEnter.setVisible(true);
    clickToEnter.setBackground(yellow);
    clickToEnter.setText("Enter");
    clickToEnter.setFont(loginFont);
    clickToEnter.setBorder(null);
    clickToEnter.setFocusable(false);
    clickToEnter.setCursor(new Cursor(Cursor.HAND_CURSOR));
    clickToEnter.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            
            if(playerTwoField.getText().length() == 0){
              JOptionPane.showMessageDialog(
                p1,
                "Enter the Player two name",
                "Try again",
                JOptionPane.ERROR_MESSAGE);
                System.out.println("if " + playerTwoField.getText());
            // return;
            }
            else{
              timeLimitPanel.setVisible(true);
              detailsPanel.setVisible(false);
              j++;

            }
          }
        });

    clickToNext.setBounds(25, 705, 1435, 45);
    clickToNext.setBackground(yellow);
    clickToNext.setText("Next");
    clickToNext.setFocusable(false);
    clickToNext.setFont(StartBtnFont);
    clickToNext.setBorder(null);
    clickToNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
    clickToNext.addActionListener(this);
    clickToNext.setFocusable(false);
    clickToNext.setVisible(false);
    clickToNext.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            playerTwoTurn();
            competition = true;
            playerOneWpm = 0;
            playerOneAccSample = 0;
            playerOneWpm = WPM;
            playerOneAccSample = accuracy;
            playerOneAcc = String.format("%.2f", playerOneAccSample);

            if (competition) {
              clickToNext.setVisible(false);
              restartTyping.setVisible(true);
              endTest.setVisible(false);
              welcUserLabel.setVisible(false);
              playerTwoName.setVisible(true);
              playerTwoName.setBounds(1355, 15, 500, 40);
              playerTwoName.setFont(new Font("Roboto", Font.BOLD, 18));
              i++;
            }
          }
        });

    clickToShowFinalRes.setBounds(25, 705, 1435, 45);
    clickToShowFinalRes.setBackground(yellow);
    clickToShowFinalRes.setText("Show Final Result");
    clickToShowFinalRes.setFocusable(false);
    clickToShowFinalRes.setFont(StartBtnFont);
    clickToShowFinalRes.setBorder(null);
    clickToShowFinalRes.setCursor(new Cursor(Cursor.HAND_CURSOR));
    clickToShowFinalRes.addActionListener(this);
    clickToShowFinalRes.setFocusable(false);
    clickToShowFinalRes.setVisible(false);
    clickToShowFinalRes.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

          }
        });

    detCloseBtn.setBounds(170, 295, 100, 37);
    detCloseBtn.setVisible(true);
    detCloseBtn.setBackground(white);
    detCloseBtn.setForeground(Color.BLACK);
    detCloseBtn.setText("Close");
    detCloseBtn.setFont(new Font("Roboto", Font.PLAIN, 18));
    detCloseBtn.setBorder(BorderFactory.createLineBorder(yellow, 1));
    detCloseBtn.setFocusable(false);
    detCloseBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    detCloseBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            detailsPanel.setVisible(false);
            signupBtn.setEnabled(true);
            loginBtn.setEnabled(true);
            clickToStartTypingBtn.setEnabled(true);
            logoutBtn.setEnabled(true);
            showPrevRec.setEnabled(true);
          }
        });

    resultsPanel.setLayout(null);
    resultsPanel.setBounds(70, 130, 1200, 600);

    resultsPanel.setVisible(false);
    resultsPanel.add(playerTwoName);
    resultsPanel.add(playerOneName);
    resultsPanel.add(playerWpmOne);
    resultsPanel.add(playerAccOne);
    resultsPanel.add(playerWpmTwo);
    resultsPanel.add(playerAccTwo);
    resultsPanel.add(winnerLabel);
    resultsPanel.add(loserLabel);
    resultsPanel.add(greetingLabel);
    resultsPanel.setBackground(headerColor);

    playerOneName.setVisible(true);
    playerOneName.setBounds(200, 20, 200, 80);
    playerOneName.setFont(new Font("Roboto", Font.BOLD, 40));
    playerOneName.setForeground(yellow);

    playerWpmOne.setBounds(200, 100, 250, 80);
    playerWpmOne.setForeground(white);
    playerWpmOne.setFont(new Font("Roboto", Font.PLAIN, 28));

    playerAccOne.setBounds(200, 150, 500, 80);
    playerAccOne.setForeground(white);
    playerAccOne.setFont(new Font("Roboto", Font.PLAIN, 28));

    playerWpmTwo.setBounds(900, 100, 250, 80);
    playerWpmTwo.setForeground(white);
    playerWpmTwo.setFont(new Font("Roboto", Font.PLAIN, 28));

    playerAccTwo.setBounds(900, 150, 500, 80);
    playerAccTwo.setForeground(white);
    playerAccTwo.setFont(new Font("Roboto", Font.PLAIN, 28));

    greetingLabel.setText("Congratulations!");
    greetingLabel.setBounds(200, 280, 600, 80);
    greetingLabel.setFont(new Font("Roboto", Font.PLAIN, 40));
    greetingLabel.setForeground(yellow);

    winnerLabel.setBounds(520, 280, 600, 80);
    winnerLabel.setFont(new Font("Roboto", Font.BOLD, 40));
    winnerLabel.setForeground(yellow);

    loserLabel.setBounds(200, 360, 600, 80);
    loserLabel.setFont(new Font("Roboto", Font.PLAIN, 25));
    loserLabel.setForeground(yellow);
    loserLabel.setForeground(new Color(128, 128, 128));

    timeLimitPanel.setLayout(null);
    timeLimitPanel.setBounds(645, 330, 200, 120);
    timeLimitPanel.setBackground(white);
    timeLimitPanel.setVisible(false);
    timeLimitPanel.add(timeLimitSixty);
    timeLimitPanel.add(timeLimitThirty);
    timeLimitPanel.add(timeLimitLabel);

    timeLimitLabel.setText("Choose (Seconds)");
    timeLimitLabel.setBounds(25, 5, 200, 45);
    timeLimitLabel.setFont(new Font("Roboto", Font.BOLD, 18));

    timeLimitThirty.setBounds(25, 60, 52, 40);
    timeLimitThirty.setBackground(yellow);
    timeLimitThirty.setForeground(headerColor);
    timeLimitThirty.setBorder(null);
    timeLimitThirty.setCursor(new Cursor(Cursor.HAND_CURSOR));
    timeLimitThirty.setFont(new Font("Roboto", Font.PLAIN, 18));
    timeLimitThirty.setFocusable(false);
    timeLimitThirty.addActionListener(this);
    timeLimitThirty.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            timeString = timeLimitThirty.getText();
            timeLimit = Integer.parseInt(timeString);
            if (j == 1) {
              competition = true;
              runningStart(timeLimit);
              competitionStart();
              enterCompDetails();
              if (competition) {
                welcTextLabel.setVisible(false);
                welcUserLabel.setText(userName);
                welcUserLabel.setVisible(true);
              }
              logoutBtn.setEnabled(true);
              showPrevRec.setEnabled(true);
              clickToStartTypingBtn.setEnabled(true);
            }
          }
        });

    timeLimitSixty.setBounds(123, 60, 52, 40);
    timeLimitSixty.setBackground(yellow);
    timeLimitSixty.setForeground(headerColor);
    timeLimitSixty.setBorder(null);
    timeLimitSixty.setCursor(new Cursor(Cursor.HAND_CURSOR));
    timeLimitSixty.setFont(new Font("Roboto", Font.PLAIN, 18));
    timeLimitSixty.setFocusable(false);
    timeLimitSixty.addActionListener(this);
    timeLimitSixty.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            timeString = timeLimitSixty.getText();
            timeLimit = Integer.parseInt(timeString);
            if (j == 1) {
              competition = true;
              runningStart(timeLimit);
              competitionStart();
              enterCompDetails();
              if (competition) {
                welcTextLabel.setVisible(false);
                welcUserLabel.setText(userName);
                welcUserLabel.setVisible(true);
              }
              logoutBtn.setEnabled(true);
              showPrevRec.setEnabled(true);
              clickToStartTypingBtn.setEnabled(true);
            }
          }
        });

    this.add(l1);
    this.add(l2);
    this.add(signupBtn);
    this.add(loginBtn);
    this.add(logoutBtn);
    this.add(welcTextLabel);
    this.add(welcUserLabel);
    this.add(typingStart);
    this.add(restartTyping);
    this.add(clickToNext);
    this.add(clickToShowFinalRes);
    this.add(endTest);
    this.add(showResult);
    this.add(counterLabel);
    this.add(signupPanel);
    this.add(loginPanel);
    this.add(pressLabel);
    this.add(pressLabelYellow);
    this.add(showPrevRec);
    this.add(homeBtn);
    this.add(detailsPanel);
    this.add(normalTypingLabel);
    this.add(yellowTypingLabel);
    this.add(clickToStartTypingBtn);
    this.add(timeLimitPanel);
    // this.add(resultPanel);
    this.add(p1);
    p1.add(hide);
    p1.add(resultsPanel);

    p1.add(compLabel);
    p1.add(clickToStart);
    p1.add(startCompLabel);

    // resultPanel.add(endResLabel);
    // resultPanel.add(speedLabel);
    // resultPanel.add(accuracyLabel);

    detailsPanel.add(detailsLabel);
    detailsPanel.add(clickToEnter);
    detailsPanel.add(enterDetailBtn);
    detailsPanel.add(playerOneField);
    detailsPanel.add(playerTwoField);
    detailsPanel.add(playerOneLabel);
    detailsPanel.add(playerTwoLabel);
    detailsPanel.add(detCloseBtn);

    signupPanel.add(signupUsernameLabel);
    signupPanel.add(signupPasswordLabel);
    signupPanel.add(tSignupUsername);
    signupPanel.add(cpasswordLabel);
    signupPanel.add(tSignupPassword);
    signupPanel.add(tcpassword);
    signupPanel.add(signupModalBtn);
    signupPanel.add(signupLabel);
    signupPanel.add(closeSignupBtn);

    loginPanel.add(loginUsernameLabel);
    loginPanel.add(loginPasswordLabel);
    loginPanel.add(tLoginUsername);
    loginPanel.add(tLoginPassword);
    loginPanel.add(loginModalBtn);
    loginPanel.add(loginLabel);
    loginPanel.add(closeBtn);
    loginPanel.add(loginLabel1);
    loginPanel.add(loginYellow);

    this.getContentPane().setBackground(Color.WHITE);
    this.addKeyListener(this);
    this.setFocusable(true);
    this.setResizable(false);
    this.setTitle("SpeedX");
    this.revalidate();

    simpleTimer();
  }

  // @Override
  public void paint(Graphics g) {
    super.paint(g);
    draw(g);
  }

  public void draw(Graphics g) {
    g.setFont(new Font("Roboto", Font.PLAIN, 26));
    g.setColor(new Color(128, 128, 128));
    if (running) {
      if (passage.length() > 1) {
        g.drawString(passage.substring(0, 100), 150, g.getFont().getSize() * 13);
        g.drawString(passage.substring(100, 200), 150, g.getFont().getSize() * 15);
        g.drawString(passage.substring(200, 300), 150, g.getFont().getSize() * 17);
        g.drawString(passage.substring(300, 400), 150, g.getFont().getSize() * 19);
      }

      // Displaying correctly typed passage in yellow
      g.setColor(yellow);
      if (typedPass.length() > 0) {
        if (typed < 100)
          g.drawString(typedPass.substring(0, typed), 150, g.getFont().getSize() * 13);
        else
          g.drawString(typedPass.substring(0, 100), 150, g.getFont().getSize() * 13);
      }
      if (typedPass.length() > 100) {
        if (typed < 200)
          g.drawString(typedPass.substring(100, typed), 150, g.getFont().getSize() * 15);
        else
          g.drawString(typedPass.substring(100, 200), 150, g.getFont().getSize() * 15);
      }
      if (typedPass.length() > 200) {
        if (typed < 300) {
          g.drawString(typedPass.substring(200, typed), 150, g.getFont().getSize() * 17);
          running = false;
        } else
          g.drawString(typedPass.substring(200, 300), 150, g.getFont().getSize() * 17);
      }
      if (typedPass.length() > 300) {
        if (typed < 400) {
          g.drawString(typedPass.substring(300, typed), 150, g.getFont().getSize() * 19);
          running = false;
        } else
          g.drawString(typedPass.substring(300, 400), 150, g.getFont().getSize() * 19);
      }
    }

    if (ended && normal == false) {
      running = false;
      if (WPM <= 40)
        message = "You are an Average Typist";
      else if (WPM > 40 && WPM <= 60)
        message = "You are a Good Typist";
      else if (WPM > 60 && WPM <= 100)
        message = "You are an Excellent Typist";
      else
        message = "You are an Elite Typist";

      FontMetrics metrics = getFontMetrics(g.getFont());
      Font myFont = new Font("Roboto", Font.BOLD, 35);
      Color yellow = new Color(255, 215, 0);

      g.setColor(Color.white);
      g.setColor(yellow);
      g.setFont(myFont);
      g.drawString("Typing Test Completed!", 540, g.getFont().getSize() * 9);

      Font descF = new Font("Roboto", Font.PLAIN, 25);
      Color white = new Color(255, 255, 255);
      g.setColor(white);
      g.setFont(descF);
      g.drawString("Speed: " + WPM + " WPM",
          (SCREEN_WIDTH - metrics.stringWidth("Speed: " + WPM + " WPM")) / 2,
          g.getFont().getSize() * 15);
      g.drawString("Accuracy: " + String.format("%.2f", accuracy) + "%",
          (SCREEN_WIDTH - metrics.stringWidth("Accuracy: " + String.format("%.2f", accuracy) + "%")) / 2,
          g.getFont().getSize() * 17);
      g.drawString(message,
          (SCREEN_WIDTH - metrics.stringWidth(message)) / 2, g.getFont().getSize() * 19);

      if (login) {
        if (ended) {
          if (!competition) {
            endTest.setVisible(true);
          }
          restartTyping.setVisible(false);
          typingStart.setVisible(false);
        }
      }

      if (competition) {
        if (ended) {
          restartTyping.setVisible(false);
          clickToNext.setVisible(true);
          if (i > 0) {
            endTest.setVisible(false);
            clickToNext.setVisible(false);
            showResult.setBounds(25, 705, 1435, 45);
            showResult.setBackground(yellow);
            showResult.setText("Show Result");
            showResult.setFocusable(false);
            showResult.setFont(StartBtnFont);
            showResult.setBorder(null);
            showResult.setCursor(new Cursor(Cursor.HAND_CURSOR));
            showResult.setFocusable(false);
            showResult.setVisible(true);
            // competition = false;
            showResult.addActionListener(
                new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                    playerTwoWpm = 0;
                    playerTwoAccSample = 0;
                    playerTwoWpm = WPM;
                    playerTwoAccSample = accuracy;
                    playerTwoAcc = String.format("%.2f", playerTwoAccSample);
                    playerTwoName.setVisible(false);
                    showResult.setVisible(false);
                    homeBtn.setVisible(true);
                    homeBtn.setBounds(25, 705, 1435, 45);
                    homeBtn.setBackground(yellow);
                    homeBtn.setForeground(headerColor);
                    homeBtn.addActionListener(
                        new ActionListener() {
                          @Override
                          public void actionPerformed(ActionEvent e) {
                            welcTextLabel.setVisible(true);
                            competition = false;
                            playerTwoField.setText("");
                            i = 0;
                            j = 0;
                          }
                        });
                    resultsPanel.setVisible(true);

                    resultsPanel.add(playerTwoName);
                    playerTwoName.setVisible(true);
                    playerTwoName.setBounds(900, 20, 300, 80);
                    playerTwoName.setFont(new Font("Roboto", Font.BOLD, 40));
                    playerTwoName.setForeground(yellow);
                    playerOneName.setText(userName);
                    playerWpmOne.setText("WPM                " + playerOneWpm);
                    playerAccOne.setText("Accuracy        " + playerOneAcc + "%");
                    playerWpmTwo.setText("WPM                " + playerTwoWpm);
                    playerAccTwo.setText("Accuracy        " + playerTwoAcc + "%");

                    if (playerOneWpm > playerTwoWpm) {
                      winnerLabel.setText(playerOneName.getText() + "  Won");
                      loserLabel.setText(playerTwoName.getText() + ", better luck next time");
                    } else if (playerOneWpm < playerTwoWpm) {
                      winnerLabel.setText(playerTwoName.getText() + "  Won");
                      loserLabel.setText(playerOneName.getText() + ", better luck next time");
                    } else if (playerOneWpm == playerTwoWpm && playerOneAccSample < playerTwoAccSample) {
                      winnerLabel.setText(playerTwoName.getText() + "  Won");
                      loserLabel.setText(playerOneName.getText() + ", better luck next time");
                    } else if (playerOneWpm == playerTwoWpm && playerOneAccSample > playerTwoAccSample) {
                      winnerLabel.setText(playerOneName.getText() + "  Won");
                      loserLabel.setText(playerTwoName.getText() + ", better luck next time");
                    } else if (playerOneWpm == playerTwoWpm && playerOneAccSample == playerTwoAccSample) {
                      greetingLabel.setText("Competition Tied");
                      winnerLabel.setVisible(false);
                      loserLabel.setVisible(false);
                    }
                  }
                });
          }
        }
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (passage.length() > 1) {
      char[] pass = passage.toCharArray();
      if (typed < 400 || typed == 400) {
        running = true;
        normal = false;
        if (e.getKeyChar() == pass[typed]) {
          typedPass = typedPass + pass[typed];
          typed++;
          if (second < timeLimit && second != 0) {
            new SoundClipRight();
          }
          corTyped = corTyped + 1;
        } else {
          if (second < timeLimit && second != 0) {
            new SoundClipWrong();
          }
          incTyped = incTyped + 1;
        }
        allCharTyped = allCharTyped + 1;
      }

      if (second == 0) {
        running = false;
        ended = true;
        normal = true;
      }
      
      if (running) {
        repaint();
      }
      
      if (ended && normal == false) {
        repaint();
      }
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  public void runningStart(int timeLimit) {
    passage = getPassage();
    running = true;
    ended = false;

    typedPass = "";
    message = "";

    typed = 0;
    incTyped = 0;
    corTyped = 0;
    allCharTyped = 1;
    second = timeLimit;
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timeLimitThirty || e.getSource() == restartTyping || e.getSource() == timeLimitSixty) {
      runningStart(timeLimit);
      if (running) {
        timeLimitPanel.setVisible(false);
        pressLabel.setVisible(false);
        pressLabelYellow.setVisible(false);
        restartTyping.setVisible(true);
        homeBtn.setVisible(true);
        typingStart.setVisible(false);
        loginBtn.setVisible(false);
        signupBtn.setVisible(false);
        logoutBtn.setVisible(false);
        clickToStart.setVisible(false);
        compLabel.setVisible(false);
        yellowTypingLabel.setVisible(false);
        normalTypingLabel.setVisible(false);
        // resultPanel.setVisible(false);
        startCompLabel.setVisible(false);
        clickToStartTypingBtn.setVisible(false);
        welcUserLabel.setBounds(1355, 15, 500, 40);
        welcTextLabel.setBounds(1270, 15, 500, 40);

        restartTyping.setBounds(25, 705, 680, 45);
        homeBtn.setBounds(778, 705, 680, 45);
        homeBtn.setBackground(headerColor);
        homeBtn.setVisible(true);
        homeBtn.setForeground(yellow);
        homeBtn.setText("Home");
        homeBtn.setFocusable(false);
        homeBtn.setFont(homeBtnFont);
        homeBtn.setBorder(null);
        homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeBtn.setFocusable(false);
        showPrevRec.setVisible(false);
        homeBtn.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                restartTyping.setVisible(false);
                counterLabel.setVisible(false);
                welcTextLabel.setVisible(true);


                // resultPanel.setVisible(false);
                timer.stop();
                homeBtn.setVisible(false);
                if (logout) {
                  showPrevRec.setVisible(false);
                }
                if (login) {
                  showPrevRec.setVisible(true);

                }
                clickToStart.setVisible(true);
                compLabel.setVisible(true);
                yellowTypingLabel.setVisible(true);
                normalTypingLabel.setVisible(true);
                startCompLabel.setVisible(true);
                clickToStartTypingBtn.setVisible(true);
                if (!login) {
                  loginBtn.setVisible(true);
                  signupBtn.setVisible(true);
                }
                if (login) {
                  logoutBtn.setVisible(true);
                  welcTextLabel.setBounds(1065, 15, 500, 40);
                  welcUserLabel.setBounds(1150, 15, 500, 40);
                }
              }
            });
      }
    }
  }

  public void playerTwoTurn() {
    runningStart(timeLimit);
    p1.add(playerTwoName);
    String playerTwo = playerTwoField.getText();
      // System.out.println("Else " + playerTwo);
      playerTwoName.setText(playerTwo);
      playerTwoName.setBounds(1100, 15, 250, 50);
      playerTwoName.setFont(StartBtnFont);
      playerTwoName.setForeground(yellow);
  }

  public void competitionStart() {
    detailsPanel.setVisible(false);

    if (running) {
      pressLabel.setVisible(false);
      pressLabelYellow.setVisible(false);
      restartTyping.setVisible(true);
      homeBtn.setVisible(true);
      typingStart.setVisible(false);
      loginBtn.setVisible(false);
      signupBtn.setVisible(false);
      logoutBtn.setVisible(false);
      clickToStart.setVisible(false);
      compLabel.setVisible(false);
      yellowTypingLabel.setVisible(false);
      normalTypingLabel.setVisible(false);
      startCompLabel.setVisible(false);
      clickToStartTypingBtn.setVisible(false);
      welcUserLabel.setBounds(1355, 15, 500, 40);
      welcTextLabel.setBounds(1270, 15, 500, 40);

      restartTyping.setBounds(25, 705, 680, 45);
      homeBtn.setBounds(778, 705, 680, 45);
      homeBtn.setBackground(headerColor);
      homeBtn.setVisible(true);
      homeBtn.setForeground(yellow);
      homeBtn.setText("Home");
      homeBtn.setFocusable(false);
      homeBtn.setFont(homeBtnFont);
      homeBtn.setBorder(null);
      homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      homeBtn.setFocusable(false);
      showPrevRec.setVisible(false);
      homeBtn.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              loginBtn.setEnabled(true);
              signupBtn.setEnabled(true);
              clickToStartTypingBtn.setEnabled(true);
              playerTwoName.setVisible(false);
              restartTyping.setVisible(false);
              counterLabel.setVisible(false);
              timer.stop();
              homeBtn.setVisible(false);
              if (logout) {
                showPrevRec.setVisible(false);
              }
              if (login) {
                showPrevRec.setVisible(true);
                resultsPanel.setVisible(false);
              }
              clickToStart.setVisible(true);
              compLabel.setVisible(true);
              yellowTypingLabel.setVisible(true);
              normalTypingLabel.setVisible(true);
              startCompLabel.setVisible(true);
              clickToStartTypingBtn.setVisible(true);
              if (!login) {
                loginBtn.setVisible(true);
                signupBtn.setVisible(true);
              }
              if (login) {
                logoutBtn.setVisible(true);
                welcTextLabel.setBounds(1065, 15, 500, 40);
                welcUserLabel.setBounds(1150, 15, 500, 40);
              }
            }
          });
    }
  }

  // Timer ActionListener
  public void simpleTimer() {
    timer = new Timer(DELAY, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent a) {
        second--;
        counterLabel.setText("" + second);
        counterLabel.setVisible(true);

        if (second == 0) {
          running = false;
          timer.stop();
          ended = true;
          counterLabel.setVisible(false);
        }

        if (ended) {
          System.out.println("\nTyped character is: " + typed);
          System.out.println("Incorrect character is: " + incTyped);
          WPM = 0;
          accuracy = 0;
          int y = ((typed / 5) - (incTyped)) / 1;
          System.out.println("Value of y is: " + y);
          if (y >= 0) {
            WPM = WPM + y;
            System.out.println("WPM is: " + WPM);
          } else {
            WPM = 0;
            System.out.println("WPM is: " + WPM);
            System.out.println("\n");
          }
          double x = ((double) corTyped / (double) allCharTyped) * 100;
          accuracy = accuracy + x;
          System.out.println("\nAll typed: " + allCharTyped);
          System.out.println("Correct Typed is: " + corTyped);
          System.out.println("The value of x is: " + x);
          System.out.println("The value of accuracy is: " + accuracy + "\n");
        }

        if (running) {
          repaint();
        }
        if (ended) {
          repaint();
        }
      }
    });
  }

  // Signup Modal Data entry to database code
  public void registerUser() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/registration",
          "root",
          "");
      String sql = "INSERT INTO signup (username, password)" + "VALUES (?, ?)";
      String sql1 = "SELECT username from signup where username = ?";
      PreparedStatement ps = con.prepareStatement(sql);
      PreparedStatement ps1 = con.prepareStatement(sql1);
      ps.setString(1, tSignupUsername.getText());
      ps1.setString(1, tSignupUsername.getText());
      ps.setString(2, String.valueOf(tSignupPassword.getPassword()));
      ResultSet rs = ps1.executeQuery();
      if (rs.next()) {
        JOptionPane.showMessageDialog(
            this,
            "Username already exists",
            "Try again",
            JOptionPane.ERROR_MESSAGE);
        return;
      } else {
        if (String.valueOf(tSignupPassword.getPassword()).length() < 8) {
          JOptionPane.showMessageDialog(
              this,
              "Password must be greater than 8 characters",
              "Try again",
              JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (!String
            .valueOf(tSignupPassword.getPassword())
            .equals(String.valueOf(tcpassword.getPassword()))) {
          JOptionPane.showMessageDialog(
              this,
              "Confirm Password does not match",
              "Try again",
              JOptionPane.ERROR_MESSAGE);
          return;
        }

        if (tSignupUsername != null &&
            tSignupPassword != null &&
            tcpassword != null) {
          JOptionPane.showMessageDialog(
              this,
              "Registered Successfully",
              "Congratulations!",
              JOptionPane.PLAIN_MESSAGE);
          tSignupUsername.setText("");
          tSignupPassword.setText("");
          tcpassword.setText("");
          signUp = true;
        } else {
          JOptionPane.showMessageDialog(
              this,
              "Failed to register new user",
              "Try again",
              JOptionPane.ERROR_MESSAGE);
        }
      }

      ps.executeUpdate();
      ps.close();
      con.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  // Login Modal data verifying code
  public void loginUser() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/registration",
          "root",
          "");
      String sql = "SELECT * from signup where username = ? and password = ?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, tLoginUsername.getText());
      ps.setString(2, String.valueOf(tLoginPassword.getPassword()));

      ResultSet r = ps.executeQuery();
      if (r.next()) {
        welcTextLabel.setVisible(true);
        welcTextLabel.setText("Welcome");
        welcTextLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        welcTextLabel.setBounds(1065, 15, 500, 40);
        welcTextLabel.setForeground(white);
        welcUserLabel.setText(tLoginUsername.getText());
        welcUserLabel.setVisible(true);
        welcUserLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        welcUserLabel.setBounds(1150, 15, 500, 40);
        welcUserLabel.setForeground(yellow);
        userName = tLoginUsername.getText();
        // System.out.println(userName);
        // typingStart.setBounds(25, 705, 710, 45);
        showPrevRec.setBounds(600, 705, 300, 45);
        showPrevRec.setBackground(headerColor);
        showPrevRec.setForeground(yellow);
        showPrevRec.setText("Previous Typing Records");
        showPrevRec.setFocusable(false);
        showPrevRec.setFont(homeBtnFont);
        showPrevRec.setBorder(null);
        showPrevRec.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showPrevRec.setFocusable(false);
        showPrevRec.setVisible(false);
        showPrevRec.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                showDashboard();
                createTable();
                clickToStartTypingBtn.setVisible(false);
                normalTypingLabel.setVisible(false);
                yellowTypingLabel.setVisible(false);
                clickToStart.setVisible(false);
                compLabel.setVisible(false);
                startCompLabel.setVisible(false);
              }
            });
        logoutBtn.setVisible(true);
        loginPanel.setVisible(false);
        loginBtn.setVisible(false);
        signupBtn.setVisible(false);
        showPrevRec.setVisible(true);
        login = true;

        JOptionPane.showMessageDialog(
            this,
            "Logged in, successfully",
            "Welcome to SpeedX",
            JOptionPane.PLAIN_MESSAGE);
        tLoginUsername.setText("");
        tLoginPassword.setText("");
        return;
      } else {
        JOptionPane.showMessageDialog(null, "Incorrect credentials");
      }
      ps.close();
      con.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public void insertResult() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "");
      String sql = "INSERT INTO `results` (`Speed (WPM)`, `Username`, `Accuracy (%)`, `Time`) VALUES (?,?,?,current_timestamp())";
      PreparedStatement ps = con.prepareStatement(sql);
      String user_name = welcUserLabel.getText();
      System.out.println("Username is " + user_name);
      String wordsPerMinute = String.valueOf(WPM);
      String acc = String.valueOf(accuracy);
      ps.setString(1, wordsPerMinute);
      ps.setString(2, user_name);
      ps.setString(3, acc);
      ps.executeUpdate();
      ps.close();
      con.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public void showDashboard() {
    p1.add(dBoardPanel);
    dBoardPanel.add(home);
    dBoardPanel.add(resLabel);
    dBoardPanel.add(refreshBtn);

    welcUserLabel.setBounds(1355, 15, 500, 40);
    welcTextLabel.setBounds(1270, 15, 500, 40);

    dBoardPanel.setBounds(145, 130, 1200, 600);
    dBoardPanel.setLayout(null);
    dBoardPanel.setBackground(headerColor);

    home.setText("Home");
    home.addActionListener(this);
    home.setBounds(1080, 15, 100, 37);
    home.setVisible(true);
    home.setBackground(headerColor);
    home.setForeground(yellow);
    home.setFont(loginFont);
    home.setBorder(null);
    home.setFocusable(false);
    home.setCursor(new Cursor(Cursor.HAND_CURSOR));
    home.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

            welcTextLabel.setBounds(1065, 15, 500, 40);
            welcUserLabel.setBounds(1150, 15, 500, 40);
            dBoardPanel.setVisible(false);
            showPrevRec.setVisible(true);
            logoutBtn.setVisible(true);
            clickToStartTypingBtn.setVisible(true);
            normalTypingLabel.setVisible(true);
            yellowTypingLabel.setVisible(true);
            clickToStart.setVisible(true);
            compLabel.setVisible(true);
            startCompLabel.setVisible(true);
          }
        });
    resLabel.setText("Previous Results");
    resLabel.setBounds(20, 15, 500, 37);
    resLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
    resLabel.setForeground(white);

    dBoardPanel.setVisible(true);
    typingStart.setVisible(false);
    restartTyping.setVisible(false);
    showPrevRec.setVisible(false);
    pressLabel.setVisible(false);
    pressLabelYellow.setVisible(false);
    logoutBtn.setVisible(false);

  }

  public void createTable() {
    try {
      JTable table = new JTable();
      JTableHeader tableHeader = table.getTableHeader();
      JScrollPane scroll = new JScrollPane(table);
      dBoardPanel.add(scroll);
      scroll.setBounds(20, 100, 1150, 400);
      table.setFont(new Font("Roboto", Font.BOLD, 20));
      table.setForeground(headerColor);
      table.setRowHeight(table.getRowHeight() + 55);
      tableHeader.setBackground(headerColor);
      tableHeader.setPreferredSize(new Dimension(10000, 65));
      tableHeader.setFont(new Font("Roboto", Font.BOLD, 30));
      tableHeader.setForeground(yellow);
      table.setEnabled(false);
      refreshBtn.setVisible(true);
      refreshBtn.setText("Refresh");
      refreshBtn.setBounds(940, 15, 100, 37);
      refreshBtn.setVisible(true);
      refreshBtn.setBackground(yellow);
      refreshBtn.setFont(loginFont);
      refreshBtn.setBorder(null);
      refreshBtn.setFocusable(false);
      refreshBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      refreshBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // createTable();
          try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            String name = welcUserLabel.getText();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration",
                "root", "");
            Statement st = con.createStatement();
            String sql = "SELECT `Username`, `Speed (WPM)`, `Accuracy (%)`, `Time` FROM `results` where `Username` = '"
                + name
                + "' ORDER BY `Username` = '" + name + "', Time ASC";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            // DefaultTableModel model = (DefaultTableModel) table.getModel();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
              colName[i] = rsmd.getColumnName(i + 1);
              model.setColumnIdentifiers(colName);
              String username, speed, accuracy, time;
              while (rs.next()) {
                speed = rs.getString(2);
                accuracy = rs.getString(3);
                time = rs.getString(4);
                username = rs.getString(1);
                String[] row = { username, speed, accuracy, time };
                model.addRow(row);
              }
            }
          } catch (Exception e1) {
            e1.printStackTrace();

          }
        }
      });

      String name = welcUserLabel.getText();
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration",
          "root", "");
      Statement st = con.createStatement();
      String sql = "SELECT `Username`, `Speed (WPM)`, `Accuracy (%)`, `Time` FROM `results` where `Username` = '"
          + name
          + "' ORDER BY `Username` = '" + name + "', Time ASC";
      ResultSet rs = st.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      int cols = rsmd.getColumnCount();
      String[] colName = new String[cols];
      for (int i = 0; i < cols; i++) {
        colName[i] = rsmd.getColumnName(i + 1);
        model.setColumnIdentifiers(colName);
        String username, speed, accuracy, time;
        while (rs.next()) {
          speed = rs.getString(2);
          accuracy = rs.getString(3);
          time = rs.getString(4);
          username = rs.getString(1);
          String[] row = { username, speed, accuracy, time };
          model.addRow(row);
        }
      }
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public void logoutUser() {
    logoutBtn.setVisible(false);
    loginBtn.setVisible(true);
    signupBtn.setVisible(true);
    welcUserLabel.setVisible(false);
    welcTextLabel.setVisible(false);
    typingStart.setBounds(25, 705, 1435, 45);
    showPrevRec.setVisible(false);
    logout = true;
    login = false;
  }

  public void enterCompDetails() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/registration",
          "root",
          "");
      String sql = "INSERT INTO compdetails (player2)" + "VALUES (?)";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, playerTwoField.getText());
      if (playerTwoField == null) {
        JOptionPane.showMessageDialog(
            this,
            "Enter the Player 2 name",
            "Try again",
            JOptionPane.ERROR_MESSAGE);
      }

      ps.executeUpdate();
      ps.close();
      con.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public static String getPassage() {
    ArrayList<String> Passages = new ArrayList<String>();

    String pas1 = "list pity alarm motorcycle goal jurisdiction formula day father hour illness serious pitch incongruous myth conviction meaning bin clock pipe pledge mix complex remain fail coin complain gesture cousin tiptoe strain population warm rich map fill party balance agony halt inquiry turkey spell propertyarm way critical improve groan descent unacquainted harming prospects beautiful green waiting exceptional song happen exceptional fashion pretty strain mountains table front potato directions nearly one pants worse open";
    String pas2 = "minister beat banish extinct confession name leaf fantasy coin vain mastermind empire rob trustee package flourish instruction temperature quarrel start desk sensitivity hilarious cinema like inhibition sleep posture impound entertain courage remind dismiss surround sketch wheel wind choose suppresshobby calorie smoke elaborate pity disappoint volume diamond feast constituency accurate unmemorable heard west sound damn crocodiles chocolate tasty roaches coffin piercing found lemonade laugh realise season diesel with first much";
    String pas3 = "sulphur verdict agency metal endure grudge soil flag admire service dragon island oh explicit criticconversation contraction spend invite greet tune bedroom safari lid cooperative behavior aisle lot literacy dedicate maze hiccup ban opposed country distribute window crystal riot way opera silence change sale miracle conglomerate bread gap excavate litigation beach giraffe minutes acquired adventure layers teenage adventure heading tour socks favorite seven hiding stole crashed pilings argument penguin";
    String pas4 = "concert similar paralyzed neighborhood speed hypothesize word dramatic spit variety recovery benefitsick apathy pollution musical tolerant bend disappoint provoke duke offensive attic flatware vague theater graze move location polite build mutation election hell curtain image retiree finger berry hostage evening manual shine arch dozen tease economics celebration swarm common pudding guard wondered really grounds counting dessert kept space toothpicks across member listener sympathy enjoyed accidentally shower noticed playing horror basement walked practicing talked confused excelled";
    String pas5 = "hut fluctuation pupil bury back rotten determine wealth coffee orientation regard diet west perception affair safari camera implicit strap grind memorandum extinct cream ceremony stop curtain violation convulsion certain correction fix expose net sacrifice choke narrow acceptable stab hit oppositionshatter sensitive hostility trolley bring guideline hen dilemma sofa transport teacher change whether pickles mechanic eyeing stalking cupboard undersea probably priest emotional elegant nothing women worse small getting instead excited whip stream toasted visited balloons hopes bug love were";
    String pas6 = "jet fan deter building suffer bride corn absorb xray workshop onion director magnetic wine application definite restoration project enjoy drum decrease rape consultation recognize philosophy chalk rescue coat marathon banner response read desire mature dollar ruin formation bean entitlement prediction thinker style accountant vehicle rotation training clay science bike air sloth coating possible toenail mirror curious finding appeared blimp overnight gruff traveled discovered immediately that moment time corny most people great fearsome scooped sat working farm burger sandy with weasel";

    Passages.add(pas1);
    Passages.add(pas2);
    Passages.add(pas3);
    Passages.add(pas4);
    Passages.add(pas5);
    Passages.add(pas6);

    Random rand = new Random();
    int place = (rand.nextInt(6));
    String toReturn = Passages.get(place).substring(0, 400);

    if (toReturn.charAt(399) == 32) {
      toReturn = toReturn.strip(); // Remove all spaces before and after the substring we have taken
      toReturn = toReturn + '.'; // Adding the fullstop at last instead of space
    }
    return (toReturn);
  }

  public static void main(String[] args) {
    new Frame();
  }
}

class SoundClipRight extends JFrame {

  public SoundClipRight() {
    try {
      // Open an audio input stream.
      java.net.URL url = this.getClass().getClassLoader().getResource("sounds/right.wav");
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
      // Get a sound clip resource.
      Clip clip = AudioSystem.getClip();
      // Open audio clip and load samples from the audio input stream.
      clip.open(audioIn);
      clip.start();
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}

class SoundClipWrong extends JFrame {

  public SoundClipWrong() {
    try {
      // Open an audio input stream.
      java.net.URL url = this.getClass().getClassLoader().getResource("sounds/wrong.wav");
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
      // Get a sound clip resource.
      Clip clip = AudioSystem.getClip();
      // Open audio clip and load samples from the audio input stream.
      clip.open(audioIn);
      clip.start();
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}

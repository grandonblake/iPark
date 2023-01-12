import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class User{
    //states
    private String username, password, firstName, lastName, mobileNumber, type, carBrand, carModel, carPlateNumber;
    public static String forSortLabel = "Latest";
    private int balance;
    private static User currentlyLoggedIn;

    public static User getCurrentlyLoggedIn() {return currentlyLoggedIn;}

    //arrayList
    static List<User> UserUsernameArraylist = new ArrayList<>();
    static List<User> FinishedUserUsernameArraylist = new ArrayList<>();
    static List<User> SortedFinishedUserUsernameArraylist = new ArrayList<>();

    static Queue<User> userQueue = new LinkedList();

    //getters
    public String get(String field){
        if(field.equals("username")){return username;}
        if(field.equals("password")){return password;}
        if(field.equals("firstName")){return firstName;}
        if(field.equals("lastName")){return lastName;}
        if(field.equals("mobileNumber")){return mobileNumber;}
        if(field.equals("type")){return type;}
        if(field.equals("carBrand")){return carBrand;}
        if(field.equals("carModel")){return carModel;}
        if(field.equals("carPlateNumber")){return carPlateNumber;}
        else{return "Error in Get Method";}
    }
    public int getBalance(){return balance;}

    //setter
    public void set(String field, String set){ //setter
        if(field.equals("username")){username = set;}
        if(field.equals("password")){password = set;}
        if(field.equals("firstName")){firstName = set;}
        if(field.equals("lastName")){lastName = set;}
        if(field.equals("mobileNumber")){mobileNumber = set;}
        if(field.equals("carBrand")){carBrand = set;}
        if(field.equals("carModel")){carModel = set;}
        if(field.equals("carPlateNumber")){carPlateNumber = set;}
    }
    public void addBalance(int amount) {balance += amount;}
    public void subtractBalance(int amount){balance -= amount;}

    //User constructor
    public User(String username, String password, String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // =========================================================================================================
    // ------------------------------------------------ METHODS ------------------------------------------------
    // =========================================================================================================

    //method for registration
    public static boolean UserRegister(String UserUsernameRegister, String UserPasswordRegister){
        if(UserUsernameArraylist.size() == 0){
            User User = new User(UserUsernameRegister, UserPasswordRegister, "customer");
            UserUsernameArraylist.add(User);
            return true;
        }
        else {
            boolean noSimilarAccount = true;
            for(int i = 0; i < UserUsernameArraylist.size(); i++){
                if (UserUsernameArraylist.get(i).get("username").equals(UserUsernameRegister)){
                    noSimilarAccount = false;
                }
            }
            while(noSimilarAccount){
                User User = new User(UserUsernameRegister, UserPasswordRegister, "customer");
                UserUsernameArraylist.add(User);
                noSimilarAccount = false;
                return true;
            }
        }
        return false;
    }

    //method for log-in
    public static int UserLogin(String UserUsernameLogin, String UserPasswordLogin){
        if (UserUsernameArraylist.size() != 0) {
            for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                if (UserUsernameArraylist.get(i).get("username").equals(UserUsernameLogin) && UserUsernameArraylist.get(i).get("password").equals(UserPasswordLogin)) {
                    currentlyLoggedIn = UserUsernameArraylist.get(i);

                    if(currentlyLoggedIn.type.equals("customer")){
                        return 1;
                    }
                    else if(currentlyLoggedIn.type.equals("admin")){
                        return 2;
                    }
                    else {
                        return 3;
                    }
                }
            }
        }
        return 3;
    }

    // =========================================================================================================
    // ------------------------------------------------ SCREENS ------------------------------------------------
    // =========================================================================================================

    public static class LoginScreen {
        //Declaration of Components
        //JFrame
        static JFrame loginFrame;

        //JLabels
        JLabel logoLabel, userNameUnderScore, passWordUnderScore, loginLabel, signUpLabel, forgotPass, questionMarkLabel;

        //JPanels
        JPanel logoPanel, usernamePanel, passwordPanel, buttonPanel, loginButton, signUpButton, questionMarkPanel;

        //JTextFields
        JTextField userName;
        //JPasswordField;
        JPasswordField passWord;

        public LoginScreen() {
            //Declaration of GUIs
            //JFrames
            loginFrame = new JFrame();

            //JPanels
            logoPanel = new JPanel();
            usernamePanel = new JPanel();
            passwordPanel = new JPanel();
            buttonPanel = new JPanel();
            questionMarkPanel = new JPanel();
            loginButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            signUpButton = new JPanel();

            //ImageIcons
            ImageIcon logo = new ImageIcon(getClass().getResource("images\\projectLogo.png"));
            Image image = logo.getImage(); // transform it
            Image newLogo = image.getScaledInstance(150, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            logo = new ImageIcon(newLogo);  // transform it back

            ImageIcon questionMark = new ImageIcon(getClass().getResource("images\\questionMark.png"));
            Image image2 = questionMark.getImage(); // transform it
            Image newQuestionMark = image2.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            questionMark = new ImageIcon(newQuestionMark);  // transform it back



            //COMPONENT SETTINGS
            //FRAME
            loginFrame.setTitle("Login");
            loginFrame.setSize(448, 796);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.getContentPane().setBackground(Color.decode("#222222"));

            //TEXTFIELDS
            userName = new JTextField("Username");
            userName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            userName.setBackground(Color.decode("#222222"));
            userName.setForeground(Color.decode("#ebebeb"));
            userName.setBorder(BorderFactory.createEmptyBorder());
            userName.setBounds(35, 3, 358, 20);
            userName.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (userName.getText().equals("Username")) {
                        userName.setText("");
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (userName.getText().isEmpty()) {
                        userName.setText("Username");
                    }
                }
            });

            //JPasswordField
            passWord = new JPasswordField("Password");
            passWord.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            passWord.setBackground(Color.decode("#222222"));
            passWord.setForeground(Color.decode("#ebebeb"));
            passWord.setBorder(BorderFactory.createEmptyBorder());
            passWord.setBounds(35, 3, 358, 20);
            passWord.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (passWord.getText().equals("Password")) {
                        passWord.setText("");
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (passWord.getText().isEmpty()) {
                        passWord.setText("Password");
                    }
                }
            });

            //LABELS
            logoLabel = new JLabel("iPark");
            logoLabel.setIcon(logo);
            logoLabel.setBounds(138, 25, 300, 300);
            logoLabel.setHorizontalTextPosition(JLabel.CENTER);
            logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
            logoLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            logoLabel.setForeground(Color.decode("#ebebeb"));
            logoLabel.setIconTextGap(15);

            userNameUnderScore = new JLabel("___________________________________________________");
            userNameUnderScore.setForeground(Color.decode("#ebebeb"));
            userNameUnderScore.setBounds(35, 6, 365, 33);

            passWordUnderScore = new JLabel("___________________________________________________");
            passWordUnderScore.setForeground(Color.decode("#ebebeb"));
            passWordUnderScore.setBounds(35, 6, 365, 33);

            loginLabel = new JLabel("Login");
            loginLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            loginLabel.setForeground(Color.decode("#ebebeb"));
            loginLabel.setBounds(90, 10, 50, 25);

            signUpLabel = new JLabel("Sign Up for iPark");
            signUpLabel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
            signUpLabel.setForeground(Color.decode("#ebebeb"));
            signUpLabel.setBounds(23, 10, 150, 25);

            forgotPass = new JLabel("Forgot Password?");
            forgotPass.setFont(new Font("Century Gothic", Font.PLAIN, 13));
            forgotPass.setForeground(Color.decode("#ebebeb"));
            forgotPass.setBounds(156, 200, 150, 25);

            questionMarkLabel = new JLabel();
            questionMarkLabel.setIcon(questionMark);
            questionMarkLabel.setBounds(0, 0, 70, 70);

            //PANELS
            logoPanel.setBackground(Color.decode("#222222"));
            logoPanel.setBounds(0, 0, 448, 300);
            logoPanel.setLayout(null);

            usernamePanel.setBounds(0, 350, 448, 40);
            usernamePanel.setBackground(Color.decode("#222222"));
            usernamePanel.setLayout(null);

            passwordPanel.setBounds(0, 400, 448, 40);
            passwordPanel.setBackground(Color.decode("#222222"));
            passwordPanel.setLayout(null);

            buttonPanel.setBounds(0, 475, 448, 350);
            buttonPanel.setBackground(Color.decode("#222222"));
            buttonPanel.setLayout(null);

            loginButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            loginButton.setBounds(98, 10, 230, 50);
            loginButton.setFocusable(false);
            loginButton.setBackground(Color.decode("#f1b31c"));
            loginButton.setForeground(Color.decode("#222222"));
            loginButton.setOpaque(false);
            loginButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(UserLogin(userName.getText(), new String(passWord.getPassword())) == 1){
                        new HomeScreen();
                        loginFrame.dispose();
                    }
                    else if(UserLogin(userName.getText(), new String(passWord.getPassword())) == 2){
                        new AdminHomeScreen();
                        loginFrame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(loginFrame,"Invalid account!", "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            loginButton.setLayout(null);

            signUpButton.setBounds(138, 148, 150, 50);
            signUpButton.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            signUpButton.setBackground(Color.decode("#222222"));
            signUpButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new RegisterScreen();
                    loginFrame.dispose();
                }
            });
            signUpButton.setLayout(null);

            questionMarkPanel.setBackground(Color.decode("#222222"));
            questionMarkPanel.setBounds(310, 180, 28, 80);
            questionMarkPanel.setLayout(null);

            logoPanel.add(logoLabel);

            usernamePanel.add(userName);
            usernamePanel.add(userNameUnderScore);

            passwordPanel.add(passWord);
            passwordPanel.add(passWordUnderScore);

            buttonPanel.add(loginButton);
            buttonPanel.add(signUpButton);
            buttonPanel.add(forgotPass);
            buttonPanel.add(questionMarkPanel);

            loginButton.add(loginLabel);

            signUpButton.add(signUpLabel);

            questionMarkPanel.add(questionMarkLabel);

            loginFrame.add(logoPanel);
            loginFrame.add(usernamePanel);
            loginFrame.add(passwordPanel);
            loginFrame.add(buttonPanel);

            loginFrame.setLocationRelativeTo(null);
            loginFrame.setLayout(null);
            loginFrame.setResizable(false);
            loginFrame.setVisible(true);
        }
    }

    public static class RegisterScreen {
        //Declaration of Components
        //JFrame
        static JFrame signUpFrame;

        //JLabels
        static JLabel backButton;
        static JLabel signUpLabel;
        static JLabel logoLabel;
        static JLabel usernameLabel;
        static JLabel passwordLabel;
        JLabel usernameIcon;
        JLabel passwordIcon;
        static JLabel usernameUnderScore;
        static JLabel passwordUnderScore;
        static JLabel registerLabel;

        //JPanels
        static JPanel signUpTab;
        static JPanel backButtonPanel;
        static JPanel logoPanel;
        static JPanel usernamePanel;
        static JPanel passwordPanel;
        JPanel registerButton;

        //ImageIcon
        static ImageIcon backArrow;
        static ImageIcon logo;
        static ImageIcon personLogo;
        static ImageIcon keyLogo;

        //JTextField
        JTextField username;
        JPasswordField password;

        public RegisterScreen() {
            //Declaration of GUIs
            //JFrames
            signUpFrame = new JFrame();

            //JLabels
            backButton = new JLabel();
            signUpLabel = new JLabel();
            logoLabel = new JLabel();
            usernameLabel = new JLabel();
            passwordLabel = new JLabel();
            usernameIcon = new JLabel();
            passwordIcon = new JLabel();
            usernameUnderScore = new JLabel();
            passwordUnderScore = new JLabel();
            registerLabel = new JLabel();

            //JPanels
            signUpTab = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(130,210); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            backButtonPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            registerButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            logoPanel = new JPanel();
            usernamePanel = new JPanel();
            passwordPanel = new JPanel();

            //ImageIcon
            ImageIcon backArrow = new ImageIcon(getClass().getResource("images\\back_arrow.png"));
            Image image = backArrow.getImage(); // transform it
            Image newBackArrow = image.getScaledInstance(20, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            backArrow = new ImageIcon(newBackArrow);  // transform it back

            ImageIcon logo = new ImageIcon(getClass().getResource("images\\projectLogo.png"));
            Image image2 = logo.getImage(); // transform it
            Image newLogo = image2.getScaledInstance(150, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            logo = new ImageIcon(newLogo);  // transform it back

            ImageIcon personLogo = new ImageIcon(getClass().getResource("images\\username_icon.png"));
            Image image3 = personLogo.getImage(); // transform it
            Image newPersonLogo = image3.getScaledInstance(17, 17,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            personLogo = new ImageIcon(newPersonLogo);  // transform it back

            ImageIcon keyLogo = new ImageIcon(getClass().getResource("images\\key_icon.png"));
            Image image4 = keyLogo.getImage(); // transform it
            Image newKeyLogo = image4.getScaledInstance(19, 12,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            keyLogo = new ImageIcon(newKeyLogo);  // transform it back

            //COMPONENT SETTINGS
            //FRAME
            signUpFrame.setTitle("Sign Up");
            signUpFrame.setSize(448, 796);
            signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signUpFrame.getContentPane().setBackground(Color.decode("#f1b31c"));

            //TEXTFIELDS
            username = new JTextField();
            username.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            username.setBackground(Color.decode("#f1b31c"));
            username.setForeground(Color.decode("#ebebeb"));
            username.setBorder(BorderFactory.createEmptyBorder());
            username.setBounds(50, 20, 340, 30);
            username.setEditable(true);

            password = new JPasswordField();
            password.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            password.setBackground(Color.decode("#f1b31c"));
            password.setForeground(Color.decode("#ebebeb"));
            password.setBorder(BorderFactory.createEmptyBorder());
            password.setBounds(50, 20, 340, 30);
            password.setEditable(true);


            //LABELS
            backButton.setIcon(backArrow);
            backButton.setBounds(14, 2, 45, 45);

            signUpLabel.setText("Sign Up");
            signUpLabel.setFont(new Font("Century Gothic", Font.BOLD, 22));
            signUpLabel.setBounds(190, 20, 100, 100);
            signUpLabel.setForeground(Color.decode("#ebebeb"));

            logoLabel.setIcon(logo);
            logoLabel.setBounds(138, 25, 300, 300);
            logoLabel.setText("iPark");
            logoLabel.setHorizontalTextPosition(JLabel.CENTER);
            logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
            logoLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            logoLabel.setForeground(Color.decode("#ebebeb"));
            logoLabel.setIconTextGap(15);

            usernameLabel.setText("Username");
            usernameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            usernameLabel.setForeground(Color.decode("#ebebeb"));
            usernameLabel.setBounds(25, 0, 100, 20);

            passwordLabel.setText("Password");
            passwordLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            passwordLabel.setForeground(Color.decode("#ebebeb"));
            passwordLabel.setBounds(25, 0, 100, 20);

            usernameIcon.setIcon(personLogo);
            usernameIcon.setBounds(25, 15, 50, 40);

            passwordIcon.setIcon(keyLogo);
            passwordIcon.setBounds(25, 15, 50, 40);

            usernameUnderScore.setText("______________________________________________");
            usernameUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            usernameUnderScore.setBounds(24, 25, 448, 40);
            usernameUnderScore.setForeground(Color.decode("#ebebeb"));

            passwordUnderScore.setText("______________________________________________");
            passwordUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            passwordUnderScore.setBounds(24, 25, 448, 40);
            passwordUnderScore.setForeground(Color.decode("#ebebeb"));

            registerLabel.setText("Register");
            registerLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            registerLabel.setBounds(139, 13, 100, 20);
            registerLabel.setForeground(Color.decode("#222222"));

            //PANELS
            signUpTab.setBounds(-13, -28, 458, 125);
            signUpTab.setFocusable(false);
            signUpTab.setBackground(Color.decode("#222222"));
            signUpTab.setForeground(Color.decode("#222222"));
            signUpTab.setOpaque(false);
            signUpTab.setLayout(null);

            backButtonPanel.setBounds(40, 45, 50, 50);
            backButtonPanel.setBackground(Color.decode("#222222"));
            backButtonPanel.setForeground(Color.decode("#ebebeb"));
            backButtonPanel.setLayout(null);
            backButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new LoginScreen();
                    signUpFrame.dispose();
                }
            });

            logoPanel.setBackground(Color.decode("#f1b31c"));
            logoPanel.setBounds(0, 80, 448, 290);
            logoPanel.setLayout(null);

            usernamePanel.setBounds(0, 420, 448, 60);
            usernamePanel.setBackground(Color.decode("#f1b31c"));
            usernamePanel.setLayout(null);

            passwordPanel.setBounds(0, 500, 448, 60);
            passwordPanel.setBackground(Color.decode("#f1b31c"));
            passwordPanel.setLayout(null);

            registerButton.setBounds(42, 575, 335, 50);
            registerButton.setBackground(Color.decode("#ebebeb"));
            registerButton.setForeground(Color.decode("#ebebeb"));
            registerButton.setFocusable(false);
            registerButton.setOpaque(false);
            registerButton.setLayout(null);
            registerButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!username.getText().isEmpty() && !new String(password.getPassword()).isEmpty()){
                        if(UserRegister(username.getText(), new String(password.getPassword()))){
                            new RegisterUserDetailsScreen();
                            signUpFrame.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(signUpFrame,"Account already exists.", "Error",  JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(signUpFrame,"Complete all fields.", "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            logoPanel.add(logoLabel);

            signUpTab.add(backButtonPanel);
            signUpTab.add(signUpLabel);

            backButtonPanel.add(backButton);

            usernamePanel.add(usernameLabel);
            usernamePanel.add(usernameIcon);
            usernamePanel.add(usernameUnderScore);
            usernamePanel.add(username);

            passwordPanel.add(passwordLabel);
            passwordPanel.add(passwordIcon);
            passwordPanel.add(passwordUnderScore);
            passwordPanel.add(password);

            registerButton.add(registerLabel);

            signUpFrame.add(signUpTab);
            signUpFrame.add(logoPanel);
            signUpFrame.add(usernamePanel);
            signUpFrame.add(passwordPanel);
            signUpFrame.add(registerButton);

            signUpFrame.setLocationRelativeTo(null);
            signUpFrame.setLayout(null);
            signUpFrame.setResizable(false);
            signUpFrame.setVisible(true);
        }
    }

    public static class RegisterUserDetailsScreen{
        //Declaration of Components
        //JFrame
        static JFrame accDetailFrame;

        //JLabels
        JLabel accDetailLabel, backButton, firstNameLabel, surnameLabel, mobileNumLabel, carBrandLabel, carModelLabel, carPlateNumLabel,
                firstNameUnderScore, surnameUnderScore, mobileNumUnderScore, carBrandUnderScore, carModelUnderScore, carPlateNumUnderScore, registerLabel;

        //JPanels
        JPanel accDetailsTab, backButtonPanel, firstNamePanel, surnamePanel, mobileNumPanel, carBrandPanel, carModelPanel, carPlateNumPanel,
                registerButton;

        //JTextFields
        JTextField firstName, surname, mobileNum, carBrand, carModel, carPlateNum;

        public RegisterUserDetailsScreen() {
            //Declaration of GUIs
            //JFrame
            accDetailFrame = new JFrame();

            //JLabels
            accDetailLabel = new JLabel("Account Details");
            backButton = new JLabel();
            firstNameLabel = new JLabel("First Name");
            surnameLabel = new JLabel("Surname");
            mobileNumLabel = new JLabel("Mobile Number");
            carBrandLabel = new JLabel("Car Brand");
            carModelLabel = new JLabel("Car Model");
            carPlateNumLabel = new JLabel("Plate Number");
            firstNameUnderScore = new JLabel("_______________________________________________");
            surnameUnderScore = new JLabel("_______________________________________________");
            mobileNumUnderScore = new JLabel("_______________________________________________");
            carBrandUnderScore = new JLabel("_______________________________________________");
            carModelUnderScore = new JLabel("_______________________________________________");
            carPlateNumUnderScore = new JLabel("_______________________________________________");
            registerLabel = new JLabel("Finish");

            //JPanels
            accDetailsTab = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(130,210); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            backButtonPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };

            firstNamePanel = new JPanel();
            surnamePanel = new JPanel();
            mobileNumPanel = new JPanel();
            carBrandPanel = new JPanel();
            carModelPanel = new JPanel();
            carPlateNumPanel = new JPanel();
            registerButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };

            //JTextFields
            firstName = new JTextField();
            surname = new JTextField();
            mobileNum = new JTextField();
            carBrand = new JTextField();
            carModel = new JTextField();
            carPlateNum = new JTextField();

            // --------------------------- COMPONENT SETTINGS ---------------------------
            //FRAME
            accDetailFrame.setTitle("Account Details");
            accDetailFrame.setSize(448, 796);
            accDetailFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            accDetailFrame.getContentPane().setBackground(Color.decode("#ffffff"));

            //LABELS
            accDetailLabel.setFont(new Font("Century Gothic", Font.BOLD, 21));
            accDetailLabel.setBounds(150, 20, 200, 100);
            accDetailLabel.setForeground(Color.decode("#fafafa"));

            firstNameLabel.setBounds(25, 0, 100, 40);
            firstNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            firstNameLabel.setForeground(Color.decode("#b2b2b2"));

            surnameLabel.setBounds(25, 0, 100, 40);
            surnameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            surnameLabel.setForeground(Color.decode("#b2b2b2"));

            mobileNumLabel.setBounds(25, 0, 120, 40);
            mobileNumLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            mobileNumLabel.setForeground(Color.decode("#b2b2b2"));

            carBrandLabel.setBounds(25, 0, 100, 40);
            carBrandLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carBrandLabel.setForeground(Color.decode("#b2b2b2"));

            carModelLabel.setBounds(25, 0, 100, 40);
            carModelLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carModelLabel.setForeground(Color.decode("#b2b2b2"));

            carPlateNumLabel.setBounds(25, 0, 100, 40);
            carPlateNumLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carPlateNumLabel.setForeground(Color.decode("#b2b2b2"));

            firstNameUnderScore.setBounds(25, 30, 350, 40);
            firstNameUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            firstNameUnderScore.setForeground(Color.decode("#b2b2b2"));

            surnameUnderScore.setBounds(25, 30, 350, 40);
            surnameUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            surnameUnderScore.setForeground(Color.decode("#b2b2b2"));

            mobileNumUnderScore.setBounds(25, 30, 350, 40);
            mobileNumUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            mobileNumUnderScore.setForeground(Color.decode("#b2b2b2"));

            carBrandUnderScore.setBounds(25, 30, 350, 40);
            carBrandUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carBrandUnderScore.setForeground(Color.decode("#b2b2b2"));

            carModelUnderScore.setBounds(25, 30, 350, 40);
            carModelUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carModelUnderScore.setForeground(Color.decode("#b2b2b2"));

            carPlateNumUnderScore.setBounds(25, 30, 350, 40);
            carPlateNumUnderScore.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carPlateNumUnderScore.setForeground(Color.decode("#b2b2b2"));

            registerLabel.setBounds(130, 0, 100, 50);
            registerLabel.setFont(new Font("Century Gotchic", Font.BOLD, 18));
            registerLabel.setForeground(Color.decode("#222222"));

            //TEXTFIELDS
            firstName.setBounds(25, 30, 330, 25);
            firstName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            firstName.setBorder(BorderFactory.createEmptyBorder());
            firstName.setForeground(Color.decode("#000000"));
            firstName.setEditable(true);

            surname.setBounds(25, 30, 330, 25);
            surname.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            surname.setBorder(BorderFactory.createEmptyBorder());
            surname.setForeground(Color.decode("#000000"));
            surname.setEditable(true);

            mobileNum.setBounds(25, 30, 330, 25);
            mobileNum.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            mobileNum.setBorder(BorderFactory.createEmptyBorder());
            mobileNum.setForeground(Color.decode("#000000"));
            mobileNum.setEditable(true);

            carBrand.setBounds(25, 30, 330, 25);
            carBrand.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carBrand.setBorder(BorderFactory.createEmptyBorder());
            carBrand.setForeground(Color.decode("#000000"));
            carBrand.setEditable(true);

            carModel.setBounds(25, 30, 330, 25);
            carModel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carModel.setBorder(BorderFactory.createEmptyBorder());
            carModel.setForeground(Color.decode("#000000"));
            carModel.setEditable(true);

            carPlateNum.setBounds(25, 30, 330, 25);
            carPlateNum.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            carPlateNum.setBorder(BorderFactory.createEmptyBorder());
            carPlateNum.setForeground(Color.decode("#000000"));
            carPlateNum.setEditable(true);

            //PANELS
            accDetailsTab.setBounds(-13, -28, 458, 125);
            accDetailsTab.setFocusable(false);
            accDetailsTab.setBackground(Color.decode("#222222"));
            accDetailsTab.setForeground(Color.decode("#222222"));
            accDetailsTab.setOpaque(false);
            accDetailsTab.setLayout(null);

            firstNamePanel.setBounds(20, 130, 365, 65);
            firstNamePanel.setBackground(Color.decode("#ffffff"));
            firstNamePanel.setForeground(Color.decode("#ebebeb"));
            firstNamePanel.setLayout(null);

            surnamePanel.setBounds(20, 220, 365, 65);
            surnamePanel.setBackground(Color.decode("#ffffff"));
            surnamePanel.setForeground(Color.decode("#ebebeb"));
            surnamePanel.setLayout(null);

            mobileNumPanel.setBounds(20, 310, 365, 65);
            mobileNumPanel.setBackground(Color.decode("#ffffff"));
            mobileNumPanel.setForeground(Color.decode("#ebebeb"));
            mobileNumPanel.setLayout(null);

            carBrandPanel.setBounds(20, 400, 365, 65);
            carBrandPanel.setBackground(Color.decode("#ffffff"));
            carBrandPanel.setForeground(Color.decode("#ebebeb"));
            carBrandPanel.setLayout(null);

            carModelPanel.setBounds(20, 495, 365, 65);
            carModelPanel.setBackground(Color.decode("#ffffff"));
            carModelPanel.setForeground(Color.decode("#ebebeb"));
            carModelPanel.setLayout(null);

            carPlateNumPanel.setBounds(20, 590, 365, 65);
            carPlateNumPanel.setBackground(Color.decode("#ffffff"));
            carPlateNumPanel.setForeground(Color.decode("#ebebeb"));
            carPlateNumPanel.setLayout(null);

            registerButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
            registerButton.setBounds(40, 670, 335, 50);
            registerButton.setFocusable(false);
            registerButton.setBackground(Color.decode("#f1b31c"));
            registerButton.setForeground(Color.decode("#f1b31c"));
            registerButton.setOpaque(false);
            registerButton.setLayout(null);
            registerButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if(firstName.getText().isEmpty() || surname.getText().isEmpty() || mobileNum.getText().isEmpty() || carBrand.getText().isEmpty() || carModel.getText().isEmpty() || carPlateNum.getText().isEmpty()){
                        JOptionPane.showMessageDialog(accDetailFrame, "Complete all input fields", "Input Details", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        UserUsernameArraylist.get(UserUsernameArraylist.size() - 1).set("firstName", firstName.getText().trim());
                        UserUsernameArraylist.get(UserUsernameArraylist.size() - 1).set("lastName", surname.getText().trim());
                        UserUsernameArraylist.get(UserUsernameArraylist.size() - 1).set("mobileNumber", mobileNum.getText().trim());
                        UserUsernameArraylist.get(UserUsernameArraylist.size() - 1).set("carBrand", carBrand.getText().trim());
                        UserUsernameArraylist.get(UserUsernameArraylist.size() - 1).set("carModel", carModel.getText().trim());
                        UserUsernameArraylist.get(UserUsernameArraylist.size() - 1).set("carPlateNumber", carPlateNum.getText().trim());

                        new LoginScreen();
                        accDetailFrame.dispose();
                    }
                }
            });

            accDetailsTab.add(accDetailLabel);
            accDetailsTab.add(backButtonPanel);

            firstNamePanel.add(firstNameLabel);
            firstNamePanel.add(firstNameUnderScore);
            firstNamePanel.add(firstName);

            surnamePanel.add(surnameLabel);
            surnamePanel.add(surnameUnderScore);
            surnamePanel.add(surname);

            mobileNumPanel.add(mobileNumLabel);
            mobileNumPanel.add(mobileNumUnderScore);
            mobileNumPanel.add(mobileNum);

            carBrandPanel.add(carBrandLabel);
            carBrandPanel.add(carBrandUnderScore);
            carBrandPanel.add(carBrand);

            carModelPanel.add(carModelLabel);
            carModelPanel.add(carModelUnderScore);
            carModelPanel.add(carModel);

            carPlateNumPanel.add(carPlateNumLabel);
            carPlateNumPanel.add(carPlateNumUnderScore);
            carPlateNumPanel.add(carPlateNum);

            registerButton.add(registerLabel);

            accDetailFrame.add(accDetailsTab);
            accDetailFrame.add(firstNamePanel);
            accDetailFrame.add(surnamePanel);
            accDetailFrame.add(mobileNumPanel);
            accDetailFrame.add(carBrandPanel);
            accDetailFrame.add(carModelPanel);
            accDetailFrame.add(carPlateNumPanel);
            accDetailFrame.add(registerButton);

            accDetailFrame.setLocationRelativeTo(null);
            accDetailFrame.setLayout(null);
            accDetailFrame.setResizable(false);
            accDetailFrame.setVisible(true);
        }
    }

    public static class HomeScreen extends ParkingSlot{
        //Declaration of Components
        //JFrame
        static JFrame homeScreenFrame;

        //JLabels
        static JLabel logoutLabel, userIconLabel, welcomeLabel, welcomeLabel2, statusLabel1, underlineLabel,
                queueLabel, paymentLabel, currentUsername, currentUsername2;

        //JPanels
        static JPanel logoutButton, userIconPanel, currUserPanel, welcomePanel, statusPanel, queueButton, paymentButton;

        //JTextField
        JTextField status, slotsAvailable, status2, status3;

        //ImageIcon
        static ImageIcon logout, userIcon;

        public HomeScreen() {
            //Declaration of GUIs
            //JFrame
            homeScreenFrame = new JFrame();

            //JLabels
            logoutLabel = new JLabel();
            userIconLabel = new JLabel();
            welcomeLabel = new JLabel("Welcome to");
            welcomeLabel2 = new JLabel("iPark,");
            statusLabel1 = new JLabel("Status");
            underlineLabel = new JLabel("____________________________________");
            queueLabel = new JLabel("Queue");
            paymentLabel = new JLabel("Payment");

            //JPanels
            logoutButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            userIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            statusPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            queueButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            paymentButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            currUserPanel = new JPanel();
            welcomePanel = new JPanel();

            currentUsername = new JLabel(currentlyLoggedIn.get("firstName") + " " + currentlyLoggedIn.get("lastName"));
            currentUsername2 = new JLabel(currentlyLoggedIn.get("firstName") + " " + currentlyLoggedIn.get("lastName"));

            int x = 0;
            for (int i = 0; i < getParkingSlotArray().length; i++) {
                if(getParkingSlotArray()[i].getIsAvailable()){ //if a parkingSlot is available
                    x++;

                }
            }

            status = new JTextField("Queue in line with us now...");

            slotsAvailable = new JTextField("SLOTS AVAILABLE: " + x);
            status2 = new JTextField("");
            status3 = new JTextField("FLR 1 | BLDG 1");

            //ImageIcons
            ImageIcon logout  = new ImageIcon(getClass().getResource("images\\logout.png"));
            Image image = logout.getImage(); // transform it
            Image newLogout = image.getScaledInstance(25, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            logout = new ImageIcon(newLogout);  // transform it back

            ImageIcon userIcon  = new ImageIcon(getClass().getResource("images\\userIcon.png"));
            Image image2 = userIcon.getImage(); // transform it
            Image newUserIcon = image2.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            userIcon = new ImageIcon(newUserIcon);  // transform it back

            // ----------------------------- COMPONENT SETTINGS -------------------------
            //FRAME
            homeScreenFrame.setTitle("Home Screen");
            homeScreenFrame.setSize(448, 796);
            homeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeScreenFrame.getContentPane().setBackground(Color.decode("#fafafa"));

            //LABELS
            logoutLabel.setIcon(logout);
            logoutLabel.setBounds(17, 7, 45, 45);

            userIconLabel.setIcon(userIcon);
            userIconLabel.setBounds(0, 0, 60, 60);

            welcomeLabel.setBounds(0, 0, 200, 50);
            welcomeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 29));
            welcomeLabel.setForeground(Color.decode("#000000"));

            welcomeLabel2.setBounds(0, 40, 200, 50);
            welcomeLabel2.setFont(new Font("Century Gothic", Font.PLAIN, 29));
            welcomeLabel2.setForeground(Color.decode("#000000"));

            statusLabel1.setBounds(25, 10, 100, 50);
            statusLabel1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            statusLabel1.setForeground(Color.decode("#f6f6f6"));

            underlineLabel.setBounds(25, 20, 350, 50);
            underlineLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            underlineLabel.setForeground(Color.decode("#6c6c6c"));

            queueLabel.setBounds(160, 4, 100, 50);
            queueLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
            queueLabel.setForeground(Color.decode("#000000"));

            paymentLabel.setBounds(155, 4, 100, 50);
            paymentLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
            paymentLabel.setForeground(Color.decode("#000000"));

            //PANELS
            logoutButton.setBounds(30, 25, 60, 60);
            logoutButton.setBackground(Color.decode("#fafafa"));
            logoutButton.setForeground(Color.decode("#b3b3b3"));
            logoutButton.setLayout(null);
            logoutButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new LoginScreen();
                    homeScreenFrame.dispose();
                }
            });

            userIconPanel.setBounds(350, 25, 60, 60);
            userIconPanel.setBackground(Color.decode("#fafafa"));
            userIconPanel.setForeground(Color.decode("#fafafa"));
            userIconPanel.setLayout(null);

            currUserPanel.setBounds(150, 40, 200, 30);
            currUserPanel.setBackground(Color.decode("#fafafa"));
            currUserPanel.setLayout(new BorderLayout());

            welcomePanel.setBounds(30, 135, 380, 100);
            welcomePanel.setBackground(Color.decode("#fafafa"));
            welcomePanel.setForeground(Color.decode("#fafafa"));
            welcomePanel.setLayout(null);

            statusPanel.setBounds(25, 240, 380, 250);
            statusPanel.setBackground(Color.decode("#333333"));
            statusPanel.setForeground(Color.decode("#333333"));
            statusPanel.setLayout(null);
            statusPanel.setFocusable(false);
            statusPanel.setOpaque(false);
            statusPanel.setLayout(null);

            queueButton.setBounds(25, 510, 380, 60);
            queueButton.setBackground(Color.decode("#fafafa"));
            queueButton.setForeground(Color.decode("#6c6c6c"));
            queueButton.setLayout(null);
            queueButton.setFocusable(false);
            queueButton.setOpaque(false);
            queueButton.addMouseListener(new MouseAdapter( ) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(park(currentlyLoggedIn)){ //if successfully parked
                        changeScreenToParked();
                    }
                    else{ //if not successfully parked / there are no slots
                        if(!userQueue.contains(currentlyLoggedIn)){
                            userQueue.add(currentlyLoggedIn);
                            changeScreenToQueued();
                        }
                        else{
                            JOptionPane.showMessageDialog(homeScreenFrame,"You are already in queue.", "Error",  JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            paymentButton.setBounds(25, 590, 380, 60);
            paymentButton.setBackground(Color.decode("#fafafa"));
            paymentButton.setForeground(Color.decode("#6c6c6c"));
            paymentButton.setLayout(null);
            paymentButton.setFocusable(false);
            paymentButton.setOpaque(false);
            paymentButton.setVisible(false);
            paymentButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new PaymentScreen();
                    homeScreenFrame.dispose();
                }
            });

            //TEXTFIELD
            currentUsername.setBounds(0, -10, 200, 50);
            currentUsername.setFont(new Font("Century Gothic", Font.BOLD, 16));
            currentUsername.setBorder(BorderFactory.createEmptyBorder());
            currentUsername.setBackground(Color.decode("#fafafa"));
            currentUsername.setForeground(Color.decode("#030303"));

            currentUsername2.setBounds(90, 40, 300, 50);
            currentUsername2.setFont(new Font("Century Gothic", Font.BOLD, 29));
            currentUsername2.setBorder(BorderFactory.createEmptyBorder());
            currentUsername2.setBackground(Color.decode("#fafafa"));
            currentUsername2.setForeground(Color.decode("#030303"));

            status.setBounds(25, 50, 350, 50);
            status.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            status.setForeground(Color.decode("#e4aa1e"));
            status.setBorder(BorderFactory.createEmptyBorder());
            status.setBackground(Color.decode("#333333"));
            status.setEditable(false);

            status2.setVisible(false);
            status2.setBounds(25, 90, 200, 70);
            status2.setFont(new Font("Century Gothic", Font.BOLD, 52));
            status2.setForeground(Color.decode("#e4aa1e"));
            status2.setBorder(BorderFactory.createEmptyBorder());
            status2.setBackground(Color.decode("#333333"));
            status2.setEditable(false);

            status3.setVisible(false);
            status3.setBounds(25, 150, 200, 50);
            status3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            status3.setForeground(Color.decode("#e4aa1e"));
            status3.setBorder(BorderFactory.createEmptyBorder());
            status3.setBackground(Color.decode("#333333"));
            status3.setEditable(false);

            slotsAvailable.setBounds(250, 200, 120, 50);
            slotsAvailable.setFont(new Font("Century Gothic", Font.PLAIN, 11));
            slotsAvailable.setForeground(Color.decode("#6c6c6c"));
            slotsAvailable.setBorder(BorderFactory.createEmptyBorder());
            slotsAvailable.setBackground(Color.decode("#333333"));
            slotsAvailable.setEditable(false);
            slotsAvailable.setOpaque(false);
            slotsAvailable.setFocusable(false);

            logoutButton.add(logoutLabel);

            userIconPanel.add(userIconLabel);

            currUserPanel.add(currentUsername, BorderLayout.LINE_END);

            welcomePanel.add(welcomeLabel);
            welcomePanel.add(welcomeLabel2);
            welcomePanel.add(currentUsername2);

            statusPanel.add(statusLabel1);
            statusPanel.add(underlineLabel);
            statusPanel.add(status);
            statusPanel.add(status2);
            statusPanel.add(status3);
            statusPanel.add(slotsAvailable);

            queueButton.add(queueLabel);
            paymentButton.add(paymentLabel);

            homeScreenFrame.add(logoutButton);
            homeScreenFrame.add(userIconPanel);
            homeScreenFrame.add(currUserPanel);
            homeScreenFrame.add(welcomePanel);
            homeScreenFrame.add(statusPanel);
            homeScreenFrame.add(queueButton);
            homeScreenFrame.add(paymentButton);

            parkFirstQueued();
            changeScreenToParked();
            changeScreenToQueued();

            homeScreenFrame.setLocationRelativeTo(null);
            homeScreenFrame.setLayout(null);
            homeScreenFrame.setResizable(false);
            homeScreenFrame.setVisible(true);
        }

        public void changeScreenToParked(){
            for (int i = 0; i < getParkingSlotArray().length; i++) {
                if(getParkingSlotArray()[i].getUserUsername().equals(currentlyLoggedIn.get("username"))){
                    status.setText("You can now Park in");
                    status.setBounds(25, 60, 350, 30);
                    status.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    status.setForeground(Color.decode("#e4aa1e"));
                    status.setBorder(BorderFactory.createEmptyBorder());
                    status.setBackground(Color.decode("#333333"));
                    status.setEditable(false);

                    status2.setVisible(true);
                    status3.setVisible(true);
                    slotsAvailable.setVisible(false);
                    queueButton.setVisible(false);

                    paymentButton.setVisible(true);
                    paymentButton.setBounds(25, 510, 380, 60);

                    int slot = i + 1;
                    status2.setText("SLOT " + slot);
                }
            }
        }

        public void changeScreenToQueued(){
            if(userQueue.contains(currentlyLoggedIn)){
                status.setText("You are currently queued...");
                status.setBounds(25, 60, 350, 30);
                status.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                status.setForeground(Color.decode("#e4aa1e"));
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setBackground(Color.decode("#333333"));
                status.setEditable(false);

                slotsAvailable.setVisible(false);
                queueButton.setVisible(false);
            }
        }

        public void parkFirstQueued(){
            if(userQueue.size() > 0){
                for (int i = 0; i < getParkingSlotArray().length; i++) {
                    if(getParkingSlotArray()[i].getIsAvailable()){ //if a parkingSlot is available
                        park(userQueue.remove());
                        break;
                    }
                }
            }
        }
    }

    public static class PaymentScreen extends ParkingSlot{
        //JFrame
        JFrame mainFrame;

        //Panels
        static JPanel mainPanel,backButtonPanel,cashInPanel,greyPanel,whitePanel,payNowPanel;

        //Label
        static JLabel arrowIconLabel, moneyLabel, totalBalance, paymentLabel, cashInLogoLabel,balanceLabel, phpLabel,___,___2,totalLabel,vatLabel,total2Label,vat2Label, discountLabel,discount2Label,TotalLabel,Total2Label,remainLabel, payNowLabel,balanceBorder;

        //constructor
        public PaymentScreen(){
            //JFrame
            mainFrame = new JFrame();

            //Panel
            mainPanel = new JPanel();

            //Labels
            ___ = new JLabel("_____________________________________________________________________");
            ___2 = new JLabel("_________________________________________________________");
            payNowLabel = new JLabel("Pay now");
            remainLabel = new JLabel("         Remaining Balance               P");
            Total2Label = new JLabel("P50.00");
            TotalLabel = new JLabel("Total");
            discount2Label = new JLabel("P0.00");
            discountLabel = new JLabel("Discount");
            vat2Label = new JLabel("P5.36");
            vatLabel = new JLabel("12% VAT");
            total2Label = new JLabel("P44.64");
            totalLabel = new JLabel("Subtotal");
            phpLabel = new JLabel("Php");
            balanceLabel = new JLabel("BALANCE");
            paymentLabel = new JLabel("Payment");

            moneyLabel = new JLabel(String.valueOf(currentlyLoggedIn.getBalance()));

            if(currentlyLoggedIn.getBalance() < 50){
                totalBalance = new JLabel("0");
            }
            else{
                totalBalance = new JLabel(String.valueOf(currentlyLoggedIn.getBalance()-50));
            }

            //Panels
            mainPanel.setBounds(0,0,448,796);
            mainPanel.setLayout(null);
            mainPanel.setBackground(new Color(34,34,34,255));

            backButtonPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            cashInPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            greyPanel = new JPanel();
            whitePanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            payNowPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(50,50); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            backButtonPanel.setBackground(new Color(34,34,34,255));
            backButtonPanel.setBounds(22,20,45,45);
            backButtonPanel.setLayout(null);
            backButtonPanel.setFocusable(false);
            backButtonPanel.setOpaque(false);
            backButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new HomeScreen();
                    mainFrame.dispose();
                }
            });
            cashInPanel.setBackground(new Color(34,34,34,255));
            cashInPanel.setBounds(370,20,45,45);
            cashInPanel.setLayout(null);
            cashInPanel.setFocusable(false);
            cashInPanel.setOpaque(false);
            cashInPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new CashInView();
                    mainFrame.dispose();
                }
            });

            greyPanel.setBounds(0,95,448,180);
            greyPanel.setBackground(new Color(67,67,67,255));
            greyPanel.setLayout(null);

            whitePanel.setBounds(35,540,360,50);
            whitePanel.setBackground(Color.WHITE);
            whitePanel.setFocusable(false);
            whitePanel.setLayout(null);
            whitePanel.setOpaque(false);

            payNowPanel.setBounds(20,650,380,50);
            payNowPanel.setBackground(new Color(219,165,32,255));
            payNowPanel.setFocusable(false);
            payNowPanel.setOpaque(false);
            payNowPanel.setLayout(null);
            payNowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int result = JOptionPane.showConfirmDialog(mainFrame,"Do you want to proceed?", "Confirm Payment",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(currentlyLoggedIn.getBalance() >= 50){
                        if(result == JOptionPane.YES_OPTION){
                            currentlyLoggedIn.subtractBalance(50);
                            JOptionPane.showMessageDialog(mainFrame, "Remaining Balance is now ₱" + currentlyLoggedIn.getBalance());

                            for (int i = 0; i < getParkingSlotArray().length; i++) { //remove from parkingslotarray
                                if(getParkingSlotArray()[i].getUserUsername().equals(currentlyLoggedIn.get("username"))){
                                    getParkingSlotArray()[i].setUserUsername("");
                                    getParkingSlotArray()[i].setIsAvailable(true);
                                }
                            }

                            for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                                if(UserUsernameArraylist.get(i).get("username").equals(currentlyLoggedIn.get("username"))){
                                    FinishedUserUsernameArraylist.add(UserUsernameArraylist.get(i)); //add to finishedUserusernamearraylist
                                }
                            }
                            new HomeScreen();
                            mainFrame.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(mainFrame,"Insufficient balance.", "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //Labels
            //Back Button (Arrow Logo)
            ImageIcon arrowIcon = new ImageIcon(getClass().getResource("images\\back_arrow.png"));
            Image image = arrowIcon.getImage();
            Image newArrowIcon = image.getScaledInstance(15,10, Image.SCALE_SMOOTH);
            arrowIcon = new ImageIcon(newArrowIcon);
            arrowIconLabel = new JLabel(arrowIcon);
            arrowIconLabel.setBounds(0,0,45,45);

            paymentLabel.setFont(new Font("Century Gothic", Font.BOLD, 22));
            paymentLabel.setForeground(Color.WHITE);
            paymentLabel.setBounds(170,30,200,25);

            //Cash In Logo
            ImageIcon cashInLogo = new ImageIcon(getClass().getResource("images\\cash_in_icon.png"));
            Image image2 = cashInLogo.getImage();
            Image newCashInLogo = image2.getScaledInstance(25,20, Image.SCALE_SMOOTH);
            cashInLogo = new ImageIcon(newCashInLogo);
            cashInLogoLabel = new JLabel(cashInLogo);
            cashInLogoLabel.setBounds(0,0,45,45);

            ImageIcon balanceBorderPic = new ImageIcon(getClass().getResource("images\\yellow_square_border.png"));
            Image image3 = balanceBorderPic.getImage();
            Image newbalanceBorder = image3.getScaledInstance(110,40, Image.SCALE_SMOOTH);
            balanceBorderPic = new ImageIcon(newbalanceBorder);
            balanceBorder = new JLabel(balanceBorderPic);
            balanceBorder.setBounds(20,20,110,50);

            //Labels
            balanceLabel.setBounds(0,0,100,50);
            balanceLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            balanceLabel.setForeground(new Color(219,165,32,255));
            balanceLabel.setBounds(18,8,100,30);

            phpLabel.setBounds(0,0,100,50);
            phpLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            phpLabel.setForeground(Color.WHITE);
            phpLabel.setBounds(20,70,100,30);

            totalLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            totalLabel.setForeground(Color.WHITE);
            totalLabel.setBounds(60,310,300,50);

            total2Label.setFont(new Font("Century Gothic", Font.BOLD, 16));
            total2Label.setForeground(new Color(219,165,32,255));
            total2Label.setBounds(270,310,300,50);

            vatLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            vatLabel.setForeground(Color.WHITE);
            vatLabel.setBounds(60,350,300,50);

            vat2Label.setFont(new Font("Century Gothic", Font.BOLD, 16));
            vat2Label.setForeground(new Color(219,165,32,255));
            vat2Label.setBounds(280,350,300,50);

            discountLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            discountLabel.setForeground(Color.WHITE);
            discountLabel.setBounds(60,390,300,50);

            discount2Label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            discount2Label.setForeground(Color.WHITE);
            discount2Label.setBounds(280,390,300,50);

            TotalLabel.setFont(new Font("Century Gothic", Font.BOLD, 23));
            TotalLabel.setBounds(60,470,100,50);
            TotalLabel.setForeground(Color.WHITE);

            Total2Label.setFont(new Font("Century Gothic", Font.BOLD, 23));
            Total2Label.setBounds(250,470,100,50);
            Total2Label.setForeground(new Color(236,175,28,255));

            remainLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            remainLabel.setBounds(0,0,250,50);
            remainLabel.setForeground(new Color(70,70,70,255));

            payNowLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
            payNowLabel.setBounds(155,-3,200,50);
            payNowLabel.setForeground(Color.WHITE);

            totalBalance.setFont(new Font("Century Gothic", Font.PLAIN, 14));
            totalBalance.setBounds(240,10,100,30);
            totalBalance.setBorder(BorderFactory.createEmptyBorder());
            totalBalance.setBackground(Color.WHITE);
            totalBalance.setHorizontalAlignment(SwingConstants.LEFT);
            totalBalance.setForeground(new Color(70,70,100,255));

            moneyLabel.setFont(new Font("Century Gothic", Font.BOLD, 45));
            moneyLabel.setForeground(Color.WHITE);
            moneyLabel.setBorder(BorderFactory.createEmptyBorder());
            moneyLabel.setBackground(new Color(67,67,67,255));
            moneyLabel.setHorizontalAlignment(SwingConstants.LEFT);
            moneyLabel.setBounds(20,95,300,50);

            //Design Label
            ___.setBounds(0,0,100,50);
            ___.setFont(new Font("Century Gothic", Font.BOLD, 12));
            ___.setForeground(Color.WHITE);
            ___.setBounds(0,148,500,50);

            ___2.setBounds(0,0,100,50);
            ___2.setFont(new Font("Century Gothic", Font.BOLD, 12));
            ___2.setForeground(Color.WHITE);
            ___2.setBounds(35,420,500,50);

            mainFrame.setTitle("Cash-In");
            mainFrame.setSize(448, 796);
            mainFrame.setLayout(null);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);
            mainFrame.setVisible(true);

            //Adding of Components
            //Main Frame
            mainFrame.add(mainPanel);

            //Main Panel
            mainPanel.add(backButtonPanel);
            mainPanel.add(paymentLabel);
            mainPanel.add(cashInPanel);
            mainPanel.add(whitePanel);
            mainPanel.add(greyPanel);

            whitePanel.add(totalBalance);
            greyPanel.add(moneyLabel);

            mainPanel.add(totalLabel);
            mainPanel.add(total2Label);
            mainPanel.add(vatLabel);
            mainPanel.add(vat2Label);
            mainPanel.add(discountLabel);
            mainPanel.add(discount2Label);
            mainPanel.add(___2);

            mainPanel.add(TotalLabel);
            mainPanel.add(Total2Label);
            mainPanel.add(totalLabel);

            //White Panel
            whitePanel.add(remainLabel);

            //GreyPanel
            greyPanel.add(balanceBorder);
            greyPanel.add(phpLabel);

            greyPanel.add(___);

            //Balance Panel
            balanceBorder.add(balanceLabel);

            //Back Panel
            backButtonPanel.add(arrowIconLabel);

            //Cash In Panel
            cashInPanel.add(cashInLogoLabel);

            mainPanel.add(payNowPanel);
            //Pay Now Panel
            payNowPanel.add(payNowLabel);
        }
    }

    public static class CashInView {
        //JFrame
        JFrame CashInFrame;

        //Panels
        static JPanel mainPanel, headerPanel, optionPanel, confirmPanel, backPanel;

        //JLabels
        static JLabel enterLabel, amountLabel, balanceLabel, phpLabel, confirmLabel,
                cashInLabel, backLabel, creditIcon, creditLabel, paypalIcon,
                paypalLabel, gcashIcon, gcashLabel, ___, ___Option, ___Option2, cashLabel;

        JTextField amountTextField;

        static JRadioButton cardButton, paypalButton, gcashButton;

        static ButtonGroup buttonGroup;

        static ImageIcon gcash,  credit, paypal;

        public CashInView() {
            //JFrame
            CashInFrame = new JFrame();

            //Labels
            enterLabel = new JLabel("Enter");
            amountLabel = new JLabel("amount");
            balanceLabel = new JLabel("Current Balance");
            phpLabel = new JLabel("Php");
            confirmLabel = new JLabel("Confirm");
            cashInLabel = new JLabel("Cash In");
            backLabel = new JLabel("◄");
            creditLabel = new JLabel("Credit Card");
            paypalLabel = new JLabel("Paypal");
            gcashLabel = new JLabel("Gcash");
            gcashIcon = new JLabel(gcash);
            creditIcon = new JLabel(credit);
            paypalIcon = new JLabel(paypal);
            ___ = new JLabel("__________________________________________________________");
            ___Option = new JLabel("__________________________________________________");
            ___Option2 = new JLabel("__________________________________________________");

            //TextFields
            amountTextField = new JTextField("");
            cashLabel = new JLabel(String.valueOf(currentlyLoggedIn.getBalance()));

            //JPanels
            mainPanel = new JPanel();
            headerPanel = new JPanel();

            //Button Group
            buttonGroup = new ButtonGroup();

            //Panels
            mainPanel.setBackground(new Color(34,34,34,255));
            mainPanel.setLayout(null);
            mainPanel.setBounds(0,0,448,796);

            headerPanel.setBackground(new Color(34,34,34,255));
            headerPanel.setBounds(0,0,448,90);
            headerPanel.setLayout(null);

            optionPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            optionPanel.setBackground(new Color(147,147,147,255));
            optionPanel.setFocusable(false);
            optionPanel.setOpaque(false);
            optionPanel.setBounds(26,100,380,240);
            optionPanel.setLayout(null);

            confirmPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            confirmPanel.setBackground(new Color(241,179,28,255));
            confirmPanel.setBounds(26,690,380,50);
            confirmPanel.setLayout(null);
            confirmPanel.setFocusable(false);
            confirmPanel.setOpaque(false);
            confirmPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(cardButton.isSelected() || gcashButton.isSelected() || paypalButton.isSelected()){
                        String selection = "";
                        if(!amountTextField.getText().isEmpty()){
                            if(cardButton.isSelected()){
                                selection = "Card";
                            }
                            if(gcashButton.isSelected()){
                                selection ="GCash";
                            }
                            if(paypalButton.isSelected()){
                                selection = "Paypal";
                            }
                            int result = JOptionPane.showConfirmDialog(CashInFrame,"Confirm Cash-In via " + selection, "Confirm Cash-In",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                            if(result == JOptionPane.YES_OPTION){
                                JOptionPane.showMessageDialog(CashInFrame, "Successfully cashed-in ₱" + amountTextField.getText());
                                currentlyLoggedIn.addBalance(Integer.parseInt(amountTextField.getText()));

                                cashLabel.setText(String.valueOf(currentlyLoggedIn.getBalance()));
                                cashLabel.repaint();
                                amountTextField.setText("");
                                buttonGroup.clearSelection();

                                new PaymentScreen();
                                CashInFrame.dispose();
                            }
                            else{

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(CashInFrame,"Must input amount.", "Error",  JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(CashInFrame,"Must select Cash-In Method.", "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            backPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            backPanel.setBackground(new Color(34,34,34,255));
            backPanel.setBounds(22,20,45,45);
            backPanel.setFocusable(false);
            backPanel.setOpaque(false);
            backPanel.setLayout(null);
            backPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new PaymentScreen();
                    CashInFrame.dispose();
                }
            });

            amountTextField.setBounds(30,400,370,30);
            amountTextField.setEditable(true);
            amountTextField.setBorder(null);
            amountTextField.setBackground(new Color(34,34,34,255));
            amountTextField.setForeground(Color.GRAY);
            amountTextField.setFont(new Font("Century Gothic", Font.PLAIN,12));

            //Image Icons
            ImageIcon credit = new ImageIcon(getClass().getResource("images\\credit_card_icon_white.png"));
            Image image = credit.getImage();
            Image newCredit = image.getScaledInstance(40,30, Image.SCALE_SMOOTH);
            credit = new ImageIcon(newCredit);
            creditIcon = new JLabel(credit);
            creditIcon.setBounds(25,20,45,45);

            creditLabel.setForeground(Color.WHITE);
            creditLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
            creditLabel.setBounds(80,30,100,20);

            ImageIcon paypal = new ImageIcon(getClass().getResource("images\\paypal_icon.png"));
            Image image2 = paypal.getImage();
            Image newPaypal = image2.getScaledInstance(40,35, Image.SCALE_SMOOTH);
            paypal = new ImageIcon(newPaypal);
            paypalIcon = new JLabel(paypal);
            paypalIcon.setBounds(25,90,45,45);

            paypalLabel.setForeground(Color.WHITE);
            paypalLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
            paypalLabel.setBounds(80,100,100,20);

            ImageIcon gcash = new ImageIcon(getClass().getResource("images\\gcash_icon_white.png"));
            Image image3 = gcash.getImage();
            Image newGcash = image3.getScaledInstance(40,35, Image.SCALE_SMOOTH);
            gcash = new ImageIcon(newGcash);
            gcashIcon = new JLabel(gcash);
            gcashIcon.setBounds(25,170,45,45);

            gcashLabel.setForeground(Color.WHITE);
            gcashLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
            gcashLabel.setBounds(80,180,100,20);

            //Radio Buttons
            //Card Button
            cardButton = new JRadioButton();
            cardButton.setBounds(320,20,50,50);
            cardButton.setBorder(null);
            cardButton.setBackground(new Color(147,147,147,255));
            buttonGroup.add(cardButton);
            //Paypal Button
            paypalButton = new JRadioButton();
            paypalButton.setBounds(320,90,50,50);
            paypalButton.setBorder(null);
            paypalButton.setBackground(new Color(147,147,147,255));
            buttonGroup.add(paypalButton);
            //Gcash Button
            gcashButton = new JRadioButton();
            gcashButton.setBounds(320,170,50,50);
            gcashButton.setBorder(null);
            gcashButton.setBackground(new Color(147,147,147,255));
            buttonGroup.add(gcashButton);

            //Frame
            CashInFrame.setTitle("Cash In");
            CashInFrame.setSize(448, 796);
            CashInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Labels
            //Design
            ___.setBounds(30,410,400,30);
            ___.setForeground(Color.GRAY);

            ___Option.setBounds(30,55,400,30);
            ___Option.setForeground(new Color(121,121,121,255));

            ___Option2.setBounds(30,135,400,30);
            ___Option2.setForeground(new Color(121,121,121,255));

            cashInLabel.setBounds(165,15,100,50);
            cashInLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
            cashInLabel.setForeground(Color.WHITE);

            enterLabel.setFont(new Font("Century Gothic", Font.PLAIN,26));
            enterLabel.setBounds(30,350,100,50);
            enterLabel.setForeground(Color.WHITE);

            amountLabel.setFont(new Font("Century Gothic", Font.BOLD,26));
            amountLabel.setBounds(100,350,100,50);
            amountLabel.setForeground(Color.WHITE);

            balanceLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            balanceLabel.setBounds(200,460,100,50);
            balanceLabel.setForeground(Color.GRAY);

            phpLabel.setBounds(220,488,50,30);
            phpLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            phpLabel.setForeground(Color.WHITE);

            cashLabel.setBounds(250,488,500,30);
            cashLabel.setFont(new Font("Century Gothic", Font.PLAIN, 24));
            cashLabel.setForeground(Color.WHITE);

            confirmLabel.setForeground(Color.WHITE);
            confirmLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
            confirmLabel.setBounds(165,15,100,20);

            backLabel.setBounds(8,5,30,30);
            backLabel.setFont(new Font("Century Gothic", Font.PLAIN, 26));
            backLabel.setForeground(Color.WHITE);

            //Adding of Components
            //Adding of Panels in mainFrame
            CashInFrame.add(mainPanel);

            //Adding Labels in Header Panel
            headerPanel.add(cashInLabel);
            headerPanel.add(backPanel);

            //Back Button Panel
            backPanel.add(backLabel);

            //Adding of Panels in mainPanel
            mainPanel.add(headerPanel);
            mainPanel.add(optionPanel);
            mainPanel.add(confirmPanel);
            //Adding Labels into the mainPanel
            mainPanel.add(enterLabel);
            mainPanel.add(amountLabel);
            mainPanel.add(___);
            mainPanel.add(balanceLabel);
            mainPanel.add(cashLabel);
            mainPanel.add(phpLabel);

            //Adding TextField into the mainPanel
            mainPanel.add(amountTextField);

            //Adding Label to Confirm Panel
            confirmPanel.add(confirmLabel);

            //Adding Radio Buttons for the cash in options
            optionPanel.add(cardButton);
            optionPanel.add(paypalButton);
            optionPanel.add(gcashButton);

            optionPanel.add(___Option);
            optionPanel.add(___Option2);

            optionPanel.add(creditLabel);
            optionPanel.add(creditIcon);
            optionPanel.add(paypalLabel);
            optionPanel.add(paypalIcon);
            optionPanel.add(gcashLabel);
            optionPanel.add(gcashIcon);

            CashInFrame.add(mainPanel);

            CashInFrame.setLayout(null);
            CashInFrame.setLocationRelativeTo(null);
            CashInFrame.setResizable(false);
            CashInFrame.setVisible(true);
        }
    }

    // =========================================================================================================
    // ------------------------------------------ ADMIN SCREENS ------------------------------------------------
    // =========================================================================================================

    public static class AdminHomeScreen {
        //DECLARATION
        //JFrame
        static JFrame mainFrame;

        //JPanels
        static JPanel mainPanel;
        static JPanel namePanel;
        static JPanel exitButtonPanel;
        static JPanel mapPanel;
        static JPanel queuePanel;
        static JPanel transactionRecordsPanel;

        //JLabels
        static JLabel logoutIconLabel;
        static JLabel userIconLabel;
        static JLabel introLabel;
        static JLabel iParkLabel;
        static JLabel nameLabel;
        static JLabel transactionRecordsLabel;
        static JLabel transactionRecordsLabel2;
        static JLabel mapLabel;
        static JLabel queueLabel;

        //JTextField
        JTextField nameTextField;

        //ImageIcon
        static ImageIcon logoutIcon, userIcon;


        public AdminHomeScreen(){
            //GUI
            //JFrame
            mainFrame = new JFrame();

            //JLabels
            logoutIconLabel = new JLabel();
            userIconLabel = new JLabel();
            introLabel = new JLabel("Welcome to");
            iParkLabel = new JLabel("iPark,");
            nameLabel = new JLabel("Admin!");
            mapLabel = new JLabel("Map");
            queueLabel = new JLabel("Queue");
            transactionRecordsLabel = new JLabel("Transaction");
            transactionRecordsLabel2 = new JLabel("Records");

            //JPanels
            mainPanel = new JPanel();
            namePanel = new JPanel();
            exitButtonPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };

            mapPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };

            queuePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };

            transactionRecordsPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };

            //JTextField
            nameTextField = new JTextField(currentlyLoggedIn.get("username"));

            //ImageIcons
            ImageIcon logoutIcon  = new ImageIcon(getClass().getResource("images\\logout.png"));
            Image image = logoutIcon.getImage(); // transform it
            Image newLogoutIcon = image.getScaledInstance(25, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            logoutIcon = new ImageIcon(newLogoutIcon);  // transform it back

            ImageIcon userIcon  = new ImageIcon(getClass().getResource("images\\userIcon.png"));
            Image image2 = userIcon.getImage(); // transform it
            Image newUserIcon = image2.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            userIcon = new ImageIcon(newUserIcon);  // transform it back

            // ----------------------------- COMPONENT SETTINGS -------------------------
            //FRAME
            mainFrame.setTitle("Admin Home Screen");
            mainFrame.setSize(448, 796);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setBackground(new Color(34,34,34,255));

            //PANELS
            mainPanel.setBounds(0,0,448,796);
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(null);

            namePanel.setBounds(72,27,290,20);
            namePanel.setBackground(Color.WHITE);
            namePanel.setLayout(null);

            exitButtonPanel.setBounds(22,20,45,45);
            exitButtonPanel.setBackground(Color.WHITE);
            exitButtonPanel.setLayout(null);
            exitButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    new LoginScreen();
                    mainFrame.dispose();
                }
            });

            mapPanel.setBounds(22,220,390,150);
            mapPanel.setBackground(new Color(51,51,51,255));
            mapPanel.setLayout(null);
            mapPanel.setFocusable(false);
            mapPanel.setOpaque(false);
            mapPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new AdminMapScreen();
                    mainFrame.dispose();
                }
            });

            queuePanel.setBounds(22,400,170,150);
            queuePanel.setBackground(new Color(51,51,51,255));
            queuePanel.setLayout(null);
            queuePanel.setFocusable(false);
            queuePanel.setOpaque(false);
            queuePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new QueueScreen();
                    mainFrame.dispose();
                }
            });

            transactionRecordsPanel.setBounds(240,400,170,150);
            transactionRecordsPanel.setBackground(new Color(51,51,51,255));
            transactionRecordsPanel.setLayout(null);
            transactionRecordsPanel.setFocusable(false);
            transactionRecordsPanel.setOpaque(false);
            transactionRecordsPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new TransactionRecordScreen();
                    mainFrame.dispose();
                }
            });

            //LABELS
            logoutIconLabel.setIcon(logoutIcon);
            logoutIconLabel.setBounds(11, 2, 45, 45);

            userIconLabel.setIcon(userIcon);
            userIconLabel.setBounds(365,20,60,60);

            introLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
            introLabel.setForeground(Color.BLACK);
            introLabel.setBounds(22,120,250,50);

            iParkLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
            iParkLabel.setForeground(Color.BLACK);
            iParkLabel.setBounds(22,160,120,50);

            nameLabel.setFont(new Font("Century Gothic", Font.BOLD, 32));
            nameLabel.setForeground(Color.BLACK);
            nameLabel.setBounds(110,160,300,50);

            mapLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            mapLabel.setForeground(Color.WHITE);
            mapLabel.setBounds(20,8,100,50);

            queueLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            queueLabel.setForeground(Color.WHITE);
            queueLabel.setBounds(20,8,100,50);

            transactionRecordsLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            transactionRecordsLabel.setForeground(Color.WHITE);
            transactionRecordsLabel.setBounds(20,8,100,50);

            transactionRecordsLabel2.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            transactionRecordsLabel2.setForeground(Color.WHITE);
            transactionRecordsLabel2.setBounds(20,28,100,50);

            //TEXTFIELD
            nameTextField.setBounds(160,30,200,50);
            nameTextField.setFont(new Font("Century Gothic", Font.BOLD, 16));
            nameTextField.setBorder(BorderFactory.createEmptyBorder());
            nameTextField.setBackground(Color.WHITE);
            nameTextField.setForeground(Color.decode("#030303"));
            nameTextField.setEditable(false);
            nameTextField.setHorizontalAlignment(SwingConstants.RIGHT);

            //ADDING OF COMPONENTS
            //Main Frame
            mainFrame.add(mainPanel);

            //Main Panel
            //Panels
            mainPanel.add(namePanel);
            mainPanel.add(exitButtonPanel);
            mainPanel.add(mapPanel);
            mainPanel.add(queuePanel);
            mainPanel.add(transactionRecordsPanel);

            //Adding Labels
            mainPanel.add(nameLabel);
            mainPanel.add(userIconLabel);
            mainPanel.add(introLabel);
            mainPanel.add(iParkLabel);
            mainPanel.add(mapLabel);
            mainPanel.add(queueLabel);
            mainPanel.add(transactionRecordsLabel);

            //Dynamic Username
            mainPanel.add(nameTextField);

            //Map Records Panel
            mapPanel.add(mapLabel);

            //Queue Panel
            queuePanel.add(queueLabel);

            //Transaction Records Panel
            transactionRecordsPanel.add(transactionRecordsLabel);
            transactionRecordsPanel.add(transactionRecordsLabel2);

            //Exit Button Panel
            exitButtonPanel.add(logoutIconLabel);

            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(null);
            mainFrame.setResizable(false);
            mainFrame.setVisible(true);

        }
    }

    // ======================================= ADMIN MAP SCREEN =======================================================

    public static class AdminMapScreen extends ParkingSlot{
        //Declaration of Components
        //JFrame
        static JFrame adminMap;

        //JLabels
        static JLabel mapLabel, backButton, parkingLotLabel, plateNoLabel, statusLabel, parkingMapLabel, slot1Label, slot2Label, slot3Label, slot4Label, slot5Label, slot6Label, slot7Label, slot8Label, slot9Label, slot10Label;

        //JPanels
        JPanel mapTab;
        static JPanel backButtonPanel, parkingMapPanel, slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10;

        //JTextFields
        JTextField parkingSlot, plateNo, status;

        public AdminMapScreen() {
            //Declaration of GUIs
            //JFrames
            adminMap = new JFrame();
            //JPanels
            mapTab = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(150,1500); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            backButtonPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };

            slot1 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot2 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot3 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot4 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot5 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot6 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot7 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot8 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot9 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            slot10 = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(30,30); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };
            parkingMapPanel = new JPanel();

            //ImageIcons
            ImageIcon backArrow = new ImageIcon(getClass().getResource("images\\black_arrow.png"));
            Image image = backArrow.getImage(); // transform it
            Image newBackArrow = image.getScaledInstance(25, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            backArrow = new ImageIcon(newBackArrow);  // transform it back

            ImageIcon parkingMap = new ImageIcon(getClass().getResource("images\\map.png"));
            Image image2 = parkingMap.getImage(); // transform it
            Image newParkingMap = image2.getScaledInstance(425, 490,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            parkingMap = new ImageIcon(newParkingMap);  // transform it back

            //-------------------------- COMPONENT SETTINGS ----------------------------------------

            //FRAME
            adminMap.setTitle("Admin Map");
            adminMap.setSize(448, 796);
            adminMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adminMap.getContentPane().setBackground(Color.decode("#252423"));

            //PANELS
            mapTab.setBounds(-15, -100, 463, 295);
            mapTab.setFocusable(false);
            mapTab.setBackground(Color.decode("#fafafa"));
            mapTab.setForeground(Color.decode("#fafafa"));
            mapTab.setOpaque(false);
            mapTab.setLayout(null);

            backButtonPanel.setBounds(50, 115, 60, 60);
            backButtonPanel.setBackground(Color.decode("#fafafa"));
            backButtonPanel.setForeground(Color.decode("#a9a9a9"));
            backButtonPanel.setLayout(null);
            backButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new AdminHomeScreen();
                    adminMap.dispose();
                }
            });

            parkingMapPanel.setBounds(0, 200, 500, 500);
            parkingMapPanel.setFocusable(false);
            parkingMapPanel.setBackground(Color.decode("#252423"));
            parkingMapPanel.setLayout(null);

            //------------------ LEFT SIDE PARKING SLOTS --------------------------------------------

            slot1.setBounds(35, 78, 125, 48);

            if(getParkingSlotArray()[0].getIsAvailable()) {
                slot1.setBackground(Color.decode("#252423"));
                slot1.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot1.setBackground(Color.decode("#f1b31c"));
                slot1.setForeground(Color.decode("#f1b31c"));
            }
            slot1.setFocusable(false);
            slot1.setOpaque(false);
            slot1.setLayout(null);
            slot1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {

                    parkingSlot.setText("S1");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[0].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            slot2.setBounds(35, 147, 125, 48);
            if(getParkingSlotArray()[1].getIsAvailable()) {
                slot2.setBackground(Color.decode("#252423"));
                slot2.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot2.setBackground(Color.decode("#f1b31c"));
                slot2.setForeground(Color.decode("#f1b31c"));
            }
            slot2.setFocusable(false);
            slot2.setOpaque(false);
            slot2.setLayout(null);
            slot2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S2");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[1].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            slot3.setBounds(35, 214, 125, 48);
            if(getParkingSlotArray()[2].getIsAvailable()) {
                slot3.setBackground(Color.decode("#252423"));
                slot3.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot3.setBackground(Color.decode("#f1b31c"));
                slot3.setForeground(Color.decode("#f1b31c"));
            }
            slot3.setFocusable(false);
            slot3.setOpaque(false);
            slot3.setLayout(null);
            slot3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S3");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[2].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            slot4.setBounds(35, 281, 125, 48);
            if(getParkingSlotArray()[3].getIsAvailable()){
                slot4.setBackground(Color.decode("#252423"));
                slot4.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot4.setBackground(Color.decode("#f1b31c"));
                slot4.setForeground(Color.decode("#f1b31c"));
            }
            slot4.setFocusable(false);
            slot4.setOpaque(false);
            slot4.setLayout(null);
            slot4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    parkingSlot.setText("S4");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[3].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            slot5.setBounds(35, 350, 125, 48);
            if(getParkingSlotArray()[4].getIsAvailable()){
                slot5.setBackground(Color.decode("#252423"));
                slot5.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot5.setBackground(Color.decode("#f1b31c"));
                slot5.setForeground(Color.decode("#f1b31c"));
            }
            slot5.setFocusable(false);
            slot5.setOpaque(false);
            slot5.setLayout(null);
            slot5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S5");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[4].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            //---------------------------------------------------------------------------------------

            //------------------ RIGHT SIDE PARKING SLOTS ------------------------------------------

            slot6.setBounds(275, 78, 125, 48);
            if(getParkingSlotArray()[5].getIsAvailable()){
                slot6.setBackground(Color.decode("#252423"));
                slot6.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot6.setBackground(Color.decode("#f1b31c"));
                slot6.setForeground(Color.decode("#f1b31c"));
            }
            slot6.setFocusable(false);
            slot6.setOpaque(false);
            slot6.setLayout(null);
            slot6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S6");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[5].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            slot7.setBounds(275, 147, 125, 48);
            if(getParkingSlotArray()[6].getIsAvailable()){
                slot7.setBackground(Color.decode("#252423"));
                slot7.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot7.setBackground(Color.decode("#f1b31c"));
                slot7.setForeground(Color.decode("#f1b31c"));
            }
            slot7.setFocusable(false);
            slot7.setOpaque(false);
            slot7.setLayout(null);
            slot7.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S7");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[6].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }
            });

            slot8.setBounds(275, 214, 125, 48);
            if(getParkingSlotArray()[7].getIsAvailable()){
                slot8.setBackground(Color.decode("#252423"));
                slot8.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot8.setBackground(Color.decode("#f1b31c"));
                slot8.setForeground(Color.decode("#f1b31c"));
            }
            slot8.setFocusable(false);
            slot8.setOpaque(false);
            slot8.setLayout(null);
            slot8.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S8");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[7].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }

            });

            slot9.setBounds(275, 281, 125, 48);
            if(getParkingSlotArray()[8].getIsAvailable()){
                slot9.setBackground(Color.decode("#252423"));
                slot9.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot9.setBackground(Color.decode("#f1b31c"));
                slot9.setForeground(Color.decode("#f1b31c"));
            }
            slot9.setFocusable(false);
            slot9.setOpaque(false);
            slot9.setLayout(null);
            slot9.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S9");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[8].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }

            });

            slot10.setBounds(275, 350, 125, 48);
            if(getParkingSlotArray()[9].getIsAvailable()){
                slot10.setBackground(Color.decode("#252423"));
                slot10.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot10.setBackground(Color.decode("#f1b31c"));
                slot10.setForeground(Color.decode("#f1b31c"));
            }
            slot10.setFocusable(false);
            slot10.setOpaque(false);
            slot10.setLayout(null);
            slot10.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    parkingSlot.setText("S10");

                    for (int i = 0; i < UserUsernameArraylist.size(); i++) {
                        if(UserUsernameArraylist.get(i).get("username").equals(getParkingSlotArray()[9].getUserUsername())){
                            plateNo.setText(UserUsernameArraylist.get(i).get("carPlateNumber"));
                            break;
                        }
                        else{
                            plateNo.setText("N/A");
                        }
                    }

                    if(plateNo.getText().equals("N/A")){
                        status.setText("Available");
                        status.setForeground(Color.decode("#1a9c00"));
                    }
                    else{
                        status.setText("Occupied");
                        status.setForeground(Color.decode("#f1b31c"));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    parkingSlot.setText("");
                    plateNo.setText("");
                    status.setText("");
                    //status.setForeground(Color.decode("#1a9c00"));
                }

            });

            //----------------------------------------------------------------------------------

            //LABELS
            mapLabel = new JLabel("Map");
            mapLabel.setFont(new Font("Century Gothic", Font.BOLD, 22));
            mapLabel.setBounds(205, 40, 200, 200);
            mapLabel.setForeground(Color.decode("#000000"));

            backButton = new JLabel();
            backButton.setIcon(backArrow);
            backButton.setBounds(17, 7, 45, 45);
            //backButton.setForeground(Color.decode("#000000"));

            parkingLotLabel = new JLabel("Parking Lot");
            parkingLotLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
            parkingLotLabel.setForeground(Color.decode("#a9a9a9"));
            parkingLotLabel.setBounds(45, 190, 120, 30);

            plateNoLabel = new JLabel("Car Plate No.");
            plateNoLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
            plateNoLabel.setForeground(Color.decode("#a9a9a9"));
            plateNoLabel.setBounds(185, 190, 120, 30);

            statusLabel = new JLabel("Status");
            statusLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
            statusLabel.setForeground(Color.decode("#a9a9a9"));
            statusLabel.setBounds(325, 190, 120, 30);

            parkingMapLabel = new JLabel();
            parkingMapLabel.setIcon(parkingMap);
            parkingMapLabel.setBounds(5, -10, 500, 500);

            slot1Label = new JLabel("S1");
            slot1Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[0].getIsAvailable()) {
                slot1Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot1Label.setForeground(Color.decode("#000000"));
            }
            slot1Label.setBounds(56, 7, 50, 30);

            slot2Label = new JLabel("S2");
            slot2Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[1].getIsAvailable()) {
                slot2Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot2Label.setForeground(Color.decode("#000000"));
            }
            slot2Label.setBounds(56, 7, 50, 30);

            slot3Label = new JLabel("S3");
            slot3Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[2].getIsAvailable()) {
                slot3Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot3Label.setForeground(Color.decode("#000000"));
            }
            slot3Label.setBounds(56, 7, 50, 30);

            slot4Label = new JLabel("S4");
            slot4Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[3].getIsAvailable()) {
                slot4Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot4Label.setForeground(Color.decode("#000000"));
            }
            slot4Label.setBounds(56, 7, 50, 30);

            slot5Label = new JLabel("S5");
            slot5Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[4].getIsAvailable()) {
                slot5Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot5Label.setForeground(Color.decode("#000000"));
            }
            slot5Label.setBounds(56, 7, 50, 30);

            slot6Label = new JLabel("S6");
            slot6Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[5].getIsAvailable()) {
                slot6Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot6Label.setForeground(Color.decode("#000000"));
            }
            slot6Label.setBounds(56, 7, 50, 30);

            slot7Label = new JLabel("S7");
            slot7Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[6].getIsAvailable()) {
                slot7Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot7Label.setForeground(Color.decode("#000000"));
            }
            slot7Label.setBounds(56, 7, 50, 30);

            slot8Label = new JLabel("S8");
            slot8Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[7].getIsAvailable()) {
                slot8Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot8Label.setForeground(Color.decode("#000000"));
            }
            slot8Label.setBounds(56, 7, 50, 30);

            slot9Label = new JLabel("S9");
            slot9Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[8].getIsAvailable()) {
                slot9Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot9Label.setForeground(Color.decode("#000000"));
            }
            slot9Label.setBounds(56, 7, 50, 30);

            slot10Label = new JLabel("S10");
            slot10Label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            if(getParkingSlotArray()[9].getIsAvailable()) {
                slot10Label.setForeground(Color.decode("#fafafa"));
            }
            else{
                slot10Label.setForeground(Color.decode("#000000"));
            }
            slot10Label.setBounds(56, 7, 50, 30);

            //TEXTFIELDS
            parkingSlot = new JTextField("");
            parkingSlot.setFont(new Font("Century Gothic", Font.BOLD, 18));
            parkingSlot.setForeground(Color.decode("#000000"));
            parkingSlot.setBackground(Color.decode("#fafafa"));
            parkingSlot.setEditable(false);
            parkingSlot.setBorder(BorderFactory.createEmptyBorder());
            parkingSlot.setBounds(45, 220, 100, 30);

            plateNo = new JTextField("");
            plateNo.setFont(new Font("Century Gothic", Font.BOLD, 18));
            plateNo.setForeground(Color.decode("#000000"));
            plateNo.setBackground(Color.decode("#fafafa"));
            plateNo.setEditable(false);
            plateNo.setBorder(BorderFactory.createEmptyBorder());
            plateNo.setBounds(185, 220, 100, 30);

            status = new JTextField("");
            status.setFont(new Font("Century Gothic", Font.BOLD, 18));
            status.setForeground(Color.decode("#1a9c00"));
            status.setBackground(Color.decode("#fafafa"));
            status.setEditable(false);
            status.setBorder(BorderFactory.createEmptyBorder());
            status.setBounds(325, 220, 100, 30);

            mapTab.add(mapLabel);
            mapTab.add(backButtonPanel);
            mapTab.add(parkingLotLabel);
            mapTab.add(plateNoLabel);
            mapTab.add(statusLabel);
            mapTab.add(parkingSlot);
            mapTab.add(plateNo);
            mapTab.add(status);

            parkingMapPanel.add(parkingMapLabel);
            parkingMapPanel.add(slot1);
            parkingMapPanel.add(slot2);
            parkingMapPanel.add(slot3);
            parkingMapPanel.add(slot4);
            parkingMapPanel.add(slot5);
            parkingMapPanel.add(slot6);
            parkingMapPanel.add(slot7);
            parkingMapPanel.add(slot8);
            parkingMapPanel.add(slot9);
            parkingMapPanel.add(slot10);

            slot1.add(slot1Label);
            slot2.add(slot2Label);
            slot3.add(slot3Label);
            slot4.add(slot4Label);
            slot5.add(slot5Label);
            slot6.add(slot6Label);
            slot7.add(slot7Label);
            slot8.add(slot8Label);
            slot9.add(slot9Label);
            slot10.add(slot10Label);

            backButtonPanel.add(backButton);

            adminMap.add(mapTab);
            adminMap.add(parkingMapPanel);

            adminMap.setLayout(null);
            adminMap.setResizable(false);
            adminMap.setVisible(true);
            adminMap.setLocationRelativeTo(null);
        }
    }

    // ========================================== QUEUE SCREEN ======================================================

    public static class QueueScreen extends ParkingSlot {

        //DECLARATION
        //JFrame
        static JFrame mainFrame;

        //JPanels
        static JPanel mainPanel, headerPanel, queuePanel, currentPanel;

        //JLabels
        static JLabel backButtonIconLabel, grayBorderIconLabel, headerTitleLabel, queueLabel, car1IconLabel, car2IconLabel, car3IconLabel, car4IconLabel, yellowCircleIconLabel, currentLabel, usernameIconLabel, contactIconLabel, brandIconLabel, carModelIconLabel, plateNumberIconLabel, usernameLabel, contactLabel, brandLabel, carModelLabel, plateNumberLabel;

        //JTextField(Dyanamic)
        static JTextField numberTextField, usernameTextField, contactTextField, brandTextField, carModelTextField, plateNumberTextField;

        //ImageIcon
        static ImageIcon backIcon, grayBorderIcon, car1Icon, car2Icon, car3Icon, car4Icon,
                yellowCircleIcon, usernameIcon, contactIcon, brandIcon, carModelIcon, plateNumberIcon;

        public QueueScreen(){
            //GUI
            //JFrame
            mainFrame = new JFrame();

            //JLabels
            backButtonIconLabel = new JLabel();
            grayBorderIconLabel = new JLabel();
            headerTitleLabel = new JLabel("Queue");
            queueLabel = new JLabel("Cars in Queue:");
            car1IconLabel = new JLabel();
            car2IconLabel = new JLabel();
            car3IconLabel = new JLabel();
            car4IconLabel= new JLabel();
            yellowCircleIconLabel = new JLabel();
            currentLabel = new JLabel("Currently in front:");
            usernameIconLabel = new JLabel();
            contactIconLabel = new JLabel();
            brandIconLabel = new JLabel();
            carModelIconLabel = new JLabel();
            plateNumberIconLabel = new JLabel();
            usernameLabel = new JLabel("Username");
            contactLabel = new JLabel("Contact No.");
            brandLabel = new JLabel("Brand");
            carModelLabel = new JLabel("Model");
            plateNumberLabel = new JLabel("Plate Number");

            mainPanel = new JPanel();
            headerPanel = new JPanel();
            queuePanel = new JPanel();
            currentPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };

            //JTextField
            if(userQueue.size() > 0){
                String size = String.valueOf(userQueue.size());
                numberTextField = new JTextField(size);
                usernameTextField = new JTextField(userQueue.peek().get("username"));
                contactTextField = new JTextField(userQueue.peek().get("mobileNumber"));
                brandTextField = new JTextField(userQueue.peek().get("carBrand"));
                carModelTextField = new JTextField(userQueue.peek().get("carModel"));
                plateNumberTextField = new JTextField(userQueue.peek().get("carPlateNumber"));
            }
            else{
                numberTextField = new JTextField("0");
                usernameTextField = new JTextField("");
                contactTextField = new JTextField("");
                brandTextField = new JTextField("");
                carModelTextField = new JTextField("");
                plateNumberTextField = new JTextField("");
            }

            //ImageIcons
            ImageIcon backIcon = new ImageIcon(getClass().getResource("images\\back_arrow.png"));
            Image image = backIcon.getImage();
            Image newBackIcon = image.getScaledInstance(19,16, Image.SCALE_SMOOTH);
            backIcon = new ImageIcon(newBackIcon);

            ImageIcon grayBorderIcon = new ImageIcon(getClass().getResource("images\\gray_panel.png"));
            Image image12 = grayBorderIcon.getImage();
            Image newGrayBorderIcon = image12.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            grayBorderIcon = new ImageIcon(newGrayBorderIcon);

            ImageIcon car1Icon = new ImageIcon(getClass().getResource("images\\car1.png"));
            Image image2 = car1Icon.getImage();
            Image newCar1Icon = image2.getScaledInstance(59,22, Image.SCALE_SMOOTH);
            car1Icon = new ImageIcon(newCar1Icon);

            ImageIcon car2Icon = new ImageIcon(getClass().getResource("images\\car2.png"));
            Image image3 = car2Icon.getImage();
            Image newCar2Icon = image3.getScaledInstance(59,22, Image.SCALE_SMOOTH);
            car2Icon = new ImageIcon(newCar2Icon);

            ImageIcon car3Icon = new ImageIcon(getClass().getResource("images\\car3.png"));
            Image image4 = car3Icon.getImage();
            Image newCar3Icon = image4.getScaledInstance(59,22, Image.SCALE_SMOOTH);
            car3Icon = new ImageIcon(newCar3Icon);

            ImageIcon car4Icon = new ImageIcon(getClass().getResource("images\\car4.png"));
            Image image5 = car4Icon.getImage();
            Image newCar4Icon = image5.getScaledInstance(59,22, Image.SCALE_SMOOTH);
            car4Icon = new ImageIcon(newCar4Icon);

            ImageIcon yellowCircleIcon = new ImageIcon(getClass().getResource("images\\yellow_circle.png"));
            Image image6 = yellowCircleIcon.getImage();
            Image newYellowCircleIcon = image6.getScaledInstance(110,110, Image.SCALE_SMOOTH);
            yellowCircleIcon = new ImageIcon(newYellowCircleIcon);

            ImageIcon usernameIcon = new ImageIcon(getClass().getResource("images\\username_icon.png"));
            Image image7 = usernameIcon.getImage();
            Image newUsernameIcon = image7.getScaledInstance(21,21, Image.SCALE_SMOOTH);
            usernameIcon = new ImageIcon(newUsernameIcon);

            ImageIcon contactIcon = new ImageIcon(getClass().getResource("images\\Contact_icon.png"));
            Image image8 = contactIcon.getImage();
            Image newContactIcon = image8.getScaledInstance(21,21, Image.SCALE_SMOOTH);
            contactIcon = new ImageIcon(newContactIcon);

            ImageIcon brandIcon = new ImageIcon(getClass().getResource("images\\Brand_icon.png"));
            Image image9 = brandIcon.getImage();
            Image newBrandIcon = image9.getScaledInstance(21,21, Image.SCALE_SMOOTH);
            brandIcon = new ImageIcon(newBrandIcon);

            ImageIcon carModelIcon = new ImageIcon(getClass().getResource("images\\Car_Model_Icon.png"));
            Image image10 = carModelIcon.getImage();
            Image newCarModelIcon = image10.getScaledInstance(21,21, Image.SCALE_SMOOTH);
            carModelIcon = new ImageIcon(newCarModelIcon);

            ImageIcon plateNumberIcon = new ImageIcon(getClass().getResource("images\\PlateNumberIcon.png"));
            Image image11 = plateNumberIcon.getImage();
            Image newPlateNumberIcon = image11.getScaledInstance(21,21, Image.SCALE_SMOOTH);
            plateNumberIcon = new ImageIcon(newPlateNumberIcon);

            // ----------------------------- COMPONENT SETTINGS -------------------------
            //FRAME
            mainFrame.setTitle("Queue Screen");
            mainFrame.setSize(448, 796);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setBackground(new Color(34,34,34,255));

            //PANELS
            mainPanel.setBounds(0,0,448,796);
            mainPanel.setBackground(new Color(34,34,34,255));
            mainPanel.setLayout(null);

            headerPanel.setBounds(0,0,448,100);
            headerPanel.setBackground(new Color(34,34,34,255));
            headerPanel.setLayout(null);

            queuePanel.setBounds(0,100,448,280);
            queuePanel.setBackground(new Color(34,34,34,255));
            queuePanel.setLayout(null);

            currentPanel.setBounds(20,380,390,250);
            currentPanel.setBackground(new Color(128,128,128,255));
            currentPanel.setLayout(null);
            currentPanel.setFocusable(false);
            currentPanel.setOpaque(false);

            //LABELS
            //Header Panel Labels
            backButtonIconLabel = new JLabel(backIcon);
            backButtonIconLabel.setBounds(35,35,19,16);
            backButtonIconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new AdminHomeScreen();
                    mainFrame.dispose();
                }
            });

            grayBorderIconLabel = new JLabel(grayBorderIcon);
            grayBorderIconLabel.setBounds(20, 20, 50, 50);

            headerTitleLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
            headerTitleLabel.setForeground(Color.WHITE);
            headerTitleLabel.setBounds(170,20,100,50);

            //Queue Panel Labels
            queueLabel.setFont(new Font("Century Gothic", Font.PLAIN, 21));
            queueLabel.setForeground(Color.WHITE);
            queueLabel.setBounds(140,0,150,50);

            car1IconLabel = new JLabel(car1Icon);
            car1IconLabel.setBounds(30,140,59, 22);

            car2IconLabel = new JLabel(car2Icon);
            car2IconLabel.setBounds(100,140,59,22);

            car3IconLabel = new JLabel(car3Icon);
            car3IconLabel.setBounds(270,140,59,22);

            car4IconLabel = new JLabel(car4Icon);
            car4IconLabel.setBounds(340,140,59,22);

            yellowCircleIconLabel = new JLabel(yellowCircleIcon);
            yellowCircleIconLabel.setBounds(160,60, 110,110);

            currentLabel.setFont(new Font("Century Gothic", Font.PLAIN, 21));
            currentLabel.setForeground(Color.WHITE);
            currentLabel.setBounds(20,230,200,50);

            //Current Panel Labels
            usernameIconLabel = new JLabel(usernameIcon);
            usernameIconLabel.setBounds(20,20, 21,21);

            contactIconLabel = new JLabel(contactIcon);
            contactIconLabel.setBounds(20,65, 21,21);

            brandIconLabel = new JLabel(brandIcon);
            brandIconLabel.setBounds(20,110, 21,21);

            carModelIconLabel = new JLabel(carModelIcon);
            carModelIconLabel.setBounds(20,155, 21,21);

            plateNumberIconLabel = new JLabel(plateNumberIcon);
            plateNumberIconLabel.setBounds(20,200, 21,21);

            usernameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setBounds(50,20, 80,21);

            contactLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            contactLabel.setForeground(Color.WHITE);
            contactLabel.setBounds(50,65, 80,21);

            brandLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            brandLabel.setForeground(Color.WHITE);
            brandLabel.setBounds(50,110, 80,21);

            carModelLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            carModelLabel.setForeground(Color.WHITE);
            carModelLabel.setBounds(50,155, 80,21);

            plateNumberLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            plateNumberLabel.setForeground(Color.WHITE);
            plateNumberLabel.setBounds(50,200, 100,21);

            //TEXTFIELD
            numberTextField.setFont(new Font("Century Gothic", Font.BOLD, 54));
            numberTextField.setForeground(Color.WHITE);
            numberTextField.setBounds(188,85,60,50);
            numberTextField.setBorder(BorderFactory.createEmptyBorder());
            numberTextField.setBackground(new Color(241,179,28,255));
            numberTextField.setEditable(false);
            numberTextField.setHorizontalAlignment(SwingConstants.RIGHT);

            usernameTextField.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            usernameTextField.setForeground(Color.WHITE);
            usernameTextField.setBounds(150,20, 200,21);
            usernameTextField.setBorder(BorderFactory.createEmptyBorder());
            usernameTextField.setBackground(new Color(128,128,128,255));
            usernameTextField.setForeground(Color.WHITE);
            usernameTextField.setEditable(false);
            usernameTextField.setHorizontalAlignment(SwingConstants.LEFT);

            contactTextField.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            contactTextField.setForeground(Color.WHITE);
            contactTextField.setBounds(150,65, 200,21);
            contactTextField.setBorder(BorderFactory.createEmptyBorder());
            contactTextField.setBackground(new Color(128,128,128,255));
            contactTextField.setForeground(Color.WHITE);
            contactTextField.setEditable(false);
            contactTextField.setHorizontalAlignment(SwingConstants.LEFT);

            brandTextField.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            brandTextField.setForeground(Color.WHITE);
            brandTextField.setBounds(150,110, 200,21);
            brandTextField.setBorder(BorderFactory.createEmptyBorder());
            brandTextField.setBackground(new Color(128,128,128,255));
            brandTextField.setForeground(Color.WHITE);
            brandTextField.setEditable(false);
            brandTextField.setHorizontalAlignment(SwingConstants.LEFT);

            carModelTextField.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            carModelTextField.setForeground(Color.WHITE);
            carModelTextField.setBounds(150,155, 200,21);
            carModelTextField.setBorder(BorderFactory.createEmptyBorder());
            carModelTextField.setBackground(new Color(128,128,128,255));
            carModelTextField.setForeground(Color.WHITE);
            carModelTextField.setEditable(false);
            carModelTextField.setHorizontalAlignment(SwingConstants.LEFT);

            plateNumberTextField.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            plateNumberTextField.setForeground(Color.WHITE);
            plateNumberTextField.setBounds(150,200, 200,21);
            plateNumberTextField.setBorder(BorderFactory.createEmptyBorder());
            plateNumberTextField.setBackground(new Color(128,128,128,255));
            plateNumberTextField.setForeground(Color.WHITE);
            plateNumberTextField.setEditable(false);
            plateNumberTextField.setHorizontalAlignment(SwingConstants.LEFT);

            //ADDING OF COMPONENTS
            //Main Frame
            mainFrame.add(mainPanel);

            //Main Panel
            mainPanel.add(headerPanel);
            mainPanel.add(queuePanel);
            mainPanel.add(currentPanel);

            //Header Panel
            headerPanel.add(backButtonIconLabel);
            headerPanel.add(grayBorderIconLabel);
            headerPanel.add(headerTitleLabel);

            //Queue Panel
            queuePanel.add(queueLabel);
            queuePanel.add(car1IconLabel);
            queuePanel.add(car2IconLabel);
            queuePanel.add(car3IconLabel);
            queuePanel.add(car4IconLabel);
            queuePanel.add(numberTextField);
            queuePanel.add(yellowCircleIconLabel);
            queuePanel.add(currentLabel);

            //Current Panel
            currentPanel.add(usernameIconLabel);
            currentPanel.add(contactIconLabel);
            currentPanel.add(brandIconLabel);
            currentPanel.add(carModelIconLabel);
            currentPanel.add(plateNumberIconLabel);
            currentPanel.add(usernameLabel);
            currentPanel.add(contactLabel);
            currentPanel.add(brandLabel);
            currentPanel.add(carModelLabel);
            currentPanel.add(plateNumberLabel);
            currentPanel.add(usernameTextField);
            currentPanel.add(contactTextField);
            currentPanel.add(brandTextField);
            currentPanel.add(carModelTextField);
            currentPanel.add(plateNumberTextField);

            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(null);
            mainFrame.setResizable(false);
            mainFrame.setVisible(true);
        }
    }

    // ========================================== TRANSACTION RECORDS SCREEN =============================================

    public static class TransactionRecordScreen{
        //Rounded Panel
        public class RoundedPane extends JPanel {

            private int shadowSize = 5;

            public RoundedPane() {
                // This is very important, as part of the panel is going to be transparent
                setOpaque(false);
            }

            @Override
            public Insets getInsets() {
                return new Insets(0, 0, 0, 0);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 200);
            }

            @Override
            protected void paintComponent(Graphics g) {
                int width = getWidth() - 1;
                int height = getHeight() - 1;

                Graphics2D g2d = (Graphics2D) g.create();
                TestDropShadowBorder.applyQualityProperties(g2d);
                Insets insets = getInsets();
                Rectangle bounds = getBounds();
                bounds.x = insets.left;
                bounds.y = insets.top;
                bounds.width = width - (insets.left + insets.right);
                bounds.height = height - (insets.top + insets.bottom);

                RoundRectangle2D shape = new RoundRectangle2D.Float(bounds.x, bounds.y, bounds.width, bounds.height, 20, 20);

                /**
                 * * THIS SHOULD BE CAHCED AND ONLY UPDATED WHEN THE SIZE OF THE
                 * COMPONENT CHANGES **
                 */
                BufferedImage img = TestDropShadowBorder.createCompatibleImage(bounds.width, bounds.height);
                Graphics2D tg2d = img.createGraphics();
                TestDropShadowBorder.applyQualityProperties(g2d);
                tg2d.setColor(Color.BLACK);
                tg2d.translate(-bounds.x, -bounds.y);
                tg2d.fill(shape);
                tg2d.dispose();
                BufferedImage shadow = TestDropShadowBorder.generateShadow(img, shadowSize, Color.BLACK, 0f);

                g2d.drawImage(shadow, shadowSize, shadowSize, this);

                g2d.setColor(getBackground());
                g2d.fill(shape);

                /**
                 * THIS ONE OF THE ONLY OCCASIONS THAT I WOULDN'T CALL
                 * super.paintComponent *
                 */
                getUI().paint(g2d, this);

                g2d.setColor(Color.GRAY);
                g2d.draw(shape);
                g2d.dispose();
            }
        }

        JFrame mainFrame;

        //Panels
        JPanel mainPanel,backPanel,yellowDesign,scrollPanel,contentPanel;


        //Labels
        static JLabel arrowIconLabel, recordLabel, transactionLabel, latestLabel, filterIconLabel, containerLabel;

        //Scroll Pane
        static JScrollPane scrollPane = new JScrollPane();

        //Variables
        static JLabel usernameIcon, contactIcon, brandIcon, modelIcon, plateNumberIcon,usernameLabel, contactLabel, brandLabel, modelLabel, plateNumberLabel,username, contact, brand, model, plateNumber;

        public TransactionRecordScreen(){

            JFrame mainFrame = new JFrame();

            mainPanel = new JPanel();
            backPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(20,20); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }

            };
            yellowDesign = new JPanel();
            scrollPanel = new JPanel();
            contentPanel = new JPanel();

            //Panels
            contentPanel = new JPanel();
            scrollPane = new JScrollPane(scrollPanel);

            //Labels
            transactionLabel = new JLabel("Transaction");
            recordLabel = new JLabel("Records");

            mainPanel.setBackground(new Color(34,34,34,255));
            mainPanel.setLayout(null);
            mainPanel.setBounds(0,0,448,796);

            backPanel.setBackground(new Color(34,34,34,255));
            backPanel.setBounds(27,20,45,45);
            backPanel.setLayout(null);
            backPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new AdminHomeScreen();
                    mainFrame.dispose();
                }
            });

            yellowDesign.setBounds(0,118,25,10);
            yellowDesign.setBackground(new Color(219,165,32,255));

            latestLabel = new JLabel(forSortLabel);

            if(FinishedUserUsernameArraylist.size() > 0 && forSortLabel.equals("Latest")){
                SortedFinishedUserUsernameArraylist.clear();
                SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);
                Collections.reverse(SortedFinishedUserUsernameArraylist);
            }

            scrollPanel.setBounds(0, 145, 448, 796);
            scrollPanel.setBackground(new Color(34, 34, 34, 255));
            scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));

            if(SortedFinishedUserUsernameArraylist.size() > 0){
                for(int i = 0; i < SortedFinishedUserUsernameArraylist.size(); i++) {

                    scrollPanel.add(Box.createRigidArea(new Dimension(0, 15)));
                    ImageIcon container = new ImageIcon(getClass().getResource("images\\gray_panel.png"));
                    Image image3 = container.getImage();
                    Image newContainer = image3.getScaledInstance(383, 230, Image.SCALE_SMOOTH);
                    container = new ImageIcon(newContainer);
                    containerLabel = new JLabel(container);
                    containerLabel.setLayout(null);
                    scrollPanel.add(containerLabel);

                    //Variables
                    //Username
                    ImageIcon userLogo = new ImageIcon(getClass().getResource("images\\username_icon.png"));
                    Image image4 = userLogo.getImage();
                    Image newUserLogo = image4.getScaledInstance(13, 15, Image.SCALE_SMOOTH);
                    userLogo = new ImageIcon(newUserLogo);
                    usernameIcon = new JLabel(userLogo);
                    usernameIcon.setBounds(10, 10, 45, 45);

                    usernameLabel = new JLabel("Username");
                    usernameLabel.setBounds(0, 0, 100, 50);
                    usernameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    usernameLabel.setForeground(Color.WHITE);
                    usernameLabel.setBounds(48, 17, 100, 30);

                    username = new JLabel(SortedFinishedUserUsernameArraylist.get(i).get("username"));
                    username.setBounds(0, 0, 100, 50);
                    username.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    username.setForeground(Color.WHITE);
                    username.setBounds(170, 17, 100, 30);

                    containerLabel.add(usernameIcon);
                    containerLabel.add(usernameLabel);
                    containerLabel.add(username);

                    //Contact Number
                    ImageIcon contactLogo = new ImageIcon(getClass().getResource("images\\Contact_icon.png"));
                    Image image5 = contactLogo.getImage();
                    Image newContactLogo = image5.getScaledInstance(25, 20, Image.SCALE_SMOOTH);
                    contactLogo = new ImageIcon(newContactLogo);
                    contactIcon = new JLabel(contactLogo);
                    contactIcon.setBounds(10, 50, 45, 45);

                    contactLabel = new JLabel("Contact Number");
                    contactLabel.setBounds(0, 0, 100, 50);
                    contactLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    contactLabel.setForeground(Color.WHITE);
                    contactLabel.setBounds(48, 57, 100, 30);

                    contact = new JLabel(SortedFinishedUserUsernameArraylist.get(i).get("mobileNumber"));
                    contact.setBounds(0, 0, 100, 50);
                    contact.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    contact.setForeground(Color.WHITE);
                    contact.setBounds(170, 57, 200, 30);

                    containerLabel.add(contactIcon);
                    containerLabel.add(contactLabel);
                    containerLabel.add(contact);

                    //Brand
                    ImageIcon brandLogo = new ImageIcon(getClass().getResource("images\\brand_icon.png"));
                    Image image6 = brandLogo.getImage();
                    Image newBrandIcon = image6.getScaledInstance(25, 20, Image.SCALE_SMOOTH);
                    brandLogo = new ImageIcon(newBrandIcon);
                    brandIcon = new JLabel(brandLogo);
                    brandIcon.setBounds(10, 90, 45, 45);

                    brandLabel = new JLabel("Brand");
                    brandLabel.setBounds(0, 0, 100, 50);
                    brandLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    brandLabel.setForeground(Color.WHITE);
                    brandLabel.setBounds(48, 97, 100, 30);

                    brand = new JLabel(SortedFinishedUserUsernameArraylist.get(i).get("carBrand"));
                    brand.setBounds(0, 0, 100, 50);
                    brand.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    brand.setForeground(Color.WHITE);
                    brand.setBounds(170, 97, 200, 30);

                    containerLabel.add(brandIcon);
                    containerLabel.add(brandLabel);
                    containerLabel.add(brand);

                    //City
                    ImageIcon modelLogo = new ImageIcon(getClass().getResource("images\\Car_Model_Icon.png"));
                    Image image7 = modelLogo.getImage();
                    Image newModelLogo = image7.getScaledInstance(25, 20, Image.SCALE_SMOOTH);
                    modelLogo = new ImageIcon(newModelLogo);
                    modelIcon = new JLabel(modelLogo);
                    modelIcon.setBounds(10, 130, 45, 45);

                    modelLabel = new JLabel("Model");
                    modelLabel.setBounds(0, 0, 100, 50);
                    modelLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    modelLabel.setForeground(Color.WHITE);
                    modelLabel.setBounds(48, 137, 100, 30);

                    model = new JLabel(SortedFinishedUserUsernameArraylist.get(i).get("carModel"));
                    model.setBounds(0, 0, 100, 50);
                    model.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    model.setForeground(Color.WHITE);
                    model.setBounds(170, 137, 200, 30);

                    containerLabel.add(modelIcon);
                    containerLabel.add(modelLabel);
                    containerLabel.add(model);

                    //Plate Number
                    ImageIcon plateNumberLogo = new ImageIcon(getClass().getResource("images\\PlateNumberIcon.png"));
                    Image image8 = plateNumberLogo.getImage();
                    Image newPlateNumberLogo = image8.getScaledInstance(25, 20, Image.SCALE_SMOOTH);
                    plateNumberLogo = new ImageIcon(newPlateNumberLogo);
                    plateNumberIcon = new JLabel(plateNumberLogo);
                    plateNumberIcon.setBounds(10, 170, 45, 45);

                    plateNumberLabel = new JLabel("Plate Number");
                    plateNumberLabel.setBounds(0, 0, 100, 50);
                    plateNumberLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    plateNumberLabel.setForeground(Color.WHITE);
                    plateNumberLabel.setBounds(48, 177, 100, 30);

                    plateNumber = new JLabel(SortedFinishedUserUsernameArraylist.get(i).get("carPlateNumber"));
                    plateNumber.setBounds(0, 0, 100, 50);
                    plateNumber.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    plateNumber.setForeground(Color.WHITE);
                    plateNumber.setBounds(170, 177, 200, 30);

                    containerLabel.add(plateNumberIcon);
                    containerLabel.add(plateNumberLabel);
                    containerLabel.add(plateNumber);
                }
            }

            contentPanel.setBounds(0, 145, 420, 796);
            contentPanel.setBackground(new Color(34, 34, 34, 255));
            contentPanel.setLayout(null);

            scrollPane.getVerticalScrollBar().setUnitIncrement(18);
            scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setBounds(25, 0, 420, 595);
            contentPanel.add(scrollPane);

            //Labels
            ImageIcon arrowIcon = new ImageIcon(getClass().getResource("images\\back_arrow.png"));
            Image image = arrowIcon.getImage();
            Image newArrowIcon = image.getScaledInstance(15,10, Image.SCALE_SMOOTH);
            arrowIcon = new ImageIcon(newArrowIcon);
            arrowIconLabel = new JLabel(arrowIcon);
            arrowIconLabel.setBounds(0,0,45,45);

            ImageIcon filter = new ImageIcon(getClass().getResource("images\\Filter_icon.png"));
            Image image2 = filter.getImage();
            Image newFilter = image2.getScaledInstance(27,27, Image.SCALE_SMOOTH);
            filter = new ImageIcon(newFilter);
            filterIconLabel = new JLabel(filter);
            filterIconLabel.setBounds(370,100,45,45);
            filterIconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new FilterScreen();
                    mainFrame.dispose();
                }
            });

            transactionLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
            transactionLabel.setForeground(Color.WHITE);
            transactionLabel.setBounds(150,20,200,25);

            recordLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
            recordLabel.setForeground(Color.WHITE);
            recordLabel.setBounds(165,55,200,25);

            latestLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            latestLabel.setForeground(Color.WHITE);
            latestLabel.setBounds(27,110,200,25);

            mainFrame.setSize(448, 796);
            mainFrame.setTitle("Cash-In");
            mainFrame.setLayout(null);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);
            mainFrame.setVisible(true);

            //Adding of Components

            //Main Frame
            mainFrame.add(mainPanel);

            //Main Panel
            mainPanel.add(backPanel);
            mainPanel.add(filterIconLabel);
            mainPanel.add(transactionLabel);
            mainPanel.add(recordLabel);
            mainPanel.add(latestLabel);
            mainPanel.add(yellowDesign);
            mainPanel.add(contentPanel);

            //Back Panel
            backPanel.add(arrowIconLabel);
        }
    }

    // ================================================ FILTER SCREEN ===================================================

    public static class FilterScreen{
        //DECLARATION
        //JFrame
        static JFrame mainFrame;

        //Panels
        static JPanel mainPanel, headerPanel, bodyPanel, applyFilterButtonPanel;

        //Labels
        static JLabel backButtonIconLabel, grayBorderIconLabel, headerTitleLabel, sortLabel, latestLabel, usernameLabel, contactNumLabel, brandLabel, modelLabel, plateNumLabel, applyLabel, design;

        //RButtons
        JRadioButton latestRButton, usernameRButton, contactNumRButton, brandRButton, modelRButton, plateNumRButton;

        //ImageIcon
        static ImageIcon backIcon, grayBorderIcon;

        public FilterScreen(){
            //GUI
            //JFrame
            mainFrame = new JFrame();

            //JLabels
            backButtonIconLabel = new JLabel();
            grayBorderIconLabel = new JLabel();
            headerTitleLabel = new JLabel("Filter");
            sortLabel = new JLabel("Sort by:");
            latestLabel = new JLabel("Latest");
            usernameLabel = new JLabel("Username");
            contactNumLabel = new JLabel("Contact No.");
            brandLabel = new JLabel("Brand");
            modelLabel = new JLabel("Model");
            plateNumLabel = new JLabel("Plate Number");
            applyLabel = new JLabel("Apply Filter");
            design = new JLabel("_______________________________________________________");

            //JRadioButton
            latestRButton = new JRadioButton("");
            usernameRButton = new JRadioButton("");
            contactNumRButton = new JRadioButton("");
            brandRButton = new JRadioButton("");
            modelRButton = new JRadioButton("");
            plateNumRButton = new JRadioButton("");

            //JPanels
            mainPanel = new JPanel();
            headerPanel = new JPanel();
            bodyPanel = new JPanel();
            applyFilterButtonPanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    //Draws the rounded panel with borders.
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                }
            };

            //ImageIcon
            ImageIcon backIcon  = new ImageIcon(getClass().getResource("images\\back_arrow.png"));
            Image image = backIcon.getImage(); // transform it
            Image newBackIcon = image.getScaledInstance(19, 16,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            backIcon = new ImageIcon(newBackIcon);  // transform it back

            ImageIcon grayBorderIcon  = new ImageIcon(getClass().getResource("images\\gray_border.png"));
            Image image2 = grayBorderIcon.getImage(); // transform it
            Image newGrayBorderIcon = image2.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            grayBorderIcon = new ImageIcon(newGrayBorderIcon);  // transform it back

            ButtonGroup group = new ButtonGroup();
            group.add(latestRButton);
            group.add(usernameRButton);
            group.add(contactNumRButton);
            group.add(brandRButton);
            group.add(modelRButton);
            group.add(plateNumRButton);

            // ----------------------------- COMPONENT SETTINGS -------------------------
            //Frame
            mainFrame.setTitle("Filter Screen");
            mainFrame.setSize(448, 796);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setBackground(new Color(34,34,34,255));

            //Panels
            mainPanel.setBounds(0,0,448,796);
            mainPanel.setBackground(new Color(34,34,34,255));
            mainPanel.setLayout(null);

            headerPanel.setBounds(0,0,448,100);
            headerPanel.setBackground(new Color(34,34,34,255));
            headerPanel.setLayout(null);

            bodyPanel.setBounds(0,100,448,500);
            bodyPanel.setBackground(new Color(34,34,34,255));
            bodyPanel.setLayout(null);

            applyFilterButtonPanel.setBounds(20,670,390,60);
            applyFilterButtonPanel.setBackground(new Color(241,179,28,255));
            applyFilterButtonPanel.setLayout(null);
            applyFilterButtonPanel.setFocusable(false);
            applyFilterButtonPanel.setOpaque(false);
            applyFilterButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(latestRButton.isSelected()){
                        SortedFinishedUserUsernameArraylist.clear();
                        SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);
                        Collections.reverse(SortedFinishedUserUsernameArraylist);

                        forSortLabel = "Latest";
                    }

                    else if(usernameRButton.isSelected()){
                        User temp;
                        SortedFinishedUserUsernameArraylist.clear();
                        SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);

                        for (int j = 0; j < SortedFinishedUserUsernameArraylist.size() - 1; j++) {
                            for (int i = j + 1; i < SortedFinishedUserUsernameArraylist.size(); i++) {
                                if (SortedFinishedUserUsernameArraylist.get(j).get("username").compareTo(SortedFinishedUserUsernameArraylist.get(i).get("username")) > 0) {
                                    temp = SortedFinishedUserUsernameArraylist.get(j);
                                    SortedFinishedUserUsernameArraylist.set(j, SortedFinishedUserUsernameArraylist.get(i));
                                    SortedFinishedUserUsernameArraylist.set(i, temp);
                                }
                            }
                        }

                        forSortLabel = "Username";
                    }

                    else if(contactNumRButton.isSelected()){
                        User temp;
                        SortedFinishedUserUsernameArraylist.clear();
                        SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);

                        for (int j = 0; j < SortedFinishedUserUsernameArraylist.size() - 1; j++) {
                            for (int i = j + 1; i < SortedFinishedUserUsernameArraylist.size(); i++) {
                                if (SortedFinishedUserUsernameArraylist.get(j).get("mobileNumber").compareTo(SortedFinishedUserUsernameArraylist.get(i).get("mobileNumber")) > 0) {
                                    temp = SortedFinishedUserUsernameArraylist.get(j);
                                    SortedFinishedUserUsernameArraylist.set(j, SortedFinishedUserUsernameArraylist.get(i));
                                    SortedFinishedUserUsernameArraylist.set(i, temp);
                                }
                            }
                        }

                        forSortLabel = "Contact Number";
                    }

                    else if(brandRButton.isSelected()){
                        User temp;
                        SortedFinishedUserUsernameArraylist.clear();
                        SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);

                        for (int j = 0; j < SortedFinishedUserUsernameArraylist.size() - 1; j++) {
                            for (int i = j + 1; i < SortedFinishedUserUsernameArraylist.size(); i++) {
                                if (SortedFinishedUserUsernameArraylist.get(j).get("carBrand").compareTo(SortedFinishedUserUsernameArraylist.get(i).get("carBrand")) > 0) {
                                    temp = SortedFinishedUserUsernameArraylist.get(j);
                                    SortedFinishedUserUsernameArraylist.set(j, SortedFinishedUserUsernameArraylist.get(i));
                                    SortedFinishedUserUsernameArraylist.set(i, temp);
                                }
                            }
                        }

                        forSortLabel = "Brand";
                    }

                    else if(modelRButton.isSelected()){
                        User temp;
                        SortedFinishedUserUsernameArraylist.clear();
                        SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);

                        for (int j = 0; j < SortedFinishedUserUsernameArraylist.size() - 1; j++) {
                            for (int i = j + 1; i < SortedFinishedUserUsernameArraylist.size(); i++) {
                                if (SortedFinishedUserUsernameArraylist.get(j).get("carModel").compareTo(SortedFinishedUserUsernameArraylist.get(i).get("carModel")) > 0) {
                                    temp = SortedFinishedUserUsernameArraylist.get(j);
                                    SortedFinishedUserUsernameArraylist.set(j, SortedFinishedUserUsernameArraylist.get(i));
                                    SortedFinishedUserUsernameArraylist.set(i, temp);
                                }
                            }
                        }

                        forSortLabel = "Model";
                    }

                    else if(plateNumRButton.isSelected()){
                        User temp;
                        SortedFinishedUserUsernameArraylist.clear();
                        SortedFinishedUserUsernameArraylist.addAll(FinishedUserUsernameArraylist);

                        for (int j = 0; j < SortedFinishedUserUsernameArraylist.size() - 1; j++) {
                            for (int i = j + 1; i < SortedFinishedUserUsernameArraylist.size(); i++) {
                                if (SortedFinishedUserUsernameArraylist.get(j).get("carPlateNumber").compareTo(SortedFinishedUserUsernameArraylist.get(i).get("carPlateNumber")) > 0) {
                                    temp = SortedFinishedUserUsernameArraylist.get(j);
                                    SortedFinishedUserUsernameArraylist.set(j, SortedFinishedUserUsernameArraylist.get(i));
                                    SortedFinishedUserUsernameArraylist.set(i, temp);
                                }
                            }
                        }

                        forSortLabel = "Plate Number";
                    }
                    new TransactionRecordScreen();
                    mainFrame.dispose();
                }
            });

            //Labels
            //Color for radio button
            Color color = new Color(34,34,34,255);

            backButtonIconLabel.setIcon(backIcon);
            backButtonIconLabel.setBounds(35,35,19,16);
            backButtonIconLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new TransactionRecordScreen();
                    mainFrame.dispose();
                }
            });

            grayBorderIconLabel.setIcon(grayBorderIcon);
            grayBorderIconLabel.setBounds(20, 20, 50, 50);

            headerTitleLabel.setFont(new Font("Century Gothic", Font.BOLD, 26));
            headerTitleLabel.setForeground(Color.WHITE);
            headerTitleLabel.setBounds(180,20,100,50);

            sortLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            sortLabel.setForeground(Color.WHITE);
            sortLabel.setBounds(20,0,100,50);

            design.setBounds(20,25,400,30);
            design.setForeground(Color.WHITE);

            latestLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
            latestLabel.setForeground(Color.WHITE);
            latestLabel.setBounds(20,60,200,50);

            usernameLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setBounds(20,120,200,50);

            contactNumLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
            contactNumLabel.setForeground(Color.WHITE);
            contactNumLabel.setBounds(20,180,200,50);

            brandLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
            brandLabel.setForeground(Color.WHITE);
            brandLabel.setBounds(20,240,200,50);

            modelLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
            modelLabel.setForeground(Color.WHITE);
            modelLabel.setBounds(20,300,200,50);

            plateNumLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
            plateNumLabel.setForeground(Color.WHITE);
            plateNumLabel.setBounds(20,360,200,50);

            applyLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            applyLabel.setForeground(Color.WHITE);
            applyLabel.setBounds(150,0,200,50);

            //RButtons
            latestRButton.setBackground(color);
            latestRButton.setBounds(380,60,30,50);

            usernameRButton.setBackground(color);
            usernameRButton.setBounds(380,120,30,50);

            contactNumRButton.setBackground(color);
            contactNumRButton.setBounds(380,180,30,50);

            brandRButton.setBackground(color);
            brandRButton.setBounds(380,240,30,50);

            modelRButton.setBackground(color);
            modelRButton.setBounds(380,300,30,50);

            plateNumRButton.setBackground(color);
            plateNumRButton.setBounds(380,360,30,50);

            //ADDING OF COMPONENTS
            //Main Frame
            mainFrame.add(mainPanel);

            //Main Panel
            mainPanel.add(headerPanel);
            mainPanel.add(bodyPanel);
            mainPanel.add(applyFilterButtonPanel);

            //Header Panel
            headerPanel.add(backButtonIconLabel);
            headerPanel.add(grayBorderIconLabel);
            headerPanel.add(headerTitleLabel);

            //Body Panel
            bodyPanel.add(sortLabel);
            bodyPanel.add(design);
            bodyPanel.add(latestLabel);
            bodyPanel.add(latestRButton);
            bodyPanel.add(usernameLabel);
            bodyPanel.add(usernameRButton);
            bodyPanel.add(contactNumLabel);
            bodyPanel.add(contactNumRButton);
            bodyPanel.add(brandLabel);
            bodyPanel.add(brandRButton);
            bodyPanel.add(modelLabel);
            bodyPanel.add(modelRButton);
            bodyPanel.add(plateNumLabel);
            bodyPanel.add(plateNumRButton);

            //Apply Filter Button Panel
            applyFilterButtonPanel.add(applyLabel);

            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(null);
            mainFrame.setResizable(false);
            mainFrame.setVisible(true);

        }
    }
}


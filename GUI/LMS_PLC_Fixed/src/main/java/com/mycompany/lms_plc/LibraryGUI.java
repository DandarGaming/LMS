package com.mycompany.lms_plc;

import javax.swing.JOptionPane;

/**
 * Main GUI for the Library Management System.
 *
 * Sub-frames live inside this one file as NetBeans generates them:
 *   LibraryGUI (main window)
 *     ├─ MemberMenu
 *     │    └─ AddMemberMenu
 *     ├─ BookMenu
 *     │    └─ LoanMenu
 *     └─ (dialogs via JOptionPane)
 */
public class LibraryGUI extends javax.swing.JFrame {

    public LibraryGUI() {
        initComponents();
        wireListeners();

        // Size and centre every window
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        MemberMenu.setSize(400, 350);
        MemberMenu.setLocationRelativeTo(null);

        BookMenu.setSize(500, 350);
        BookMenu.setLocationRelativeTo(null);

        AddMemberMenu.setSize(500, 400);
        AddMemberMenu.setLocationRelativeTo(null);

        LoanMenu.setSize(400, 300);
        LoanMenu.setLocationRelativeTo(null);
    }

    // ── Extra listener wiring (buttons added beyond the basic stubs) ──────────

    private void wireListeners() {

        // ── MemberMenu ──────────────────────────────────────────────────────
        AddNewMemberBtn.addActionListener(e -> {
            clearAddMemberFields();
            MemberMenu.setVisible(false);
            AddMemberMenu.setLocationRelativeTo(MemberMenu);
            AddMemberMenu.setVisible(true);
        });

        ViewMemberBtn.addActionListener(e -> {
            java.util.HashMap<String, Member> all = Loan.GetAllMembers();
            if (all.isEmpty()) {
                JOptionPane.showMessageDialog(MemberMenu, "No members in the system.",
                        "View Members", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            StringBuilder sb = new StringBuilder();
            all.values().forEach(m -> sb.append(m).append("\n─────────────────\n"));
            showScrollableDialog(MemberMenu, sb.toString(), "All Members");
        });

        EditMemberBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(MemberMenu,
                    "Enter the member's name to edit:");
            if (name == null || name.isBlank()) return;
            Member m = Loan.GetAllMembers().get(name.trim());
            if (m == null) {
                JOptionPane.showMessageDialog(MemberMenu, "Member not found: " + name,
                        "Edit Member", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newEmail = JOptionPane.showInputDialog(MemberMenu,
                    "Current email: " + m.getEmail() + "\nEnter new email (leave blank to keep):");
            if (newEmail != null && !newEmail.isBlank()) m.setEmail(newEmail.trim());
            JOptionPane.showMessageDialog(MemberMenu, "Member updated:\n" + m,
                    "Edit Member", JOptionPane.INFORMATION_MESSAGE);
        });

        // AddMemberMenu – Save & Go Back button
        AddMemberExitBtn.addActionListener(e -> handleSaveMember());

        // ── BookMenu ────────────────────────────────────────────────────────
        AddNewBookBtn.addActionListener(e -> handleAddBook());

        ViewBookBtn.addActionListener(e -> {                 // View Books
            java.util.HashMap<String, Book> all = Book.GetAllBooks();
            if (all.isEmpty()) {
                JOptionPane.showMessageDialog(BookMenu, "No books in the system.",
                        "View Books", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            StringBuilder sb = new StringBuilder();
            all.values().forEach(b -> sb.append(b).append("\n─────────────────\n"));
            showScrollableDialog(BookMenu, sb.toString(), "All Books");
        });

        LoanReturnBtn.addActionListener(e -> {               // Loan / Return Book
            BookMenu.setVisible(false);
            LoanMenu.setLocationRelativeTo(BookMenu);
            LoanMenu.setVisible(true);
        });

        EditBookBtn.addActionListener(e -> handleEditBook());
        DeleteBookBtn.addActionListener(e -> handleDeleteBook());

        ExitBookMenuBtn.addActionListener(e -> {
            BookMenu.setVisible(false);
            this.setVisible(true);
        });

        // ── LoanMenu ────────────────────────────────────────────────────────
        BorrowBookBtn.addActionListener(e -> handleBorrowBook());
        ReturnBookBtn.addActionListener(e -> handleReturnBook());

        LoanExitBtn.addActionListener(e -> {
            LoanMenu.setVisible(false);
            BookMenu.setVisible(true);
        });
    }

    // ── Action handlers ───────────────────────────────────────────────────────

    private void handleSaveMember() {
        String idText = MemberIDInput.getText().trim();
        String name   = NameInput.getText().trim();
        String email  = EmailInput.getText().trim();
        String type   = (String) MemberTypeComboBox.getSelectedItem();

        if (name.isEmpty() || idText.isEmpty() || "Select".equals(type)) {
            AddMemberMenu.setVisible(false);
            MemberMenu.setVisible(true);
            return;
        }

         if (!email.contains("@") || !email.contains(".")) {
    JOptionPane.showMessageDialog(AddMemberMenu,
            "Please enter a valid email address (must contain '@' and '.').",
            "Invalid Email", JOptionPane.ERROR_MESSAGE);
    return;
}
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(AddMemberMenu,
                    "Member ID must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = Loan.addMemberGUI(type, name, id, email);
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(AddMemberMenu,
                    type + " member \"" + name + "\" added successfully!",
                    "Member Added", JOptionPane.INFORMATION_MESSAGE);
            clearAddMemberFields();
            AddMemberMenu.setVisible(false);
            MemberMenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(AddMemberMenu,
                    "Error: " + result, "Add Member Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAddBook() {
        String title  = JOptionPane.showInputDialog(BookMenu, "Book title:");
        if (title == null || title.isBlank()) return;
        String author = JOptionPane.showInputDialog(BookMenu, "Author:");
        if (author == null || author.isBlank()) return;
        String isbn   = JOptionPane.showInputDialog(BookMenu, "ISBN:");
        if (isbn == null || isbn.isBlank()) return;

        String result = Book.addBookGUI(title.trim(), author.trim(), isbn.trim());
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(BookMenu,
                    "\"" + title.trim() + "\" added to the library!",
                    "Book Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(BookMenu,
                    "Error: " + result, "Add Book Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleEditBook() {
        String title = JOptionPane.showInputDialog(BookMenu, "Enter the book title to edit:");
        if (title == null || title.isBlank()) return;

        Book book = Book.GetBook(title.trim());
        if (book == null) {
            JOptionPane.showMessageDialog(BookMenu, "Book not found: " + title,
                    "Edit Book", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newAuthor = JOptionPane.showInputDialog(BookMenu,
                "Current author: " + book.getAuthor() + "\nNew author (leave blank to keep):");
        String newIsbn = JOptionPane.showInputDialog(BookMenu,
                "Current ISBN: " + book.getIsbn() + "\nNew ISBN (leave blank to keep):");

        String result = Book.editBookGUI(title.trim(),
                newAuthor == null ? "" : newAuthor.trim(),
                newIsbn   == null ? "" : newIsbn.trim());
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(BookMenu,
                    "\"" + title.trim() + "\" updated successfully.",
                    "Book Updated", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(BookMenu,
                    "Error: " + result, "Edit Book Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeleteBook() {
        if (Book.GetAllBooks().isEmpty()) {
            JOptionPane.showMessageDialog(BookMenu, "No books in the system.",
                    "Delete Book", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String title = JOptionPane.showInputDialog(BookMenu,
                "Enter the title of the book to delete:");
        if (title == null || title.isBlank()) return;

        int confirm = JOptionPane.showConfirmDialog(BookMenu,
                "Are you sure you want to delete \"" + title.trim() + "\"?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String result = Book.removeBookGUI(title.trim());
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(BookMenu,
                    "\"" + title.trim() + "\" removed from the library.",
                    "Book Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(BookMenu,
                    "Error: " + result, "Delete Book Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBorrowBook() {
        if (Loan.GetAllMembers().isEmpty()) {
            JOptionPane.showMessageDialog(LoanMenu, "No members in the system. Add a member first.",
                    "Borrow Book", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Book.GetAllBooks().isEmpty()) {
            JOptionPane.showMessageDialog(LoanMenu, "No books in the system. Add a book first.",
                    "Borrow Book", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String memberName = JOptionPane.showInputDialog(LoanMenu, "Member name:");
        if (memberName == null || memberName.isBlank()) return;
        String bookTitle = JOptionPane.showInputDialog(LoanMenu, "Book title:");
        if (bookTitle == null || bookTitle.isBlank()) return;

        String result = Loan.borrowGUI(memberName.trim(), bookTitle.trim());
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(LoanMenu,
                    memberName.trim() + " has borrowed \"" + bookTitle.trim() + "\".",
                    "Borrow Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(LoanMenu,
                    "Error: " + result, "Borrow Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleReturnBook() {
        String memberName = JOptionPane.showInputDialog(LoanMenu, "Member name:");
        if (memberName == null || memberName.isBlank()) return;

        String loans = Loan.getMemberLoansGUI(memberName.trim());
        String bookTitle = JOptionPane.showInputDialog(LoanMenu,
                loans + "\n\nEnter the title of the book to return:");
        if (bookTitle == null || bookTitle.isBlank()) return;

        String result = Loan.returnGUI(memberName.trim(), bookTitle.trim());
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(LoanMenu,
                    "\"" + bookTitle.trim() + "\" returned successfully.",
                    "Return Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(LoanMenu,
                    "Error: " + result, "Return Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showScrollableDialog(java.awt.Component parent, String text, String title) {
        javax.swing.JTextArea area = new javax.swing.JTextArea(text);
        area.setEditable(false);
        area.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(420, 320));
        JOptionPane.showMessageDialog(parent, scroll, title, JOptionPane.PLAIN_MESSAGE);
    }

    private void clearAddMemberFields() {
        MemberIDInput.setText("");
        NameInput.setText("");
        EmailInput.setText("");
        MemberTypeComboBox.setSelectedIndex(0);
    }

    // ── NetBeans-generated initComponents ─────────────────────────────────────

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MemberMenu = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        AddNewMemberBtn = new javax.swing.JToggleButton();
        ViewMemberBtn = new javax.swing.JToggleButton();
        EditMemberBtn = new javax.swing.JButton();
        DeleteMemberBtn = new javax.swing.JToggleButton();
        ExitMemberMenuBtn = new javax.swing.JButton();
        BookMenu = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        AddNewBookBtn = new javax.swing.JButton();
        ViewBookBtn = new javax.swing.JButton();
        LoanReturnBtn = new javax.swing.JButton();
        EditBookBtn = new javax.swing.JButton();
        DeleteBookBtn = new javax.swing.JButton();
        ExitBookMenuBtn = new javax.swing.JButton();
        AddMemberMenu = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        MemberIDInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        NameInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        EmailInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        MemberTypeComboBox = new javax.swing.JComboBox<>();
        AddMemberExitBtn = new javax.swing.JButton();
        LoanMenu = new javax.swing.JFrame();
        jLabel6 = new javax.swing.JLabel();
        BorrowBookBtn = new javax.swing.JButton();
        ReturnBookBtn = new javax.swing.JButton();
        LoanExitBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        MemberBtn = new javax.swing.JToggleButton();
        BookBtn1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();

        // ── MemberMenu ──────────────────────────────────────────────────────
        MemberMenu.setTitle("Member Menu");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36));
        jLabel3.setText("Member Menu");
        AddNewMemberBtn.setText("Add New Member");
        ViewMemberBtn.setText("View Members");
        EditMemberBtn.setText("Edit Member");
        DeleteMemberBtn.setText("Delete Member");
        DeleteMemberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMemberBtnActionPerformed(evt);
            }
        });
        ExitMemberMenuBtn.setText("Exit to Main Menu");
        ExitMemberMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMemberMenuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MemberMenuLayout = new javax.swing.GroupLayout(MemberMenu.getContentPane());
        MemberMenu.getContentPane().setLayout(MemberMenuLayout);
        MemberMenuLayout.setHorizontalGroup(
            MemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemberMenuLayout.createSequentialGroup()
                .addGap(77, 77, 77).addComponent(jLabel3)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MemberMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(MemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MemberMenuLayout.createSequentialGroup()
                        .addComponent(ExitMemberMenuBtn).addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MemberMenuLayout.createSequentialGroup()
                        .addGroup(MemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ViewMemberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddNewMemberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditMemberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeleteMemberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))))
        );
        MemberMenuLayout.setVerticalGroup(
            MemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemberMenuLayout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel3).addGap(27, 27, 27)
                .addComponent(AddNewMemberBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewMemberBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EditMemberBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DeleteMemberBtn).addGap(36, 36, 36)
                .addComponent(ExitMemberMenuBtn).addContainerGap(18, Short.MAX_VALUE))
        );

        // ── BookMenu ────────────────────────────────────────────────────────
        BookMenu.setTitle("Book Menu");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36));
        jLabel4.setText("Book Menu");
        AddNewBookBtn.setText("Add New Book");
        ViewBookBtn.setText("View Books");
        LoanReturnBtn.setText("Loan / Return Book");
        EditBookBtn.setText("Edit Book");
        DeleteBookBtn.setText("Delete Book");
        ExitBookMenuBtn.setText("Exit to Main Menu");

        javax.swing.GroupLayout BookMenuLayout = new javax.swing.GroupLayout(BookMenu.getContentPane());
        BookMenu.getContentPane().setLayout(BookMenuLayout);
        BookMenuLayout.setHorizontalGroup(
            BookMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BookMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddNewBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(ViewBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(LoanReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(EditBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(DeleteBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4).addGap(172, 172, 172))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitBookMenuBtn).addContainerGap())
        );
        BookMenuLayout.setVerticalGroup(
            BookMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookMenuLayout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddNewBookBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewBookBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoanReturnBtn).addGap(18, 18, 18)
                .addComponent(EditBookBtn).addGap(18, 18, 18)
                .addComponent(DeleteBookBtn).addGap(18, 18, 18)
                .addComponent(ExitBookMenuBtn).addGap(14, 14, 14))
        );

        // ── AddMemberMenu ───────────────────────────────────────────────────
        AddMemberMenu.setTitle("Add Member");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel5.setText("Add Member");
        jLabel9.setText("Member ID:");
        jLabel8.setText("Name:");
        jLabel7.setText("Email:");
        jLabel10.setText("Member Type:");
        MemberIDInput.setColumns(20);
        NameInput.setColumns(20);
        EmailInput.setColumns(20);
        MemberTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Select", "Student", "Staff"}));
        AddMemberExitBtn.setText("Save & Go Back");

        javax.swing.GroupLayout AddMemberMenuLayout = new javax.swing.GroupLayout(AddMemberMenu.getContentPane());
        AddMemberMenu.getContentPane().setLayout(AddMemberMenuLayout);
        AddMemberMenuLayout.setHorizontalGroup(
            AddMemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddMemberMenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(AddMemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(MemberIDInput, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(NameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(EmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addGroup(AddMemberMenuLayout.createSequentialGroup()
                        .addComponent(MemberTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(AddMemberExitBtn)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        AddMemberMenuLayout.setVerticalGroup(
            AddMemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddMemberMenuLayout.createSequentialGroup()
                .addGap(12, 12, 12).addComponent(jLabel5)
                .addGap(12, 12, 12).addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(MemberIDInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8).addComponent(jLabel8)
                .addGap(4, 4, 4)
                .addComponent(NameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8).addComponent(jLabel7)
                .addGap(4, 4, 4)
                .addComponent(EmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12).addComponent(jLabel10)
                .addGap(8, 8, 8)
                .addGroup(AddMemberMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MemberTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddMemberExitBtn))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        AddMemberMenu.pack();

        // ── LoanMenu ────────────────────────────────────────────────────────
        LoanMenu.setTitle("Loan Menu");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel6.setText("Loan / Return");
        BorrowBookBtn.setText("Borrow Book");
        ReturnBookBtn.setText("Return Book");
        LoanExitBtn.setText("Go Back");

        javax.swing.GroupLayout LoanMenuLayout = new javax.swing.GroupLayout(LoanMenu.getContentPane());
        LoanMenu.getContentPane().setLayout(LoanMenuLayout);
        LoanMenuLayout.setHorizontalGroup(
            LoanMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoanMenuLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addGroup(LoanMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoanMenuLayout.createSequentialGroup()
                        .addGroup(LoanMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BorrowBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ReturnBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(144, 144, 144))
                    .addGroup(LoanMenuLayout.createSequentialGroup()
                        .addComponent(jLabel6).addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoanMenuLayout.createSequentialGroup()
                        .addComponent(LoanExitBtn).addContainerGap())))
        );
        LoanMenuLayout.setVerticalGroup(
            LoanMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoanMenuLayout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel6).addGap(38, 38, 38)
                .addComponent(BorrowBookBtn).addGap(18, 18, 18)
                .addComponent(ReturnBookBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(LoanExitBtn).addContainerGap())
        );

        // ── Main window ─────────────────────────────────────────────────────
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Management System");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel1.setText("Library Management System");

        MemberBtn.setText("Member");
        MemberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemberBtnActionPerformed(evt);
            }
        });

        BookBtn1.setText("Book");
        BookBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87).addComponent(jLabel1)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(MemberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BookBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel1).addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MemberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BookBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ── Event handler stubs ───────────────────────────────────────────────────

    private void MemberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberBtnActionPerformed
        this.setVisible(false);
        MemberMenu.setLocationRelativeTo(this);
        MemberMenu.setVisible(true);
    }//GEN-LAST:event_MemberBtnActionPerformed

    private void BookBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookBtn1ActionPerformed
        this.setVisible(false);
        BookMenu.setLocationRelativeTo(this);
        BookMenu.setVisible(true);
    }//GEN-LAST:event_BookBtn1ActionPerformed

    private void ExitMemberMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMemberMenuBtnActionPerformed
        MemberMenu.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_ExitMemberMenuBtnActionPerformed

    private void DeleteMemberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteMemberBtnActionPerformed
        String name = JOptionPane.showInputDialog(MemberMenu, "Enter the member's name to delete:");
        if (name == null || name.isBlank()) return;

        int confirm = JOptionPane.showConfirmDialog(MemberMenu,
                "Are you sure you want to delete member \"" + name.trim() + "\"?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String result = Loan.removeMemberGUI(name.trim());
        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(MemberMenu,
                    "Member \"" + name.trim() + "\" deleted.",
                    "Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(MemberMenu,
                    "Error: " + result, "Delete Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteMemberBtnActionPerformed

    // ── Entry point ───────────────────────────────────────────────────────────

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryGUI.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new LibraryGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMemberExitBtn;
    private javax.swing.JFrame AddMemberMenu;
    private javax.swing.JButton AddNewBookBtn;
    private javax.swing.JToggleButton AddNewMemberBtn;
    private javax.swing.JToggleButton BookBtn1;
    private javax.swing.JFrame BookMenu;
    private javax.swing.JButton BorrowBookBtn;
    private javax.swing.JButton DeleteBookBtn;
    private javax.swing.JToggleButton DeleteMemberBtn;
    private javax.swing.JButton EditBookBtn;
    private javax.swing.JButton EditMemberBtn;
    private javax.swing.JTextField EmailInput;
    private javax.swing.JButton ExitBookMenuBtn;
    private javax.swing.JButton ExitMemberMenuBtn;
    private javax.swing.JButton LoanExitBtn;
    private javax.swing.JFrame LoanMenu;
    private javax.swing.JButton LoanReturnBtn;
    private javax.swing.JToggleButton MemberBtn;
    private javax.swing.JTextField MemberIDInput;
    private javax.swing.JFrame MemberMenu;
    private javax.swing.JComboBox<String> MemberTypeComboBox;
    private javax.swing.JTextField NameInput;
    private javax.swing.JButton ReturnBookBtn;
    private javax.swing.JButton ViewBookBtn;
    private javax.swing.JToggleButton ViewMemberBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}

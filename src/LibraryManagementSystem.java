
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class LibraryManagementSystem extends JFrame implements ActionListener {
    JTextField idField, titleField, authorField;
    JButton addBtn, viewBtn, updateBtn, deleteBtn;
    JTextArea displayArea;

    BookDAO bookDAO = new BookDAO();

    LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("Book ID:");
        idLabel.setBounds(20, 20, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 150, 25);
        add(idField);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 60, 80, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 60, 150, 25);
        add(titleField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(20, 100, 80, 25);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(100, 100, 150, 25);
        add(authorField);

        addBtn = new JButton("Add");
        addBtn.setBounds(270, 20, 100, 25);
        addBtn.addActionListener(this);
        add(addBtn);

        viewBtn = new JButton("View");
        viewBtn.setBounds(270, 60, 100, 25);
        viewBtn.addActionListener(this);
        add(viewBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(270, 100, 100, 25);
        updateBtn.addActionListener(this);
        add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(270, 140, 100, 25);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        displayArea = new JTextArea();
        displayArea.setBounds(20, 180, 440, 260);
        add(displayArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();

            if (e.getSource() == addBtn) {
                bookDAO.addBook(new Book(id, title, author));
                displayArea.setText("Book added successfully!\n");
            } else if (e.getSource() == viewBtn) {
                List<Book> books = bookDAO.getAllBooks();
                displayArea.setText("");
                for (Book b : books) {
                    displayArea.append("ID: " + b.getId() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor() + "\n");
                }
            } else if (e.getSource() == updateBtn) {
                bookDAO.updateBook(new Book(id, title, author));
                displayArea.setText("Book updated successfully!\n");
            } else if (e.getSource() == deleteBtn) {
                bookDAO.deleteBook(id);
                displayArea.setText("Book deleted successfully!\n");
            }
        } catch (Exception ex) {
            displayArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}

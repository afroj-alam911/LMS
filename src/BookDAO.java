import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public void addBook(Book book) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO books (id, title, author) VALUES (?, ?, ?)");
        pst.setInt(1, book.getId());
        pst.setString(2, book.getTitle());
        pst.setString(3, book.getAuthor());
        pst.executeUpdate();
    }

    public List<Book> getAllBooks() throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM books");
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author")));
        }
        return books;
    }

    public void updateBook(Book book) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("UPDATE books SET title=?, author=? WHERE id=?");
        pst.setString(1, book.getTitle());
        pst.setString(2, book.getAuthor());
        pst.setInt(3, book.getId());
        pst.executeUpdate();
    }

    public void deleteBook(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM books WHERE id=?");
        pst.setInt(1, id);
        pst.executeUpdate();
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLImportCSV {
    public static void main(String[] args) {
        // MySQL数据库连接信息
        String url = "jdbc:mysql://localhost:3306/url";
        String username = "root";
        String password = "123456";

        // CSV文件路径
        String csvFilePath = "D:\\code\\url\\data.csv";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            String sql = "INSERT INTO a (keyword, count) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // 跳过CSV文件的表头行
            reader.readLine();

            // 逐行读取CSV文件并插入数据库
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String keyword = data[0];
                int count = Integer.parseInt(data[1]);

                statement.setString(1, keyword);
                statement.setInt(2, count);
                statement.executeUpdate();
            }

            System.out.println("数据导入完成。");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}

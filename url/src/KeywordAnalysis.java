import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class
KeywordAnalysis{

    public static void main(String[] args) {
        // 读取文本文件
        String filePath = "D:\\code\\url\\data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, Integer> keywordCountMap = new HashMap<>();

            // 提取关键词并统计出现次数
            while ((line = reader.readLine()) != null) {
                // 判断是否为关键词记录行（以关键词开头）
                if (line.startsWith("计算机学院") || line.startsWith("教授") ||
                        line.startsWith("5月") || line.startsWith("6月")) {
                    String[] parts = line.split("\\s+"); // 拆分关键词和出现次数
                    String keyword = parts[0];
                    String countString = parts[2].replace("次", ""); // 去除次字符串
                    int count = Integer.parseInt(countString);

                    keywordCountMap.put(keyword, count);
                }
            }

            // 保存数据到 CSV 文件
            String csvFilePath = "D:\\code\\url\\data.csv";
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath), "UTF-8"))) {
                // 写入表头
                writer.write("关键词,出现次数");
                writer.newLine();

                // 写入数据行
                for (String keyword : keywordCountMap.keySet()) {
                    int count = keywordCountMap.get(keyword);
                    writer.write(keyword + "," + count);
                    writer.newLine();
                }

                System.out.println("数据已保存到 " + csvFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

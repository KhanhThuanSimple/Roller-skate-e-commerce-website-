package vn.edu.hcmuaf.fit.doanweb.service.review;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReviewService {
    private List<String> positiveWords = new ArrayList<>();
    private List<String> negativeWords = new ArrayList<>();

    // Constructor: Tải từ điển khi khởi tạo service
    public ReviewService() {
        loadKeywordDictionary("dictionary.txt");
        System.out.println("Đã tải từ điển: " + positiveWords.size() + " từ tích cực, "
                + negativeWords.size() + " từ tiêu cực");
    }

    // Triển khai phương thức đọc từ điển
    public void loadKeywordDictionary(String path) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("POSITIVE:")) {
                    String[] words = line.substring(9).split(",");
                    for (String word : words) {
                        positiveWords.add(word.trim().toLowerCase());
                    }
                } else if (line.startsWith("NEGATIVE:")) {
                    String[] words = line.substring(9).split(",");
                    for (String word : words) {
                        negativeWords.add(word.trim().toLowerCase());
                    }
                }
            }
        } catch (IOException | NullPointerException e) {
            // Dùng từ khóa mặc định nếu không đọc được file
            positiveWords = Arrays.asList("tốt", "đẹp", "êm", "hài lòng", "bền", "thoải mái");
            negativeWords = Arrays.asList("xấu", "kém", "đau", "tệ", "rách", "chậm");
            System.err.println("Không đọc được file từ điển, sử dụng từ khóa mặc định");
        }
    }

    public int evaluate(String review) {
        // Tiền xử lý text (giữ dấu tiếng Việt)
        String processed = review.toLowerCase()
                .replaceAll("[^a-z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ\\s]", "");

        // Tính điểm
        int score = 0;
        for (String word : positiveWords) {
            if (processed.contains(word)) score += 1;
        }
        for (String word : negativeWords) {
            if (processed.contains(word)) score -= 1;
        }

        // Chuyển điểm thành rating 1-5 sao
        return normalizeRating(score);
    }

    private int normalizeRating(int score) {
        if (score >= 3) return 5;
        else if (score == 2) return 4;
        else if (score == 1) return 3;
        else if (score == -1) return 2;
        else return 1; // score <= -2
    }
}
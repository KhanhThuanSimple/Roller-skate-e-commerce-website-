package vn.edu.hcmuaf.fit.doanweb.dao.model.review;

import weka.classifiers.Classifier;
import weka.core.*;
import weka.core.SerializationHelper;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ReviewClassifier {
    private Classifier classifier;

    public ReviewClassifier() throws Exception {
        // Sửa đường dẫn: thêm dấu / phía trước
        InputStream modelStream = getClass().getClassLoader().getResourceAsStream("review_model.model"); // Đã sửa
        if (modelStream == null) {
            throw new Exception("Không tìm thấy file model. Đặt file trong src/main/resources/ và đảm bảo đường dẫn chính xác!");
        }
        try {
            this.classifier = (Classifier) SerializationHelper.read(modelStream);
        } catch (Exception e) {
            throw new Exception("Lỗi khi đọc file model: " + e.getMessage(), e);
        } finally {
            if (modelStream != null) {
                modelStream.close(); // Đảm bảo đóng stream
            }
        }
    }

    // Tiền xử lý text tiếng Việt
    private String preprocessText(String text) {
        text = text.toLowerCase();
        // text = Normalizer.normalize(text, Normalizer.Form.NFD);
        // text = text.replaceAll("[^\\p{ASCII}]", "");
        text = text.replaceAll("[^a-z0-9\\sàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]", "");
        return text.trim();
    }

    // Sửa lại phương thức predictRating
    public int predictRating(String reviewText) throws Exception {
        // 1. Tiền xử lý (cần giống hệt khi train)
        String processedText = preprocessText(reviewText);

        // 2. Tạo attributes giống khi train
        FastVector attributes = new FastVector(2);
        attributes.addElement(new Attribute("text", (FastVector) null));

        // Tạo class values
        FastVector classValues = new FastVector();
        for (int i = 1; i <= 5; i++) classValues.addElement(String.valueOf(i));
        attributes.addElement(new Attribute("class", classValues));

        // 3. Tạo dataset
        Instances dataset = new Instances("reviewData", attributes, 0);
        dataset.setClassIndex(1);

        // 4. Tạo instance
        Instance instance = new DenseInstance(2);
        instance.setDataset(dataset);
        instance.setValue(0, processedText);

        // 5. Dự đoán
        double prediction = classifier.classifyInstance(instance);
        return (int) prediction + 1; // +1 vì Weka index từ 0
    }

    public static void main(String[] args) {
        try {
            System.setProperty("org.glassfish.web.jar", "jakarta.servlet-api-6.0.0.jar");

            ReviewClassifier classifier = new ReviewClassifier();
            // Thêm vào main method
            String[] testReviews = {
                    "Sản phẩm rất tốt, tôi rất thích",
                    "Tuyệt vời, hoàn hảo",
                    "Tạm được",
                    "Không tốt lắm",
                    "Rất tệ, không bao giờ mua nữa"
            };

            for (String review : testReviews) {
                int rating = classifier.predictRating(review);
                System.out.println("Review: " + review + " → Rating: " + rating);
            }
//            int rating = classifier.predictRating(testReview);
//            System.out.println("Review: \"" + testReview + "\"");
//            System.out.println("→ Điểm dự đoán: " + rating + " sao");
            System.out.println("Model class: " + classifier.getClass().getName());
            System.out.println("Model summary:\n" + classifier.toString());
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
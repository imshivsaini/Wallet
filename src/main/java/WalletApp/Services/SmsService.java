package WalletApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class SmsService {
    @Autowired
    private RestTemplate restTemplate;

    @Async("smsTaskExecutor")
    public CompletableFuture<String> sendSms(Long mobileNumber, String message) {
        String url = "https://api.kiwiplans.com:7002/send/sms";
        SmsRequest smsRequest = new SmsRequest(mobileNumber, message);

        try {
            SmsResponse response = restTemplate.postForObject(url, smsRequest, SmsResponse.class);
            return CompletableFuture.completedFuture(response != null ? "Sms Sent: " + response.getMessage() : "Error in sending SMS");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return CompletableFuture.completedFuture("Error in sending SMS: " + e.getMessage());
        }
    }

    static class SmsRequest {
        private Long mobile;
        private String message;

        public SmsRequest(Long mobile, String message) {
            this.mobile = mobile;
            this.message = message;
        }


        public Long getMobile() {
            return mobile;
        }

        public void setMobile(Long mobile) {
            this.mobile = mobile;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    static class SmsResponse {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

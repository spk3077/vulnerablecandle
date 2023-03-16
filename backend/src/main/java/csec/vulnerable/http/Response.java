package csec.vulnerable.http;

public class Response {
    private boolean success;
    private int code;
    private String message;

    // Constructor for success without a custom message
    public Response(boolean success) {
        this(success, success ? 200 : 400, success ? "Success" : "Failure");
    }

    // Constructor for success with a custom message
    public Response(boolean success, String message) {
        this(success, success ? 200 : 400, message);
    }

    // Constructor with custom success, code, and message
    public Response(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response [success=" + success + ", code=" + code + ", message=" + message + "]";
    }
}

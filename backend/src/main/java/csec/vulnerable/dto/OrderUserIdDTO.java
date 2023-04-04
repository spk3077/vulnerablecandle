package csec.vulnerable.dto;

public class OrderUserIdDTO {
    
    private int user_id;

    public OrderUserIdDTO() {
    }

    public OrderUserIdDTO(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "OrderUserIdDTO [user_id=" + user_id + "]";
    }
    
   
    
}
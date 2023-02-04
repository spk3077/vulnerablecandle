package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.Payment;
import csec.vulnerable.dao.PaymentDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService {

    @Autowired
    UserDao userDao;
	
	@Autowired
	PaymentDao paymentDao;
	
	public Payment getPayment(int id) {
		return paymentDao.findById(id).get();
	}
	
	public List<Payment> getPayments() {
		return paymentDao.findAll();
	}
    

/****************************************************************************************************************** */

    public boolean isValid(long cnumber) {
        return (thesize(cnumber) >= 13 && thesize(cnumber) <= 16) && (prefixmatch(cnumber, 4)
           || prefixmatch(cnumber, 5) || prefixmatch(cnumber, 37) || prefixmatch(cnumber, 6))
           && ((sumdoubleeven(cnumber) + sumodd(cnumber)) % 10 == 0);
	}
    
    public static int sumdoubleeven(long cnumber) {
        int sum = 0;
        String num = cnumber + "";
        for (int i = thesize(cnumber) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
        return sum;
    }
    // Return this cnumber if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int cnumber) {
        if (cnumber < 9)
            return cnumber;
        return cnumber / 10 + cnumber % 10;
    }
    // Return sum of odd-place digits in cnumber
    public static int sumodd(long cnumber) {
        int sum = 0;
        String num = cnumber + "";
        for (int i = thesize(cnumber) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }
    // Return true if the digit d is a prefix for cnumber
    public static boolean prefixmatch(long cnumber, int d) {
        return getprefx(cnumber, thesize(d)) == d;
    }
    // Return the number of digits in d
    public static int thesize(long d) {
        String num = d + "";
        return num.length();
    }
    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getprefx(long cnumber, int k) {
        if (thesize(cnumber) > k) {
            String num = cnumber + "";
            return Long.parseLong(num.substring(0, k));
        }
        return cnumber;
    }

/****************************************************************************************************************** */


    //post
	public Response addPayment(Payment payment,org.springframework.security.core.Authentication authentication) {
        if(isValid(payment.getCard_number())){
            payment.setUser(userDao.findByUsername(authentication.getName()));
            paymentDao.save(payment);
            return new Response(true);
        }else{
            return new Response(false,"card number is invaid");
        }
        
	}
	//put
	public Response changePayment(Payment payment,org.springframework.security.core.Authentication authentication) {
        Payment py = paymentDao.findById(payment.getId()).get();
        if(py.getUser().equals(userDao.findByUsername(authentication.getName()) )){
            if(isValid(payment.getCard_number())){
                py.setCard_number(payment.getCard_number());
                py.setOwner_name(payment.getOwner_name());
                py.setExpiration_date(payment.getExpiration_date());
                py.setSec_code(payment.getSec_code());
                paymentDao.save(py);
                return new Response(true);
            }else{
                return new Response(false,"card number is invaid");
            }
        }else{
            return new Response(false,"Autentication error");
        }
		
	}
	
	//delete
	public Response deletePayment(int id,org.springframework.security.core.Authentication authentication) {
		if(paymentDao.findById(id)!=null) {
            if(paymentDao.findById(id).get().getUser().equals(userDao.findByUsername(authentication.getName()))){
                paymentDao.deleteById(id);
			    return new Response(true);
            }else{
                return new Response(false,"Autentication error");
            }
		}else {
			return new Response(false,"Payment is not found");
		}
	}

}

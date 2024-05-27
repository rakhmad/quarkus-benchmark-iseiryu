package id.mamato;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.Date;

@Path("/")
public class GreetingResource {

    record AccountHolder(String id, String firstName, String lastName, Address address, String email) {}
    record Address(String street, String city, String zip, State state) {}
    public record SendMoneyRequest(AccountHolder from, AccountHolder to, BigDecimal amount, Date timeStamp) {}
    public record Receipt(String fromAccount, String toAccount, BigDecimal amount, Date timeStamp, String toAddress) {}
    enum State { CA, NY, WA, TX, FL, IL, PA, OH, GA, MI, NC, NJ, VA }

    @Path("/hi")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hi";
    }

    @Path("/send-money")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Receipt sendMoney(SendMoneyRequest request) {
        return new Receipt(
                request.from().firstName() + request.from().lastName(),
                request.to().firstName() + request.to().lastName(),
                request.amount(),
                new Date(),
                request.to().address().street() + "," + request.to().address().city() + ", " + request.to().address().state() + ", " + request.to().address().zip()
        );
    }
}


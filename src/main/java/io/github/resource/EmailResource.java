package io.github.resource;

import io.github.dto.EmailBodyDTO;
import io.github.dto.EmailDTO;
import io.github.dto.GenericResponseDTO;
import io.github.service.EmailService;
import io.github.service.impl.EmailServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/email")
public class EmailResource {

    private EmailService emailService = new EmailServiceImpl();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(EmailDTO emailDTO) {
        try {
            GenericResponseDTO genericResponse = new GenericResponseDTO();
            boolean verifyResponse = emailService.verifyReCaptchaChallenge(emailDTO.getGrecaptcha());

            if (!verifyResponse) {
                genericResponse.setMessage("Invalid reCaptcha");
                return Response.status(Response.Status.BAD_REQUEST).entity(genericResponse).build();
            }

            EmailBodyDTO emailBody = emailService.emailBodyBuilder(emailDTO);
            boolean emailSent = emailService.sendEmail(emailBody);

            if (!emailSent) {
                genericResponse.setMessage("Error on email service");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(genericResponse).build();
            }

            genericResponse.setMessage("Email sent");
            return Response.status(Response.Status.OK).entity(genericResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}

package io.github.dto;

import java.util.ArrayList;
import java.util.List;

public class SiteVerifyDTO {

    private String success;
    private List<String> errorCodes = new ArrayList<>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

}
